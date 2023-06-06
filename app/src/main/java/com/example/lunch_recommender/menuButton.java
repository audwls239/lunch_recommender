package com.example.lunch_recommender;

import android.content.Context;

import androidx.annotation.NonNull;

public class menuButton extends androidx.appcompat.widget.AppCompatButton {
    String name = "";
    String showName = "";
    String explain = "";


    public menuButton(@NonNull Context context, dbData dbdata) {
        super(context);

        this.name = dbdata.name;
        this.showName = dbdata.showName;
        this.explain = dbdata.explain;
    }
}
