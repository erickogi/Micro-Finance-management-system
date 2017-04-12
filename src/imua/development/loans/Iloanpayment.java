/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imua.development.loans;

import IDataHolders.ILoanDataHolder;
import IDataHolders.ILoanDetailsDataHolder;
import imua.development.*;
import imua.development.Reports.Print;
import java.awt.Color;
import java.awt.Container;
import java.awt.Toolkit;
import static java.lang.System.out;
import static java.lang.Thread.sleep;
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
public class Iloanpayment extends javax.swing.JFrame {
  private String typeOfAccount=null;
    String toLoan[];
    String accounts[];
     Methods m = new Methods();
     Methods methods = new Methods();
     
     
     
     
     String loantypeselected;//jcombobox selection result
      ArrayList<ILoanDataHolder> dataList;//will hold last loan installment data
     
    /**
     * Creates new form loan
     */
    public Iloanpayment() {
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
            Logger.getLogger(Iloanpayment.class.getName()).log(Level.SEVERE, null, ex);
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
    
   
    //okay-------fetches user details 
     public void   fetchUserDetails(String CustomerIdvalue, String idColumn) {

        try {
            Methods m = new Methods();
            Connection con = m.getConnection();
            Statement st = con.createStatement();
            String searchQuery = "SELECT * FROM `users` WHERE `" + idColumn + "` = '" + CustomerIdvalue + "'";
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
            Logger.getLogger(Iloanpayment.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
     //okay------gets the last installment for a particular customer..returns autoid
     public int maxid(String where,String value){
    int id=0;
    Methods m = new Methods();Connection con = m.getConnection();
    try{
        String getMaxId="SELECT MAX(autoid) FROM `iloans` WHERE `" + where + "` = '" + value + "'";
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
      //okay------gets the last installment for a particular customer..puts them into arraylist dataList of type ILoanDataHandler
     public void getAllFromLoan(String autoidColumn,int autoidValue){
  
    dataList = new ArrayList();
    try {
            
            Connection con = methods.getConnection();
            Statement st = con.createStatement();
           
            String searchQuery = "SELECT * FROM `iloans` WHERE "
             + "`" + autoidColumn+ "` = '" + autoidValue+ "' ";
            ResultSet rs = st.executeQuery(searchQuery);
            
             if (rs.next())
          {
      
    
        ILoanDataHolder user = new ILoanDataHolder(rs.getString("autoid"),rs.getString("customerid"),
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
               
               // ,rs.getString("guranters")
        
        
        );
        
        dataList.add(user);
            
            
            
            
            
            
            
            
            
            
            
            
            
//           
//loantype=rs.getString("loantype");
//applicablestatus=rs.getString("applicablestatus");
//installmentamount=rs.getString("installmentamount");
//installmentsno=rs.getString("installmentsno");
//periodtype=rs.getString("periodtype");
//frequencyperperiod=rs.getString("frequencyperperiod");
//datesupposed=rs.getDate("targetdate");
//loanAmount=rs.getString("loanAmount");
//loanRequested=rs.getString("loanRequested");
//loanbalance=rs.getString("loanbalance");
//balancebf=rs.getString("balancebf");
//todaypay=rs.getString("todaypay");
// givenOn=rs.getString("givenOn");
//paidon=rs.getString("paidon");
//paymentstatus=rs.getString("paymentstatus");
//                no=installmentsno;
//               txtloan.setText(loanAmount);
//               txtBalance.setText(loanbalance);
//               txtinstallment.setText(installmentamount);
//               txtBBF.setText(String.valueOf(alreadyPayed));
//               int v=( Integer.valueOf(installmentsno))+1;
//               nextP =  nxp(txtID.getText()+loantypeselected,String.valueOf(v));
//               if(nextP.equals("pm")){
//                   try{
//                       
//                     alreadyPayed=Double.valueOf(defaultacc);
//                     txtBBF.setText(String.valueOf(alreadyPayed));
//                     
//                   }
//                   catch(Exception nn){
//                       
//                     System.out.println(nn);
//                     
//                   }
                       
               }
                
//                autoid, 
//                id, loantype, installmentamount,
//                installmentsno, periodtype, frequencyperperiod,
//                targetdate, loanAmount, loanbalance, 
//                defaultacc, applicationfee, todaypay,
//                givenOn, paidon

            
      st.close();
      rs.close();
      con.close();
    }
    catch(Exception m){
        m.printStackTrace();
    }

    
    
   // return dataList;
    
    
}
public void threadInsert(){
Thread log=new Thread(){
public void run(){
    
    try{
   insert();
      
      
    }
      catch(Exception b){
         System.out.println("Error");
      }
} 
      }   ;  
      log.start();
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
        txtAlreadyPaid = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();

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

        txtSirname.setEditable(false);

        jLabel4.setText("Sir Name");

        jLabel5.setText("Other Names");

        jLabel6.setText("Mobile No.");

        jLabel7.setText("Town");

        jLabel8.setText("County");

        jLabel9.setText("Occupation");

        txtOtherName.setEditable(false);

        txtMobile.setEditable(false);

        txtTown.setEditable(false);

        txtCounty.setEditable(false);

        txtOccupation.setEditable(false);

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 51, 102));
        jLabel10.setText("Loan");

        txtloan.setEditable(false);
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

        txtLoanBalance.setEditable(false);

        jLabel18.setText("Installments  Remaining");

        txtInstallmentsRemaining.setEditable(false);

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

        txtBalance.setEditable(false);

        jLabel12.setText("BALANCE");

        txtinstallment.setEditable(false);

        jLabel13.setText("INSTALMENT");

        jComboBoxMode.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Mode", "Cash", "Mpesa", "Cheque" }));

        txtBBF.setEditable(false);

        jLabel1.setText("BBF");

        txtAlreadyPaid.setEditable(false);

        jLabel2.setText("Already Paid");

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
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtAlreadyPaid)
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
                            .addComponent(jLabel8)
                            .addComponent(txtAlreadyPaid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2)))
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
   //okay------intitiates maxid which gets last instalment autoid by loanid
                //intiates getAllFromLoan method which fetches loan parameters into a dataList ArrayList
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
    if(loantypeselected.equals("choose"))  {
         JOptionPane.showMessageDialog(null, "CHOOSE LOAN TYPE");
    }else{
    int lastInstallment=   maxid("loanid",txtID.getText()+loantypeselected);
    if(lastInstallment!=0){
       fetchUserDetails(txtID.getText(), "id");
       getAllFromLoan("autoid",lastInstallment);
       Double loanBalance=Double.valueOf(((ILoanDataHolder)dataList.get(0)).getLoanbalance())-Double.valueOf(((ILoanDataHolder)dataList.get(0)).getTodaypay());
       
               txtloan.setText(((ILoanDataHolder)dataList.get(0)).getLoanAmount());
               txtBalance.setText(String.valueOf(loanBalance));
               txtinstallment.setText(((ILoanDataHolder)dataList.get(0)).getInstallmentamount());
               txtBBF.setText(((ILoanDataHolder)dataList.get(0)).getBalancebf());
               txtAlreadyPaid.setText(((ILoanDataHolder)dataList.get(0)).getTodaypay());
       
       
       
    }
    else{
        JOptionPane.showMessageDialog(null, txtID.getText()+"   has no loan");
    }
       
    } 

    }//GEN-LAST:event_jButton1ActionPerformed
//okay------intitiates maxid which gets last instalment autoid by loanid
                //intiates getAllFromLoan method which fetches loan parameters into a dataList ArrayList
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
       //((ILoanDataHolder)dataList.get(1)).getLoanbalance();
        
        
        
        Double loanBalance=(Double.valueOf(((ILoanDataHolder)dataList.get(0)).getLoanbalance())-
                
                (Double.valueOf(((ILoanDataHolder)dataList.get(0)).getTodaypay())+Double.valueOf(txtPaying.getText())));
        
        
                        txtLoanBalance.setText(loanBalance.toString());
                        
                        
        Double installmentsRemaining= (Double.valueOf(txtLoanBalance.getText())/Double.valueOf(((ILoanDataHolder)dataList.get(0)).getInstallmentamount()));
                        txtInstallmentsRemaining.setText(String.valueOf(installmentsRemaining));
    }//GEN-LAST:event_txtPayingKeyReleased

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        threadInsert();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jComboBox1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox1ItemStateChanged
            loantypeselected=jComboBox1.getSelectedItem().toString();
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox1ItemStateChanged

