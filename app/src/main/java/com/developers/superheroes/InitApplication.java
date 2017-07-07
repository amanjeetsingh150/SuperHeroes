package com.developers.superheroes;

import android.app.Application;

import com.developers.superheroes.dagger.AppComponent;
import com.developers.superheroes.dagger.AppModule;
import com.developers.superheroes.dagger.DaggerAppComponent;

/**
 * Created by Amanjeet Singh on 07-Jul-17.
 */

public class InitApplication extends Application {

    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        appComponent=initDagger(this);
    }

    protected AppComponent initDagger(InitApplication application){
        return DaggerAppComponent.builder()
                .appModule(new AppModule(application))
                .build();
    }

    public AppComponent getAppComponent(){
        return appComponent;
    }

}
