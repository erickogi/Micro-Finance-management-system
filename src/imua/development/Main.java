/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imua.development;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import static java.lang.Thread.sleep;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author Kamau
 */
public class Main extends javax.swing.JFrame {
static int on;
  static  int a=0;
    /**
     * Creates new form Main
     */
    public Main() {
        initComponents();
       
       setTitle();
        
       
   
    pic();
    
     getTime();
       if(a==0){
         login();
       }
       
    // getTime();
        
    }
    public void setTitle(){
         Methods n=new Methods();
   String t= n.setTitle();
    this.setTitle(t);
    String i=n.setIconImage();
    this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource(i)));
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
    
    public static void checkTodays(){
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
            
        }  
              
         // JOptionPane.showMessageDialog(null, todays.size()+ "LOANS REPAYMENT EXPECTED TODAY");
          }
          checkLoanApplications();
          
      } catch (SQLException ex) {
          Logger.getLogger(ProcessLoan.class.getName()).log(Level.SEVERE, null, ex);
      }
      
      //c.add(Calendar.MONTH, hw);
      
    } 
    private static void checkLoanApplications(){
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
    public static  void checkLoanRepayments(){
         Calendar  c= Calendar.getInstance();
         HashMap<String,String>todays=new HashMap<String,String>();
         Date today=addOne(c.getTime(),-2);
         
         java.util.Date d=(today);
  
         java.sql.Date DATE=new java.sql.Date(d.getTime());
      try {
          Methods n = new Methods();
          String nl="null";
          Connection con =n. getConnection();
          
          Statement st = con.createStatement();
          String searchQuery = "SELECT * FROM `loans`WHERE targetdate<'" + DATE + "'AND todaypay='"+nl+"' ";
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
        
        JOptionPane.showConfirmDialog(null,todays.size()+ "  LOANS DEFAULTED \n Select YES to check them out ");
        if(dialogButton==JOptionPane.YES_OPTION){
            
        }
          //JOptionPane.showMessageDialog(null, todays.size()+ "  LOANS DEFAULTED ");
          }
          //checkLoanRepayments();
          
      } catch (SQLException ex) {
          Logger.getLogger(ProcessLoan.class.getName()).log(Level.SEVERE, null, ex);
      }
    }

     public static Date addOne(Date date,int hw){
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
        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txttymer = new javax.swing.JLabel();
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
        jMenuItem15 = new javax.swing.JMenuItem();
        jMenu14 = new javax.swing.JMenu();
        jMenuItem16 = new javax.swing.JMenuItem();
        jCheckBoxMenuItem1 = new javax.swing.JCheckBoxMenuItem();
        jMenu9 = new javax.swing.JMenu();
        jMenu10 = new javax.swing.JMenu();
        jMenu15 = new javax.swing.JMenu();
        jMenu16 = new javax.swing.JMenu();
        jMenuItem17 = new javax.swing.JMenuItem();
        jMenuItem18 = new javax.swing.JMenuItem();
        jMenu11 = new javax.swing.JMenu();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenuItem7 = new javax.swing.JMenuItem();

        jMenu1.setText("jMenu1");

        jMenu13.setText("jMenu13");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
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

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(jLabel2)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, 129, Short.MAX_VALUE)
                                    .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jLabel2)
                .addGap(33, 33, 33)
                .addComponent(jButton4)
                .addGap(31, 31, 31)
                .addComponent(jButton1)
                .addGap(27, 27, 27)
                .addComponent(jButton2)
                .addGap(28, 28, 28)
                .addComponent(jButton3)
                .addGap(24, 24, 24)
                .addComponent(jButton6)
                .addGap(18, 18, 18)
                .addComponent(jButton7)
                .addGap(18, 18, 18)
                .addComponent(jButton5)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 700, Short.MAX_VALUE)
        );

        txttymer.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txttymer.setText("Time");

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

        jMenuItem15.setText("All Accounts");
        jMenuItem15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem15ActionPerformed(evt);
            }
        });
        jMenu12.add(jMenuItem15);

        jMenu8.add(jMenu12);

        jMenu14.setText("Loans");

        jMenuItem16.setText("Personal Report");
        jMenu14.add(jMenuItem16);

        jCheckBoxMenuItem1.setSelected(true);
        jCheckBoxMenuItem1.setText("All Loan Report");
        jMenu14.add(jCheckBoxMenuItem1);

        jMenu8.add(jMenu14);

        jMenuBar1.add(jMenu8);

        jMenu9.setText("Staff");
        jMenu9.setMaximumSize(new java.awt.Dimension(60, 32767));
        jMenuBar1.add(jMenu9);

        jMenu10.setText("Quick Tools");
        jMenu10.setMaximumSize(new java.awt.Dimension(81, 32767));
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
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txttymer)
                .addGap(226, 226, 226))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(txttymer)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
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
        txttymer.setText("DATE:   "+day+" "+z+" "+year+"   TIME:  "+hour+":"+min+":"+second+":"+x);
        if(a>0){
            setEnabled();
            a=0;
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
        // TODO add your handling code here:
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
       openLoanForm(); 
       
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

    private void jMenuItem15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem15ActionPerformed
     openAccountsReport();
        

    }//GEN-LAST:event_jMenuItem15ActionPerformed

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
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItem1;
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
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenu jMenu8;
    private javax.swing.JMenu jMenu9;
    private javax.swing.JMenuBar jMenuBar1;
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
    private javax.swing.JLabel txttymer;
    // End of variables declaration//GEN-END:variables
}
