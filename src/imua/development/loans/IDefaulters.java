/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imua.development.loans;

import IDataHolders.ILoanDataHolder;
import IDataHolders.ILoanDataHolder1;
import imua.development.defaultersHolder;
import imua.development.*;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static java.lang.System.out;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**ooo
 *
 * @author kimani kogi
 */
public class IDefaulters extends javax.swing.JFrame {
 ButtonGroup radiog =new ButtonGroup();
 Methods m=new Methods();
 Methods methods=new Methods();
 ArrayList<ILoanDataHolder1> singleDefaultLoan=new ArrayList<>();
   DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:SS");
 int action=0;
 Iloanpayment iloanpayment=new Iloanpayment();
    /**
     * Creates new form Defaulters
     */
    public IDefaulters() {
        initComponents();
                 
        refresh();
      //  sorter(3);
        setTilteImage();
       // getNewRenderedTable(table);
        table.getTableHeader().setEnabled(false);
        radiog.add(jRadioButtonWaive);
        radiog.add(jRadioButtonEffect);
        jRadioButtonEffect.setSelected(true);
        // TableRowSorter<TableModel> sorter = new TableRowSorter<>(table.getModel());
        //table.setRowSorter(sorter);
       // sorter.setSortable(0, false);
       
       
       getNewRenderedTable(table);
       
       
        jRadioButtonWaive.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
        action=1;
        process(((ILoanDataHolder1)singleDefaultLoan.get(0)).getLoantype());
        
        }
    });
         jRadioButtonEffect.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
        action=0;
        process(((ILoanDataHolder1)singleDefaultLoan.get(0)).getLoantype());

        }
    });

    }
    private void setTilteImage(){
        try {
            Methods n=new Methods();
            String t= n.setTitle();
            this.setTitle(t);
            String i=n.setIconImage();
            this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource(i)));
            
            String col=n.selectcolor();
            Color c=new Color(Integer.parseInt(col));
            Container cont=this.getContentPane();
            cont.setBackground(c);
            jPanel1.setBackground(c);
            jPanel2.setBackground(c);
            jPanel3.setBackground(c);
        } catch (Exception ex) {
            Logger.getLogger(IDefaulters.class.getName()).log(Level.SEVERE, null, ex);
        }
}
     public  Date addOne(Date date,int hw){
      Calendar  c= Calendar.getInstance();
      c.setTime(date);
      c.add(Calendar.DATE, hw);
   //   c.add(Calendar.WEEK_OF_MONTH, hw);
      
      return c.getTime();
  }
    public ArrayList<ILoanDataHolder> ListUsers( )
  {
    ArrayList<ILoanDataHolder> usersList = new ArrayList();
   
          
    try
    {
        Methods m=new Methods();
        Connection con = m.getConnection();
     // Connection con = getConnection();
      Statement st = con.createStatement();
    // String searchQuery1 = "SELECT * FROM `accounttypes`";
      String searchQuery = "SELECT * FROM `iloansdefaulters`";
      ResultSet rs = st.executeQuery(searchQuery);
      while (rs.next())
      { 
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
          
                
     
        
      usersList.add(data);
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
    ArrayList<ILoanDataHolder> users = ListUsers();
    DefaultTableModel model = new DefaultTableModel();
  //   String ID, Date ate, String Installmentamount, String LoanAmount,String autoid,String instno
    // model.setColumnIdentifiers(new Object[] { "DATE","LOANID","ID", "NAME","ELAPSED","INSTALLMENT","LOAN","LOAN TYPE","UNIQ","IO"});
    model.setColumnIdentifiers(new Object[] { "DATE","F-NAME","L-NAME","ELAPSED","INSTALLMENT","INSTALLMENT BALANCE","LOAN BALANCE","ID"});
    Object[] row = new Object[8];
    for (int i = 0; i < users.size(); i++)
    {
         row[0] = ((ILoanDataHolder)users.get(i)).getDatesupposed();
       // String id=((ILoanDataHolder)users.get(i)).getDatesupposed();
       String[] name=     getName(((ILoanDataHolder)users.get(i)).getCustomerid());
           row[1]= name[0];
           row[2]=name[1];
        Date d=new Date();
        Date e=((ILoanDataHolder)users.get(i)).getDatesupposed();
       
        
        
        row[3]=het(d,e);
        
        
        row[4] = ((ILoanDataHolder)users.get(i)).getInstallmentamount();
        
        Double installmentBalance=(Double.valueOf(((ILoanDataHolder)users.get(i)).getInstallmentamount())-(Double.valueOf(((ILoanDataHolder)users.get(i)).getBalancebf())+Double.valueOf(((ILoanDataHolder)users.get(i)).getTodaypay())));
        
         row[5] = installmentBalance;
        
        row[6] = ((ILoanDataHolder)users.get(i)).getLoanbalance();
        
        
      // row[7] = ((defaultersHolder) users.get(i)).getLoanType();
        row[7] = ((ILoanDataHolder)users.get(i)).getAutoid();
        
        
        
        
        
   
        model.addRow(row);
        
    }
   
    this.table.setModel(model);
    //getNewRenderedTable(table);
  // sorter(3);
  }
  public String[] getName(String id){
        String name="";
        String sname="";
      try {
            
            Methods m = new Methods();
            
            Connection con = m.getConnection();
            Statement st = con.createStatement();
            String searchQuery = "SELECT fname,sname FROM `users` WHERE `id` = '" + id + "'";
            ResultSet rs = st.executeQuery(searchQuery);
            while (rs.next()) {
                name=rs.getString("fname");
                sname=rs.getString("sname");
            }
            st.close();
            rs.close();
            con.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(IDefaulters.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new String []{name,sname};
  }
    public long het(Date now,Date thn){
     long day=0;
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

      //System.out.println("Your Day Difference="+ddays);
      return ddays;
}
    private  JTable getNewRenderedTable(final JTable table) {
       // sorter(3);
        table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer(){
            @Override
            public Component getTableCellRendererComponent(JTable table,
                    Object value, boolean isSelected, boolean hasFocus, int row, int col) {
                super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, col);
                int status = Integer.valueOf(table.getModel().getValueAt(row, 3).toString());
                if (status<2) {
                  setBackground(table.getBackground());
                    setForeground(table.getForeground());
                } else if(status>=2&&status<4){
                     setBackground(Color.BLACK);
                    setForeground(Color.WHITE);
                }
                else if(status>=4&&status<6){
                     setBackground(Color.YELLOW);
                    setForeground(Color.BLACK);
                }
                 else if(status>=6){
                     setBackground(Color.RED);
                    setForeground(Color.WHITE);
                }
                else {
                    setBackground(table.getBackground());
                    setForeground(table.getForeground());
                }
                return this;
            }   
        });
       
        return table;
    }
    private void sorter(int column){
     TableRowSorter<TableModel> sorter = new TableRowSorter<>(table.getModel());
        table.setRowSorter(sorter);
       
        List<RowSorter.SortKey>sortKey=new ArrayList<>();
        
        int colToSort=column;
        sortKey.add(new RowSorter.SortKey(colToSort, SortOrder.DESCENDING));
        
        sorter.setSortKeys(sortKey);
        sorter.sort();
    
  
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
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        txtName = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        txtInstallmentAmount = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtFrom = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtDays = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtLoan = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtInstallment = new javax.swing.JTextField();
        txtRate = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtBBF = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jRadioButtonWaive = new javax.swing.JRadioButton();
        jRadioButtonEffect = new javax.swing.JRadioButton();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtFined = new javax.swing.JTextField();
        txtInstallmentValue = new javax.swing.JTextField();
        txtTotal = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel11 = new javax.swing.JLabel();
        txtBalance = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jLabel14 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        table.setAutoCreateRowSorter(true);
        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        table.setRowHeight(40);
        table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(table);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 707, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 565, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("PROPERTIES"));

        txtName.setEditable(false);

        jLabel1.setText("NAME");

        txtInstallmentAmount.setEditable(false);

        jLabel2.setText("INSTALLMENT AMOUNT");

        jLabel3.setText("FROM");

        txtFrom.setEditable(false);

        jLabel4.setText("DAYS");

        txtDays.setEditable(false);

        jLabel5.setText("LOAN BALANCE ");

        txtLoan.setEditable(false);

        jLabel6.setText("INSTALLMENT BALANCE ");

        txtInstallment.setEditable(false);

        txtRate.setEditable(false);

        jLabel7.setText("FINE");

        txtBBF.setEditable(false);

        jLabel12.setText("BBF");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(160, 160, 160)
                                .addComponent(jLabel4))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addGap(116, 116, 116)
                                .addComponent(jLabel6)))
                        .addGap(63, 63, 63))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel1)
                            .addComponent(jLabel7)
                            .addComponent(txtName, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 171, Short.MAX_VALUE)
                            .addComponent(txtLoan, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtFrom, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtRate))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel12)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(txtBBF, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtInstallment)
                                    .addComponent(txtDays))
                                .addGap(48, 48, 48))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(txtInstallmentAmount, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addGap(11, 11, 11)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtInstallmentAmount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtFrom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDays, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtLoan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtInstallment, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtRate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtBBF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Process"));

        jRadioButtonWaive.setText("WAIVE ");
        jRadioButtonWaive.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jRadioButtonWaiveItemStateChanged(evt);
            }
        });
        jRadioButtonWaive.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonWaiveActionPerformed(evt);
            }
        });

        jRadioButtonEffect.setText("EFFECT");

        jLabel8.setText("FINE");

        jLabel9.setText("AMOUNT");

        jLabel10.setText("TOTAL");

        txtFined.setEditable(false);

        txtInstallmentValue.setEditable(false);

        txtTotal.setEditable(false);

        jButton1.setText("CLEAR");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Mode", "Cash", "Mpesa", "Cheque" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jLabel11.setText("MODE");

        txtBalance.setEditable(false);

        jLabel13.setText("BALANCE");

        jLabel14.setText("DATE (OPTION)");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jRadioButtonWaive, javax.swing.GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE)
                            .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel14, javax.swing.GroupLayout.Alignment.LEADING))
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jRadioButtonEffect, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(46, 46, 46))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(46, 46, 46)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jDateChooser1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtBalance)
                                    .addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 109, Short.MAX_VALUE)
                                    .addComponent(txtTotal)
                                    .addComponent(txtInstallmentValue)
                                    .addComponent(txtFined))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jRadioButtonWaive)
                                .addGap(18, 18, 18)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel8)
                                    .addComponent(txtFined, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel9)
                                    .addComponent(txtInstallmentValue, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel10)
                                    .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jRadioButtonEffect))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtBalance, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel14))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

