/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imua.development;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import static java.lang.Thread.sleep;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author Kamau
 */
public class Main extends javax.swing.JFrame {
static int on;
  static  int a=0;
  static int  ch=0;
  Methods m = new Methods();
  String s = "Inua ";
  
    /**
     * Creates new form Main
     */
    public Main() {
         
      
        initComponents();
       //  ex();
       setTilteImage();
     
       
  
    pic();
    
     getTime();
       if(a==0){
         login();
        
       }
      
       
    // getTime();
        
    }
    private Color setTilteImage(){
        Color c=null;
        try {
            Methods n=new Methods();
            String t= n.setTitle();
            this.setTitle(t);
            String i=n.setIconImage();
            this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource(i)));
            
            String col=n.selectcolor();
             c=new Color(Integer.parseInt(col));
            jPanel1.setBackground(c);
            Container cont=this.getContentPane();
            cont.setBackground(c);
            jPanel2.setBackground(c);
            jToolBar1.setBackground(c);
            this.setForeground(c);
        } catch (Exception ex) {
            Logger.getLogger(Accgroups.class.getName()).log(Level.SEVERE, null, ex);
        }
        return c;
}
    
    public void check(){
Thread log=new Thread(){
public void run(){
    
     try{
      sleep(10000);
      checkTodays();
     }
      catch(Exception b){
         System.out.println("Error");
      }
} 
      }   ;  
      log.start();
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
          String searchQuery = "SELECT * FROM `loans`WHERE targetdate='" + DATE + "' ";
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
          Logger.getLogger(ProcessLoan.class.getName()).log(Level.SEVERE, null, ex);
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
        ProcessLoan a=new ProcessLoan();
         //a.typeoftransaction="withdrawal";
        a.setVisible(true);
        } 
              
              
         // JOptionPane.showMessageDialog(null, todays.size()+ "LOANS APPLICATIONS AVAILABLE");
          }
          checkLoanRepayments();
          
      } catch (SQLException ex) {
          Logger.getLogger(ProcessLoan.class.getName()).log(Level.SEVERE, null, ex);
      }
      
    }
    public  void checkIfThereIsNext(String autoid,String id,String inNo,String ltype){
        //Main ma=new Main();
    try {
        Methods n = new Methods();
        Connection con =n. getConnection();
        int inno=Integer.valueOf(inNo)-1;
        Statement st = con.createStatement();
        String searchQuery = "SELECT * FROM `loans`WHERE autoid>'" + autoid + "'AND id='"+id+"'"
                + " AND installmentsno='"+inno+"'AND loantype='"+ltype+"'  ";
        ResultSet rs = st.executeQuery(searchQuery);
        if (rs.next())
        {   
            
        }
        else{
           setNewTarget("autoid",autoid); 
            }
    } catch (SQLException ex) {
        Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
    }
    }
    public  void checkLoanRepayments(){
       
         HashMap<String,String>todays=new HashMap<String,String>();
           Calendar  c= Calendar.getInstance();
         Date today=addOne(c.getTime(),-1);
         
         java.util.Date d=(today);
  //Days d=Days.daysBeetween();
         java.sql.Date DATE=new java.sql.Date(d.getTime());
      try {
          Methods n = new Methods();
          String nl="np";
          Connection con =n. getConnection();
          
          Statement st = con.createStatement();
          String searchQuery = "SELECT * FROM `loans`WHERE targetdate<'" + DATE + "'AND nxp='"+nl+"' ";
          ResultSet rs = st.executeQuery(searchQuery);
          while (rs.next())
          {   String maxid=rs.getString("autoid");
              String inNo=rs.getString("installmentsno");
             String id= rs.getString("id");
             String loanType= rs.getString("loantype");
             
              checkIfThereIsNext(maxid,id,inNo,loanType);
              todays.put(rs.getString("id"), rs.getString("installmentamount"));
            
          }
          st.close();
          rs.close();
          con.close();
          
          if(todays.size()>0){
        int dialogButton=
        
        JOptionPane.showConfirmDialog(null,todays.size()+ "  LOANS DEFAULTED \n Select YES to check them out ");
        if(dialogButton==JOptionPane.YES_OPTION){
            
                    Defaulters kd=new Defaulters();
         //a.typeoftransaction="withdrawal";
                     kd.setVisible(true);
        }
          //JOptionPane.showMessageDialog(null, todays.size()+ "  LOANS DEFAULTED ");
          }
          //checkLoanRepayments();
          
      } catch (SQLException ex) {
          Logger.getLogger(ProcessLoan.class.getName()).log(Level.SEVERE, null, ex);
      }
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
        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        jButton13 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txttymer = new javax.swing.JLabel();
        jToolBar1 = new javax.swing.JToolBar();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenuItem9 = new javax.swing.JMenuItem();
        jMenuItem10 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem12 = new javax.swing.JMenuItem();
        jMenuItem13 = new javax.swing.JMenuItem();
        jMenu6 = new javax.swing.JMenu();
        jMenuItem8 = new javax.swing.JMenuItem();
        jMenuItem11 = new javax.swing.JMenuItem();
        jMenu8 = new javax.swing.JMenu();
        jMenu12 = new javax.swing.JMenu();
        jMenuItem14 = new javax.swing.JMenuItem();
        jMenu14 = new javax.swing.JMenu();
        jMenuItem16 = new javax.swing.JMenuItem();
        jMenu9 = new javax.swing.JMenu();
        jMenu10 = new javax.swing.JMenu();
        jMenuItem15 = new javax.swing.JMenuItem();
        jMenu15 = new javax.swing.JMenu();
        jMenu16 = new javax.swing.JMenu();
        jMenuItem17 = new javax.swing.JMenuItem();
        jMenuItem18 = new javax.swing.JMenuItem();
        jMenu11 = new javax.swing.JMenu();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenuItem7 = new javax.swing.JMenuItem();

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

        jButton1.setText("Account Deposit");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Account Withdraw");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Loans Repay");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("Open Accout");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setText("Reports");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setText("Quick Links");

        jButton6.setText("Loans Request");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton7.setText("Loan Processing");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jButton8.setText("Loan History");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jButton9.setText("Customers");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        jButton10.setText("Defaulters");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        jButton11.setText("Todays Repayments");
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });

        jButton12.setText("Org's Account");
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });

        jButton13.setText("Setings");
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addComponent(jLabel2))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jButton13, javax.swing.GroupLayout.DEFAULT_SIZE, 129, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jButton12, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton11, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                .addComponent(jButton10, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton9, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 129, Short.MAX_VALUE)
                                .addComponent(jButton4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                .addContainerGap(12, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton13)
                .addContainerGap(246, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1257, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 655, Short.MAX_VALUE))
        );

        txttymer.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txttymer.setText("Time");

        jToolBar1.setFloatable(false);

        jMenu2.setText("File");
        jMenu2.setMaximumSize(new java.awt.Dimension(47, 32767));

        jMenuItem1.setText("Backup");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem1);

        jMenuItem2.setText("Restore");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem2);

        jMenuItem5.setText("User Accounts");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem5);

        jMenuItem9.setText("Log Out");
        jMenuItem9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem9ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem9);

        jMenuItem10.setText("System Users");
        jMenuItem10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem10ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem10);

        jMenuItem3.setText("System Exit");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem3);

        jMenuBar1.add(jMenu2);

        jMenu3.setText(" Accounts");
        jMenu3.setMaximumSize(new java.awt.Dimension(70, 32767));

        jMenuItem4.setText("New Account");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem4);

        jMenuItem12.setText("Deposit");
        jMenuItem12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem12ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem12);

        jMenuItem13.setText("Withdraw");
        jMenuItem13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem13ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem13);

        jMenuBar1.add(jMenu3);

        jMenu6.setText("Loans");
        jMenu6.setMaximumSize(new java.awt.Dimension(60, 32767));
        jMenu6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu6ActionPerformed(evt);
            }
        });

        jMenuItem8.setText("Loan Request");
        jMenuItem8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem8ActionPerformed(evt);
            }
        });
        jMenu6.add(jMenuItem8);

        jMenuItem11.setText("Pay Loan");
        jMenuItem11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem11ActionPerformed(evt);
            }
        });
        jMenu6.add(jMenuItem11);

        jMenuBar1.add(jMenu6);

        jMenu8.setText("Reports");
        jMenu8.setMaximumSize(new java.awt.Dimension(60, 32767));

        jMenu12.setText("Accounts");

        jMenuItem14.setText("Personal Report");
        jMenuItem14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem14ActionPerformed(evt);
            }
        });
        jMenu12.add(jMenuItem14);

        jMenu8.add(jMenu12);

        jMenu14.setText("Loans");

        jMenuItem16.setText("Personal Report");
        jMenuItem16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem16ActionPerformed(evt);
            }
        });
        jMenu14.add(jMenuItem16);

        jMenu8.add(jMenu14);

        jMenuBar1.add(jMenu8);

        jMenu9.setText("Staff");
        jMenu9.setMaximumSize(new java.awt.Dimension(60, 32767));
        jMenuBar1.add(jMenu9);

        jMenu10.setText("Quick Tools");
        jMenu10.setMaximumSize(new java.awt.Dimension(81, 32767));

        jMenuItem15.setText("Print Group Collection");
        jMenuItem15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem15ActionPerformed(evt);
            }
        });
        jMenu10.add(jMenuItem15);

        jMenuBar1.add(jMenu10);

        jMenu15.setText("Settings");
        jMenu15.setMaximumSize(new java.awt.Dimension(60, 32767));

        jMenu16.setText("Loans Settings");

        jMenuItem17.setText("Client With Saving");
        jMenuItem17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem17ActionPerformed(evt);
            }
        });
        jMenu16.add(jMenuItem17);

        jMenuItem18.setText("Client Without Saving");
        jMenuItem18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem18ActionPerformed(evt);
            }
        });
        jMenu16.add(jMenuItem18);

        jMenu15.add(jMenu16);

        jMenuBar1.add(jMenu15);

        jMenu11.setText("Help");
        jMenu11.setMaximumSize(new java.awt.Dimension(60, 32767));

        jMenuItem6.setText("About");
        jMenu11.add(jMenuItem6);

        jMenuItem7.setText("System Help");
        jMenu11.add(jMenuItem7);

        jMenuBar1.add(jMenu11);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 769, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txttymer)
                .addGap(226, 226, 226))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txttymer)
                    .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
                                 
        // TODO add your handling code here:
      
    
     public void pic(){
         BufferedImage d;
    try {
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
    }
    catch(Exception m){
        m.printStackTrace();
    }

    
    
    return a;
    
    
}
    public void setNewTarget(String where,String val){
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
               
             + ",'"+String.valueOf(Double.valueOf(loanbalance)-Double.valueOf(installmentamount)) +"'"
             + ",'"+nulll+"'"
             + ",'"+this.applicationfee+"'"
             + ",'" + nulll+ "'"
                           + ",'" + nxp+ "'"
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
    
    
    
    
    
    
    
    
    }
    
    
    //////////////////////////////////////
    //i must be a genios
    /////////////////////////////////////
    
    
    //its 5 am eric you going to die
    
    
    
    
    
