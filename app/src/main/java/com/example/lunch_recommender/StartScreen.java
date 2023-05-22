package com.example.lunch_recommender;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

public class StartScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_screen);

        Log.i("console.log","로딩 화면 시작");

        // 3초 후 화면 전환
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(StartScreen.this, MainActivity.class);
                startActivity(intent);

                finish();
                Log.i("console.log","로딩 화면 종료");
            }
        }, 1000);
    }
}
