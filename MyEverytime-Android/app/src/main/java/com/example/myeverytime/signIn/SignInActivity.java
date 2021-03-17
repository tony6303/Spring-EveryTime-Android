package com.example.myeverytime.signIn;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.myeverytime.BaseActivity;
import com.example.myeverytime.MainActivity;
import com.example.myeverytime.R;
import com.example.myeverytime.signIn.interfaces.SignInActivityView;
import com.example.myeverytime.signIn.model.SignInDto;
import com.example.myeverytime.signIn.model.SignInRespDto;
import com.example.myeverytime.signUp.SignUpInputFormActivity;

import java.util.HashMap;

public class SignInActivity extends BaseActivity implements SignInActivityView {
    private static final String TAG = "SignInActivity";

    private EditText et_mainLogin_userID;
    private EditText et_mainLogin_userPW;
    private Button btn_login;
    private Button btn_signUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        et_mainLogin_userID = findViewById(R.id.et_logIn_id);
        et_mainLogin_userPW = findViewById(R.id.et_logIn_pw);
        btn_login = findViewById(R.id.btn_logIn_logIn);
        btn_signUp = findViewById(R.id.btn_logIn_signUp);

        // 로그인 버튼을 누를 때
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (et_mainLogin_userID.getText().toString().equals("") && et_mainLogin_userPW.getText().toString().equals("")) {
                    showCustomToast("아이디와 비밀번호를 입력해주세요");
                } else if (et_mainLogin_userID.getText().toString().equals("")) {
                    showCustomToast("아이디를 입력해주세요");
                } else if (et_mainLogin_userPW.getText().toString().equals("")) {
                    showCustomToast("비밀번호를 입력해주세요");
                } else {
                    String inputID = et_mainLogin_userID.getText().toString();
                    String inputPW = et_mainLogin_userPW.getText().toString();

                    // todo
                    // 아이디와 비밀번호 칸을 모두 채운 경우에는 담아서 서버로 보낸다.

                    tryPostSignIn(inputID, inputPW);
                }
            }
        });

        // 회원가입 버튼을 누를 때
        btn_signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignInActivity.this, SignUpInputFormActivity.class);
                startActivity(intent);
            }
        });
    }


    private void tryPostSignIn(String inputID, String inputPW) {
        SignInDto signInDto = new SignInDto(inputID,inputPW);

//        HashMap<String, Object> params = new HashMap<>();
//        params.put("userID", inputID);
//        params.put("pw", inputPW);

        final SignInService signInService = new SignInService(this);
        signInService.postSignIn(signInDto);
    }


    @Override
    public void validateSuccess(String text) {
        showCustomToast(text);
    }

    @Override
    public void validateFailure(String message) {
        showCustomToast(message == null || message.isEmpty() ? getString(R.string.network_error) : message);
    }

    @Override
    public void signInSuccess(SignInRespDto signInRespDto) {
        switch (signInRespDto.getCode()) {
            case 100:
//                sSharedPreferences = getSharedPreferences("jwt", MODE_PRIVATE);
//                SharedPreferences.Editor editor = sSharedPreferences.edit();
//                editor.putString("jwt", signInResponse.getSignInResult().getJwt());
//                editor.apply();
//
//                X_ACCESS_TOKEN =sSharedPreferences.getString ("jwt","");
                showCustomToast("로그인 성공");
                Log.d(TAG, "signInSuccess: 로그인 성공 code 100");

                Intent intent = new Intent(SignInActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
                break;
            default:
                showCustomToast("아이디와 비밀번호가 틀립니다");
                break;
        }
    }
}