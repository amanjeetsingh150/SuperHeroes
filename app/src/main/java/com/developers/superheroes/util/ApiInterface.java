package com.developers.superheroes.util;

import com.developers.superheroes.model.Result;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Amanjeet Singh on 07-Jul-17.
 */

public interface ApiInterface {

    @GET(Constants.BASE_URL+"/{id}")
    Call<Result> getHeroesById(@Path("id") int charId);

}
