package com.example.myeverytime.signUp;

import android.util.Log;

import com.example.myeverytime.BaseActivity;
import com.example.myeverytime.signUp.interfaces.SignUpInputFormActivityView;
import com.example.myeverytime.signUp.interfaces.SignUpInputFormRetrofitInterface;
import com.example.myeverytime.signUp.model.SignUpDto;
import com.example.myeverytime.signUp.model.SignUpRespDto;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.myeverytime.ApplicationClass.getRetrofit;

public class SignUpInputFormService extends BaseActivity {

    private static final String TAG = "SignUpInputFormService";
    private SignUpInputFormRetrofitInterface signUpInputFormRetrofitInterface;

    private final SignUpInputFormActivityView mSignUpInputFormActivity_View;

    SignUpInputFormService(final SignUpInputFormActivityView signUpInputFormActivity_view) {
        this.mSignUpInputFormActivity_View = signUpInputFormActivity_view;
    }


    public void postSignUp(SignUpDto signUpDto) {
        signUpInputFormRetrofitInterface = getRetrofit().create(SignUpInputFormRetrofitInterface.class);
        Call<SignUpRespDto<SignUpDto>> signUpCall = signUpInputFormRetrofitInterface.save(signUpDto);
        signUpCall.enqueue(new Callback<SignUpRespDto<SignUpDto>>() {
            @Override
            public void onResponse(Call<SignUpRespDto<SignUpDto>> call, Response<SignUpRespDto<SignUpDto>> response) {
                final SignUpRespDto signUpRespDto = response.body();
                if (signUpRespDto == null) {
                    Log.d(TAG, "onResponse: 회원가입 실패");
                    mSignUpInputFormActivity_View.validateFailure(null);
                    return;
                }

                mSignUpInputFormActivity_View.signUpSuccess(signUpRespDto);
                Log.d(TAG, "onResponse: 회원가입 성공");
            }

            @Override
            public void onFailure(Call<SignUpRespDto<SignUpDto>> call, Throwable t) {
                mSignUpInputFormActivity_View.validateFailure(null);
                Log.d(TAG, "onResponse: 회원가입 구조적으로 실패 " + t.getMessage());
            }
        });
    }
}