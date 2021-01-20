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
    TextView x_txt_g, y_txt_g, z_txt_g, txtTitleGyro;

    private Sensor Accelerometer;
    private SensorManager SM_acc;

    private Sensor Gyroscope;
    private SensorManager SM_gyro;



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


        // Sensormanager Gyro
        SM_gyro = (SensorManager)getSystemService(SENSOR_SERVICE);

        // Gyroscope Sensor
        Gyroscope = SM_gyro.getDefaultSensor(Sensor.TYPE_GYROSCOPE);

        // Register Sensor Listener Gyroscope
        SM_gyro.registerListener(this, Gyroscope, SensorManager.SENSOR_DELAY_NORMAL);

        //Assign TextViews
        x_txt_g = (TextView)findViewById(R.id.x_txt_g);
        y_txt_g = (TextView)findViewById(R.id.y_txt_g);
        z_txt_g = (TextView)findViewById(R.id.z_txt_g);

    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        x_txt.setText("X: " + event.values[0]);
        y_txt.setText("Y: " + event.values[1]);
        z_txt.setText("Z: " + event.values[2]);

        x_txt_g.setText("X: " + event.values[0]);
        y_txt_g.setText("Y: " + event.values[1]);
        z_txt_g.setText("Z: " + event.values[2]);

    }




    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // Not in use

    }
}