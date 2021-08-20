package com.example.brainvireinfotechinclogin;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    public static final String TABLE_NAME = "login_info";
    public static final String COLUMN_1 = "NAME";
    public static final String COLUMN_2 = "EMAIL";
    public static final String COLUMN_3 = "PASSWORD";

    public DBHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, "MyDB.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+TABLE_NAME+"(NAME TEXT,EMAIL TEXT, PASSWORD TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String NAME,String EMAIL,String PASSWORD) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues Values = new ContentValues();
        Values.put(COLUMN_1, NAME);
        Values.put(COLUMN_2, EMAIL);
        Values.put(COLUMN_3, PASSWORD);

        long res = db.insert(TABLE_NAME, null, Values);
        if (res == -1) {
            return false;
        } else {
            return true;
        }
    }

    public Cursor showData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor get = db.rawQuery("SELECT * FROM "+TABLE_NAME,null);
        return get;
    }
}
