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
public class defaultersHolder {
String perid;
String loantype;
String loanid;
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
String autoid;
static TableModel resultSetToTableModel(ResultSet result)
  {
    throw new UnsupportedOperationException("Not supported yet.");
  }
  
  public defaultersHolder(String LID,String ID, Date ate, String Installmentamount,
  String LoanAmount,String autoid,String instno,String Acctype,String defaultAmount,String nxp)
  {
      loanid=LID;
      this.perid = ID;
      this.targetdate=ate;
      this.installmentamount=Installmentamount;
    this.loanAmount=LoanAmount;
    this.autoid=autoid;
    this.installmentsno=instno;
    this.loantype=Acctype;
  }
   public String getId()
  {
    return this.perid;
  }
    public String getLId()
  {
    return this.loanid;
  }
    public Date getDate()
  {
    return this.targetdate;
  }
     public String getInstallmentAmount()
  {
    return this.installmentamount;
  }
      public String getLoanAmount()
  {
    return this.loanAmount;
  }
       public String getAutoId()
  {
    return this.autoid;
  }
  public String getInstallmentNo()
  {
    return this.installmentsno;
  }
   public String getLoanType()
  {
    return this.loantype;
  }
}
