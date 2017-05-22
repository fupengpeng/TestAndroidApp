package com.fupengpeng.testandroidapp.mvp.bean;

/**
 * Created by fp on 2017/5/21.
 */

public class Girl {
    private int photo;
    private String evaluation;
    private String introduce;

    public Girl(int photo, String evaluation, String introduce) {
        this.photo = photo;
        this.evaluation = evaluation;
        this.introduce = introduce;
    }

    public int getPhoto() {
        return photo;
    }

    public void setPhoto(int photo) {
        this.photo = photo;
    }

    public String getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(String evaluation) {
        this.evaluation = evaluation;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }
}
