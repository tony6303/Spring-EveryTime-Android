package com.example.myeverytime.signUp.model;

public class SignUpRespDto<T> {
    private int code;
    private T data;

    public SignUpRespDto(int code, T data) {
        this.code = code;
        this.data = data;
    }

    public SignUpRespDto() {
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
