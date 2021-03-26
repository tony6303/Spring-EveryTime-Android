package com.example.myeverytime.signIn;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.example.myeverytime.BaseActivity;
import com.example.myeverytime.CMRespDto;
import com.example.myeverytime.MainActivity;
import com.example.myeverytime.R;
import com.example.myeverytime.signIn.interfaces.SignInActivityView;
import com.example.myeverytime.signIn.model.SignInDto;
import com.example.myeverytime.signUp.SignUpInputFormActivity;

import static com.example.myeverytime.SharedPreference.getAttributeBoolean;
import static com.example.myeverytime.SharedPreference.setAttribute;
import static com.example.myeverytime.SharedPreference.getAttribute;
import static com.example.myeverytime.SharedPreference.removeAllAttribute;
import static com.example.myeverytime.SharedPreference.setAttributeBoolean;

public class SignInActivity extends BaseActivity implements SignInActivityView {
    private static final String TAG = "SignInActivity";

    private Context mContext;

    private EditText et_mainLogin_userID;
    private EditText et_mainLogin_userPW;
    private Button btn_login;
    private Button btn_signUp;
    private CheckBox auto_login_check;
    private Boolean loginChecked;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
            mContext = this;
            et_mainLogin_userID = findViewById(R.id.et_logIn_id);
            et_mainLogin_userPW = findViewById(R.id.et_logIn_pw);
            btn_login = findViewById(R.id.btn_logIn_logIn);
            btn_signUp = findViewById(R.id.btn_logIn_signUp);
            auto_login_check = findViewById(R.id.auto_login_check);

            auto_login_check.setChecked(getAttributeBoolean(mContext, "loginChecked"));
            et_mainLogin_userID.setText(getAttribute(mContext, "loginId"));
            et_mainLogin_userPW.setText(getAttribute(mContext, "loginPw"));

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
                        Boolean instant_login_check = auto_login_check.isChecked();
                        Log.d(TAG, "onClick: ischecked" + auto_login_check.isChecked());
                        String inputID = et_mainLogin_userID.getText().toString();
                        String inputPW = et_mainLogin_userPW.getText().toString();

                        tryPostSignIn(instant_login_check, inputID, inputPW);
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

            // 체크박스 상태 유무, loginChecked 변수 상태를 변경 한다.
            // 체크박스는 그냥 view 일 뿐, 상태가 아니다.
            auto_login_check.setOnCheckedChangeListener((buttonView, isChecked) -> {
                if(isChecked){
                    loginChecked = true;
                }else{
                    loginChecked = false;
                    removeAllAttribute(mContext); // sharedPreference 정보 모두 삭제
                }
            });
    }


    private void tryPostSignIn(Boolean loginChecked, String inputID, String inputPW) {
        SignInDto signInDto = new SignInDto(inputID,inputPW);

//        HashMap<String, Object> params = new HashMap<>();
//        params.put("userID", inputID);
//        params.put("pw", inputPW);
        if(loginChecked){
            setAttributeBoolean(mContext, "loginChecked",true);
            setAttribute(mContext,"loginId",inputID);
            setAttribute(mContext, "loginPw",inputPW);
            Log.d(TAG, "tryPostSignIn: shared에 로그인 기억");
        }

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
    public void signInSuccess(CMRespDto cmRespDto) {
        switch (cmRespDto.getCode()) {
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