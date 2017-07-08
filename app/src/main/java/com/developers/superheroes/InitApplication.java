package com.developers.superheroes;

import android.app.Application;

import com.developers.superheroes.dagger.AppComponent;
import com.developers.superheroes.dagger.AppModule;
import com.developers.superheroes.dagger.DaggerAppComponent;
import com.developers.superheroes.dagger.NetModule;
import com.developers.superheroes.dagger.PresenterModule;

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
                .netModule(new NetModule())
                .presenterModule(new PresenterModule())
                .build();
    }

    public AppComponent getAppComponent(){
        return appComponent;
    }

}
