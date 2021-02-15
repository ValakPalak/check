package com.example.plutoacademy;

public class ExpertsModel {
    private String mExpertImage;
    private String mExpertName;
    private String mExpertType;



    public String getmExpertImage() {
        return mExpertImage;
    }

    public String getmExpertName() {
        return mExpertName;
    }

    public String getmExpertType() {
        return mExpertType;
    }

    public ExpertsModel(String mExpertImage, String mExpertName, String mExpertType) {
        this.mExpertImage = mExpertImage;
        this.mExpertName = mExpertName;
        this.mExpertType = mExpertType;
    }
}
