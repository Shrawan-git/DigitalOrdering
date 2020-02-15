package com.digitalorder.np;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.digitalorder.np.api.UsersAPI;
import com.digitalorder.np.model.Rating;
import com.digitalorder.np.ui.home.HomeFragment;
import com.digitalorder.np.url.Url;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderRating extends AppCompatActivity {
    private RatingBar ratebar;
    private EditText feedbacker;
    private Button btnSend;
    private TextView rateNumber;
    private float ratefeedback;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_rating);




        rateNumber = findViewById(R.id.rateNumber);
        ratebar = findViewById(R.id.ratebar);
        feedbacker = findViewById(R.id.feedbacker);
        btnSend = findViewById(R.id.btnSend);

        ratebar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                rateNumber.setText("Rating:" + rating);
                ratefeedback = rating;
            }
        });

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Orderfeedback();
                Toast.makeText(OrderRating.this, "Rated food", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void Orderfeedback(){
        String feedback = feedbacker.getText().toString();
        String rate = Float.toString(ratefeedback);

        Rating rating = new Rating(rate, feedback);

        UsersAPI usersAPI = Url.getInstance().create(UsersAPI.class);
        Call<Void> feedbackCall = usersAPI.addRating(Url.token,rating);

        feedbackCall.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(OrderRating.this, "Code" + response.body(), Toast.LENGTH_SHORT).show();
                return;
                }
            }
            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(OrderRating.this, "Error" + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


}
