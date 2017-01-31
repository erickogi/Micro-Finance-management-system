/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imua.development;

import imua.development.*;
import java.sql.ResultSet;
import java.util.Date;
import javax.swing.table.TableModel;

/**
 *
 * @author kimani kogi
 */
public class LoanDataHolder {
    String autoid;
    String id;
    String loanid;
    String loantype;
String applicable;
String installmentamount;
String installmentsno;
String periodtype;
String frequencyperperiod;
Date targetdate;
String loanAmount;
String loanRequested;
String loanbalance;
String defaultacc;
String applicationfee;
String todaypay;
String givenOn;
String paidon;
static TableModel resultSetToTableModel(ResultSet result)
  {
    throw new UnsupportedOperationException("Not supported yet.");
  }
  
  public LoanDataHolder(String AID,String ID,String LoanID,String LoanType, 
          String Applicable,String InstallmentAmount,String InstallmentNo,
          String PeriodType,String FrequencyPerPeriod,
          Date Target, String LoanAmount, String LoanRequested,
         String LoanBalance,String Penalty,String ApplicationFee,String TodayPay,
         String GivenOn,String PaidOn)
  {
       this.autoid = AID;
      this.id = ID;
     this.loanid=LoanID;
     this.loantype=LoanType;
     this.applicable=Applicable;
     this.installmentamount=InstallmentAmount;
     this.installmentsno=InstallmentNo;
     this.periodtype=PeriodType;
     this.frequencyperperiod=FrequencyPerPeriod;
     this.targetdate=Target;
     this.loanAmount=LoanAmount;
     this.loanRequested=LoanRequested;
     this.loanbalance=LoanBalance;
     this.defaultacc=Penalty;
     this.applicationfee=ApplicationFee;
     this.todaypay=TodayPay;
     this.givenOn=GivenOn;
     this.paidon=PaidOn;
  }
   public String getAutoId()
  {
   return this.autoid;
 }
     public String getId()
  {
   return this.id;
 }
      public String getLoanId()
  {
   return this.loanid;
 }
       public String getLoanType()
  {
   return this.loantype;
 }
        public String getApplicable()
  {
   return this.applicable;
 }
         public String getInstallmentAmount()
  {
   return this.installmentamount;
 }
   public String getInstallmentNo()
  {
   return this.installmentsno;
 } 
   public String getPeriodType()
  {
   return this.periodtype;
 }
  public String getFrequencyPerPeriod()
  {
   return this.frequencyperperiod;
 }
  public Date getTarget()
  {
   return this.targetdate;
 }
   public String getLoanAmount()
  {
   return this.loanAmount;
 }
    public String getLoanRequested()
  {
   return this.loanRequested;
 }
     public String getLoanBalance()
  {
   return this.loanbalance;
 }
      public String getPenalty()
  {
   return this.defaultacc;
 }
       public String getApplicationFee()
  {
   return this.applicationfee;
 }
        public String getTodayPay()
  {
   return this.todaypay;
 }
         public String getGivenOn()
  {
   return this.givenOn;
 }
          public String getPaidOn()
  {
   return this.paidon;
 }
}
