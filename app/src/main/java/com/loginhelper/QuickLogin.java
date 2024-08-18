package com.loginhelper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.mainpage.Main2Activity;
import com.zhang.onlineshop.R;
public class QuickLogin extends AppCompatActivity {

    private Button btn_quick_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_quick_login);
        btn_quick_login = (Button) findViewById (R.id.btn_quick_login);


        btn_quick_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(QuickLogin.this,"支付宝登录成功！",Toast.LENGTH_LONG).show();
                Intent it = new Intent();
                it.setClass(QuickLogin.this, Main2Activity.class);
                startActivity(it);
            }
        });


    }
}
