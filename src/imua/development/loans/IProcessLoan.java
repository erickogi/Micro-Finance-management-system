/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imua.development.loans;

//import static imua.development.loanform.y;
import IDataHolders.ILoanDetailsDataHolder;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Toolkit;
import java.awt.event.ItemEvent;
import java.sql.Connection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JSpinner.DefaultEditor;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import imua.development.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.JComboBox;

/**
 *
 * @author kimani kogi
 */
public class IProcessLoan extends javax.swing.JFrame {
    Methods methods = new Methods();
    
    
    
    
    
   Double rate;
   String rateper;
   
   
   //used to determine if loan is applicable to reducing balance intrest rates or not
   String applicableStatus;
   
   
   
   int rateTime;
   Double appfee;
   Double penalty;
   String timeUnit;
   int frequency=1;
   int jsNoOfInstallments;
   Double txtValueOfInstallmentAmount=0.0;
   int y=1;
   String loanAmount;
    /**
     * Creates new form ProcessLoan
     */
    public IProcessLoan() {
        initComponents();
        setTilteImage();
        refresh();
        getNewRenderedTable(table);
        
        //sets this label invisible it will be set otherwise as per the type of loan requested
        lblnoOfWeeks.setVisible(false);
        //sets frequency spinner  un editable will be set otherwise as per the type of loan requested
       ((DefaultEditor)jSpinnerFrequency.getEditor()).getTextField().setEditable(false);
       
       jDateChooser1.getDateEditor().addPropertyChangeListener(
    new PropertyChangeListener() {
        @Override
        public void propertyChange(PropertyChangeEvent e) {
              Date a=null;
         try{
         if(timeUnit.equals("Weekly")){
           Date f= jDateChooser1.getDate();
           Calendar  c= Calendar.getInstance();
           a=addWeek(f,frequency);
        }
            
            
        else if (timeUnit.equals("Monthly")){
         Date f= jDateChooser1.getDate();
         Calendar  c= Calendar.getInstance();
         a=addMonth(f,frequency);
        }
        
        
        else if (timeUnit.equals("Daily")){
         Date f= jDateChooser1.getDate();
         Calendar  c= Calendar.getInstance();
         a=addDay(f,frequency);
        }
        
        
        else if (timeUnit.equals("Yearly")){
         Date f= jDateChooser1.getDate();
         Calendar  c= Calendar.getInstance();
         a=addYear(f,frequency);
        }
        
         jDateChooser2.setDate(a);
            
         }
         catch(Exception ad){
          Calendar  c1= Calendar.getInstance();
        
           Date   a1=addYear( c1.getTime(),frequency);
           
           jDateChooser2.setDate(a1);
             //JOptionPane.showMessageDialog(null, "Enter other fields first");
         }
            
            
            
            
            
            
            
            
        }
        

           
    });
       
       
       
       
       
      
    }
    
    //okay--------sets frame icon,frame and panels colour from class Methods
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
             Container cont=this.getContentPane();
            cont.setBackground(c);
            jPanel2.setBackground(c);
        } catch (Exception ex) {
            Logger.getLogger(IProcessLoan.class.getName()).log(Level.SEVERE, null, ex);
        }
}
   
