package com.example.accelerometry_3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;


public class measurement extends AppCompatActivity implements SensorEventListener{
    TextView x_txt, y_txt, z_txt, txtTitleAcc;
    private Sensor Accelerometer;
    private SensorManager SM_acc;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_measurement);

        // Sensormanager accelerometer
        SM_acc = (SensorManager)getSystemService(SENSOR_SERVICE);

        // Accelerometer Sensor
        Accelerometer = SM_acc.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        // Register Sensor Listener Accelerometer
        SM_acc.registerListener(this, Accelerometer, SensorManager.SENSOR_DELAY_NORMAL);

        //Assign TextViews
        x_txt = (TextView)findViewById(R.id.x_txt);
        y_txt = (TextView)findViewById(R.id.y_txt);
        z_txt = (TextView)findViewById(R.id.z_txt);

    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        x_txt.setText("X: " + event.values[0]);
        y_txt.setText("Y: " + event.values[1]);
        z_txt.setText("Z: " + event.values[2]);

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // Not in use

    }
}