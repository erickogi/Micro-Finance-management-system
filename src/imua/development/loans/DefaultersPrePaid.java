/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imua.development.loans;

import com.lowagie.text.BadElementException;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Image;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import imua.development.defaultersHolder;
import imua.development.*;
import imua.development.Reports.GroupSheet;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Desktop;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import static java.lang.System.out;
import java.net.MalformedURLException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
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
public class DefaultersPrePaid extends javax.swing.JFrame {
 ButtonGroup radiog =new ButtonGroup();
 int action=0;
 Methods m=new Methods();
    /**
     * Creates new form Defaulters
     */
    public DefaultersPrePaid() {
        initComponents();
                 
        refresh();
      //  sorter(3);
        setTilteImage();
       // getNewRenderedTable(table);
        table.getTableHeader().setEnabled(false);
       // radiog.add(jRadioButtonWaive);
       // radiog.add(jRadioButtonEffect);
       // jRadioButtonEffect.setSelected(true);
        // TableRowSorter<TableModel> sorter = new TableRowSorter<>(table.getModel());
        //table.setRowSorter(sorter);
       // sorter.setSortable(0, false);
       
       
     //  getNewRenderedTable(table);
       
       
//        jRadioButtonWaive.addActionListener(new ActionListener() {
//        @Override
//        public void actionPerformed(ActionEvent e) {
//        action=1;
//        process();
//        
//        }
//    });
//         jRadioButtonEffect.addActionListener(new ActionListener() {
//        @Override
//        public void actionPerformed(ActionEvent e) {
//        action=0;
//        process();
//
//        }
//    });

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
            // ex.
            //StackTraceElement[]   stackTrace  =  ex.getStackTrace();
            
            
            
            
            
            
            
            
            
            
            Logger.getLogger(DefaultersPrePaid.class.getName()).log(Level.SEVERE, null, ex);
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
         Date today=addOne(c.getTime(),-1);
         
         java.util.Date d=(today);
  //Days d=Days.daysBeetween();
         java.sql.Date DATE=new java.sql.Date(d.getTime());
        String nfp="nfp";
        String np="np";
          String paid;
         paid = "paid";
          
    try
    {
        Methods m=new Methods();
        Connection con = m.getConnection();
     // Connection con = getConnection();
      Statement st = con.createStatement();
    // String searchQuery1 = "SELECT * FROM `accounttypes`";
      String searchQuery = "SELECT * FROM `loans`WHERE  nxp='"+nfp+"' OR nxp='"+np+"'";
      ResultSet rs = st.executeQuery(searchQuery);
      while (rs.next())
      { 
         // String ID, Date ate, String Installmentamount, String LoanAmount,String autoid,String instno
        defaultersHolder user = new defaultersHolder(
                 rs.getString("loanid")
                ,rs.getString("id")
                ,rs.getDate("targetdate")
                ,rs.getString("installmentamount")
                ,rs.getString("loanAmount")
                ,rs.getString("autoid")
                ,rs.getString("installmentsno")
                ,rs.getString("loantype")
                ,rs.getString("defaultacc")
                ,rs.getString("nxp")
                
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
    // model.setColumnIdentifiers(new Object[] { "DATE","LOANID","ID", "NAME","ELAPSED","INSTALLMENT","LOAN","LOAN TYPE","UNIQ","IO"});
    model.setColumnIdentifiers(new Object[] { "DATE","F-NAME","L-NAME","DATE","INSTALLMENT","LOAN","ID"});
    Object[] row = new Object[7];
    for (int i = 0; i < users.size(); i++)
    {
        row[0] = ((defaultersHolder)users.get(i)).getDate();
       // row[2] = ((defaultersHolder)users.get(i)).getId();
       // row[1] = ((defaultersHolder)users.get(i)).getLId();
           String[] fname=     getName(((defaultersHolder)users.get(i)).getId());
           row[1]= fname[0];
           row[2]=fname[1];
         
       // SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:SS");
        Date d=new Date();
       // Date e=((defaultersHolder)users.get(i)).getDate();
       // het(d,e);
      // row[3]=het(d,e);
       row[3]=((defaultersHolder)users.get(i)).getDate();
        row[4] = ((defaultersHolder) users.get(i)).getInstallmentAmount();
        row[5] = ((defaultersHolder) users.get(i)).getLoanAmount();
      // row[7] = ((defaultersHolder) users.get(i)).getLoanType();
        row[6] = ((defaultersHolder) users.get(i)).getAutoId();
     //   row[9] = ((defaultersHolder) users.get(i)).getInstallmentNo();
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
            Logger.getLogger(DefaultersPrePaid.class.getName()).log(Level.SEVERE, null, ex);
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
        txtId = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtLoan = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtInstallment = new javax.swing.JTextField();
        txtBBF = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtInstallmentValue = new javax.swing.JTextField();
        txtTotal = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel11 = new javax.swing.JLabel();

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
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 664, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 565, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("PROPERTIES"));

        jLabel1.setText("NAME");

        jLabel2.setText("ID");

        jLabel5.setText("LOAN AMOUNT");

        jLabel6.setText("INSTALLMENT ");

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
                        .addComponent(jLabel5)
                        .addGap(116, 116, 116)
                        .addComponent(jLabel6)
                        .addGap(63, 63, 63))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txtBBF, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtLoan, javax.swing.GroupLayout.DEFAULT_SIZE, 83, Short.MAX_VALUE)
                            .addComponent(txtName)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING))
                        .addGap(106, 106, 106)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(58, Short.MAX_VALUE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(txtInstallment, javax.swing.GroupLayout.DEFAULT_SIZE, 95, Short.MAX_VALUE)
                                .addGap(48, 48, 48))))))
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
                .addGap(74, 74, 74)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtLoan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtInstallment, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtBBF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Process"));

        jLabel9.setText("INSTALLMENT");

        jLabel10.setText("TOTAL");

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

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11)
                    .addComponent(jLabel9)
                    .addComponent(jLabel10))
                .addGap(59, 59, 59)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 109, Short.MAX_VALUE)
                    .addComponent(txtTotal)
                    .addComponent(txtInstallmentValue))
                .addContainerGap(97, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtInstallmentValue, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
    Double toBUsed=0.0;
    try{
        Double post=Math.abs(Double.valueOf(nfpamount));
       if(post>=1){
           toBUsed=post;
           txtBBF.setText(String.valueOf(toBUsed));
      // Double amnt= Double.valueOf(txtInstallment.getText());
      // Double dfa=Double.valueOf(nfpamount);
      
        
    } 
       else{
           toBUsed=Double.valueOf(txtInstallment.getText());
       }
       txtBBF.setText(String.valueOf("0.0"));
    }
    catch(Exception m){
        System.out.println("Not a no");
        toBUsed=Double.valueOf(txtInstallment.getText());
    }
    
    
    
    if(action==0){
      Double rate=  getDefaultRate();
     
      DecimalFormat df=new DecimalFormat("#.0");
     //  JOptionPane.showMessageDialog(null, rate+" "+rate);
    Double processpenalty=Double.valueOf(df.format(rate));
    // txtRate.setText(String.valueOf(processpenalty));
  //JOptionPane.showMessageDialog(null, rate+" "+processpenalty);
      // Double fine=   (processpenalty*toBUsed)*Integer.valueOf(txtDays.getText());
     // txtFined.setText(String.valueOf(fine));
      txtInstallmentValue.setText(String.valueOf(toBUsed));
     // txtTotal.setText(String.valueOf(fine+Double.valueOf(txtInstallmentValue.getText())));
     // JOptionPane.showMessageDialog(null, fine+" "+rate);
      
      
    }
    else{
         Double fine= 0.0;  
    //   txtFined.setText(String.valueOf(fine));
      txtInstallmentValue.setText(String.valueOf(toBUsed));
      txtTotal.setText(txtInstallmentValue.getText());
        
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
            st.close();
            rs.close();
            con.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(DefaultersPrePaid.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    return rate;
}
String loantypew;
String autoidd;
String loanidd;
String insno;
String nfpamount;
    private void tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableMouseClicked
        int i = this.table.getSelectedRow();
     ;
                TableModel model = this.table.getModel();
                 this.autoidd=(model.getValueAt(i, 6).toString());
                this.txtName.setText(model.getValueAt(i, 1).toString());
                
              //  this.txtDays.setText(model.getValueAt(i, 3).toString());
                this.txtLoan.setText(model.getValueAt(i, 5).toString());
              //  this.txtFrom.setText(model.getValueAt(i, 0).toString());
                this.txtInstallment.setText(model.getValueAt(i, 4).toString());
                
                
                
                getDetails(autoidd);
               
             //   JOptionPane.showMessageDialog(this, table.getSelectedRow()+"\n "+table.getSelectedColumn()+" "+model.getValueAt(i, 0));
      
             //   txtId.setText(model.getValueAt(i, 2).toString());
             //   this.loantypew=(model.getValueAt(i, 7).toString());
             //   this.loanidd=(model.getValueAt(i, 1).toString());
             //   this.insno=(model.getValueAt(i, 9).toString());
        
              process();
        
        
        
    }//GEN-LAST:event_tableMouseClicked
    String tnxp;
    public void getDetails(String value){
     try {
            
            Methods m = new Methods();
            Connection con= m.getConnection();
            Statement st = con.createStatement();
             String searchQuery = "SELECT id,loanid,loantype,installmentsno,defaultacc,nxp FROM `loans`WHERE autoid='"+value+"'";
          
            ResultSet rs = st.executeQuery(searchQuery);
            while (rs.next()) {
                txtId.setText(rs.getString("id"));
                //loantypew=rs.getString("loantype");
                loanidd=rs.getString("loanid");
                insno=rs.getString("installmentsno");
                nfpamount=rs.getString("defaultacc");
                tnxp=rs.getString("nxp");
         }
            st.close();
            rs.close();
            con.close();
            
            
        } catch (SQLException ex) {
            Logger.getLogger(DefaultersPrePaid.class.getName()).log(Level.SEVERE, null, ex);
        }
}
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
      if(jComboBox1.getSelectedItem().toString().equals("Mode")){
          JOptionPane.showMessageDialog(null, "Select mode of payment","Message",JOptionPane.ERROR_MESSAGE);
      }
      else{
        Methods n=new Methods();
        String mode=jComboBox1.getSelectedItem().toString();
        Double finr=Double.valueOf(txtTotal.getText());
        String fee = JOptionPane.showInputDialog("Enter Total ");
        Double bal =Double.valueOf(fee)-finr;
       // n.addToOrgAccount(Double.valueOf(txtFined.getText()),"LOAN PENALTY" ,mode);
        n.addToOrgAccount(Double.valueOf(txtInstallmentValue.getText()),"Loan Installments",mode );
        if(bal>0){
             //JOptionPane.showMessageDialog(null, "Balance is " +bal.toString());
             updateLoan(txtTotal.getText(),String.valueOf(bal),autoidd,"pm");
        }
        else if(bal<0){
              updateLoan(txtTotal.getText(),String.valueOf(bal),autoidd,"nfp");
              JOptionPane.showMessageDialog(null, "Balance " +bal.toString());
        }
        else if (bal==0){
            updateLoan(txtTotal.getText(),String.valueOf(bal),autoidd,"paid");
        }
      }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox1ActionPerformed
//public void update 
    public void updateLoan(String todaypay,String Default,String auto,String tp){
    int installmentNo=Integer.valueOf(insno);
    String instno=String.valueOf(installmentNo-1);
     Methods n=new Methods();
     //String tp="paid";
     
    //// JOptionPane.showMessageDialog(null, todaypay+" "+penalty+" "+instno+" "+loanidd);
    
    String query = "UPDATE `loans` SET `todaypay`='"+todaypay+"',`defaultacc`='"+Default+ "',`tp`='"+tp+"'  WHERE loanid= '" +loanidd+"'AND installmentsno='" +instno+"'";
    n.executeSQlQueryN(query);
    
   // updateLoanNp(txtInstallment.getText(),txtFined.getText(),autoidd,tp);
    
    // }
    // else{
    //     JOptionPane.showMessageDialog(null, "ERROR ENCOUNTERD","ERROR",JOptionPane.ERROR_MESSAGE);
    // }
    //    if( n. executeSQlQuery(query, "Updated")==1){
    //        loanpayment lp=new loanpayment();
    //        String maxv=String.valueOf(maxid("loanid",loanidd));
    //        checkBalance("autoid",maxv);
    //       // checkWhetherHasOutStandingFines(txtId.getText(),autoidd);
    //        clear();
    //    }
    
    
}
   public void updateLoanNp(String todaypay,String penalty,String auto,String nxp){
    int installmentNo=Integer.valueOf(insno);
    String instno=String.valueOf(installmentNo);
    String tp="paid";
    try{
    //String nxp="paid";
    Methods n=new Methods();
    String query = "UPDATE `loans` SET `nxp`='"+nxp+"'WHERE installmentsno='" +instno+"'AND loanid='" +loanidd+"'";
   
   
    if( n.executeSQlQuery(query, "Updated")==1){
       clear();
        String maxv=String.valueOf(maxid("loanid",loanidd));
        
        checkBalance("autoid",maxv);
        
        clear();
    }
    else{
        out.println("Error occoured while updating nxp of previous loan record");
    }
   }
   catch(Exception n){
       n.printStackTrace();
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
            java.util.logging.Logger.getLogger(DefaultersPrePaid.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DefaultersPrePaid.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DefaultersPrePaid.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DefaultersPrePaid.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DefaultersPrePaid().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable table;
    private javax.swing.JTextField txtBBF;
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
     //txtRate.setText("");
  
    //txtDays.setText("");
    // txtFined.setText("");
   //txtFrom.setText("");
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
       st.close();
      rs.close();
      con.close();
    }
    catch(Exception nw){
        nw.printStackTrace();
    }
    return id;
}
public void checkBalance(String where,String max){
Methods m = new Methods(); Connection con = m.getConnection();
    try{
        String getMaxId="SELECT loanbalance FROM `loans` WHERE `" + where + "` = '" + max + "'";
         Statement st = con.createStatement();
           ResultSet rs = st.executeQuery(getMaxId);
           if(rs.next()){
              if(rs.getDouble(1)==0.0||rs.getDouble(1)<1){
                checkWhetherHasOutStandingFines(txtId.getText(),loanidd);
               }
                   
              
           }
      st.close();
      rs.close();
      con.close();
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
             String paid="paid";
          Connection con =n. getConnection();
          
          Statement st = con.createStatement();
            String searchQuery = "SELECT * FROM `loans`WHERE (loanid='" + loanid + "'AND todaypay='"+nl+"')OR (loanid='" + loanid + "'AND nxp!='"+paid+"')OR (loanid='" + loanid + "'AND tp!='"+paid+"') ";
        //  String searchQuery = "SELECT * FROM `loans`WHERE targetdate='" + DATE + "' ";
          ResultSet rs = st.executeQuery(searchQuery);
          while (rs.next())
          {
              
              defaults.put(rs.getString("targetdate"), rs.getString("installmentamount"));
              
          }
          st.close();
          rs.close();
          con.close();
          if(defaults.isEmpty()||defaults.size()<=1){
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
             deleteGuranters(value);
          }
          else{
              JOptionPane.showMessageDialog(null, "Error moving to archive");
          }
    }
    catch(Exception m){
        m.printStackTrace();
    }

    
    
    }
public void deleteGuranters(String value){
     Methods m=new Methods();
    
              String query = "DELETE FROM `guranters`  WHERE `id` = '" +value+"'";
                m.executeSQlQueryN(query);
    
    
    
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
    
    
    
    
    
    
    
    
    
    
    
    
      public int print(String pid,String loanid,String loantype){
          int rc=0;
      String []resl=  m.getNameImage(pid);
      String name=resl[0];
      String imgurl=resl[1];
      String []res= m.getNameImage();
      String Oimgurl=res[0];
      
        Calendar  c= Calendar.getInstance();
       Date today=c.getTime();
         
         java.util.Date d=(today);
  //Days d=Days.daysBeetween();
         java.sql.Date DATE=new java.sql.Date(d.getTime());
         String OrgDetails=res[1]+"\n"+DATE.toString();
    try {
        // jComboBoxGroup
        
        JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(new java.io.File(","));
        chooser.setDialogTitle("Save at");
        chooser.setApproveButtonText("save");
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        if(chooser.showOpenDialog(this)==JFileChooser.APPROVE_OPTION){
            try {
                Document pdfp=new Document();
                PdfWriter.getInstance(pdfp, new FileOutputStream(new File(chooser.getSelectedFile(),loanid+""+DATE+".pdf")));
                pdfp.open();
                PdfPTable header=new PdfPTable(3);
                //  tbl.setWidthPercentage(100);
                
                header.setTotalWidth(575);
                header.setLockedWidth(true);
                
                header.setWidths(new int[]{1,4,1});
                if(Oimgurl.equals("image")){
                    header.addCell("no image ");
                    
                    String img=Oimgurl;
                }
                else{
                    header.addCell(createImageCell(Oimgurl));
                    
                }
                
                header.addCell(createTextCell(OrgDetails));
                if(imgurl.equals("image")){
                    header.addCell("no image ");
                    
                    String img=imgurl;
                }
                else{
                    header.addCell(createImageCell(imgurl));
                    
                }
                
                PdfPTable group=new PdfPTable(2);
                // headings.s
                 ArrayList<LoanDataHolder> Loan = ListUsers(loanid);
                // for(int f=0;f<Loan.size();f++){
                int ap=Integer.valueOf(((LoanDataHolder)Loan.get(0)).getInstallmentNo())+1;
                group.addCell(creatTextCellHeader("Name        :"+name));
                group.addCell(creatTextCellHeader("ID          :"+pid));
                group.addCell(creatTextCellHeader("Amount     :"+((LoanDataHolder)Loan.get(0)).getLoanAmount()));
                group.addCell(creatTextCellHeader("Type       :"+loantype));
                group.addCell(creatTextCellHeader("Installment:"+String.valueOf(ap)));
                group.addCell(creatTextCellHeader("Value      :"+((LoanDataHolder)Loan.get(0)).getInstallmentAmount()));
                group.addCell(creatTextCellHeader("Given On   :"+((LoanDataHolder)Loan.get(0)).getGivenOn() ));
                group.addCell(creatTextCellHeader(""));
              //   }
                
                
                PdfPTable headings=new PdfPTable(3);
                headings.setTotalWidth(575);
                
                headings.setLockedWidth(true);
                
                
                headings.addCell("Date");
              //  headings.addCell("InstallMent No");
                headings.addCell("Balance");
                headings.addCell("Installments value");
               // headings.addCell("Amount paid");
               // headings.addCell("Others");
                
                
                
                ArrayList<LoanDataHolder> users = ListUsers(loanid);
                
                for (int a=0;a<users.size();a++) {
                   
                    
                        
                        // String name=((LoanDataHolder)Loanusers.get(i)).getId().toString();
                        headings.addCell(((LoanDataHolder)users.get(a)).getPaidOn());
                        headings.addCell(((LoanDataHolder)users.get(a)).getLoanBalance());
                        headings.addCell(((LoanDataHolder)users.get(a)).getTodayPay());
                       // headings.addCell(((LoanDataHolder)users.get(a)).getInstallmentAmount());
                       // headings.addCell("");
                       // headings.addCell("");
                    
                    
                }
                
                
                
                
//            headings.addCell("Name");
//            headings.addCell("Product");
//            headings.addCell("Previous Balance");
//            headings.addCell("Installments value");
//            headings.addCell("Amount paid");
//            headings.addCell("Others");
           







pdfp.add(header);
pdfp.add(group);
pdfp.add(headings);
pdfp.close();




            } catch (DocumentException ex) {
                Logger.getLogger(GroupSheet.class.getName()).log(Level.SEVERE, null, ex);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(GroupSheet.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(GroupSheet.class.getName()).log(Level.SEVERE, null, ex);
            }
            
                        
            if(Desktop.isDesktopSupported()){
                try{
                    File file=new File(chooser.getSelectedFile(),loanid+""+DATE+".pdf");
                    Desktop.getDesktop().open(file);
                }
                catch(Exception ex){
                    ex.printStackTrace();
                }
            }
            
//            
//             FileInputStream psStream = null;
//        try {
//            psStream = new FileInputStream(new File(chooser.getSelectedFile(),loanid+""+DATE+".pdf"));
//            } catch (FileNotFoundException ffne) {
//              ffne.printStackTrace();
//            }
//            if (psStream == null) {
//                return rc;
//               // return;
//            }
//        DocFlavor psInFormat = DocFlavor.INPUT_STREAM.AUTOSENSE;
//        Doc myDoc = new SimpleDoc(psStream, psInFormat, null);  
//        PrintRequestAttributeSet aset = new HashPrintRequestAttributeSet();
//        PrintService[] services = PrintServiceLookup.lookupPrintServices(psInFormat, aset);
//       //   String []list={ "Cash", "Mpesa", "Cheque"};
//            JComboBox jcb=new JComboBox(services);
//            JOptionPane.showMessageDialog(null, jcb,"Select mode of printing",JOptionPane.QUESTION_MESSAGE);
//           int mode=jcb.getSelectedIndex();
//        // this step is necessary because I have several printers configured
//        PrintService myPrinter = null;
//        for (int i = 0; i < services.length; i++){
//          //  System.out.println("service found: "+svcName);
//            String svcName = services[i].toString();           
//            if (svcName.contains("printer closest to me")){
//                myPrinter = services[i];
//                System.out.println("my printer found: "+svcName);
//                break;
//            }
//        }
//         myPrinter=services[mode];
//        if (myPrinter != null) {            
//            DocPrintJob job = myPrinter.createPrintJob();
//            try {
//            job.print(myDoc, aset);
//             
//            } catch (Exception pe) {pe.printStackTrace();}
//        } else {
//            System.out.println("no printer services found");
//        }
//            
//            
//               JOptionPane.showMessageDialog(null, "Done ","Success",JOptionPane.PLAIN_MESSAGE); 
               rc=1;
        }  
        
}
    catch(Exception m){
        m.printStackTrace();
    }
    return rc;
    }

      public ArrayList<LoanDataHolder> First(String loanid )
  {
    ArrayList<LoanDataHolder> usersList = new ArrayList();
      
         String nl="null";
    try
    {
        Methods m=new Methods();
        Connection con = m.getConnection();
     // Connection con = getConnection();
      Statement st = con.createStatement();
      String xr="0";
     // String searchQuery = "SELECT * FROM `accounttypes`";
      String searchQuery = "SELECT * FROM `loans`WHERE loanid='"+loanid+"'AND `todaypay`='"+xr+" ";
      ResultSet rs = st.executeQuery(searchQuery);
      while (rs.next())
      {
        LoanDataHolder user = new LoanDataHolder(rs.getString("autoid"),rs.getString("id"), rs.getString("loanid"), rs.getString("loantype"),
                rs.getString("applicable")
                ,rs.getString("installmentamount"),rs.getString("installmentsno"),rs.getString("periodtype"),rs.getString("frequencyperperiod"),
                rs.getDate("targetdate"),
                rs.getString("loanAmount"),rs.getString("loanRequested"),rs.getString("loanbalance"),rs.getString("defaultacc"),
                rs.getString("applicationfee"),rs.getString("todaypay")
                ,rs.getString("givenOn"),rs.getString("paidon"));
        
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
    
    public ArrayList<LoanDataHolder> ListUsers(String loanid )
  {
    ArrayList<LoanDataHolder> usersList = new ArrayList();
      
         String nl="null";
    try
    {
        Methods m=new Methods();
        Connection con = m.getConnection();
     // Connection con = getConnection();
      Statement st = con.createStatement();
     // String searchQuery = "SELECT * FROM `accounttypes`";
      String searchQuery = "SELECT * FROM `loans`WHERE loanid='"+loanid+"' ";
      ResultSet rs = st.executeQuery(searchQuery);
      while (rs.next())
      {
        LoanDataHolder user = new LoanDataHolder(rs.getString("autoid"),rs.getString("id"), rs.getString("loanid"), rs.getString("loantype"),
                rs.getString("applicable")
                ,rs.getString("installmentamount"),rs.getString("installmentsno"),rs.getString("periodtype"),rs.getString("frequencyperperiod"),
                rs.getDate("targetdate"),
                rs.getString("loanAmount"),rs.getString("loanRequested"),rs.getString("loanbalance"),rs.getString("defaultacc"),
                rs.getString("applicationfee"),rs.getString("todaypay")
                ,rs.getString("givenOn"),rs.getString("paidon"));
        
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
     public PdfPCell createTextCell(String text){
     
     PdfPCell cell=new PdfPCell();
     Paragraph p=new Paragraph();
     p.setFont(FontFactory.getFont(FontFactory.TIMES_BOLD,16,java.awt.Font.BOLD));
   //  p.setFont(Font.BOLD);
     p.setAlignment(Element.ALIGN_CENTER);
     p.add(text);
     cell.addElement(p);
     cell.setBorder(Rectangle.NO_BORDER);
     cell.setVerticalAlignment(Rectangle.NO_BORDER);
    
    return cell;
     
 }
     public PdfPCell createImageCell(String path) throws IOException{
        PdfPCell cell=null;
        try {
            Image img=Image.getInstance(path);
            cell=new PdfPCell(img,true);
            cell.setFixedHeight(60);
              cell.setBorder(Rectangle.NO_BORDER);
            
        } catch (BadElementException ex) {
            Logger.getLogger(GroupSheet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(GroupSheet.class.getName()).log(Level.SEVERE, null, ex);
        }
    
   

    return cell;
}
     public PdfPCell creatTextCellHeader(String text){
        PdfPCell cell = new PdfPCell();
        Paragraph p = new Paragraph();
        p.setFont(FontFactory.getFont(FontFactory.TIMES_BOLD, 12, Font.BOLD));
        p.add(text);
        cell.addElement(p);
        cell.setBorder(Rectangle.NO_BORDER);
        return cell;
}
}
