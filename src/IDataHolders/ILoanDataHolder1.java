/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IDataHolders;

import imua.development.*;
import imua.development.*;
import java.sql.ResultSet;
import java.util.Date;
import javax.swing.table.TableModel;

/**
 *
 * @author kimani kogi
 * 
 * 
 */

/**  
  String autoid;
  
  String customerid;
  
  String loanid;
  
  String loantype;
  
  String applicablestatus;
  
  String applicationfee;
  
  
  String periodtype;
  
  String frequencyperperiod;
  
  
  Date datesupposed;
  
  
  
  
  
  String installmentamount;
  String installmentsno;
  String loanAmount;
  String loanRequested;
  String loanbalance;
  
  
  String moreorlesspaid;
  String todaypay;
  String paymentstatus;
  
  String defaultstatus;
  
  
  
  
  String balancebf;
  
  
  String extra;
  
  
  String loangivenOn;
  
  String paidon;
  
  


**/



public class ILoanDataHolder1 {
    
     String iloanautoid;
     
   String autoid;
  
  String customerid;
  
  String loanid;
  
  String loantype;
  
  String applicablestatus;
  
  String applicationfee;
  
  
  String periodtype;
  
  String frequencyperperiod;
  
  
  Date datesupposed;
  
  
  
  
  
  String installmentamount;
  String installmentsno;
  String loanAmount;
  String loanRequested;
  String loanbalance;
  
  
  String moreorlesspaid;
  String todaypay;
  String paymentstatus;
  
  String defaultstatus;
  
  
  
  
  String balancebf;
  
  
  String extra;
  
  
  String loangivenOn;
  
  String paidon;
static TableModel resultSetToTableModel(ResultSet result)
  {
    throw new UnsupportedOperationException("Not supported yet.");
  }

    public ILoanDataHolder1() {
    }

    public ILoanDataHolder1(String iloanautoid,String autoid, String customerid, String loanid, String loantype, String applicablestatus, String applicationfee, String periodtype, String frequencyperperiod, Date datesupposed, String installmentamount, String installmentsno, String loanAmount, String loanRequested, String loanbalance, String moreorlesspaid, String todaypay, String paymentstatus, String defaultstatus, String balancebf, String extra, String loangivenOn, String paidon) {
       this.iloanautoid=iloanautoid;
        this.autoid = autoid;
        this.customerid = customerid;
        this.loanid = loanid;
        this.loantype = loantype;
        this.applicablestatus = applicablestatus;
        this.applicationfee = applicationfee;
        this.periodtype = periodtype;
        this.frequencyperperiod = frequencyperperiod;
        this.datesupposed = datesupposed;
        this.installmentamount = installmentamount;
        this.installmentsno = installmentsno;
        this.loanAmount = loanAmount;
        this.loanRequested = loanRequested;
        this.loanbalance = loanbalance;
        this.moreorlesspaid = moreorlesspaid;
        this.todaypay = todaypay;
        this.paymentstatus = paymentstatus;
        this.defaultstatus = defaultstatus;
        this.balancebf = balancebf;
        this.extra = extra;
        this.loangivenOn = loangivenOn;
        this.paidon = paidon;
    }

    public String getIloanautoid() {
        return iloanautoid;
    }

    public void setIloanautoid(String iloanautoid) {
        this.iloanautoid = iloanautoid;
    }

    public String getAutoid() {
        return autoid;
    }

    public void setAutoid(String autoid) {
        this.autoid = autoid;
    }

    public String getCustomerid() {
        return customerid;
    }

    public void setCustomerid(String customerid) {
        this.customerid = customerid;
    }

    public String getLoanid() {
        return loanid;
    }

    public void setLoanid(String loanid) {
        this.loanid = loanid;
    }

    public String getLoantype() {
        return loantype;
    }

    public void setLoantype(String loantype) {
        this.loantype = loantype;
    }

    public String getApplicablestatus() {
        return applicablestatus;
    }

    public void setApplicablestatus(String applicablestatus) {
        this.applicablestatus = applicablestatus;
    }

    public String getApplicationfee() {
        return applicationfee;
    }

    public void setApplicationfee(String applicationfee) {
        this.applicationfee = applicationfee;
    }

    public String getPeriodtype() {
        return periodtype;
    }

    public void setPeriodtype(String periodtype) {
        this.periodtype = periodtype;
    }

    public String getFrequencyperperiod() {
        return frequencyperperiod;
    }

    public void setFrequencyperperiod(String frequencyperperiod) {
        this.frequencyperperiod = frequencyperperiod;
    }

    public Date getDatesupposed() {
        return datesupposed;
    }

    public void setDatesupposed(Date datesupposed) {
        this.datesupposed = datesupposed;
    }

    public String getInstallmentamount() {
        return installmentamount;
    }

    public void setInstallmentamount(String installmentamount) {
        this.installmentamount = installmentamount;
    }

    public String getInstallmentsno() {
        return installmentsno;
    }

    public void setInstallmentsno(String installmentsno) {
        this.installmentsno = installmentsno;
    }

    public String getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(String loanAmount) {
        this.loanAmount = loanAmount;
    }

    public String getLoanRequested() {
        return loanRequested;
    }

    public void setLoanRequested(String loanRequested) {
        this.loanRequested = loanRequested;
    }

    public String getLoanbalance() {
        return loanbalance;
    }

    public void setLoanbalance(String loanbalance) {
        this.loanbalance = loanbalance;
    }

    public String getMoreorlesspaid() {
        return moreorlesspaid;
    }

    public void setMoreorlesspaid(String moreorlesspaid) {
        this.moreorlesspaid = moreorlesspaid;
    }

    public String getTodaypay() {
        return todaypay;
    }

    public void setTodaypay(String todaypay) {
        this.todaypay = todaypay;
    }

    public String getPaymentstatus() {
        return paymentstatus;
    }

    public void setPaymentstatus(String paymentstatus) {
        this.paymentstatus = paymentstatus;
    }

    public String getDefaultstatus() {
        return defaultstatus;
    }

    public void setDefaultstatus(String defaultstatus) {
        this.defaultstatus = defaultstatus;
    }

    public String getBalancebf() {
        return balancebf;
    }

    public void setBalancebf(String balancebf) {
        this.balancebf = balancebf;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }

    public String getLoangivenOn() {
        return loangivenOn;
    }

    public void setLoangivenOn(String loangivenOn) {
        this.loangivenOn = loangivenOn;
    }

    public String getPaidon() {
        return paidon;
    }

    public void setPaidon(String paidon) {
        this.paidon = paidon;
    }
  
 
}
