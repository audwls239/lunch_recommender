package com.example.lunch_recommender;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    static final List<String[]> foodList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//      DB 호출
        foodList.clear();
        getVal();
        
//      돌림판 화면 클릭 리스너 설정
        Button screenChangeBtn = (Button) findViewById(R.id.screenChangeBtn);
        screenChangeBtn.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, slotMachineScreen.class);

            startActivity(intent);
        });
    }

//
    public void getVal() {
        myDBHelper dbHelper = new myDBHelper(this, "foodList.db");
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        // 데이터베이스 읽기
        Cursor cursor;
        cursor = db.rawQuery("SELECT * FROM list;", null);

        // 데이터 자료형으로 변환
        while (cursor.moveToNext()) {
            String[] tmp = new String[]{
                cursor.getString(0),
                cursor.getString(1),
                cursor.getString(2),
                cursor.getString(3)
            };
            foodList.add(tmp);
        }


        // 데이터 개수 별로 메뉴 버튼 생성
        LinearLayout listLayout = findViewById(R.id.foodList);

        for (int i = 0; i < foodList.size(); i++) {
            menuButton btn = new menuButton(this, foodList.get(i));
            btn.setText(btn.showName);
            btn.setOnClickListener(v -> {
                Intent intent = new Intent(MainActivity.this, foodExplanation.class);

                intent.putExtra("name", btn.name);
                intent.putExtra("showName", btn.showName);
                intent.putExtra("explain", btn.explain);
                startActivity(intent);
            });
            listLayout.addView(btn);
        }

        cursor.close();
        dbHelper.close();
    }
}

