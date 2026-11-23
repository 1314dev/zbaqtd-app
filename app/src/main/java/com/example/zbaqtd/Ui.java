package com.example.zbaqtd;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

/** 纯代码构建界面，不依赖任何 XML 布局，避免 aapt 资源链接问题 */
final class Ui {

    static TextView title(Context c, String text, int color, int sp) {
        TextView t = new TextView(c);
        t.setText(text);
        t.setTextColor(color);
        t.setTextSize(sp);
        t.setGravity(Gravity.CENTER);
        t.setPadding(dp(c, 16), dp(c, 10), dp(c, 16), dp(c, 6));
        return t;
    }

    static TextView body(Context c, String text) {
        TextView t = new TextView(c);
        t.setText(text);
        t.setTextColor(Color.parseColor("#444444"));
        t.setTextSize(15);
        t.setLineSpacing(dp(c, 4), 1f);
        t.setPadding(dp(c, 20), dp(c, 8), dp(c, 20), dp(c, 20));
        return t;
    }

    static TextView button(Context c, String text, int bg, final Activity host, final Class<?> target) {
        TextView t = new TextView(c);
        t.setText(text);
        t.setTextColor(Color.WHITE);
        t.setTextSize(26);
        t.setGravity(Gravity.CENTER);
        t.setPadding(dp(c, 16), dp(c, 22), dp(c, 16), dp(c, 22));
        t.setBackground(round(bg, dp(c, 16)));
        t.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                host.startActivity(new android.content.Intent(host, target));
            }
        });
        return t;
    }

    static TextView ghostButton(Context c, String text, int border, final Activity host) {
        TextView t = new TextView(c);
        t.setText(text);
        t.setTextColor(border);
        t.setTextSize(16);
        t.setGravity(Gravity.CENTER);
        t.setPadding(dp(c, 16), dp(c, 14), dp(c, 16), dp(c, 14));
        GradientDrawable d = round(Color.WHITE, dp(c, 12));
        d.setStroke(dp(c, 2), border);
        t.setBackground(d);
        t.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                host.startActivity(new android.content.Intent(
                    android.content.Intent.ACTION_DIAL,
                    android.net.Uri.parse("tel:110")));
            }
        });
        return t;
    }

    static GradientDrawable round(int color, int radius) {
        GradientDrawable d = new GradientDrawable();
        d.setColor(color);
        d.setCornerRadius(radius);
        return d;
    }

    static int dp(Context c, int v) {
        return (int) (v * c.getResources().getDisplayMetrics().density + 0.5f);
    }

    static View gap(Context c, int h) {
        View v = new View(c);
        v.setMinimumHeight(dp(c, h));
        return v;
    }

    /** 外层滚动容器，小屏手机也不会被截断 */
    static ScrollView wrap(Context c, View content) {
        ScrollView sv = new ScrollView(c);
        sv.setFillViewport(true);
        sv.addView(content);
        return sv;
    }

    static LinearLayout column(Context c, int bg) {
        LinearLayout l = new LinearLayout(c);
        l.setOrientation(LinearLayout.VERTICAL);
        l.setBackgroundColor(bg);
        l.setPadding(dp(c, 20), dp(c, 40), dp(c, 20), dp(c, 32));
        return l;
    }

    static LinearLayout.MarginLayoutParams margins(Context c, View v, int top) {
        LinearLayout.MarginLayoutParams p =
            new LinearLayout.MarginLayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                                                LinearLayout.LayoutParams.WRAP_CONTENT);
        p.topMargin = dp(c, top);
        v.setLayoutParams(p);
        return p;
    }
}
