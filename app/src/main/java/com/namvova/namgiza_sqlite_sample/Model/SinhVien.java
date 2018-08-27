package com.namvova.namgiza_sqlite_sample.Model;

public class SinhVien {
    private int mID;
    private String mName;
    private String mClass;

    public SinhVien() {
    }

    public SinhVien(int mID, String mName, String mClass) {
        this.mID = mID;
        this.mName = mName;
        this.mClass = mClass;
    }

    public SinhVien(String mName, String mClass) {
        this.mName = mName;
        this.mClass = mClass;
    }

    public int getmID() {
        return mID;
    }

    public void setmID(int mID) {
        this.mID = mID;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmClass() {
        return mClass;
    }

    public void setmClass(String mClass) {
        this.mClass = mClass;
    }
}
