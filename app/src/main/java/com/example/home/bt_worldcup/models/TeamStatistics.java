package com.example.home.bt_worldcup.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by Home on 6/27/2018.
 */

public class TeamStatistics implements Serializable,Parcelable{
    private Integer ballPossession;
    private Integer yellowCards;
    private Integer redCards;
    private Object foulsCommitted;

    public TeamStatistics() {
    }

    public TeamStatistics(Integer ballPossession, Integer yellowCards, Integer redCards, Object foulsCommitted) {

        this.ballPossession = ballPossession;
        this.yellowCards = yellowCards;
        this.redCards = redCards;
        this.foulsCommitted = foulsCommitted;
    }

    protected TeamStatistics(Parcel in) {
        if (in.readByte() == 0) {
            ballPossession = null;
        } else {
            ballPossession = in.readInt();
        }
        if (in.readByte() == 0) {
            yellowCards = null;
        } else {
            yellowCards = in.readInt();
        }
        if (in.readByte() == 0) {
            redCards = null;
        } else {
            redCards = in.readInt();
        }
    }

    public static final Creator<TeamStatistics> CREATOR = new Creator<TeamStatistics>() {
        @Override
        public TeamStatistics createFromParcel(Parcel in) {
            return new TeamStatistics(in);
        }

        @Override
        public TeamStatistics[] newArray(int size) {
            return new TeamStatistics[size];
        }
    };

    public Integer getBallPossession() {
        return ballPossession;
    }

    public void setBallPossession(Integer ballPossession) {
        this.ballPossession = ballPossession;
    }

    public Integer getYellowCards() {
        return yellowCards;
    }

    public void setYellowCards(Integer yellowCards) {
        this.yellowCards = yellowCards;
    }

    public Integer getRedCards() {
        return redCards;
    }

    public void setRedCards(Integer redCards) {
        this.redCards = redCards;
    }

    public Object getFoulsCommitted() {
        return foulsCommitted;
    }

    public void setFoulsCommitted(Object foulsCommitted) {
        this.foulsCommitted = foulsCommitted;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        if (ballPossession == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(ballPossession);
        }
        if (yellowCards == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(yellowCards);
        }
        if (redCards == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(redCards);
        }
    }
}
