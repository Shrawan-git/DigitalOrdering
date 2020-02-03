package com.digitalorder.np;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.renderscript.Short4;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.digitalorder.np.api.UsersAPI;
import com.digitalorder.np.model.OrderMod;
import com.digitalorder.np.model.Product;
import com.digitalorder.np.model.Users;
import com.digitalorder.np.strictmode.StrictModeClass;
import com.digitalorder.np.url.Url;

import java.io.InputStream;
import java.net.URL;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Body;

public class Onclickfood extends AppCompatActivity {
    ImageView img_view;
    TextView tv_name, tv_price, tv_category, tv_description;
    Button orderForm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onclickfood);

        // List<OrderMod> productModelList;

        img_view = findViewById(R.id.img_view);
        tv_name = findViewById(R.id.tv_name);
        tv_price = findViewById(R.id.tv_price);
        tv_category = findViewById(R.id.tv_category);
        tv_description = findViewById(R.id.tv_description);
        orderForm = findViewById(R.id.orderForm);

        Bundle bundle = getIntent().getExtras();

        if (bundle != null) {
            String img = bundle.getString("Image");
OrderMod orderMod =new OrderMod(img,"","","","");
//            Bitmap bitmap = getIntent().getByteArrayExtra("Image");
//            Bitmap bmp = BitmapFactory.decodeByteArray(b, 0, b.length);
//            img_view.setImageBitmap(bmp);
            String imgPath = Url.imagePath + orderMod.getImage();
            StrictModeClass.StrictMode();
            try{
                URL url = new URL(imgPath);
                img_view.setImageBitmap(BitmapFactory.decodeStream((InputStream)url.getContent()));
            }catch (Exception e){
                e.printStackTrace();
            }
            tv_name.setText(bundle.getString("Name"));
            tv_price.setText(bundle.getString("Price"));
            tv_category.setText(bundle.getString("Category"));
            tv_description.setText(bundle.getString("Description"));
//                String image=bundle.getString("dishImageName");
//
//                Picasso.with(this).load(image).into(img_view);

            orderForm.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    orderFood();
                }
            });


        }
        Toast.makeText(this, "Dish Name" + tv_name.getText().toString(), Toast.LENGTH_LONG).show();


    }


    private void orderFood() {
        String foodName = tv_name.getText().toString().trim();
        String foodPrice = tv_price.getText().toString().trim();
        String foodCategory = tv_category.getText().toString().trim();

        Product product = new Product(foodName,foodPrice,foodCategory);

        UsersAPI usersAPI = Url.getInstance().create(UsersAPI.class);
        Call<Void> orderCall = usersAPI.orderUser(Url.token,product);

        orderCall.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(Onclickfood.this, "" + response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });
    }


}