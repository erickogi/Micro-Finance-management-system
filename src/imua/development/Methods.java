/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imua.development;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
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
        JOptionPane.showMessageDialog(null, "User" + message + " Succefully");
      }
      else
      {
        JOptionPane.showMessageDialog(null, "User " + message);
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
}
