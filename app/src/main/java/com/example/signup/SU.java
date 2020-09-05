package com.example.signup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class SU extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_s_u);
        getSupportActionBar().setTitle("Carpool");
    }

    public void btn_Login(View view) {
        startActivity(new Intent(getApplicationContext(),Login.class));
    }
    public void btn_DriverRegister(View view) {
        startActivity(new Intent(getApplicationContext(),DriverRegister.class));
    }
}