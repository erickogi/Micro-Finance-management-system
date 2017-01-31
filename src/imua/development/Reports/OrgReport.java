/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imua.development.Reports;

import imua.development.Methods;
import imua.development.OrgReportDataHolder;
import java.awt.Color;
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
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author kimani kogi
 */
public class OrgReport extends javax.swing.JFrame {
Methods m=new Methods();
    /**
     * Creates new form OrgReport
     */
    public OrgReport() {
        initComponents();
        setTilteImage();
        refresh();
        getData();
      
        
    }
    private void setTilteImage(){
        Calendar  cs= Calendar.getInstance();
      Date td=cs.getTime();
        
        jDateChooserFrom.setDate(td);
        jDateChooserTo.setDate(td);
        try {
            Methods n=new Methods();
            String t= n.setTitle();
            this.setTitle(t);
            String i=n.setIconImage();
            this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource(i)));
            
            String col=n.selectcolor();
            Color c=new Color(Integer.parseInt(col));
            jPanel1.setBackground(c);
            jPanel2.setBackground(c);
            jPanel3.setBackground(c);
        } catch (Exception ex) {
            Logger.getLogger(OrgReport.class.getName()).log(Level.SEVERE, null, ex);
        }
}
   // String modeto=jComboBoxMode.getSelectedItem().toString();
