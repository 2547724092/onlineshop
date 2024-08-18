package com.shopcar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import com.boutique.Jingpin;
import com.mainpage.Main2Activity;
import com.zhang.onlineshop.R;
import com.aboutme.AboutMe;
import com.vip.VipActivity;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


public class ShoppingActivity extends AppCompatActivity implements CartAdapter.RefreshPriceInterface, View.OnClickListener {
    private ListView listView;
    private CheckBox cb_check_all;
    private TextView tv_total_price;
    private TextView tv_delete;
    private TextView tv_go_to_pay;
    private Button btn_back;
    private Button btn_jingpin;
    private Button btn_member;
    private Button btn_shopping;
    private Button btn_car;
    private Button btn_my;

    private CartAdapter adapter;

    private double totalPrice = 0.00;
    private int totalCount = 0;
    private List<HashMap<String, String>> goodsList;
    private String[] names={
            "麦富迪 牛肉+蛋黄佰萃成犬粮 20kg 添加蛋黄颗粒 亮泽毛发",
            "酷极kyjen 七连环益智狗狗玩具",
            "伊丽 大象变身装狗衣服可爱动物变身双脚装 舒适透气 回头率高",
            "犬心保 犬用体内驱虫 口服 适用12kg-22kg中型犬 单粒/1个月剂量 美国进口",
            "句句兽 大满足系列冻干鲜果综合肉脆片狗零食 400g",
            "雪诗雅Schesir 鸡肉木瓜狗罐头 150g*10"};

    private String[] prices={"179","78","99","57","122","149"};
    private int[] pics={R.drawable.necklace, R.drawable.oil,R.drawable.airpods,R.drawable.skirt,R.drawable.xianglian,R.drawable.qidian};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping);
        btn_back=(Button) findViewById(R.id.btn_firstpage);
        btn_jingpin = (Button) findViewById (R.id.btn_choose);
        btn_shopping=(Button) findViewById( R.id.btn_shopping);
        btn_member=(Button) findViewById(R.id.btn_member);
        btn_car = (Button)findViewById (R.id.btn_addcar);
        btn_my = (Button)findViewById (R.id.btn_my);

        initDate();

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish ();
                Intent ie = new Intent();
                ie.setClass ( ShoppingActivity.this, Main2Activity.class);
                ShoppingActivity.this.startActivity ( ie );
            }
        });

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish ();
                Intent ie = new Intent();
                ie.setClass ( ShoppingActivity.this, Jingpin.class);
                ShoppingActivity.this.startActivity ( ie );
            }
        });

        btn_shopping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ie = new Intent();
                ie.setClass ( ShoppingActivity.this,ShoppingActivity.class);
                ShoppingActivity.this.startActivity ( ie );
            }

        });
        btn_member.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ie= new Intent();
                ie.setClass ( ShoppingActivity.this, VipActivity.class);
                ShoppingActivity.this.startActivity ( ie );
            }
        });

        btn_my.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent ie= new Intent();
                ie.setClass ( ShoppingActivity.this, AboutMe.class);
               ShoppingActivity.this.startActivity ( ie );
            }
        });
    }

    //控制价格展示
    private void priceControl(Map<String, Integer> pitchOnMap) {
        totalCount = 0;
        totalPrice = 0.00;
        for (int i = 0; i < goodsList.size(); i++) {
            if (pitchOnMap.get(goodsList.get(i).get("id")) == 1) {
                totalCount = totalCount + Integer.valueOf(goodsList.get(i).get("count"));
                double goodsPrice = Integer.valueOf(goodsList.get(i).get("count")) * Double.valueOf(goodsList.get(i).get("price"));
                totalPrice = totalPrice + goodsPrice;
            }
        }
        tv_total_price.setText("￥ " + totalPrice);
        tv_go_to_pay.setText("付款(" + totalCount + ")");
    }
    @Override
    public void refreshPrice(Map<String, Integer> pitchOnMap) {
        priceControl(pitchOnMap);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.all_chekbox:
                AllTheSelected();
                break;
            case R.id.tv_go_to_pay:
                if (totalCount <= 0) {
                    Toast.makeText(this, "请选择要付款的商品~", Toast.LENGTH_SHORT).show();
                    return;
                }
                Toast.makeText(this, "钱就是另一回事了~", Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv_delete:
                if (totalCount <= 0) {
                    Toast.makeText(this, "请选择要删除的商品~", Toast.LENGTH_SHORT).show();
                    return;
                }
                checkDelete(adapter.getPitchOnMap());
                break;
        }
    }
    //删除操作
    private void checkDelete(Map<String, Integer> map) {
        List<HashMap<String, String>> waitDeleteList = new ArrayList<>();
        Map<String, Integer> waitDeleteMap = new HashMap<>();
        for (int i = 0; i < goodsList.size(); i++) {
            if (map.get(goodsList.get(i).get("id")) == 1) {
                waitDeleteList.add(goodsList.get(i));
                waitDeleteMap.put(goodsList.get(i).get("id"), map.get(goodsList.get(i).get("id")));
            }
        }
        goodsList.removeAll(waitDeleteList);
        map.remove(waitDeleteMap);
        priceControl(map);
        adapter.notifyDataSetChanged();
    }
    //全选或反选
    private void AllTheSelected() {
        Map<String, Integer> map = adapter.getPitchOnMap();
        boolean isCheck = false;
        boolean isUnCheck = false;
        Iterator iter = map.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry entry = (Map.Entry) iter.next();
            if (Integer.valueOf(entry.getValue().toString()) == 1) isCheck = true;
            else isUnCheck = true;
        }
        if (isCheck == true && isUnCheck == false) {//已经全选,做反选
            for (int i = 0; i < goodsList.size(); i++) {
                map.put(goodsList.get(i).get("id"), 0);
            }
            cb_check_all.setChecked(false);
        } else if (isCheck == true && isUnCheck == true) {//部分选择,做全选
            for (int i = 0; i < goodsList.size(); i++) {
                map.put(goodsList.get(i).get("id"), 1);
            }
            cb_check_all.setChecked(true);
        } else if (isCheck == false && isUnCheck == true) {//一个没选,做全选
            for (int i = 0; i < goodsList.size(); i++) {
                map.put(goodsList.get(i).get("id"), 1);
            }
            cb_check_all.setChecked(true);
        }
        priceControl(map);
        adapter.setPitchOnMap(map);
        adapter.notifyDataSetChanged();
    }
    private void initView() {
        listView = (ListView) findViewById(R.id.listview);
        cb_check_all = (CheckBox) findViewById(R.id.all_chekbox);
        tv_total_price = (TextView) findViewById(R.id.tv_total_price);
        tv_delete = (TextView) findViewById(R.id.tv_delete);
        tv_go_to_pay = (TextView) findViewById(R.id.tv_go_to_pay);
        tv_go_to_pay.setOnClickListener(this);
        tv_delete.setOnClickListener(this);
        cb_check_all.setOnClickListener(this);
        adapter = new CartAdapter(this, goodsList);
        adapter.setRefreshPriceInterface(this);
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
    private void initDate() {
        goodsList = new ArrayList<>();

        for (int i = 0; i < 6; i++) {
            HashMap<String, String> map = new HashMap<>();
            map.put("id", (i+1)+"");
            map.put("name", names[i]);
            map.put("type", "有货");
            map.put("price", prices[i]);
            map.put("count", "1");
            goodsList.add(map);
        }
        initView();

    }



}


 
 