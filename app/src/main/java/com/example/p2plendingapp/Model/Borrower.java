package com.example.p2plendingapp.Model;

public class Borrower extends Customer {

    Customer aCustomer = new Customer();

    private Integer bId;
    private String pStatus;
    private double hmExp;
    private String rStatus;
    private double mNetIncome;
    private double mSNetIncome;
    private String mStatus;

    public Borrower() {

    }

    public Borrower(String pStatus, double hmExp, String rStatus, double mNetIncome, double mSNetIncome, String mStatus) {
        this.bId = aCustomer.getcId();
        this.pStatus = pStatus;
        this.hmExp = hmExp;
        this.rStatus = rStatus;
        this.mNetIncome = mNetIncome;
        this.mSNetIncome = mSNetIncome;
        this.mStatus = mStatus;
    }

    public Integer getbId() {
        return bId;
    }

    public String getpStatus() {
        return pStatus;
    }

    public void setpStatus(String pStatus) {
        this.pStatus = pStatus;
    }

    public double getHmExp() {
        return hmExp;
    }

    public void setHmExp(double hmExp) {
        this.hmExp = hmExp;
    }

    public String getrStatus() {
        return rStatus;
    }

    public void setrStatus(String rStatus) {
        this.rStatus = rStatus;
    }

    public double getmNetIncome() {
        return mNetIncome;
    }

    public void setmNetIncome(double mNetIncome) {
        this.mNetIncome = mNetIncome;
    }

    public double getmSNetIncome() {
        return mSNetIncome;
    }

    public void setmSNetIncome(double mSNetIncome) {
        this.mSNetIncome = mSNetIncome;
    }

    public String getmStatus() {
        return mStatus;
    }

    public void setmStatus(String mStatus) {
        this.mStatus = mStatus;
    }
}
