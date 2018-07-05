package com.example.home.bt_worldcup.models;

import java.io.Serializable;

/**
 * Created by Home on 6/26/2018.
 */

public class TeamEvents implements Serializable{
    private Integer id;
    private String typeOfEvent;
    private String player;
    private String country;
    private String code_team;
    private String time;
    private int goalsHome;
    private int goalsAway;
    //private Map<String, Object> additionalProperties = new HashMap<String, Object>();


    public TeamEvents(Integer id, String typeOfEvent, String player, String time) {
        this.id = id;
        this.typeOfEvent = typeOfEvent;
        this.player = player;
        this.time = time;
    }

    public TeamEvents(Integer id, String typeOfEvent, String player, String country, String code_team, String time) {
        this.id = id;
        this.typeOfEvent = typeOfEvent;
        this.player = player;
        this.country = country;
        this.code_team = code_team;
        this.time = time;
    }

    public TeamEvents(Integer id, String typeOfEvent, String player, String country, String code_team, String time, int goalsHome, int goalsAway) {
        this.id = id;
        this.typeOfEvent = typeOfEvent;
        this.player = player;
        this.country = country;
        this.code_team = code_team;
        this.time = time;
        this.goalsHome = goalsHome;
        this.goalsAway = goalsAway;
    }

    public int getGoalsHome() {
        return goalsHome;
    }

    public void setGoalsHome(int goalsHome) {
        this.goalsHome = goalsHome;
    }

    public int getGoalsAway() {
        return goalsAway;
    }

    public void setGoalsAway(int goalsAway) {
        this.goalsAway = goalsAway;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCode_team() {
        return code_team;
    }

    public void setCode_team(String code_team) {
        this.code_team = code_team;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTypeOfEvent() {
        return typeOfEvent;
    }

    public void setTypeOfEvent(String typeOfEvent) {
        this.typeOfEvent = typeOfEvent;
    }

    public String getPlayer() {
        return player;
    }

    public void setPlayer(String player) {
        this.player = player;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    /*public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }*/
}
