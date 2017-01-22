/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imua.development;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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

/**
 *
 * @author kimani kogi
 */
public class Defaulters extends javax.swing.JFrame {
 ButtonGroup radiog =new ButtonGroup();
 int action=0;
    /**
     * Creates new form Defaulters
     */
    public Defaulters() {
        initComponents();
        refresh();
        setTilteImage();
        getNewRenderedTable(table);
        table.getTableHeader().setEnabled(false);
        radiog.add(jRadioButton1);
        radiog.add(jRadioButton2);
        jRadioButton2.setSelected(true);
        // TableRowSorter<TableModel> sorter = new TableRowSorter<>(table.getModel());
        //table.setRowSorter(sorter);
       // sorter.setSortable(0, false);
       
       
       
        jRadioButton1.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
        action=1;
        
        }
    });
         jRadioButton2.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
        action=0;

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
            Logger.getLogger(Accgroups.class.getName()).log(Level.SEVERE, null, ex);
        }
}
     public  Date addOne(Date date,int hw){
      Calendar  c= Calendar.getInstance();
      c.setTime(date);
      c.add(Calendar.DATE, hw);
   //   c.add(Calendar.WEEK_OF_MONTH, hw);
      
      return c.getTime();
  }
    public ArrayList<defaultersHolder> ListUsers( )
  {
    ArrayList<defaultersHolder> usersList = new ArrayList();
      Calendar  c= Calendar.getInstance();
         Date today=addOne(c.getTime(),-2);
         
         java.util.Date d=(today);
  //Days d=Days.daysBeetween();
         java.sql.Date DATE=new java.sql.Date(d.getTime());
         String nl="np";
    try
    {
        Methods m=new Methods();
        Connection con = m.getConnection();
     // Connection con = getConnection();
      Statement st = con.createStatement();
     // String searchQuery = "SELECT * FROM `accounttypes`";
      String searchQuery = "SELECT * FROM `loans`WHERE targetdate<'" + DATE + "'AND nxp='"+nl+"' ";
      ResultSet rs = st.executeQuery(searchQuery);
      while (rs.next())
      {
         // String ID, Date ate, String Installmentamount, String LoanAmount,String autoid,String instno
        defaultersHolder user = new defaultersHolder(rs.getString("loanid"),rs.getString("id"), rs.getDate("targetdate"),rs.getString("installmentamount")
                ,rs.getString("loanAmount")
                ,rs.getString("autoid")
                ,rs.getString("installmentsno")
                ,rs.getString("loantype")
                
        
        
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
    ArrayList<defaultersHolder> users = ListUsers();
    DefaultTableModel model = new DefaultTableModel();
  //   String ID, Date ate, String Installmentamount, String LoanAmount,String autoid,String instno
    model.setColumnIdentifiers(new Object[] { "DATE","LOANID","ID", "NAME","ELAPSED","INSTALLMENT","LOAN","LOAN TYPE","UNIQ","IO"});
    Object[] row = new Object[10];
    for (int i = 0; i < users.size(); i++)
    {
        row[0] = ((defaultersHolder)users.get(i)).getDate();
        row[2] = ((defaultersHolder)users.get(i)).getId();
         row[1] = ((defaultersHolder)users.get(i)).getLId();
        row[3]= getName(((defaultersHolder)users.get(i)).getId());
       // SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:SS");
        Date d=new Date();
        Date e=((defaultersHolder)users.get(i)).getDate();
        het(d,e);
        row[4]=het(d,e);
        row[5] = ((defaultersHolder) users.get(i)).getInstallmentAmount();
        row[6] = ((defaultersHolder) users.get(i)).getLoanAmount();
        row[7] = ((defaultersHolder) users.get(i)).getLoanType();
        row[8] = ((defaultersHolder) users.get(i)).getAutoId();
        row[9] = ((defaultersHolder) users.get(i)).getInstallmentNo();
        model.addRow(row);
    }
    this.table.setModel(model);
  
  }
  public String getName(String id){
        String name=null;
      try {
            
            Methods m = new Methods();
            Connection con = m.getConnection();
            Statement st = con.createStatement();
            String searchQuery = "SELECT `fname` FROM `users` WHERE `id` = '" + id + "'";
            ResultSet rs = st.executeQuery(searchQuery);
            while (rs.next()) {
                name=rs.getString("fname");
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(Defaulters.class.getName()).log(Level.SEVERE, null, ex);
        }
        return name;
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
        sorter(4);
        table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer(){
            @Override
            public Component getTableCellRendererComponent(JTable table,
                    Object value, boolean isSelected, boolean hasFocus, int row, int col) {
                super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, col);
                int status = Integer.valueOf(table.getModel().getValueAt(row, 4).toString());
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
        txtId = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtFrom = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtDays = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtLoan = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtInstallment = new javax.swing.JTextField();
        jTextField7 = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtFined = new javax.swing.JTextField();
        txtInstallmentValue = new javax.swing.JTextField();
        txtTotal = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

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
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 664, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("PROPERTIES"));

        jLabel1.setText("NAME");

        jLabel2.setText("ID");

        jLabel3.setText("FROM");

        jLabel4.setText("DAYS");

        jLabel5.setText("LOAN AMOUNT");

        jLabel6.setText("INSTALLMENT ");

        jLabel7.setText("FINE");

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
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jTextField7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 83, Short.MAX_VALUE)
                            .addComponent(txtLoan)
                            .addComponent(txtFrom)
                            .addComponent(txtName)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING))
                        .addGap(106, 106, 106)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(txtInstallment, javax.swing.GroupLayout.DEFAULT_SIZE, 95, Short.MAX_VALUE)
                                .addGap(48, 48, 48))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtDays, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap())))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(0, 0, Short.MAX_VALUE))))
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
                    .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Process"));

        jRadioButton1.setText("WAIVE ");
        jRadioButton1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jRadioButton1ItemStateChanged(evt);
            }
        });
        jRadioButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton1ActionPerformed(evt);
            }
        });

        jRadioButton2.setText("EFFECT");

        jLabel8.setText("FINE");

        jLabel9.setText("INSTALLMENT");

        jLabel10.setText("TOTAL");

        jButton1.setText("CLEAR");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

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
                            .addComponent(jRadioButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE))
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jRadioButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(46, 46, 46))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(46, 46, 46)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
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
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jRadioButton1)
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
                    .addComponent(jRadioButton2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 49, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addGap(65, 65, 65))
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
public void process(){
    if(action==0){
      Double rate=  getDefaultRate();
      
       Double fine=   (rate*Double.valueOf(txtInstallment.getText()))*Integer.valueOf(txtDays.getText());
      txtFined.setText(String.valueOf(fine));
      txtInstallmentValue.setText(txtInstallment.getText());
      txtTotal.setText(String.valueOf(fine+Double.valueOf(txtInstallment.getText())));
      JOptionPane.showMessageDialog(null, fine+" "+rate);
      
      
    }
    else{
         Double fine= 0.0;  
       txtFined.setText(String.valueOf(fine));
      txtInstallmentValue.setText(txtInstallment.getText());
      txtTotal.setText(String.valueOf(fine+Double.valueOf(txtInstallment.getText())));
        
    }
}
public double getDefaultRate(){
    double rate=0;
     try {
            
            Methods m = new Methods();
            Connection con = m.getConnection();
            Statement st = con.createStatement();
            String searchQuery = "SELECT `penalt` FROM `accounttypes` WHERE `name` = '" + loantypew + "'";
            ResultSet rs = st.executeQuery(searchQuery);
            while (rs.next()) {
                rate=Double.valueOf(rs.getString("penalt"));
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(Defaulters.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    return rate;
}
String loantypew;
String autoidd;
String loanidd;
String insno;
    private void tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableMouseClicked
        int i = this.table.getSelectedRow();
     ;
    TableModel model = this.table.getModel();
                txtId.setText(model.getValueAt(i, 2).toString());
                this.txtName.setText(model.getValueAt(i, 3).toString());
                this.txtDays.setText(model.getValueAt(i, 4).toString());
                this.txtLoan.setText(model.getValueAt(i, 6).toString());
                this.txtFrom.setText(model.getValueAt(i, 0).toString());
                this.txtInstallment.setText(model.getValueAt(i, 5).toString());
                this.loantypew=(model.getValueAt(i, 7).toString());
                this.autoidd=(model.getValueAt(i, 8).toString());
                this.loanidd=(model.getValueAt(i, 1).toString());
                this.insno=(model.getValueAt(i, 9).toString());
        
              process();
        
        
        
    }//GEN-LAST:event_tableMouseClicked

    private void jRadioButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButton1ActionPerformed

    private void jRadioButton1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jRadioButton1ItemStateChanged
        
    }//GEN-LAST:event_jRadioButton1ItemStateChanged

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
      Methods n=new Methods();
       Double finr=Double.valueOf(txtTotal.getText());
        String fee=   JOptionPane.showInputDialog("Enter Total ");
       Double bal=Double.valueOf(fee)-Double.valueOf(finr);
        n.addToOrgAccount(Double.valueOf(txtFined.getText()),"LOAN PENALTY" );
        if(bal>0){
             JOptionPane.showMessageDialog(null, "Balance is " +bal.toString());
        }
        updateLoan(txtInstallment.getText(),txtFined.getText(),autoidd);
    }//GEN-LAST:event_jButton1ActionPerformed
