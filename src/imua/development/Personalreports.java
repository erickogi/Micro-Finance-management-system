/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imua.development;

import java.sql.ResultSet;
import javax.swing.table.TableModel;

/**
 *
 * @author kimani kogi
 */
public class Personalreports {
    

  private String date;
  private String type;

  private String deposit;
  private String withdrwal;
   private String balance;
 // private String status;
  
  static TableModel resultSetToTableModel(ResultSet result)
  {
    throw new UnsupportedOperationException("Not supported yet.");
  }
  
  public Personalreports(String Date, String Type, String Deposit, String Withdrawal,String Balance)
  {
    this.date = Date;
    this.type = Type;
    this.deposit = Deposit;
    this.withdrwal = Withdrawal;
     this.balance = Balance;
  }
  
  public String getDate()
  {
    return this.date;
  }
  
  public String getType()
  {
    return this.type;
  }
  
  public String getDeposit()
  {
    return this.deposit;
  }
  
  public String getWithdrawal()
  {
    return this.withdrwal;
  }
   public String getBalance()
  {
    return this.balance;
  }
}
