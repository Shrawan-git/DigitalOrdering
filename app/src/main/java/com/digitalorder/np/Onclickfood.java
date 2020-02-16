package com.digitalorder.np;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.Notification;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.renderscript.Short4;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.digitalorder.np.api.UsersAPI;
import com.digitalorder.np.bll.OrderBLL;
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
    Button orderForm,favForm;
    private NotificationManagerCompat notificationManagerCompat;
    private int id = 2;
    SensorManager sensorManager;
    private Sensor gyroscopeSensor;
    private SensorEventListener gyroscopeEventlistener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onclickfood);

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        gyroscopeSensor = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);

        if (gyroscopeSensor == null) {
            Toast.makeText(this, "The device no gyro", Toast.LENGTH_SHORT).show();
        }

        gyroscopeEventlistener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                if (event.values[2] > 0.5f) {
                    getWindow().getDecorView().setBackgroundColor(Color.GRAY);
                } else if (event.values[2] < -0.5f) {
                    getWindow().getDecorView().setBackgroundColor(Color.LTGRAY);
                }
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {
            }
        };

        notificationManagerCompat = NotificationManagerCompat.from(this);
        CreateChannel channel = new CreateChannel(this);
        channel.createChannel();

        img_view = findViewById(R.id.img_view);
        tv_name = findViewById(R.id.tv_name);
        tv_price = findViewById(R.id.tv_price);
        tv_category = findViewById(R.id.tv_category);
        tv_description = findViewById(R.id.tv_description);
        orderForm = findViewById(R.id.orderForm);
        favForm = findViewById(R.id.favForm);

        Bundle bundle = getIntent().getExtras();

        if (bundle != null) {
            String img = bundle.getString("Image");
            OrderMod orderMod = new OrderMod(img, "", "", "", "");

            String imgPath = Url.imagePath + orderMod.getImage();
            StrictModeClass.StrictMode();
            try {
                URL url = new URL(imgPath);
                img_view.setImageBitmap(BitmapFactory.decodeStream((InputStream) url.getContent()));
            } catch (Exception e) {
                e.printStackTrace();
            }
            tv_name.setText(bundle.getString("Name"));
            tv_price.setText(bundle.getString("Price"));
            tv_category.setText(bundle.getString("Category"));
            tv_description.setText(bundle.getString("Description"));

            favForm.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    favFood();
                }
            });

            orderForm.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    orderFood();
                    DisplayNotification();
                }
            });


        }

    }

    private void orderFood() {
        String foodName = tv_name.getText().toString().trim();
        String foodPrice = tv_price.getText().toString().trim();
        String foodCategory = tv_category.getText().toString().trim();

        OrderBLL orderBLL = new OrderBLL();
        StrictModeClass.StrictMode();
        if (orderBLL.orderUser(foodName, foodPrice, foodCategory)) {
            Toast.makeText(Onclickfood.this, "" , Toast.LENGTH_SHORT).show();
            return;
        }

    }
    private void favFood() {
        String foodName = tv_name.getText().toString().trim();
        String foodPrice = tv_price.getText().toString().trim();
        String foodCategory = tv_category.getText().toString().trim();

        Product product = new Product(foodName, foodPrice, foodCategory);

        UsersAPI usersAPI = Url.getInstance().create(UsersAPI.class);
        Call<Void> orderCall = usersAPI.favouriteDetatil(Url.token, product);

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

    private void DisplayNotification() {
        String name = tv_name.getText().toString();

        Notification notification = new NotificationCompat.Builder(this, CreateChannel.CHANNEL_1)
                .setSmallIcon(R.drawable.ic_info_black_24dp)
                .setContentTitle("You have ordered " + name)
                .setContentText("Order successful")
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .build();
        notificationManagerCompat.notify(id, notification);
    }
    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(gyroscopeEventlistener, gyroscopeSensor, SensorManager.SENSOR_DELAY_FASTEST);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(gyroscopeEventlistener);
    }

}