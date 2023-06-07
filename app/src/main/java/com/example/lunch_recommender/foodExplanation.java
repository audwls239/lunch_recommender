package com.example.lunch_recommender;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class foodExplanation extends AppCompatActivity {
    ArrayList<restaurantDB> restaurantList = new ArrayList<>();
    TextView foodNameView;
    TextView foodExplainView;
    String foodName;
    String foodShowName;
    String foodExplain;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.food_explanation);

        foodName = getIntent().getStringExtra("name");
        foodShowName = getIntent().getStringExtra("showName");
        foodExplain = getIntent().getStringExtra("explain");

        foodNameView = findViewById(R.id.foodShowName);
        foodExplainView = findViewById(R.id.foodExplain);

        foodNameView.setText(foodShowName);
        foodExplainView.setText(foodExplain);

        getVal();
    }

    public void getVal() {
        myDBHelper dbHelper = new myDBHelper(this, "restaurantList.db");
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        // 데이터베이스 읽기
        Cursor cursor;
        cursor = db.rawQuery("SELECT * FROM " + foodName + ";", null);

        // 데이터 자료형으로 변환
        while (cursor.moveToNext()) {
            restaurantDB tmp = new restaurantDB(
                    cursor.getString(0),
                    cursor.getString(1),
                    cursor.getString(2)
            );
            restaurantList.add(tmp);
        }

        LinearLayout listLayout = findViewById(R.id.linearLayout);

        for (int i = 0; i < restaurantList.size(); i++) {
            Button btn = new Button(this);

            btn.setText(restaurantList.get(i).name);
            btn.setOnClickListener(v -> {

            });
            listLayout.addView(btn);
        }

        cursor.close();
        dbHelper.close();
    }
}
