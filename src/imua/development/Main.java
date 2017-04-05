/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imua.development;

import IDataHolders.ILoanDataHolder;
import imua.development.Accounts.Accgroups;
import imua.development.Accounts.AccountRegistration;
import imua.development.Accounts.AccountsReport;
import imua.development.Accounts.GroupView;
import imua.development.Accounts.Personalreport;
import imua.development.Configurations.Settings;
import imua.development.Configurations.about;
import imua.development.Configurations.changepass;
import imua.development.Reports.OrgReport;
import imua.development.Reports.GroupSheet;
import imua.development.Accounts.Users;
import imua.development.BackupRestore.Restore;
import imua.development.DepositandWithdrawal.Deposit;
import imua.development.loans.Defaulters;
import imua.development.loans.LoanHistory;
import imua.development.loans.LoanWithSavings;
import imua.development.loans.LoanWithoutSavings;
import imua.development.loans.ProcessLoan;
import imua.development.loans.TodaysLoans;
import imua.development.loans.loanform;
import imua.development.loans.loanpayment;
import imua.development.loans.loantypes;
import imua.development.loginClass.AddUsers;
import imua.development.loginClass.login1;
import imua.development.BackupRestore.backup;
import imua.development.Reports.MainOrgAccount;
import imua.development.loans.IDefaulters;
import imua.development.loans.IProcessLoan;
import imua.development.loans.Iloanpayment;
//import imua.development.loans.P;
import java.awt.Color;
import java.awt.Container;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import static java.lang.System.out;
import static java.lang.Thread.sleep;
import java.net.URI;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.Border;

/**
 *
 * @author Kimani
 */
public class Main extends javax.swing.JFrame {
    public static String hash="";
   public static int on;
  public static  int a=0;
  public static int  ch=0;
  Methods m = new Methods();
  Methods methods = new Methods();
  String s = "Inua ";
  
    /**
     * Creates new form Main
     */
    public Main() {
         
      
        initComponents();
        jToolBar1.setVisible(false);
      // jToolBar2.add(Box.createHorizontalGlue());
    
       //  ex();
       setTilteImage();
     
       
  
        pic();
    
       getTime();
       if(a==0){
         login();
         
       }
    //   else{
    //       timerForLoanApplication();
    //   }
      
       
    // getTime();
        
    }
    //okay------sets color.icon,frame name/title
    public Color setTilteImage(){
        Color c=null;
        try {
            Methods n=new Methods();
            String t= n.setTitle();
            this.setTitle(t);
            String i=n.setIconImage();
            this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource(i)));
            
            String col=n.selectcolor();
             c=new Color(Integer.parseInt(col));
           // jPanel1.setBackground(c);
            Container cont=this.getContentPane();
            cont.getWidth();
            cont.setBackground(c);
            
            jPanel2.setBackground(c);
            jToolBar1.setBackground(c);
            this.setForeground(c);
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        return c;
}
    
 
    ////////////////// i teary ////////////////
    public  void checkTodays(){
       Calendar  c= Calendar.getInstance();
         HashMap<String,String>todays=new HashMap<String,String>();
       Date today=c.getTime();
       java.util.Date d=(today);
     
     java.sql.Date DATE=new java.sql.Date(d.getTime());
      try {
          Methods n = new Methods();
          
          Connection con =n. getConnection();
          
          Statement st = con.createStatement();
          String nxps="np";
          String searchQuery = "SELECT * FROM `iloans`WHERE datesupposed='" + DATE + "'AND paymentstatus='" + nxps+ "'  ";
          ResultSet rs = st.executeQuery(searchQuery);
          while (rs.next())
          {
              
           todays.put(rs.getString("id"), rs.getString("installmentamount"));
            
          }
          st.close();
          rs.close();
          con.close();
          
          if(todays.size()>0){
           int dialogButton=
        
        JOptionPane.showConfirmDialog(null,todays.size()+ "  LOANS REPAYMENT EXPECTED TODAY \n Select YES to check them out ");
        if(dialogButton==JOptionPane.YES_OPTION){
            TodaysLoans tl=new TodaysLoans();
            tl.setVisible(true);
        }  
              
         // JOptionPane.showMessageDialog(null, todays.size()+ "LOANS REPAYMENT EXPECTED TODAY");
          }
          checkLoanApplications();
          
      } catch (SQLException ex) {
          Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
      }
      
      //c.add(Calendar.MONTH, hw);
      
    } 
    private  void checkLoanApplications(){
             //  Calendar  c= Calendar.getInstance();
         HashMap<String,String>todays=new HashMap<String,String>();
//      Date today=c.getTime();
//       java.util.Date d=(today);
//     
//     java.sql.Date DATE=new java.sql.Date(d.getTime());
      try {
          Methods n = new Methods();
          
          Connection con =n. getConnection();
          
          Statement st = con.createStatement();
          String searchQuery = "SELECT * FROM `applications` ";
          ResultSet rs = st.executeQuery(searchQuery);
          while (rs.next())
          {
              
          todays.put(rs.getString("id"), rs.getString("amount"));
            
          }
          st.close();
          rs.close();
          con.close();
          
          if(todays.size()>0){
        int dialogButton=
        
        JOptionPane.showConfirmDialog(null,todays.size()+ "  LOANS APPLICATIONS AVAILABLE \n Select YES to check them out ");
        if(dialogButton==JOptionPane.YES_OPTION){
        // Main.setEnabled(false);
        IProcessLoan a=new IProcessLoan();
         //a.typeoftransaction="withdrawal";
        a.setVisible(true);
        } 
              
              
         // JOptionPane.showMessageDialog(null, todays.size()+ "LOANS APPLICATIONS AVAILABLE");
          }
          //checkLoanRepayments();
          checkiLoansDefaulters();
          
      } catch (SQLException ex) {
          Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
      }
      
    }
//    public  int checkIfThereIsNext(String autoid,String id,String inNo,String ltype,String loanId,String instAmount,Double loanBalance){
//        //Main ma=new Main();
//        double mLoanBalance=0.0;//what to minus from loan balance
//        int pd=0;
//           out.println(checkCarriedForward(inNo,loanId));
//    try {
//        Methods n = new Methods();
//        Connection con =n. getConnection();
//        int inno=Integer.valueOf(inNo)-1;
//        Statement st = con.createStatement();
//        String searchQuery = "SELECT * FROM `loans`WHERE autoid>'" + autoid + "'AND id='"+id+"'"
//                + " AND installmentsno='"+inno+"'AND loantype='"+ltype+"'  ";
//        ResultSet rs = st.executeQuery(searchQuery);
//        if (rs.next())
//        {   
//            pd=0;
//            //i think i have been thru this step atleast once so no need of doing it again 
//            // i mean we already created a next target.right now we just have to notify the admin that this guy is a defaulter thus dp=0
//            if(checkCarriedForward(inNo,loanId)>0){
//                if(checkCarriedForward(inNo,loanId)==Double.valueOf(instAmount)){
//                    //dont now what to do
//                }
//                if(checkCarriedForward(inNo,loanId)>Double.valueOf(instAmount)){
//                    //dont now what to do
//                }
//                if(checkCarriedForward(inNo,loanId)<Double.valueOf(instAmount)){
//                    //dont know what to do
//                }
//            }
//        }
//        else{
//            //so we need to set another target day as this guy didnt pay the last one so its a default
//            if(checkCarriedForward(inNo,loanId)>0){
//             //if credit carried forward is there then we need to clear or reduce the penalty as below
//              if(checkCarriedForward(inNo,loanId)==Double.valueOf(instAmount)){
////if credit brought forwad is equal to the instalment required then clear the guy by setting tp  to paid and nxp of previous entry to paid 
//                  String defaultamount="null";
//                  Double defaultValue=0.0;
//                  pd=1;//notify admin that this guy has been cleared
//                  String nxp="paid";
//                  updateTonxp(inNo,loanId,nxp) ;
//                  ///loan balance will be 0.0 - - mloanbalance
//                  mLoanBalance=loanBalance;
//                  setNewTarget("autoid",autoid,defaultamount,defaultValue,nxp,mLoanBalance); 
//              }
//               if(checkCarriedForward(inNo,loanId)>Double.valueOf(instAmount)){
////if credit brought forwad is more than the instalment required then clear the guy by setting tp  to pm and nxp of previous entry to pm 
//                   String defaultamount=String.valueOf(checkCarriedForward(inNo,loanId)-Double.valueOf(instAmount));
// //carry forward the remainder to next target
//                   String nxp="pm";
//                   pd=1;//notify the admin that this guy has been cleared
//                   Double defaultValue=checkCarriedForward(inNo,loanId)-Double.valueOf(instAmount);
//                   
//                   updateTonxp(inNo,loanId,nxp) ;
//                   ///loan balance will be 0.0 - -mloanbalance
//                    mLoanBalance=loanBalance;
//                  setNewTarget("autoid",autoid,defaultamount,defaultValue,nxp,mLoanBalance); 
//              }
//                if(checkCarriedForward(inNo,loanId)<Double.valueOf(instAmount)){
////if credit brought forwad is less than the instalment required then the guy is still doomed .he is still a defaulter by the diffrence                  
//                    pd=2;//notify admin that he is a defaulter
//                   String nxp="nfp";
//                    String defaultamount=String.valueOf(Double.valueOf(instAmount)-checkCarriedForward(inNo,loanId));
//                  //   String defaultamount=String.valueOf(checkCarriedForward(inNo,loanId)-Double.valueOf(instAmount));
//                 updateTonxp(inNo,loanId,nxp) ;
//                 Double defaultValue=Double.valueOf(instAmount)-checkCarriedForward(inNo,loanId);
//                 ///loan balance will be 0.0 - - mloanbalance-
//                 mLoanBalance=loanBalance-(defaultValue);
//                 
//                  setNewTarget("autoid",autoid,defaultamount,defaultValue,nxp,mLoanBalance); 
//              }
//             //setNewTarget("autoid",autoid); 
//             
//                }
//            else{
//                pd=2;//this guy is still a defaulter as he/she had no previous credit brought forward
//               String nxp="np";
//               Double defaultValue=0.0;
//               String defaultamount="null";
//               mLoanBalance=loanBalance-Double.valueOf(instAmount);
//               setNewTarget("autoid",autoid,defaultamount,defaultValue,nxp,mLoanBalance); 
//            }
//            
//          
//            }
//        st.close();
//        rs.close();
//        con.close();
//    } catch (SQLException ex) {
//        Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
//    }
//    return pd;
//    }
//    public double checkCarriedForward(String instNo,String loanId){
//        double cf=0.0;
//        int a=1;
//        try {
//            
//            Methods n = new Methods();
//            Connection con =n. getConnection();
//            Statement st = con.createStatement();
//            Statement st1 = con.createStatement();
//            int preInst=Integer.valueOf(instNo)+1;
//            int Inst=Integer.valueOf(instNo);
//            String searchQuery = "SELECT defaultacc FROM `loans`WHERE loanid='" + loanId + "' AND installmentsno='"+Inst+"' ";
//            String searchQuery1 = "SELECT nxp FROM `loans`WHERE loanid='" + loanId + "' AND installmentsno='"+preInst+"' ";
//            ResultSet rs = st.executeQuery(searchQuery);
//            ResultSet rs1 = st1.executeQuery(searchQuery1);
//            if (rs.next()&&rs1.next())   
//            {
//                // out.println("found rs.next"+rs.getString(1)+" "+a);
//                // a++;
//                if(rs1.getString("nxp").equals("pm")){
//                   // out.println("found nxp-pm");
//                    try{
//                    cf=Double.valueOf(rs.getString("defaultacc"));
//                    }
//                    catch(NumberFormatException m){
//                    //    out.println("null");
//                    }
//                }
//            }
//            
//            
//            st.close();
//            st1.close();
//            rs.close();
//            rs1.close();
//            con.close();
//        } catch (SQLException ex) {
//            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return cf;
//    }
//    public void updateTonxp(String instNo,String loanId,String nxp){
//         String query = "UPDATE `loans` SET `nxp`='" + nxp + "'WHERE loanid= '" + loanId+ "' AND installmentsno='" + instNo + "'";
//      
//      m.executeSQlQueryN(query);
//    }
//    public int maxid(String where,String value){
//    int id=0;
//    Connection con = m.getConnection();
//    try{
//        String getMaxId="SELECT MAX(autoid) FROM `loans` WHERE `" + where + "` = '" + value + "'";
//         Statement st = con.createStatement();
//           ResultSet rs = st.executeQuery(getMaxId);
//           if(rs.next()){
//               id=rs.getInt(1);
//           }
//       st.close();
//      rs.close();
//      con.close();
//    }
//    catch(Exception nw){
//        nw.printStackTrace();
//    }
//    return id;
//}
//    public double checkBalance(String where,String max){
//        double bal=0.0;
// Connection con = m.getConnection();
//    try{
//        String getMaxId="SELECT loanbalance FROM `loans` WHERE `" + where + "` = '" + max + "'";
//         Statement st = con.createStatement();
//           ResultSet rs = st.executeQuery(getMaxId);
//           if(rs.next()){
//             
//                   
//               bal=rs.getDouble("loanbalance");
//           }
//      st.close();
//      rs.close();
//      con.close();
//    }
//    catch(Exception nw){
//        nw.printStackTrace();
//    }
//    return bal;
//}
//    
    
