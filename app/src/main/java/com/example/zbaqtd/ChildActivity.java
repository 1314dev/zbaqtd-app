package com.example.zbaqtd;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;

/** 子女端：查看长辈状态、常用看护入口 */
public class ChildActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        LinearLayout root = Ui.column(this, Color.parseColor("#FFF6F6"));

        root.addView(Ui.title(this, "子女端", Color.parseColor("#E31C1C"), 30));
        root.addView(Ui.body(this,
            "这里是子女端。你可以：\n\n"
          + "· 查看长辈当前位置与在线状态\n"
          + "· 接收长辈的一键求助提醒\n"
          + "· 设置紧急联系人与用药提醒\n"
          + "· 远程协助长辈操作手机\n\n"
          + "长辈按下求助键后，你会第一时间收到通知。"));
        root.addView(Ui.gap(this, 8));

        TextView call = Ui.ghostButton(this, "拨给长辈", Color.parseColor("#E31C1C"), this);
        root.addView(call);

        setContentView(Ui.wrap(this, root));
    }
}
