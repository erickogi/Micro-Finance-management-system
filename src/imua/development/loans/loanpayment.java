/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imua.development.loans;

import imua.development.*;
import java.awt.Color;
import java.awt.Container;
import java.awt.Toolkit;
import static java.lang.System.out;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Kogi
 */
public class loanpayment extends javax.swing.JFrame {
  private String typeOfAccount=null;
    String toLoan[];
     String accounts[];
     Methods m = new Methods();
    /**
     * Creates new form loan
     */
    public loanpayment() {
        initComponents();
        System.out.println("jj");
                
        setTilteImage();
        findLoans();
        accountsTypes();
    }
    public void setTilteImage(){
        try {
            Methods n=new Methods();
            String t= n.setTitle();
            this.setTitle(t);
            String i=n.setIconImage();
            this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource(i)));
            
            String col=n.selectcolor();
            Color c=new Color(Integer.parseInt(col));
            jPanel1.setBackground(c);
              Container cont=this.getContentPane();
            cont.setBackground(c);
        } catch (Exception ex) {
            Logger.getLogger(loanpayment.class.getName()).log(Level.SEVERE, null, ex);
        }
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
              // JOptionPane.showMessageDialog(null,"rs ");
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
            Logger.getLogger(loanpayment.class.getName()).log(Level.SEVERE, null, ex);
        }

        return balance;
    }
     public void   fetchUserDetails(String value, String where) {

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

               
            }
            st.close();
            rs.close();
            con.close();

        } catch (SQLException ex) {
            Logger.getLogger(loanpayment.class.getName()).log(Level.SEVERE, null, ex);
        }

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
        jLabel3 = new javax.swing.JLabel();
        txtID = new javax.swing.JTextField();
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
        txtInstallmentsRemaining = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        txtPaying = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        txtBalance = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txtinstallment = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jComboBoxMode = new javax.swing.JComboBox<>();
        txtBBF = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setLocation(new java.awt.Point(200, 70));

        jPanel1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jPanel1KeyReleased(evt);
            }
        });

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

        jLabel18.setText("Installments  Remaining");

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

        jComboBox1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox1ItemStateChanged(evt);
            }
        });

        jLabel12.setText("BALANCE");

        jLabel13.setText("INSTALMENT");

        jComboBoxMode.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Mode", "Cash", "Mpesa", "Cheque" }));

        txtBBF.setEditable(false);

        jLabel1.setText("BBF");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel18, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel19, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtBBF)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, 166, Short.MAX_VALUE)
                    .addComponent(jComboBoxMode, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtinstallment)
                    .addComponent(txtLoanBalance, javax.swing.GroupLayout.DEFAULT_SIZE, 166, Short.MAX_VALUE)
                    .addComponent(txtInstallmentsRemaining, javax.swing.GroupLayout.DEFAULT_SIZE, 166, Short.MAX_VALUE)
                    .addComponent(txtPaying, javax.swing.GroupLayout.DEFAULT_SIZE, 166, Short.MAX_VALUE)
                    .addComponent(txtloan, javax.swing.GroupLayout.DEFAULT_SIZE, 166, Short.MAX_VALUE)
                    .addComponent(txtBalance))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(126, 126, 126)
                        .addComponent(jLabel20))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(183, 183, 183)
                        .addComponent(jLabel3)
                        .addGap(58, 58, 58)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(61, 61, 61)
                                .addComponent(jButton1))
                            .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(158, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel20)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel4)
                        .addComponent(txtSirname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtloan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtBalance, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12))))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtOtherName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtMobile, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6)
                            .addComponent(txtPaying, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtTown, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7)
                            .addComponent(txtBBF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtCounty, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtinstallment, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtInstallmentsRemaining, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18))
                .addGap(9, 9, 9)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtOccupation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(txtLoanBalance, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jComboBoxMode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton2)
                .addGap(35, 35, 35))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
    if(loantypeselected.equals("choose"))  {
         JOptionPane.showMessageDialog(null, "CHOOSE LOAN TYPE");
    }else{
    int isThere=   maxid("loanid",txtID.getText()+loantypeselected);
    if(isThere!=0){
       fetchUserDetails(txtID.getText(), "id");
       getAllFromLoan("autoid",isThere);
    }
    else{
        JOptionPane.showMessageDialog(null, txtID.getText()+"   has no loan");
    }
       
    } 
    // if(checkLoanRepayments()==1){
    //     clear();
    // } 
    }//GEN-LAST:event_jButton1ActionPerformed

    private void txtIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIDActionPerformed
         if(loantypeselected.equals("choose"))  {
         JOptionPane.showMessageDialog(null, "CHOOSE LOAN TYPE");
    }else{
    int isThere=   maxid("loanid",txtID.getText()+loantypeselected);
    if(isThere!=0){
       fetchUserDetails(txtID.getText(), "id");
      getAllFromLoan("autoid",isThere);
    }
    else{
        JOptionPane.showMessageDialog(null, txtID.getText()+"   has no loan");
    }
       
    } 
    }//GEN-LAST:event_txtIDActionPerformed

    private void txtPayingKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPayingKeyReleased
        Double loanBalance=(Double.valueOf(loanbalance)-(Double.valueOf(txtPaying.getText())));
                        txtLoanBalance.setText(loanBalance.toString());
        Double installmentsRemaining= (Double.valueOf(txtLoanBalance.getText())/Double.valueOf(installmentamount));
                        txtInstallmentsRemaining.setText(String.valueOf(installmentsRemaining));
    }//GEN-LAST:event_txtPayingKeyReleased

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
      try {
          insert();
//      JOptionPane.showMessageDialog(null, "NOT YET READY");
//    int id= maxid("id",txtID.getText()+loantypeselected);
//  getAllFromLoan("autoid",id);
      } catch (ParseException ex) {
          Logger.getLogger(loanpayment.class.getName()).log(Level.SEVERE, null, ex);
      }
    }//GEN-LAST:event_jButton2ActionPerformed
