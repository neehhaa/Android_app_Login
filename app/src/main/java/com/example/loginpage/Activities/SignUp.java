package com.example.loginpage.Activities;

import androidx.appcompat.app.AppCompatActivity;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputFilter;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.Toast;

import com.example.loginpage.ModelResponse.RegisterResponse;
import com.example.loginpage.R;
import com.example.loginpage.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUp extends AppCompatActivity {

    EditText first_name, last_name, email_signup, mobile_number, password, confirm_password;
    Button btn_signup;
    ScrollView scrollView;

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
            registerUser();
            closeKeyboard();
//            if (checkDataEntered()) {
//                Intent i = new Intent(SignUp.this, HomePage.class);
//                startActivity(i);
//            }
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

    private void registerUser() {

        String userName = first_name.getText().toString();
        String lastName = last_name.getText().toString();
        String userEmail = email_signup.getText().toString();
        String userMobile = mobile_number.getText().toString();
        String userPassword = password.getText().toString();
        String userConPassword = confirm_password.getText().toString();




        if (ValidationUtils.isEmpty(first_name)) {
            first_name.setError("First Name Required!!");
            return ;
        }
        if (ValidationUtils.isEmpty(last_name)) {
            last_name.setError("Last Name Required!!");
            return ;
        }
        if (!ValidationUtils.isEmail(email_signup)) {
            email_signup.setError(("Enter valid email!!"));
            return ;
        }
        if (ValidationUtils.isEmpty(mobile_number)) {
            mobile_number.setError("Mobile Number Required!!");
            return ;
        }
        if (!mobile_number.getText().toString().matches("\\d{10}$")){
            mobile_number.setError("Enter 10 digits");
            return;
        }

        if (ValidationUtils.isEmpty(password)) {
            password.setError("Password cannot be empty");
            return ;
        }

        if (ValidationUtils.isEmpty(confirm_password)) {
            confirm_password.setError("Confirm password is empty");
            return ;
        }
        if (!password.getText().toString().matches(confirm_password.getText().toString())) {
            confirm_password.setError("Password doesn't match");
            return ;
        }

        Call<RegisterResponse> call = RetrofitClient
                .getInstance()
                .getApi()
                .register(userName,lastName,userEmail, userMobile,userPassword,userConPassword);
        call.enqueue(new Callback<RegisterResponse>() {
            @Override
            public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                RegisterResponse registerResponse = response.body();

                if (response.isSuccessful()){
                   /* Intent i = new Intent(SignUp.this, HomePage.class);
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK| Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(i);
                    finish();*/
                    Log.e("SignUp", "onResponse: " + registerResponse.getMessage());
                    Toast.makeText(SignUp.this, registerResponse.getMessage(), Toast.LENGTH_SHORT).show();


                }
                else {
                    Toast.makeText(SignUp.this, registerResponse.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<RegisterResponse> call, Throwable t) {
                Toast.makeText(SignUp.this, t.getMessage(), Toast.LENGTH_SHORT).show();
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

//    public boolean checkDataEntered() {
//        if (ValidationUtils.isEmpty(first_name)) {
//            first_name.setError("First Name Required!!");
//            return false;
//        }
//        if (ValidationUtils.isEmpty(last_name)) {
//            last_name.setError("Last Name Required!!");
//            return false;
//        }
//        if (!ValidationUtils.isEmail(email_signup)) {
//            email_signup.setError(("Enter valid email!!"));
//            return false;
//        }
//        if (ValidationUtils.isEmpty(mobile_number)) {
//            mobile_number.setError("Mobile Number Required!!");
//            return false;
//        }
//        if (!mobile_number.getText().toString().matches("\\d{10}$")){
//            mobile_number.setError("Enter 10 digits");
//            return false;
//        }
//
//        if (ValidationUtils.isEmpty(password)) {
//            password.setError("Password cannot be empty");
//            return false;
//        }
//
//        if (ValidationUtils.isEmpty(confirm_password)) {
//            confirm_password.setError("Confirm password is empty");
//            return false;
//        }
//        if (!password.getText().toString().matches(confirm_password.getText().toString())) {
//            confirm_password.setError("Password doesn't match");
//            return false;
//        }
//        return true;
//    }
    public void hideKeyboard(View view){
        InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(),0);
    }
}