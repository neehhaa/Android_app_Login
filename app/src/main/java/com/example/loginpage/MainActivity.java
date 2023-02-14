package com.example.loginpage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button btn_signup_main,btn_login_main;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_login_main = findViewById(R.id.btn_login_main);
        btn_signup_main = findViewById(R.id.btn_signup_main);

        btn_signup_main.setOnClickListener(v -> {
            Intent i =new Intent(MainActivity.this,SignUp.class);
            startActivity(i);
        });

        btn_login_main.setOnClickListener(v -> {
            Intent i = new Intent(MainActivity.this,Login.class);
            startActivity(i);
        });
    }
}