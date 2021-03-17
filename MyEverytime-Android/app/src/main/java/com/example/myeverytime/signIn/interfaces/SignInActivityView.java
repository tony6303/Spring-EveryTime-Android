package com.example.myeverytime.signIn.interfaces;

import com.example.myeverytime.signIn.model.SignInRespDto;

public interface SignInActivityView {
    void validateSuccess(String text);

    void validateFailure(String message);

    void signInSuccess(SignInRespDto signInResponse);
}