//    public  void checkLoanRepayments(){
//       
//         HashMap<String,String>todays=new HashMap<String,String>();
//           Calendar  c= Calendar.getInstance();
//          Date today=addOne(c.getTime(),-1);
//         
//         java.util.Date d=(today);
//  //Days d=Days.daysBeetween();
//         java.sql.Date DATE=new java.sql.Date(d.getTime());
//      try {
//          Methods n = new Methods();
//          String nl="np";
//          String lp="nfp";
//          Connection con =n. getConnection();
//          
//          Statement st = con.createStatement();
//          String searchQuery = "SELECT * FROM `loans`WHERE (targetdate<'" + DATE + "'AND nxp='"+nl+"') OR (targetdate<'" + DATE + "'AND nxp='"+lp+"')";
//          ResultSet rs = st.executeQuery(searchQuery);
//          while (rs.next())
//          {  String maxid=rs.getString("autoid");
//             String inNo=rs.getString("installmentsno");
//             String id= rs.getString("id");
//             String loanId= rs.getString("loanid");
//             String loanType= rs.getString("loantype");
//             String instAmount= rs.getString("installmentamount");
//             
//             String maxv=String.valueOf(maxid("loanid",loanId));
//             double bal=checkBalance("autoid",maxv);
//             
//             int pd= checkIfThereIsNext(maxid,id,inNo,loanType,loanId,instAmount,bal);
//              if(pd==0||pd==2){
//              todays.put(rs.getString("id"), rs.getString("installmentamount"));
//              }
//            
//          }
//          st.close();
//          rs.close();
//          con.close();
//          
//          if(todays.size()>0){
//        int dialogButton=
//        
//        JOptionPane.showConfirmDialog(null,todays.size()+ "  LOANS DEFAULTED \n Select YES to check them out ");
//        if(dialogButton==JOptionPane.YES_OPTION){
//            
//                    Defaulters kd=new Defaulters();
//                    
//        //a.typeoftransaction="withdrawal";
//                     kd.setVisible(true);
//        }
//        
//          //JOptionPane.showMessageDialog(null, todays.size()+ "  LOANS DEFAULTED ");
//          }
//          
//          //checkLoanRepayments();
//          
//      } catch (SQLException ex) {
//          Logger.getLogger(ProcessLoan.class.getName()).log(Level.SEVERE, null, ex);
//      }
//    }
//    
    
    
    
    
    /*
    To switch back to previous version uncoment all methods from line 235 ...
    Also uncomment the method call on line 228
    
    Note>>>>>>to maintain order,any new code should go below this comments
    
    
    New version====
    
    
    check loan with date greater than today...\
    if there is, 
    
    if not updated{
    update the  default status to id--in default
    
    copy n push the whole loan with its properties to iloansdefaulters db-table
    
    
    add an entry with null data but ,,,,to track dates
    
    
    
    
    
    
    
    
    
    
    
    
    */
    
    public void checkiLoansDefaulters(){
          Calendar  c= Calendar.getInstance();
          Date today=addOne(c.getTime(),-1);
          java.util.Date d=(today);
          java.sql.Date DATE=new java.sql.Date(d.getTime());
          
           ArrayList<ILoanDataHolder>    defaultersDataList = new ArrayList();
           ArrayList<ILoanDataHolder>notPushedToDefaulters=new ArrayList();
           ArrayList<ILoanDataHolder>setNewLoanEntry=new ArrayList();
           
          Methods n = new Methods();
          
           try 
           {
          
          String nl="np";
          String lp="nfp";
          Connection con =n. getConnection();
          
          Statement st = con.createStatement();
          String searchQuery = "SELECT * FROM `iloans` WHERE (datesupposed<'" + DATE + "'AND paymentstatus='"+nl+"') OR (datesupposed<'" + DATE + "'AND paymentstatus='"+lp+"')";
          ResultSet rs = st.executeQuery(searchQuery);
          while (rs.next())
          { 
              Double todayPay=Double.valueOf(rs.getString("todaypay"));
              Double balancebf=Double.valueOf(rs.getString("balancebf"));
              Double installmentAmount=Double.valueOf(rs.getString("installmentamount"));
              
              
              
          ILoanDataHolder data = new ILoanDataHolder(rs.getString("autoid"),rs.getString("customerid"),
                rs.getString("loanid")
                ,rs.getString("loantype")
                ,rs.getString("applicablestatus")
                ,rs.getString("applicationfee")
                ,rs.getString("periodtype")
                ,rs.getString("frequencyperperiod")
                ,rs.getDate("datesupposed")
                ,rs.getString("installmentamount")
                ,rs.getString("installmentsno")
                ,rs.getString("loanAmount")
                ,rs.getString("loanRequested")
                ,rs.getString("loanbalance")
                ,rs.getString("moreorlesspaid")
                ,rs.getString("todaypay")
                ,rs.getString("paymentstatus")
                ,rs.getString("defaultstatus")
                ,rs.getString("balancebf")
                ,rs.getString("extra")
                ,rs.getString("loangivenOn")
                ,rs.getString("paidon")
          
          );
          
           if((balancebf+todayPay)>=installmentAmount){
                  
                  setNewLoanEntry.add(data);
              }
           
           
           else{
          
          
          if(rs.getString("defaultstatus").equals("nd")){
             
             
             
                  
                  notPushedToDefaulters.add(data);
              
          }
          
         
          
         
          
                 defaultersDataList.add(data);
          
          
          
          
           }
          
          
          }
          st.close();
          rs.close();
          con.close();
          
          if(!notPushedToDefaulters.isEmpty()){
           pushToDefaulters(notPushedToDefaulters);
          }
          if(!defaultersDataList.isEmpty()){
           setNextSupposedToPayDate(defaultersDataList);
          }
          if(!setNewLoanEntry.isEmpty()){
           setNewLoanEntry(setNewLoanEntry);
          }
          
          
          
          
          
        if(defaultersDataList.size()>0){
        int dialogButton=
        
        JOptionPane.showConfirmDialog(null,defaultersDataList.size()+ "  LOANS DEFAULTED \n Select YES to check them out ");
        if(dialogButton==JOptionPane.YES_OPTION){
            
                    IDefaulters kd=new IDefaulters();
                    
        
                     kd.setVisible(true);
        }
        
          
          }
        
        
        
       
          
          
          
          
          
          } catch (SQLException ex) {
          Logger.getLogger(ProcessLoan.class.getName()).log(Level.SEVERE, null, ex);
      }
           
           
           
           
           
           
           
           
    }
    
    
    
    public boolean pushToDefaulters(ArrayList<ILoanDataHolder>notPushedToDefaulters){
        boolean success=false;
         int s=0;
        
        for(int count=0;count<notPushedToDefaulters.size();count++){
        
         String query = "INSERT INTO iloansdefaulters("
                 
             + "`iloanautoid`,"    
             + "`customerid`,"
             + "`loanid`,"
             
             + "`loantype`,"
             +"`applicablestatus`,"
             + "`applicationfee`"
             
             + ",`periodtype`"
             + ",`frequencyperperiod`"
             + ",`datesupposed`"
             
             
             
             
             
             
             + " ,`installmentamount`"
             + ",`installmentsno`"
             
             
             
             + ",`loanAmount`"
             + ",`loanRequested`"
             + ",`loanbalance`"
             + ",`moreorlesspaid`"
             
             
             + ",`todaypay`"
             
             + ",`paymentstatus`"
             
             + ",`defaultstatus`"
             
             + ",`balancebf`"
             
             + ",`extra`"
             
             + ",`loangivenOn`"
             
             + ",`paidon`)"
             
             + " VALUES ("
             + "'" + ((ILoanDataHolder)notPushedToDefaulters.get(count)).getAutoid() + "'"    
             + ",'" + ((ILoanDataHolder)notPushedToDefaulters.get(count)).getCustomerid() + "'"
             + ",'" + ((ILoanDataHolder)notPushedToDefaulters.get(count)).getLoanid()+ "'"
             + ",'" + ((ILoanDataHolder)notPushedToDefaulters.get(count)).getLoantype()+ "'"
             + ",'" + ((ILoanDataHolder)notPushedToDefaulters.get(count)).getApplicablestatus()+ "'"
             + ",'" + ((ILoanDataHolder)notPushedToDefaulters.get(count)).getApplicationfee() + "'"
             + ",'" +((ILoanDataHolder)notPushedToDefaulters.get(count)).getPeriodtype()+"'"
             + ",'" + ((ILoanDataHolder)notPushedToDefaulters.get(count)).getFrequencyperperiod()+ "'"
            
                   
            + ",'" +((ILoanDataHolder)notPushedToDefaulters.get(count)).getDatesupposed() + "'"
             + ",'"+((ILoanDataHolder)notPushedToDefaulters.get(count)).getInstallmentamount()+"'"
             + ",'"+((ILoanDataHolder)notPushedToDefaulters.get(count)).getInstallmentsno()+"'"
                
             + ",'"+((ILoanDataHolder)notPushedToDefaulters.get(count)).getLoanAmount()+"'"
             + ",'"+((ILoanDataHolder)notPushedToDefaulters.get(count)).getLoanRequested()+"'"
             + ",'"+((ILoanDataHolder)notPushedToDefaulters.get(count)).getLoanbalance()+"'"
         
         
             + ",'" + ((ILoanDataHolder)notPushedToDefaulters.get(count)).getMoreorlesspaid() + "'"
             + ",'" +((ILoanDataHolder)notPushedToDefaulters.get(count)).getTodaypay()+ "'"
         
             + ",'" + ((ILoanDataHolder)notPushedToDefaulters.get(count)).getPaymentstatus() + "'"
             + ",'" + ((ILoanDataHolder)notPushedToDefaulters.get(count)).getDefaultstatus()+ "'"
             + ",'" + ((ILoanDataHolder)notPushedToDefaulters.get(count)).getBalancebf() + "'"
           
             + ",'"+((ILoanDataHolder)notPushedToDefaulters.get(count)).getExtra()+"'"
              + ",'"+((ILoanDataHolder)notPushedToDefaulters.get(count)).getLoangivenOn()+"'"
            + ",now())";
         
         
         
        // s=s+
        if(methods.executeSQlQueryN(query)==1){
         Date columnValue=null;
         updateiLoan("defaultstatus",columnValue,"autoid",((ILoanDataHolder)notPushedToDefaulters.get(count)).getAutoid(),"dl",0);
        }
        }
        if(s==notPushedToDefaulters.size()){
             success=true;
         }
         else{
             success=false;
         }
        
        
        
        
        
        
        return success;
    }
    
    
    public boolean setNextSupposedToPayDate(ArrayList<ILoanDataHolder>defaultersDataList){
        boolean success=false;
          for(int count=0;count<defaultersDataList.size();count++){
            Date columnValue  =  addPeriod(((ILoanDataHolder)defaultersDataList.get(count)).getDatesupposed(),((ILoanDataHolder)defaultersDataList.get(count)).getPeriodtype()
                 ,((ILoanDataHolder)defaultersDataList.get(count)).getFrequencyperperiod());
            
            
            
           updateiLoan("paidon",columnValue,"autoid",((ILoanDataHolder)defaultersDataList.get(count)).getAutoid(),"dl",1);
          }
        
        
        return success;
        
    }
    
    public boolean setNewLoanEntry(ArrayList<ILoanDataHolder>setNewLoanEntry){
        boolean success=false;
        String todayPay="0.0";
        String paymentStatus="np";
        String defaultStatus="nd";
        String i="0.0";
        
        
        for(int count=0;count<setNewLoanEntry.size();count++){
            
        Date date  =  addPeriod(((ILoanDataHolder)setNewLoanEntry.get(count)).getDatesupposed(),((ILoanDataHolder)setNewLoanEntry.get(count)).getPeriodtype()
                 ,((ILoanDataHolder)setNewLoanEntry.get(count)).getFrequencyperperiod());
        SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:SS");
            String   newdate = DATE_FORMAT.format( date);
        Double balancebf=Double.valueOf(((ILoanDataHolder)setNewLoanEntry.get(count)).getBalancebf());
        Double installmentValue=Double.valueOf(((ILoanDataHolder)setNewLoanEntry.get(count)).getInstallmentamount());
        String bbf=String.valueOf(balancebf-installmentValue);
        
        String query = "INSERT INTO iloans("
             + "`customerid`,"
             + "`loanid`,"
             
             + "`loantype`,"
             +"`applicablestatus`,"
             + "`applicationfee`"
             
             + ",`periodtype`"
             + ",`frequencyperperiod`"
             + ",`datesupposed`"
             
             + " ,`installmentamount`"
             + ",`installmentsno`"
           
             + ",`loanAmount`"
             + ",`loanRequested`"
             + ",`loanbalance`"
             + ",`moreorlesspaid`"
             
             
             + ",`todaypay`"
             
             + ",`paymentstatus`"
             
             + ",`defaultstatus`"
             
             + ",`balancebf`"
             
             + ",`extra`"
             
             + ",`loangivenOn`"
             
             + ",`paidon`)"
             
             + " VALUES ("
             + "'" + ((ILoanDataHolder)setNewLoanEntry.get(count)).getCustomerid()+ "'"
             + ",'" +((ILoanDataHolder)setNewLoanEntry.get(count)).getLoanid()+ "'"
             + ",'" + ((ILoanDataHolder)setNewLoanEntry.get(count)).getLoantype()+ "'"
             + ",'" + ((ILoanDataHolder)setNewLoanEntry.get(count)).getApplicablestatus()+ "'"
             + ",'" + ((ILoanDataHolder)setNewLoanEntry.get(count)).getApplicationfee() + "'"
             + ",'" +((ILoanDataHolder)setNewLoanEntry.get(count)).getPeriodtype()+"'"
             + ",'" + ((ILoanDataHolder)setNewLoanEntry.get(count)).getFrequencyperperiod()+ "'"
            
                   
            + ",'" + newdate + "'"
             + ",'"+((ILoanDataHolder)setNewLoanEntry.get(count)).getInstallmentamount()+"'"
             + ",'"+(Integer.valueOf(((ILoanDataHolder)setNewLoanEntry.get(count)).getInstallmentsno())-1)+"'"
                
             + ",'"+((ILoanDataHolder)setNewLoanEntry.get(count)).getLoanAmount()+"'"
             + ",'"+((ILoanDataHolder)setNewLoanEntry.get(count)).getLoanRequested()+"'"
             + ",'"+((ILoanDataHolder)setNewLoanEntry.get(count)).getLoanbalance()+"'"
         
         
             + ",'" + todayPay + "'"
             + ",'" + todayPay + "'"
         
             + ",'" + paymentStatus + "'"
             + ",'" + defaultStatus + "'"
             + ",'" + bbf+ "'"
           
             + ",'"+i+"'"
             + ",'"+((ILoanDataHolder)setNewLoanEntry.get(count)).getLoangivenOn()+"'"
             + ",now())";
       if( methods.executeSQlQueryN(query)==1){
           Date dateValueToBeUpdated=null;
           
                Calendar  c= Calendar.getInstance();
          Date today=c.getTime();
                dateValueToBeUpdated=today;  
          java.util.Date d=(today);
          java.sql.Date DATE=new java.sql.Date(d.getTime());
           updateiLoan("paymentstatus",DATE,"autoid",((ILoanDataHolder)setNewLoanEntry.get(count)).getAutoid(),"paid",2);
       }
        
        
    }
        return success;
    }
    
    
    public void updateiLoan(String columnNameToBeUpdated,Date dateValueToBeUpdated,String keyColumn,String keyValue,String other,int othervalue){
       String query="";
       
      //update default status to ld
        if(othervalue==0){
         query = "UPDATE `iloans` SET `defaultstatus` ='" + other + "'"
               
              + "WHERE autoid= '" + keyValue+ "' ";
        }
        
        //update loan paid on to next target date to keep track of installment days
        else if(othervalue==1){
            
            
            SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:SS");
            String   date = DATE_FORMAT.format( dateValueToBeUpdated);
            query = "UPDATE `iloans` SET `paidon`='" + date + "'"
                
              + "WHERE autoid= '" + keyValue+ "' ";
        }
        
        else if(othervalue==2){
            SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:SS");
            String   date = DATE_FORMAT.format( dateValueToBeUpdated);
            
            
            query = "UPDATE `iloans` SET `paymentstatus`='" + other+ "',"
                + "`paidon`= '" + dateValueToBeUpdated+ "'"
              + "WHERE autoid= '" + keyValue+ "' ";
            
            
            
        }
      
              methods.executeSQlQueryN(query);
        
        
        
    }
    public Date addPeriod(Date from,String period,String frequency){
         Date tar=null;
        if(period.equals("Monthly")){
            
            tar=Methods.addMonth(from,Integer.valueOf(frequency));
        }
        else if (period.equals("Weekly")){
            tar=Methods.addWeek(from,Integer.valueOf(frequency));
        }
        else if (period.equals("Daily")){
            tar=Methods.addDay(from,Integer.valueOf(frequency));
        }
        else if (period.equals("Yearly")){
            tar=Methods.addYear(from,Integer.valueOf(frequency));
        }
        return tar;
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
//is ths even normal its 6am and i still havent had sleep
     public  Date addOne(Date date,int hw){
      Calendar  c= Calendar.getInstance();
      c.setTime(date);
      c.add(Calendar.DATE, hw);
   //   c.add(Calendar.WEEK_OF_MONTH, hw);
      
      return c.getTime();
  }
 public void login(){
Thread log=new Thread(){
public void run(){
    
     try{
      sleep(1000);
      a();
      
     }
      catch(Exception b){
         System.out.println("Error");
      }
} 
      }   ;  
      log.start();
  }  
    
    public void a(){
        login1 a=new login1();
        //this.setVisible(true);
        this.setEnabled(false);
        a.setVisible(true);
        
      
    }
    
   public void seten(){
        this.setEnabled(true);
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenu1 = new javax.swing.JMenu();
        jMenu13 = new javax.swing.JMenu();
        jMenuBar2 = new javax.swing.JMenuBar();
        jMenu4 = new javax.swing.JMenu();
        jMenu5 = new javax.swing.JMenu();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jToolBar1 = new javax.swing.JToolBar();
        jToolBar2 = new javax.swing.JToolBar();
        jAddCustomer = new javax.swing.JButton();
        jDeposit = new javax.swing.JButton();
        jApplication = new javax.swing.JButton();
        jProcess = new javax.swing.JButton();
        jPay = new javax.swing.JButton();
        jWithDraw = new javax.swing.JButton();
        jTransactions = new javax.swing.JButton();
        jDefaulters = new javax.swing.JButton();
        jLoans = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMo = new javax.swing.JMenu();
        jMBackUp = new javax.swing.JMenuItem();
        jMRestore = new javax.swing.JMenuItem();
        jMenuItem9 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMAddCustomers = new javax.swing.JMenuItem();
        jMViewCustomers = new javax.swing.JMenuItem();
        jMDeposit = new javax.swing.JMenuItem();
        jMWithdraw = new javax.swing.JMenuItem();
        jMViewTransactions = new javax.swing.JMenuItem();
        jMCustomersAccountsGroups = new javax.swing.JMenuItem();
        jMGroups = new javax.swing.JMenuItem();
        jMenu6 = new javax.swing.JMenu();
        jMLoanRequest = new javax.swing.JMenuItem();
        jMLoanProcessing = new javax.swing.JMenuItem();
        jMLoanReport = new javax.swing.JMenuItem();
        jMLoanDefaulters = new javax.swing.JMenuItem();
        jMLoanPayment = new javax.swing.JMenuItem();
        jMLoanPayableToday = new javax.swing.JMenuItem();
        jMLoanSettings = new javax.swing.JMenuItem();
        jMenu8 = new javax.swing.JMenu();
        jMPrintGroupCollection = new javax.swing.JMenuItem();
        jMOrgAccount = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMAddUsers = new javax.swing.JMenuItem();
        jMenu15 = new javax.swing.JMenu();
        jMSystemUserPass = new javax.swing.JMenuItem();
        jMPrefrences = new javax.swing.JMenuItem();
        jMenu11 = new javax.swing.JMenu();
        jMAbout = new javax.swing.JMenuItem();
        jMSystemHelp = new javax.swing.JMenuItem();
        jMenu9 = new javax.swing.JMenu();
        jMAddStaff = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();

        jMenu1.setText("jMenu1");

        jMenu13.setText("jMenu13");

        jMenu4.setText("File");
        jMenuBar2.add(jMenu4);

        jMenu5.setText("Edit");
        jMenuBar2.add(jMenu5);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(setTilteImage());
        setForeground(setTilteImage());
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1424, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 688, Short.MAX_VALUE)
        );

        jToolBar1.setFloatable(false);

        jToolBar2.setRollover(true);

        jAddCustomer.setBackground(setTilteImage());
        jAddCustomer.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imua/development/icons/add-user-64.png"))); // NOI18N
        jAddCustomer.setText("CUSTOMER");
        jAddCustomer.setFocusable(false);
        jAddCustomer.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jAddCustomer.setMargin(new java.awt.Insets(1, 14, 1, 14));
        jAddCustomer.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jAddCustomer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jAddCustomerActionPerformed(evt);
            }
        });
        jToolBar2.add(jAddCustomer);

        jDeposit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imua/development/icons/f4cd7501.png"))); // NOI18N
        jDeposit.setText("DEPOSIT");
        jDeposit.setBorderPainted(false);
        jDeposit.setFocusable(false);
        jDeposit.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jDeposit.setMargin(new java.awt.Insets(1, 14, 1, 14));
        jDeposit.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jDeposit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jDepositActionPerformed(evt);
            }
        });
        jToolBar2.add(jDeposit);

        jApplication.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imua/development/icons/Apply-Now-icon.png"))); // NOI18N
        jApplication.setText("APPLICATION");
        jApplication.setFocusable(false);
        jApplication.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jApplication.setMargin(new java.awt.Insets(1, 14, 1, 14));
        jApplication.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jApplication.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jApplicationActionPerformed(evt);
            }
        });
        jToolBar2.add(jApplication);

        jProcess.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imua/development/icons/Offer-Icon-Offshore-Loan.png"))); // NOI18N
        jProcess.setText("PROCESS");
        jProcess.setFocusable(false);
        jProcess.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jProcess.setMargin(new java.awt.Insets(1, 14, 1, 14));
        jProcess.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jProcess.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jProcessActionPerformed(evt);
            }
        });
        jToolBar2.add(jProcess);

        jPay.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imua/development/icons/valuable-sell-mort-icon6-1.png"))); // NOI18N
        jPay.setText("PAY");
        jPay.setFocusable(false);
        jPay.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPay.setMargin(new java.awt.Insets(1, 14, 1, 14));
        jPay.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jPay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jPayActionPerformed(evt);
            }
        });
        jToolBar2.add(jPay);

        jWithDraw.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imua/development/icons/44881817-atm-flat-icon-with-long-shadow.jpg"))); // NOI18N
        jWithDraw.setText("WITHDRAW");
        jWithDraw.setFocusable(false);
        jWithDraw.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jWithDraw.setMargin(new java.awt.Insets(1, 14, 1, 14));
        jWithDraw.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jWithDraw.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jWithDrawActionPerformed(evt);
            }
        });
        jToolBar2.add(jWithDraw);

        jTransactions.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imua/development/icons/finance_money_transaction_incoming_outgoing_coin-512.png"))); // NOI18N
        jTransactions.setText("TRANSACTIONS");
        jTransactions.setFocusable(false);
        jTransactions.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jTransactions.setMargin(new java.awt.Insets(1, 14, 1, 14));
        jTransactions.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jTransactions.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTransactionsActionPerformed(evt);
            }
        });
        jToolBar2.add(jTransactions);

        jDefaulters.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imua/development/icons/icon41.png"))); // NOI18N
        jDefaulters.setText("DEFAULTERS");
        jDefaulters.setFocusable(false);
        jDefaulters.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jDefaulters.setMargin(new java.awt.Insets(1, 14, 1, 14));
        jDefaulters.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jDefaulters.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jDefaultersActionPerformed(evt);
            }
        });
        jToolBar2.add(jDefaulters);

        jLoans.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imua/development/icons/PL-Icon.png"))); // NOI18N
        jLoans.setText("LOANS");
        jLoans.setFocusable(false);
        jLoans.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLoans.setMargin(new java.awt.Insets(1, 14, 1, 14));
        jLoans.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jLoans.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jLoansActionPerformed(evt);
            }
        });
        jToolBar2.add(jLoans);

        jLabel3.setText("jLabel3");

        jMo.setText("File");
        jMo.setMaximumSize(new java.awt.Dimension(47, 32767));

        jMBackUp.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_B, java.awt.event.InputEvent.CTRL_MASK));
        jMBackUp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imua/development/icons/Backup 2.png"))); // NOI18N
        jMBackUp.setText("Backup");
        jMBackUp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMBackUpActionPerformed(evt);
            }
        });
        jMo.add(jMBackUp);

        jMRestore.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.CTRL_MASK));
        jMRestore.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imua/development/icons/download.jpg"))); // NOI18N
        jMRestore.setText("Restore");
        jMRestore.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMRestoreActionPerformed(evt);
            }
        });
        jMo.add(jMRestore);

        jMenuItem9.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_L, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imua/development/icons/Apps-Dialog-Logout-icon.png"))); // NOI18N
        jMenuItem9.setText("Log Out");
        jMenuItem9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem9ActionPerformed(evt);
            }
        });
        jMo.add(jMenuItem9);

        jMenuItem3.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_E, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imua/development/icons/Actions-application-exit-icon.png"))); // NOI18N
        jMenuItem3.setText("System Exit");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMo.add(jMenuItem3);

        jMenuBar1.add(jMo);

        jMenu3.setText(" Accounts");
        jMenu3.setMaximumSize(new java.awt.Dimension(70, 32767));

        jMAddCustomers.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        jMAddCustomers.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imua/development/icons/add.png"))); // NOI18N
        jMAddCustomers.setText("Add  Customer");
        jMAddCustomers.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMAddCustomersActionPerformed(evt);
            }
        });
        jMenu3.add(jMAddCustomers);

        jMViewCustomers.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_V, java.awt.event.InputEvent.CTRL_MASK));
        jMViewCustomers.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imua/development/icons/customers-icon-53440.png"))); // NOI18N
        jMViewCustomers.setText("View Customers");
        jMViewCustomers.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMViewCustomersActionPerformed(evt);
            }
        });
        jMenu3.add(jMViewCustomers);

        jMDeposit.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_D, java.awt.event.InputEvent.CTRL_MASK));
        jMDeposit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imua/development/icons/deposit_icon.jpg"))); // NOI18N
        jMDeposit.setText("Deposit");
        jMDeposit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMDepositActionPerformed(evt);
            }
        });
        jMenu3.add(jMDeposit);

        jMWithdraw.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_W, java.awt.event.InputEvent.CTRL_MASK));
        jMWithdraw.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imua/development/icons/P-1-51-128.png"))); // NOI18N
        jMWithdraw.setText("Withdraw");
        jMWithdraw.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMWithdrawActionPerformed(evt);
            }
        });
        jMenu3.add(jMWithdraw);

        jMViewTransactions.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_T, java.awt.event.InputEvent.CTRL_MASK));
        jMViewTransactions.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imua/development/icons/dollar_money_bag-512.png"))); // NOI18N
        jMViewTransactions.setText("View Transactions");
        jMViewTransactions.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMViewTransactionsActionPerformed(evt);
            }
        });
        jMenu3.add(jMViewTransactions);

        jMCustomersAccountsGroups.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_G, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        jMCustomersAccountsGroups.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imua/development/icons/engineer-icon.png"))); // NOI18N
        jMCustomersAccountsGroups.setText("Customers Accounts /Groups");
        jMCustomersAccountsGroups.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMCustomersAccountsGroupsActionPerformed(evt);
            }
        });
        jMenu3.add(jMCustomersAccountsGroups);

        jMGroups.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_G, java.awt.event.InputEvent.CTRL_MASK));
        jMGroups.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imua/development/icons/32441.png"))); // NOI18N
        jMGroups.setText("Groups");
        jMGroups.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMGroupsActionPerformed(evt);
            }
        });
        jMenu3.add(jMGroups);

        jMenuBar1.add(jMenu3);

        jMenu6.setText("Loans");
        jMenu6.setMaximumSize(new java.awt.Dimension(60, 32767));
        jMenu6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu6ActionPerformed(evt);
            }
        });

        jMLoanRequest.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_R, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        jMLoanRequest.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imua/development/icons/12.23.2014-Pic-Safe-Online-Application-1024x980.jpg"))); // NOI18N
        jMLoanRequest.setText("Loan Request");
        jMLoanRequest.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMLoanRequestActionPerformed(evt);
            }
        });
        jMenu6.add(jMLoanRequest);

        jMLoanProcessing.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_P, java.awt.event.InputEvent.CTRL_MASK));
        jMLoanProcessing.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imua/development/icons/loans-icon.png"))); // NOI18N
        jMLoanProcessing.setText("Loan Processing");
        jMLoanProcessing.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMLoanProcessingActionPerformed(evt);
            }
        });
        jMenu6.add(jMLoanProcessing);

        jMLoanReport.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_L, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        jMLoanReport.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imua/development/icons/140-512.png"))); // NOI18N
        jMLoanReport.setText("Loan Report");
        jMLoanReport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMLoanReportActionPerformed(evt);
            }
        });
        jMenu6.add(jMLoanReport);

        jMLoanDefaulters.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_D, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        jMLoanDefaulters.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imua/development/icons/images (1).png"))); // NOI18N
        jMLoanDefaulters.setText("Loan Defaulters");
        jMLoanDefaulters.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMLoanDefaultersActionPerformed(evt);
            }
        });
        jMenu6.add(jMLoanDefaulters);

        jMLoanPayment.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_P, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        jMLoanPayment.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imua/development/icons/buy-gain-income-money-pay-payment-icon--19.png"))); // NOI18N
        jMLoanPayment.setText("Loan Payment");
        jMLoanPayment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMLoanPaymentActionPerformed(evt);
            }
        });
        jMenu6.add(jMLoanPayment);

        jMLoanPayableToday.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_T, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        jMLoanPayableToday.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imua/development/icons/korganizer.png"))); // NOI18N
        jMLoanPayableToday.setText("Loans Payable Today");
        jMLoanPayableToday.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMLoanPayableTodayActionPerformed(evt);
            }
        });
        jMenu6.add(jMLoanPayableToday);

        jMLoanSettings.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        jMLoanSettings.setIcon(new javax.swing.ImageIcon("C:\\Users\\kimani kogi\\Pictures\\icons\\settings.png")); // NOI18N
        jMLoanSettings.setText("Loan Settings");
        jMLoanSettings.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMLoanSettingsActionPerformed(evt);
            }
        });
        jMenu6.add(jMLoanSettings);

        jMenuBar1.add(jMenu6);

        jMenu8.setText("Admin");
        jMenu8.setMaximumSize(new java.awt.Dimension(60, 32767));

        jMPrintGroupCollection.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_P, java.awt.event.InputEvent.ALT_MASK));
        jMPrintGroupCollection.setIcon(new javax.swing.ImageIcon("C:\\Users\\kimani kogi\\Pictures\\icons\\gnome_dev_printer.png")); // NOI18N
        jMPrintGroupCollection.setText("Print Group Collection");
        jMPrintGroupCollection.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMPrintGroupCollectionActionPerformed(evt);
            }
        });
        jMenu8.add(jMPrintGroupCollection);

        jMOrgAccount.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.ALT_MASK));
        jMOrgAccount.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imua/development/icons/icon_lrg_performance.png"))); // NOI18N
        jMOrgAccount.setText("Business Account Transaction Records");
        jMOrgAccount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMOrgAccountActionPerformed(evt);
            }
        });
        jMenu8.add(jMOrgAccount);

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_B, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem1.setText("Bussines Account");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu8.add(jMenuItem1);

        jMAddUsers.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_U, java.awt.event.InputEvent.ALT_MASK));
        jMAddUsers.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imua/development/icons/add.png"))); // NOI18N
        jMAddUsers.setText("Add Users");
        jMAddUsers.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMAddUsersActionPerformed(evt);
            }
        });
        jMenu8.add(jMAddUsers);

        jMenuBar1.add(jMenu8);

        jMenu15.setText("Settings");
        jMenu15.setMaximumSize(new java.awt.Dimension(60, 32767));

        jMSystemUserPass.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_U, java.awt.event.InputEvent.SHIFT_MASK));
        jMSystemUserPass.setIcon(new javax.swing.ImageIcon("C:\\Users\\kimani kogi\\Pictures\\icons\\edit.png")); // NOI18N
        jMSystemUserPass.setText("System Users Pass");
        jMSystemUserPass.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMSystemUserPassActionPerformed(evt);
            }
        });
        jMenu15.add(jMSystemUserPass);

        jMPrefrences.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_P, java.awt.event.InputEvent.SHIFT_MASK));
        jMPrefrences.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imua/development/icons/rt.png"))); // NOI18N
        jMPrefrences.setText("Prefrences");
        jMPrefrences.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMPrefrencesActionPerformed(evt);
            }
        });
        jMenu15.add(jMPrefrences);

        jMenuBar1.add(jMenu15);

        jMenu11.setText("Help");
        jMenu11.setMaximumSize(new java.awt.Dimension(60, 32767));

        jMAbout.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        jMAbout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imua/development/icons/1474675499_about.png"))); // NOI18N
        jMAbout.setText("About");
        jMAbout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMAboutActionPerformed(evt);
            }
        });
        jMenu11.add(jMAbout);

        jMSystemHelp.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_H, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        jMSystemHelp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imua/development/icons/Help.png"))); // NOI18N
        jMSystemHelp.setText("System Help");
        jMSystemHelp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMSystemHelpActionPerformed(evt);
            }
        });
        jMenu11.add(jMSystemHelp);

        jMenuBar1.add(jMenu11);

        jMenu9.setText("Staff");
        jMenu9.setMaximumSize(new java.awt.Dimension(60, 32767));

        jMAddStaff.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imua/development/icons/add.png"))); // NOI18N
        jMAddStaff.setText("Add");
        jMenu9.add(jMAddStaff);

        jMenuBar1.add(jMenu9);

        jMenu2.setText("Tools");

        jMenuItem2.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_M, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem2.setText("Calculator");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem2);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                .addComponent(jToolBar2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jToolBar2, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(42, Short.MAX_VALUE))
        );

        jToolBar2.add(Box.createHorizontalGlue());
        jToolBar2.add(Box.createHorizontalStrut(20));
        jToolBar2.add(txttymer);
        JLabel txttymerb=   new JLabel("                        ");
        jToolBar2.add(txttymerb);

        pack();
    }// </editor-fold>//GEN-END:initComponents
                                 
        // TODO add your handling code here:
      
    
     public void pic(){
         BufferedImage d;
    try {
         Container cont=this.getContentPane();
            //;
        d = ImageIO.read(Main.class.getResource("/imua/development/Wall.jpg"));
          Image f=d.getScaledInstance(jLabel1.getWidth(), jLabel1.getHeight(),Image.SCALE_SMOOTH);
         // 
        
       jLabel1.setIcon(new ImageIcon(f));
    } catch (IOException ex) {
        Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
    }       
    }