public double getDefaultRate(String loanType){
    double rate=0;
     try {
            
            Methods m = new Methods();
            Connection con = m.getConnection();
            Statement st = con.createStatement();
            String searchQuery = "SELECT `penalt` FROM `accounttypes` WHERE `name` = '" + loanType + "'";
            ResultSet rs = st.executeQuery(searchQuery);
            while (rs.next()) {
                rate=Double.valueOf(rs.getString("penalt"));
            }
            st.close();
            rs.close();
            con.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(IDefaulters.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    return rate;
}




    private void tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableMouseClicked
        int i = this.table.getSelectedRow();
     ;
                TableModel model = this.table.getModel();
 
               //  this.autoidd=(model.getValueAt(i, 7).toString());
                 
                this.txtName.setText(model.getValueAt(i, 1).toString());
                
                txtInstallmentAmount.setText(model.getValueAt(i, 4).toString());
                
                this.txtDays.setText(model.getValueAt(i, 3).toString());
                
                this.txtLoan.setText(model.getValueAt(i, 6).toString());
                
                
                this.txtFrom.setText(model.getValueAt(i, 0).toString());
                
                this.txtInstallment.setText(model.getValueAt(i, 5).toString());
                
                
                singleDefaultLoan= getDetails(model.getValueAt(i, 7).toString());
                String dt=((ILoanDataHolder1)singleDefaultLoan.get(0)).getPaidon();
                
                
                 
            Calendar  c= Calendar.getInstance();
       
           Date today=c.getTime();
      
            Date startDate=(today);
       
           try {
               startDate = df.parse(((ILoanDataHolder1)singleDefaultLoan.get(0)).getPaidon());
               
               
           } catch (ParseException ex) {
               startDate=today;
               ex.printStackTrace();
               Logger.getLogger(IDefaulters.class.getName()).log(Level.SEVERE, null, ex);
           }
              Date columnValue  =  addPeriod(startDate,((ILoanDataHolder1)singleDefaultLoan.get(0)).getPeriodtype()
                 ,((ILoanDataHolder1)singleDefaultLoan.get(0)).getFrequencyperperiod());
              int as=1;
              
             while(columnValue.before(today)){
                  out.println(as);
                  as++;
                  columnValue = addPeriod(columnValue,((ILoanDataHolder1)singleDefaultLoan.get(0)).getPeriodtype()
                 ,((ILoanDataHolder1)singleDefaultLoan.get(0)).getFrequencyperperiod());
              }
              
              
              
               jDateChooser1.setDate(columnValue);
               txtBBF.setText(((ILoanDataHolder1)singleDefaultLoan.get(0)).getBalancebf());
                process(((ILoanDataHolder1)singleDefaultLoan.get(0)).getLoantype());
     
    }//GEN-LAST:event_tableMouseClicked
   
   public void process(String loantype){
    Double toBUsed=Double.valueOf(txtInstallment.getText());
       Double currentLoanBalance=Double.valueOf(((ILoanDataHolder1)singleDefaultLoan.get(0)).getLoanbalance());
    Double installmentAmount=Double.valueOf(((ILoanDataHolder1)singleDefaultLoan.get(0)).getInstallmentamount());
    String newBalance=String.valueOf(currentLoanBalance-installmentAmount);
    if(action==0){
      Double rate=  getDefaultRate(loantype);
     
      DecimalFormat df=new DecimalFormat("#.0");
     //  JOptionPane.showMessageDialog(null, rate+" "+rate);
     Double processpenalty=(rate/100);
     txtRate.setText(String.valueOf(processpenalty));
    
  
      Double fine  =   (processpenalty*toBUsed)*Integer.valueOf(txtDays.getText());
       
      
      
        int nfine= fine.intValue();
      txtFined.setText(String.valueOf(nfine));
      txtInstallmentValue.setText(String.valueOf(toBUsed));
      txtTotal.setText(String.valueOf(nfine+Double.valueOf(txtInstallmentValue.getText())));
     
      txtBalance.setText(newBalance);
      
    }
    else{
         Double fine= 0.0;  
       txtFined.setText(String.valueOf(fine));
      txtInstallmentValue.setText(String.valueOf(toBUsed));
      txtTotal.setText(txtInstallmentValue.getText());
      

      
      txtBalance.setText(newBalance);
        
    }
} 
   
 public ArrayList<ILoanDataHolder1> getDetails(String value){
        ArrayList<ILoanDataHolder1> dataList=new ArrayList<>();
     try {
            
            
            Connection con= methods.getConnection();
            Statement st = con.createStatement();
             String searchQuery = "SELECT * FROM `iloansdefaulters`WHERE autoid='"+value+"'";
          
            ResultSet rs = st.executeQuery(searchQuery);
            while (rs.next()) {
             ILoanDataHolder1 data = new ILoanDataHolder1(
                 rs.getString("iloanautoid"),
                 rs.getString("autoid"),
                     
                 rs.getString("customerid"),
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
          
                
     
        
      dataList.add(data);
                
         }
            st.close();
            rs.close();
            con.close();
            
            
        } catch (SQLException ex) {
            Logger.getLogger(IDefaulters.class.getName()).log(Level.SEVERE, null, ex);
        }
     return dataList;
}


    private void jRadioButtonWaiveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonWaiveActionPerformed
        
    }//GEN-LAST:event_jRadioButtonWaiveActionPerformed

    private void jRadioButtonWaiveItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jRadioButtonWaiveItemStateChanged
        
    }//GEN-LAST:event_jRadioButtonWaiveItemStateChanged

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
      if(jComboBox1.getSelectedItem().toString().equals("Mode")){
        JOptionPane.showMessageDialog(null, "Select mode of payment","Message",JOptionPane.ERROR_MESSAGE);
      }
      else{
       
       double loanBalance=Double.valueOf(txtBalance.getText());
        String mode=jComboBox1.getSelectedItem().toString();
        Double finr=Double.valueOf(txtTotal.getText());
        String fee = JOptionPane.showInputDialog("Enter Total ");
        
        Double bal =Double.valueOf(fee)-finr;
       
        
        if(bal>=0){
          String paymentstatus="paid";
           String newDate=df.format(jDateChooser1.getDate());
        
         if(setNewLoanEntry(String.valueOf(bal),newDate,bal)){
             if(bal>0){
              paymentstatus="pm";   
             }
             updateLoanDetails(fee,paymentstatus,String.valueOf(bal),((ILoanDataHolder1)singleDefaultLoan.get(0)).getIloanautoid(),"nd");
             deleteLoanDefaulters(((ILoanDataHolder1)singleDefaultLoan.get(0)).getAutoid());
             
              methods.addToOrgAccount(Double.valueOf(txtFined.getText()),"LOAN PENALTY" ,mode);
              methods.addToOrgAccount(Double.valueOf(fee),"Loan Installments",mode );
             refresh();
             
           if(  ((Double.valueOf(txtBalance.getText()))-bal)<1){
               JOptionPane.showMessageDialog(this, "Give customer "+(bal-(Double.valueOf(txtBalance.getText()))));
               
               if(iloanpayment.getToArchives("loanid",((ILoanDataHolder1)singleDefaultLoan.get(0)).getLoanid() )){
                   JOptionPane.showMessageDialog(this, "lOAN MOVED TO ARCHIVES");
               }
             }
             
             
             clear();
         }
         

        
        }
        else if(bal<0){
            
            

            Calendar  c= Calendar.getInstance();
        
           Date today=c.getTime();
           java.util.Date d=(today);
     
           java.sql.Date DATE=new java.sql.Date(d.getTime());
            Date startDate=DATE;
       
           try {
            startDate = df.parse(((ILoanDataHolder1)singleDefaultLoan.get(0)).getPaidon());
           } 
           catch (ParseException ex) {
               ex.printStackTrace();
               
               
               Logger.getLogger(IDefaulters.class.getName()).log(Level.SEVERE, null, ex);
           }
                  Date columnValue  =  addPeriod(startDate,((ILoanDataHolder1)singleDefaultLoan.get(0)).getPeriodtype()
                 ,((ILoanDataHolder1)singleDefaultLoan.get(0)).getFrequencyperperiod());
                   String   date = df.format(columnValue);
          if( 
              updateLoanDefaulted(Double.valueOf(((ILoanDataHolder1)singleDefaultLoan.get(0)).getTodaypay()),Double.valueOf(fee),
              ((ILoanDataHolder1)singleDefaultLoan.get(0)).getAutoid(),date) ){
              
              
        methods.addToOrgAccount(Double.valueOf(txtFined.getText()),"LOAN PENALTY" ,mode);
        methods.addToOrgAccount(Double.valueOf(fee),"Loan Installments",mode );
        
        
        clear();
        refresh();
        JOptionPane.showMessageDialog(null, "Balance " +bal.toString());
          }
        }
       
      }
    }//GEN-LAST:event_jButton1ActionPerformed
