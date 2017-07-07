package com.developers.superheroes.ui.SuperHeroActivity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.developers.superheroes.R;
import com.developers.superheroes.InitApplication;
import com.developers.superheroes.model.Result;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements MainView {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    List<Integer> idList;

    @Inject
    MainPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ((InitApplication)getApplication()).getAppComponent().inject(this);
        ButterKnife.bind(this);
        presenter.setView(this);
        makeList();
    }

    private void makeList(){
        idList=new ArrayList<>(Arrays.asList(1,2,3,4,5,6,7,8,9,10));
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.getHeroes(idList);
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showError(String error) {

    }

    @Override
    public void showHeroes(List<Result> resultList) {

    }

}