//    public class ImagePanel extends jPanel2
//    {
//        
//    }
String id;
String loanid;
 String loantype;
String applicable;
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
    public int getAllFromLoan(String where,int value){
   // String getMaxId="SELECT MAX(autoid) FROM `loans` WHERE `" + where + "` = '" + value + "'";
   int a=0;
    try {
            Methods m = new Methods();
            Connection con = m.getConnection();
            Statement st = con.createStatement();
            String checkpay="0";
            String searchQuery = "SELECT * FROM `loans` WHERE `" + where + "` = '" + value + "' ";
            ResultSet rs = st.executeQuery(searchQuery);
            while (rs.next()) {
 loanid=rs.getString("loanid");
 id=rs.getString("id");
loantype=rs.getString("loantype");
applicable=rs.getString("applicable");
installmentamount=rs.getString("installmentamount");
installmentsno=rs.getString("installmentsno");
periodtype=rs.getString("periodtype");
frequencyperperiod=rs.getString("frequencyperperiod");
targetdate=rs.getDate("targetdate");
loanAmount=rs.getString("loanAmount");
loanRequested=rs.getString("loanRequested");
loanbalance=rs.getString("loanbalance");
defaultacc=rs.getString("defaultacc");
todaypay=rs.getString("todaypay");
 givenOn=rs.getString("givenOn");
paidon=rs.getString("paidon");
            //lonely a    
              a=1;
              
               
                
//                autoid, 
//                id, loantype, installmentamount,
//                installmentsno, periodtype, frequencyperperiod,
//                targetdate, loanAmount, loanbalance, 
//                defaultacc, applicationfee, todaypay,
//                givenOn, paidon
            }
      st.close();
      rs.close();
      con.close();
    }
    catch(Exception m){
        m.printStackTrace();
    }

    
    
    return a;
    
    
}
    public void setNewTarget(String where,String val,String defaultAmount,Double defaultValue,String tp,Double mLoanBalance){
     
        
      
       
        
        if(getAllFromLoan( where, Integer.valueOf(val))==1){
                Date tar=null;
        if(periodtype.equals("Monthly")){
            
            tar=Methods.addMonth(targetdate,Integer.valueOf(frequencyperperiod));
        }
        else if (periodtype.equals("Weekly")){
            tar=Methods.addWeek(targetdate,Integer.valueOf(frequencyperperiod));
        }
        else if (periodtype.equals("Daily")){
            tar=Methods.addDay(targetdate,Integer.valueOf(frequencyperperiod));
        }
        else if (periodtype.equals("Yearly")){
            tar=Methods.addYear(targetdate,Integer.valueOf(frequencyperperiod));
        }
        double dv=Double.valueOf(installmentamount);
        if(defaultValue>1){
           dv=defaultValue;
           
       }
        SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:SS");
        String   date = DATE_FORMAT.format(tar);
        String nulll="null";
        String nxp="np";
                   String query = "INSERT INTO loans("
             + "`id`,"
             + "`loanid`,"
             
             + "`loantype`,"
             +"`applicable`,"
             + " `installmentamount`"
             + ",`installmentsno`"
             + ",`periodtype`"
             + ",`frequencyperperiod`"
             + ",`targetdate`"
             + ",`loanAmount`"
              + ",`loanRequested`"
             + ",`loanbalance`"
             + ",`defaultacc`"
             + ",`applicationfee`"
             + ",`todaypay`"
             + ",`nxp`"
             + ",`tp`"
             + ",`givenOn`"
             + ",`paidon`)"
             + " VALUES ("
             + "'" + this.id+ "'"
             + ",'" + this.loanid+ "'"
             + ",'" + loantype+ "'"
             + ",'" + this.applicable+ "'"
             + ",'" + this.installmentamount + "'"
             + ",'" +String.valueOf((Integer.valueOf(installmentsno)-1))+"'"
             + ",'" + this.periodtype + "'"
             + ",'" + frequencyperperiod+ "'"
                   
            + ",'" + date + "'"
             + ",'"+loanAmount+"'"
             + ",'"+loanRequested+"'"
               
             + ",'"+String.valueOf(mLoanBalance)+"'"
             + ",'"+defaultAmount+"'"
             + ",'"+this.applicationfee+"'"
             + ",'" +String.valueOf( dv)+ "'"
             + ",'" + nxp+ "'"
             + ",'" + tp+ "'"
             + ",'" + this.givenOn + "'"
           
             + ",now())";
                   m.executeSQlQueryN(query);
      // String fee=   JOptionPane.showInputDialog("Enter application fee");
     //  Double bal=Double.valueOf(fee)-Double.valueOf(appfe);
       
      // JOptionPane.showMessageDialog(null, "Balance is " +bal.toString());
       
//    if(  m.executeSQlQuery(query, "Processed\n NEXT TARGET DAY IS ON \n "+date +"  \n"+mess)==1) {
//        m.addToOrgAccount(Double.valueOf(txtPaying.getText()),"Loan Installments" );
//        
//        clear();
//    }
        
    }
    
    
    
//    if(Double.valueOf(loanbalance)-(mLoanBalance) <=1 &&(tp.equals("paid")||tp.equals("pm"))){
//        loanpayment lp=new loanpayment()  ;
//        lp.checkWhetherHasOutStandingFines("",this.loanid);
//        } 
//     else{
//    out.println("has defaults");
//        }
    
    
    }
    
    
    //////////////////////////////////////
    //i must be a genios
    /////////////////////////////////////
    
    
    //its 5 am eric you going to die
    
    
    
    public void timerForLoanApplication(){
        Thread checkLoan=new Thread(){
            public void run(){
    String x;
    String z;
for(;;){
//do my checking in here    
 checkLoanApplications1();
try{
sleep(5000);
}
catch(InterruptedException ex){

}
}
}  
            
        };
        checkLoan.start();
    }
    
