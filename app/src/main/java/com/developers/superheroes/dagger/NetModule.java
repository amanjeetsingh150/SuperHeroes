package com.developers.superheroes.dagger;

import android.util.Log;

import com.developers.superheroes.BuildConfig;
import com.developers.superheroes.util.ApiInterface;
import com.developers.superheroes.util.Constants;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.developers.superheroes.BuildConfig.SUPERHERO_KEY;

/**
 * Created by Amanjeet Singh on 07-Jul-17.
 */
@Module
public class NetModule {

    private static final String BASE_URL = "NAME_BASE_URL";

    @Provides
    @Named(BASE_URL)
    String provideBaseUrlString() {
        return Constants.BASE_URL;
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(@Named(BASE_URL) String base_url,
                             Converter.Factory factory) {
        return new Retrofit.Builder()
                .baseUrl(base_url)
                .addConverterFactory(factory)
                .build();
    }

    @Provides
    @Singleton
    Converter.Factory provideGsonConverter(){
        return GsonConverterFactory.create();
    }

    @Provides
    @Singleton
    ApiInterface provideSuperHeroApi(Retrofit retrofit)
    {
        return retrofit.create(ApiInterface.class);
    }


}
