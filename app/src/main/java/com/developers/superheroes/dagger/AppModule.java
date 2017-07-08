package com.developers.superheroes.dagger;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Amanjeet Singh on 07-Jul-17.
 */
@Module
public class AppModule {

    private Application application;

    public AppModule(Application application){
        this.application=application;
    }

    @Provides
    @Singleton
    public Context getContext(){
        return application;
    }


    @Provides
    @Singleton
    SharedPreferences providesSharedPreferences() {
        return PreferenceManager.getDefaultSharedPreferences(application);
    }

}
