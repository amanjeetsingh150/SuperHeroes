package com.developers.superheroes.ui.SuperHeroActivity;

import android.content.Context;
import android.util.Log;

import com.developers.superheroes.InitApplication;
import com.developers.superheroes.model.Result;
import com.developers.superheroes.util.ApiInterface;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Amanjeet Singh on 07-Jul-17.
 */

public class MainPresenterImpl implements MainPresenter {

    private MainView view;

    @Inject
    ApiInterface apiClient;

    private static final String TAG = MainPresenterImpl.class.getSimpleName();


    public MainPresenterImpl(Context context) {
        ((InitApplication) context).getAppComponent().inject(this);
    }

    @Override
    public void setView(MainView view) {
        this.view = view;
    }

    @Override
    public void getHeroes(int id) {
        apiClient.getHeroesById(id)
                .enqueue(new Callback<Result>() {
                    @Override
                    public void onResponse(Call<Result> call, Response<Result> response) {
                        String name=response.body().getName();
                    }

                    @Override
                    public void onFailure(Call<Result> call, Throwable t) {

                    }
                });

    }
}
