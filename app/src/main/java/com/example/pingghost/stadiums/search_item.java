package com.example.pingghost.stadiums;

import java.util.Date;

/**
 * Created by pingghost on 08/12/18.
 */

public class search_item {
    private String itemName;
    private Boolean open;
    private String open_time;
    private double score;
    private String location;

    public search_item(String itemName, Boolean open, String open_time, double score, String location) {
        this.itemName = itemName;
        this.open = open;
        this.open_time = open_time;
        this.score = score;
        this.location = location;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Boolean getOpen() {
        return open;
    }

    public void setOpen(Boolean open) {
        this.open = open;
    }

    public String getOpen_time() {
        return open_time;
    }

    public void setOpen_time(String open_time) {
        this.open_time = open_time;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