private boolean setNewLoanEntry(String bbf,String newDate,double toMinusBalance) {
 boolean success=false;
         String todayPay="0.0";
        String paymentStatus="np";
        String defaultStatus="nd";
        String i="0.0";
        
        
        
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
             + "'" + ((ILoanDataHolder1)singleDefaultLoan.get(0)).getCustomerid()+ "'"
             + ",'" +((ILoanDataHolder1)singleDefaultLoan.get(0)).getLoanid()+ "'"
             + ",'" + ((ILoanDataHolder1)singleDefaultLoan.get(0)).getLoantype()+ "'"
             + ",'" + ((ILoanDataHolder1)singleDefaultLoan.get(0)).getApplicablestatus()+ "'"
             + ",'" + ((ILoanDataHolder1)singleDefaultLoan.get(0)).getApplicationfee() + "'"
             + ",'" +((ILoanDataHolder1)singleDefaultLoan.get(0)).getPeriodtype()+"'"
             + ",'" + ((ILoanDataHolder1)singleDefaultLoan.get(0)).getFrequencyperperiod()+ "'"
            
                   
            + ",'" + newDate + "'"
             + ",'"+((ILoanDataHolder1)singleDefaultLoan.get(0)).getInstallmentamount()+"'"
             + ",'"+(Integer.valueOf(((ILoanDataHolder1)singleDefaultLoan.get(0)).getInstallmentsno())-1)+"'"
                
             + ",'"+((ILoanDataHolder1)singleDefaultLoan.get(0)).getLoanAmount()+"'"
             + ",'"+((ILoanDataHolder1)singleDefaultLoan.get(0)).getLoanRequested()+"'"
             + ",'"+((Double.valueOf(txtBalance.getText()))-toMinusBalance)+"'"
         
         
             + ",'" + todayPay + "'"
             + ",'" + todayPay + "'"
         
             + ",'" + paymentStatus + "'"
             + ",'" + defaultStatus + "'"
             + ",'" + bbf+ "'"
           
             + ",'"+i+"'"
             + ",'"+((ILoanDataHolder1)singleDefaultLoan.get(0)).getLoangivenOn()+"'"
             + ",now())";
 
  if( methods.executeSQlQueryN(query)==1){
       success=true;
            
  
  }
 
 
 return success;
}

    private boolean updateLoanDetails(String todaypay,String paymentstatus,String moreorlesspaid,String autoid,String defaultstatus) {
     boolean success=false;
             Calendar  c= Calendar.getInstance();
        
           Date today=c.getTime();
           java.util.Date d=(today);
     
           java.sql.Date DATE=new java.sql.Date(d.getTime());
         String query = "UPDATE `iloans` SET `todaypay`='" + todaypay + "',`moreorlesspaid`='" + moreorlesspaid + "',"
                + "`paymentstatus`='" + paymentstatus + "'"
                + "`defaultstatus`='" + defaultstatus + "'"
                + ",`paidon`='" + DATE + "'"
                + "WHERE autoid= '" + autoid+ "' ";
      
      if(methods.executeSQlQueryN(query)==1){
          out.print(d);
      success=true;
      }
     
      return success;
    }

    private void deleteLoanDefaulters(String loanIdValue) {
      String query = "DELETE FROM `iloansdefaulters`  WHERE `autoid` = '" +loanIdValue+"'";
        m.executeSQlQueryN(query);
     
    }

    private boolean updateLoanDefaulted(Double lastPay,Double todayPay,String id,String newDate) {
       boolean success=false;
     
       
       
       String paymentstatus="nfp";      
       double tp=todayPay+lastPay;
            String query = "UPDATE `iloansdefaulters` SET `todaypay`='" + tp + "',"
                + "`paymentstatus`='" + paymentstatus + "'"
                 + "`loanbalance`='" +  txtBalance.getText() + "'"    
                 + ",`paidon`='" + newDate + "'"
                 + "WHERE autoid= '" + id+ "' ";
            if( methods.executeSQlQueryN(query)==1){
                success=true;
            }
      return success;
    }
    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox1ActionPerformed

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
            java.util.logging.Logger.getLogger(IDefaulters.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(IDefaulters.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(IDefaulters.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(IDefaulters.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new IDefaulters().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox<String> jComboBox1;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JRadioButton jRadioButtonEffect;
    private javax.swing.JRadioButton jRadioButtonWaive;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable table;
    private javax.swing.JTextField txtBBF;
    private javax.swing.JTextField txtBalance;
    private javax.swing.JTextField txtDays;
    private javax.swing.JTextField txtFined;
    private javax.swing.JTextField txtFrom;
    private javax.swing.JTextField txtInstallment;
    private javax.swing.JTextField txtInstallmentAmount;
    private javax.swing.JTextField txtInstallmentValue;
    private javax.swing.JTextField txtLoan;
    private javax.swing.JTextField txtName;
    private javax.swing.JTextField txtRate;
    private javax.swing.JTextField txtTotal;
    // End of variables declaration//GEN-END:variables


public void clear(){
     txtRate.setText("");
  
    txtDays.setText("");
     txtFined.setText("");
   txtFrom.setText("");
    txtInstallmentAmount.setText("");
     txtInstallment.setText("");
   txtInstallmentValue.setText("");
    txtLoan.setText("");
    txtName.setText("");
    txtTotal.setText("");
    jDateChooser1.removeAll();
    txtBalance.setText("");
    refresh();
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


//
//    public void getToArchives(String loanIdColumn,String loanIdValue){
//         boolean results=false;
//          String loantype="";
//         ArrayList<ILoanDataHolder> toArchive=new ArrayList<>();
//        try {
//            
//            Connection con = methods.getConnection();
//            Statement st = con.createStatement();
//            String checkpay="0";
//            String searchQuery = "SELECT * FROM `iloans` WHERE `" +loanIdColumn + "` = '" + loanIdValue+ "' ";
//            ResultSet rs = st.executeQuery(searchQuery);
//            while (rs.next()) {
//                 loantype=rs.getString("loantype");
//ILoanDataHolder user = new ILoanDataHolder(rs.getString("autoid"),rs.getString("customerid"),
//                rs.getString("loanid")
//                ,rs.getString("loantype")
//                ,rs.getString("applicablestatus")
//                ,rs.getString("applicationfee")
//                ,rs.getString("periodtype")
//                ,rs.getString("frequencyperperiod")
//                ,rs.getDate("datesupposed")
//                ,rs.getString("installmentamount")
//                ,rs.getString("installmentsno")
//                ,rs.getString("loanAmount")
//                ,rs.getString("loanRequested")
//                ,rs.getString("loanbalance")
//                ,rs.getString("moreorlesspaid")
//                ,rs.getString("todaypay")
//                ,rs.getString("paymentstatus")
//                ,rs.getString("defaultstatus")
//                ,rs.getString("balancebf")
//                ,rs.getString("extra")
//                ,rs.getString("loangivenOn")
//                ,rs.getString("paidon")
//               
//               // ,rs.getString("guranters")
//        
//        
//        );
//        
//        toArchive.add(user);
//
//            }
//         results = moveToArchives(toArchive);
//          st.close();
//          rs.close();
//          con.close();
//          
//          if(results){
//           //DefaultersPrePaid dp=new DefaultersPrePaid();   
//           //if(dp.print(id,loanIdValue,loantype)==1){
//               
//              
//                String query = "DELETE FROM `iloans`  WHERE `loanid` = '" +loanIdValue+"'";
//                m.executeSQlQueryN(query);
//                deleteGuranters();
//                clear();
//          // }
//          // else{
//          //     JOptionPane.showMessageDialog(null, loanIdValue+" Loan error moving to archive"); 
//          // }
//       
//             
//          }
//          else{
//              JOptionPane.showMessageDialog(null, "Error moving to archive");
//          }
//    }
//    catch(Exception m){
//        m.printStackTrace();
//    }
//
//    
//    
//    }
//    public boolean moveToArchives(ArrayList<ILoanDataHolder> toArchives){
//        boolean success=false;
//        int s=0;
//        for(int count=0;count<toArchives.size();count++){
//        
//         String query = "INSERT INTO iloansarchives("
//             + "`customerid`,"
//             + "`loanid`,"
//             
//             + "`loantype`,"
//             +"`applicablestatus`,"
//             + "`applicationfee`"
//             
//             + ",`periodtype`"
//             + ",`frequencyperperiod`"
//             + ",`datesupposed`"
//             
//             
//             
//             
//             
//             
//             + " ,`installmentamount`"
//             + ",`installmentsno`"
//             
//             
//             
//             + ",`loanAmount`"
//             + ",`loanRequested`"
//             + ",`loanbalance`"
//             + ",`moreorlesspaid`"
//             
//             
//             + ",`todaypay`"
//             
//             + ",`paymentstatus`"
//             
//             + ",`defaultstatus`"
//             
//             + ",`balancebf`"
//             
//             + ",`extra`"
//             
//             + ",`loangivenOn`"
//             
//             + ",`paidon`)"
//             
//             + " VALUES ("
//             + "'" + ((ILoanDataHolder)toArchives.get(count)).getCustomerid() + "'"
//             + ",'" + ((ILoanDataHolder)toArchives.get(count)).getLoanid()+ "'"
//             + ",'" + ((ILoanDataHolder)toArchives.get(count)).getLoantype()+ "'"
//             + ",'" + ((ILoanDataHolder)toArchives.get(count)).getApplicablestatus()+ "'"
//             + ",'" + ((ILoanDataHolder)toArchives.get(count)).getApplicationfee() + "'"
//             + ",'" +((ILoanDataHolder)toArchives.get(count)).getPeriodtype()+"'"
//             + ",'" + ((ILoanDataHolder)toArchives.get(count)).getFrequencyperperiod()+ "'"
//            
//                   
//            + ",'" +((ILoanDataHolder)toArchives.get(count)).getDatesupposed() + "'"
//             + ",'"+((ILoanDataHolder)toArchives.get(count)).getInstallmentamount()+"'"
//             + ",'"+((ILoanDataHolder)toArchives.get(count)).getInstallmentsno()+"'"
//                
//             + ",'"+((ILoanDataHolder)toArchives.get(count)).getLoanAmount()+"'"
//             + ",'"+((ILoanDataHolder)toArchives.get(count)).getLoanRequested()+"'"
//             + ",'"+((ILoanDataHolder)toArchives.get(count)).getLoanbalance()+"'"
//         
//         
//             + ",'" + ((ILoanDataHolder)toArchives.get(count)).getMoreorlesspaid() + "'"
//             + ",'" +((ILoanDataHolder)toArchives.get(count)).getTodaypay()+ "'"
//         
//             + ",'" + ((ILoanDataHolder)toArchives.get(count)).getPaymentstatus() + "'"
//             + ",'" + ((ILoanDataHolder)toArchives.get(count)).getDefaultstatus()+ "'"
//             + ",'" + ((ILoanDataHolder)toArchives.get(count)).getBalancebf() + "'"
//           
//             + ",'"+((ILoanDataHolder)toArchives.get(count)).getExtra()+"'"
//              + ",'"+((ILoanDataHolder)toArchives.get(count)).getLoangivenOn()+"'"
//            + ",now())";
//         
//         
//         
//          s=s+methods.executeSQlQueryN(query);
//         
//        }
//         if(s==toArchives.size()){
//             success=true;
//         }
//         else{
//             success=false;
//         }
//         return success;
//    }

    private void deleteGuranters() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
}