public  void getTime(){
    
Thread clock=new Thread(){
public void run(){
    String x;
    String z;
for(;;){
Calendar cal=new GregorianCalendar();
        int month=cal.get(Calendar.MONTH);
        if (month+1==1){
            z="Jan";
        }
        else if(month+1==2){
        z="Feb";
}
        else if(month+1==3){
        z="Mar";
}
        else if(month+1==4){
        z="Apr";
}
        else if(month+1==5){
        z="May";
}
        else if(month+1==6){
        z="June";
}
        else if(month+1==7){
        z="July";
}
        else if(month+1==8){
        z="Aug";
}
        else if(month+1==9){
        z="Sept";
}
        else if(month+1==10){
        z="Oct";
}
        else if(month+1==11){
        z="Nov";
}
        else {
        z="Dec";
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
        checkLoanApplications1();
        txttymer.setText("DATE:   "+day+" "+z+" "+year+"   TIME:  "+hour+":"+min+":"+second+":"+x);
        if(a>0){
            setEnabled();
            a=0;
        }
         if(ch==1){
          checkTodays();
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
 MarqueePanel mp= new MarqueePanel(s, 100);
public void b(String h){
    mp= new MarqueePanel(h, 100);
    
     
      // MarqueePanel mp = new MarqueePanel(s, 100);
      jToolBar1. remove(mp);
     jToolBar1. add(mp);
        mp.start();
}
 private  void checkLoanApplications1(){
      String  s="i";
     String mesoLoan= checkLoanRepayments1();
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
             s=" "+todays.size()+ "  LOANS APPLICATIONS AVAILABLE FOR PROCESSING";
            
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
      String mesoYote=mesoLoan+""+s;
      b(mesoYote); 
    }
     public  String checkLoanRepayments1(){
       String meso="";
         HashMap<String,String>todays=new HashMap<String,String>();
           Calendar  c= Calendar.getInstance();
         Date today=addOne(c.getTime(),-1);
         
         java.util.Date d=(today);
  //Days d=Days.daysBeetween();
         java.sql.Date DATE=new java.sql.Date(d.getTime());
      try {
          Methods n = new Methods();
          String nl="np";
          Connection con =n. getConnection();
          
          Statement st = con.createStatement();
          String searchQuery = "SELECT * FROM `loans`WHERE targetdate<'" + DATE + "'AND nxp='"+nl+"' ";
          ResultSet rs = st.executeQuery(searchQuery);
          while (rs.next())
          {   String maxid=rs.getString("autoid");
              String inNo=rs.getString("installmentsno");
             String id= rs.getString("id");
             String loanType= rs.getString("loantype");
             
              checkIfThereIsNext(maxid,id,inNo,loanType);
              todays.put(rs.getString("id"), rs.getString("installmentamount"));
            
          }
          st.close();
          rs.close();
          con.close();
          
          if(todays.size()>0){
              
              meso=""+todays.size()+ "  LOANS DEFAULTED";
              
              
//        int dialogButton=
//        
//        JOptionPane.showConfirmDialog(null,todays.size()+ "  LOANS DEFAULTED \n Select YES to check them out ");
//        if(dialogButton==JOptionPane.YES_OPTION){
//            
//                    Defaulters kd=new Defaulters();
//         //a.typeoftransaction="withdrawal";
//                     kd.setVisible(true);
//        }
          //JOptionPane.showMessageDialog(null, todays.size()+ "  LOANS DEFAULTED ");
          }
          //checkLoanRepayments();
          
      } catch (SQLException ex) {
          Logger.getLogger(ProcessLoan.class.getName()).log(Level.SEVERE, null, ex);
      }
      return meso;
    }
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
        ProcessLoan a=new ProcessLoan();
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
    
    
    
    
    
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
          openDeposit();
      
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
         openWithdrawForm();
       
        
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
         openNewAccount();      
        
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
       OrgReport r=new OrgReport();
       r.setVisible(true);
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
      
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        // TODO add your handling code here:
        openNewAccount();
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jMenuItem8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem8ActionPerformed
     openLoanForm();
              
         
    }//GEN-LAST:event_jMenuItem8ActionPerformed

    private void jMenu6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu6ActionPerformed
     
        loanpayment a=new loanpayment();
        a.setVisible(true);
        
    }//GEN-LAST:event_jMenu6ActionPerformed

    private void jMenuItem11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem11ActionPerformed
        loanpayment a=new loanpayment();
        a.setVisible(true);
       
    }//GEN-LAST:event_jMenuItem11ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
         loanpayment a=new loanpayment();
        a.setVisible(true);
        
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jMenuItem12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem12ActionPerformed
       
      openDeposit();
    }//GEN-LAST:event_jMenuItem12ActionPerformed

    private void jMenuItem13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem13ActionPerformed
     openWithdrawForm();
    }//GEN-LAST:event_jMenuItem13ActionPerformed

    private void jMenuItem14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem14ActionPerformed
     openPersonalReport();
       
    }//GEN-LAST:event_jMenuItem14ActionPerformed

    private void jMenuItem17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem17ActionPerformed
    //  this.setEnabled(false);
        loantypes n= new loantypes();
    
      n.setVisible(true);
    }//GEN-LAST:event_jMenuItem17ActionPerformed

    private void jMenuItem18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem18ActionPerformed
         openLoanWithoutSavings();
    }//GEN-LAST:event_jMenuItem18ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
       openLoanForm();
    }//GEN-LAST:event_jButton6ActionPerformed

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

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
      //  this.setEnabled(false);
        Accgroups m = new Accgroups();
        m.setVisible(true);
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void jMenuItem10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem10ActionPerformed
       // this.setEnabled(false);
        changepass n =new changepass();
        
        n.setVisible(true);
    }//GEN-LAST:event_jMenuItem10ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
