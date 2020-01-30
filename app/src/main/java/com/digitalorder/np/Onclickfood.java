package com.digitalorder.np;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.digitalorder.np.api.OrdersAPI;
import com.digitalorder.np.model.OrderMod;
import com.digitalorder.np.url.Url;

public class Onclickfood extends AppCompatActivity implements View.OnClickListener {
    ImageView img_view;
    TextView tv_name,tv_price,tv_category;
    Button orderForm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onclickfood);

       // List<OrderMod> productModelList;

            img_view=findViewById(R.id.img_view);
            tv_name=findViewById(R.id.tv_name);
            tv_price=findViewById(R.id.tv_price);
            tv_category=findViewById(R.id.tv_category);
            orderForm=findViewById(R.id.orderForm);
            orderForm.setOnClickListener(this);

            Bundle bundle = getIntent().getExtras();

            if(bundle !=null)
            {
                img_view.setImageResource(bundle.getInt("Image"));
                tv_name.setText(bundle.getString("Name"));
                tv_price.setText(bundle.getString("Price"));
                tv_category.setText(bundle.getString("Category"));
//                String image=bundle.getString("dishImageName");
//
//                Picasso.with(this).load(image).into(img_view);
            }
            Toast.makeText(this,"Dish Name" + tv_name.getText().toString() , Toast.LENGTH_LONG).show();



        }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.orderForm){
            OrdersAPI ordersAPI = Url.getInstance().create(OrdersAPI.class);

            String Foodname = tv_name.getText().toString();
            String Price = tv_price.getText().toString();
            String Category = tv_category.getText().toString();


            OrderMod orderMod = new OrderMod(Foodname, Price, Category, )



        }
    }
}
