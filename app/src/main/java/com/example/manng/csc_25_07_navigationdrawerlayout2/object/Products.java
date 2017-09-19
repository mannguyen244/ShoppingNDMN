package com.example.manng.csc_25_07_navigationdrawerlayout2.object;

import java.io.Serializable;

/**
 * Created by DINH TDT on 12/08/2017.
 */

public class Products implements Serializable {
    private String name;
    private String descript;
    private int image;
    private double price;
    private double total;
    private boolean choose = true;

    public Products(int image, String name, double price, double total, boolean choose) {
        this.image = image;
        this.name = name;
        this.price = price;
        this.total = total;
        this.choose = choose;
    }

    public Products(int image, String name, double price) {
        this.name = name;
        this.image = image;
        this.price = price;
        this.total = price;
        this.choose = true;
    }

    public Products() {
    }

    public Products(String name, String descript, int image) {
        this.name = name;
        this.descript = descript;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescript() {
        return descript;
    }

    public void setDescript(String descript) {
        this.descript = descript;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public boolean isChoose() {
        return choose;
    }

    public void setChoose(boolean choose) {
        this.choose = choose;
    }
}