public  void getTime(){

Thread clock=new Thread(){
public void run(){
    
    String x;
    String z;
for(;;){
Calendar cal=new GregorianCalendar();
        int month=cal.get(Calendar.MONTH)+1;
        switch (month) {
            case 1:
                z="Jan";
                break;
            case 2:
                z="Feb";
                break;
            case 3:
                z="Mar";
                break;
            case 4:
                z="Apr";
                break;
            case 5:
                z="May";
                break;
            case 6:
                z="June";
                break;
            case 7:
                z="July";
                break;
            case 8:
                z="Aug";
                break;
            case 9:
                z="Sept";
                break;
            case 10:
                z="Oct";
                break;
            case 11:
                z="Nov";
                break;
            default:
                z="Dec";
                break;
        }
        
        int year=cal.get(Calendar.YEAR);
        int day=cal.get(Calendar.DAY_OF_MONTH);
        
        int pmam=cal.get(Calendar.AM_PM);
       if(pmam==1){
           x="PM";
       }
       else{
            x="AM";
       }
        int second=cal.get(Calendar.SECOND);
        int min=cal.get(Calendar.MINUTE);
        if(min==1){
            
           //x="PM";
       }
       else{
           // x="AM";
       }
        int hour=cal.get(Calendar.HOUR);
     //   checkLoanApplications1();//i did this in another thread to change the time interval 
        txttymer.setFont(new java.awt.Font("Tahoma", 1, 16));
       
        Border pb=BorderFactory.createEmptyBorder(0, 10, 30, 10);
         txttymer.setBorder(BorderFactory.createCompoundBorder(pb, pb));
         
        txttymer.setText("DATE:   "+day+" "+z+" "+year+"   TIME:  "+hour+":"+min+":"+second+":"+x);
        if(a>0){
            setEnabled();
            a=0;
        }
         if(ch==1){
             
          checkTodays();
          timerForLoanApplication();
          processGhash(hash);
           
          ch=0;
       }
try{
sleep(1000);
}
catch(InterruptedException ex){

}
}
}
};
clock.start();    



}
//jLabel2 = new javax.swing.JLabel();

//jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11));
 JLabel txttymer=   new JLabel();
      //  this.txttymer.setFont(new java.awt.Font("Tahoma", 1, 11));
 //txttymer.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
 
 MarqueePanel mp= new MarqueePanel(s, 200);
public void b(String h){
    
    mp= new MarqueePanel(h, 250);
    
     
      // MarqueePanel mp = new MarqueePanel(s, 100);
        jToolBar1. remove(mp);
        jToolBar1. add(mp);
        mp.start();
}
 private  void checkLoanApplications1(){
     //out.println("checking");
      String  s="i";
     String mesoLoan= "";//checkLoanRepayments1();
             //  Calendar  c= Calendar.getInstance();
         HashMap<String,String>todays=new HashMap<String,String>();
//      Date today=c.getTime();
//       java.util.Date d=(today);
//     
//     java.sql.Date DATE=new java.sql.Date(d.getTime());
      try {
          Methods n = new Methods();
          
          Connection con =n. getConnection();
          
          Statement st = con.createStatement();
          String searchQuery = "SELECT * FROM `applications` ";
          ResultSet rs = st.executeQuery(searchQuery);
          while (rs.next())
          {
              
           todays.put(rs.getString("id"), rs.getString("amount"));
            
          }
          st.close();
          rs.close();
          con.close();
          
          if(todays.size()>0){
              jToolBar1.setVisible(true);
             s=""+todays.size()+ " LOANS APPLICATIONS AVAILABLE FOR PROCESSING";
            
        } 
          else{
              jToolBar1.setVisible(false);
           //  MarqueePanel mp = new MarqueePanel(s, 100); 
            mp.stop();
             jToolBar1.remove(mp);
             mp.stop();
          }
          //ime blink araka sana
          if(!"".equals(mesoLoan)){
             //  jToolBar1.setVisible(true);
          }
        
//              
 //JOptionPane.showMessageDialog(null, todays.size()+ "LOANS APPLICATIONS AVAILABLE");
//          }
//mp.stop();
//MarqueePanel mp = new MarqueePanel(s, 100);
//jToolBar1.remove(mp);
//b();
          //checkLoanRepayments();
          
      } catch (SQLException ex) {
          Logger.getLogger(ProcessLoan.class.getName()).log(Level.SEVERE, null, ex);
      }
      if("".equals(mesoLoan)){
           String mesoYote=s;
      b(mesoYote);
      }
      else{
      String mesoYote=mesoLoan+""+s;
      b(mesoYote);
      }
    }
