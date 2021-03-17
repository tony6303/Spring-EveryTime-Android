package com.example.myeverytime.signIn;

import android.util.Log;

import com.example.myeverytime.signIn.interfaces.SignInActivityView;
import com.example.myeverytime.signIn.interfaces.SignInRetrofitInterface;
import com.example.myeverytime.signIn.model.SignInDto;
import com.example.myeverytime.signIn.model.SignInRespDto;

import static com.example.myeverytime.ApplicationClass.getRetrofit;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignInService {
    private static final String TAG = "SignInService";

    private final SignInActivityView mSignInActivityView;
    private SignInRetrofitInterface signInRetrofitInterface;


    SignInService(final SignInActivityView signInActivityView) {
        this.mSignInActivityView = signInActivityView;
    }

    public void postSignIn(SignInDto signInDto) {
        signInRetrofitInterface = getRetrofit().create(SignInRetrofitInterface.class);
        Call<SignInRespDto<SignInDto>> signInCall = signInRetrofitInterface.signIn(signInDto);
        signInCall.enqueue(new Callback<SignInRespDto<SignInDto>>() {
            @Override
            public void onResponse(Call<SignInRespDto<SignInDto>> call, Response<SignInRespDto<SignInDto>> response) {
                final SignInRespDto signInRespDto = response.body();

                if (signInRespDto.getData() == null) {
                    Log.d(TAG, "onResponse: " + signInRespDto);
                    Log.d(TAG, "onResponse: 로그인 실패");
                    mSignInActivityView.validateFailure(null);

                    return;
                }
                Log.d(TAG, "onResponse: " + signInRespDto);

                    mSignInActivityView.signInSuccess(signInRespDto);
                    Log.d(TAG, "onResponse: 로그인 성공");
            }

            @Override
            public void onFailure(Call<SignInRespDto<SignInDto>> call, Throwable t) {
                mSignInActivityView.validateFailure(null);
                Log.d(TAG, "onFailure: 로그인 구조적으로 실패");
            }
        });

    }
}
