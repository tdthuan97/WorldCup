package com.example.home.bt_worldcup.models;

import android.support.annotation.NonNull;

/**
 * Created by Home on 6/25/2018.
 */

public class TeamGroup implements Comparable<TeamGroup>{
    int id;
    String country;
    String fifa_code;
    int points;
    int wins;
    int draws;
    int losses;
    int game_played;
    int goals_for;
    int goals_against;
    int goal_differential;

    public TeamGroup(int id, String country, String fifa_code, int points, int wins, int draws, int losses, int game_played, int goals_for, int goals_against, int goal_differential) {
        this.id = id;
        this.country = country;
        this.fifa_code = fifa_code;
        this.points = points;
        this.wins = wins;
        this.draws = draws;
        this.losses = losses;
        this.game_played = game_played;
        this.goals_for = goals_for;
        this.goals_against = goals_against;
        this.goal_differential = goal_differential;
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

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public int getDraws() {
        return draws;
    }

    public void setDraws(int draws) {
        this.draws = draws;
    }

    public int getLosses() {
        return losses;
    }

    public void setLosses(int losses) {
        this.losses = losses;
    }

    public int getGame_played() {
        return game_played;
    }

    public void setGame_played(int game_played) {
        this.game_played = game_played;
    }

    public int getGoals_for() {
        return goals_for;
    }

    public void setGoals_for(int goals_for) {
        this.goals_for = goals_for;
    }

    public int getGoals_against() {
        return goals_against;
    }

    public void setGoals_against(int goals_against) {
        this.goals_against = goals_against;
    }

    public int getGoal_differential() {
        return goal_differential;
    }

    public void setGoal_differential(int goal_differential) {
        this.goal_differential = goal_differential;
    }

    @Override
    public int compareTo(@NonNull TeamGroup teamGroup) {
        int point = this.points - teamGroup.points;
        if(point == 0){
            return this.goal_differential - teamGroup.goal_differential;
        }
        return this.points - teamGroup.points;
    }
}
