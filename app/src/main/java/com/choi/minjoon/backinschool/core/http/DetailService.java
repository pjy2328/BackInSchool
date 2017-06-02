package com.choi.minjoon.backinschool.core.http;

import com.choi.minjoon.backinschool.common.domain.detail.DetailItem;
import com.choi.minjoon.backinschool.common.domain.home.FirstItem;
import com.choi.minjoon.backinschool.common.domain.home.SecondItem;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by User on 2017-06-01.
 */

public interface DetailService {

    public static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://192.168.56.1:8080/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    @GET("users")
    Call<List<DetailItem>> getItems();

    @GET("users/{userId}")
    Call<FirstItem> getItem(@Path("userId") String id);


    @POST("users")
    Call<SecondItem> createItem();
}
