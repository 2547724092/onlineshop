package com.aboutme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.LoginActivity;
import com.boutique.Jingpin;
import com.mainpage.Main2Activity;
import com.shopcar.ShoppingActivity;
import com.zhang.onlineshop.R;
import com.vip.VipActivity;

public class AboutMe extends AppCompatActivity {

    private Button btn_back;
    private Button btn_jingpin;
    private Button btn_member;
    private Button btn_shopping;
    private Button btn_car;
    private Button btn_my;
    private Button my_quit;
    private Button aboutme_more;
    private Button btn_pingjia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.about_me);

        btn_back=(Button) findViewById(R.id.btn_back);
        btn_shopping=(Button) findViewById( R.id.btn_shopping);
        btn_member=(Button) findViewById(R.id.btn_member);
        btn_car = (Button)findViewById (R.id.btn_addcar);
        btn_my = (Button)findViewById (R.id.btn_my);
        btn_jingpin = (Button) findViewById (R.id.btn_choose);
        aboutme_more = (Button)findViewById (R.id.aboutme_more);
        btn_pingjia = (Button)findViewById (R.id.btn_pingjia);
        my_quit = findViewById (R.id.my_quit);

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish ();
                Intent ie = new Intent();
                ie.setClass ( AboutMe.this, Main2Activity.class);
              AboutMe.this.startActivity ( ie );
            }
        });

        btn_jingpin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish ();
                Intent ie = new Intent();
                ie.setClass ( AboutMe.this, Jingpin.class);
                AboutMe.this.startActivity ( ie );
            }
        });

        btn_shopping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish ();
                Intent ie = new Intent();
                ie.setClass ( AboutMe.this, ShoppingActivity.class);
              AboutMe.this.startActivity ( ie );
            }

        });
        btn_member.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ie= new Intent();
                ie.setClass ( AboutMe.this, VipActivity.class);
                AboutMe.this.startActivity ( ie );
            }
        });

        btn_my.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent ie= new Intent();
                ie.setClass ( AboutMe.this,AboutMe.class);
               AboutMe.this.startActivity ( ie );
            }
        });

        my_quit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent ie= new Intent();
                ie.setClass ( AboutMe.this, LoginActivity.class);
                AboutMe.this.startActivity ( ie );
            }
        });


        aboutme_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ie = new Intent();
                ie.setClass ( AboutMe.this, AboutMore.class);
                AboutMe.this.startActivity ( ie );
            }
        });


        btn_pingjia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ie = new Intent();
                ie.setClass ( AboutMe.this, com.liuyanban.class);
                AboutMe.this.startActivity ( ie );
            }
        });


    }
}