    private void jPanel1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jPanel1KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_jPanel1KeyReleased


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
        String periodtype=((ILoanDataHolder)dataList.get(0)).getPeriodtype();
        String frequencyperperiod=((ILoanDataHolder)dataList.get(0)).getFrequencyperperiod();
        Date targetdate=((ILoanDataHolder)dataList.get(0)).getDatesupposed();
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
        
        String todayPay="0.0";
        String paymentStatus="np";
        String defaultStatus="nd";
        String i="0.0";
        
        
        Double moreorless=(Double.valueOf(txtPaying.getText())
                +Double.valueOf(((ILoanDataHolder)dataList.get(0)).getBalancebf())
                
                +Double.valueOf(((ILoanDataHolder)dataList.get(0)).getTodaypay()))-
              Double.valueOf(((ILoanDataHolder)dataList.get(0)).getInstallmentamount());
        String paidstatus="paid";
        
        if(a==1){
       if((Double.valueOf(txtPaying.getText())
               +Double.valueOf(((ILoanDataHolder)dataList.get(0)).getBalancebf())
               +Double.valueOf(((ILoanDataHolder)dataList.get(0)).getTodaypay()))
               
               <
               
               Double.valueOf(((ILoanDataHolder)dataList.get(0)).getInstallmentamount())){
           
           
           
           updateLoan(((ILoanDataHolder)dataList.get(0)).getAutoid(),
           Double.valueOf(txtPaying.getText())+Double.valueOf(((ILoanDataHolder)dataList.get(0)).getTodaypay())     ,String.valueOf(moreorless),"nfp");
          
           
           //clear();
       
       }
       else{
           
           
           
           
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
             + "'" + this.txtID.getText() + "'"
             + ",'" + txtID.getText()+loantypeselected+ "'"
             + ",'" + ((ILoanDataHolder)dataList.get(0)).getLoantype()+ "'"
             + ",'" + ((ILoanDataHolder)dataList.get(0)).getApplicablestatus()+ "'"
             + ",'" + ((ILoanDataHolder)dataList.get(0)).getApplicationfee() + "'"
             + ",'" +((ILoanDataHolder)dataList.get(0)).getPeriodtype()+"'"
             + ",'" + ((ILoanDataHolder)dataList.get(0)).getFrequencyperperiod()+ "'"
            
                   
            + ",'" + date + "'"
             + ",'"+((ILoanDataHolder)dataList.get(0)).getInstallmentamount()+"'"
             + ",'"+(Integer.valueOf(((ILoanDataHolder)dataList.get(0)).getInstallmentsno())-1)+"'"
                
             + ",'"+((ILoanDataHolder)dataList.get(0)).getLoanAmount()+"'"
             + ",'"+((ILoanDataHolder)dataList.get(0)).getLoanRequested()+"'"
             + ",'"+this.txtLoanBalance.getText()+"'"
         
         
             + ",'" + todayPay + "'"
             + ",'" +todayPay + "'"
         
             + ",'" + paymentStatus + "'"
             + ",'" + defaultStatus + "'"
             + ",'" + moreorless + "'"
           
             + ",'"+i+"'"
              + ",now()"
             + ",now())";
      // String fee=   JOptionPane.showInputDialog("Enter application fee");
     //  Double bal=Double.valueOf(fee)-Double.valueOf(appfe);
       
      // JOptionPane.showMessageDialog(null, "Balance is " +bal.toString());
       
    if(m.executeSQlQuery(query, "Processed\n NEXT TARGET DAY IS ON \n "+date +"  \n")==1) {
                  if(moreorless>0){
                  paidstatus="pm";
           }
           updateLoan(((ILoanDataHolder)dataList.get(0)).getAutoid(),
           Double.valueOf(txtPaying.getText())+Double.valueOf(((ILoanDataHolder)dataList.get(0)).getTodaypay())  ,String.valueOf(moreorless),paidstatus);
           
           
         
      
        // m.addToOrgAccount(Double.valueOf(txtPaying.getText()),"Loan Installments",jComboBoxMode.getSelectedItem().toString() );
        
        
       
     }//if excutequery==1
   } //else paid/pm    
  }//a==1
       
