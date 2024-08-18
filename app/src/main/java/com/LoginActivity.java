package com;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.loginhelper.DBHelper;
import com.mainpage.Main2Activity;
import com.zhang.onlineshop.R;
import com.loginhelper.QuickLogin;

public class LoginActivity extends Activity implements View.OnClickListener {

    private SharedPreferences sp;
    private EditText user, psw;
    private CheckBox rem_psw;
    private Editor editor;
    private Button btn;
    private TextView news;
    private Button btn_reset;
    private Button btn_other_login;
    private MediaPlayer mediaPlayer;
    private Button playMusicButton;

    DBHelper helper;
    SQLiteDatabase sqLiteDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        playMusicButton = findViewById(R.id.playMusicButton);
        playMusicButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 点击播放音乐按钮时播放音乐
                if (mediaPlayer != null) {
                    mediaPlayer.stop();
                    mediaPlayer = null;
                }
                mediaPlayer = MediaPlayer.create(LoginActivity.this, R.raw.start); // 创建MediaPlayer对象并关联音乐资源
                mediaPlayer.start(); // 开始播放音乐
            }
        });




        user=(EditText) findViewById(R.id.names);
        psw=(EditText) findViewById(R.id.password);
        rem_psw=(CheckBox) findViewById(R.id.jizhu);
        btn = (Button) findViewById(R.id.login_button);
        news = (TextView) findViewById(R.id.news);
        btn_reset=(Button)findViewById(R.id.btn_reset);
        btn_other_login = (Button) findViewById (R.id.btn_other_login);
        btn.setOnClickListener(this);
        news.setOnClickListener(this);
        helper = new DBHelper(LoginActivity.this, "user_db", null, 1);
        sqLiteDatabase = helper.getWritableDatabase();

        sp = getSharedPreferences("user_mes", MODE_PRIVATE);
        editor = sp.edit();
        if (sp.getBoolean("flag", false)) {
            String user_read = sp.getString("user", "");
            String psw_read = sp.getString("psw", "");
            user.setText(user_read);
            psw.setText(psw_read);
        }


        btn_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user.setText(null);
                psw.setText(null);
            }
        });


        btn_other_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent();
                it.setClass(LoginActivity.this, QuickLogin.class);
                startActivity(it);
            }
        });

    }



    @Override
    public void onClick(View view) {
        // TODO Auto-generated method stub
        switch (view.getId()) {
            case R.id.login_button:
                String user_str = user.getText().toString();
                String psw_str = psw.getText().toString();
                if (user_str.equals("") || user_str.equals("")) {
                    Toast.makeText(this, "账号或密码不能为空", Toast.LENGTH_SHORT).show();
                }else {
                    Cursor cursor = sqLiteDatabase.query("user", new String[]{"password"}, "name=?", new String[]{user_str}, null, null, null);
                    if(cursor.moveToNext()){
                        String psw_query=cursor.getString(cursor.getColumnIndex("password"));
                        if(psw_str.equals(psw_query)){
                            //记住密码
                            if (rem_psw.isChecked()) {
                                editor.putBoolean("flag", true);
                                editor.putString("user", user_str);
                                editor.putString("psw", psw_str);
                                editor.commit();
                                Toast.makeText(LoginActivity.this, "成功记住密码", Toast.LENGTH_LONG).show();
                            } else {
                                editor.clear();
                                editor.commit();
                            }
                            final  SharedPreferences sharedPreferences = getSharedPreferences("zj", MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedPreferences.edit();//获取编辑器
                            editor.putString("username", user_str);
                            editor.commit();//提交修改
                            Toast.makeText(this, "登录成功!", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(LoginActivity.this, Main2Activity.class);
                            startActivity(intent);
                        }else{
                            Toast.makeText(this, "账号或密码错误", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(this, "账号不存在，请先注册！", Toast.LENGTH_SHORT).show();
                    }
                }
                break;
            case R.id.news:
                Intent intent = new Intent(LoginActivity.this, Register.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }

}