//okay---------gets loans applications 
public ArrayList<ApplicationData> ListUsers( String Id)
  {
    ArrayList<ApplicationData> usersList = new ArrayList();
    try
    {
        Methods m=new Methods();
        Connection con = m.getConnection();
     // Connection con = getConnection();
      Statement st = con.createStatement();
     // String searchQuery = "SELECT * FROM `accounttypes`";
      String searchQuery = "SELECT * FROM `applications` WHERE CONCAT(`id`,`name`) LIKE '%" + Id + "%'";
      ResultSet rs = st.executeQuery(searchQuery);
      while (rs.next())
      {
        ApplicationData user = new ApplicationData(rs.getString("id"), rs.getString("name"),rs.getString("amount")
                ,rs.getString("updated_at")
                ,rs.getString("status")
                ,rs.getString("type")
                ,rs.getString("guranters")
        
        
        );
        
        usersList.add(user);
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


//okay ----------refresh jtable after any changes
  private void refresh(){
       
        DefaultTableModel model = (DefaultTableModel)this.table.getModel();
        
        model.setRowCount(0);
        
        findUsers();
  }
  
  
//okay -------displays all loans applications on the jtable
  public void findUsers() 
  {
    ArrayList<ApplicationData> users = ListUsers(txtFilter.getText());
    DefaultTableModel model = new DefaultTableModel();
    
    model.setColumnIdentifiers(new Object[] { "DATE","ID", "NAME","AMOUNT","STATUS","TYPE","GURANTERS"});
    Object[] row = new Object[7];
    for (int i = 0; i < users.size(); i++)
    {
      row[0] = ((ApplicationData)users.get(i)).getDate();
    
      row[1] = ((ApplicationData)users.get(i)).getID();
      
      row[2]= ((ApplicationData) users.get(i)).getName();
      row[3]=((ApplicationData) users.get(i)).getAmount();
      row[4] = ((ApplicationData) users.get(i)).getStatus();
       row[5] = ((ApplicationData) users.get(i)).getType();
        row[6] = ((ApplicationData) users.get(i)).getGuranters();
   

      model.addRow(row);
    }
    this.table.setModel(model);
  
  }
  
  
//okay-------calculates days between two dates
 public static long daysBetween(Calendar startDate, Calendar endDate) {  
  Calendar date = (Calendar) startDate.clone();  
  long daysBetween = 0;  
  while (date.before(endDate)) {  
    date.add(Calendar.DAY_OF_MONTH, 1);  
    daysBetween++;  
  }  
  return daysBetween;  
}  
 
 
//okay -------shows diffrence in days
public void het(Date now,Date thn){
    Calendar calendar1 = Calendar.getInstance();
      Calendar calendar2 = Calendar.getInstance();
      calendar1.setTime(thn);
      calendar2.setTime(now);
      long milsecs1= calendar1.getTimeInMillis();
      long milsecs2 = calendar2.getTimeInMillis();
      long diff = milsecs2 - milsecs1;
      long dsecs = diff / 1000;
      long dminutes = diff / (60 * 1000);
      long dhours = diff / (60 * 60 * 1000);
      long ddays = diff / (24 * 60 * 60 * 1000);

      System.out.println("Your Day Difference="+ddays);
}



//okay ------gets loan details by id into ILoanDetailsDataHolder and into global variables
public ArrayList<ILoanDetailsDataHolder> getLoanDetails(String a){
       ArrayList<ILoanDetailsDataHolder> dataList = new ArrayList();
      try {
          
          
          Connection con = methods. getConnection();
          
          Statement st = con.createStatement();
          String searchQuery = "SELECT * FROM `accounttypes`WHERE name='" + a + "' ";
          ResultSet rs = st.executeQuery(searchQuery);
          if (rs.next())
          {
      String   iapplicableStatus=rs.getString("applicable");
      if(iapplicableStatus.equals("A")){
                txtPreferedInstallmentsValue.setEditable(false);
            }
        ILoanDetailsDataHolder user = new ILoanDetailsDataHolder(rs.getString("ratePer"),Integer.valueOf(rs.getString("rateTime")),
                rs.getString("applicable")
                ,Double.valueOf(rs.getString("rate"))
                ,Double.valueOf(rs.getString("appfee"))
                ,Double.valueOf(rs.getString("penalt"))
               // ,rs.getString("guranters")
        
        
        );
        
        dataList.add(user);
      
           rateper=rs.getString("ratePer");
           rateTime=Integer.valueOf(rs.getString("rateTime"));
           applicableStatus=rs.getString("applicable");
           rate=Double.valueOf(rs.getString("rate"))   ;
           appfee=Double.valueOf(rs.getString("appfee"));
           penalty= Double.valueOf(rs.getString("penalt"));
           if(applicableStatus.equals("A")){
                txtPreferedInstallmentsValue.setEditable(false);
           }
          }
          st.close();
          rs.close();
          con.close();
      } catch (SQLException ex) {
          Logger.getLogger(IProcessLoan.class.getName()).log(Level.SEVERE, null, ex);
      }
      return dataList;
    }
 

//okay---------renders the jtable based on days loanapplication has been in waiting
private static JTable getNewRenderedTable(final JTable table) {
        table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer(){
            @Override
            public Component getTableCellRendererComponent(JTable table,
                    Object value, boolean isSelected, boolean hasFocus, int row, int col) {
                super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, col);
                String status = (String)table.getModel().getValueAt(row, 0);
                if ("void".equals(status)) {
                    setBackground(Color.BLACK);
                    setForeground(Color.WHITE);
                } else {
                    setBackground(table.getBackground());
                    setForeground(table.getForeground());
                }       
                return this;
            }   
        });
        return table;
    }
      
  
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuItem1 = new javax.swing.JMenuItem();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        txtFilter = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        lblId = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        txtName = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtAmount = new javax.swing.JTextField();
        lblStatus = new javax.swing.JLabel();
        lblDate = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jComboBoxChooseMode = new javax.swing.JComboBox<>();
        jSpinnerFrequency = new javax.swing.JSpinner();
        jLabel4 = new javax.swing.JLabel();
        lblnoOfWeeks = new javax.swing.JLabel();
        jSpinnerNoOfInstalments = new javax.swing.JSpinner();
        txtPreferedInstallmentsValue = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        lblTime = new javax.swing.JLabel();
        lblInstallment = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        lblType = new javax.swing.JLabel();
        lblPersonId = new javax.swing.JLabel();
        totalpayable = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        payablewithin = new javax.swing.JLabel();
        txtTotal = new javax.swing.JLabel();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jComboBoxMode = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jDateChooser2 = new com.toedter.calendar.JDateChooser();
        jLabel11 = new javax.swing.JLabel();

        jMenuItem1.setText("jMenuItem1");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(table);

        txtFilter.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtFilterKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtFilter)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 552, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtFilter, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 553, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "LOAN DETAILS", javax.swing.border.TitledBorder.LEADING, javax.swing.border.TitledBorder.DEFAULT_POSITION));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel1.setText("Name ");

        txtName.setEditable(false);

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel2.setText("Requested Amount");

        lblStatus.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblStatus.setText("status of application");

        lblDate.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblDate.setText("date of application");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setText("PAYMENT MODE");

        jComboBoxChooseMode.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Choose", "Weekly", "Monthly", "Daily", "Yearly" }));
        jComboBoxChooseMode.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBoxChooseModeItemStateChanged(evt);
            }
        });

        jSpinnerFrequency.setModel(new javax.swing.SpinnerNumberModel(1, 1, 4, 1));
        jSpinnerFrequency.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jSpinnerFrequency.setFocusCycleRoot(true);
        jSpinnerFrequency.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSpinnerFrequencyStateChanged(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setText("AFTER EVERY");

        lblnoOfWeeks.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblnoOfWeeks.setText("Weeks");
        lblnoOfWeeks.setEnabled(false);
        lblnoOfWeeks.setFocusCycleRoot(true);

        jSpinnerNoOfInstalments.setModel(new javax.swing.SpinnerNumberModel(0, 0, null, 1));
        jSpinnerNoOfInstalments.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSpinnerNoOfInstalmentsStateChanged(evt);
            }
        });

        txtPreferedInstallmentsValue.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPreferedInstallmentsValueKeyReleased(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel5.setText("Prefered Installments value");

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel6.setText("Prefered No Of Installments");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel7.setText("ENTER ONLY ONE OF THE  CHOICES BELOW ");

        lblTime.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblTime.setText("TIME");

        lblInstallment.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblInstallment.setText("INSTALLMENT");

        jButton1.setText("PROCESS");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("CLEAR");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("DELETE");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        lblType.setText("TYPE OF LOAN");

        lblPersonId.setText("PERSONS ID");

        totalpayable.setText("TOTAL PAYABLE");

        jLabel9.setText("IN");

        payablewithin.setText("THIS TIME");

        txtTotal.setText("TOTAL");

        jDateChooser1.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jDateChooser1PropertyChange(evt);
            }
        });

        jComboBoxMode.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Mode", "Cash", "Mpesa", "Cheque" }));
        jComboBoxMode.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBoxModeItemStateChanged(evt);
            }
        });

        jLabel8.setText("Start Count As From");

        jLabel10.setText("Mode");

        jDateChooser2.setEnabled(false);
        jDateChooser2.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jDateChooser2PropertyChange(evt);
            }
        });

        jLabel11.setText("First Installment On");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(lblType, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(78, 78, 78))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jButton2)
                                .addGap(35, 35, 35)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton3))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(lblStatus, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(txtName, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(lblPersonId, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(136, 136, 136)
                                                .addComponent(lblId))
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(18, 18, 18)
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                    .addComponent(txtAmount)
                                                    .addComponent(lblDate, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)))))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jComboBoxMode, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jLabel10)
                                            .addComponent(jLabel8))))
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(lblTime, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(56, 56, 56)
                                .addComponent(lblInstallment, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jLabel6)
                                            .addComponent(jSpinnerNoOfInstalments, javax.swing.GroupLayout.Alignment.TRAILING))
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel5)
                                            .addComponent(txtPreferedInstallmentsValue, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(86, 86, 86)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                                .addComponent(lblnoOfWeeks, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBoxChooseMode, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jSpinnerFrequency, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(payablewithin, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel9)
                                .addComponent(totalpayable)))))
                .addGap(52, 52, 52))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jDateChooser2, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel11)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblId))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblType)
                        .addComponent(lblPersonId)))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtAmount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblStatus)
                    .addComponent(lblDate))
                .addGap(25, 25, 25)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(totalpayable))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBoxChooseMode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(lblnoOfWeeks)
                    .addComponent(payablewithin))
                .addGap(11, 11, 11)
                .addComponent(jSpinnerFrequency, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(26, 26, 26)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jSpinnerNoOfInstalments, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtPreferedInstallmentsValue, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(33, 33, 33)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblTime)
                            .addComponent(lblInstallment)
                            .addComponent(txtTotal))
                        .addGap(22, 22, 22)
                        .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jDateChooser2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jComboBoxMode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton2)
                            .addComponent(jButton1)
                            .addComponent(jButton3))
                        .addGap(39, 39, 39))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    
    /*okay-----determines default { totalAmountpayable and within how long from intrest (rate*loanAmount)+loanAmount} 
      b4 customer chooses period or amount*/
    private void getPayableAmount(String loanAmount){
    if(applicableStatus.equals("A")){
    Double payable=(rate*Double.valueOf(loanAmount))+Double.valueOf(loanAmount);
    int withinTime=rateTime;
    String withinPer=rateper;
    
   totalpayable.setText(String.valueOf(payable));
    
   payablewithin.setText(String.valueOf(withinTime)+" "+withinPer);
    
    }
}
    
    
    
    //okay-------gets customers /returns ..to be set in garanters form if garanters had not being filled
    public  String getId(String id){
   String   ID=null;
    if (id.isEmpty()){
        
    }
    else{
  garanters.id=id;
    }
    return ID;
}
    
    
    //okay-------once jtable row is clicked we get details/ check status of garanters and prompt accordingly
                 //we also initiate getting loan details by loan name as in the row clicked
                 //we also getDefault payable amount and time by loan amount(parameter)
    private void tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableMouseClicked
   
        
        int i = this.table.getSelectedRow();
    
    TableModel model = this.table.getModel();
   
       
    if(model.getValueAt(i, 6).toString().equals("void")){
        Object []options={"FILL IT","CONTINUE ANYWAY","CANCEL"};
           int btn= JOptionPane.showOptionDialog(null, "GURANTERS FORM NOT FIELD","CONFIRM",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.QUESTION_MESSAGE,null,options,options[0]);
        switch (btn) {
            //if continue anyway was selected
            case 1:
                lblPersonId.setText(model.getValueAt(i, 1).toString());///customer id
                this.txtName.setText(model.getValueAt(i, 2).toString());///customer name
                this.lblType.setText(model.getValueAt(i, 5).toString());///loan type
                this.txtAmount.setText(model.getValueAt(i, 3).toString());///requested amount
                this.lblDate.setText(model.getValueAt(i, 0).toString());///request date
                this.lblStatus.setText(model.getValueAt(i, 4).toString());///if accepted or not 
                                                                         //may be not accepted if garantrs not filled
                getLoanDetails(model.getValueAt(i, 5).toString());  /// getting loan details by loan name as in the row clicked
                getPayableAmount(model.getValueAt(i, 3).toString());///getDefault payable amount and time by loan amount(parameter)
                
                
                break;
                //if cancel was selected
            case 2:
                clear();
                
                break;
                
                
        ///if fill it was selected....launches garanters form
            default:
        String PersonId=(model.getValueAt(i, 1).toString());
        getId(PersonId);
        garanters n = new garanters();
        n.setVisible(true);
        
        
                break;
        }
    }
    else{
        //if loan status is accepted
               lblPersonId.setText(model.getValueAt(i, 1).toString());///customer id
                this.txtName.setText(model.getValueAt(i, 2).toString());///customer name
                this.lblType.setText(model.getValueAt(i, 5).toString());///loan type
                this.txtAmount.setText(model.getValueAt(i, 3).toString());///requested amount
                this.lblDate.setText(model.getValueAt(i, 0).toString());///request date
                this.lblStatus.setText(model.getValueAt(i, 4).toString());///if accepted or not 
                                                                         //may be not accepted if garantrs not filled
                getLoanDetails(model.getValueAt(i, 5).toString());  /// getting loan details by loan name as in the row clicked
                getPayableAmount(model.getValueAt(i, 3).toString());///getDefault payable amount and time by loan amount(parameter)
                
               
    }
    
    }//GEN-LAST:event_tableMouseClicked
