package com.developers.superheroes.ui.SuperHeroActivity;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.developers.superheroes.R;
import com.developers.superheroes.InitApplication;
import com.developers.superheroes.adapter.SuperHeroAdapter;
import com.developers.superheroes.model.Result;
import com.developers.superheroes.model.SuperHero;
import com.developers.superheroes.util.OnLoadMoreListener;

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
    @Inject
    SharedPreferences sharedPreferences;


    private static final String TAG=MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ((InitApplication)getApplication()).getAppComponent().inject(this);
        ButterKnife.bind(this);
        presenter.setView(this);
        makeList();
        presenter.getHeroes(idList);
    }

    private void makeList(){
        idList=new ArrayList<>(Arrays.asList(1,2,3,4,5,6,7,8,9,10));
    }

    @Override
    protected void onStart() {
        super.onStart();
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
    public void showHeroes(final List<SuperHero> resultList) {
        GridLayoutManager llm = new GridLayoutManager(getApplicationContext(),2);
        final SuperHeroAdapter superHeroAdapter=new SuperHeroAdapter(MainActivity.this,recyclerView,resultList,llm);
        recyclerView.setLayoutManager(llm);
        recyclerView.setAdapter(superHeroAdapter);
        superHeroAdapter.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                Log.d(TAG,"Load More Data");
                if(resultList.size()<=10){
                    resultList.add(null);
                    superHeroAdapter.notifyItemInserted(resultList.size()-1);
                    //Generate more data

                }
            }
        });
    }

}
