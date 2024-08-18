package com.loginhelper;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;

import com.LoginActivity;
import com.zhang.onlineshop.R;

import java.util.Timer;
import java.util.TimerTask;

public class enterActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView ( R.layout.activity_enter);

        final Timer timer = new Timer ();
        final TimerTask task = new TimerTask () {
            @Override
            public void run() {
                Intent it = new Intent();
                it.setClass(enterActivity.this, LoginActivity.class);
                startActivity(it);
            }
        };
        timer.schedule (task,2900);

    }
}