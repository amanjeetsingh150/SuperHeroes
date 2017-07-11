package com.developers.superheroes.ui.SuperHeroActivity;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;

import com.developers.superheroes.InitApplication;
import com.developers.superheroes.adapter.SuperHeroAdapter;
import com.developers.superheroes.model.Result;
import com.developers.superheroes.model.SuperHero;
import com.developers.superheroes.util.ApiInterface;
import com.developers.superheroes.util.Constants;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;
import javax.net.ssl.HttpsURLConnection;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Amanjeet Singh on 07-Jul-17.
 */

public class MainPresenterImpl implements MainPresenter {

    private MainView view;
    private SuperHeroAdapter superHeroAdapter;

    @Inject
    ApiInterface apiClient;

    private List<SuperHero> superHeroesList = new ArrayList<>();
    private int i;

    private static final String TAG = MainPresenterImpl.class.getSimpleName();


    public MainPresenterImpl(Context context) {
        ((InitApplication) context).getAppComponent().inject(this);
    }

    @Override
    public void setView(MainView view) {
        this.view = view;
    }

    @Override
    public void getHeroes(final List<Integer> ids, SuperHeroAdapter superHeroAdapter) {
        this.superHeroAdapter=superHeroAdapter;
        new FetchHeroes().execute(ids);
    }



    class FetchHeroes extends AsyncTask<List<Integer>, Void, List<SuperHero>> {

        List<SuperHero> superHeroList = new ArrayList<>();

        @Override
        protected List<SuperHero> doInBackground(List<Integer>... lists) {
            for (int i = 0; i < lists[0].size(); i++) {
                Log.d(TAG,lists[0].get(i)+"");
                HttpURLConnection connection = null;
                BufferedReader bufferReader = null;
                try {
                    URL url = new URL(Constants.BASE_URL + String.valueOf(lists[0].get(i)));
                    connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.connect();
                    InputStream inputstream = connection.getInputStream();
                    StringBuffer buffer = new StringBuffer();
                    String line = "";
                    bufferReader = new BufferedReader(new InputStreamReader(inputstream));
                    while ((line = bufferReader.readLine()) != null) {
                        buffer.append(line + "\n");
                    }
                    Log.d(TAG, buffer.toString());
                    superHeroList = parseJson(buffer.toString());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return superHeroList;
        }

        @Override
        protected void onPostExecute(List<SuperHero> superHeroList) {
            super.onPostExecute(superHeroList);
            view.showHeroes(superHeroList);
        }

        private List<SuperHero> parseJson(String response) {
            try {
                JSONObject responseObj = new JSONObject(response);
                String name = responseObj.getString("name");
                JSONObject powerResponse = responseObj.getJSONObject("powerstats");
                String intelligence = powerResponse.getString("intelligence");
                String strength = powerResponse.getString("strength");
                String speed = powerResponse.getString("speed");
                String durability = powerResponse.getString("durability");
                String power = powerResponse.getString("power");
                String combat = powerResponse.getString("combat");
                JSONObject biographyResponse = responseObj.getJSONObject("biography");
                String fullName = biographyResponse.getString("full-name");
                String alter = biographyResponse.getString("alter-egos");
                String placeOfBirth = biographyResponse.getString("place-of-birth");
                String firstAppearance = biographyResponse.getString("first-appearance");
                String publisher = biographyResponse.getString("publisher");
                JSONObject appearanceResponse = responseObj.getJSONObject("appearance");
                String gender = appearanceResponse.getString("gender");
                String race = appearanceResponse.getString("race");
                String heightArr = appearanceResponse.getString("height");
                heightArr = heightArr.substring(1, 6);
                String weightArr = appearanceResponse.getString("weight");
                weightArr = weightArr.substring(1, weightArr.indexOf(","));
                JSONObject imageResponse = responseObj.getJSONObject("image");
                String imageUrl = imageResponse.getString("url");
                SuperHero superHero = new SuperHero();
                superHero.setIntelligence(intelligence);
                superHero.setStrength(strength);
                superHero.setSpeed(speed);
                superHero.setDurability(durability);
                superHero.setCombat(combat);
                superHero.setImgUrl(imageUrl);
                superHero.setFullName(fullName);
                superHero.setAlter(alter);
                superHero.setPlaceOfBirth(placeOfBirth);
                superHero.setFirstAppear(firstAppearance);
                superHero.setPublisher(publisher);
                superHero.setGender(gender);
                superHero.setPower(power);
                superHero.setRace(race);
                superHero.setHeight(heightArr);
                superHero.setWeight(weightArr);
                superHero.setName(name);
                superHeroesList.add(superHero);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return superHeroesList;
        }
    }
}
