package com.vip;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.LoginActivity;
import com.aboutme.AboutMe;
import com.boutique.Jingpin;
import com.loginhelper.DBHelper;
import com.mainpage.Main2Activity;
import com.shopcar.ShoppingActivity;
import com.zhang.onlineshop.R;

import static android.app.ProgressDialog.show;

public class VipActivity extends  AppCompatActivity {
    DBHelper helper;
    SQLiteDatabase sqLiteDatabase;
    private Button button_sub;;
    private Button btn_back;
    private Button btn_jingpin;
    private Button btn_member;
    private Button btn_shopping;
    private Button btn_car;
    private Button btn_my;
    private EditText vip_name,vip_tel,vip_interest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_vip );
        helper = new DBHelper(VipActivity.this, "user_db", null, 1);
        sqLiteDatabase = helper.getWritableDatabase();
        button_sub = (Button) findViewById ( R.id.vip_sub );

        vip_name = (EditText) findViewById (R.id.et_vip) ;
        vip_tel = (EditText) findViewById (R.id.vip_tel) ;
        vip_interest = (EditText) findViewById (R.id.vip_interest) ;
        btn_back=(Button) findViewById(R.id.btn_back);
        btn_jingpin = (Button) findViewById (R.id.btn_choose);
        btn_shopping=(Button) findViewById( R.id.btn_shopping);
        btn_member=(Button) findViewById(R.id.btn_member);
        btn_car = (Button)findViewById (R.id.btn_addcar);
        btn_my = (Button)findViewById (R.id.btn_my);
        SharedPreferences sharedPreferences = getSharedPreferences("zj", MODE_PRIVATE);
        String username = sharedPreferences.getString("username", "");
        System.out.println (username);
        vip_name.setText (username);
        Cursor cursor = sqLiteDatabase.query("user", new String[]{"name","hobby","phone"},
                "name=?", new String[]{username},
                null, null, null);
        while (cursor.moveToNext ()){
            String hobby=cursor.getString(cursor.getColumnIndex("hobby"));
            vip_interest.setText (hobby);
            String phone=cursor.getString(cursor.getColumnIndex("phone"));
            vip_tel.setText (phone);
        }
        button_sub.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                Toast.makeText ( VipActivity.this, "开通成功！", Toast.LENGTH_SHORT ).show ();
            }
        } );




        btn_shopping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ie = new Intent();
                ie.setClass ( VipActivity.this, ShoppingActivity.class);
                VipActivity.this.startActivity ( ie );
            }

        });


        btn_jingpin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish ();
                Intent ie = new Intent();
                ie.setClass ( VipActivity.this, Jingpin.class);
                VipActivity.this.startActivity ( ie );
            }
        });

        btn_member.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ie= new Intent();
                ie.setClass ( VipActivity.this,VipActivity.class);
                VipActivity.this.startActivity ( ie );
            }
        });

        btn_my.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent ie= new Intent();
                ie.setClass ( VipActivity.this, AboutMe.class);
               VipActivity.this.startActivity ( ie );
            }
        });

    }
}
