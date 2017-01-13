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
public class ApplicationData {
    
  private String id;
  private String name;

  private String type;
  private String amount;
   private String date;
 private String status;
 private String guranters;
// private String status;
  
  static TableModel resultSetToTableModel(ResultSet result)
  {
    throw new UnsupportedOperationException("Not supported yet.");
  }
  
  public ApplicationData(String ID, String Name, String Amount, String Date,String Status,String Type,String Guranters)
  {
      this.id = ID;
     this.name = Name;
     this.amount = Amount;
     this.date = Date;
    this.status = Status;
    this.type = Type;
    this.guranters = Guranters;
    
  }
   public String getName()
  {
    return this.name;
  }
    public String getGuranters()
  {
    return this.guranters;
  }
  public String getDate()
  {
    return this.date;
  }
  
  public String getType()
  {
    return this.type;
  }
  
  public String getID()
  {
    return this.id;
  }
  
  public String getStatus()
  {
    return this.status;
  }
   public String getAmount()
  {
    return this.amount;
  }
}
