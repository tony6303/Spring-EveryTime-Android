package com.example.myeverytime.signIn.model;

public class SignInRespDto<T> {
    private int code;
    private T data;

    public SignInRespDto(int code, T data) {
        this.code = code;
        this.data = data;
    }

    @Override
    public String toString() {
        return "SignInRespDto{" +
                "code=" + code +
                ", data=" + data +
                '}';
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
