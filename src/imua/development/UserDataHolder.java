/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imua.development;

import java.sql.ResultSet;
import java.util.Date;
import javax.swing.table.TableModel;

/**
 *
 * @author kimani kogi
 */
public class UserDataHolder {
String id;
String salutation;
String fname;
String lname;
String email;
String phone;
String address;
String county;
String town;
String occupation;
String marital;
String acc;
String group;
String img;
static TableModel resultSetToTableModel(ResultSet result)
  {
    throw new UnsupportedOperationException("Not supported yet.");
  }
  
  public UserDataHolder(String ID,String Sal,String Fname,String Lname, 
          String Email,String Phone,String Address,
          String County,String Town,
         String Occu, String Marital, String Acc,
         String Group,String Img)
  {
       
      this.id = ID;
     this.salutation=Sal;
     this.fname=Fname;
     this.lname=Lname;
     this.email=Email;
     this.phone=Phone;
     this.address=Address;
     this.county=County;
     this.town=Town;
     this.occupation=Occu;
     this.marital=Marital;
     this.acc=Acc;
     this.group=Group;
     this.img=Img;
    
  }
  public String getId()
  {
   return this.id;
 }
  public String getSalutation()
  {
   return this.salutation;
 }
  public String getFname()
  {
   return this.fname;
 }
  public String getLname()
  {
   return this.lname;
 }
  public String getEmail()
  {
   return this.email;
 }
  public String getPhone()
  {
   return this.phone;
 }
  public String getAddress()
  {
   return this.address;
 }
  public String getCounty()
  {
   return this.county;
 }
  public String getTown()
  {
   return this.town;
 }
  public String getOccupation()
  {
   return this.occupation;
 }
  public String getMarital()
  {
   return this.marital;
 }
  public String getAccount()
  {
   return this.acc;
 }
  public String getGroup()
  {
   return this.group;
 }
  public String getImage()
  {
   return this.img;
 }
}