//     public  String checkLoanRepayments1(){
//       String meso="";
//         HashMap<String,String>todays=new HashMap<String,String>();
//           Calendar  c= Calendar.getInstance();
//         Date today=addOne(c.getTime(),-1);
//         
//         java.util.Date d=(today);
//  //Days d=Days.daysBeetween();
//         java.sql.Date DATE=new java.sql.Date(d.getTime());
//      try {
//          Methods n = new Methods();
//          String nl="np";
//          Connection con =n. getConnection();
//          
//          Statement st = con.createStatement();
//          String searchQuery = "SELECT * FROM `loans`WHERE targetdate<'" + DATE + "'AND nxp='"+nl+"' ";
//          ResultSet rs = st.executeQuery(searchQuery);
//          while (rs.next())
//          {   String maxid=rs.getString("autoid");
//              String inNo=rs.getString("installmentsno");
//             String id= rs.getString("id");
//             String loanType= rs.getString("loantype");
//             String loanId= rs.getString("loanid");
//             
//              checkIfThereIsNext(maxid,id,inNo,loanType,loanId);
//              todays.put(rs.getString("id"), rs.getString("installmentamount"));
//            
//          }
//          st.close();
//          rs.close();
//          con.close();
//          
//          if(todays.size()>0){
//              
//              meso=""+todays.size()+ "  LOANS DEFAULTED";
//              
//              
////        int dialogButton=
////        
////        JOptionPane.showConfirmDialog(null,todays.size()+ "  LOANS DEFAULTED \n Select YES to check them out ");
////        if(dialogButton==JOptionPane.YES_OPTION){
////            
////                    Defaulters kd=new Defaulters();
////         //a.typeoftransaction="withdrawal";
////                     kd.setVisible(true);
////        }
//          //JOptionPane.showMessageDialog(null, todays.size()+ "  LOANS DEFAULTED ");
//          }
//          //checkLoanRepayments();
//          
//      } catch (SQLException ex) {
//          Logger.getLogger(ProcessLoan.class.getName()).log(Level.SEVERE, null, ex);
//      }
//      return meso;
//    }
//set the windows enabled ,called in the method getTime at 1 sec interval if int a <1;
  public void setEnabled(){
      this.setEnabled(true);
      this.setVisible(true);
  }
    
 public void openNewAccount(){
     this.setEnabled(false);
      AccountRegistration a=new AccountRegistration();
        a.setVisible(true);
 }   
    
    public void openDeposit(){
        this.setEnabled(false);
        Deposit a=new Deposit();
//        a.with="withdraw";
      a.typeoftransaction="deposits";
        a.setTitle("Customer Deposit Information Form","Deposit");
        //a.title="Customer Deposit Information Form";
        a.setVisible(true);
    }
    public void openWithdrawal(){
        this.setEnabled(false);
        Deposit a=new Deposit();
//        a.with="withdraw";
       a.typeoftransaction="withdrawal";
        a.setTitle("Customer WithDraw Information Form","Withdraw");
      //  a.title="Customer Deposit Information Form";
        a.setVisible(true);
    }
    public void openLoanForm(){
        this.setEnabled(false);
        loanform a=new loanform();
         //a.typeoftransaction="withdrawal";
        a.setVisible(true);
    }
     public void openLoanProcessing(){
        this.setEnabled(false);
        IProcessLoan a=new IProcessLoan();
         //a.typeoftransaction="withdrawal";
        a.setVisible(true);
    }
