package com.example.myeverytime.main.boards.in_post;


import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.example.myeverytime.BaseActivity;
import com.example.myeverytime.CMRespDto;
import com.example.myeverytime.R;
import com.example.myeverytime.main.boards.freeboard.FreeBoardActivity;
import com.example.myeverytime.main.boards.in_post.interfaces.InPostActivityView;
import com.example.myeverytime.main.boards.in_post.interfaces.InPostRetrofitInterface;
import com.example.myeverytime.main.boards.model.PostItem;
import com.example.myeverytime.main.boards.updating.UpdatingActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.myeverytime.ApplicationClass.getRetrofit;

public class InPostActivity extends BaseActivity implements InPostActivityView, PopupMenu.OnMenuItemClickListener {

    private static final String TAG = "InPostActivity";
    private InPostRetrofitInterface inPostRetrofitInterface;
    private InPostActivityView mInpostActivityView;
//    private ArrayList<CommentItem> m_comment_item_list;

//    private CommentAdapter comment_adapter;

    private CheckBox chk_in_post_anonymous;

    private RecyclerView rv_in_post_comment;
    private LinearLayoutManager linear_layout_manager;

    private TextView tv_in_post_nickname, tv_in_post_time, tv_in_post_title, tv_in_post_content, tv_in_post_like_num, tv_in_post_comment_num, tv_in_post_scrap_num;

    private InPostService inPostService;
    private String clicked;

    private EditText et_in_post_comment;
    private ImageView iv_in_post_register_comment;

    private int m_clicked_free_pos;
    private int m_clicked_secret_pos;
    private int m_clicked_alumni_pos;
    private int m_clicked_freshmen_pos;

    private int m_from_board_num;

    private int m_index_of_this_post;

    private boolean m_from_frag_home;

    private Long id;

    public InPostActivity() {
    }

    public InPostActivity(InPostActivityView mInpostActivityView) {
        this.mInpostActivityView = mInpostActivityView;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_in_post);

        inPostService = new InPostService(this);
        ViewBinding();

        Intent intent = getIntent();
        id = intent.getLongExtra("freeBoardId", 0);

