package com.developers.superheroes.ui.SuperHeroActivity;

import java.util.List;

/**
 * Created by Amanjeet Singh on 07-Jul-17.
 */

public interface MainPresenter {

    void setView(MainView view);
    void getHeroes(List<Integer> ids);
}
