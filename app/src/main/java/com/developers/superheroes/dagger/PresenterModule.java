package com.developers.superheroes.dagger;

import android.content.Context;

import com.developers.superheroes.ui.SuperHeroActivity.MainPresenter;
import com.developers.superheroes.ui.SuperHeroActivity.MainPresenterImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Amanjeet Singh on 07-Jul-17.
 */
@Module
public class PresenterModule {

    @Provides
    @Singleton
    protected MainPresenter provideMainPresenter(Context context){
        return new MainPresenterImpl(context);
    }

}
