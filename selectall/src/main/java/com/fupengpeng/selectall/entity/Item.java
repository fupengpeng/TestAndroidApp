package com.fupengpeng.selectall.entity;

/**
 * Created by fupengpeng on 2017/6/21 0021.
 */

public class Item {
    private String name;
    private boolean b = false;

    public Item(){}

    public Item(String name, boolean b) {
        this.name = name;
        this.b = b;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isB() {
        return b;
    }

    public void setB(boolean b) {
        this.b = b;
    }
}
