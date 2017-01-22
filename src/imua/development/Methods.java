/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imua.development;

import java.awt.Color;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author kimani kogi
 */
public class Methods {
    
     public Connection getConnection()
  {
      //for client server situations ,unomment the following line getpath();
      //then comment line 103=path="localhost";
    //getPath();
    Connection con = null;
    try
    {
    String path;
   // String db=":3306/inua";
   String db=":3306/inua?autoReeconnect=true&useSSL=false";
   String jdbc="jdbc:mysql://";
   String user="root";
   String pass="123ERYcog.";
   //for client server instance comment next line path =localhost
   path="localhost";
   String dbp=(jdbc+path+db);
        
      con = DriverManager.getConnection(dbp,user,pass);
     // con = DriverManager.getConnection("jdbc:mysql://localhost:3306/library", "root", "123ERYcog.");
    }
    catch (Exception ex)
    {
      System.out.println(ex.getMessage());
    }
    return con;
  }
      public int executeSQlQuery(String query, String message)
  {
      int a=0;
 //  methods m=new methods();
        Connection con = getConnection();
    try
    {
      Statement st = con.createStatement();
      if (st.executeUpdate(query) == 1)
      {
        a=1;
        JOptionPane.showMessageDialog(null,  message + " Succefully");
      }
      else
      {
        JOptionPane.showMessageDialog(null, message);
      }
      st.close();
      con.close();  
    }
    catch (Exception ex)
    {
      ex.printStackTrace();
    }
    return a;
  }
       public int executeSQlQueryN(String query)
  {
      int a=0;
 //  methods m=new methods();
        Connection con = getConnection();
    try
    {
      Statement st = con.createStatement();
      if (st.executeUpdate(query) == 1)
      {
        a=1;
       
      }
      else
      {
        a=0;
      }
      st.close();
      con.close();  
    }
    catch (Exception ex)
    {
      ex.printStackTrace();
    }
    return a;
  }
      
      public String setIconImage() {
       String image="invt.png";
       return image;
    }
public void addToOrgAccount(Double amount,String Description){
    String xro="0";
     String query = "INSERT INTO orgaccount("
             + "`cashin`,"
             + "`cashout`,"
             
             + "`description`"
             + ",`date`)"
             + " VALUES ("
             + "'" + amount + "'"
             + ",'" + xro+ "'"
             + ",'" + Description+ "'"
            + ",now())";
    
    executeSQlQueryN(query);
    
    
    
}
public void RemoveFromOrgAccount(Double amount,String Description){
    String xro="0";
     String query = "INSERT INTO orgaccount("
             + "`cashin`,"
             + "`cashout`,"
             
             + "`description`"
             + ",`date`)"
             + " VALUES ("
             + "'" + xro + "'"
             + ",'" + amount+ "'"
             + ",'" + Description+ "'"
            + ",now())";
    
    executeSQlQueryN(query);
    
    
    
}
    public String setTitle() {
        String title="Inua Development";
      
         return title;
    }
      
     public ArrayList<String> ListUsers()
  {
    ArrayList<String> usersList = new ArrayList<String>();
    try
    {
      
        Connection con = getConnection();
     // Connection con = getConnection();
      Statement st = con.createStatement();
      String searchQuery = "SELECT * FROM `useracctypes` ";
      ResultSet rs = st.executeQuery(searchQuery);
      while (rs.next())
      {
          
        usersList.add(rs.getString("accountname"));
        
        //usersList.add(user);
      }
      st.close();
      rs.close();
      con.close();
    }
    catch (Exception ex)
    {
      System.out.println(ex.getMessage());
    }
    return usersList;
  }
      public String[] findUsers()
  {
     String []accounts;
    ArrayList<String> usersList =  ListUsers();
   accounts=new String[usersList.size()];
   usersList.toArray(accounts);
    
   
      //accounts=users.toArray();
 // users=new ArrayList<users>();
   
   return accounts;
  }
     public ArrayList<String> ListUsersGroups()
  {
    ArrayList<String> usersList = new ArrayList<String>();
    try
    {
      
        Connection con = getConnection();
     // Connection con = getConnection();
      Statement st = con.createStatement();
      String searchQuery = "SELECT * FROM `usergroups` ";
      ResultSet rs = st.executeQuery(searchQuery);
      while (rs.next())
      {
          
        usersList.add(rs.getString("groupname"));
        
        //usersList.add(user);
      }
      st.close();
      rs.close();
      con.close();
    }
    catch (Exception ex)
    {
      System.out.println(ex.getMessage());
    }
    return usersList;
  }
      public ArrayList<String> ListLoanTypes()
  {
    ArrayList<String> usersList = new ArrayList<String>();
    try
    {
      
        Connection con = getConnection();
     // Connection con = getConnection();
      Statement st = con.createStatement();
      String searchQuery = "SELECT * FROM `accounttypes` ";
      ResultSet rs = st.executeQuery(searchQuery);
      while (rs.next())
      {
          
        usersList.add(rs.getString("name"));
        
        //usersList.add(user);
      }
      st.close();
      rs.close();
      con.close();
    }
    catch (Exception ex)
    {
      System.out.println(ex.getMessage());
    }
    return usersList;
  }
//      public String[] findGroups()
//  {
//     String []accounts;
//    ArrayList<String> usersList =  ListUsersGroups();
//   accounts=new String[usersList.size()];
//   usersList.toArray(accounts);
//    
//   
//      //accounts=users.toArray();
// // users=new ArrayList<users>();
//   
//   return accounts;
//  }
  //public 
      
      
      
       public static Date addMonth(Date date,int hw){
      Calendar  c= Calendar.getInstance();
      c.setTime(date);
      
      c.add(Calendar.MONTH, hw);
      
      return c.getTime();
  }
   public static Date addWeek(Date date,int hw){
      Calendar  c= Calendar.getInstance();
      c.setTime(date);
      
      c.add(Calendar.DATE, hw*7);
      
      return c.getTime();
  }
    public static Date addDay(Date date,int hw){
      Calendar  c= Calendar.getInstance();
      c.setTime(date);
      
      c.add(Calendar.DAY_OF_WEEK_IN_MONTH, hw);
      
      return c.getTime();
  }
    public static Date addYear(Date date,int hw){
      Calendar  c= Calendar.getInstance();
      c.setTime(date);
      
      c.add(Calendar.YEAR, hw);
      
      return c.getTime();
  }
       
        public String selectcolor()
    throws Exception
           
  { String col="";
      try{
         File x=new File("file.txt"); 
        // File x=new File("file.txt");
           Scanner sc=new Scanner(x);
           String fs="";
           while(sc.hasNext()){
              col=col+sc.next(); 
              }
           
             try{
                 
            
     
       Color c=new Color(Integer.parseInt(col));
       }
           
            
       catch(Exception cc){
          // c.printStackTrace();
           col="-1";
       }
             

      }
        catch(Exception cc){
         // cc.printStackTrace();
           col="-1";
       }
    return col;
  }
}
