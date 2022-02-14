package chapter6;
/*
Calculate an itemized cell phone bill
 */

import java.io.Serializable;

public class PhoneBill implements Serializable {
    //fields

    int id;
    double planPrice;
    int minutesUsed;
    int minutesAllowed;
    double taxes;
    double totalDue;
    double overageCost;
    String planId;
    int planCycle;


    public int getPlanCycle() {
        return planCycle;
    }

    public void setPlanCycle(int planCycle) {
        this.planCycle = planCycle;
    }


    public String getPlanId() {
        return planId;
    }

    public void setPlan(String planId) {
        this.planId = planId;
    }


    public int getId() {

        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getPlanPrice() {
        return planPrice;
    }

    public void setPlanPrice(double planPrice) {
        this.planPrice = planPrice;
    }

    public int getMinutesUsed() {
        return minutesUsed;
    }

    public void setMinutesUsed(int minutesUsed) {
        this.minutesUsed = minutesUsed;
    }

    public int getMinutesAllowed() {
        return minutesAllowed;
    }

    public void setMinutesAllowed(int minutesAllowed) {
        this.minutesAllowed = minutesAllowed;
    }

    public void setTaxes(double taxes) {
        this.taxes = taxes;
    }

    public double getTotalDue() {
        return totalDue;
    }

    public void setTotalDue(double totalDue) {
        this.totalDue = totalDue;
    }

    public double getOverageCost() {
        return overageCost;
    }

    public void setOverageCost(double overageCost) {
        this.overageCost = overageCost;
    }


    // constructor

    public PhoneBill(int id, String planId, double planPrice, int minutesUsed, int minutesAllowed){
        this.id = id;
        this.planPrice = planPrice;
        this.planId = planId;
        this.minutesUsed = minutesUsed;
        this.minutesAllowed = minutesAllowed;
    }

    /*
    calculate excess minutes cost, taxes, and calculate itemized bill
     */

        public void calculateOverage(){
        double overage = minutesUsed - minutesAllowed;
        if (overage<0){
            overage = 0;
        }
        double overageCostPerMinute = .25;
        overageCost = overage * overageCostPerMinute;
    }

    public void getTaxes(){
        double taxPercentage = .15;
        taxes = (planPrice + overageCost) * taxPercentage;
    }

    public void GetTotalBill(){
        calculateOverage();
        getTaxes();
        totalDue = planPrice + overageCost + taxes;
    }

    /*
    print the itemized bill
     */

    public void printBill(){
        GetTotalBill();
        System.out.println("Phone Bill Statement");
        System.out.println("Account id: " + id);
        System.out.println("Plan Type: " + planId);
        System.out.println("Plan price: $" + String.format("%.2f",planPrice));
        System.out.println("Overage: $" + String.format("%.2f",overageCost));
        System.out.println("Tax: $" + String.format("%.2f",taxes));
        System.out.println("Total: $" + String.format("%.2f",totalDue));
    }

}
