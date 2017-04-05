/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IDataHolders;

/**
 *
 * @author kimani kogi
 */
public class ILoanDetailsDataHolder {
   String  rateper;
    int    rateTime;
     String applicableStatus;
      double     rate;
       double    appfee;
       double    penalty;

    public ILoanDetailsDataHolder(String rateper, int rateTime, String applicableStatus, double rate, double appfee, double penalty) {
        this.rateper = rateper;
        this.rateTime = rateTime;
        this.applicableStatus = applicableStatus;
        this.rate = rate;
        this.appfee = appfee;
        this.penalty = penalty;
    }

    public String getRateper() {
        return rateper;
    }

    public void setRateper(String rateper) {
        this.rateper = rateper;
    }

    public int getRateTime() {
        return rateTime;
    }

    public void setRateTime(int rateTime) {
        this.rateTime = rateTime;
    }

    public String getApplicableStatus() {
        return applicableStatus;
    }

    public void setApplicableStatus(String applicableStatus) {
        this.applicableStatus = applicableStatus;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public double getAppfee() {
        return appfee;
    }

    public void setAppfee(double appfee) {
        this.appfee = appfee;
    }

    public double getPenalty() {
        return penalty;
    }

    public void setPenalty(double penalty) {
        this.penalty = penalty;
    }
            
}