//okay-------sets no of periods 
    private void jComboBoxChooseModeItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBoxChooseModeItemStateChanged
     if(evt.getItem()!=""&&evt.getStateChange()==ItemEvent.SELECTED&&evt.getItem()!="Choose"){

      timeUnit=evt.getItem().toString();
      lblnoOfWeeks.setText(timeUnit);
   

     }
     else{
      timeUnit="PER";  
       lblnoOfWeeks.setText(timeUnit);
      } 
        
    }//GEN-LAST:event_jComboBoxChooseModeItemStateChanged

    private void jSpinnerFrequencyStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSpinnerFrequencyStateChanged
//frequency=1;    
      frequency=(Integer)jSpinnerFrequency.getValue();

//  lblPersonId.setText(evt.);
      //  JOptionPane.showMessageDialog(null, d);
    }//GEN-LAST:event_jSpinnerFrequencyStateChanged
//okay------
    private void jSpinnerNoOfInstalmentsStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSpinnerNoOfInstalmentsStateChanged
       txtValueOfInstallmentAmount=0.0;
       txtPreferedInstallmentsValue.setText("");
       
        jsNoOfInstallments=(Integer)jSpinnerNoOfInstalments.getValue();
        checkWhich(jsNoOfInstallments,1);
        
    }//GEN-LAST:event_jSpinnerNoOfInstalmentsStateChanged

    private void txtPreferedInstallmentsValueKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPreferedInstallmentsValueKeyReleased
      jsNoOfInstallments=0;
      // jSpinnerNoOfInstalments.setValue(0);
        
        try{
        txtValueOfInstallmentAmount=Double.valueOf(txtPreferedInstallmentsValue.getText());
         checkWhich(Integer.valueOf(txtPreferedInstallmentsValue.getText()),2);
       }
       catch(NumberFormatException S){
           S.printStackTrace();
       }
       
    }//GEN-LAST:event_txtPreferedInstallmentsValueKeyReleased
