/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imua.development.DepositandWithdrawal;

import imua.development.*;
import java.awt.Color;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ItemEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author Kamau
 */
public class Deposit extends javax.swing.JFrame {

    private String filePath;
    private String fileurlp = null;
    private String balance ="0";
    static String with = "withdrawal";
  public  static String typeoftransaction = null;
    static String title = null;
    private int p = 0;
 //private String typeOfAccount=null;
   String toLoan[];
     String accounts[];
    /**
     * Creates new form loan
     *
     */
    public Deposit() {
        initComponents();
        setTilteImage();
        findLoans();
    }
     public void findLoans()
  {
      Methods m=new Methods();
    ArrayList<String> usersList =  m.ListLoanTypes();
   toLoan=new String[usersList.size()];
   usersList.toArray(toLoan);
  // accountsTypes();
  }
    // private void accountsTypes() {
      //  = {"Regular Acc", "Projects Acc", "Table Acc"};
     //   toLoan = accounts;
       
   // }
   private void setTilteImage(){
        try {
            Methods n=new Methods();
            String t= n.setTitle();
            this.setTitle(t);
            String i=n.setIconImage();
            this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource(i)));
            
            String col=n.selectcolor();
            Color c=new Color(Integer.parseInt(col));
            jPanel1.setBackground(c);
           
        } catch (Exception ex) {
            Logger.getLogger(Deposit.class.getName()).log(Level.SEVERE, null, ex);
        }
}
//set Title 
    public void setTitle(String tite,String t) {
        lbltitle.setText(tite);
        lbltypeoftransaction.setText(t);
        
    }
