package com.example.pingghost.stadiums.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * Created by pingghost on 17/11/18.
 */

public class Contract {
    @SerializedName("_id")
    @Expose
    private String _id;
    @SerializedName("stade")
    @Expose
    private String stadium;
    @SerializedName("stat")
    @Expose
    private String stat;
    @SerializedName("date")
    @Expose
    private Date date;
    @SerializedName("match_date")
    @Expose
    private Date match_date;
    @SerializedName("user")
    @Expose
    private String user_id;

    public Contract() {
    }

    public Contract(String _id, String stadium, String stat, Date date, Date match_date, String user_id) {
        this._id = _id;
        this.stadium = stadium;
        this.stat = stat;
        this.date = date;
        this.match_date = match_date;
        this.user_id = user_id;
    }

    public String getStadium() {
        return stadium;
    }

    public void setStadium(String stadium) {
        this.stadium = stadium;
    }

    public String getStat() {
        return stat;
    }

    public void setStat(String stat) {
        this.stat = stat;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    public String DateText(){
        String dt=  "";
        return date.toString();
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public Date getMatch_date() {
        return match_date;
    }

    public void setMatch_date(Date match_date) {
        this.match_date = match_date;
    }
}
