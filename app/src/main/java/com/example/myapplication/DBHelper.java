package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    public static final String TABLE_NAME="progresstable";
    public static final String DATABASE_NAME="progress.db";
    public DBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + " (ID INTEGER,NAME TEXT,POINTS INTEGER,LEVEL INTEGER,PROGRESS INTEGER)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
    }

public boolean putdata(int id,String name,int points,int level,int progress){
    SQLiteDatabase db = this.getWritableDatabase();
        long result = -1;
        ContentValues cv=new ContentValues();
        cv.put("ID",id);
        cv.put("NAME",name);
        cv.put("POINTS",points);
        cv.put("LEVEL",level);
        cv.put("PROGRESS",progress);
    result = db.insert(TABLE_NAME, null, cv);

    if (result == -1)
        return false;

    else

        return true;

    }

    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME, null);
        return res;
    }

    public void update_points(int Id,int Points){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put("POINTS",Points);
        db.update(TABLE_NAME,cv,"ID="+Id, null);
    }

    public void update_streak(int Id,int streak){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put("PROGRESS",streak);
        db.update(TABLE_NAME,cv,"ID="+Id, null);
    }
    public void update_level(int Id,int level){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put("LEVEL",level);
        db.update(TABLE_NAME,cv,"ID="+Id, null);
    }
}
