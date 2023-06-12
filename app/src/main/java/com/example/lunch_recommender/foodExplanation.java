package com.example.lunch_recommender;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
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
                    cursor.getString(0)
            );
            restaurantList.add(tmp);
        }

        LinearLayout listLayout = findViewById(R.id.linearLayout);

        for (int i = 0; i < restaurantList.size(); i++) {
            // 리니어 레이아웃 생성 & 설정
            LinearLayout itemLayout = new LinearLayout(this);
            itemLayout.setOrientation(LinearLayout.HORIZONTAL);

            LinearLayout.LayoutParams itemParams = new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
            );
            itemLayout.setLayoutParams(itemParams);

            // 이미지 뷰 생성 & 설정
            ImageView imageView = new ImageView(this);
            LinearLayout.LayoutParams imageParams = new LinearLayout.LayoutParams(
                    420, // Adjust the width and height as needed
                    200
            );
            imageView.setLayoutParams(imageParams);
            imageView.setImageResource(getImageResourceID(i)); // Set the image resource based on index
            itemLayout.addView(imageView);

            // 버튼 생성 & 설정
            Button btn = new Button(this);
            btn.setLayoutParams(new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    1.0f
            ));
            btn.setText(restaurantList.get(i).name);
            itemLayout.addView(btn);

            listLayout.addView(itemLayout);
        }

        cursor.close();
        dbHelper.close();
    }
    private int getImageResourceID(int index) {
        int[] imageResources = {
                R.drawable.chicken_brand_bbq,
                R.drawable.chicken_brand_bhc,
                R.drawable.chicken_brand_kyo,
                R.drawable.chicken_brand_goob,
                R.drawable.chicken_brand_pura,
                R.drawable.chicken_brand_no,
                R.drawable.chicken_brand_nene,
                R.drawable.chicken_brand_out,
        };

        return imageResources[index % imageResources.length];
    }
}
