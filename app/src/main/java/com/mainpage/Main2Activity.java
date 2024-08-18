package com.mainpage;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.view.View.OnClickListener;
import androidx.annotation.Nullable;

import android.widget.Toast;

import com.boutique.Jingpin;
import com.zhang.onlineshop.R;
import com.aboutme.AboutMe;
import com.shopcar.ShoppingActivity;
import com.vip.VipActivity;

public class Main2Activity extends Activity {
    private ListView lv;
    private String[] names={
            "麦富迪 牛肉+蛋黄佰萃成犬粮 20kg 添加蛋黄颗粒 亮泽毛发",
            "酷极kyjen 七连环益智狗狗玩具",
            "伊丽 大象变身装狗衣服可爱动物变身双脚装 舒适透气 回头率高",
            "犬心保 犬用体内驱虫 口服 适用12kg-22kg中型犬 单粒/1个月剂量 美国进口",
            "句句兽 大满足系列冻干鲜果综合肉脆片狗零食 400g",
            "雪诗雅Schesir 鸡肉木瓜狗罐头 150g*10"};
    private String[] prices={"￥179","￥78","￥99","￥57","￥112","￥券后149"};
    private int[] pics={R.drawable.necklace, R.drawable.oil,R.drawable.airpods,R.drawable.skirt,R.drawable.xianglian,R.drawable.qidian};
    private String[] places={"酷极旗舰店 广州>","伊丽旗舰店 韩国>","犬心保专营店 浙江>","句句兽 深圳>","佐卡伊官方旗舰店 杭州>","雪诗雅旗舰店 泰安>"};
    private String[] paysums={"201人付款","300人付款","23人付款","42人付款","428人付款","700人付款"};
    private String[] lingquans = {"满150减10","满200减20","宠物币抵扣2.65","第二件立减15元","首单直降30","满三赠一"};
    private Button btn_firstpage;
    private Button btn_jingpin;
    private Button btn_member;
    private Button btn_shopping;
    private Button btn_car;
    private Button btn_my;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        lv=(ListView)findViewById(R.id.lv);
        btn_firstpage=(Button) findViewById(R.id.btn_firstpage);
        btn_jingpin = (Button)findViewById (R.id.btn_jingpin);
        btn_shopping=(Button) findViewById( R.id.btn_shopping);
        btn_member=(Button) findViewById(R.id.btn_member);
        btn_car = (Button)findViewById (R.id.btn_addcar);
        btn_my = (Button)findViewById (R.id.btn_my);

        MyBaseAdapter adapter = new MyBaseAdapter();
        lv.setAdapter (adapter);



        btn_firstpage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish ();
                Intent ie = new Intent();
                ie.setClass ( Main2Activity.this, Main2Activity.class);
                Main2Activity.this.startActivity ( ie );
            }
        });

        btn_jingpin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ie = new Intent();
                ie.setClass ( Main2Activity.this, Jingpin.class);
                Main2Activity.this.startActivity ( ie );
            }
        });

        btn_shopping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ie = new Intent();
                ie.setClass ( Main2Activity.this, ShoppingActivity.class);
                Main2Activity.this.startActivity ( ie );
            }

        });
        btn_member.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ie= new Intent();
                ie.setClass ( Main2Activity.this, VipActivity.class);
                Main2Activity.this.startActivity ( ie );
            }
        });

        btn_my.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ie= new Intent();
                ie.setClass ( Main2Activity.this, AboutMe.class);
                Main2Activity.this.startActivity ( ie );
            }
        });




    }
    static class ViewHolder
    {

        TextView txtName,price,place,paysum,linhquan;
        ImageView img;
        Button btn_car1;

    }
    class MyBaseAdapter extends BaseAdapter
    {
        @Override
        public int getCount() {
            return names.length;
        }

        @Override
        public Object getItem(int position) {
            return names[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }


        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            ViewHolder holder=null;
            if(convertView==null) {
                convertView=View.inflate(Main2Activity.this,R.layout.listitem,null);
                holder= new ViewHolder ();
                holder.txtName=(TextView) convertView.findViewById(R.id.txtName);
                holder.price=(TextView) convertView.findViewById(R.id.price);
                holder.img=(ImageView) convertView.findViewById(R.id.iv);
                holder.place=(TextView) convertView.findViewById (R.id.place);
                holder.paysum=(TextView) convertView.findViewById (R.id.paysum);
                holder.linhquan=(TextView) convertView.findViewById (R.id.lingquan);
                holder.btn_car1=(Button) convertView.findViewById (R.id.btn_addcar);
                convertView.setTag(holder);
            }
            else
            {
                holder=(ViewHolder) convertView.getTag();

            }
            holder.txtName.setText(names[position]);
            holder.price.setText(prices[position]);
            holder.img.setBackgroundResource(pics[position]);
            holder.place.setText (places[position]);
            holder.paysum.setText (paysums[position]);
            holder.linhquan.setText (lingquans[position]);
            holder.btn_car1.setOnClickListener (new OnClickListener () {
                @Override
                public void onClick(View v) {
                    Toast.makeText (Main2Activity.this,"添加成功！在购物车等着亲哦",Toast.LENGTH_SHORT).show ();
                }
            });
            return convertView;
        }


    }



}


