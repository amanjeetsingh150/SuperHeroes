package com.developers.superheroes.dagger;

import com.developers.superheroes.ui.SuperHeroActivity.MainActivity;
import com.developers.superheroes.ui.SuperHeroActivity.MainPresenterImpl;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Amanjeet Singh on 07-Jul-17.
 */
@Singleton
@Component(modules={AppModule.class,
                    PresenterModule.class,
                    NetModule.class})
public interface AppComponent {

    void inject(MainActivity target);

    void inject(MainPresenterImpl Impltarget);


}
