package com.example.accelerometry_3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class HomeActivity extends AppCompatActivity {
    String name, email;
    int SubjectNumber;

    EditText edtTxtName;
    EditText edtTxtMail;
    EditText edtTxtNumber;

    Button btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        edtTxtName = (EditText) findViewById(R.id.edtTxtName);
        edtTxtMail = (EditText) findViewById(R.id.edtTxtMail);
        edtTxtNumber = (EditText) findViewById(R.id.edtTxtNumber);

        btnSubmit =  (Button) findViewById(R.id.btnSubmit);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = edtTxtName.getText().toString();
                email = edtTxtMail.getText().toString();
                SubjectNumber = Integer.valueOf(edtTxtNumber.getText().toString());

                moveToMeasurement();

            }
        });
    }

    private void moveToMeasurement(){
        Intent intent =  new Intent(HomeActivity.this, measurement.class);
        startActivity(intent);
    }
}