package com.fupengpeng.selectall.entity;

import java.io.Serializable;

/**
 * Created by fupengpeng on 2017/6/21 0021.
 */

public class CommodityObject implements Serializable {

    private boolean designate;
    private int commodityPic;
    private String name;
    private float price;
    private int number;

    public CommodityObject(){}

    public CommodityObject(boolean designate,
                           int commodityPic,
                           String name,
                           float price,
                           int number) {
        this.designate = designate;
        this.commodityPic = commodityPic;
        this.name = name;
        this.price = price;
        this.number = number;
    }

    public boolean isDesignate() {
        return designate;
    }

    public void setDesignate(boolean designate) {
        this.designate = designate;
    }

    public int getCommodityPic() {
        return commodityPic;
    }

    public void setCommodityPic(int commodityPic) {
        this.commodityPic = commodityPic;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