String loantypeselected;
    private void jComboBox1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox1ItemStateChanged
            loantypeselected=        jComboBox1.getSelectedItem().toString();
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox1ItemStateChanged

    private void jPanel1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jPanel1KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_jPanel1KeyReleased
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
String nextP;
double alreadyPayed=0.0;
 public String nxp(String loanid,String inst){
    String id=null;
    Methods m = new Methods();Connection con = m.getConnection();
    try{
        String getMaxId="SELECT nxp FROM `loans` WHERE installmentsno = '" + inst + "' AND loanid='"+loanid+"' ";
         Statement st = con.createStatement();
           ResultSet rs = st.executeQuery(getMaxId);
           if(rs.next()){
               id=rs.getString("nxp");
              // JOptionPane.showMessageDialog(this,id );
           }
      st.close();
      rs.close();
      con.close();
    }
    catch(Exception nw){
        nw.printStackTrace();
    }
    return id;
}
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
      st.close();
      rs.close();
      con.close();
    }
 
    catch(Exception nw){
        nw.printStackTrace();
    }
    return id;
}
String no;
public void update(String hw,Double value){
    Methods m = new Methods();
   // if(){
        
   // }
    //String notFullyPaid="nfp";
   // String paidMore="pm";
   // String paid="paid";
    
    String query = "UPDATE `loans` SET `nxp`='" + hw + "'WHERE loanid= '" + txtID.getText()+loantypeselected+ "' AND installmentsno='" + no + "'";
      
      m.executeSQlQueryN(query);
}
    public void getAllFromLoan(String where,int value){
   // String getMaxId="SELECT MAX(autoid) FROM `loans` WHERE `" + where + "` = '" + value + "'";
    try {
            Methods m = new Methods();
            Connection con = m.getConnection();
            Statement st = con.createStatement();
            String checkpay="0";
            String searchQuery = "SELECT * FROM `loans` WHERE `" + where + "` = '" + value + "' ";
            ResultSet rs = st.executeQuery(searchQuery);
            while (rs.next()) {
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
nextP=rs.getString("nxp");
                no=installmentsno;
               txtloan.setText(loanAmount);
               txtBalance.setText(loanbalance);
               
               txtinstallment.setText(installmentamount);
                txtBBF.setText(String.valueOf(alreadyPayed));
           int v=( Integer.valueOf(installmentsno))+1;
          // JOptionPane.showMessageDialog(this,v +" \n"+txtID.getText()+loantypeselected);
            nextP =  nxp(txtID.getText()+loantypeselected,String.valueOf(v));
               if(nextP.equals("pm")){
                   try{
                       
                     alreadyPayed=Double.valueOf(defaultacc);
                     txtBBF.setText(String.valueOf(alreadyPayed));
                     
                   }
                   catch(Exception nn){
                       
                     System.out.println(nn);
                     
                   }
                       
               }
                
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

    
    
    
    
    
}
    
    private int check(){
       int a=1; 
       if(txtBalance.getText().isEmpty()||
          txtID.getText().isEmpty()||
               txtInstallmentsRemaining.getText().isEmpty()||
               txtLoanBalance.getText().isEmpty()||
               txtloan.getText().isEmpty()||
               txtinstallment.getText().isEmpty()||
               jComboBoxMode.getSelectedItem().toString().equals("Mode")||
               txtPaying.getText().isEmpty()){
           a=0;
       }
       
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
    
    private void insert() throws ParseException{
        int a=check();
        String defaultamount="null";
        Date tar=null;
        if(periodtype.equals("Monthly")){
            
            tar=addMonth(targetdate,Integer.valueOf(frequencyperperiod));
        }
        else if (periodtype.equals("Weekly")){
            tar=addWeek(targetdate,Integer.valueOf(frequencyperperiod));
        }
        else if (periodtype.equals("Daily")){
            tar=addDay(targetdate,Integer.valueOf(frequencyperperiod));
        }
        else if (periodtype.equals("Yearly")){
            tar=addYear(targetdate,Integer.valueOf(frequencyperperiod));
        }
        SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:SS");
       Calendar  c= Calendar.getInstance();
      c.setTime(tar);
      String mess="";
         c.get(Calendar.DAY_OF_WEEK); 
        if(c.get(Calendar.DAY_OF_WEEK)==Calendar.SATURDAY||c.get(Calendar.DAY_OF_WEEK)==Calendar.SUNDAY){
           mess="Being a weekend ,the installment is therefore expected on Monday of the next week\n";
           
        }
        String   date = DATE_FORMAT.format(tar);
        String  paidstatus="paid";
        
        if(a==1){
            if((Double.valueOf(txtPaying.getText()) + alreadyPayed)< Double.valueOf(installmentamount)){
                paidstatus="nfp";
               defaultamount=String.valueOf(Double.valueOf(installmentamount)-((Double.valueOf(txtPaying.getText()))+alreadyPayed));
                //defaultamount=String.valueOf(((Double.valueOf(txtPaying.getText()))+alreadyPayed)-Double.valueOf(installmentamount));
             
            }
            if((Double.valueOf(txtPaying.getText()) + alreadyPayed)> Double.valueOf(installmentamount)){
                defaultamount=String.valueOf(((Double.valueOf(txtPaying.getText()))+alreadyPayed)-Double.valueOf(installmentamount));
                paidstatus="pm";
            }
             if((Double.valueOf(txtPaying.getText()) + alreadyPayed)== Double.valueOf(installmentamount)){
                defaultamount=String.valueOf(((Double.valueOf(txtPaying.getText()))+alreadyPayed)-Double.valueOf(installmentamount));
                paidstatus="paid";
            }
            if(txtLoanBalance.getText().equals("0.0")){
                
               // date="LOAN INSTALLAMENTS PAID IN-FULL";
            }
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
             + ",`tp`"
             + ",`givenOn`"
             + ",`paidon`)"
             + " VALUES ("
             + "'" + this.txtID.getText() + "'"
             + ",'" + txtID.getText()+loantypeselected+ "'"
             + ",'" + loantype+ "'"
             + ",'" + this.applicable+ "'"
             + ",'" + this.installmentamount + "'"
             + ",'" +String.valueOf((Integer.valueOf(installmentsno)-1))+"'"
             + ",'" + this.periodtype + "'"
             + ",'" + frequencyperperiod+ "'"
                   
            + ",'" + date + "'"
             + ",'"+loanAmount+"'"
             + ",'"+loanRequested+"'"
                
             + ",'"+txtLoanBalance.getText()+"'"
             + ",'"+defaultamount+"'"
             + ",'"+this.applicationfee+"'"
             + ",'" + this.txtPaying.getText() + "'"
             + ",'" + paidstatus + "'"
             + ",'" + this.givenOn + "'"
           
             + ",now())";
      // String fee=   JOptionPane.showInputDialog("Enter application fee");
     //  Double bal=Double.valueOf(fee)-Double.valueOf(appfe);
       
      // JOptionPane.showMessageDialog(null, "Balance is " +bal.toString());
       
    if(m.executeSQlQuery(query, "Processed\n NEXT TARGET DAY IS ON \n "+date +"  \n")==1) {
// if its weekend issue +mess
        
          if((Double.valueOf(txtPaying.getText())+ alreadyPayed)<Double.valueOf(installmentamount)){//if paying less than installment
            double less=Double.valueOf(installmentamount)-(Double.valueOf(txtPaying.getText())+alreadyPayed);
            
            update("nfp",less);
            
           // JOptionPane.showMessageDialog(this,(Double.valueOf(txtPaying.getText())+ alreadyPayed) );
          }
          else if((Double.valueOf(txtPaying.getText())+ alreadyPayed)>Double.valueOf(installmentamount)){//if paying more han installment
              
              double more=Double.valueOf(txtPaying.getText()+alreadyPayed)-Double.valueOf(installmentamount);
              update("pm",more);
          }
          else{
            // JOptionPane.showMessageDialog(this,(Double.valueOf(txtPaying.getText())+ alreadyPayed) );
              Double equal=0.0;
              update("paid",equal);
          }
      
        m.addToOrgAccount(Double.valueOf(txtPaying.getText()),"Loan Installments",jComboBoxMode.getSelectedItem().toString() );
        
        if(txtLoanBalance.getText().equals("0.0")||Double.valueOf(txtLoanBalance.getText())<0.0){
            checkWhetherHasOutStandingFines(txtID.getText(),(txtID.getText()+loantypeselected));
        }
        clear();
    }
            
        }
        else{
            JOptionPane.showMessageDialog(null, "SOME DETAILS ARE MISSING");
        }
        
    }
    //checks wh
    public int checkWhetherHasOutStandingFines(String id,String loanid){
         HashMap<String,String>defaults=new HashMap<String,String>();
      try {
          Calendar  c= Calendar.getInstance();
         
          Date today=c.getTime();
          java.util.Date d=(today);
          
          java.sql.Date DATE=new java.sql.Date(d.getTime());
          
          Methods n = new Methods();
          String nl="null";
          String paid="nfp";
          Connection con =n. getConnection();
          
          Statement st = con.createStatement();
          
          String searchQuery = "SELECT * FROM `loans` WHERE (loanid='" + loanid + "'AND todaypay='"+nl+"') OR (loanid='" + loanid + "' AND nxp ='"+paid+"') ";
            
        //  String searchQuery = "SELECT * FROM `loans`WHERE targetdate='" + DATE + "' ";
        
          ResultSet rs = st.executeQuery(searchQuery);
          while (rs.next())
          {
              
              defaults.put(rs.getString("targetdate"), rs.getString("installmentamount"));
              
          }
          st.close();
          rs.close();
          con.close();
          //JOptionPane.showMessageDialog(this, defaults.size());
          Logger.getLogger(loanpayment.class.getName()).log(Level.SEVERE, null, defaults.size());
          if(defaults.size()<=1||defaults.isEmpty()){
              getToArchives("loanid",loanid);
          }
          else{
              JOptionPane.showMessageDialog(null, "Has a defaulted payment");
          }
      } catch (SQLException ex) {
          ex.printStackTrace();
          Logger.getLogger(loanpayment.class.getName()).log(Level.SEVERE, null, ex);
      }
        
        
        
        
       return defaults.size(); 
        
    }
    String id;
    String loanid;
    String lnxp;
    public void getToArchives(String where,String value){
         int results=0; 
        try {
            Methods m = new Methods();
            Connection con = m.getConnection();
            Statement st = con.createStatement();
            String checkpay="0";
            String searchQuery = "SELECT * FROM `loans` WHERE `" + where + "` = '" + value + "' ";
            ResultSet rs = st.executeQuery(searchQuery);
            while (rs.next()) {
id=rs.getString("id");
loanid=rs.getString("loanid");
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
defaultacc=rs.getString("loantype");
applicationfee=rs.getString("applicationfee");
todaypay=rs.getString("todaypay");
 givenOn=rs.getString("givenOn");
paidon=rs.getString("paidon");
lnxp=rs.getString("nxp");
                 
        results=results+moveToArchives();
              
               
                
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
          
          if(results>0){
           DefaultersPrePaid dp=new DefaultersPrePaid();   
           if(dp.print(id,value,loantype)==1){
               
              
                String query = "DELETE FROM `loans`  WHERE `loanid` = '" +value+"'";
                m.executeSQlQueryN(query);
                deleteGuranters();
           }
           else{
               JOptionPane.showMessageDialog(null, value+" Loan error moving to archive"); 
           }
              
//               Object []options={"PRINT REPORT","CONTINUE ANYWAY","CANCEL"};
//           int btn= JOptionPane.showOptionDialog(null, "LOAN REPORT","CONFIRM",
//                    JOptionPane.DEFAULT_OPTION,
//                    JOptionPane.QUESTION_MESSAGE,null,options,options[0]);
//        switch (btn) {
//            case 1:
//           DefaultersPrePaid dp=new DefaultersPrePaid();   
//           if(dp.print(id,value,loantype)==1){
//               
//               
//               String query = "DELETE FROM `loans`  WHERE `loanid` = '" +value+"'";
//                m.executeSQlQueryN(query);
//                deleteGuranters();
//            
//           }
//           else{
//                JOptionPane.showMessageDialog(null, value+" Loan error moving to archive"); 
//           }
//            
//                break;
//                
//            case 2:
//                String query = "DELETE FROM `loans`  WHERE `loanid` = '" +value+"'";
//                m.executeSQlQueryN(query);
//                deleteGuranters();
//                break;
//                
//            case 3:
//                String queryq = "DELETE FROM `loans`  WHERE `loanid` = '" +value+"'";
//                m.executeSQlQueryN(queryq);
//                deleteGuranters();
//        }
            
             
          }
          else{
              JOptionPane.showMessageDialog(null, "Error moving to archive");
          }
    }
    catch(Exception m){
        m.printStackTrace();
    }

    
    
    }
    public int moveToArchives(){
         String query = "INSERT INTO loansarchives("
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
             + ",`givenOn`"
             + ",`paidon`)"
             + " VALUES ("
             + "'" + this.id + "'"
             + ",'" + this.loanid+"'"
             + ",'" + loantype+ "'"
             + ",'" + this.applicable+ "'"
             + ",'" + this.installmentamount + "'"
             + ",'" +installmentsno+"'"
             + ",'" + this.periodtype + "'"
             + ",'" + frequencyperperiod+ "'"
                   
             + ",'" + targetdate + "'"
             + ",'"+loanAmount+"'"
             + ",'"+loanRequested+"'"
                
             + ",'"+loanbalance+"'"
             + ",'"+defaultacc+"'"
             + ",'"+this.applicationfee+"'"
             + ",'" + this.todaypay + "'"
                 + ",'" + this.lnxp + "'"
             + ",'" + this.givenOn + "'"
           
             + ",'" + this.paidon + "')";
         
         int s=m.executeSQlQueryN(query);
         return s;
    }
   // public DateTime dateTime(){
       
   // }
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
    private javax.swing.JComboBox<String> jComboBoxMode;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField txtBBF;
    private javax.swing.JTextField txtBalance;
    private javax.swing.JTextField txtCounty;
    private javax.swing.JTextField txtID;
    private javax.swing.JTextField txtInstallmentsRemaining;
    private javax.swing.JTextField txtLoanBalance;
    private javax.swing.JTextField txtMobile;
    private javax.swing.JTextField txtOccupation;
    private javax.swing.JTextField txtOtherName;
    private javax.swing.JTextField txtPaying;
    private javax.swing.JTextField txtSirname;
    private javax.swing.JTextField txtTown;
    private javax.swing.JTextField txtinstallment;
    private javax.swing.JTextField txtloan;
    // End of variables declaration//GEN-END:variables
private void clear(){
txtBalance.setText("");
          txtID.setText("");
               txtInstallmentsRemaining.setText("");
               txtLoanBalance.setText("");
               txtloan.setText("");
               txtinstallment.setText("");
               txtPaying.setText("");
               txtCounty.setText("");
               txtMobile.setText("");
               txtOccupation.setText("");
               txtOtherName.setText("");
               txtSirname.setText("");
               txtTown.setText("");
               txtBBF.setText("");
               jComboBox1.setSelectedIndex(0);
}
public void deleteGuranters(){
    
    
              String query = "DELETE FROM `guranters`  WHERE `id` = '" +jComboBox1.getSelectedItem().toString()+txtID.getText()+"'";
                m.executeSQlQueryN(query);
    
    
    
}





 public  int checkLoanRepayments(){
       int lr=0;
         HashMap<String,String>todays=new HashMap<String,String>();
           Calendar  c= Calendar.getInstance();
         Date today=addOne(c.getTime(),-1);
         
         java.util.Date d=(today);
  //Days d=Days.daysBeetween();
         java.sql.Date DATE=new java.sql.Date(d.getTime());
      try {
          Methods n = new Methods();
          String nl="np";
          String lp="nfp";
          Connection con =n. getConnection();
          
          Statement st = con.createStatement();
          String searchQuery = "SELECT * FROM `loans`WHERE (targetdate<'" + DATE + "'AND nxp='"+nl+"') OR (targetdate<'" + DATE + "'AND nxp='"+lp+"')";
          ResultSet rs = st.executeQuery(searchQuery);
          while (rs.next())
          {  // String maxid=rs.getString("autoid");
             // String inNo=rs.getString("installmentsno");
             //String id= rs.getString("id");
             //String loanId= rs.getString("loanid");
             //String loanType= rs.getString("loantype");
            // String instAmount= rs.getString("installmentamount");
             
             //int pd= checkIfThereIsNext(maxid,id,inNo,loanType,loanId,instAmount);
             // if(pd==0||pd==2){
              todays.put(rs.getString("id"), rs.getString("installmentamount"));
             // }
            
          }
          st.close();
          rs.close();
          con.close();
          
          if(todays.size()>0){
        int dialogButton=
        
        JOptionPane.showConfirmDialog(null,todays.size()+ "  LOANS DEFAULTED \n Select YES to check them out ");
        //JOptionPane
              switch (dialogButton) {
                  case JOptionPane.YES_OPTION:
                      lr=0;
                      Defaulters kd=new Defaulters();
                      //a.typeoftransaction="withdrawal";
                      kd.setVisible(true);
                      this.dispose();
                      break;
              //JOptionPane.showMessageDialog(null, todays.size()+ "  LOANS DEFAULTED ");
              //out.println("no");
                  case JOptionPane.NO_OPTION:
                      lr=1;
                      break;
              //  out.println("cancel");
                  case JOptionPane.CANCEL_OPTION:
                      lr=1;
                      break;
              //  out.println("close");
                  case JOptionPane.CLOSED_OPTION:
                      lr=1;
                      break;
                  default:
                      break;
              }
          }
          
          //checkLoanRepayments();
          
      } catch (SQLException ex) {
          Logger.getLogger(ProcessLoan.class.getName()).log(Level.SEVERE, null, ex);
      }
      return lr;
    }
     public  Date addOne(Date date,int hw){
      Calendar  c= Calendar.getInstance();
      c.setTime(date);
      c.add(Calendar.DATE, hw);
   //   c.add(Calendar.WEEK_OF_MONTH, hw);
      
      return c.getTime();
  }








//setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_M, java.awt.event.InputEvent.CTRL_MASK));



}
