package com.example.zbaqtd;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;

/** 简易端：极简大字模式，只有两个超大按钮 */
public class SimpleActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        LinearLayout root = Ui.column(this, Color.parseColor("#FAF6FF"));

        root.addView(Ui.title(this, "简易端", Color.parseColor("#8A44C9"), 32));
        root.addView(Ui.gap(this, 30));

        TextView sos = Ui.button(this, "求助", Color.parseColor("#E31C1C"), this, MainActivity.class);
        android.view.ViewGroup.LayoutParams lp = sos.getLayoutParams();
        lp.height = Ui.dp(this, 170);
        sos.setLayoutParams(lp);
        sos.setTextSize(46);
        root.addView(sos);

        TextView back = Ui.button(this, "回家", Color.parseColor("#8A44C9"), this, MainActivity.class);
        android.view.ViewGroup.LayoutParams lp2 = back.getLayoutParams();
        lp2.height = Ui.dp(this, 120);
        back.setLayoutParams(lp2);
        Ui.margins(this, back, 24);
        root.addView(back);

        setContentView(Ui.wrap(this, root));
    }
}
