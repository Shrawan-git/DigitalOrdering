package com.digitalorder.np;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.digitalorder.np.model.Contacts;
import com.digitalorder.np.url.Url;
import com.squareup.picasso.Picasso;

import java.util.List;

public class Onclickfood extends AppCompatActivity {
    ImageView img_view;
    TextView tv_name,tv_price,tv_category;
    Button orderForm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onclickfood);

       // List<Contacts> productModelList;

            img_view=findViewById(R.id.img_view);
            tv_name=findViewById(R.id.tv_name);
            tv_price=findViewById(R.id.tv_price);
            tv_category=findViewById(R.id.tv_category);
            orderForm=findViewById(R.id.orderForm);

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

//    @Override
//    public void onClick(View view) {
//        if (view.getId() == R.id.orderForm){
//
//            Context vcontext= view.getContext();
//
//            Intent intent=new Intent(this, OrderActivity.class);
////
//////            intent.putExtra("dishImageName",img_view.get().toString());
////            intent.putExtra("dishName1",tv_name.getText().toString());
////            intent.putExtra("Price1",tv_price.getText().toString());
////            intent.putExtra("Category1",tv_category.getText().toString());
//
////            intent.putExtra("",productModel.getPrice());
////            intent.putExtra("dishImageName",BASE_URL+"/images/"+productModel.getDishImageName());
////            intent.putExtra("",productModel.getCategory());
//
//            vcontext.startActivity(intent);
//        }
//    }
}
