package com.example.pingghost.stadiums.model;

import com.google.gson.JsonObject;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by pingghost on 08/12/18.
 */

public class Stadium {
    @SerializedName("_id")
    @Expose
    private String _id;
    @SerializedName("capacity")
    @Expose
    private int capacity;
    @SerializedName("open_time")
    @Expose
    private String open_time;
    @SerializedName("close_time")
    @Expose
    private String close_time;
    @SerializedName("location")
    @Expose
    private JsonObject location;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("_id_owner")
    @Expose
    private String _id_owner;

    public Stadium() {
    }

    public Stadium(String _id, int capacity, String open_time, String close_time, JsonObject location, String name, String _id_owner) {
        this._id = _id;
        this.capacity = capacity;
        this.open_time = open_time;
        this.close_time = close_time;
        this.location = location;
        this.name = name;
        this._id_owner = _id_owner;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getOpen_time() {
        return open_time;
    }

    public void setOpen_time(String open_time) {
        this.open_time = open_time;
    }

    public String getClose_time() {
        return close_time;
    }

    public void setClose_time(String close_time) {
        this.close_time = close_time;
    }

    public JsonObject getLocation() {
        return location;
    }

    public void setLocation(JsonObject location) {
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String get_id_owner() {
        return _id_owner;
    }

    public void set_id_owner(String _id_owner) {
        this._id_owner = _id_owner;
    }
}
