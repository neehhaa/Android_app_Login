package com.example.loginpage;

import androidx.appcompat.app.AppCompatActivity;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

public class ForgotPassword extends AppCompatActivity {

    EditText et_new_pass,et_conf_pass,et_email;
    Button btn_change_pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        et_new_pass = findViewById(R.id.et_new_pass);
        et_conf_pass = findViewById(R.id.et_conf_pass);
        et_email = findViewById(R.id.et_email);
        btn_change_pass = findViewById(R.id.btn_change_pass);

        btn_change_pass.setOnClickListener(v -> {
            if (checkDataEntered()) {
                Intent i = new Intent(ForgotPassword.this, Login.class);
                startActivity(i);
            }
        });
    }
    boolean checkDataEntered() {
        if (!ValidationUtils.isEmail(et_email)) {
            et_email.setError(("Enter valid email!!"));
            return false;
        }
        if (ValidationUtils.isEmpty(et_new_pass)) {
            et_new_pass.setError("Password cannot be empty");
            return false;
        }

        if (ValidationUtils.isEmpty(et_conf_pass)) {
            et_conf_pass.setError("Confirm password is empty");
            return false;
        }
        if (!et_new_pass.getText().toString().matches(et_conf_pass.getText().toString())) {
            et_conf_pass.setError("Password doesn't match");
            return false;
        }
        return true;
    }



    public void hideKeyboard(View view){
        InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(),0);
    }
}