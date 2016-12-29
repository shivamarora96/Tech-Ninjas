package com.teamninjas.tech_ninjas;

import com.google.gson.annotations.SerializedName;

/**
 * Created by ShivamArora on 29-12-2016.
 */

public class Response {

    @SerializedName("Engine")
    String mEngine ;

    @SerializedName("Price")
    String mPrice ;

    @SerializedName("Colors")
    String mColors ;

    @SerializedName("image_url")
    String mImage_Url ;

    @SerializedName("Varients")
    String mVarients ;

    @SerializedName("Model")
    String mModel ;


    public String getmEngine() {
        return mEngine;
    }

    public void setmEngine(String mEngine) {
        this.mEngine = mEngine;
    }

    public String getmPrice() {
        return mPrice;
    }

    public void setmPrice(String mPrice) {
        this.mPrice = mPrice;
    }

    public String getmColors() {
        return mColors;
    }

    public void setmColors(String mColors) {
        this.mColors = mColors;
    }

    public String getmImage_Url() {
        return mImage_Url;
    }

    public void setmImage_Url(String mImage_Url) {
        this.mImage_Url = mImage_Url;
    }

    public String getmVarients() {
        return mVarients;
    }

    public void setmVarients(String mVarients) {
        this.mVarients = mVarients;
    }

    public String getmModel() {
        return mModel;
    }

    public void setmModel(String mModel) {
        this.mModel = mModel;
    }
}
