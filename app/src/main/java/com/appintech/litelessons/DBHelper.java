package com.appintech.litelessons;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    DBHelper(Context context){
        super(context,"student_data_base",null,1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE stu_acc(uname text,email text PRIMARY KEY, pwd text )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean insertData(String uName,String pwd, String email){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues c = new ContentValues();
        c.put("uname",uName);
        c.put("email",email);
        c.put("pwd", pwd);
        long ins = db.insert("stu_acc",null,c);
        if (ins == -1)
            return false;
        return true;

    }
    public Boolean chkMail(String mail){
        SQLiteDatabase ob = this.getReadableDatabase();

        Cursor cur =  ob.rawQuery("select * from stu_acc where email =?", new String[]{mail});
        if (cur.getCount()>0) return true;
        return false;
    }

    public Boolean chkMailPass(String mail, String pass){
        SQLiteDatabase ob = this.getReadableDatabase();
        Cursor cur = ob.rawQuery("select * from stu_acc where email=? and pwd=?", new String[]{mail,pass});
        if(cur.getCount()>0) return true;
        return false;
    }
}
