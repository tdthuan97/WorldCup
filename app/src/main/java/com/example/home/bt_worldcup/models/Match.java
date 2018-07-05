package com.example.home.bt_worldcup.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Home on 6/27/2018.
 */

public class Match implements Parcelable,Serializable{
    private String venue;
    private String location;
    private String status;
    private Object time;
    private String fifaId;
    private Weather weather;
    private String attendance;
    private List<String> officials = null;
    private String stageName;
    private String homeTeamCountry;
    private String awayTeamCountry;
    private String datetime;
    private Object winner;
    private Object winnerCode;
    private TeamsInMatch homeTeam;
    private TeamsInMatch awayTeam;
    private List<TeamEvents> homeTeamEvents = null; // status completed
    private List<TeamEvents> awayTeamEvents = null;
    private  TeamStatistics homeTeamStatistics;
    private  TeamStatistics awayTeamStatistics;
    private Object lastEventUpdateAt;
    private Object lastScoreUpdateAt;
    //private Map<String, Object> additionalProperties = new HashMap<String, Object>();


    public Match(String venue, String location, String status, Object time, String fifaId, Weather weather, String attendance, List<String> officials, String stageName, String homeTeamCountry, String awayTeamCountry, String datetime, Object winner, Object winnerCode, TeamsInMatch homeTeam, TeamsInMatch awayTeam, List<TeamEvents> homeTeamEvents, List<TeamEvents> awayTeamEvents, TeamStatistics homeTeamStatistics, TeamStatistics awayTeamStatistics, Object lastEventUpdateAt, Object lastScoreUpdateAt) {
        this.venue = venue;
        this.location = location;
        this.status = status;
        this.time = time;
        this.fifaId = fifaId;
        this.weather = weather;
        this.attendance = attendance;
        this.officials = officials;
        this.stageName = stageName;
        this.homeTeamCountry = homeTeamCountry;
        this.awayTeamCountry = awayTeamCountry;
        this.datetime = datetime;
        this.winner = winner;
        this.winnerCode = winnerCode;
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.homeTeamEvents = homeTeamEvents;
        this.awayTeamEvents = awayTeamEvents;
        this.homeTeamStatistics = homeTeamStatistics;
        this.awayTeamStatistics = awayTeamStatistics;
        this.lastEventUpdateAt = lastEventUpdateAt;
        this.lastScoreUpdateAt = lastScoreUpdateAt;
    }


    protected Match(Parcel in) {
        venue = in.readString();
        location = in.readString();
        status = in.readString();
        fifaId = in.readString();
        attendance = in.readString();
        officials = in.createStringArrayList();
        stageName = in.readString();
        homeTeamCountry = in.readString();
        awayTeamCountry = in.readString();
        datetime = in.readString();
    }

    public static final Creator<Match> CREATOR = new Creator<Match>() {
        @Override
        public Match createFromParcel(Parcel in) {
            return new Match(in);
        }

        @Override
        public Match[] newArray(int size) {
            return new Match[size];
        }
    };

    public TeamStatistics getHomeTeamStatistics() {
        return homeTeamStatistics;
    }

    public void setHomeTeamStatistics(TeamStatistics homeTeamStatistics) {
        this.homeTeamStatistics = homeTeamStatistics;
    }

    public TeamStatistics getAwayTeamStatistics() {
        return awayTeamStatistics;
    }

    public void setAwayTeamStatistics(TeamStatistics awayTeamStatistics) {
        this.awayTeamStatistics = awayTeamStatistics;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Object getTime() {
        return time;
    }

    public void setTime(Object time) {
        this.time = time;
    }

    public String getFifaId() {
        return fifaId;
    }

    public void setFifaId(String fifaId) {
        this.fifaId = fifaId;
    }

    public Weather getWeather() {
        return weather;
    }

    public void setWeather(Weather weather) {
        this.weather = weather;
    }

    public String getAttendance() {
        return attendance;
    }

    public void setAttendance(String attendance) {
        this.attendance = attendance;
    }

    public List<String>  getOfficials() {
        return officials;
    }

    public void setOfficials(List<String> officials) {
        this.officials = officials;
    }

    public String getStageName() {
        return stageName;
    }

    public void setStageName(String stageName) {
        this.stageName = stageName;
    }

    public String getHomeTeamCountry() {
        return homeTeamCountry;
    }

    public void setHomeTeamCountry(String homeTeamCountry) {
        this.homeTeamCountry = homeTeamCountry;
    }

    public String getAwayTeamCountry() {
        return awayTeamCountry;
    }

    public void setAwayTeamCountry(String awayTeamCountry) {
        this.awayTeamCountry = awayTeamCountry;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public Object getWinner() {
        return winner;
    }

    public void setWinner(Object winner) {
        this.winner = winner;
    }

    public Object getWinnerCode() {
        return winnerCode;
    }

    public void setWinnerCode(Object winnerCode) {
        this.winnerCode = winnerCode;
    }

    public TeamsInMatch getHomeTeam() {
        return homeTeam;
    }

    public void setHomeTeam(TeamsInMatch homeTeam) {
        this.homeTeam = homeTeam;
    }

    public TeamsInMatch getAwayTeam() {
        return awayTeam;
    }

    public void setAwayTeam(TeamsInMatch awayTeam) {
        this.awayTeam = awayTeam;
    }

    public List<TeamEvents> getHomeTeamEvents() {
        return homeTeamEvents;
    }

    public void setHomeTeamEvents(List<TeamEvents> homeTeamEvents) {
        this.homeTeamEvents = homeTeamEvents;
    }

    public List<TeamEvents> getAwayTeamEvents() {
        return awayTeamEvents;
    }

    public void setAwayTeamEvents(List<TeamEvents> awayTeamEvents) {
        this.awayTeamEvents = awayTeamEvents;
    }

    public Object getLastEventUpdateAt() {
        return lastEventUpdateAt;
    }

    public void setLastEventUpdateAt(Object lastEventUpdateAt) {
        this.lastEventUpdateAt = lastEventUpdateAt;
    }

    public Object getLastScoreUpdateAt() {
        return lastScoreUpdateAt;
    }

    public void setLastScoreUpdateAt(Object lastScoreUpdateAt) {
        this.lastScoreUpdateAt = lastScoreUpdateAt;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(venue);
        parcel.writeString(location);
        parcel.writeString(status);
        parcel.writeString(fifaId);
        parcel.writeString(attendance);
        parcel.writeStringList(officials);
        parcel.writeString(stageName);
        parcel.writeString(homeTeamCountry);
        parcel.writeString(awayTeamCountry);
        parcel.writeString(datetime);
    }
}
