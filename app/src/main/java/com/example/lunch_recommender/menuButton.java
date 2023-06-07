package com.example.lunch_recommender;

import android.content.Context;

import androidx.annotation.NonNull;

public class menuButton extends androidx.appcompat.widget.AppCompatButton {
    String name;
    String showName;
    String explain;


    public menuButton(@NonNull Context context, String[] list) {
        super(context);

        this.name = list[0];
        this.showName = list[1];
        this.explain = list[3];
    }
}