//okay-----checks whether this customers has such same loan not payed returns 0 if no 1 if exist
    public int checkWetherLoanExist(String loanid){
    int res=0;
    try {
          Methods n = new Methods();
          
          Connection con =n. getConnection();
          
          Statement st = con.createStatement();
          String searchQuery = "SELECT * FROM `loans`WHERE loanid='" + loanid+ "' ";
          ResultSet rs = st.executeQuery(searchQuery);
          if (rs.next())
          {
            res=1;
           
          }
          st.close();
          rs.close();
          con.close();
      } catch (SQLException ex) {
          Logger.getLogger(IProcessLoan.class.getName()).log(Level.SEVERE, null, ex);
      }
    
    return res;
}
    public void threadExecute(){
Thread log=new Thread(){
public void run(){
    
    try{
   
     check()  ;
      
    }
      catch(Exception b){
         System.out.println("Error");
      }
} 
      }   ;  
      log.start();
  }
    
    //okay-------initiates  checkWetherLoanExist method 
                //initiates loan processing procedures by calling check method
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
    String lnId=lblPersonId.getText() +""+lblType.getText();
        if(checkWetherLoanExist(lnId)==0){
        threadExecute();
    }
    else{
        JOptionPane.showMessageDialog(null, "Customer has this type of loan not fully paid","Warning",JOptionPane.WARNING_MESSAGE,null);
    }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
     clear() ; 
    }//GEN-LAST:event_jButton2ActionPerformed
