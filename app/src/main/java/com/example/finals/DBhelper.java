package com.example.finals;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;

    public class DBhelper extends SQLiteOpenHelper {

    public static final String DBNAME = "finals.db";
    public DBhelper( Context context) {
        super(context, "finals.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL("create table users(username TEXT primary key, email TEXT, password TEXT)");
    }

    @Override
    public  void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("drop table if exists users");
    }

    public Boolean createUser(String username, String email, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("username", username);
        values.put("email", email);
        values.put("password", password);

        long res = db.insert("users", null, values);

        if(res == -1) return  false;
        else return true;

    }

    public Boolean auth (String username, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from users where username=? and password=?", new String[] {username, password});
        if(cursor.getCount() > 0) return  true;
        else return  false;
    }

    public Boolean checkUsername (String username){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from users where username =?", new String[] {username});
        if(cursor.getCount() > 0) return  true;
        else  return false;
    }

    @SuppressLint("Range")
    public String getEmail(String username){
        SQLiteDatabase db = this.getWritableDatabase();
        String email;
        Cursor cursor = db.rawQuery("select email from users where username =? ", new String[] {username});
        cursor.moveToFirst();
        email = cursor.getString(cursor.getColumnIndex("email"));
        return  email;
    }
}
