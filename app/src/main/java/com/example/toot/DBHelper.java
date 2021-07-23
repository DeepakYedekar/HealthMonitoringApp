package com.example.toot;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DBNAME ="lord.db";

    public DBHelper(@Nullable  Context context)
    {
        super(context,DBNAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase MyDB) {
        String q="create Table users(_id integer primary key autoincrement, username TEXT, password TEXT,email TEXT)";
        MyDB.execSQL(q);
    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int oldVersion, int newVersion) {
        MyDB.execSQL("drop Table if exists users");
        onCreate(MyDB);
    }
    public Boolean insertData(String username, String password,String email) {
        SQLiteDatabase MyDB =this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("username",username);
        contentValues.put("email",email);
        contentValues.put("password",password);
        long result= MyDB.insert("users",null, contentValues);
        if(result==-1) return false;
        else
            return  true;


    }
    public  boolean checkusername(String username){
        SQLiteDatabase MyDB =this.getWritableDatabase();
        Cursor cursor =MyDB.rawQuery("Select * from users where username = ?", new String[] {username});
        if (cursor.getCount()>0)
            return  true;
        else
            return false;

    }
    public  boolean checkusernamepassword(String username, String password)
    { SQLiteDatabase MyDB =this.getWritableDatabase();
        Cursor cursor =MyDB.rawQuery("Select * from users where username=? and password=?",new String[]{username,password});
        if(cursor.getCount()>0)
            return true;
        else
            return  false;

    }

    public Boolean update(String username,String email,String password) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", username);
        contentValues.put("email", email);
        contentValues.put("password",password);
        Cursor cursor = MyDB.rawQuery("select * from users where username=?", new String[]{username});
        if (cursor.getCount() > 0) {
            long r = MyDB.update("users", contentValues, "username=?", new String[]{username});
            if (r == -1) return false;
            else
                return true;
        } else
            return false;
    }

    public  Boolean delete_data(String username){
        SQLiteDatabase MyDB= this.getWritableDatabase();
        Cursor cursor=MyDB.rawQuery("select * from users where username=?",new String[]{username});
        if (cursor.getCount()>0){
            long r=MyDB.delete("users","username=?",new String[]{username});
            if (r==-1)
                return false;
            else
                return true;
        }else
            return false;

    }

    public Cursor getinfo()
    {
        SQLiteDatabase Mydb=this.getWritableDatabase();
        Cursor cursor=Mydb.rawQuery("select * from users ",null);
        return cursor;
    }

}