//okay--------clears all variables
    void clearVariables(){
     rate=null;
          rateper=null;
   
   
   //used to determine if loan is applicable to reducing balance intrest rates or not
   applicableStatus=null;
   
   
   
   rateTime=0;
   appfee=null;
   penalty=null;
   timeUnit=null;
   frequency=1;
   jsNoOfInstallments=0;
   txtValueOfInstallmentAmount=0.0;
   int y=1;
   loanAmount=null;
}

//okay--------clears all variables
    private void clear(){
         
        jDateChooser1.setDate(null);
        jDateChooser2.setDate(null);
    txtTotal.setText("TOTAL");
    totalpayable.setText("TOTAL PAYABLE");
    payablewithin.setText("THIS TIME");
        jSpinnerFrequency.setValue(1);
    jSpinnerNoOfInstalments.setValue(0);
     lblDate.setText("date of application");
    //lblId.setText("PERSONS ID");
     lblInstallment.setText("INSTALLMENT");
     lblPersonId.setText("PERSONS ID");
     lblStatus.setText("status of application");
     lblTime.setText("TIME");
    lblType.setText("TYPE OF LOAN");
    lblnoOfWeeks.setText("Weeks");
    
     txtAmount.setText("");
    txtFilter.setText("");
    txtName.setText("");
    txtPreferedInstallmentsValue.setText("");
    jComboBoxChooseMode.setSelectedIndex(0);
    refresh();
    
}
    
    
    
  //okay------checks whether all required details have been provided  
