package com.shrawan.np.digitalorderwear;

import android.content.Intent;
import android.os.Bundle;
import android.support.wearable.activity.WearableActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.shrawan.np.digitalorderwear.Bll.LoginBll;

public class MainActivity extends WearableActivity {

    private EditText wearUserName, wearPassword;
    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        btnLogin = findViewById(R.id.btnLogin);
        wearUserName = findViewById(R.id.wearUserName);
        wearPassword = findViewById(R.id.wearPassword);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = wearUserName.getText().toString().trim();
                String password = wearPassword.getText().toString().trim();

                LoginBll loginBll = new LoginBll();

                StrictModeClassWear.StrictMode();
                if (loginBll.checkUser(name, password)) {
                    Intent intent = new Intent(MainActivity.this, DashboardWear.class);
                    intent.putExtra("username",name);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(MainActivity.this, "Either username or password is incorrect", Toast.LENGTH_SHORT).show();
                    wearUserName.requestFocus();
                }
            }

        });

        // Enables Always-on
        setAmbientEnabled();
    }
}
