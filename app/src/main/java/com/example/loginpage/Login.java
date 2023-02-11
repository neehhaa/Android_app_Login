package com.example.loginpage;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
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

        btn_login.setOnClickListener(v -> {
            checkDataEntered();
            closeKeyboard();
        });
    }
    private void closeKeyboard(){
        View view = this.getCurrentFocus();
        if (view != null){
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(),0);
        }
    }

    boolean isEmail(EditText text){
        CharSequence email_signup = text.getText().toString();
        return (!TextUtils.isEmpty(email_signup) && Patterns.EMAIL_ADDRESS.matcher(email_signup).matches());
    }

    boolean isEmpty(EditText text){
        CharSequence str = text.getText().toString();
        return TextUtils.isEmpty(str);
    }
    void checkDataEntered(){
        if (!isEmail(ed_email)){
            ed_email.setError(("Enter valid email!!"));
        }
        if(ed_password.length()==0){
            ed_password.setError("Password cannot be empty");
        }
    }
    public void hideKeyboard(View view){
        InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(),0);
    }
}