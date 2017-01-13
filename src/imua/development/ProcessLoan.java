/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imua.development;

//import static imua.development.loanform.y;
import java.awt.Toolkit;
import java.awt.event.ItemEvent;
import java.sql.Connection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JSpinner.DefaultEditor;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author kimani kogi
 */
public class ProcessLoan extends javax.swing.JFrame {
  Double rate;
  String rateper;
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
    public ProcessLoan() {
        initComponents();
        setTilteImage();
        findUsers();
        lblnoOfWeeks.setVisible(false);
        jInternalFrame1.setVisible(false);
        
        ((DefaultEditor)jSpinnerFrequency.getEditor()).getTextField().setEditable(false);
       // lblPersonId.setText("12134564332");
    }
    private void setTilteImage(){
     Methods n=new Methods();
    String t= n.setTitle();
    this.setTitle(t);
    String i=n.setIconImage();
    this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource(i)));
}
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
  private void refresh(){
       
        DefaultTableModel model = (DefaultTableModel)this.table.getModel();
        
        model.setRowCount(0);
        
        findUsers();
  }
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
 

  public void getLoanDetails(String a){
      try {
          Methods n = new Methods();
          
          Connection con =n. getConnection();
          
          Statement st = con.createStatement();
          String searchQuery = "SELECT * FROM `accounttypes`WHERE name='" + a + "' ";
          ResultSet rs = st.executeQuery(searchQuery);
          while (rs.next())
          {
            
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
          Logger.getLogger(ProcessLoan.class.getName()).log(Level.SEVERE, null, ex);
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
        jInternalFrame1 = new javax.swing.JInternalFrame();
        jPanel3 = new javax.swing.JPanel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        txtTotal = new javax.swing.JLabel();

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

        jInternalFrame1.setVisible(true);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 303, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );

        jMenu1.setText("File");
        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");

        jMenuItem2.setText("jMenuItem2");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem2);

        jMenuBar1.add(jMenu2);

        jInternalFrame1.setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout jInternalFrame1Layout = new javax.swing.GroupLayout(jInternalFrame1.getContentPane());
        jInternalFrame1.getContentPane().setLayout(jInternalFrame1Layout);
        jInternalFrame1Layout.setHorizontalGroup(
            jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jInternalFrame1Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 20, Short.MAX_VALUE))
        );
        jInternalFrame1Layout.setVerticalGroup(
            jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jInternalFrame1Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        txtTotal.setText("TOTAL");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
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
                                    .addComponent(lblDate, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE))))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(lblType, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(78, 78, 78))))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(jInternalFrame1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
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
                                        .addComponent(jButton2)
                                        .addGap(18, 18, 18)
                                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(19, 19, 19)
                                        .addComponent(jButton3))
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
                .addGap(43, 43, 43)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(jButton3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jInternalFrame1)
                .addContainerGap())
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
private void getPayableAmount(String loanAmount){
    if(applicableStatus.equals("A")){
    Double payable=(rate*Double.valueOf(loanAmount))+Double.valueOf(loanAmount);
    int withinTime=rateTime;
    String withinPer=rateper;
    
   totalpayable.setText(String.valueOf(payable));
    
   payablewithin.setText(String.valueOf(withinTime)+" "+withinPer);
    
    }
}
    public  String getId(String id){
   String   ID=null;
    if (id.isEmpty()){
        
    }
    else{
  garanters.id=id;
    }
    return ID;
}
    private void tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableMouseClicked
   
        
        int i = this.table.getSelectedRow();
    
    TableModel model = this.table.getModel();
   
       
    if(model.getValueAt(i, 6).toString().equals("void")){
        Object []options={"FILL IT","CONTINUE ANYWAY","CANCEL"};
           int btn= JOptionPane.showOptionDialog(null, "GURANTERS FORM NOT FIELD","CONFIRM",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.PLAIN_MESSAGE,null,options,options[0]);
        switch (btn) {
            case 1:
                lblPersonId.setText(model.getValueAt(i, 1).toString());
                this.txtName.setText(model.getValueAt(i, 2).toString());
                this.lblType.setText(model.getValueAt(i, 5).toString());
                this.txtAmount.setText(model.getValueAt(i, 3).toString());
                this.lblDate.setText(model.getValueAt(i, 0).toString());
                this.lblStatus.setText(model.getValueAt(i, 4).toString());
                getLoanDetails(model.getValueAt(i, 5).toString());
                getPayableAmount(model.getValueAt(i, 3).toString());
                break;
            case 2:
                clear();
                break;
        //clear();
            default:
        String PersonId=(model.getValueAt(i, 1).toString());
        getId(PersonId);
        garanters n = new garanters();
        n.setVisible(true);
                break;
        }
    }
    else{
         lblPersonId.setText(model.getValueAt(i, 1).toString());
                this.txtName.setText(model.getValueAt(i, 2).toString());
                this.lblType.setText(model.getValueAt(i, 5).toString());
                this.txtAmount.setText(model.getValueAt(i, 3).toString());
                this.lblDate.setText(model.getValueAt(i, 0).toString());
                this.lblStatus.setText(model.getValueAt(i, 4).toString());
                getLoanDetails(model.getValueAt(i, 5).toString());
                getPayableAmount(model.getValueAt(i, 3).toString());
    }
    
    }//GEN-LAST:event_tableMouseClicked

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

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
     check()  ;
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
     clear() ; 
    }//GEN-LAST:event_jButton2ActionPerformed
