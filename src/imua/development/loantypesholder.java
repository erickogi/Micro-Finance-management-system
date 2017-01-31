/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imua.development;

import imua.development.*;

/**
 *
 * @author kimani kogi
 */
public class loantypesholder {
    
    
    private String id;
    private String name;
    private String rate;
    private String appfee;
    private String penalty;
    private String balance;
    private String rateTime,ratePer,applicable;
  
             
    
     public loantypesholder(String Id, String Name,String Rate,String Appfee,String Penalty,String Balance,String RateTime,String RatePer,String Applicable)
  {
    this.id = Id;
    this.name = Name;
    this.rate = Rate;
    this.appfee = Appfee;
    this.penalty = Penalty;
    this.balance = Balance;
    this.rateTime=RateTime;
    this.ratePer=RatePer;
    this.applicable=Applicable;
    
   
 
  }
  
  public String getId()
  {
   return this.id;
 }
  
  public String getName()
  {
    return this.name;
  }
   public String getRate()
  {
    return this.rate;
  }
   public String getAppFee()
  {
    return this.appfee;
  }
   public String getPenalty()
  {
    return this.penalty;
  }
   public String getBalance()
  {
    return this.balance;
  }
    public String getApplicable()
  {
    return this.applicable;
  }
     public String getRatePer()
  {
    return this.ratePer;
  }
      public String getRateTime()
  {
    return this.rateTime;
  }
  
  
  
 
}
