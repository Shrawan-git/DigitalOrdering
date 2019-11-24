package com.digitalorder.np;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import de.hdodenhof.circleimageview.CircleImageView;

    public class DetailActivity extends AppCompatActivity {
        CircleImageView circleImg;
        TextView tvName, tvPhone;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_detail);

            circleImg = findViewById(R.id.circleImg);
            tvName = findViewById(R.id.tvName);
            tvPhone = findViewById(R.id.tvPrice);

            Bundle bundle = getIntent().getExtras();

            if (bundle != null) {
                circleImg.setImageResource(bundle.getInt("Image"));
                tvName.setText(bundle.getString("Name"));
                tvPhone.setText(bundle.getString("Price"));

            }

        }
    }


