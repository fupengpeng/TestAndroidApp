package com.fupengpeng.shopcartselect.shopping01.entity;

import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by fupengpeng on 2017/6/29 0029.
 */

public class ShoppingCartFragmentEvent {

    private boolean batchModel;
    private Button btnFragmentShoppingCartSettlement;
    private LinearLayout llFragmentShoppingCartPriceTotal;
    private double totalPrice;
    private TextView tvFragmentShoppingCartTotal;

    public ShoppingCartFragmentEvent(boolean batchModel,
                                     Button btnFragmentShoppingCartSettlement,
                                     LinearLayout llFragmentShoppingCartPriceTotal,
                                     double totalPrice,
                                     TextView tvFragmentShoppingCartTotal) {
        this.batchModel = batchModel;
        this.btnFragmentShoppingCartSettlement = btnFragmentShoppingCartSettlement;
        this.llFragmentShoppingCartPriceTotal = llFragmentShoppingCartPriceTotal;
        this.totalPrice = totalPrice;
        this.tvFragmentShoppingCartTotal = tvFragmentShoppingCartTotal;
    }

    public ShoppingCartFragmentEvent(){}

    public boolean isBatchModel() {
        return batchModel;
    }

    public void setBatchModel(boolean batchModel) {
        this.batchModel = batchModel;
    }

    public Button getBtnFragmentShoppingCartSettlement() {
        return btnFragmentShoppingCartSettlement;
    }

    public void setBtnFragmentShoppingCartSettlement(Button btnFragmentShoppingCartSettlement) {
        this.btnFragmentShoppingCartSettlement = btnFragmentShoppingCartSettlement;
    }

    public LinearLayout getLlFragmentShoppingCartPriceTotal() {
        return llFragmentShoppingCartPriceTotal;
    }

    public void setLlFragmentShoppingCartPriceTotal(LinearLayout llFragmentShoppingCartPriceTotal) {
        this.llFragmentShoppingCartPriceTotal = llFragmentShoppingCartPriceTotal;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public TextView getTvFragmentShoppingCartTotal() {
        return tvFragmentShoppingCartTotal;
    }

    public void setTvFragmentShoppingCartTotal(TextView tvFragmentShoppingCartTotal) {
        this.tvFragmentShoppingCartTotal = tvFragmentShoppingCartTotal;
    }
}
