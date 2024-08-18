package com.loginhelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory,
                    int version) {
        super(context, name, factory, version);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        // TODO Auto-generated method stub
        //创建数据表
        String sql = "create table user(id integer primary key autoincrement,name varchar(20),password varchar(20),sex varchar(2),hobby varchar(20),birth varchar(20),city varchar(20),phone varchar(20))";
        sqLiteDatabase.execSQL(sql);
    }
    //将注册信息添加到数据表user中
    public void addData(SQLiteDatabase sqLiteDatabase,String name,String password,String sex,String hobby,String birth,String city,String phone){
        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("password", password);
        values.put("sex", sex);
        values.put("hobby", hobby);
        values.put("birth", birth);
        values.put("city", city);
        values.put("phone", phone);
        sqLiteDatabase.insert("user", null, values);
    }

    @Override
    public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
        // TODO Auto-generated method stub

    }

}
