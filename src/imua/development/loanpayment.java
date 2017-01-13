/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imua.development;

import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Kamau
 */
public class loanpayment extends javax.swing.JFrame {
  private String typeOfAccount=null;
    String toLoan[];
     String accounts[];
    /**
     * Creates new form loan
     */
    public loanpayment() {
        initComponents();
        setTilteImage();
        findLoans();
        accountsTypes();
    }
    private void setTilteImage(){
     Methods n=new Methods();
    String t= n.setTitle();
    this.setTitle(t);
    String i=n.setIconImage();
    this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource(i)));
}
    
         public void findLoans()
  {
      Methods m=new Methods();
    ArrayList<String> usersList =  m.ListLoanTypes();
   accounts=new String[usersList.size()];
   usersList.toArray(accounts);
  }
     private void accountsTypes() {
      //  = {"Regular Acc", "Projects Acc", "Table Acc"};
        toLoan = accounts;
        setAccountModel();
    }
      private void setAccountModel() {
        jComboBox1.removeAllItems();
        jComboBox1.addItem("choose");
        for (int a = 0; a < toLoan.length; a++) {

            jComboBox1.addItem(toLoan[a]);
        }
    }
    
    private String fetchBalance(String where, String value) {
        String balance = "0";
        try {
            Methods m = new Methods();
            Connection con = m.getConnection();
            Statement st = con.createStatement();
            Statement st1 = con.createStatement();
           
//fetches total deposits
            String searchQuery = "SELECT SUM(todaypay) FROM `loans` WHERE `" + where + "` ="
                    + " '" + value + "'";
//fetch total withdrawals
            String searchQuery2 = "SELECT SUM(loanAmount) FROM `loans` WHERE `" + where + "` ="
                    + " '" + value + "'";

            ResultSet rs = st.executeQuery(searchQuery);

            ResultSet rs1 = st1.executeQuery(searchQuery2);

            if (rs.next()&& rs1.next())
            {
 
                double a = 0;
                double b = 0;

                try 
                {
//subtracts withdrawals from total deposits to get current account balance
                    if(rs1.getString(1)==null&&rs.getString(1)==null){
                          JOptionPane.showMessageDialog(null, "Has no LOAN");
                    }
                    else{
                        txtloan.setText(rs1.getString(1));
                    a = Double.valueOf(rs1.getString(1));
                    b = Double.valueOf(rs.getString(1));
                   // JOptionPane.showMessageDialog(null,a+ " "+b);
                    balance = String.valueOf(a - b);
                    }

                } 
                catch (Exception n) 
                {

                    balance = rs.getString(1);

                }
            } 
            else if (rs.next()) 
            {
               JOptionPane.showMessageDialog(null,"rs ");
                balance = rs.getString(1);
            } 
            else 
            {
                JOptionPane.showMessageDialog(null,"no");
//if no deposits found in rs query excution
                JOptionPane.showMessageDialog(null, "Has no deposit");
            }
            st.close();
            st1.close();
            rs.close();
            rs1.close();
            con.close();

        } catch (SQLException ex) {

            JOptionPane.showMessageDialog(null, ex);
            Logger.getLogger(Deposit.class.getName()).log(Level.SEVERE, null, ex);
        }

        return balance;
    }
     public Double   fetchUserDetails(String value, String where) {
Double  balance=0.0;
        try {
            Methods m = new Methods();
            Connection con = m.getConnection();
            Statement st = con.createStatement();
            String searchQuery = "SELECT * FROM `users` WHERE `" + where + "` = '" + value + "'";
            ResultSet rs = st.executeQuery(searchQuery);
            while (rs.next()) {
                txtSirname.setText(rs.getString("fname"));
                txtOtherName.setText(rs.getString("sname"));
                txtMobile.setText(rs.getString("phone"));
              //  txtAddress.setText(rs.getString("address"));
                txtTown.setText(rs.getString("town"));
              //  txtAccountType.setText(rs.getString("acounttype"));
                txtCounty.setText(rs.getString("county"));
                txtOccupation.setText(rs.getString("occupation"));

                try {
                   
//call fetchbalance method and give it the same parameters used to fetch user details
                       balance =Double.valueOf(fetchBalance(where, value)) ;
                      
                       
                    //set txtBalance to the returned value
                    

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex);
                    Logger.getLogger(Deposit.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            st.close();
            rs.close();
            con.close();

        } catch (SQLException ex) {
            Logger.getLogger(Deposit.class.getName()).log(Level.SEVERE, null, ex);
        }
return balance;
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtID = new javax.swing.JTextField();
        txtAccountNo = new javax.swing.JTextField();
        txtSirname = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtOtherName = new javax.swing.JTextField();
        txtMobile = new javax.swing.JTextField();
        txtTown = new javax.swing.JTextField();
        txtCounty = new javax.swing.JTextField();
        txtOccupation = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtloan = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        txtLoanBalance = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        txtSirname2 = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        txtPaying = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setLocation(new java.awt.Point(200, 70));

        jLabel1.setText("Account No.");

        jLabel2.setText("Pic");

        jLabel3.setText("ID No.");

        txtID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIDActionPerformed(evt);
            }
        });

        jLabel4.setText("Sir Name");

        jLabel5.setText("Other Names");

        jLabel6.setText("Mobile No.");

        jLabel7.setText("Town");

        jLabel8.setText("County");

        jLabel9.setText("Occupation");

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 51, 102));
        jLabel10.setText("Loan");

        txtloan.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtloan.setForeground(new java.awt.Color(255, 0, 51));

        jButton1.setText("Confirm");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("PROCESS");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel18.setText("No. Months Remaining");

        jLabel19.setText("Loan Balance");

        jLabel20.setFont(new java.awt.Font("Rockwell Extra Bold", 0, 18)); // NOI18N
        jLabel20.setText("Customer Loan Payments Form");

        txtPaying.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPayingKeyReleased(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel11.setText("PAYING");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel7)
                                        .addComponent(jLabel6))
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addGap(24, 24, 24)
                                            .addComponent(jLabel8))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtCounty, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtMobile, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtTown, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtOccupation, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel4))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(txtSirname, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 166, Short.MAX_VALUE)
                                    .addComponent(txtOtherName, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 166, Short.MAX_VALUE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 64, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(64, 64, 64)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel10)
                                    .addComponent(jLabel11))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtPaying)
                                    .addComponent(txtloan, javax.swing.GroupLayout.DEFAULT_SIZE, 166, Short.MAX_VALUE)))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jButton2)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel18)
                                        .addComponent(jLabel19))
                                    .addGap(18, 18, 18)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txtLoanBalance, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtSirname2, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(36, 36, 36)
                                .addComponent(jButton1))))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGap(111, 111, 111)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel3))
                                .addGap(24, 24, 24)
                                .addComponent(txtAccountNo, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel20))))
                .addContainerGap(109, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel20)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(txtAccountNo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(27, 27, 27)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton1))))
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtSirname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)
                    .addComponent(txtloan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtOtherName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(txtPaying, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtMobile, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtTown, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtSirname2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel18))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtLoanBalance, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel19))))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCounty, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtOccupation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                .addComponent(jButton2)
                .addGap(61, 61, 61))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
 Double val;
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
       val=  fetchUserDetails(txtID.getText(),"id");
    }//GEN-LAST:event_jButton1ActionPerformed

    private void txtIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIDActionPerformed

    private void txtPayingKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPayingKeyReleased
        Double loanBalance=(val-Double.valueOf(txtPaying.getText()));
                        txtLoanBalance.setText(loanBalance.toString());
    }//GEN-LAST:event_txtPayingKeyReleased

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
      JOptionPane.showMessageDialog(null, "NOT YET READY");
     int id= maxid("id",txtID.getText());
  getAllFromLoan("loanid",id);
    }//GEN-LAST:event_jButton2ActionPerformed
