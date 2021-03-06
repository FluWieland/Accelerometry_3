package com.example.accelerometry_3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;

import java.io.FileWriter;
import java.io.IOException;


public class measurement extends AppCompatActivity implements SensorEventListener{
    //name all the elements of the page
    TextView x_txt, y_txt, z_txt, txtTitleAcc;
    TextView x_txt_g, y_txt_g, z_txt_g, txtTitleGyro;

    Button btnStartRecording, btnStopRecording, btnSaveData;

    private Sensor Accelerometer;
    private SensorManager SM_acc;

    private Sensor Gyroscope;
    private SensorManager SM_gyro;

    private Accelerometer accelerometer;
    private Gyroscope gyroscope;

    private FileWriter writer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_measurement);

        //set the java classes for Acc and gyro
        accelerometer = new Accelerometer(this);
        gyroscope =  new Gyroscope(this);

        //set the Listener for accelerometer sensor change and define the string that are input
        accelerometer.setListener(new Accelerometer.Listener() {
            @Override
            public void onTranslation(float tx, float ty, float tz) {
                x_txt.setText("X: " + tx);
                y_txt.setText("Y: " + ty);
                z_txt.setText("Z: " + tz);

            }
        });

        //set the Listener for gyroscpoe sensor change and define the string that are input
        gyroscope.setListener(new Gyroscope.Listener() {
            @Override
            public void onRotation(float rx, float ry, float rz) {
                x_txt_g.setText("X: " + rx);
                y_txt_g.setText("Y: " + ry);
                z_txt_g.setText("Z: " + rz);

            }
        });


        // SensorManager accelerometer
        // SM_acc = (SensorManager)getSystemService(SENSOR_SERVICE);

        // Accelerometer Sensor
        // Accelerometer = SM_acc.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        // Register Sensor Listener Accelerometer
        //SM_acc.registerListener(this, Accelerometer, SensorManager.SENSOR_DELAY_NORMAL);

        //Assign TextViews Accelerometer
        x_txt = (TextView)findViewById(R.id.x_txt);
        y_txt = (TextView)findViewById(R.id.y_txt);
        z_txt = (TextView)findViewById(R.id.z_txt);


        // SensorManager Gyro
        //SM_gyro = (SensorManager)getSystemService(SENSOR_SERVICE);

        // Gyroscope Sensor
        //Gyroscope = SM_gyro.getDefaultSensor(Sensor.TYPE_GYROSCOPE);

        // Register Sensor Listener Gyroscope
        //SM_gyro.registerListener(this, Gyroscope, SensorManager.SENSOR_DELAY_NORMAL);

        //Assign TextViews Gyro
        x_txt_g = (TextView)findViewById(R.id.x_txt_g);
        y_txt_g = (TextView)findViewById(R.id.y_txt_g);
        z_txt_g = (TextView)findViewById(R.id.z_txt_g);

        //Define the buttons
        btnStartRecording = (Button) findViewById(R.id.btnStartRecord);
        btnStopRecording  = (Button) findViewById(R.id.btnStopRecord);
        btnSaveData = (Button) findViewById(R.id.btnSaveData);

        //Define what happens when the buttons are clicked
        btnStartRecording.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onStartClick(); //function see below
            }
        });

        btnStopRecording.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onStopClick(); //function see below
            }
        });

        btnSaveData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToSendPage(); //function see below
            }
        });

    }

    //Function to go to the next page
    private void goToSendPage() {
        Intent intent =  new Intent(measurement.this, SendDataPage.class);
        startActivity(intent);
    }

    //functions to start and stop data writing and to send the data to the server
    private void onStartClick() {
        SM_acc.registerListener(this, Accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
    }

    private void onStopClick() { SM_acc.unregisterListener(this);
    }

    //functions for Data writing
    @Override
    protected void onResume(){
        super.onResume();

        accelerometer.register();
        gyroscope.register();

        try {
            writer = new FileWriter("myfile.json",true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();

        accelerometer.unregister();
        gyroscope.unregister();

        if(writer != null) {
            try {
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onSensorChanged(SensorEvent event) {

        float x = event.values[0];
        float y = event.values[1];
        float z = event.values[2];
        try {
            writer.write(x+","+y+","+z+"\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // this one I dont use
    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // Not in use

    }
}