package com.example.myeverytime.signIn.interfaces;

import com.example.myeverytime.signIn.model.SignInDto;
import com.example.myeverytime.signIn.model.SignInRespDto;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface SignInRetrofitInterface {

    @POST("login")
    Call<SignInRespDto<SignInDto>> signIn(@Body SignInDto signInDto);
}