//fetch persons account balance
    private String fetchBalance(String where, String value) {
        String balance = "0";
        try {
            Methods m = new Methods();
            Connection con = m.getConnection();
            Statement st = con.createStatement();
            Statement st1 = con.createStatement();
            String withi = "withdrawal";
            String depo = "deposits";
//fetches total deposits
            String searchQuery = "SELECT SUM(amount) FROM `transactions` WHERE `" + where + "` ="
                    + " '" + value + "'AND `typeoftransaction`='" + depo + "'";
//fetch total withdrawals
            String searchQuery2 = "SELECT SUM(amount) FROM `transactions` WHERE `" + where + "` ="
                    + " '" + value + "'AND `typeoftransaction`='" + withi + "'";

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
                          JOptionPane.showMessageDialog(null, "Has no deposit");
                    }
                    else{
                    a = Double.valueOf(rs1.getString(1));
                    b = Double.valueOf(rs.getString(1));
                    balance = String.valueOf(b - a);
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
//fetches customers details from user tbl by id or account no(where,value parameters)
    public void fetch(String value, String where) {

        try {
            Methods m = new Methods();
            Connection con = m.getConnection();
            Statement st = con.createStatement();
            String searchQuery = "SELECT * FROM `users` WHERE `" + where + "` = '" + value + "'";
            ResultSet rs = st.executeQuery(searchQuery);
            while (rs.next()) {
                txtSirname.setText(rs.getString("fname"));
                txtOtherName.setText(rs.getString("sname"));
                txtPhone.setText(rs.getString("phone"));
                txtAddress.setText(rs.getString("address"));
                txtTown.setText(rs.getString("town"));
                txtType.setText(rs.getString("acounttype"));
                txtCounty.setText(rs.getString("county"));
                txtOccupation.setText(rs.getString("occupation"));

                try {
                    if (typeoftransaction.equals("deposits")) 
                    {
//call fetchbalance method and give it the same parameters used to fetch user details
                        //return value from the fetchBalance method is set to variable balance
                        balance = fetchBalance(where, value);
                    } 
                    else 
                    {
//call fetchbalance method and give it the same parameters used to fetch user details
                        balance = fetchBalance(where, value);
                    }
                    //set txtBalance to the returned value
                    txtBalance.setText(balance);
                    showimg(where,value);

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

    }
//profile photo loading done here
    public void showimg(String where,String value)
            throws Exception 
    {
        
        this.img.setIcon(null);
        this.img.setText(" no image");
        try 
        {
            Methods m = new Methods();
           
            Connection con = m.getConnection();
            Statement st2 = con.createStatement();
///
            ResultSet res7 = st2.executeQuery("SELECT imgurl FROM users  WHERE `"+where+"`='" + value + "'");
            if (res7.next())
            {
                this.filePath = res7.getString("imgurl");

                st2.close();
                res7.close();
                con.close();
                
                String op = "image";
                if (this.filePath.equals(op))
                {
                    this.img.setIcon(null);
                    this.img.setIcon(null);
                    this.img.setText(" no image");
                } else 
                {
                    BufferedImage img = null;
                    try 
                    {
                        img = ImageIO.read(new java.io.File(this.filePath));
                        this.fileurlp = this.filePath.replace("\\", "\\\\");
                    } catch (IOException e) {
                        //JOptionPane.showMessageDialog(null, "error loading image \n  make sure image is in images folder ");

                        this.img.setIcon(null);
                        this.img.setText(" no image");
                    }
                    Image dimg = img.getScaledInstance(this.img.getWidth(), this.img.getHeight(), 4);

                    ImageIcon icon = new ImageIcon(dimg);
                    this.img.setText("");
                    this.img.setIcon(icon);
                }
                
            }
            else 
            {
                //JOptionPane.showMessageDialog(null, "error loading image \n  make sure image is in images folder ");
            }
        } 
        catch (Exception ex) 
        {
            System.out.println(ex.getMessage());
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
        img = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtId = new javax.swing.JTextField();
        txtSirname = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtOtherName = new javax.swing.JTextField();
        txtPhone = new javax.swing.JTextField();
        txtAddress = new javax.swing.JTextField();
        txtTown = new javax.swing.JTextField();
        txtOccupation = new javax.swing.JTextField();
        lbltypeoftransaction = new javax.swing.JLabel();
        txtDeposit = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        lbltitle = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jComboBox1 = new javax.swing.JComboBox();
        jLabel11 = new javax.swing.JLabel();
        txtModeId = new javax.swing.JTextField();
        modelbl = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        txtCounty = new javax.swing.JTextField();
        txtBalance = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtType = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setLocation(new java.awt.Point(200, 70));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        jLabel3.setText("ID No.");

        txtId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIdActionPerformed(evt);
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

        txtPhone.setEditable(false);

        txtAddress.setEditable(false);

        txtTown.setEditable(false);

        txtOccupation.setEditable(false);

        lbltypeoftransaction.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lbltypeoftransaction.setForeground(new java.awt.Color(255, 51, 102));
        lbltypeoftransaction.setText("Deposit");

        txtDeposit.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtDeposit.setForeground(new java.awt.Color(255, 0, 51));
        txtDeposit.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtDepositKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtDepositKeyReleased(evt);
            }
        });

        jButton1.setText("Confirm");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        lbltitle.setFont(new java.awt.Font("Rockwell Extra Bold", 0, 18)); // NOI18N
        lbltitle.setText("Customer Deposit Information Form");

        jButton3.setText("Save");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Mode", "Cash", "Mpesa", "Cheque" }));
        jComboBox1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox1ItemStateChanged(evt);
            }
        });
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jLabel11.setText("Payments Mode");

        txtModeId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtModeIdActionPerformed(evt);
            }
        });

        modelbl.setText("Mpesa Transaction ID");

        jLabel13.setText("Address");

        txtCounty.setEditable(false);

        txtBalance.setEditable(false);
        txtBalance.setText("0");

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 0, 255));
        jLabel14.setText("BALANCE");

        jLabel2.setText("Acc Type");

        txtType.setEditable(false);
        txtType.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTypeActionPerformed(evt);
            }
        });

        jButton2.setText("Clear");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel12.setText("User Info");

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel15.setText("Accounts Info");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton2)
                        .addGap(18, 18, 18)
                        .addComponent(jButton3)
                        .addGap(112, 112, 112))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(img, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 113, Short.MAX_VALUE)
                        .addComponent(lbltitle)
                        .addGap(200, 200, 200))))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(27, 27, 27)
                        .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel2))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtPhone, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtType, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addGap(188, 188, 188)))
                        .addGap(237, 237, 237))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel13)
                                .addGap(18, 18, 18)
                                .addComponent(txtAddress, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel5)
                                    .addGap(18, 18, 18)
                                    .addComponent(txtOtherName, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel12)
                                        .addComponent(txtSirname, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(txtCounty, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtTown, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addGap(18, 18, 18)
                                .addComponent(txtOccupation, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGap(16, 16, 16)
                                .addComponent(jLabel11))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(125, 125, 125)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel14)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addGap(45, 45, 45)
                                            .addComponent(lbltypeoftransaction))
                                        .addComponent(modelbl)))
                                .addGap(8, 8, 8)))))
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtModeId)
                    .addComponent(txtBalance, javax.swing.GroupLayout.DEFAULT_SIZE, 155, Short.MAX_VALUE)
                    .addComponent(txtDeposit)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jButton1)
                        .addComponent(jLabel15)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lbltitle)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(55, 55, 55)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel3)
                                    .addComponent(jButton1)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(90, 90, 90)
                                .addComponent(jLabel12))))
                    .addComponent(img, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel15)
                        .addGap(29, 29, 29)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtDeposit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbltypeoftransaction)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtSirname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addGap(9, 9, 9)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtOtherName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtPhone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtModeId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(modelbl))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtBalance, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel14))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 31, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtAddress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtTown, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtCounty, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8))))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtOccupation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addGap(29, 29, 29)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton3)
                    .addComponent(jButton2))
                .addGap(26, 26, 26))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
      try
           
       {
        if ( txtId.getText().isEmpty())
        {
            JOptionPane.showMessageDialog(null, "FILL ONE OF THE FIELDS");
        } 
        
       
        
        else
        {
              int v=Integer.valueOf(txtId.getText());
            fetch(txtId.getText(), "id");
        }
        
       }
       catch(HeadlessException | NumberFormatException r)
       {
           JOptionPane.showMessageDialog(null, "Enter correct Value For ID or Acc No");
       }
    }//GEN-LAST:event_jButton1ActionPerformed
