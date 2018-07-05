package com.example.home.bt_worldcup.models;

import android.support.annotation.NonNull;

/**
 * Created by Home on 6/25/2018.
 */

public class Team implements Comparable<Team>{
    int id;
    String country;
    String fifa_code;
    int id_group;
    String group_letter;

    public Team(int id, String country, String fifa_code, int id_group, String group_letter) {
        this.id = id;
        this.country = country;
        this.fifa_code = fifa_code;
        this.id_group = id_group;
        this.group_letter = group_letter;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getFifa_code() {
        return fifa_code;
    }

    public void setFifa_code(String fifa_code) {
        this.fifa_code = fifa_code;
    }

    public int getId_group() {
        return id_group;
    }

    public void setId_group(int id_group) {
        this.id_group = id_group;
    }

    public String getGroup_letter() {
        return group_letter;
    }

    public void setGroup_letter(String group_letter) {
        this.group_letter = group_letter;
    }

    @Override
    public int compareTo(@NonNull Team team) {
        return this.id - team.getId();
    }
}
