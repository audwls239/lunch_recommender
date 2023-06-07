package com.example.lunch_recommender;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;


public class slotMachineScreen extends AppCompatActivity {
    slotMachine slotMachine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.slot_screen);

        slotMachine = new slotMachine(this, MainActivity.foodList);

        ConstraintLayout tmp = findViewById(R.id.main);
        tmp.addView(slotMachine);
    }
}