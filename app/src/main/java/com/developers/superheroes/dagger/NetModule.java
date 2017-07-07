package com.developers.superheroes.dagger;

import com.developers.superheroes.util.ApiInterface;
import com.developers.superheroes.util.Constants;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Converter;
import retrofit2.Retrofit;

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
    ApiInterface provideSuperHeroApi(Retrofit retrofit)
    {
        return retrofit.create(ApiInterface.class);
    }


}
