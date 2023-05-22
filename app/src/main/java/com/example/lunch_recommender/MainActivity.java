package com.example.lunch_recommender;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    List<String> ab = new ArrayList<String>();
    List<String> ba = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.i("console.log","메인 화면 시작");
        getVal();
        Log.i("console.log","메인 화면 1");
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
        Cursor cursor;
        cursor = db.rawQuery("SELECT * FROM list;", null);

        Log.i("console.log","getval 2");
        while (cursor.moveToNext()) {
            ab.add(cursor.getString(0));
            ba.add(cursor.getString(1));
            Log.i("console.log","if");
        }
        Log.i("console.log","getval 3");

        for (int i = 0; i < ab.size(); i++) {
            Log.i("console.log", ab.get(i));
        }

        cursor.close();
        dbHelper.close();
    }
}