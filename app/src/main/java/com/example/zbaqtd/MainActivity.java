package com.example.zbaqtd;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;

/** 首页：欢迎来到长辈安全通道 + 请选择身份 + 三个入口 */
public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        LinearLayout root = Ui.column(this, Color.parseColor("#F2F6FC"));

        root.addView(Ui.title(this, "欢迎来到", Color.parseColor("#0B57C8"), 30));
        root.addView(Ui.title(this, "长辈安全通道", Color.parseColor("#0B57C8"), 38));
        root.addView(Ui.gap(this, 18));
        root.addView(Ui.title(this, "请选择身份", Color.parseColor("#FFC700"), 24));
        root.addView(Ui.gap(this, 28));

        TextView child = Ui.button(this, "子女端", Color.parseColor("#E31C1C"), this, ChildActivity.class);
        Ui.margins(this, child, 0);
        root.addView(child);

        TextView elder = Ui.button(this, "老人端", Color.parseColor("#1C9C3B"), this, ElderActivity.class);
        Ui.margins(this, elder, 18);
        root.addView(elder);

        TextView simple = Ui.button(this, "简易端", Color.parseColor("#8A44C9"), this, SimpleActivity.class);
        Ui.margins(this, simple, 18);
        root.addView(simple);

        root.addView(Ui.gap(this, 24));
        TextView tip = Ui.body(this, "长辈安全通道 · 一键求助\n子女端看护 · 老人端求助 · 简易端大字模式");
        tip.setGravity(android.view.Gravity.CENTER);
        root.addView(tip);

        setContentView(Ui.wrap(this, root));
    }
}