openLoanProcessing();       
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
      LoanHistory h=new LoanHistory();
      h.setVisible(true);
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
       Users u=new Users();
       u.setVisible(true);
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        Defaulters d=new Defaulters();
        d.setVisible(true);
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jMenuItem16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem16ActionPerformed
        LoanHistory h=new LoanHistory();
      h.setVisible(true);
    }//GEN-LAST:event_jMenuItem16ActionPerformed

    private void jMenuItem15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem15ActionPerformed
        GroupSheet gs=new GroupSheet();
        gs.setVisible(true);
    }//GEN-LAST:event_jMenuItem15ActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
         TodaysLoans tl=new TodaysLoans();
       tl.setVisible(true);
    }//GEN-LAST:event_jButton11ActionPerformed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
       OrgReport or=new OrgReport();
       or.setVisible(true);
    }//GEN-LAST:event_jButton12ActionPerformed

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
       Settings st=new Settings();
       st.setVisible(true);
           
       
    }//GEN-LAST:event_jButton13ActionPerformed
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
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu10;
    private javax.swing.JMenu jMenu11;
    private javax.swing.JMenu jMenu12;
    private javax.swing.JMenu jMenu13;
    private javax.swing.JMenu jMenu14;
    private javax.swing.JMenu jMenu15;
    private javax.swing.JMenu jMenu16;
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
    private javax.swing.JMenuItem jMenuItem10;
    private javax.swing.JMenuItem jMenuItem11;
    private javax.swing.JMenuItem jMenuItem12;
    private javax.swing.JMenuItem jMenuItem13;
    private javax.swing.JMenuItem jMenuItem14;
    private javax.swing.JMenuItem jMenuItem15;
    private javax.swing.JMenuItem jMenuItem16;
    private javax.swing.JMenuItem jMenuItem17;
    private javax.swing.JMenuItem jMenuItem18;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JMenuItem jMenuItem9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JLabel txttymer;
    // End of variables declaration//GEN-END:variables
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