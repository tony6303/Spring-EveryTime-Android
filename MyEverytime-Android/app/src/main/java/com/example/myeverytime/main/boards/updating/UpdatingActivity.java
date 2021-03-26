package com.example.myeverytime.main.boards.updating;

import androidx.appcompat.app.AppCompatActivity;

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
import com.example.myeverytime.R;
import com.example.myeverytime.main.boards.freeboard.FreeBoardActivity;
import com.example.myeverytime.main.boards.interfaces.UpdatingActivityView;
import com.example.myeverytime.main.boards.model.PostItem;
import com.example.myeverytime.main.boards.updating.model.UpdatingReqDto;
import com.example.myeverytime.main.boards.writing.WritingActivity;
import com.example.myeverytime.main.boards.writing.WritingService;

public class UpdatingActivity extends BaseActivity implements UpdatingActivityView {

    private static final String TAG = "UpdatingActivity";
    private long mBackKeyPressedTime = 0;
    private Toast mToast;

    private int num_of_board_from;
    private Button btn_updating_complete;
    private EditText et_updating_title, et_updating_content;
    private ImageView iv_updating_cancel;
    private CheckBox chk_writing_anonymous;

    private Long id;
    private String updating_title;
    private String updating_content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updating);

        Intent intent = getIntent();
        num_of_board_from = intent.getExtras().getInt("boardName");
        id = intent.getLongExtra("freeBoardId", 0);
        updating_title = intent.getExtras().getString("title");
        updating_content = intent.getExtras().getString("content");

        Log.d(TAG, "onCreate: id:" + id);
        Log.d(TAG, "onCreate: title" + updating_title);
        Log.d(TAG, "onCreate: content" + updating_content);

        et_updating_title = findViewById(R.id.et_updating_title);
        et_updating_content = findViewById(R.id.et_updating_content);
        iv_updating_cancel = findViewById(R.id.btn_updating_cancel);
        chk_writing_anonymous = findViewById(R.id.chk_writing_anonymous);

        et_updating_title.setText(updating_title);
        et_updating_content.setText(updating_content);

        btn_updating_complete = findViewById(R.id.btn_updating_complete);
        btn_updating_complete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (et_updating_title.getText().toString().equals("") && et_updating_content.getText().toString().equals("")) {
                    showCustomToast("제목과 내용을 입력하세요");
                } else if (et_updating_title.getText().toString().equals("")) {
                    showCustomToast("제목을 입력하세요");
                } else if (et_updating_content.getText().toString().equals("")) {
                    showCustomToast("내용을 입력하세요");
                } else {
                    String input_title = et_updating_title.getText().toString();
                    String input_content = et_updating_content.getText().toString();

                    tryPostUpdating(id, input_title, input_content);
                }

            }
        });


        iv_updating_cancel.setOnClickListener(v -> {
            Log.d(TAG, "onCreate: 클릭리스너 실행됨");
            onBackPressed();
        });
    }

    private void tryPostUpdating(Long id, String title, String content) {
//        HashMap<String, Object> params = new HashMap<>();
//        params.put("contentTitle", title);
//        params.put("contentInf", content);
//        params.put("userStatus", chk_writing_anonymous.isChecked() ? 0 : 1);
        UpdatingReqDto updatingReqDto = new UpdatingReqDto(title, content);

        final UpdatingService updatingService = new UpdatingService(this);
        updatingService.postUpdating(id, updatingReqDto);
    }

    @Override
    public void validateSuccess(String text) {

    }

    @Override
    public void validateFailure(String message) {

    }

    @Override
    public void UpdatingSuccess(CMRespDto cmRespDto) {
        switch (cmRespDto.getCode()) {
            case 100:
//                sSharedPreferences = getSharedPreferences("jwt", MODE_PRIVATE);
//                SharedPreferences.Editor editor = sSharedPreferences.edit();
//                editor.putString("jwt", signInResponse.getSignInResult().getJwt());
//                editor.apply();
//
//                X_ACCESS_TOKEN =sSharedPreferences.getString ("jwt","");
                showCustomToast("글수정 성공");
                Log.d(TAG, "UpdatingSuccess: 글 수정 성공 code 100");

                Intent intent = new Intent(UpdatingActivity.this, FreeBoardActivity.class);
                startActivity(intent);
                finish();
                break;
            default:
                showCustomToast("글수정 실패");
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
                    intent = new Intent(UpdatingActivity.this, FreeBoardActivity.class);
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