package com.developers.superheroes.ui.SuperHeroActivity;

import android.content.Context;
import android.util.Log;

import com.developers.superheroes.InitApplication;
import com.developers.superheroes.model.Appearance;
import com.developers.superheroes.model.Biography;
import com.developers.superheroes.model.Image;
import com.developers.superheroes.model.Powerstats;
import com.developers.superheroes.model.Result;
import com.developers.superheroes.util.ApiInterface;

import java.util.List;

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
    public void getHeroes(List<Integer> ids) {
        for (int i : ids) {
            apiClient.getHeroesById(i)
                    .enqueue(new Callback<Result>() {
                        @Override
                        public void onResponse(Call<Result> call, Response<Result> response) {
                            String name = response.body().getName();
                            Powerstats powerstats = response.body().getPowerstats();
                            String intelligence = powerstats.getIntelligence();
                            String strength = powerstats.getStrength();
                            String speed = powerstats.getSpeed();
                            String durability = powerstats.getDurability();
                            String power = powerstats.getPower();
                            String combat = powerstats.getCombat();
                            Image image = response.body().getImage();
                            String imgUrl = image.getUrl();
                            Biography biography = response.body().getBiography();
                            String fullName=biography.getFullName();
                            String alter=biography.getAlterEgos();
                            String placeOfBirth=biography.getPlaceOfBirth();
                            String firstAppear=biography.getFirstAppearance();
                            String publisher=biography.getPublisher();
                            Appearance appearance=response.body().getAppearance();
                            String gender=appearance.getGender();
                            String race=appearance.getRace();
                            List<String> height=appearance.getHeight();
                            List<String> weight=appearance.getWeight();
                        }

                        @Override
                        public void onFailure(Call<Result> call, Throwable t) {

                        }
                    });
        }
    }
}
