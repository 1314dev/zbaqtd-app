#!/data/data/com.termux/files/usr/bin/bash
set -e
H=/data/data/com.termux/files/home
SDK=$H/android-sdk; BT=$SDK/build-tools/34.0.0; P=$SDK/platforms/android-33
PROJ=$H/zbaqtd
AAPT2_URL="${AAPT2_URL:-https://github.com/rendiix/termux-apktool/releases/latest/download/aapt2-arm64}"
echo "== [1/8] 基础包 =="
pkg update -y >/dev/null 2>&1 || true
pkg install openjdk-21 git gradle unzip tar -y
echo "== [2/8] 建目录 =="
mkdir -p $BT/lld/bin $BT/renderscript $P $SDK/licenses
echo "== [3/8] 下 SDK 包 =="
cd $H
[ -f bt34.zip ]  || curl -fL --retry 3 -o bt34.zip  "https://mirrors.cloud.tencent.com/AndroidSDK/build-tools_r34-linux.zip"
[ -f plat33.zip ] || curl -fL --retry 3 -o plat33.zip "https://mirrors.cloud.tencent.com/AndroidSDK/platform-33-ext3_r03.zip"
ls -lh bt34.zip plat33.zip
echo "== [4/8] 解包 =="
rm -rf bt34 plat33
unzip -q -o bt34.zip -d bt34
unzip -q -o plat33.zip -d plat33
cp $(find bt34 -maxdepth 2 -type d -name "android-*" | head -1)/* $BT/ 2>/dev/null || true
cp $(find plat33 -maxdepth 2 -type d -name "android-*" | head -1)/* $P/ 2>/dev/null || true
echo "== [5/8] 补 ARM64 aapt2 =="
for N in aapt2-arm64 aapt2-linux-arm64 aapt2-aarch64; do
  curl -sfL -o a.bin "${AAPT2_URL%/*}/$N" && [ $(stat -c%s a.bin) -gt 1000000 ] && { mv a.bin $BT/aapt2; break; }
done
chmod 0755 $BT/aapt2
$BT/aapt2 version || { echo "!! aapt2 失败"; exit 1; }
echo "== [6/8] 占位 + 删 d8 =="
for t in aapt aidl apksigner d8 dexdump dx zipalign split-select; do
  printf '#!/system/bin/sh\nexit 0\n' > $BT/$t; chmod 0755 $BT/$t
done
rm -f $BT/d8 $BT/dexdump $BT/dx
printf '#!/system/bin/sh\nexit 0\n' > $BT/lld/bin/llvm-readelf; chmod 0755 $BT/lld/bin/llvm-readelf
printf '#!/system/bin/sh\nexit 0\n' > $BT/renderscript/llvm-rs-cc; chmod 0755 $BT/renderscript/llvm-rs-cc
printf 'Pkg.Name=Android SDK Build-Tools\nPkg.Revision=34.0.0\n' > $BT/source.properties
printf 'Pkg.Name=Android SDK Platform 33\nPkg.Revision=1\nAndroidVersion.ApiLevel=33\n' > $P/source.properties
echo "== [7/8] licenses + 配置 =="
printf '\n8933bad161af4178b1185d1a37fbf41ea5269c55\nd56f5187479451eabf01fb78af6dfcb131a6481e\n24333f8a63b6825ea9c5514f83c2829b004d1fee\n' > $SDK/licenses/android-sdk-license
echo "sdk.dir=$SDK" > $PROJ/local.properties
printf 'android.useAndroidX=true\nandroid.enableJetifier=false\nandroid.aapt2FromMavenOverride=%s/aapt2\nandroid.builder.sdkDownload=false\norg.gradle.jvmargs=-Xmx1024m -XX:MaxMetaspaceSize=512m\norg.gradle.parallel=false\norg.gradle.daemon=false\n' "$BT" > $PROJ/gradle.properties
echo "== [8/8] 验证 + 编译 =="
N=$(unzip -l $P/core-for-system-modules.jar | grep -c '\.class' || echo 0)
echo "class 数 = $N"
[ "$N" -gt 1000 ] || { echo "!! jar 不合格"; exit 1; }
cd $PROJ
[ -f gradlew ] || gradle wrapper --gradle-version 8.7
chmod +x gradlew
./gradlew assembleDebug --no-daemon --max-workers=1 2>&1 | tee build.log
APK=app/build/outputs/apk/debug/app-debug.apk
[ -f "$APK" ] || { echo "!! 编译失败"; exit 1; }
cp "$APK" /sdcard/Download/长辈安全通道.apk
echo "APK 已生成: $(stat -c%s "$APK") 字节"
