
package com.example.myheroesinkotlin.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Appearance {

    @SerializedName("gender")
    @Expose
    private String gender;
    @SerializedName("race")
    @Expose
    private String race;
    @SerializedName("height")
    @Expose
    private List<String> height = null;
    @SerializedName("weight")
    @Expose
    private List<String> weight = null;
    @SerializedName("eyeColor")
    @Expose
    private String eyeColor;
    @SerializedName("hairColor")
    @Expose
    private String hairColor;

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

    public List<String> getHeight() {
        return height;
    }

    public void setHeight(List<String> height) {
        this.height = height;
    }

    public List<String> getWeight() {
        return weight;
    }

    public void setWeight(List<String> weight) {
        this.weight = weight;
    }

    public String getEyeColor() {
        return eyeColor;
    }

    public void setEyeColor(String eyeColor) {
        this.eyeColor = eyeColor;
    }

    public String getHairColor() {
        return hairColor;
    }

    public void setHairColor(String hairColor) {
        this.hairColor = hairColor;
    }

}
