package com;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.loginhelper.DBHelper;
import com.zhang.onlineshop.R;

import java.util.Calendar;

public class Register extends Activity implements View.OnClickListener,
        RadioGroup.OnCheckedChangeListener, Spinner.OnItemSelectedListener{

    DBHelper helper;
    SQLiteDatabase sqLiteDatabase;
    private String name_str="";
    private String paswd_str="";
    private String sex_str="男";
    private String hobby_str="1";
    private String birth_str="";
    private String city_str="";
    private String phone_str="";
    EditText name_deit,paswd_deit,birth_deit,phone_deit;
    RadioGroup sex_group;
    RadioButton nan_btn,nv_btn;
    CheckBox study,play,music;
    Spinner spinner;
    Button btn;
    final String[] city = new String[]{"河北","山西","山东","辽宁","吉林","黑龙江","江苏","浙江","安徽","江西","河南","湖北","湖南","海南","广东","四川","贵州","云南","陕西","北京","上海"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        name_deit = (EditText) findViewById(R.id.name);
        paswd_deit = (EditText) findViewById(R.id.paswd);
        birth_deit = (EditText) findViewById(R.id.birth);
        phone_deit = (EditText) findViewById (R.id.phone);
        sex_group = (RadioGroup) findViewById(R.id.sex);
        nan_btn = (RadioButton) findViewById(R.id.nan);
        nv_btn = (RadioButton) findViewById(R.id.nv);
        study = (CheckBox) findViewById(R.id.study);
        play = (CheckBox) findViewById(R.id.play);
        music = (CheckBox) findViewById(R.id.music);
        spinner = (Spinner) findViewById(R.id.spinner);
        btn = (Button) findViewById(R.id.reg_btn);

        sex_group.setOnCheckedChangeListener(this);
        spinner.setOnItemSelectedListener(this);
        birth_deit.setOnClickListener(this);
        btn.setOnClickListener(this);

        helper = new DBHelper(Register.this, "user_db", null, 1);
        sqLiteDatabase = helper.getWritableDatabase();

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,city);
        spinner.setAdapter(adapter);

    }

    @Override
    public void onClick(View view) {
        // TODO Auto-generated method stub
        switch (view.getId()) {
            case R.id.birth:
                Calendar cal = Calendar.getInstance();
                Calendar calendar=Calendar.getInstance();
                new DatePickerDialog( Register.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        String text = year + "年" + (month + 1) + "月" + dayOfMonth + "日";
                        birth_deit.setText(text);
                    }
                }
                        ,calendar.get(Calendar.YEAR)
                        ,calendar.get(Calendar.MONTH)
                        ,calendar.get(Calendar.DAY_OF_MONTH)).show();
                break;
            case R.id.reg_btn:
                new AlertDialog.Builder(Register.this).setTitle("系统提示")
                        .setMessage("是否确定提交？")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface arg0, int arg1) {
                                // TODO Auto-generated method stub
                                name_str = name_deit.getText().toString();
                                paswd_str = paswd_deit.getText().toString();
                                hobby_str ="";
                                if(study.isChecked()){
                                    hobby_str += study.getText().toString();
                                }if(play.isChecked()){
                                    hobby_str += play.getText().toString();
                                }if(music.isChecked()){
                                    hobby_str += music.getText().toString();
                                }
                                birth_str = birth_deit.getText().toString();
                                phone_str = phone_deit.getText ().toString ();

                                Cursor cursor = sqLiteDatabase.query(
                                        "user", new String[]{"name"},
                                        "name=?", new String[]{name_str},
                                        null, null, null);
                                if(cursor.getCount()!=0){
                                    Toast.makeText(Register.this, "该用户已注册!", Toast.LENGTH_SHORT).show();
                                }else{
                                    helper.addData(sqLiteDatabase, name_str, paswd_str, sex_str, hobby_str, birth_str, city_str,phone_str);
                                    Toast.makeText(Register.this, "注册成功,请登录!", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(Register.this, LoginActivity.class);
                                    startActivity(intent);
                                    finish();
                                }
                            }
                        }).setNegativeButton("返回", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        // TODO Auto-generated method stub

                    }
                }).show();
                break;
            default:
                break;
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        // TODO Auto-generated method stub
        RadioButton rb = (RadioButton) findViewById(i);
        sex_str=rb.getText().toString();
    }

    @Override
    public void onItemSelected(AdapterView<?> arg0, View view, int i,
                               long l) {
        // TODO Auto-generated method stub
        city_str = city[i];
    }

    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub

    }

}
