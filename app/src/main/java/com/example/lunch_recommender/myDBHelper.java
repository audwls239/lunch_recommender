package com.example.lunch_recommender;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class myDBHelper extends SQLiteOpenHelper {
    private final static String DB_NAME = "menuList.db";
    private static String DB_PATH = "";
    private Context mContext;

    public myDBHelper(Context context) {
        super(context, DB_NAME, null, 1);


        Log.i("console.log","myDBHelper 시작");
        DB_PATH = "/data/data/" + context.getPackageName() + "/databases/";
        this.mContext = context;
        dataBaseCheck();

        Log.i("console.log","myDBHelper 종료");
    }

    private void dataBaseCheck() {
        Log.i("console.log","dataBaseCheck");

        File dbFile = new File(DB_PATH + DB_NAME);
        if (!dbFile.exists()) {
            dbCopy();
            Log.d("dbReader","DB 복사 완료.");
        }
    }

    private void dbCopy() {
        Log.i("console.log","dbCopy");

        try {
            File folder = new File(DB_PATH);
            if (!folder.exists()) {
                folder.mkdir();
            }

            InputStream inputStream = mContext.getAssets().open(DB_NAME);
            String out_filename = DB_PATH + DB_NAME;
            OutputStream outputStream = new FileOutputStream(out_filename);
            byte[] mBuffer = new byte[1024];
            int mLength;
            while ((mLength = inputStream.read(mBuffer)) > 0) {
                outputStream.write(mBuffer,0,mLength);
            }
            outputStream.flush();;
            outputStream.close();
            inputStream.close();

        } catch (IOException e) {
            e.printStackTrace();
            Log.d("dbCopy","IOException 발생함");
        }
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i("dbReader", "onCreate()");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.i("dbReader", "onUpgrade()");
    }
}
