package com.example.lunch_recommender;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class myDBHelper extends SQLiteOpenHelper {
    private static String DB_NAME = "";
    private static String DB_PATH = "";
    private final Context mContext;

    public myDBHelper(Context context, String DB_NAME) {
        super(context, DB_NAME, null, 1);

//      경로 설정
        DB_PATH = "/data/data/" + context.getPackageName() + "/databases/";
        this.DB_NAME = DB_NAME;
        this.mContext = context;
        dbCopy();
    }

    private void dbCopy() {
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
        }
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}
