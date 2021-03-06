package com.developers.superheroes.ui.SuperHeroActivity;

import com.developers.superheroes.model.Result;
import com.developers.superheroes.model.SuperHero;

import java.util.List;

/**
 * Created by Amanjeet Singh on 07-Jul-17.
 */

public interface MainView {

    void showLoading();

    void hideLoading();

    void showError(String error);

    void showHeroes(List<SuperHero> resultList);

}
