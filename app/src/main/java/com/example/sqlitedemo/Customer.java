package com.example.sqlitedemo;

public class Customer {

    private int iID;
    private int iAge;

    private boolean bIsActive;


    private String sName;

    public int getiID() {
        return iID;
    }

    public void setiID(int iID) {
        this.iID = iID;
    }

    public String getsName() {
        return sName;
    }

    public void setsName(String sName) {
        this.sName = sName;
    }

    public int getiAge() {
        return iAge;
    }

    public void setiAge(int iAge) {
        this.iAge = iAge;
    }

    public boolean isbIsActive() {
        return bIsActive;
    }

    public void setbIsActive(boolean bIsActive) {
        this.bIsActive = bIsActive;
    }



    @Override
    public String toString() {
        return "Customer{" +
                "iID=" + iID +
                ", sName='" + sName + '\'' +
                ", iAge=" + iAge +
                ", bIsActive=" + bIsActive +
                '}';
    }


    public Customer(int iID, String sName, int iAge, boolean bIsActive) {
        this.iID = iID;
        this.sName = sName;
        this.iAge = iAge;
        this.bIsActive = bIsActive;
    }

    public Customer(){}


}