        else{
            JOptionPane.showMessageDialog(null, "SOME DETAILS ARE MISSING");
        }
        
    }
    
    
    
    void updateLoan(String autoid,double todaypay,String moreorlesspaid,String paymentstatus){
        String query = "UPDATE `iloans` SET `todaypay`='" + todaypay + "',`moreorlesspaid`='" + moreorlesspaid + "',"
                + "`paymentstatus`='" + paymentstatus + "'"
               
                + "WHERE autoid= '" + autoid+ "' ";
      
      if(methods.executeSQlQueryN(query)==1){
       methods.addToOrgAccount(Double.valueOf(txtPaying.getText()),"Loan Installments",jComboBoxMode.getSelectedItem().toString() );
       JOptionPane.showMessageDialog(null, "Successful........");
      }
      if(txtLoanBalance.getText().equals("0.0")||Double.valueOf(txtLoanBalance.getText())<=0.0){
          getToArchives("loanid",((ILoanDataHolder)dataList.get(0)).getLoanid());
          clear();
        }
      else{
          clear(); 
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
          Logger.getLogger(Iloanpayment.class.getName()).log(Level.SEVERE, null, defaults.size());
          if(defaults.size()<=1||defaults.isEmpty()){
              getToArchives("loanid",loanid);
          }
          else{
              JOptionPane.showMessageDialog(null, "Has a defaulted payment");
          }
      } catch (SQLException ex) {
          ex.printStackTrace();
          Logger.getLogger(Iloanpayment.class.getName()).log(Level.SEVERE, null, ex);
      }
        
        
        
        
       return defaults.size(); 
        
    }
    String id;
    String loanid;
    String lnxp;
    public boolean getToArchives(String loanIdColumn,String loanIdValue){
         boolean results=false;
          String loantype="";
         ArrayList<ILoanDataHolder> toArchive=new ArrayList<>();
        try {
            
            Connection con = methods.getConnection();
            Statement st = con.createStatement();
            String checkpay="0";
            String searchQuery = "SELECT * FROM `iloans` WHERE `" +loanIdColumn + "` = '" + loanIdValue+ "' ";
            ResultSet rs = st.executeQuery(searchQuery);
            while (rs.next()) {
                 loantype=rs.getString("loantype");
ILoanDataHolder user = new ILoanDataHolder(rs.getString("autoid"),rs.getString("customerid"),
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
               
               // ,rs.getString("guranters")
        
        
        );
        
        toArchive.add(user);

            }
         results = moveToArchives(toArchive);
          st.close();
          rs.close();
          con.close();
          
          if(results){
          Print dp=new Print();   
          
                
             if(dp.print(id,loanIdValue,loantype)==1){
               
                  
           }
           else{
               JOptionPane.showMessageDialog(null, loanIdValue+" Loan error printing report "); 
           }
                 String query = "DELETE FROM `iloans`  WHERE `loanid` = '" +loanIdValue+"'";
                m.executeSQlQueryN(query);
                deleteGuranters(loanIdValue);
             
          }
          else{
              JOptionPane.showMessageDialog(null, "Error moving to archive");
          }
    }
    catch(Exception m){
        m.printStackTrace();
    }

    return results;
    
    }
    public boolean moveToArchives(ArrayList<ILoanDataHolder> toArchives){
        boolean success=false;
        int s=0;
        for(int count=0;count<toArchives.size();count++){
        
         String query = "INSERT INTO iloansarchives("
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
             + "'" + ((ILoanDataHolder)toArchives.get(count)).getCustomerid() + "'"
             + ",'" + ((ILoanDataHolder)toArchives.get(count)).getLoanid()+ "'"
             + ",'" + ((ILoanDataHolder)toArchives.get(count)).getLoantype()+ "'"
             + ",'" + ((ILoanDataHolder)toArchives.get(count)).getApplicablestatus()+ "'"
             + ",'" + ((ILoanDataHolder)toArchives.get(count)).getApplicationfee() + "'"
             + ",'" +((ILoanDataHolder)toArchives.get(count)).getPeriodtype()+"'"
             + ",'" + ((ILoanDataHolder)toArchives.get(count)).getFrequencyperperiod()+ "'"
            
                   
            + ",'" +((ILoanDataHolder)toArchives.get(count)).getDatesupposed() + "'"
             + ",'"+((ILoanDataHolder)toArchives.get(count)).getInstallmentamount()+"'"
             + ",'"+((ILoanDataHolder)toArchives.get(count)).getInstallmentsno()+"'"
                
             + ",'"+((ILoanDataHolder)toArchives.get(count)).getLoanAmount()+"'"
             + ",'"+((ILoanDataHolder)toArchives.get(count)).getLoanRequested()+"'"
             + ",'"+((ILoanDataHolder)toArchives.get(count)).getLoanbalance()+"'"
         
         
             + ",'" + ((ILoanDataHolder)toArchives.get(count)).getMoreorlesspaid() + "'"
             + ",'" +((ILoanDataHolder)toArchives.get(count)).getTodaypay()+ "'"
         
             + ",'" + ((ILoanDataHolder)toArchives.get(count)).getPaymentstatus() + "'"
             + ",'" + ((ILoanDataHolder)toArchives.get(count)).getDefaultstatus()+ "'"
             + ",'" + ((ILoanDataHolder)toArchives.get(count)).getBalancebf() + "'"
           
             + ",'"+((ILoanDataHolder)toArchives.get(count)).getExtra()+"'"
              + ",'"+((ILoanDataHolder)toArchives.get(count)).getLoangivenOn()+"'"
            + ",now())";
         
         
         
          s=s+methods.executeSQlQueryN(query);
         
        }
         if(s==toArchives.size()){
             success=true;
         }
         else{
             success=false;
         }
         return success;
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
            java.util.logging.Logger.getLogger(Iloanpayment.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Iloanpayment.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Iloanpayment.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Iloanpayment.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Iloanpayment().setVisible(true);
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
    private javax.swing.JTextField txtAlreadyPaid;
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
               txtAlreadyPaid.setText("");
}
public void deleteGuranters(String loanid){
    
    
              String query = "DELETE FROM `guranters`  WHERE `id` = '" +loanid+"'";
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
