package com.example.pingghost.stadiums.viewItem;

/**
 * Created by pingghost on 07/11/18.
 */

public class Menu_item {
    private String itemName;
    private int type;
    private int back_img;
    private int icon;
    private String descrip;
    private String direction;


    public Menu_item(String itemName, int type, int back_img,int icon,String descrip,String direction) {
        this.itemName = itemName;
        this.type = type;
        this.back_img = back_img;
        this.icon = icon;
        this.descrip = descrip;
        this.direction = direction;
    }

    public String getItemName() {
        return itemName;
    }

    public String getDescrip() {
        return descrip;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public void setDescrip(String descrip) {
        this.descrip = descrip;
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
