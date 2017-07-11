package com.developers.superheroes.ui.SuperHeroActivity;

import android.app.SearchManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.developers.superheroes.R;
import com.developers.superheroes.InitApplication;
import com.developers.superheroes.adapter.SuperHeroAdapter;
import com.developers.superheroes.model.Result;
import com.developers.superheroes.model.SuperHero;
import com.developers.superheroes.util.OnLoadMoreListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements MainView {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    List<Integer> idList;
    private Set<String> ids;
    private List<Integer> updateIntList;
    private int idInt;
    private Set<String> prefSet;
    private List<SuperHero> mSuperHeroes;
    String text;

    @Inject
    MainPresenter presenter;
    @Inject
    SharedPreferences sharedPreferences;
    SuperHeroAdapter superHeroAdapter;


    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ((InitApplication) getApplication()).getAppComponent().inject(this);
        ButterKnife.bind(this);
        presenter.setView(this);
        makeList();
        presenter.getHeroes(idList, null);
        updateIntList = new ArrayList<>();
    }

    private void makeList() {
        idList = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
        prefSet = new HashSet<String>(Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8",
                "9", "10"));
        sharedPreferences.edit().putStringSet("IDS", prefSet).apply();
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
        this.mSuperHeroes=resultList;
        Log.d(TAG,"Size: "+mSuperHeroes.size());
        GridLayoutManager llm = new GridLayoutManager(getApplicationContext(), 2);
        superHeroAdapter = new SuperHeroAdapter(MainActivity.this, recyclerView, resultList, llm);
        recyclerView.setLayoutManager(llm);
        recyclerView.setAdapter(superHeroAdapter);
        if (resultList.size() <= 600) {
            resultList.add(null);
            superHeroAdapter.notifyItemInserted(resultList.size() - 1);
        }
        superHeroAdapter.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                if (resultList.size() <= 600) {
                    //Generate more data
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            resultList.remove(resultList.size() - 1);
                            superHeroAdapter.notifyItemRemoved(resultList.size());
                            ids = sharedPreferences.getStringSet("IDS", null);
                            if (ids != null) {
                                Log.d(TAG, "Updating...");
                                for (String s : ids) {
                                    idInt = Integer.parseInt(s);
                                    updateIntList.add(idInt);
                                }
                                Log.d(TAG, updateIntList + "");
                                for (int i = 0; i < updateIntList.size(); i++) {
                                    updateIntList.set(i, updateIntList.get(i) + 10);
                                }
                                Log.d(TAG, updateIntList + "");
                            } else {
                                Log.d(TAG, "Set unavailable");
                            }
                            presenter.getHeroes(updateIntList, superHeroAdapter);
                            superHeroAdapter.notifyDataSetChanged();
                            superHeroAdapter.setLoaded();
                            prefSet.clear();
                            for (int j = 0; j < updateIntList.size(); j++) {
                                prefSet.add(String.valueOf(updateIntList.get(j)));
                            }
                            sharedPreferences.edit().putStringSet("IDS", prefSet).apply();
                        }
                    }, 5000);
                } else {
                    Log.d(TAG, "Stopped : " + resultList.size());
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.main_menu,menu);
        MenuItem searchItem=menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                //TODO:Handle NullPointer
                List<SuperHero> filteredModelList = filter(mSuperHeroes, query);
                superHeroAdapter.setFilter(filteredModelList);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                Log.d(TAG,newText);
                return true;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    private List<SuperHero> filter(List<SuperHero> models, String query) {
        query = query.toLowerCase();
        final List<SuperHero> filteredModelList = new ArrayList<>();
        if(models!=null){
            Log.d(TAG,"Searching Null");
        }
        for(int j=0;j<models.size();j++){
            text = models.get(j).getName().toLowerCase();
            if (text.contains(query)) {
                filteredModelList.add(models.get(j));
            }
        }
        return filteredModelList;
    }

}
