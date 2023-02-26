package com.example.loginpage.Activities;

import androidx.appcompat.app.AppCompatActivity;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.loginpage.ModelResponse.LoginResponse;
import com.example.loginpage.R;
import com.example.loginpage.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
            userLogin();

//            if (checkDataEntered()){
//                Intent i =new Intent(Login.this,HomePage.class);
//                startActivity(i);
//            }
        });
    }

    private void userLogin() {

        String userEmail = ed_email.getText().toString();
        String userPassword = ed_password.getText().toString();

        if (!ValidationUtils.isEmail(ed_email)){
            ed_email.setError(("Enter valid email!!"));
            return ;
        }
        if(ValidationUtils.isEmpty(ed_password)){
            ed_password.setError("Password cannot be empty");
            return ;
        }

        Call<LoginResponse> call = RetrofitClient.getInstance().getApi().login(userEmail,userPassword);

        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {

                LoginResponse loginResponse = response.body();
                Log.e("check","response"+loginResponse);

                if (response.isSuccessful()) {
                    if (loginResponse.getError().equals("200")) {
                        Log.e("check","response"+response.code());
                        Intent i = new Intent(Login.this, HomePage.class);
                        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(i);
                        finish();
                        Toast.makeText(Login.this, loginResponse.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                }
            }
            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Toast.makeText(Login.this, t.getMessage(), Toast.LENGTH_SHORT).show();

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

//    boolean checkDataEntered(){
//        if (!ValidationUtils.isEmail(ed_email)){
//            ed_email.setError(("Enter valid email!!"));
//            return false;
//        }
//        if(ValidationUtils.isEmpty(ed_password)){
//            ed_password.setError("Password cannot be empty");
//            return false;
//        }
//        return true;
//    }
    public void hideKeyboard(View view){
        InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(),0);
    }
}