package com.developers.superheroes.util;

import com.developers.superheroes.model.Result;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Amanjeet Singh on 07-Jul-17.
 */

public interface ApiInterface {

    @GET("/")
    Call<Result> getHeroesById(@Query("id")int charId);

}
