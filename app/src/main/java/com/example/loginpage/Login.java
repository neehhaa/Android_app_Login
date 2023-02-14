package com.example.loginpage;

import androidx.appcompat.app.AppCompatActivity;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Login extends AppCompatActivity {
    EditText ed_email, ed_password;
    TextView lbl_forgot_pass;
    Button btn_login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ed_email = findViewById(R.id.ed_email);
        ed_password = findViewById(R.id.ed_password);
        lbl_forgot_pass = findViewById(R.id.btn_forgot_pass);
        btn_login = findViewById(R.id.btn_login);

        lbl_forgot_pass.setOnClickListener(v ->{
            Intent i =new Intent(Login.this,ForgotPassword.class);
            startActivity(i);
        });

        btn_login.setOnClickListener(v -> {
            closeKeyboard();
            if (checkDataEntered()){
                Intent i =new Intent(Login.this,HomePage.class);
                startActivity(i);
            }
        });
    }
    private void closeKeyboard(){
        View view = this.getCurrentFocus();
        if (view != null){
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(),0);
        }
    }

    boolean checkDataEntered(){
        if (!ValidationUtils.isEmail(ed_email)){
            ed_email.setError(("Enter valid email!!"));
            return false;
        }
        if(ValidationUtils.isEmpty(ed_password)){
            ed_password.setError("Password cannot be empty");
            return false;
        }
        return true;
    }
    public void hideKeyboard(View view){
        InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(),0);
    }
}