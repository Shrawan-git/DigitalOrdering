package com.digitalorder.np;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    SensorManager mySensorManager;
    Sensor myProximitySensor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

            mySensorManager = (SensorManager) getSystemService(
                    Context.SENSOR_SERVICE);
            myProximitySensor = mySensorManager.getDefaultSensor(
                    Sensor.TYPE_PROXIMITY);

                mySensorManager.registerListener(proximitySensorEventListener,
                        myProximitySensor,
                        SensorManager.SENSOR_DELAY_NORMAL);
            }

        SensorEventListener proximitySensorEventListener
                = new SensorEventListener() {
            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {
                // TODO Auto-generated method stub
            }
            @Override
            public void onSensorChanged(SensorEvent event) {
                // TODO Auto-generated method stub
                WindowManager.LayoutParams params = getWindow().getAttributes();
                if(event.sensor.getType()== Sensor.TYPE_PROXIMITY){

                    if(event.values[0]==0){
                        params.flags |= WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON;
                        params.screenBrightness = 0;
                        getWindow().setAttributes(params);
                    }
                    else{
                        params.flags |= WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON;
                        params.screenBrightness = -1f;
                        getWindow().setAttributes(params);
                    }
                }
            }
    };
}
