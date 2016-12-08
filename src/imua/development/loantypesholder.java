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
public class loantypesholder {
    
    
    private String id;
    private String name;
    private String rate;
    private String appfee;
    private String penalty;
    private String balance;
    private String lv1;
    private String lv2;
    private String lv3;
    private String lv4;
    private String lv5;
    private String lv6;
    private String lv7;
    private String lv8;
    private String lv9;
    private String lv10;
             
    
     public loantypesholder(String Id, String Name,String Rate,String Appfee,String Penalty,String Balance,String tlv1,String tlv2,String tlv3,String tlv4,String tlv5,String tlv6,String tlv7,String tlv8,String tlv9,String tlv10)
  {
    this.id = Id;
    this.name = Name;
    this.rate = Rate;
    this.appfee = Appfee;
    this.penalty = Penalty;
    this.balance = Balance;
    this.lv1 = tlv1;
    this.lv2 = tlv2;
    this.lv3 = tlv3;
    this.lv4 = tlv4;
    this.lv5 = tlv5;
    this.lv6 = tlv6;
    this.lv7= tlv7;
    this.lv8 = tlv8;
    this.lv9 = tlv9;
    this.lv10 = tlv10;
   
 
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
    public String getLv1()
  {
    return this.lv1;
  }
     public String getLv2()
  {
    return this.lv2;
  }
     public String getLv3()
  {
    return this.lv3;
  }
      public String getLv4()
  {
    return this.lv4;
  }
       public String getLv5()
  {
    return this.lv5;
  }
        public String getLv6()
  {
    return this.lv6;
  }
         public String getLv7()
  {
    return this.lv7;
  }
          public String getLv8()
  {
    return this.lv8;
  }
           public String getLv9()
  {
    return this.lv9;
  }
            public String getLv10()
  {
    return this.lv10;
  }
          
  
  
 
}
