/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imua.development;



/**
 *
 * @author kimani kogi
 */
public class UserAcc {
    


  private String id;
  private String accountname;

 
  
  public UserAcc(String Id, String Accountname)
  {
    this.id = Id;
    this.accountname = Accountname;
 
  }
  
  public String getId()
  {
   return this.id;
 }
  
  public String getAccName()
  {
    return this.accountname;
  }
  
 
}
