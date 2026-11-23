package com.example.zbaqtd;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;

/** 老人端：大号一键求助 */
public class ElderActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        LinearLayout root = Ui.column(this, Color.parseColor("#F3FCF5"));

        root.addView(Ui.title(this, "老人端", Color.parseColor("#1C9C3B"), 30));
        root.addView(Ui.body(this, "遇到紧急情况，按下面这个大按钮"));
        root.addView(Ui.gap(this, 16));

        TextView sos = Ui.button(this, "求助", Color.parseColor("#E31C1C"), this, MainActivity.class);
        android.view.ViewGroup.LayoutParams lp = sos.getLayoutParams();
        lp.height = Ui.dp(this, 150);
        sos.setLayoutParams(lp);
        sos.setTextSize(40);
        root.addView(sos);

        root.addView(Ui.gap(this, 20));
        TextView call = Ui.ghostButton(this, "拨打 110", Color.parseColor("#1C9C3B"), this);
        root.addView(call);

        setContentView(Ui.wrap(this, root));
    }
}
