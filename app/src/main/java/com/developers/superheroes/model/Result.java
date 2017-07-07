package com.developers.superheroes.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Amanjeet Singh on 07-Jul-17.
 */

public class Result {

    @SerializedName("response")
    @Expose
    private String response;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("powerstats")
    @Expose
    private Powerstats powerstats;
    @SerializedName("biography")
    @Expose
    private Biography biography;
    @SerializedName("appearance")
    @Expose
    private Appearance appearance;
    @Expose
    private Image image;

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Powerstats getPowerstats() {
        return powerstats;
    }

    public void setPowerstats(Powerstats powerstats) {
        this.powerstats = powerstats;
    }

    public Biography getBiography() {
        return biography;
    }

    public void setBiography(Biography biography) {
        this.biography = biography;
    }

    public Appearance getAppearance() {
        return appearance;
    }

    public void setAppearance(Appearance appearance) {
        this.appearance = appearance;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

}

