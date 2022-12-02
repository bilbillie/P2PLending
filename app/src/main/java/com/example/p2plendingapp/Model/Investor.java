package com.example.p2plendingapp.Model;

public class Investor extends Customer {

    Customer aCustomer = new Customer();
    private Integer inId;
    private String sOFunds;
    private String pRLevel;

    public Investor() {

    }

    public Investor(String sOFunds, String pRLevel) {
        this.inId = aCustomer.getcId();
        this.sOFunds = sOFunds;
        this.pRLevel = pRLevel;
    }


    public String getsOFunds() {
        return sOFunds;
    }

    public void setsOFunds(String sOFunds) {
        this.sOFunds = sOFunds;
    }

    public String getpRLevel() {
        return pRLevel;
    }

    public void setpRLevel(String pRLevel) {
        this.pRLevel = pRLevel;
    }
}