private void check(){
      String jd= ((JTextField)jDateChooser1.getDateEditor().getUiComponent()).getText();
    
    if(lblPersonId.getText().equals("PERSONS ID")||
            lblInstallment.getText().equals("INSTALLMENT")||
           lblTime.getText().equals("TIME")||
            lblnoOfWeeks.getText().equals("Weeks")||
             jd.equals("")||
            jComboBoxMode.getSelectedItem().toString().equals("Mode")
          //  jSpinnerFrequency.getValue()
            ){
        JOptionPane.showMessageDialog(null, "Provide all details","Warning",JOptionPane.WARNING_MESSAGE);
    }
    else{
         insert()   ;
    }
}

//Okay-----calls deleteApplication by person id
    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
deleteApplication(lblPersonId.getText()); 
           
    }//GEN-LAST:event_jButton3ActionPerformed

    private void txtFilterKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFilterKeyReleased
        refresh();
    }//GEN-LAST:event_txtFilterKeyReleased

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
       Main.a=1; 
    }//GEN-LAST:event_formWindowClosed

    private void jComboBoxModeItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBoxModeItemStateChanged
       
    }//GEN-LAST:event_jComboBoxModeItemStateChanged

    private void jDateChooser2PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jDateChooser2PropertyChange
       
    }//GEN-LAST:event_jDateChooser2PropertyChange

    private void jDateChooser1PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jDateChooser1PropertyChange
        
    }//GEN-LAST:event_jDateChooser1PropertyChange
 
    //okay-----adds one month to any date given
    public static Date addMonth(Date date,int hw){
      Calendar  c= Calendar.getInstance();
      c.setTime(date);
      
      c.add(Calendar.MONTH, hw);
      
      return c.getTime();
  }
    
    //okay-------adds one week to any date given
   public static Date addWeek(Date date,int hw){
      Calendar  c= Calendar.getInstance();
      c.setTime(date);
      
      c.add(Calendar.WEEK_OF_MONTH, hw);
      
      return c.getTime();
  }
   
   //okay-------adds one day to any date given
    public static Date addDay(Date date,int hw){
      Calendar  c= Calendar.getInstance();
      c.setTime(date);
      
      c.add(Calendar.DAY_OF_WEEK, hw);
      
      return c.getTime();
  }
    
    //okay-------adds one year to any date given
     public static Date addYear(Date date,int hw){
      Calendar  c= Calendar.getInstance();
      c.setTime(date);
      
      c.add(Calendar.YEAR, hw);
      
      return c.getTime();
  }
    
     
     
    private void insert(){
        Date a = null;
       
       String applicationfee=appfee.toString();
       
      //confirm the action of processing this loan 
       int dialogButton=
       JOptionPane.showConfirmDialog(null, "Confirm This \n  LOAN OF  "+ this.txtAmount.getText() +"  \n TO  "+txtName.getText());
        if(dialogButton==JOptionPane.YES_OPTION){
            
            
        if(y>0){
            if(timeUnit.equals("Weekly")){
           Date f= jDateChooser1.getDate();
           Calendar  c= Calendar.getInstance();
           a=addWeek(f,frequency);
        }
            
            
        else if (timeUnit.equals("Monthly")){
         Date f= jDateChooser1.getDate();
         Calendar  c= Calendar.getInstance();
         a=addMonth(f,frequency);
        }
        
        
        else if (timeUnit.equals("Daily")){
         Date f= jDateChooser1.getDate();
         Calendar  c= Calendar.getInstance();
         a=addDay(f,frequency);
        }
        
        
        else if (timeUnit.equals("Yearly")){
         Date f= jDateChooser1.getDate();
         Calendar  c= Calendar.getInstance();
         
         a=addYear(f,frequency);
        }
            
            
            
            
    
     String appfe=String.valueOf(appfee*Double.valueOf(txtAmount.getText()));
     String i="0.0";
     double todaypay=0.0;
     String z="0.0";
     String givenOn="givenOn";
     String paymentStatus="np";
     String defaultStatus="nd";
     
   //String loanamount=calculate();
     java.util.Date d=(a);
     String lnId=lblPersonId.getText() +""+lblType.getText();
     String lnIdno="0";
     java.sql.Date DATE=new java.sql.Date(d.getTime());
     
     String nxp="np";
     
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
             + "'" + this.lblPersonId.getText() + "'"
             + ",'" + lnId+ "'"
            
             + ",'" + this.lblType.getText()+ "'"
              + ",'" + applicableStatus+ "'"
             + ",'"+appfe+"'"
             + ",'" + this.timeUnit + "'"
             + ",'" + jSpinnerFrequency.getValue().toString()+ "'"
             + ",'" + DATE + "'"
             
              + ",'" + this.lblInstallment.getText() + "'"
             + ",'" + this.lblTime.getText()+"'"
             
             + ",'"+loanAmount+"'"
             
             
             + ",'"+txtAmount.getText()+"'"
              
             + ",'"+loanAmount+"'"
            
             + ",'"+i+"'"
             + ",'"+i+"'"
             + ",'"+paymentStatus+"'"
             + ",'"+defaultStatus+"'"
             + ",'"+i+"'"
             + ",'"+i+"'"
              + ",now()"
             + ",now())";
             
             
             
    
      String []list={ "Cash", "Mpesa", "Cheque"};
            JComboBox jcb=new JComboBox(list);
            JOptionPane.showMessageDialog(null, jcb,"Select mode of Loan Application fee Payment",JOptionPane.QUESTION_MESSAGE);
            String mode=jcb.getSelectedItem().toString();
       String fee=   JOptionPane.showInputDialog("Enter application fee");
       Double bal=Double.valueOf(fee)-Double.valueOf(appfe);
        methods.addToOrgAccount(Double.valueOf(appfe),"Loan Application Fee" ,mode);
        if(bal>0){
             JOptionPane.showMessageDialog(null, "Balance is " +bal.toString());
              if(methods.executeSQlQuery(query, "Processed")==1){
             methods.RemoveFromOrgAccount(Double.valueOf(txtAmount.getText()),"LOAN GIVEN",jComboBoxMode.getSelectedItem().toString());
             deleteApplication(lblPersonId.getText());
       }
        }
        else if(bal<0){
             JOptionPane.showMessageDialog(null, "Balance is less by" +bal.toString());
              clear();
              refresh();
        }
      
        else if(bal==0){
           if(methods.executeSQlQuery(query, "Processed")==1){
          methods.RemoveFromOrgAccount(Double.valueOf(txtAmount.getText()),"LOAN GIVEN",jComboBoxMode.getSelectedItem().toString());
          deleteApplication(lblPersonId.getText());
       }  
        }
       
      
        }
        else{
             JOptionPane.showMessageDialog(null, "Fill  Guarantors form first");
        }
    }
}
 
   public void deleteApplication(String id){
        Methods n=new Methods();
        String query = "DELETE FROM `applications`  WHERE `id` = '" +id+"'";
                n.executeSQlQuery(query, "Deleted");
              clear();
              refresh();
       
       
   }
    private void checkWhich(int val,int which){
        if(applicableStatus.equals("A")){
        loanAmount=calculateReducingIntrest(val,which);
        }
        else{
        loanAmount=calculateFixedIntrest(val,which);
        }

    }
    
    private String calculateFixedIntrest(int NOI,int which){
    Double requestedAmount=Double.valueOf(txtAmount.getText().toString());
    Double intrest=requestedAmount*rate;
    Double fullLoanAmount=(requestedAmount+intrest);
    if(which==1){
    Double instalments=fullLoanAmount/NOI;
    lblTime.setText(String.valueOf(NOI));
    lblInstallment.setText(instalments.toString());
    
    txtTotal.setText(String.valueOf(fullLoanAmount));
    }
    else{
    Double ltime=(fullLoanAmount/NOI);
   // JOptionPane.showMessageDialog(null, fullLoanAmount+" "+NOI+" "+ltime);
    lblInstallment.setText(String.valueOf(NOI));
    lblTime.setText(String.valueOf(ltime.toString()));
    
    txtTotal.setText(String.valueOf(fullLoanAmount));
    }
    return String.valueOf(fullLoanAmount);
    
}
    private String calculateReducingIntrest(int NOI,int which){
    Double requestedAmount=Double.valueOf(txtAmount.getText());
    Double fullLoanAmount=0.0;
    Double rt=Double.valueOf(rateTime);
    
   // Double intrest=requestedAmount*rate;
   // Double fullLoanAmount=(requestedAmount+intrest);
    if(which==1){
        
        Double userRateTime=getCostomerRateTime(NOI);
    if( Objects.equals(userRateTime, rt)){
    Double intrest=requestedAmount*rate;
     fullLoanAmount=requestedAmount+intrest;            
    Double instalments=fullLoanAmount/NOI;
    lblTime.setText(String.valueOf(NOI));
    lblInstallment.setText(instalments.toString());
    txtTotal.setText(String.valueOf(fullLoanAmount));
                 
     }
    else if(userRateTime>rt){
        Double nr = rate / rt;
        Double a = (userRateTime - rt);
        Double intrest1 = requestedAmount * (nr * userRateTime);

        fullLoanAmount = requestedAmount + intrest1;

        Double instalments = (fullLoanAmount) / NOI;

        lblTime.setText(String.valueOf(NOI));
        lblInstallment.setText(instalments.toString());
        txtTotal.setText(String.valueOf(fullLoanAmount));
     // JOptionPane.showMessageDialog(null, a);
    }
    else if(userRateTime<rt){
         Double nr= rate/rt;
        
         Double intrest=requestedAmount*(nr*userRateTime);
         fullLoanAmount=requestedAmount+intrest;            
         Double instalments=fullLoanAmount/NOI;
         lblTime.setText(String.valueOf(NOI));
         lblInstallment.setText(instalments.toString());
         txtTotal.setText(String.valueOf(fullLoanAmount));
      
    }
    
    }
    else if(which==2){
   // Double installment=Double.valueOf(txtPreferedInstallmentsValue.getText())    ;
    
        
   
    }
    return String.valueOf(fullLoanAmount);
    
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
            java.util.logging.Logger.getLogger(IProcessLoan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(IProcessLoan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(IProcessLoan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(IProcessLoan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new IProcessLoan().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JComboBox<String> jComboBoxChooseMode;
    private javax.swing.JComboBox<String> jComboBoxMode;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private com.toedter.calendar.JDateChooser jDateChooser2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSpinner jSpinnerFrequency;
    private javax.swing.JSpinner jSpinnerNoOfInstalments;
    private javax.swing.JLabel lblDate;
    private javax.swing.JLabel lblId;
    private javax.swing.JLabel lblInstallment;
    private javax.swing.JLabel lblPersonId;
    private javax.swing.JLabel lblStatus;
    private javax.swing.JLabel lblTime;
    private javax.swing.JLabel lblType;
    private javax.swing.JLabel lblnoOfWeeks;
    private javax.swing.JLabel payablewithin;
    private javax.swing.JTable table;
    private javax.swing.JLabel totalpayable;
    private javax.swing.JTextField txtAmount;
    private javax.swing.JTextField txtFilter;
    private javax.swing.JTextField txtName;
    private javax.swing.JTextField txtPreferedInstallmentsValue;
    private javax.swing.JLabel txtTotal;
    // End of variables declaration//GEN-END:variables
private void give(){
    
}
    private Double getCostomerRateTime(int NOI) {
       Double a=0.01;
       
     if(timeUnit.equals(rateper)&&frequency==1) {
       a=1*Double.valueOf(NOI);
         
     } 
     
     else if(timeUnit.equals(rateper)&&frequency!=1){
          a=frequency*Double.valueOf(NOI);
         
         
     }
     
     else if(timeUnit.equals("Weekly")){
           switch (rateper) {
               case "Monthly":
                   a=(frequency*Double.valueOf(NOI))/4.0;
                   break;
               case "Daily":
                   a=(frequency*Double.valueOf(NOI))*7.0;
                   break;
               case "Yearly":
                   a=(frequency*Double.valueOf(NOI))/52.0;
                   break;
               default:
                   break;
           }
     }
     
      else if(timeUnit.equals("Daily")){
           switch (rateper) {
               case "Monthly":
                   a=(frequency*Double.valueOf(NOI))/30.0;
                   break;
               case "Weekly":
                   a=(frequency*Double.valueOf(NOI))/7.0;
                   break;
               case "Yearly":
                   a=(frequency*Double.valueOf(NOI))/365.0; 
                   break;
               default:
                   break;
           }
     }
       else if(timeUnit.equals("Monthly")){
           switch (rateper) {
               case "Daily":
                   a=(frequency*Double.valueOf(NOI))*30.0;
                   break;
               case "Weekly":
                   a=(frequency*Double.valueOf(NOI))/4.0;
                   break;
               case "Yearly":
                   a=(frequency*Double.valueOf(NOI))/12.0; 
                   break;
               default:
                   break;
           }
     }
      else if(timeUnit.equals("Yearly")){
           switch (rateper) {
               case "Monthly":
                   a=(frequency*Double.valueOf(NOI))*12;
                   break;
               case "Weekly":
                   a=(frequency*Double.valueOf(NOI))*52;
                   break;
               case "Daily":
                   a=(frequency*Double.valueOf(NOI))*365; 
                   break;
               default:
                   break;
           }
     }
       return a;
    }
}
