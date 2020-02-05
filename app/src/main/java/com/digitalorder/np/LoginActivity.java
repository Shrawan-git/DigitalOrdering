package com.digitalorder.np;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.Notification;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Paint;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.digitalorder.np.bll.LoginBLL;
import com.digitalorder.np.strictmode.StrictModeClass;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class LoginActivity extends AppCompatActivity {

    private EditText edname, edpwd;
    private TextView txtsignup;
    private Button btnlogin;
    private String Username, Password;
    private SensorManager sm;
    private CheckBox check;
    private NotificationManagerCompat notificationManagerCompat;
    private int id =1;

    private float acelVal; //Current acceleration value and gravity
    private float acelLast; //Last acceleration value and gravity
    private float shake; //Acceleration value differ from gravity

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        notificationManagerCompat = NotificationManagerCompat.from(this);
        CreateChannel channel = new CreateChannel(this);
        channel.createChannel();

        btnlogin = findViewById(R.id.btnlogin);
        edname = findViewById(R.id.edname);
        edpwd = findViewById(R.id.edpwd);
        txtsignup = findViewById(R.id.txtsignup);
        check = findViewById(R.id.check);
        txtsignup.setPaintFlags(txtsignup.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

        txtsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });

        sm = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        sm.registerListener(sensorListener, sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL);

        acelVal = SensorManager.GRAVITY_EARTH;
        acelLast = SensorManager.GRAVITY_EARTH;
        shake = 0.00f;
    }

    private final SensorEventListener sensorListener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {
            float x = event.values[0];
            float y = event.values[1];
            float z = event.values[2];

            acelLast = acelVal;
            acelVal = (float) Math.sqrt((double) (x*x + y*y + z*z));
            float delta = acelVal - acelLast;
            shake = shake * 0.9f + delta;

            if (shake > 12) {
                Toast.makeText(LoginActivity.this, "This is the android assignment", Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    };

    private void login(){
        String name = edname.getText().toString();
        String password = edpwd.getText().toString();

        LoginBLL loginBLL = new LoginBLL();

        StrictModeClass.StrictMode();
        if (loginBLL.checkUser(name, password)) {
            DisplayNotification();

            if(check.isChecked()){
                SaveSharedPreferences();
                DisplayNotification();
                Intent intent = new Intent(LoginActivity.this, DashboardActivity.class);
                startActivity(intent);
                finish();
                return;
            }
            Intent intent = new Intent(LoginActivity.this, DashboardActivity.class);
            startActivity(intent);
            finish();
        } else {
            Toast.makeText(this, "Either username or password is incorrect", Toast.LENGTH_SHORT).show();
            edname.requestFocus();
        }
    }
    private void SaveSharedPreferences() {
        SharedPreferences sharedPreferences = this.getSharedPreferences("User", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("username",edname.getText().toString().trim());
        editor.putString("password", edpwd.getText().toString().trim());
        editor.commit();
    }
    private void DisplayNotification(){
        String name = edname.getText().toString();

        Notification notification = new NotificationCompat.Builder(this, CreateChannel.CHANNEL_1)
                .setSmallIcon(R.drawable.ic_info_black_24dp)
                .setContentTitle("Welcome" +name)
                .setContentText("Login successful")
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .build();
        notificationManagerCompat.notify(id,notification);

    }
}


