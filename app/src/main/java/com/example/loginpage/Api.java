package com.example.loginpage;

import com.example.loginpage.ModelResponse.LoginResponse;
import com.example.loginpage.ModelResponse.RegisterResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface Api {

    @FormUrlEncoded
    @POST("register.php")
    Call<RegisterResponse> register(
            @Field("username") String username,
            @Field("lastname") String lastname,
            @Field("email") String email,
            @Field("mobile") String mobile,
            @Field("password") String password,
            @Field("conf_pass") String conPassword
    );

    @FormUrlEncoded
    @POST("login.php")
    Call<LoginResponse> login(
            @Field("email") String email,
            @Field("password") String password
    );
}