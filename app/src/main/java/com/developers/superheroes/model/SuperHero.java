package com.developers.superheroes.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Amanjeet Singh on 08-Jul-17.
 */

public class SuperHero implements Parcelable {

    String intelligence;
    String strength;
    String speed;
    String durability;
    String combat;
    String imgUrl;
    String fullName;
    String alter;
    String placeOfBirth;
    String firstAppear;
    String publisher;
    String gender;
    String power;
    String race;
    String height;
    String weight;
    String name;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getPower() {
        return power;
    }

    public void setPower(String power) {
        this.power = power;
    }

    public String getIntelligence() {
        return intelligence;
    }

    public void setIntelligence(String intelligence) {
        this.intelligence = intelligence;
    }

    public String getStrength() {
        return strength;
    }

    public void setStrength(String strength) {
        this.strength = strength;
    }

    public String getSpeed() {
        return speed;
    }

    public void setSpeed(String speed) {
        this.speed = speed;
    }

    public String getDurability() {
        return durability;
    }

    public void setDurability(String durability) {
        this.durability = durability;
    }

    public String getCombat() {
        return combat;
    }

    public void setCombat(String combat) {
        this.combat = combat;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAlter() {
        return alter;
    }

    public void setAlter(String alter) {
        this.alter = alter;
    }

    public String getPlaceOfBirth() {
        return placeOfBirth;
    }

    public void setPlaceOfBirth(String placeOfBirth) {
        this.placeOfBirth = placeOfBirth;
    }

    public String getFirstAppear() {
        return firstAppear;
    }

    public void setFirstAppear(String firstAppear) {
        this.firstAppear = firstAppear;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }


    public SuperHero() {

    }

    protected SuperHero(Parcel in) {
        String[] data=new String[17];
        in.readStringArray(data);
        this.intelligence=data[0];
        this.strength=data[1];
        this.speed=data[2];
        this.durability=data[3];
        this.combat=data[4];
        this.imgUrl=data[5];
        this.fullName=data[6];
        this.alter=data[7];
        this.placeOfBirth=data[8];
        this.firstAppear=data[9];
        this.publisher=data[10];
        this.gender=data[11];
        this.race=data[12];
        this.height=data[13];
        this.weight=data[14];
        this.power=data[15];
        this.name=data[16];
    }

    public static final Creator<SuperHero> CREATOR = new Creator<SuperHero>() {
        @Override
        public SuperHero createFromParcel(Parcel in) {
            return new SuperHero(in);
        }

        @Override
        public SuperHero[] newArray(int size) {
            return new SuperHero[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeStringArray(new String[]{
                this.intelligence,
                this.strength,
                this.speed,
                this.durability,
                this.combat,
                this.imgUrl,
                this.fullName,
                this.alter,
                this.placeOfBirth,
                this.firstAppear,
                this.publisher,
                this.gender,
                this.race,
                this.height,
                this.weight,
                this.power,
                this.name
        });
    }
}
