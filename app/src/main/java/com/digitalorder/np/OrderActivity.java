package com.digitalorder.np;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.digitalorder.np.model.OrderMod;

import java.util.List;

public class OrderActivity extends AppCompatActivity {
    ImageView img_view;
    TextView tv_name,tv_price,tv_category;
    Bundle bundle;
    List<OrderMod> productModelList;
    Button orderDish;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
    }
}