String loantype;
String installmentamount;
String installmentsno;
String periodtype;
String frequencyperperiod;
String targetdate;
String loanAmount;
String loanbalance;
String defaultacc;
String todaypay;
String givenOn;
String paidon;
public int maxid(String where,String value){
    int id=0;
    Methods m = new Methods();Connection con = m.getConnection();
    try{
        String getMaxId="SELECT MAX(autoid) FROM `loans` WHERE `" + where + "` = '" + value + "'";
         Statement st = con.createStatement();
           ResultSet rs = st.executeQuery(getMaxId);
           if(rs.next()){
               id=rs.getInt(1);
           }
    }
    catch(Exception nw){
        nw.printStackTrace();
    }
    return id;
}
    public void getAllFromLoan(String where,int value){
   // String getMaxId="SELECT MAX(autoid) FROM `loans` WHERE `" + where + "` = '" + value + "'";
    try {
            Methods m = new Methods();
            Connection con = m.getConnection();
            Statement st = con.createStatement();
            String checkpay="0";
            String searchQuery = "SELECT * FROM `loans` WHERE `" + where + "` = '" + value + "'AND `todaypay`='"+checkpay+"'AND `autoid`= ";
            ResultSet rs = st.executeQuery(searchQuery);
            while (rs.next()) {
loantype=rs.getString("loantype");
installmentamount=rs.getString("installmentamount");
installmentsno=rs.getString("installmentsno");
periodtype=rs.getString("periodtype");
frequencyperperiod=rs.getString("frequencyperperiod");
targetdate=rs.getString("targetdate");
loanAmount=rs.getString("loanAmount");
loanbalance=rs.getString("loanbalance");
defaultacc=rs.getString("loantype");
todaypay=rs.getString("todaypay");
 givenOn=rs.getString("givenOn");
paidon=rs.getString("paidon");
                
                insert();
                
                
//                autoid, 
//                id, loantype, installmentamount,
//                installmentsno, periodtype, frequencyperperiod,
//                targetdate, loanAmount, loanbalance, 
//                defaultacc, applicationfee, todaypay,
//                givenOn, paidon
            }
    }
    catch(Exception m){
        m.printStackTrace();
    }

    
    
    
    
    
}
    
    private int check(){
       int a=0; 
        
        return a;
    }
    
     public static Date addMonth(Date date,int hw){
      Calendar  c= Calendar.getInstance();
      c.setTime(date);
      
      c.add(Calendar.MONTH, hw);
      
      return c.getTime();
  }
   public static Date addWeek(Date date,int hw){
      Calendar  c= Calendar.getInstance();
      c.setTime(date);
      
      c.add(Calendar.WEEK_OF_MONTH, hw);
      
      return c.getTime();
  }
    public static Date addDay(Date date,int hw){
      Calendar  c= Calendar.getInstance();
      c.setTime(date);
      
      c.add(Calendar.DAY_OF_WEEK_IN_MONTH, hw);
      
      return c.getTime();
  }
    
    private void insert(){
        int a=check();
        Date tar;
        if(periodtype.equals("Monthly")){
           // tar=addMonth(targetdate,frequencyperperiod);
        }
        else if (periodtype.equals("Weekly")){
            
        }
        else if (periodtype.equals("Daily")){
            
        }
        else if (periodtype.equals("Yearly")){
            
        }
        
        
        
        if(a==1){
            
           String query = "INSERT INTO loans(`id`,`loantype`, `installmentamount`"
             + ",`installmentsno`"
             + ",`periodtype`"
             + ",`frequencyperperiod`"
             + ",`targetdate`"
             + ",`loanAmount`"
             + ",`loanbalance`"
             + ",`defaultacc`"
             + ",`applicationfee`"
             + ",`todaypay`"
             + ",`givenOn`"
             + ",`paidon`)"
             + " VALUES ('" + this.txtID.getText() + "'"
             + ",'" + loantype+ "'"
             + ",'" + this.txtPaying.getText() + "'"
             + ",'" +String.valueOf((Integer.valueOf(installmentsno)-1))+"'"
             + ",'" + this.periodtype + "'"
             + ",'" + frequencyperperiod+ "'"
            // + ",'" + DATE + "'"
             + ",'"+loanAmount+"'"
             + ",'"+loanAmount+"'"
            // + ",'"+i+"'"
            // + ",'"+appfe+"'"
            // + ",'"+i+"'"
             + ",now()"
             + ",now())";
       String fee=   JOptionPane.showInputDialog("Enter application fee");
     //  Double bal=Double.valueOf(fee)-Double.valueOf(appfe);
       
      // JOptionPane.showMessageDialog(null, "Balance is " +bal.toString());
       
      // n.executeSQlQuery(query, "Processed");
            
        }
        else{
            JOptionPane.showMessageDialog(null, "SOME DETAILS ARE MISSING");
        }
        
        
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
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
            java.util.logging.Logger.getLogger(loanpayment.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(loanpayment.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(loanpayment.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(loanpayment.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new loanpayment().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField txtAccountNo;
    private javax.swing.JTextField txtCounty;
    private javax.swing.JTextField txtID;
    private javax.swing.JTextField txtLoanBalance;
    private javax.swing.JTextField txtMobile;
    private javax.swing.JTextField txtOccupation;
    private javax.swing.JTextField txtOtherName;
    private javax.swing.JTextField txtPaying;
    private javax.swing.JTextField txtSirname;
    private javax.swing.JTextField txtSirname2;
    private javax.swing.JTextField txtTown;
    private javax.swing.JTextField txtloan;
    // End of variables declaration//GEN-END:variables
}
