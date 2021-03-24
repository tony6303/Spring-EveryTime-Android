package com.example.myeverytime.signUp.interfaces;

import com.example.myeverytime.CMRespDto;
import com.example.myeverytime.signUp.model.SignUpDto;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface SignUpInputFormRetrofitInterface {

    @POST("user")
    Call<CMRespDto<SignUpDto>> save(@Body SignUpDto signUpDto);

//    @POST("/user")
//    Call<SignUpRespDto> signUpTest(@Body HashMap<String, Object> params);
}