public void updateLoan(String todaypay,String penalty,String auto){
    int installmentNo=Integer.valueOf(insno)-1;
    String instno=String.valueOf(installmentNo);
     Methods n=new Methods();
    // JOptionPane.showMessageDialog(null, todaypay+" "+penalty+" "+instno+" "+loanidd);
     String query = "UPDATE `loans` SET `todaypay`='"+todaypay+"',`defaultacc`='"+penalty+
             "'WHERE loanid= '" +loanidd+"'AND installmentsno='" +instno+"'";
     if(n.executeSQlQueryN(query)==1){
      updateLoanNp(txtInstallment.getText(),txtFined.getText(),autoidd);
     }
     else{
         JOptionPane.showMessageDialog(null, "ERROR ENCOUNTERD","ERROR",JOptionPane.ERROR_MESSAGE);
     }
//    if( n. executeSQlQuery(query, "Updated")==1){
//        loanpayment lp=new loanpayment();
//        String maxv=String.valueOf(maxid("loanid",loanidd));
//        checkBalance("autoid",maxv);
//       // checkWhetherHasOutStandingFines(txtId.getText(),autoidd);
//        clear();
//    }
    
    
}
public void updateLoanNp(String todaypay,String penalty,String auto){
   // int installmentNo=Integer.valueOf(insno)-1;
   // String instno=String.valueOf(installmentNo);
    String nxp="paid";
     Methods n=new Methods();
     String query = "UPDATE `loans` SET `nxp`='"+nxp+"'WHERE autoid= '" +auto+"'AND installmentsno='" +insno+"'AND loanid='" +loanidd+"'";
      
    if( n. executeSQlQuery(query, "Updated")==1){
        loanpayment lp=new loanpayment();
        String maxv=String.valueOf(maxid("loanid",loanidd));
        checkBalance("autoid",maxv);
       // checkWhetherHasOutStandingFines(txtId.getText(),autoidd);
        clear();
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
            java.util.logging.Logger.getLogger(Defaulters.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Defaulters.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Defaulters.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Defaulters.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Defaulters().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
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
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTable table;
    private javax.swing.JTextField txtDays;
    private javax.swing.JTextField txtFined;
    private javax.swing.JTextField txtFrom;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtInstallment;
    private javax.swing.JTextField txtInstallmentValue;
    private javax.swing.JTextField txtLoan;
    private javax.swing.JTextField txtName;
    private javax.swing.JTextField txtTotal;
    // End of variables declaration//GEN-END:variables

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

public void clear(){
     jTextField7.setText("");
  
    txtDays.setText("");
     txtFined.setText("");
   txtFrom.setText("");
    txtId.setText("");
     txtInstallment.setText("");
   txtInstallmentValue.setText("");
    txtLoan.setText("");
    txtName.setText("");
    txtTotal.setText("");
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
    }
    catch(Exception nw){
        nw.printStackTrace();
    }
    return id;
}
public void checkBalance(String where,String max){
    Methods m = new Methods();Connection con = m.getConnection();
    try{
        String getMaxId="SELECT loanbalance FROM `loans` WHERE `" + where + "` = '" + max + "'";
         Statement st = con.createStatement();
           ResultSet rs = st.executeQuery(getMaxId);
           if(rs.next()){
               if(rs.getDouble(1)==0.0){
                 checkWhetherHasOutStandingFines(txtId.getText(),loanidd);
               }
                   
              
           }
    }
    catch(Exception nw){
        nw.printStackTrace();
    }
}
public int checkWhetherHasOutStandingFines(String id,String loanid){
         HashMap<String,String>defaults=new HashMap<String,String>();
      try {
          Calendar  c= Calendar.getInstance();
         
          Date today=c.getTime();
          java.util.Date d=(today);
          
          java.sql.Date DATE=new java.sql.Date(d.getTime());
          
          Methods n = new Methods();
          String nl="null";
          Connection con =n. getConnection();
          
          Statement st = con.createStatement();
            String searchQuery = "SELECT * FROM `loans`WHERE loanid='" + loanid + "'AND todaypay='"+nl+"' ";
        //  String searchQuery = "SELECT * FROM `loans`WHERE targetdate='" + DATE + "' ";
          ResultSet rs = st.executeQuery(searchQuery);
          while (rs.next())
          {
              
              defaults.put(rs.getString("targetdate"), rs.getString("installmentamount"));
              
          }
          st.close();
          rs.close();
          con.close();
          if(defaults.isEmpty()){
              getToArchives("loanid",loanid);
          }
      } catch (SQLException ex) {
          Logger.getLogger(loanpayment.class.getName()).log(Level.SEVERE, null, ex);
      }
        
        
        
        
       return defaults.size(); 
        
    }

//i had to add m on each srring aty for debugging 
// i actualy dont need them but am afraid to delete them am sorry future me hope you will forgive me
String id;
    String loanid;
    String idm;
    String loanidm;
    String loantypem;
String applicablem;
String installmentamountm;
String installmentsnom;
String periodtypem;
String frequencyperperiodm;
Date targetdatem;
String loanAmountm;
String loanRequestedm;
String loanbalancem;
String defaultaccm;
String applicationfeem;
String todaypaym;
String givenOnm;
String paidonm;

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
idm=rs.getString("id");
loanidm=rs.getString("loanid");
loantypem=rs.getString("loantype");
applicablem=rs.getString("applicable");
installmentamountm=rs.getString("installmentamount");
installmentsnom=rs.getString("installmentsno");
periodtypem=rs.getString("periodtype");
frequencyperperiodm=rs.getString("frequencyperperiod");
targetdatem=rs.getDate("targetdate");
loanAmountm=rs.getString("loanAmount");
loanRequestedm=rs.getString("loanRequested");
loanbalancem=rs.getString("loanbalance");
defaultaccm=rs.getString("loantype");
applicationfeem=rs.getString("applicationfee");
todaypaym=rs.getString("todaypay");
 givenOnm=rs.getString("givenOn");
paidonm=rs.getString("paidon");
                 
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
               String query = "DELETE FROM `loans`  WHERE `loanid` = '" +value+"'";
                m.executeSQlQueryN(query);
             JOptionPane.showMessageDialog(null, "Succesfully moved to archive"); 
             
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
        int s=0;
    //ii ni urogi sioni shida
//    \\\\\\\  /////////
//     \\\\\\ //////////        /
                
    ////yes  ssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss
    
    
    
       
        Methods m=new Methods();
         String query = "INSERT INTO `loansarchives`("
             + "`id`,"
             + "`loanid`,"
             
             + "`loantype`,"
             +"`applicable`"
             + ",`installmentamount`"
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
             + "'" + idm + "'"
             + ",'" + loanidm+"'"
             + ",'" + loantypem+ "'"
             + ",'" + applicablem+ "'"
             + ",'" + installmentamountm + "'"
             + ",'" +installmentsnom+"'"
             + ",'" + periodtypem + "'"
             + ",'" + frequencyperperiodm+ "'"
                   
            + ",'" + targetdatem + "'"
             + ",'"+loanAmountm+"'"
             + ",'"+loanRequestedm+"'"
                
             + ",'"+loanbalancem+"'"
             + ",'"+defaultaccm+"'"
             + ",'"+applicationfeem+"'"
             + ",'" +todaypaym + "'"
             + ",'" + givenOnm + "'"
           
             + ",'" + paidonm + "')";
         
          s=m.executeSQlQueryN(query);
        
       
         return s;
    } 
}
