package com.choi.minjoon.backinschool.core.http;

import com.choi.minjoon.backinschool.common.domain.home.FirstItem;
import com.choi.minjoon.backinschool.common.domain.home.SecondItem;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by User on 2017-06-01.
 */

public interface HomeService {

    public static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    @GET("posts")
    Call<List<FirstItem>> FirstItems();

    @GET("posts/{id}")
    Call<FirstItem> getItem(@Path("id") String id);


    @GET("albums")
    Call<List<SecondItem>> SecondItems();
}
