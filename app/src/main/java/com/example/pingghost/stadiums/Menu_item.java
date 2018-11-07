package com.example.pingghost.stadiums;

/**
 * Created by pingghost on 07/11/18.
 */

public class Menu_item {
    private String itemName;
    private int type;
    private int back_img;
    private int icon;


    public Menu_item(String itemName, int type, int back_img,int icon) {
        this.itemName = itemName;
        this.type = type;
        this.back_img = back_img;
        this.icon = icon;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getBack_img() {
        return back_img;
    }

    public void setBack_img(int back_img) {
        this.back_img = back_img;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }
}