        Log.d(TAG, "onCreate: item의 실제 Id 받았어요 :" + id);
//        String title = intent.getExtras().getString("freeBoardTitle");
//        String content = intent.getExtras().getString("freeBoardContent");
//        String nickname = intent.getExtras().getString("freeBoardWriter");
//        String time = intent.getExtras().getString("freeBoardTime");

//        tv_in_post_title.setText(title);
//        tv_in_post_content.setText(content);
//        tv_in_post_nickname.setText(nickname);
//        tv_in_post_time.setText(time);
        tryGetOneFreeBoard(id);

    }

    public void ViewBinding() {

        chk_in_post_anonymous = findViewById(R.id.chk_in_post_anonymous);

        et_in_post_comment = findViewById(R.id.et_in_post_comment);
        iv_in_post_register_comment = findViewById(R.id.iv_in_post_register_comment);

        tv_in_post_nickname = findViewById(R.id.tv_in_post_nickname);
        tv_in_post_time = findViewById(R.id.tv_in_post_time);
        tv_in_post_title = findViewById(R.id.tv_in_post_title);
        tv_in_post_content = findViewById(R.id.tv_in_post_content);

        tv_in_post_like_num = findViewById(R.id.tv_in_post_like_num);
        tv_in_post_comment_num = findViewById(R.id.tv_in_post_comment_num);
        tv_in_post_scrap_num = findViewById(R.id.tv_in_post_scrap_num);
    }

    private void tryGetOneFreeBoard(Long id){
        final InPostService inPostService = new InPostService(this);
        inPostService.getOneFreeBoard(id);
    }


    public void customOnClick2(View view) {
        switch (view.getId()) {
            case R.id.btn_in_post_go_back:
                Log.d(TAG, "customOnClick:  inpost 에서 뒤로가기 누름");
                Intent intent = new Intent(InPostActivity.this, FreeBoardActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.btn_in_post_more:
                showPopUp(view);
                break;
        }

    }

    public void showPopUp(View v) {
        PopupMenu popupMenu = new PopupMenu(this, v);

        popupMenu.setOnMenuItemClickListener(this);
        popupMenu.inflate(R.menu.menu_board_delete_update);
        popupMenu.show();
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.post_delete:
                Log.d(TAG, "onMenuItemClick: 글 삭제 버튼 누름");
                AlertDialog.Builder dlg = new AlertDialog.Builder(InPostActivity.this);
                dlg.setTitle("에브리타임");
                dlg.setMessage("글을 삭제 하시겠습니까?");
                dlg.setPositiveButton("삭제", (dialog, which) -> {
                    Log.d(TAG, "onMenuItemClick: 삭제 버튼");
                    inPostRetrofitInterface = getRetrofit().create(InPostRetrofitInterface.class);
                    Call<Void> deleteOneFreeBoardCall = inPostRetrofitInterface.deleteOneFreeBoard(id);
                    deleteOneFreeBoardCall.enqueue(new Callback<Void>() {
                        @Override
                        public void onResponse(Call<Void> call, Response<Void> response) {
                            // 자꾸 mInpostActivityView가 null이라는 런타임에러가 발생해서 그냥 사용안함 (원인을 모르겠어서)
                            // mInpostActivityView.freeBoardDeleteSuccess();
                            Intent intent = new Intent(InPostActivity.this, FreeBoardActivity.class);
                            startActivity(intent);
                            finish();
                            Log.d(TAG, "onResponse: 글 삭제  성공");
                            showCustomToast("글 삭제 성공");
                        }

                        @Override
                        public void onFailure(Call<Void> call, Throwable t) {
                            Log.d(TAG, "onFailure: 글 삭제 구조적으로 실패");
                        }
                    });

                }) // dlg setPositive End
                .setNegativeButton("취소", (dialog, which) -> {
                    Log.d(TAG, "onMenuItemClick: 취소 버튼");
                   dialog.cancel();
                });
                dlg.show();

                return true;
                // todo 글 수정 마저 만들기 ~!
            case R.id.post_update:
                Log.d(TAG, "onMenuItemClick: 글 수정 버튼 누름");
                Intent intent = new Intent(InPostActivity.this, UpdatingActivity.class);
                intent.putExtra("boardName", 1);
                intent.putExtra("freeBoardId", id);
                intent.putExtra("title", tv_in_post_title.getText());
                intent.putExtra("content", tv_in_post_content.getText());
                startActivity(intent);
                finish();

                return true;
            default:
                return false;
        }
    }

    @Override
    public void validateSuccess(String text) {

    }

    @Override
    public void validateFailure(String message) {
        Log.d(TAG, "validateFailure: 실패했네요");
    }

    @Override
    public void freeBoardSuccess(CMRespDto cmRespDto) {
        switch (cmRespDto.getCode()) {
            case 100:
                Log.d(TAG, "freeBoardSuccess: 글 한건 보기 성공 code 100");
                PostItem postItem = (PostItem)cmRespDto.getData();

                tv_in_post_nickname.setText(postItem.getWriter());
                tv_in_post_time.setText(postItem.getTime().substring(0,16));
                tv_in_post_title.setText(postItem.getTitle());
                tv_in_post_content.setText(postItem.getContent());
                // LikeNum , commentNum 아직 추가 안했음.
                break;
            default:

                break;
        }
    }

    @Override
    public void freeBoardUpdateSuccess(CMRespDto cmRespDto) {
        // todo 수정 했으니 freeboardActivity 로 이동 또는 원래 게시물 다시 시작
        Log.d(TAG, "freeBoardUpdateSuccess:  수정 성공");
        showCustomToast("글 수정 성공");
        //restartActivity(InPostActivity.this);
    }

    private void restartActivity(Activity activity) {
        Intent intent = new Intent();
        intent.setClass(activity, activity.getClass());
        activity.startActivity(intent);
        activity.finish();
    }
}