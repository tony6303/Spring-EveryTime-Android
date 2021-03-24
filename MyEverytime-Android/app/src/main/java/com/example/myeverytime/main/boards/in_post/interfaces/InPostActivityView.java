package com.example.myeverytime.main.boards.in_post.interfaces;

import com.example.myeverytime.CMRespDto;

public interface InPostActivityView {

    void validateSuccess(String text);

    void validateFailure(String message);

    void freeBoardSuccess(CMRespDto cmRespDto);

    void freeBoardDeleteSuccess();

    void freeBoardUpdateSuccess(CMRespDto cmRespDto);
}