//      public static void openLoanProcessings(){
//        this.setEnabled(false);
//        ProcessLoan a=new ProcessLoan();
//         //a.typeoftransaction="withdrawal";
//        a.setVisible(true);
//    }
    public void openWithdrawForm(){
        openWithdrawal();
    }
    public void openLoanWithSavings(){
         LoanWithSavings a= new LoanWithSavings();
       a.setVisible(true);
    }
    public void openLoanWithoutSavings(){
         LoanWithoutSavings a= new LoanWithoutSavings();
       a.setVisible(true);
    }    
    public void openAccountsReport(){
            AccountsReport a= new AccountsReport();
       a.setVisible(true);
    }
    public void openPersonalReport(){
    Personalreport a= new Personalreport();
       a.setVisible(true);
    }
    
    
    
    
    
    private void jMBackUpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMBackUpActionPerformed
backup b=new backup() ;
b.setVisible(true);

    }//GEN-LAST:event_jMBackUpActionPerformed

    private void jMRestoreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMRestoreActionPerformed
      Restore r=new Restore() ;
     r.setVisible(true);
    }//GEN-LAST:event_jMRestoreActionPerformed

    private void jMAddCustomersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMAddCustomersActionPerformed
        // TODO add your handling code here:
        openNewAccount();
    }//GEN-LAST:event_jMAddCustomersActionPerformed

    private void jMLoanRequestActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMLoanRequestActionPerformed
     openLoanForm();
              
         
    }//GEN-LAST:event_jMLoanRequestActionPerformed

    private void jMenu6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu6ActionPerformed
     
        Iloanpayment a=new Iloanpayment();
        a.setVisible(true);
        
    }//GEN-LAST:event_jMenu6ActionPerformed

    private void jMLoanPaymentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMLoanPaymentActionPerformed
        Iloanpayment a=new Iloanpayment();
        a.setVisible(true);
       
    }//GEN-LAST:event_jMLoanPaymentActionPerformed

    private void jMDepositActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMDepositActionPerformed
       
      openDeposit();
    }//GEN-LAST:event_jMDepositActionPerformed

    private void jMWithdrawActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMWithdrawActionPerformed
     openWithdrawForm();
    }//GEN-LAST:event_jMWithdrawActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
       System.exit(1);
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem9ActionPerformed
        a();
        a=0;
        this.setEnabled(false);
    }//GEN-LAST:event_jMenuItem9ActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // TODO add your handling code here:
    }//GEN-LAST:event_formWindowOpened

    private void jMCustomersAccountsGroupsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMCustomersAccountsGroupsActionPerformed
      //  this.setEnabled(false);
        Accgroups m = new Accgroups();
        m.setVisible(true);
    }//GEN-LAST:event_jMCustomersAccountsGroupsActionPerformed

    private void jMSystemUserPassActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMSystemUserPassActionPerformed
       // this.set Enabled(false);
        changepass n =new changepass();
        
        n.setVisible(true);
    }//GEN-LAST:event_jMSystemUserPassActionPerformed

    private void jMPrintGroupCollectionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMPrintGroupCollectionActionPerformed
        GroupSheet gs=new GroupSheet();
        gs.setVisible(true);
    }//GEN-LAST:event_jMPrintGroupCollectionActionPerformed

    private void jAddCustomerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jAddCustomerActionPerformed
         openNewAccount(); 
    }//GEN-LAST:event_jAddCustomerActionPerformed

    private void jDepositActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jDepositActionPerformed
        openDeposit();
    }//GEN-LAST:event_jDepositActionPerformed

    private void jApplicationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jApplicationActionPerformed
        openLoanForm();
    }//GEN-LAST:event_jApplicationActionPerformed

    private void jProcessActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jProcessActionPerformed
        openLoanProcessing();    
    }//GEN-LAST:event_jProcessActionPerformed

    private void jPayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jPayActionPerformed
System.out.println("jj");
        Iloanpayment a=new Iloanpayment();
        a.setVisible(true);
    }//GEN-LAST:event_jPayActionPerformed

    private void jWithDrawActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jWithDrawActionPerformed
        openWithdrawForm();
    }//GEN-LAST:event_jWithDrawActionPerformed

    private void jTransactionsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTransactionsActionPerformed
         openPersonalReport();
    }//GEN-LAST:event_jTransactionsActionPerformed

    private void jDefaultersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jDefaultersActionPerformed
          IDefaulters d=new IDefaulters();
        d.setVisible(true);
    }//GEN-LAST:event_jDefaultersActionPerformed

    private void jLoansActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jLoansActionPerformed
      LoanHistory h=new LoanHistory();
      h.setVisible(true);
    }//GEN-LAST:event_jLoansActionPerformed

    private void jMViewCustomersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMViewCustomersActionPerformed
         Users u=new Users();
       u.setVisible(true);
    }//GEN-LAST:event_jMViewCustomersActionPerformed

    private void jMViewTransactionsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMViewTransactionsActionPerformed
         openPersonalReport();
    }//GEN-LAST:event_jMViewTransactionsActionPerformed

    private void jMLoanPayableTodayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMLoanPayableTodayActionPerformed
        TodaysLoans tl=new TodaysLoans();
       tl.setVisible(true);
    }//GEN-LAST:event_jMLoanPayableTodayActionPerformed

    private void jMOrgAccountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMOrgAccountActionPerformed
        OrgReport or=new OrgReport();
       or.setVisible(true);
    }//GEN-LAST:event_jMOrgAccountActionPerformed

    private void jMPrefrencesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMPrefrencesActionPerformed
       Settings s=new Settings();
       s.setVisible(true);
    }//GEN-LAST:event_jMPrefrencesActionPerformed

    private void jMAddUsersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMAddUsersActionPerformed
      AddUsers au=new AddUsers();
      au.setVisible(true);
      
    }//GEN-LAST:event_jMAddUsersActionPerformed

    private void jMAboutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMAboutActionPerformed
        about au=new about();
      au.setVisible(true);
    }//GEN-LAST:event_jMAboutActionPerformed

    private void jMSystemHelpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMSystemHelpActionPerformed
         try
    {
      String url = "help.html";
      Desktop.getDesktop().browse(URI.create(url));
    }
    catch (IOException e1)
    {
       JOptionPane.showMessageDialog(null, "NOT AVAILABLE");
    }
    }//GEN-LAST:event_jMSystemHelpActionPerformed

    private void jMLoanSettingsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMLoanSettingsActionPerformed
        loantypes lt=new loantypes();
        lt.setVisible(true);
    }//GEN-LAST:event_jMLoanSettingsActionPerformed

    private void jMGroupsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMGroupsActionPerformed
       GroupView m = new  GroupView();
        m.setVisible(true);
    }//GEN-LAST:event_jMGroupsActionPerformed

    private void jMLoanProcessingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMLoanProcessingActionPerformed
        openLoanProcessing();  
    }//GEN-LAST:event_jMLoanProcessingActionPerformed

    private void jMLoanReportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMLoanReportActionPerformed
 LoanHistory h=new LoanHistory();
      h.setVisible(true);     
    }//GEN-LAST:event_jMLoanReportActionPerformed

    private void jMLoanDefaultersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMLoanDefaultersActionPerformed
 IDefaulters d=new IDefaulters();
        d.setVisible(true);        
    }//GEN-LAST:event_jMLoanDefaultersActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
       MainOrgAccount ma=new MainOrgAccount();
       ma.setVisible(true);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        Calc n=new Calc();
       
       
    }//GEN-LAST:event_jMenuItem2ActionPerformed
