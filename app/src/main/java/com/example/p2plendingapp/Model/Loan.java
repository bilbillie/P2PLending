package com.example.p2plendingapp.Model;

public class Loan extends Customer {

    Customer aCustomer = new Customer();

    private Integer lId;
    private String pOBorrowing;
    private double oFee;
    private double iRate;
    private double uPFee;
    private double lPFee;
    private double mPAmount;
    private Boolean aTerms;
    private double lAmount;
    private Integer pPeriod;
    private String sDOAgreement;
    private Integer cId;

    public Loan() {

    }

    public Loan(Integer lId, String pOBorrowing, double oFee, double uPFee, double lPFee, double mPAmount, Boolean aTerms, double lAmount, Integer pPeriod, String sDOAgreement) {
        this.lId = lId;
        this.pOBorrowing = pOBorrowing;
        this.oFee = oFee;
        this.uPFee = uPFee;
        this.lPFee = lPFee;
        this.mPAmount = mPAmount;
        this.aTerms = aTerms;
        this.lAmount = lAmount;
        this.pPeriod = pPeriod;
        this.sDOAgreement = sDOAgreement;
        this.cId = aCustomer.getcId();
    }

    public Integer getcId() {
        return cId;
    }

    public Integer getlId() {
        return lId;
    }

    public void setlId(Integer lId) {
        this.lId = lId;
    }

    public String getpOBorrowing() {
        return pOBorrowing;
    }

    public void setpOBorrowing(String pOBorrowing) {
        this.pOBorrowing = pOBorrowing;
    }

    public double getoFee() {
        return oFee;
    }

    public void setoFee(double oFee) {
        if (this.lAmount >= 1000 && this.lAmount < 5000) {
            this.oFee = 0.02;
        } else if (this.lAmount >= 5000 && this.lAmount < 15000) {
            this.oFee = 0.03;
        } else {
            this.oFee = 0.04;
        }

    }

    public double getiRate() {
        return iRate;
    }

    public void setiRate(double iRate) {
        this.iRate = iRate;
    }

    public double getuPFee() {
        return uPFee;
    }

    public void setuPFee(double uPFee) {
        this.uPFee = uPFee;
    }

    public double getlPFee() {
        return lPFee;
    }

    public void setlPFee(double lPFee) {
        this.lPFee = lPFee;
    }

    public double getmPAmount() {
        return mPAmount;
    }

    public void setmPAmount(double mPAmount) {
        this.mPAmount = mPAmount;
    }

    public Boolean getaTerms() {
        return aTerms;
    }

    public void setaTerms(Boolean aTerms) {
        this.aTerms = aTerms;
    }

    public double getlAmount() {
        return lAmount;
    }

    public void setlAmount(double lAmount) {
        this.lAmount = lAmount + this.oFee * this.lAmount;
    }

    public String getsDOAgreement() {
        return sDOAgreement;
    }

    public void setsDOAgreement(String sDOAgreement) {
        this.sDOAgreement = sDOAgreement;
    }

    public Integer getpPeriod() {
        return pPeriod;
    }

    public void setpPeriod(Integer pPeriod) {
        this.pPeriod = pPeriod;
    }
}
