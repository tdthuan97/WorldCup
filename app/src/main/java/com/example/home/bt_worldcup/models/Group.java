package com.example.home.bt_worldcup.models;


import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Home on 6/25/2018.
 */

public class Group implements Parcelable{
    int id;
    String letter;
    ArrayList<TeamGroup> teamGroupArrayList;

    public Group(int id, String letter, ArrayList<TeamGroup> teamGroupArrayList) {
        this.id = id;
        this.letter = letter;
        this.teamGroupArrayList = teamGroupArrayList;
    }

    protected Group(Parcel in) {
        id = in.readInt();
        letter = in.readString();
    }

    public static final Creator<Group> CREATOR = new Creator<Group>() {
        @Override
        public Group createFromParcel(Parcel in) {
            return new Group(in);
        }

        @Override
        public Group[] newArray(int size) {
            return new Group[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLetter() {
        return letter;
    }

    public void setLetter(String letter) {
        this.letter = letter;
    }

    public ArrayList<TeamGroup> getTeamGroupArrayList() {
        return teamGroupArrayList;
    }

    public void setTeamGroupArrayList(ArrayList<TeamGroup> teamGroupArrayList) {
        this.teamGroupArrayList = teamGroupArrayList;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(letter);
    }
}
