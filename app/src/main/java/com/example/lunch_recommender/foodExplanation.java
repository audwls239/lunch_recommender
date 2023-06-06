package com.example.lunch_recommender;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class foodExplanation extends AppCompatActivity {
    TextView foodNameView;
    TextView foodExplainView;
    String foodName;
    String foodExplain;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.food_explanation);

        foodName = getIntent().getStringExtra("name");
        foodExplain = getIntent().getStringExtra("explain");

        foodNameView = findViewById(R.id.foodName);
        foodExplainView = findViewById(R.id.foodExplain);

        foodNameView.setText(foodName);
        foodExplainView.setText(foodExplain);
    }

//    public void getVal() {
//        myDBHelper dbHelper = new myDBHelper(this, foodName);
//        SQLiteDatabase db = dbHelper.getReadableDatabase();
//
//        // 데이터베이스 읽기
//        Cursor cursor;
//        cursor = db.rawQuery("SELECT * FROM list;", null);
//
//        // 데이터 자료형으로 변환
//        while (cursor.moveToNext()) {
//            dbData tmp = new dbData(
//                    cursor.getString(0),
//                    cursor.getString(1),
//                    cursor.getString(2),
//                    cursor.getString(3)
//            );
//        }
//    }
}
