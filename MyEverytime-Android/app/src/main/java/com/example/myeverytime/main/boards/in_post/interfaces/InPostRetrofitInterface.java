package com.example.myeverytime.main.boards.in_post.interfaces;

import com.example.myeverytime.CMRespDto;
import com.example.myeverytime.main.boards.model.PostItem;
import com.example.myeverytime.main.boards.updating.model.UpdatingReqDto;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface InPostRetrofitInterface {

    @GET("board/{id}")
    Call<CMRespDto<PostItem>> getOneFreeBoard(@Path("id") Long id);

    @DELETE("board/{id}")
    Call<Void> deleteOneFreeBoard(@Path("id") Long id);

    @PUT("board/{id}")
    Call<CMRespDto<UpdatingReqDto>> updateFreeBoard(@Path("id") Long id, @Body UpdatingReqDto updatingReqDto);
}
