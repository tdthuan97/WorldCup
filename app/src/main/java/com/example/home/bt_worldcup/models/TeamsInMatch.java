package com.example.home.bt_worldcup.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by Home on 6/26/2018.
 */

public class TeamsInMatch implements Serializable,Parcelable{
    private String country;
    private String code;
    private Integer goals;
    private Object penalties;
    //private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public TeamsInMatch(String country, String code, Integer goals, Object penalties) {
        this.country = country;
        this.code = code;
        this.goals = goals;
        this.penalties = penalties;
    }

    public TeamsInMatch() {
    }


    protected TeamsInMatch(Parcel in) {
        country = in.readString();
        code = in.readString();
        if (in.readByte() == 0) {
            goals = null;
        } else {
            goals = in.readInt();
        }

    }

    public static final Creator<TeamsInMatch> CREATOR = new Creator<TeamsInMatch>() {
        @Override
        public TeamsInMatch createFromParcel(Parcel in) {
            return new TeamsInMatch(in);
        }

        @Override
        public TeamsInMatch[] newArray(int size) {
            return new TeamsInMatch[size];
        }
    };

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getGoals() {
        return goals;
    }

    public void setGoals(Integer goals) {
        this.goals = goals;
    }

    public Object getPenalties() {
        return penalties;
    }

    public void setPenalties(Object penalties) {
        this.penalties = penalties;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(country);
        parcel.writeString(code);
        if (goals == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(goals);
        }
    }


    /*public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }*/
}
