package com.boutique;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.SimpleAdapter;


import com.google.android.material.internal.FlowLayout;
import com.mainpage.Main2Activity;
import com.search.SearchMain;
import com.zhang.onlineshop.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Jingpin extends AppCompatActivity {

    private EditText search_jingpin = null;
    private ListView mListView ;
    private Button jingpin_btn_back;
    private Button btn_bangdan;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_jingpin);
        search_jingpin = (EditText) findViewById(R.id.search_jingpin);
        jingpin_btn_back = (Button)findViewById ((R.id.jingpin_btn_back));
        btn_bangdan = (Button)findViewById (R.id.btn__bangdan);
        mListView = (ListView) findViewById(R.id.listView);
        // 1创建数据

        search_jingpin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish ();
                Intent ie = new Intent();
                ie.setClass ( Jingpin.this, SearchMain.class);
                Jingpin.this.startActivity ( ie );
            }
        });

        jingpin_btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish ();
                Intent ie = new Intent();
                ie.setClass ( Jingpin.this, Main2Activity.class);
                Jingpin.this.startActivity ( ie );
            }
        });


        btn_bangdan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent ie = new Intent();
                ie.setClass ( Jingpin.this,BangDan.class);
                Jingpin.this.startActivity ( ie );
            }
        });


        List<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
        HashMap<String, Object> map1 = new HashMap<String, Object>();
        map1.put("name", "麦富迪 牛肉+蛋黄佰萃成犬粮 20kg 添加蛋黄颗粒 亮泽毛发");
        map1.put("image", R.drawable.necklace);
        map1.put ("quan","满200减100");
        map1.put ("paysum","201人付款");

        HashMap<String, Object> map2 = new HashMap<String, Object>();
        map2.put("name", "犬心保 犬用体内驱虫 口服 适用12kg-22kg中型犬 单粒/1个月剂量 美国进口");
        map2.put("image", R.drawable.skirt);
        map2.put ("quan","领券立减30");
        map2.put ("paysum","45人付款");

        HashMap<String, Object> map3 = new HashMap<String, Object>();
        map3.put("name", "句句兽 大满足系列冻干鲜果综合肉脆片狗零食 400g");
        map3.put("image", R.drawable.airpods);
        map3.put ("quan","领券立减100");
        map3.put ("paysum","42人付款");

        HashMap<String, Object> map4 = new HashMap<String, Object>();
        map4.put("name", "麦富迪 牛肉+蛋黄佰萃成犬粮 20kg 添加蛋黄颗粒 亮泽毛发");
        map4.put("image", R.drawable.necklace);
        map4.put ("quan","满200减100");
        map4.put ("paysum","201人付款");

        HashMap<String, Object> map5 = new HashMap<String, Object>();
        map5.put("name", "犬心保 犬用体内驱虫 口服 适用12kg-22kg中型犬 单粒/1个月剂量 美国进口");
        map5.put("image", R.drawable.skirt);
        map5.put ("quan","领券立减30");
        map5.put ("paysum","45人付款");

        HashMap<String, Object> map6 = new HashMap<String, Object>();
        map6.put("name", "句句兽 大满足系列冻干鲜果综合肉脆片狗零食 400g");
        map6.put("image", R.drawable.airpods);
        map6.put ("quan","领券立减100");
        map6.put ("paysum","42人付款");




        list.add(map1);
        list.add(map2);
        list.add(map3);
        list.add(map4);
        list.add(map5);
        list.add(map6);


// 2创建适配器
        SimpleAdapter adapter = new SimpleAdapter(this, list,
                R.layout.jingpin_item, new String[] { "name", "image","quan","paysum" },
                new int[] { R.id.jingpin_name,R.id.jingpin_img,R.id.jingpin_lingquan,R.id.jingpin_paysum});
// 3 填充
        mListView.setAdapter(adapter);

    }
}
