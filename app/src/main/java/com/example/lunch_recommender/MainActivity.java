package com.example.lunch_recommender;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    List<String> foodList = new ArrayList<String>();
//    List<String> ba = new ArrayList<String>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i("console.log","메인 화면 시작");
        super.onCreate(savedInstanceState);
        Log.i("console.log","메인 화면 1");
        setContentView(R.layout.activity_main);
        Log.i("console.log","메인 화면 2");

        getVal();
        Log.i("console.log","메인 화면 3");
        Button screenChangeBtn = (Button) findViewById(R.id.screenChangeBtn);
        screenChangeBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                setContentView(R.layout.wheel_screen);
            }
        });
        Log.i("console.log","메인 화면 종료");
    }

    public void getVal() {
        myDBHelper dbHelper = new myDBHelper(this);
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Log.i("console.log","getval 1");
        LinearLayout listLayout = findViewById(R.id.foodList);

        // 데이터베이스 읽어오기
        Cursor cursor;
        cursor = db.rawQuery("SELECT * FROM list;", null);

        // 데이터 자료형으로 변환
        Log.i("console.log","getval 2");
        while (cursor.moveToNext()) {
            foodList.add(cursor.getString(0));
//            ba.add(cursor.getString(1));
            Log.i("console.log","if");
        }

        Log.i("console.log","getval 3");

        for (int i = 0; i < foodList.size(); i++) {
            Log.i("console.log", foodList.get(i));

            Button btn = new Button(this);
            btn.setText(foodList.get(i));
            listLayout.addView(btn);
        }

        cursor.close();
        dbHelper.close();
    }
}