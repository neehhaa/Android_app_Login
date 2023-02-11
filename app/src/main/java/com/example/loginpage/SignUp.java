package com.example.loginpage;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

public class SignUp extends AppCompatActivity {

    EditText first_name, last_name, email_signup, mobile_number, password, confirm_password;
    Button btn_signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        first_name = findViewById(R.id.first_name);
        last_name = findViewById(R.id.last_name);
        email_signup = findViewById(R.id.email_signup);
        mobile_number = findViewById(R.id.mobile_number);
        password = findViewById(R.id.password);
        confirm_password = findViewById(R.id.confirm_password);
        btn_signup = findViewById(R.id.btn_signup);

        btn_signup.setOnClickListener(v -> {
            checkDataEntered();
            closeKeyboard();
            Intent i =new Intent(SignUp.this,Login.class);
            startActivity(i);
        });


        InputFilter filter = (source, start, end, dest, dstart, dend) -> {
            for (int i = start; i < end; i++) {
                if (!Character.isLetter(source.charAt(i))) {
                    return "";
                }
            }
            return null;
        };
        first_name.setFilters(new InputFilter[]{filter});
        last_name.setFilters(new InputFilter[]{filter});
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
    void checkDataEntered() {
        if (isEmpty(first_name)) {
            first_name.setError("First Name Required!!");
        }
        if (isEmpty(last_name)) {
            last_name.setError("Last Name Required!!");
        }
        if (!isEmail(email_signup)) {
            email_signup.setError(("Enter valid email!!"));
        }
        if (isEmpty(mobile_number)) {
            mobile_number.setError("Mobile Number Required!!");
        }
        if (!mobile_number.getText().toString().matches("\\d{10}$"))
            mobile_number.setError("Enter 10 digits");

        if (password.length() == 0) {
            password.setError("Password cannot be empty");
        }

        if (confirm_password.length() == 0) {
            confirm_password.setError("Confirm password is empty");
        }
        if (!password.getText().toString().matches(confirm_password.getText().toString())) {
            confirm_password.setError("Password doesn't match");
        }
    }
    public void hideKeyboard(View view){
        InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(),0);
    }
}