public void getData(){
   
    String qo=null;
    String qi=null;
        Date fr= jDateChooserFrom.getDate();
    Date to= jDateChooserTo.getDate();
    java.util.Date frm=(fr);
     java.util.Date too=(to);
     java.sql.Date FDATE=new java.sql.Date(frm.getTime());
     java.sql.Date TDATE=new java.sql.Date(too.getTime());
     try {
            Methods m = new Methods();
            Connection con = m.getConnection();
            Statement st = con.createStatement();
    Statement st1 = con.createStatement();
     if(jComboBoxMode.getSelectedItem().toString().equals("All")){
         
         if(jComboBoxDescription.getSelectedItem().toString().equals("All")){
        qo="SELECT SUM(cashout)FROM orgaccount WHERE `date`>= '"+FDATE+"' AND `date`<= '"+TDATE+"'";
        qi="SELECT SUM(cashin)FROM orgaccount WHERE `date`>= '"+FDATE+"' AND `date`<= '"+TDATE+"'";
         }
         else{
         qo="SELECT SUM(cashout)FROM orgaccount WHERE `date`>= '"+FDATE+"' AND `date`<= '"+TDATE+"'"
                 + " AND `description`='"+jComboBoxDescription.getSelectedItem().toString()+"'";
        qi="SELECT SUM(cashin)FROM orgaccount WHERE `date`>= '"+FDATE+"' AND `date`<= '"+TDATE+"'"
                + "AND `description`='"+jComboBoxDescription.getSelectedItem().toString()+"'";
         }
    }
    else{
         if(jComboBoxDescription.getSelectedItem().toString().equals("All")){
        qo="SELECT SUM(cashout)FROM orgaccount WHERE `date`>= '"+FDATE+"' AND `date`<= '"+TDATE+"' AND `mode` = '"+jComboBoxMode.getSelectedItem().toString()+"'";
        qi="SELECT SUM(cashin)FROM orgaccount WHERE `date`>= '"+FDATE+"' AND `date`<= '"+TDATE+"' AND `mode` = '"+jComboBoxMode.getSelectedItem().toString()+"'";
         }
         else{
         qo="SELECT SUM(cashout)FROM orgaccount WHERE `date`>= '"+FDATE+"' AND `date`<= '"+TDATE+"' "
                 + "AND `mode` = '"+jComboBoxMode.getSelectedItem().toString()+"'"
                 + "AND `description`='"+jComboBoxDescription.getSelectedItem().toString()+"'";
        qi="SELECT SUM(cashin)FROM orgaccount WHERE `date`>= '"+FDATE+"' AND `date`<= '"+TDATE+"' "
                + "AND `mode` = '"+jComboBoxMode.getSelectedItem().toString()+"'"
                + "AND `description`='"+jComboBoxDescription.getSelectedItem().toString()+"'";
             
         }
         
         }
    ResultSet res = st.executeQuery(qo);
    ResultSet res1 = st1.executeQuery(qi);
            while (res.next()&&res1.next()) {
              txtIn.setText(res1.getString(1));
               txtOut.setText(res.getString(1));
               
               if(!"".equals(txtIn.getText())){
               Double tt=(Double.valueOf(txtIn.getText())-(Double.valueOf(txtOut.getText())));
               txtTotal.setText(tt.toString());
               }
               
            }
            st.close();
            res.close();
            con.close();

        } catch (SQLException ex) {
            Logger.getLogger(OrgReport.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
}
 public ArrayList<OrgReportDataHolder> ListUsers()
  {
      String query=null;
    ArrayList<OrgReportDataHolder> usersList = new ArrayList();
    Date fr= jDateChooserFrom.getDate();
    Date to= jDateChooserTo.getDate();
    java.util.Date frm=(fr);
     java.util.Date too=(to);
     java.sql.Date FDATE=new java.sql.Date(frm.getTime());
     java.sql.Date TDATE=new java.sql.Date(too.getTime());
    try
    {
        Methods m=new Methods();
        Connection con = m.getConnection();
     // Connection con = getConnection();
      Statement st = con.createStatement();
      if(jComboBoxMode.getSelectedItem().toString().equals("All")){
          if(jComboBoxDescription.getSelectedItem().toString().equals("All")){
          query="SELECT * FROM `orgaccount` WHERE `date`>= '"+FDATE+"' AND `date`<= '"+TDATE+"' ";
          }
          else{
           query="SELECT * FROM `orgaccount` WHERE `date`>= '"+FDATE+"' AND `date`<= '"+TDATE+"'"
                   + "AND `description`='"+jComboBoxDescription.getSelectedItem().toString()+"' ";   
          }
      }
      else{
           if(jComboBoxDescription.getSelectedItem().toString().equals("All")){
           query="SELECT * FROM `orgaccount` WHERE `date`>= '"+FDATE+"' AND `date`<= '"+TDATE+"' AND `mode`='"+jComboBoxMode.getSelectedItem().toString()+"'";
           }
           else{
            query="SELECT * FROM `orgaccount` WHERE `date`>= '"+FDATE+"' AND `date`<= '"+TDATE+"'"
                    + " AND `mode`='"+jComboBoxMode.getSelectedItem().toString()+"'"
                    + "AND `description`='"+jComboBoxDescription.getSelectedItem().toString()+"'";    
           }
           }
          
      String searchQuery = query;
      ResultSet rs = st.executeQuery(searchQuery);
      while (rs.next())
      {
        OrgReportDataHolder user = new OrgReportDataHolder(rs.getString("autoid"), rs.getString("cashin"),rs.getString("cashout")
                ,rs.getString("description"),rs.getString("date"),rs.getString("mode"));
        
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
    ArrayList<OrgReportDataHolder> users = ListUsers();
    DefaultTableModel model = new DefaultTableModel();
    
    model.setColumnIdentifiers(new Object[] { "ID", "IN","OUT","DESCRIPTION","MODE","DATE" });
    Object[] row = new Object[6];
    for (int i = 0; i < users.size(); i++)
    {
      row[0] = ((OrgReportDataHolder)users.get(i)).getID();
      row[1] = ((OrgReportDataHolder)users.get(i)).getIn();
      row[2] = ((OrgReportDataHolder)users.get(i)).getOut();
      row[3] = ((OrgReportDataHolder)users.get(i)).getDescription();
      row[4] = ((OrgReportDataHolder)users.get(i)).getMode();
      row[5] = ((OrgReportDataHolder)users.get(i)).getDate();
      
     
      
      model.addRow(row);
    }
    this.table.setModel(model);
  }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        txtAdd = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jComboBoxAdd = new javax.swing.JComboBox<>();
        jButton2 = new javax.swing.JButton();
        txtWithdrawal = new javax.swing.JTextField();
        jComboBoxWithdraw = new javax.swing.JComboBox<>();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jComboBoxMode = new javax.swing.JComboBox<>();
        jComboBoxDescription = new javax.swing.JComboBox<>();
        jButton3 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jDateChooserFrom = new com.toedter.calendar.JDateChooser();
        jLabel6 = new javax.swing.JLabel();
        jDateChooserTo = new com.toedter.calendar.JDateChooser();
        jLabel2 = new javax.swing.JLabel();
        txtTotal = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtIn = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtOut = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

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
        jScrollPane1.setViewportView(table);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Transact"));

        jButton1.setText("ADD");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jComboBoxAdd.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Mode", "Cash", "Mpesa", "Cheque" }));

        jButton2.setText("WITHDRAW");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jComboBoxWithdraw.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Mode", "Cash", "Mpesa", "Cheque" }));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(96, 96, 96)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(txtAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBoxAdd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(txtWithdrawal, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBoxWithdraw, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(68, 68, 68))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtAdd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1)
                    .addComponent(jComboBoxAdd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(46, 46, 46)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(txtWithdrawal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBoxWithdraw, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(121, 121, 121))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel1.setText("Mode");

        jComboBoxMode.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "All", "Cash", "Mpesa", "Cheque" }));
        jComboBoxMode.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBoxModeItemStateChanged(evt);
            }
        });

        jComboBoxDescription.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "All", "CAPTIAL ADDED", "CAPTIAL WITHDRAWN", "Registration Fee", "Customer Account Deposit", "Customer Account Withdrawal", "Loan Installments", "LOAN PENALTY", "Loan Application Fee", "LOAN GIVEN" }));

        jButton3.setText("REFRESH");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel5.setText("FROM");

        jLabel6.setText("TO");

        jLabel2.setText("TOTAL AMOUNT ");

        jLabel3.setText("IN ");

        jLabel4.setText("OUT");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jComboBoxMode, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboBoxDescription, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5)
                                    .addComponent(jDateChooserFrom, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel3)
                                    .addComponent(txtIn, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(110, 110, 110)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jDateChooserTo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtOut)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel4)
                                            .addComponent(jLabel6))
                                        .addGap(0, 0, Short.MAX_VALUE))))))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(208, 208, 208)
                        .addComponent(jLabel2))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(194, 194, 194)
                        .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jComboBoxMode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBoxDescription, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3))
                .addGap(30, 30, 30)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jDateChooserFrom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jDateChooserTo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtIn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtOut, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(11, 11, 11)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 356, Short.MAX_VALUE))
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
      if(txtAdd.getText().isEmpty()||jComboBoxAdd.getSelectedItem().toString().equals("Mode")){
          JOptionPane.showMessageDialog(null, "YOU MUST ENTER A FIGURE AND MODE","ERROR",JOptionPane.ERROR_MESSAGE);
      }else{
        try{
        Double cas=Double.valueOf(txtAdd.getText());
        m.addToOrgAccount(cas,"CAPTIAL ADDED",jComboBoxAdd.getSelectedItem().toString() );
        getData();
        txtAdd.setText("");
        
                 JOptionPane.showMessageDialog(null, "CAPITAL SUCCESSFULLY","SUCCESS",JOptionPane.INFORMATION_MESSAGE);
      }
      catch(Exception m){
          m.printStackTrace();
      }
      }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
         if(txtWithdrawal.getText().isEmpty()||jComboBoxAdd.getSelectedItem().toString().equals("Mode")){
          JOptionPane.showMessageDialog(null, "YOU MUST ENTER A FIGURE AND MODE","ERROR",JOptionPane.ERROR_MESSAGE);
      }
         else{
        try{
        Double cas=Double.valueOf(txtWithdrawal.getText());
        m.RemoveFromOrgAccount(cas,"CAPTIAL WITHDRAWN",jComboBoxAdd.getSelectedItem().toString() );
        getData();  
        txtWithdrawal.setText("");
        JOptionPane.showMessageDialog(null, "WITHDRWAL SUCCESSFUL","SUCCESS",JOptionPane.INFORMATION_MESSAGE);
                
      }
      catch(Exception m){
          m.printStackTrace();
      }
         }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
       refresh();
       getData();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jComboBoxModeItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBoxModeItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxModeItemStateChanged

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
            java.util.logging.Logger.getLogger(OrgReport.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(OrgReport.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(OrgReport.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(OrgReport.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new OrgReport().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JComboBox<String> jComboBoxAdd;
    private javax.swing.JComboBox<String> jComboBoxDescription;
    private javax.swing.JComboBox<String> jComboBoxMode;
    private javax.swing.JComboBox<String> jComboBoxWithdraw;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private com.toedter.calendar.JDateChooser jDateChooserFrom;
    private com.toedter.calendar.JDateChooser jDateChooserTo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable table;
    private javax.swing.JTextField txtAdd;
    private javax.swing.JTextField txtIn;
    private javax.swing.JTextField txtOut;
    private javax.swing.JTextField txtTotal;
    private javax.swing.JTextField txtWithdrawal;
    // End of variables declaration//GEN-END:variables
}
