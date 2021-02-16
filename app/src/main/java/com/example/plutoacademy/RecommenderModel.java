package com.example.plutoacademy;

public class RecommenderModel {

    private String mRecName;
    private int mRecDes;
    private String mSource;

    public String getmRecName() {
        return mRecName;
    }

    public int getmRecDes() {
        return mRecDes;
    }

    public String getmSource() {
        return mSource;
    }

    public RecommenderModel(String mRecName, int mRecDes, String mSource) {
        this.mRecName = mRecName;
        this.mRecDes = mRecDes;
        this.mSource = mSource;
    }
}
