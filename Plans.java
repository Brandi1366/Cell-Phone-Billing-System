package chapter6;

import java.io.Serializable;

public class Plans implements Serializable {

    String planId;
    int allowedMinutes;
    double planPrice;

    public String getPlanId() {
        return planId;
    }

    public void setPlanId(String planId) {
        this.planId = planId;
    }

    public int getAllowedMinutes() {
        return allowedMinutes;
    }

    public void setAllowedMinutes(int allowedMinutes) {
        this.allowedMinutes = allowedMinutes;
    }

    public double getPlanPrice() {
        return planPrice;
    }

    public void setPrice(double planPrice) {
        this.planPrice = planPrice;
    }
    public Plans(String planId, int allowedMinutes, double planPrice) {
        this.planId = planId;
        this.allowedMinutes = allowedMinutes;
        this.planPrice = planPrice;
    }

}
