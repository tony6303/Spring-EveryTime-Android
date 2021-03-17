package com.example.myeverytime.signUp.interfaces;

import com.example.myeverytime.signUp.model.SignUpRespDto;

public interface SignUpInputFormActivityView {
    void validateSuccess(String text);

    void validateFailure(String message);

    void signUpSuccess(SignUpRespDto signUpRespDto);
}
