package com.example.loginpage;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

public class ValidationUtils {

    public static boolean isEmpty(EditText text){
        CharSequence str = text.getText().toString();
        return TextUtils.isEmpty(str);
    }

    public static boolean isEmail(EditText text){
        CharSequence email_signup = text.getText().toString();
        return (!TextUtils.isEmpty(email_signup) && Patterns.EMAIL_ADDRESS.matcher(email_signup).matches());
    }

    public void hideKeyboard(View view, Context context){
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(),0);
    }
}