package com.shrawan.np.digitalorderwear;

import android.os.Bundle;
import android.support.wearable.activity.WearableActivity;
import android.widget.TextView;

public class DashboardWear extends WearableActivity {

    private TextView username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_wear);

        username = findViewById(R.id.text);

        String name = getIntent().getStringExtra("username");
        username.setText("Welcome! " +name);

        // Enables Always-on
        setAmbientEnabled();
    }
}
