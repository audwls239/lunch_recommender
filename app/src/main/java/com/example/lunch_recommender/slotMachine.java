package com.example.lunch_recommender;

import android.content.Context;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.List;

public class slotMachine extends LinearLayout {
    private List<String> list;

    private TextView topText;
    private TextView midText;
    private TextView botText;

    public slotMachine(@NonNull Context context, List list) {
        super(context);

        setOrientation(VERTICAL);

        this.list = list;

        topText = new TextView(context);
        midText = new TextView(context);
        botText = new TextView(context);

        topText.setText("섹스");
        midText.setText("섹스");
        botText.setText("섹스");

        super.addView(this.topText);
        super.addView(this.midText);
        super.addView(this.botText);

    }
}
