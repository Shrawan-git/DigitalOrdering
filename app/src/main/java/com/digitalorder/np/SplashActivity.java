package com.digitalorder.np;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.widget.Toast;

import com.digitalorder.np.bll.LoginBLL;
import com.digitalorder.np.strictmode.StrictModeClass;

import java.io.InputStream;


public class SplashActivity extends AppCompatActivity {
    String username, password;
    private SensorManager sensorManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                checkUser();
                proximitysense();
            }
        },2000);
    }

    private void checkUser(){
        SharedPreferences sharedPreferences = getSharedPreferences("User", Context.MODE_PRIVATE);
         username= sharedPreferences.getString("username", null);
         password= sharedPreferences.getString("password", null);

        if(username != null && password!=null){
            Login();
            Intent intent = new Intent(SplashActivity.this,DashboardActivity.class);
            startActivity(intent);
            finish();
        }else{
            Intent intent = new Intent(SplashActivity.this,LoginActivity.class);
            startActivity(intent);
            finish();
        }
    }

    private void Login(){
        String user = username;
        String pass = password;

        LoginBLL loginBLL = new LoginBLL();
        StrictModeClass.StrictMode();
        if(loginBLL.checkUser(user,pass)){
            Intent intent = new Intent(SplashActivity.this,DashboardActivity.class);
            startActivity(intent);
            finish();
        }else{
            Toast.makeText(this, "token expired", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        }
    }

    private void proximitysense(){
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        final Sensor sensor = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);

        SensorEventListener proxListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                if(event.values[0] <= 1) {
                  Intent homeIntent = new Intent(Intent.ACTION_MAIN);
                  homeIntent.addCategory(Intent.CATEGORY_HOME);
                  homeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                  startActivity(homeIntent);
            }
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }
        };
        if(sensor != null){
            sensorManager.registerListener(proxListener,sensor, SensorManager.SENSOR_DELAY_NORMAL);
        }else{
            Toast.makeText(this, "No sensor found", Toast.LENGTH_SHORT).show();
        }
    }
}
