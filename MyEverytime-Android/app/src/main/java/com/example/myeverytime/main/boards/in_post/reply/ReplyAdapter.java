package com.example.myeverytime.main.boards.in_post.reply;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myeverytime.R;
import com.example.myeverytime.main.boards.in_post.reply.model.Reply;

import java.util.ArrayList;

public class ReplyAdapter extends RecyclerView.Adapter<ReplyAdapter.ReplyViewHolder> {

    private static final String TAG = "ReplyAdapter";
    private ArrayList<Reply> reply_item_list;

    public ReplyAdapter(ArrayList<Reply> reply_item_list) {
        this.reply_item_list = reply_item_list;
    }

    @NonNull
    @Override
    public ReplyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ReplyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_reply, parent,false));
        // ViewHolder에 꼽아서 반환함
    }

    @Override
    public void onBindViewHolder(@NonNull ReplyViewHolder holder, int position) {
        holder.setReply(reply_item_list.get(position));
    }

    @Override
    public int getItemCount() {
        return reply_item_list.size();
    }


    // 1번 ViewHolder 만들기 . 내부클래스로 만들어야함!!!
    // 다른데서 안쓰고 나만 쓸거라서 내부클래스임
    // ViewHolder란 : 하나의 View를 갖고있음.  // 위험한점 : 데이터는 없고 View만 있음
    public class ReplyViewHolder extends RecyclerView.ViewHolder {

        // 2번 item_reply가 가지고 있는 위젯들을 선언
        private TextView tv_reply_nickname;
        private TextView tv_reply_content;
        private TextView tv_reply_create_date;
        private ImageView iv_item_reply_more;
        private ImageView iv_item_reply_like;


        public ReplyViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_reply_nickname = itemView.findViewById(R.id.tv_item_reply_nickname);
            tv_reply_content = itemView.findViewById(R.id.tv_item_reply_content);
            tv_reply_create_date = itemView.findViewById(R.id.tv_item_reply_time);
            iv_item_reply_more = itemView.findViewById(R.id.iv_item_reply_more);
            iv_item_reply_like = itemView.findViewById(R.id.iv_item_reply_like);

            iv_item_reply_more.setOnClickListener(v -> {


            });
        }

        public void setReply(Reply reply){
            tv_reply_nickname.setText(reply.getNickname());
            tv_reply_content.setText(reply.getContent());
            tv_reply_create_date.setText(reply.getCreateDate());
//            tv_reply_create_date.setText(reply.getCreateDate().substring(5,16));
        }
    }
}
