package com.example.myeverytime.main.boards.writing;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.myeverytime.BaseActivity;
import com.example.myeverytime.CMRespDto;
import com.example.myeverytime.MainActivity;
import com.example.myeverytime.R;
import com.example.myeverytime.main.boards.freeboard.FreeBoardActivity;
import com.example.myeverytime.main.boards.interfaces.WritingActivityView;
import com.example.myeverytime.main.boards.model.PostItem;
import com.example.myeverytime.main.boards.writing.model.WritingDto;
import com.example.myeverytime.signIn.SignInActivity;

import java.util.HashMap;

public class WritingActivity extends BaseActivity implements WritingActivityView {
    private static final String TAG = "WritingActivity";

    private long mBackKeyPressedTime = 0;
    private Toast mToast;

    private int num_of_board_from;
    private Button btn_writing_complete;
    private EditText et_writing_title, et_writing_content;
    private ImageView iv_writing_cancel;
    private CheckBox chk_writing_anonymous;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write);

        Intent intent = getIntent();
        num_of_board_from = intent.getExtras().getInt("boardName");

        et_writing_title = findViewById(R.id.et_writing_title);
        et_writing_content = findViewById(R.id.et_writing_content);
        iv_writing_cancel = findViewById(R.id.btn_writing_cancel);
        chk_writing_anonymous = findViewById(R.id.chk_writing_anonymous);

        btn_writing_complete = findViewById(R.id.btn_writing_complete);
        btn_writing_complete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (et_writing_title.getText().toString().equals("") && et_writing_content.getText().toString().equals("")) {
                    showCustomToast("제목과 내용을 입력하세요");
                } else if (et_writing_title.getText().toString().equals("")) {
                    showCustomToast("제목을 입력하세요");
                } else if (et_writing_content.getText().toString().equals("")) {
                    showCustomToast("내용을 입력하세요");
                } else {
                    String input_title = et_writing_title.getText().toString();
                    String input_content = et_writing_content.getText().toString();

                    tryPostWriting(input_title, input_content);
                }

            }
        });


        iv_writing_cancel.setOnClickListener(v -> {
            Log.d(TAG, "onCreate: 클릭리스너 실행됨");
            onBackPressed();
        });
    }


    private void tryPostWriting(String title, String content) {
//        HashMap<String, Object> params = new HashMap<>();
//        params.put("contentTitle", title);
//        params.put("contentInf", content);
//        params.put("userStatus", chk_writing_anonymous.isChecked() ? 0 : 1);
        PostItem postItem = new PostItem(title, content);

        final WritingService writingService = new WritingService(this);
        writingService.postWriting(postItem);
    }


    @Override
    public void validateSuccess(String text) {

    }

    @Override
    public void validateFailure(String message) {

    }

    @Override
    public void WritingSuccess(CMRespDto cmRespDto) {
        switch (cmRespDto.getCode()) {
            case 100:
//                sSharedPreferences = getSharedPreferences("jwt", MODE_PRIVATE);
//                SharedPreferences.Editor editor = sSharedPreferences.edit();
//                editor.putString("jwt", signInResponse.getSignInResult().getJwt());
//                editor.apply();
//
//                X_ACCESS_TOKEN =sSharedPreferences.getString ("jwt","");
                showCustomToast("글쓰기 성공");
                Log.d(TAG, "WritingSuccess: 글쓰기 성공 code 100");

                Intent intent = new Intent(WritingActivity.this, FreeBoardActivity.class);
                startActivity(intent);
                finish();
                break;
            default:
                showCustomToast("글쓰기 실패");
                break;
        }
    }

    @Override
    public void onBackPressed() {
        if(System.currentTimeMillis() > mBackKeyPressedTime + 2000) {
            mBackKeyPressedTime = System.currentTimeMillis();
            mToast = Toast.makeText(this, "\'뒤로\' 버튼을 한번 더 누르시면 작성을 종료합니다.", Toast.LENGTH_SHORT);
            mToast.show();
        }
        else if(System.currentTimeMillis() <= mBackKeyPressedTime + 2000) {

            Intent intent;

            switch (num_of_board_from){
                case 1:
                    intent = new Intent(WritingActivity.this, FreeBoardActivity.class);
                    startActivity(intent);
                    finish();
                    break;
//                case 2:
//                    intent = new Intent(WritingActivity.this, SecretBoardActivity.class);
//                    startActivity(intent);
//                    finish();
//                    break;
//                case 3:
//                    intent = new Intent(WritingActivity.this, AlumniBoardActivity.class);
//                    startActivity(intent);
//                    finish();
//                    break;
//                case 4:
//                    intent = new Intent(WritingActivity.this, FreshmenBoardActivity.class);
//                    startActivity(intent);
//                    finish();
//                    break;
            }
            mToast.cancel();
        }
    }
}