private void clear(){
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
    
    
}
private void check(){
    if(lblPersonId.getText().equals("PERSONS ID")||
            lblInstallment.getText().equals("INSTALLMENT")||
           lblTime.getText().equals("TIME")||
            lblnoOfWeeks.getText().equals("Weeks")
          //  jSpinnerFrequency.getValue()
            ){
        JOptionPane.showMessageDialog(null, "Provide all details");
    }
    else{
         insert()   ;
    }
}
    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
deleteApplication(lblPersonId.getText()); 
           
    }//GEN-LAST:event_jButton3ActionPerformed

    private void txtFilterKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFilterKeyReleased
        findUsers();
    }//GEN-LAST:event_txtFilterKeyReleased

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
       Main.a=1; 
    }//GEN-LAST:event_formWindowClosed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
      JOptionPane.showMessageDialog(null, y);
    }//GEN-LAST:event_jMenuItem2ActionPerformed
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
      
      c.add(Calendar.DAY_OF_WEEK, hw);
      
      return c.getTime();
  }
     public static Date addYear(Date date,int hw){
      Calendar  c= Calendar.getInstance();
      c.setTime(date);
      
      c.add(Calendar.YEAR, hw);
      
      return c.getTime();
  }
    
    private void insert(){
        Date a = null;
       String applicationfee=appfee.toString();
       int dialogButton=
        
        JOptionPane.showConfirmDialog(null, "Confirm This \n  LOAN OF  "+ this.txtAmount.getText() +"  \n TO  "+txtName.getText());
        if(dialogButton==JOptionPane.YES_OPTION){
        if(y>0){
            if(timeUnit.equals("Weekly")){
            
        Calendar  c= Calendar.getInstance();
        Date f=  c.getTime();
         a=addWeek(f,frequency);
       
            
            
            }
            
            
            else if (timeUnit.equals("Monthly")){
         Calendar  c= Calendar.getInstance();
        Date f=  c.getTime();
         a=addMonth(f,frequency);
        //JOptionPane.showMessageDialog(null, a+" "+f);
        
                
                
            }
             else if (timeUnit.equals("Daily")){
         Calendar  c= Calendar.getInstance();
        Date f=  c.getTime();
         a=addDay(f,frequency);
        //JOptionPane.showMessageDialog(null, a+" "+f);
         }
             else if (timeUnit.equals("Yearly")){
         Calendar  c= Calendar.getInstance();
        Date f=  c.getTime();
         a=addYear(f,frequency);
        //JOptionPane.showMessageDialog(null, a+" "+f);
        
                
                
            }
            
     Methods n=new Methods();
     String appfe=String.valueOf(appfee*Double.valueOf(txtAmount.getText()));
     String i="null";
   //String loanamount=calculate();
     java.util.Date d=(a);
     String lnId=lblPersonId.getText() +""+lblType.getText();
     java.sql.Date DATE=new java.sql.Date(d.getTime());
     
     
     
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
             + ",`givenOn`"
             + ",`paidon`)"
             + " VALUES ("
             + "'" + this.lblPersonId.getText() + "'"
             + ",'" + lnId+ "'"
             + ",'" + this.lblType.getText()+ "'"
              + ",'" + applicableStatus+ "'"
             + ",'" + this.lblInstallment.getText() + "'"
             + ",'" + this.lblTime.getText()+"'"
             + ",'" + this.timeUnit + "'"
             + ",'" + jSpinnerFrequency.getValue().toString()+ "'"
             + ",'" + DATE + "'"
             + ",'"+loanAmount+"'"
             + ",'"+txtAmount.getText()+"'"
              
             + ",'"+loanAmount+"'"
            
             + ",'"+i+"'"
             + ",'"+appfe+"'"
             + ",'"+i+"'"
             + ",now()"
             + ",now())";
       String fee=   JOptionPane.showInputDialog("Enter application fee");
       Double bal=Double.valueOf(fee)-Double.valueOf(appfe);
       
       JOptionPane.showMessageDialog(null, "Balance is " +bal.toString());
       
       n.executeSQlQuery(query, "Processed");
          deleteApplication(lblPersonId.getText());
          
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
    }
    else{
    Double ltime=(fullLoanAmount/NOI);
    JOptionPane.showMessageDialog(null, fullLoanAmount+" "+NOI+" "+ltime);
    lblInstallment.setText(String.valueOf(NOI));
    lblTime.setText(String.valueOf(ltime.toString()));
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
            java.util.logging.Logger.getLogger(ProcessLoan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ProcessLoan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ProcessLoan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ProcessLoan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ProcessLoan().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JComboBox<String> jComboBoxChooseMode;
    private javax.swing.JInternalFrame jInternalFrame1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
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
          if(rateper.equals("Monthly")){
       a=(frequency*Double.valueOf(NOI))/4.0;  
         }
         else if(rateper.equals("Daily")){
          a=(frequency*Double.valueOf(NOI))*7.0;     
         }
          else if(rateper.equals("Yearly")){
          a=(frequency*Double.valueOf(NOI))/52.0;     
         }
     }
     
      else if(timeUnit.equals("Daily")){
      if(rateper.equals("Monthly")){
       a=(frequency*Double.valueOf(NOI))/30.0;  
         }
         else if(rateper.equals("Weekly")){
          a=(frequency*Double.valueOf(NOI))/7.0;     
         }
          else if(rateper.equals("Yearly")){
          a=(frequency*Double.valueOf(NOI))/365.0;     
         } 
     }
       else if(timeUnit.equals("Monthly")){
      if(rateper.equals("Daily")){
       a=(frequency*Double.valueOf(NOI))*30.0;  
         }
         else if(rateper.equals("Weekly")){
          a=(frequency*Double.valueOf(NOI))/4.0;     
         }
          else if(rateper.equals("Yearly")){
          a=(frequency*Double.valueOf(NOI))/12.0;     
         } 
     }
      else if(timeUnit.equals("Yearly")){
        if(rateper.equals("Monthly")){
         a=(frequency*Double.valueOf(NOI))*12;  
         }
        else if(rateper.equals("Weekly")){
          a=(frequency*Double.valueOf(NOI))*52;     
         }
          else if(rateper.equals("Daily")){
          a=(frequency*Double.valueOf(NOI))*365;     
         } 
     }
       return a;
    }
}