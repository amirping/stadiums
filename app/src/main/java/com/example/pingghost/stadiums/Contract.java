package com.example.pingghost.stadiums;

import java.util.Date;

/**
 * Created by pingghost on 17/11/18.
 */

public class Contract {
    private String stadium;
    private String stat;
    private Date date;

    public Contract(String stadium, String stat, Date date) {
        this.stadium = stadium;
        this.stat = stat;
        this.date = date;
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
}
