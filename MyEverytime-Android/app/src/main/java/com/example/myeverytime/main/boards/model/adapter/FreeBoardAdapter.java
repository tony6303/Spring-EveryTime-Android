package com.example.myeverytime.main.boards.model.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myeverytime.R;
import com.example.myeverytime.main.boards.freeboard.FreeBoardActivity;
import com.example.myeverytime.main.boards.in_post.InPostActivity;
import com.example.myeverytime.main.boards.model.PostItem;

import java.util.ArrayList;

public class FreeBoardAdapter extends RecyclerView.Adapter<FreeBoardAdapter.CustomViewHolder> {

    private static final String TAG = "FreeBoardAdapter";
    private Context mContext;
    private ArrayList<PostItem> post_item_list;

    public FreeBoardAdapter(Context mContext, ArrayList<PostItem> post_item_list) {
        this.mContext = mContext;
        this.post_item_list = post_item_list;
    }

    public Long getBoardId(int position){
        return post_item_list.get(position).getId();
    }

    public String getBoardTitle(int position){
        return post_item_list.get(position).getTitle();
    }

    public String getBoardContent(int position){
        return post_item_list.get(position).getContent();
    }

    public String getBoardTime(int position){
        return post_item_list.get(position).getTime().substring(0,16);
    }

    // nickname
    public String getBoardWriter(int position){
        return post_item_list.get(position).getWriter();
    }

    @NonNull
    @Override
    public FreeBoardAdapter.CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_summarized_post, parent, false);
        CustomViewHolder holder = new CustomViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull FreeBoardAdapter.CustomViewHolder holder, int position) {

        holder.tv_item_post_title.setText(getBoardTitle(position));
        holder.tv_item_post_content.setText(getBoardContent(position));
        holder.tv_item_post_time.setText(getBoardTime(position));
        holder.tv_item_post_nickname.setText(getBoardWriter(position));
        holder.tv_item_post_like_num.setText(Integer.toString(post_item_list.get(position).getLike_num()));
        holder.tv_item_post_comment_num.setText(Integer.toString(post_item_list.get(position).getComment_num()));

        holder.itemView.setTag(position);

        /**
         * click 리스너 달 거면 여기에 달 것
         * */
//        holder.itemView.setOnClickListener(v -> {
//            Log.d(TAG, "onBindViewHolder: 아이템 클릭됨!");
//            // new Intent(내위치 , 이동할 위치) 해야하는데 내위치 찾는법을 알아내야함. Context랑 관련있음
//            // putExtra 해서 post_item 값들 다 넘겨줘야할듯.
//            Intent intent = new Intent(mContext, InPostActivity.class).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//            mContext.startActivity(intent);
//        });

    }

    @Override
    public int getItemCount() {
        return (null != post_item_list ? post_item_list.size() : 0);
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {

        protected int content_index;
        protected TextView tv_item_post_title;
        protected TextView tv_item_post_content;
        protected TextView tv_item_post_time;
        protected TextView tv_item_post_nickname;
        protected TextView tv_item_post_like_num;
        protected TextView tv_item_post_comment_num;
        protected LinearLayout free_board_item;


        public CustomViewHolder(@NonNull final View itemView) {

            super(itemView);
            this.tv_item_post_title = itemView.findViewById(R.id.tv_item_post_title);
            this.tv_item_post_content = itemView.findViewById(R.id.tv_item_post_content);
            this.tv_item_post_time = itemView.findViewById(R.id.tv_item_post_time);
            this.tv_item_post_nickname = itemView.findViewById(R.id.tv_item_post_nickname);
            this.tv_item_post_like_num = itemView.findViewById(R.id.tv_item_post_like_num);
            this.tv_item_post_comment_num = itemView.findViewById(R.id.tv_item_post_comment_num);
            free_board_item = itemView.findViewById(R.id.item_summarized_post);

             // 글 목록 (아이템) 눌렀을 때
            free_board_item.setOnClickListener(v -> {
                Log.d(TAG, "CustomViewHolder: 아이템 눌렀음! 어댑터번호" + getAdapterPosition());
                Log.d(TAG, "CustomViewHolder: 실제 아이디" + getBoardId(getAdapterPosition()));


                Intent intent = new Intent(mContext, InPostActivity.class).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("freeBoardId", getBoardId(getAdapterPosition()));
//                intent.putExtra("freeBoardTitle", getBoardTitle(getAdapterPosition()));
//                intent.putExtra("freeBoardContent", getBoardContent(getAdapterPosition()));
//                intent.putExtra("freeBoardWriter", getBoardWriter(getAdapterPosition()));
//                intent.putExtra("freeBoardTime", getBoardTime(getAdapterPosition()));
                mContext.startActivity(intent);
                ((Activity)v.getContext()).finish();


            });
        }
    }
}