//my main logic block
    //inserts the withdrawal or deposit amount
        private void insert() {
        int dialogButton=
        
        JOptionPane.showConfirmDialog(null, "Confirm Your Want TO  "+typeoftransaction+"  "+txtDeposit.getText()+"\n For  "+txtSirname.getText());
        if(dialogButton==JOptionPane.YES_OPTION){
        String a=null;
        String b=null;
        int wod=0;
        if(typeoftransaction.equals("deposits"))
        {    wod=1;
            a=txtDeposit.getText();
        }else if(typeoftransaction.equals("withdrawal"))
        {    wod=2;
             b=txtDeposit.getText();
        }
         String rm=jComboBox1.getSelectedItem()+"-" +txtModeId.getText();
        Methods m = new Methods();
        
        String query = "INSERT INTO `transactions`(`id`, `amount`, `modeofpayment`,`typeofaccount`,  `typeoftransaction`,`withdrawn`,"
                + "`deposited`,`balance`,`updated_at`) VALUES ('" + this.txtId.getText() + "','" + this.txtDeposit.getText() + "',"
                + "'" + rm + "','" + this.txtType.getText() + "','" + typeoftransaction + "',"
                + "'" + b + "','" + a + "','" + txtBalance.getText() + "',now())";

       int n= m.executeSQlQuery(query, "Inserted");
       if(n==1){
           if(wod==1){
              m.addToOrgAccount(Double.valueOf(a),"Customer Account Deposit",jComboBox1.getSelectedItem().toString() );
           }
           else {
                m.RemoveFromOrgAccount(Double.valueOf(b),"Customer Account Withdrawal",jComboBox1.getSelectedItem().toString());
           }
       clear(); 
       }
        }
        
    }
    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
       if (p == 0) {
            if (txtDeposit.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Enter correct value for  " + typeoftransaction + "  Amount");
            } else {
                int a = Integer.valueOf(txtDeposit.getText());
                try {
                    validateInput();

                } catch (Exception n) {
                    JOptionPane.showMessageDialog(null, "Enter correct value for  " + typeoftransaction + "  Amount");
                }
            }
        } else {
     JOptionPane.showMessageDialog(null, "Enter correct value for  " + p+ "  Amount");
        }


    }//GEN-LAST:event_jButton3ActionPerformed
    private void validateInput() {
        try {
            Double dep = Double.valueOf(txtDeposit.getText());

            if (txtSirname.getText().isEmpty()
                    || txtOtherName.getText().isEmpty()
                    || txtPhone.getText().isEmpty()
                    || txtAddress.getText().isEmpty()
                    //|| txtTown.getText().isEmpty()
                    || txtType.getText().isEmpty()
                   // || txtCounty.getText().isEmpty()
                   // || txtOccupation.getText().isEmpty()
                    || // txtId.getText().isEmpty()||
                    // txtAccountNo.getText().isEmpty()||
                        txtDeposit.getText().isEmpty()
                    || txtBalance.getText().isEmpty()
                    || txtModeId.getText().isEmpty()
                    || jComboBox1.getSelectedIndex() == 0 // img.setIcon(null);
                    // filePath = null;
                    // fileurlp = null;
                    ) {
                JOptionPane.showMessageDialog(null, "Fill All FIELDS");

            } else {
                insert();
            }
        } catch (Exception n) {

        }

    }
    private void jComboBox1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox1ItemStateChanged

        String choice[] = {"Mode", "Cash", "Mpesa", "Cheque",};

        if (evt.getItem() == choice[1] && evt.getStateChange() == ItemEvent.SELECTED) {
            modelbl.setText(choice[1]+" Reciept No");

        }
        if (evt.getItem() == choice[2] && evt.getStateChange() == ItemEvent.SELECTED) {
            modelbl.setText(choice[2]+" Trans NO");

        }
        if (evt.getItem() == choice[3] && evt.getStateChange() == ItemEvent.SELECTED) {
            modelbl.setText(choice[3]+" Cheque No");

        }

        if (evt.getItem() == choice[0] && evt.getStateChange() == ItemEvent.SELECTED) {
            modelbl.setText("Mode");

        }
    }//GEN-LAST:event_jComboBox1ItemStateChanged

    private void txtTypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTypeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTypeActionPerformed

    private void txtDepositKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDepositKeyReleased
       if (balance != null) {

            try {
                Double d = Double.valueOf(txtDeposit.getText());
                Double b = Double.valueOf(balance);

                if (typeoftransaction.equals("withdrawal")) {
                   Double comittedbal= getAccountBalanceNotFixed();
                    Double r = (b-comittedbal) - d;
                    if (r >=0.0) {
                        p = 0;
                        txtBalance.setText(String.valueOf(r));
                    } else {
                        p = 1;
                        JOptionPane.showMessageDialog(null, "account balance is insufficient");
                        return;
                    }
                } else if (typeoftransaction.equals("deposits")) {
                    txtBalance.setText(String.valueOf(b + d));
                }

            } catch (Exception n) {
                txtBalance.setText(balance);
            }
        }
    }//GEN-LAST:event_txtDepositKeyReleased

    private void txtDepositKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDepositKeyPressed

    }//GEN-LAST:event_txtDepositKeyPressed

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        Main.a = 1;
    }//GEN-LAST:event_formWindowClosed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        clear();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void txtModeIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtModeIdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtModeIdActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void txtIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIdActionPerformed
         try
           
       {
        if ( txtId.getText().isEmpty())
        {
            JOptionPane.showMessageDialog(null, "FILL ONE OF THE FIELDS");
        } 
        
       
        
        else
        {
              int v=Integer.valueOf(txtId.getText());
            fetch(txtId.getText(), "id");
        }
        
       }
       catch(HeadlessException | NumberFormatException r)
       {
           JOptionPane.showMessageDialog(null, "Enter correct Value For ID or Acc No");
       }
    }//GEN-LAST:event_txtIdActionPerformed

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
            java.util.logging.Logger.getLogger(Deposit.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Deposit.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Deposit.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Deposit.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Deposit().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel img;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lbltitle;
    private javax.swing.JLabel lbltypeoftransaction;
    private javax.swing.JLabel modelbl;
    private javax.swing.JTextField txtAddress;
    private javax.swing.JTextField txtBalance;
    private javax.swing.JTextField txtCounty;
    private javax.swing.JTextField txtDeposit;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtModeId;
    private javax.swing.JTextField txtOccupation;
    private javax.swing.JTextField txtOtherName;
    private javax.swing.JTextField txtPhone;
    private javax.swing.JTextField txtSirname;
    private javax.swing.JTextField txtTown;
    private javax.swing.JTextField txtType;
    // End of variables declaration//GEN-END:variables
private void clear() {

        txtSirname.setText("");
        txtOtherName.setText("");
        txtPhone.setText("");
        txtAddress.setText("");
        txtTown.setText("");
        txtType.setText("");
        txtCounty.setText("");
        txtOccupation.setText("");
        txtId.setText("");
       // txtAccountNo.setText("");
        txtDeposit.setText("");
        txtBalance.setText("");
        txtModeId.setText("");
        jComboBox1.setSelectedIndex(0);
        img.setIcon(null);
        filePath = null;
        fileurlp = null;

    }
public Double getAccountBalanceNotFixed(){
    Double xc=0.0;
   int a=toLoan.length;
    String tdpy="0";
   for(int b=0;b<a;b++){
       
       try {
           Methods m = new Methods();
           Connection con = m.getConnection();
           Statement st = con.createStatement();
           
           String searchQuery = "SELECT loanRequested FROM `loans` WHERE `loantype` = '" +toLoan[b]+ "'"
                   + "AND `todaypay`='"+tdpy+"'AND `id`='"+txtId.getText()+"'";
           ResultSet rs = st.executeQuery(searchQuery);
           if(rs.next()){
               
               
               
            xc=xc+    Double.valueOf(rs.getString("loanRequested"))/specifBalance(toLoan[b]);
              // ballevel=rs.getString("balance");
               
               
               
           }
       } catch (SQLException ex) {
           Logger.getLogger(Deposit.class.getName()).log(Level.SEVERE, null, ex);
       }
       
       
       
   }
    
    
    
    return xc;
    
}
public Double specifBalance(String where){
    Double val=0.0;
       try {
           Methods m = new Methods();
           Connection con = m.getConnection();
           Statement st = con.createStatement();
           
           String searchQuery = "SELECT balance FROM `accounttypes` WHERE `name` = '" +where+ "'";
           ResultSet rs = st.executeQuery(searchQuery);
           if(rs.next()){
               
               val=Double.valueOf(rs.getString("balance"));
               
               
               
           }
       } catch (SQLException ex) {
           Logger.getLogger(Deposit.class.getName()).log(Level.SEVERE, null, ex);
       } 
       return val;
}
}
