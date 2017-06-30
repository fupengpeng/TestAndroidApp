package com.fupengpeng.shopcartselect.shopping01.entity;

/**
 * Created by fupengpeng on 2017/6/30 0030.
 */

public class ShoppingCartData {
    private boolean isBatchModel;

    public ShoppingCartData (){}

    public ShoppingCartData(boolean isBatchModel) {
        this.isBatchModel = isBatchModel;
    }

    public boolean isBatchModel() {
        return isBatchModel;
    }

    public void setBatchModel(boolean batchModel) {
        isBatchModel = batchModel;
    }
}