public static void ex(){
        Calendar today = Calendar.getInstance();
Calendar expires = Calendar.getInstance();
expires.set(2017, 7, 15,0,0,0);
if(today.after(expires)) {

                JOptionPane.showMessageDialog(null,"This software is expired..\n"
                        + "contact 0714406984\n"
                        + "0r erickogi14@gmail.com\n"
                        + "or vist erickogi.co.ke\n");

  System.err.println("This software is expired.");
  System.exit(1);
}
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        //ex();
       /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                //String on;
                
                new Main().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jAddCustomer;
    private javax.swing.JButton jApplication;
    private javax.swing.JButton jDefaulters;
    private javax.swing.JButton jDeposit;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JButton jLoans;
    private javax.swing.JMenuItem jMAbout;
    private javax.swing.JMenuItem jMAddCustomers;
    private javax.swing.JMenuItem jMAddStaff;
    private javax.swing.JMenuItem jMAddUsers;
    private javax.swing.JMenuItem jMBackUp;
    private javax.swing.JMenuItem jMCustomersAccountsGroups;
    private javax.swing.JMenuItem jMDeposit;
    private javax.swing.JMenuItem jMGroups;
    private javax.swing.JMenuItem jMLoanDefaulters;
    private javax.swing.JMenuItem jMLoanPayableToday;
    private javax.swing.JMenuItem jMLoanPayment;
    private javax.swing.JMenuItem jMLoanProcessing;
    private javax.swing.JMenuItem jMLoanReport;
    private javax.swing.JMenuItem jMLoanRequest;
    private javax.swing.JMenuItem jMLoanSettings;
    private javax.swing.JMenuItem jMOrgAccount;
    private javax.swing.JMenuItem jMPrefrences;
    private javax.swing.JMenuItem jMPrintGroupCollection;
    private javax.swing.JMenuItem jMRestore;
    private javax.swing.JMenuItem jMSystemHelp;
    private javax.swing.JMenuItem jMSystemUserPass;
    private javax.swing.JMenuItem jMViewCustomers;
    private javax.swing.JMenuItem jMViewTransactions;
    private javax.swing.JMenuItem jMWithdraw;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu11;
    private javax.swing.JMenu jMenu13;
    private javax.swing.JMenu jMenu15;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenu jMenu8;
    private javax.swing.JMenu jMenu9;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuBar jMenuBar2;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem9;
    private javax.swing.JMenu jMo;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JButton jPay;
    private javax.swing.JButton jProcess;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JToolBar jToolBar2;
    private javax.swing.JButton jTransactions;
    private javax.swing.JButton jWithDraw;
    // End of variables declaration//GEN-END:variables

    public  void processGhash(String Ghash){
   
        if(Ghash.length()==22){
       // sellectAll();
    }
    else{
       deactivateButtons();
        for(int a=0;a<Ghash.length();a++){
            if(Ghash.charAt(a)=='A'){
                //JOptionPane.showMessageDialog(null, Ghash.charAt(a));
             jMBackUp.setEnabled(true);
           
              
            }
            else if(Ghash.charAt(a)=='B'){
                jMRestore.setEnabled(true);
               
            }
            else if(Ghash.charAt(a)=='C'){
                
            }
            else if(Ghash.charAt(a)=='D'){
                
            }
            else if(Ghash.charAt(a)=='E'){
               jMAddCustomers.setEnabled(true);
               jAddCustomer.setEnabled(true);
            }
            else if(Ghash.charAt(a)=='F'){
                jMViewCustomers.setEnabled(true);
            }
            else if(Ghash.charAt(a)=='G'){
               jMDeposit.setEnabled(true);
                jDeposit.setEnabled(true);
            }
            else if(Ghash.charAt(a)=='H'){
                jMWithdraw.setEnabled(true);
                jWithDraw.setEnabled(true);
            }
            else if(Ghash.charAt(a)=='I'){
                jMViewTransactions.setEnabled(true);
                jTransactions.setEnabled(true);
            }
            else if(Ghash.charAt(a)=='J'){
               jMCustomersAccountsGroups.setEnabled(true);
            }
            else if(Ghash.charAt(a)=='K'){
                 jMGroups.setEnabled(true);
            }
            else if(Ghash.charAt(a)=='L'){
                jMLoanRequest.setEnabled(true);
                jApplication.setEnabled(true);
            }
            else if(Ghash.charAt(a)=='M'){
              jMLoanProcessing.setEnabled(true);
                jProcess.setEnabled(true);
            }
            else if(Ghash.charAt(a)=='N'){
               jMLoanReport.setEnabled(true);
               jLoans.setEnabled(true);
            }
            else if(Ghash.charAt(a)=='O'){
               jMLoanDefaulters.setEnabled(true);
               jDefaulters.setEnabled(true);
            }
            else if(Ghash.charAt(a)=='P'){
               jMLoanPayment.setEnabled(true);
               jPay.setEnabled(true);
            }
            else if(Ghash.charAt(a)=='Q'){
              jMLoanPayableToday.setEnabled(true);
            }
            else if(Ghash.charAt(a)=='R'){
             jMLoanSettings.setEnabled(true);
            }
            else if(Ghash.charAt(a)=='S'){
             jMAddStaff.setEnabled(true);
            }
            else if(Ghash.charAt(a)=='T'){
             jMPrintGroupCollection.setEnabled(true);
            }
            else if(Ghash.charAt(a)=='U'){
             jMOrgAccount.setEnabled(true);
             jMenuItem1.setEnabled(true);
            }
            else if(Ghash.charAt(a)=='V'){
             jMAddUsers.setEnabled(true);
            }
            else if(Ghash.charAt(a)=='W'){
              jMSystemUserPass.setEnabled(true);
            }
            else if(Ghash.charAt(a)=='X'){
             jMPrefrences.setEnabled(true);
                
            }
        }
    }
    
    
}
    public void deactivateButtons(){
                 jMBackUp.setEnabled(false);
         jMRestore.setEnabled(false);
         jMAddCustomers.setEnabled(false);
         jAddCustomer.setEnabled(false);
         jMViewCustomers.setEnabled(false);
         jMDeposit.setEnabled(false);
        jDeposit.setEnabled(false);
         jMWithdraw.setEnabled(false);
         jWithDraw.setEnabled(false);
          jMViewTransactions.setEnabled(false);
          jTransactions.setEnabled(false);
           jMCustomersAccountsGroups.setEnabled(false);
            jMGroups.setEnabled(false);
            jMLoanRequest.setEnabled(false);
            jApplication.setEnabled(false);
            jMLoanProcessing.setEnabled(false);
                jProcess.setEnabled(false);
                  jMLoanReport.setEnabled(false);
               jLoans.setEnabled(false);
                jMLoanDefaulters.setEnabled(false);
               jDefaulters.setEnabled(false);
               jMLoanPayment.setEnabled(false);
               jPay.setEnabled(false);
                jMLoanPayableToday.setEnabled(false);
                jMLoanSettings.setEnabled(false);
                 jMAddStaff.setEnabled(false);
                  jMPrintGroupCollection.setEnabled(false);
                  jMOrgAccount.setEnabled(false);
                   jMAddUsers.setEnabled(false);
                     jMSystemUserPass.setEnabled(false);
                      jMPrefrences.setEnabled(false);
                      jMenuItem1.setEnabled(false);
    }
}
class MarqueePanel extends JPanel implements ActionListener {
///////////////////////////////////////////////////////////////
    private static final int RATE = 12;                       //
    private final Timer timer = new Timer(1000 / RATE, this); //
    private final JLabel label = new JLabel();                //  
    private final String s;                                   //SWEEEEEEEEEEEEEET
                                                              //
    private final int n;                                       //
    private int index;

    public MarqueePanel(String s, int n) {
        if (s == null || n < 1) {
            throw new IllegalArgumentException("Null string or n < 1");
        }
        StringBuilder sb = new StringBuilder(n);
        for (int i = 0; i < n; i++) {
            sb.append(' ');
        }
        this.s = sb + s + sb;
        this.n = n;
        label.setFont(new Font("Serif", Font.ITALIC, 20));
        label.setBackground(Color.red);
        label.setText(sb.toString());
        this.add(label);
    }

    public void start() {
        timer.start();
    }

    public void stop() {
        timer.stop();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        index++;
        if (index > s.length() - n) {
            index = 0;
        }
        label.setText(s.substring(index, index + n));
    }
    ///////////////////////////////////////////////////////////////////////
    

}
///  1464 LINES