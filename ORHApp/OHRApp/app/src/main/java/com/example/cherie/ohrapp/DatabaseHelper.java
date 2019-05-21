package com.example.cherie.ohrapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.widget.Button;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper{
    public DatabaseHelper(Context context){
        super(context, "hro.db", null, 1);
    }

    ///     CREATING DB      ///
    @Override
    public void onCreate(SQLiteDatabase db) {
        ///6 tables are created
        db.execSQL("Create table codes(_id integer primary key autoincrement, code text, date text, class text, course text, teacher text)");
        db.execSQL("Create table comments (_id integer primary key autoincrement, filename text not null unique )");
        db.execSQL("Create table comparison (_id integer primary key autoincrement, otherteacher text not null, course text not null )");
        db.execSQL("Create table login (_id integer primary key autoincrement, username text not null unique, password text not null, teacher text not null, student text not null)");
        db.execSQL("Create table reasons ( _id integer primary key autoincrement, teacher text , course text , reason text )");
        db.execSQL("Create table teachers (_id integer primary key autoincrement, username text not null, course text not null )");

        ContentValues contentValues = new ContentValues();
        contentValues.put("username", "ADM");
        contentValues.put("password", "123");
        contentValues.put("teacher", "yes");
        contentValues.put("student", "yes");
        db.insert("login",null,contentValues);

        /// shoutout to my niggas
        /// https://www.youtube.com/watch?v=HiUGWXe6Yzk
        ContentValues p1 = new ContentValues();
        p1.put("username", "mehdi");
        p1.put("password", "g");
        p1.put("teacher", "no");
        p1.put("student", "yes");
        db.insert("login",null,p1);

        ContentValues p2 = new ContentValues();
        p2.put("username", "alex");
        p2.put("password", "123");
        p2.put("teacher", "no");
        p2.put("student", "yes");
        db.insert("login",null,p2);

    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists codes");
    }



    ///     RETRIEVING FROM DB      ///

    //GET COUNT
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public int countReasons(String username, String course, String reason){
        int reasonCount = 0;
        SQLiteDatabase db = this.getReadableDatabase();
        //run query
        Cursor cursor = db.rawQuery("select count(*) from reasons where teacher =? and course=? and reason =?", new String[]{username, course, reason});

        if (cursor.getCount() > 0){
            cursor.moveToFirst();
            reasonCount = cursor.getInt(0);
        }
        cursor.close();

        return reasonCount;
    }








    //teacher name
    public ArrayList<String> getTeacherName(String codename) {
        ArrayList<String> array_list = new ArrayList<String>();

        ///puts column value in array to be later accessed as string
        SQLiteDatabase db = this.getReadableDatabase();

        //searches for value where entered code is present
        Cursor res =  db.rawQuery( "select * from codes where code=?", new String[]{codename});
        res.moveToFirst();
        while(res.isAfterLast() == false){

            /*gets teacher name where code matched output will be [teacherusername]
            substrings will be used to extract only the username e.g [ADM] => ADM*/
            array_list.add(res.getString(res.getColumnIndex("teacher")));
            res.moveToNext();
        }
        return array_list;
    }

    //course
    public ArrayList<String> getCourse(String codename) {
        ArrayList<String> array_list = new ArrayList<String>();
        ///puts column value in array to be later accessed as string
        SQLiteDatabase db = this.getReadableDatabase();
        //searches for value where entered code is present
        Cursor res =  db.rawQuery( "select * from codes where code=?", new String[]{codename});
        res.moveToFirst();
        while(res.isAfterLast() == false){
            /*gets course name where code matched output will be [0/0/0]
            substrings will be used to extract only the course name e.g [ANL1] => ANL1*/
            array_list.add(res.getString(res.getColumnIndex("course")));
            res.moveToNext();
        }
        return array_list;
    }

    //date
    public ArrayList<String> getDate(String codename) {
        ArrayList<String> array_list = new ArrayList<String>();
        ///puts column value in array to be later accessed as string
        SQLiteDatabase db = this.getReadableDatabase();
        //searches for value where entered code is present
        Cursor res =  db.rawQuery( "select * from codes where code=?", new String[]{codename});
        res.moveToFirst();
        while(res.isAfterLast() == false){
            /*gets date where code matched output will be [0/0/0]
            substrings will be used to extract only the date e.g [0/0/0] => 0/0/0*/
            array_list.add(res.getString(res.getColumnIndex("date")));
            res.moveToNext();
        }
        return array_list;
    }




    ///     INSERTS     ///

    //inserting class codes in db
    public boolean insert(String course, String classs, String date, String code, String teacher){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("course", course);
        contentValues.put("class", classs);
        contentValues.put("date", date);
        contentValues.put("code", code);
        contentValues.put("teacher", teacher);
        long ins = db.insert("codes", null,contentValues);
        if(ins==-1)return false;
        else return true;

    }
    //inserts user login
    public boolean insertLogin(String username, String password, String teacher, String student){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", username);
        contentValues.put("password", password);
        contentValues.put("teacher", teacher);
        contentValues.put("student", student);
        long ins = db.insert("login", null,contentValues);
        if(ins==-1)return false;
        else return true;

    }
    /// insterts values into reasons table
    public boolean insertReason(String s1, String s2, String s3){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("teacher", s1);
        values.put("course", s2);
        values.put("reason", s3);
        long ins = db.insert("reasons", null, values);
        if(ins==-1)return false;
        else return true;

    }

    // inserts filename of textfiles into table comments
    public boolean insertFileName(String filename) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("filename", filename);
        long ins = db.insert("comments", null, contentValues);
        if (ins == -1) return false;
        else return true;
    }




    ///     CHECKERS    ///

    //check if code exists returns false if code *DOES* exist
    public Boolean chckCode(String code){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from codes where code=?", new String[]{code});
        if(cursor.getCount()>0) return false;
        else return true;
    }

    //returns true if code exists
    public Boolean chckEnterCode(String input){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from codes where code=?", new String[]{input});
        if(cursor.getCount()>0) return true;
        else return false;
    }

    //checks if user exists
    public Boolean chckUser(String username){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from login where username=?", new String[]{username});
        if(cursor.getCount()>0) return false;
        else return true;
    }

    //check if username and password match db values and if user authority lv. = student
    public Boolean Susernamepassword(String username, String password){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from login where username=? and password=? and student='yes'", new String[]{username,password});
        if(cursor.getCount()>0) return true;
        else return false;
    }
    //check if username and password match db values and if user authority lv. = teacher
    public Boolean Tusernamepassword(String username, String password){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from login where username=? and password=? and teacher='yes'", new String[]{username,password});
        if(cursor.getCount()>0) return true;
        else return false;
    }

}
