package com.example.lunch_recommender;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
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
        ImageButton screenChangeBtn = (ImageButton) findViewById(R.id.screenChangeBtn);
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

        cursor.close();
        dbHelper.close();

        // 데이터 개수 별로 메뉴 버튼 생성
        LinearLayout listLayout = findViewById(R.id.foodList);
        int rowSize = 2; // 한 줄에 보여줄 이미지 버튼 개수
        int rowCount = (int) Math.ceil((double) foodList.size() / rowSize); // 필요한 줄 수 계산

        //
        for (int i = 0; i < rowCount; i++) {
            LinearLayout rowLayout = new LinearLayout(this);
            rowLayout.setOrientation(LinearLayout.HORIZONTAL);

            LinearLayout.LayoutParams rowParams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );
            rowLayout.setLayoutParams(rowParams);

            //
            for (int j = 0; j < rowSize; j++) {
                int index = i * rowSize + j;
                if (index < foodList.size()) {
                    String[] foodData = foodList.get(index);

                    LinearLayout.LayoutParams btnParams = new LinearLayout.LayoutParams(
                            0,
                            LinearLayout.LayoutParams.WRAP_CONTENT,
                            1.0f
                    );

                    ImageButton btn = new ImageButton(this);
                    btn.setImageResource(getImageResourceID(index)); // 이미지 리소스 ID를 설정
                    btn.setLayoutParams(btnParams);
                    btn.setOnClickListener(v -> {
                        Intent intent = new Intent(MainActivity.this, foodExplanation.class);
                        intent.putExtra("name", foodData[0]);
                        intent.putExtra("showName", foodData[1]);
                        intent.putExtra("explain", foodData[3]);
                        startActivity(intent);
                    });

                    rowLayout.addView(btn);
                }
            }

            listLayout.addView(rowLayout);
        }
    }
    private int getImageResourceID(int index) {
        int[] imageResources = {
                R.drawable.food_list_chicken,
                R.drawable.food_list_pizza,
                R.drawable.food_list_hamburger,
                R.drawable.food_list_sandwich,
                R.drawable.food_list_jajangmyeon,
                R.drawable.food_list_champon
        };

        return imageResources[index % imageResources.length];
    }
}

