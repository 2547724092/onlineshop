package com.shopcar;

import android.os.Bundle;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.shopcar.CartAdapter;
import com.zhang.onlineshop.R;

public class ShopCart extends AppCompatActivity {
    public ListView lv;
    public CartAdapter adapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_shopping);
        lv = (ListView)findViewById (R.id.listview);
        lv.setAdapter (adapter);
    }
}
