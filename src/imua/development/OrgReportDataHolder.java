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
public class OrgReportDataHolder {
    String in;
    String out;
    String id;
    String description;
    String date;
 static TableModel resultSetToTableModel(ResultSet result)
  {
    throw new UnsupportedOperationException("Not supported yet.");
  }
  
  public OrgReportDataHolder(String ID, String In, String Out, String Description, String Date)
  {
      this.id = ID;
     this.in = In;
     this.out = Out;
     this.description=Description;
     this.date=Date;
    
    
  }
   public String getID()
  {
    return this.id;
  }
     public String getIn()
  {
    return this.in;
  }
      public String getOut()
  {
    return this.out;
  }
      public String getDescription()
  {
    return this.description;
  }
       public String getDate()
  {
    return this.date;
  }
}