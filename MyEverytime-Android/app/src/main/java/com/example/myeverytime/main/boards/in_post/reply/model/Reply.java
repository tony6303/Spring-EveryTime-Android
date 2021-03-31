package com.example.myeverytime.main.boards.in_post.reply.model;

public class Reply {
    private String nickname;
    private String content;
    private String createDate;

    public Reply(String nickname, String content, String createDate) {
        this.nickname = nickname;
        this.content = content;
        this.createDate = createDate;
    }

    public Reply() {
    }

    @Override
    public String toString() {
        return "Reply{" +
                "nickname='" + nickname + '\'' +
                ", content='" + content + '\'' +
                ", createDate='" + createDate + '\'' +
                '}';
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }
}
