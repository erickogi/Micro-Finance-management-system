/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imua.development.Accounts;

import imua.development.Reports.GroupSheet;
import imua.development.*;
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
import java.awt.Color;
import java.awt.Container;
import java.awt.Toolkit;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Kamau
 */
public class Personalreport extends javax.swing.JFrame {
Methods m=new Methods();
    /**
     * Creates new form Personalreport
     */
    public Personalreport() {
        initComponents();
        setTilteImage();
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
        } catch (Exception ex) {
            Logger.getLogger(Accgroups.class.getName()).log(Level.SEVERE, null, ex);
        }
}
 public ArrayList<Personalreports> ListUsers( String Id)
  {
    ArrayList<Personalreports> usersList = new ArrayList();
    
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
     // String searchQuery = "SELECT * FROM `accounttypes`";
      String searchQuery = "SELECT * FROM `transactions` WHERE `id` = '" + Id + "'AND `updated_at`>= '"+FDATE+"' AND `updated_at`<= '"+TDATE+"'";
      ResultSet rs = st.executeQuery(searchQuery);
      while (rs.next())
      {
        Personalreports user = new Personalreports(rs.getString("updated_at"), rs.getString("modeofpayment"),rs.getString("deposited")
                ,rs.getString("withdrawn")
                ,rs.getString("balance"),rs.getString("autoid"));
        
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
  
  public void findUsers()
  {
    ArrayList<Personalreports> users = ListUsers(txtID.getText());
    DefaultTableModel model = new DefaultTableModel();
    
    model.setColumnIdentifiers(new Object[] { "DATE", "MODE","+","-","BALANCE"});
    Object[] row = new Object[5];
    for (int i = 0; i < users.size(); i++)
    {
      row[0] = ((Personalreports)users.get(i)).getDate();
      row[1] = ((Personalreports)users.get(i)).getType();
      
     String h= ((Personalreports) users.get(i)).getDeposit();
     String g=((Personalreports) users.get(i)).getWithdrawal();
      row[4] = ((Personalreports) users.get(i)).getBalance();
     if(h.contentEquals("null")){
          row[2] ="-";
          row[3] = g;
     }else{
       row[3] = "-";
        row[2] =h;
     }
// ((Personalreports) users.get(i)).getDeposit();
        
//((Personalreports) users.get(i)).getWithdrawal();
       
       
       
      
     
      
      model.addRow(row);
    }
    this.table.setModel(model);
  }
    private void refresh(){
       
        DefaultTableModel model = (DefaultTableModel)this.table.getModel();
        
        model.setRowCount(0);
        
        findUsers();
  }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel20 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtID = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();
        jDateChooserFrom = new com.toedter.calendar.JDateChooser();
        jDateChooserTo = new com.toedter.calendar.JDateChooser();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel20.setFont(new java.awt.Font("Rockwell Extra Bold", 0, 18)); // NOI18N
        jLabel20.setText("Personal Report");

        jLabel3.setText("ID No.");

        txtID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIDActionPerformed(evt);
            }
        });

        jButton1.setText("Confirm");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
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
        table.setToolTipText("");
        table.setRowHeight(40);
        table.setRowMargin(4);
        jScrollPane1.setViewportView(table);

        jButton2.setText("Print");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel1.setText("FROM");

        jLabel4.setText("TO");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jDateChooserFrom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jDateChooserTo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(43, 43, 43)
                        .addComponent(jButton1)
                        .addGap(48, 48, 48)
                        .addComponent(jButton2))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(255, 255, 255)
                        .addComponent(jLabel20))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addComponent(jLabel3)
                        .addGap(153, 153, 153)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4)))
                .addContainerGap(128, Short.MAX_VALUE))
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 769, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel20)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton2)
                            .addComponent(jButton1)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jDateChooserFrom, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jDateChooserTo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1))
            .addGroup(layout.createSequentialGroup()
                .addGap(111, 111, 111)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(422, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
if(  txtID.getText().isEmpty()||((JTextField)jDateChooserFrom.getDateEditor().getUiComponent()).getText().isEmpty()||
        ((JTextField)jDateChooserTo.getDateEditor().getUiComponent()).getText().isEmpty()){
 
}
else{
   refresh()   ;   
}
         
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
  if(txtID.getText().isEmpty()||
              table.getRowCount()==0||
              table.getColumnCount()==0
              
               
              
              ){
     JOptionPane.showMessageDialog(null,"NO DATA","ERROR",JOptionPane.INFORMATION_MESSAGE);
  }
  else{
     printD();  
  }
      
               
    }//GEN-LAST:event_jButton2ActionPerformed

    private void txtIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIDActionPerformed
       refresh()   ;   
    }//GEN-LAST:event_txtIDActionPerformed
String name;
String imgurl;
    public void getNameImage(String id){
//        try {
//            Methods m=new Methods();
//            Connection con = m.getConnection();
//            // Connection con = getConnection();
//            Statement st = con.createStatement();
//            // String searchQuery = "SELECT * FROM `accounttypes`";
//            String searchQuery = "SELECT fname,imgurl FROM `users` WHERE `id`="+id+" ";
//            ResultSet rs = st.executeQuery(searchQuery);
//            if(rs.next()){
//             name=rs.getString("fname");
//             imgurl=rs.getString("imgurl");
//            }
//            
//            
//        } catch (SQLException ex) {
//            Logger.getLogger(Personalreport.class.getName()).log(Level.SEVERE, null, ex);
//        }
    
    
    
}
    String orimgurl;
    String orcontact;
    public void printD(){
        Calendar  c= Calendar.getInstance();
       Date today=c.getTime();
         
         java.util.Date d=(today);
  //Days d=Days.daysBeetween();
         java.sql.Date DATE=new java.sql.Date(d.getTime());
         //get name and img of user
    String[] ni=m.getNameImage(txtID.getText());
     name=ni[0];
    imgurl=ni[1];
    
    
    GroupSheet pr=new GroupSheet();
     String der[]=pr.getNameImage();
       orimgurl=der[0];
       orcontact=der[1];
     JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(new java.io.File(","));
        chooser.setDialogTitle("Save at");
        chooser.setApproveButtonText("save");
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        if(chooser.showOpenDialog(this)==JFileChooser.APPROVE_OPTION){
            try {
                Document pdfp=new Document();
                PdfWriter.getInstance(pdfp, new FileOutputStream(new File(chooser.getSelectedFile(),txtID.getText()+" TransactionReport"+DATE+".pdf")));
                pdfp.open();
                PdfPTable header=new PdfPTable(3);
                //  tbl.setWidthPercentage(100);
                
                header.setTotalWidth(575);
                header.setLockedWidth(true);
                
                header.setWidths(new int[]{1,4,1});
                if(orimgurl.equals("image")){
                    header.addCell("no image ");
                    
                    String img=orimgurl;
                }
                else{
                    header.addCell(createImageCell(orimgurl));
                    
                }
                
                header.addCell(createTextCell(orcontact));
                
                   if(imgurl.equals("image")){
                    header.addCell("no image ");
                    
                    String img=imgurl;
                }
                else{
                    header.addCell(createImageCell(imgurl));
                    
                }
                
                
                pdfp.add(header);
              double[] res=  fetchBalance("id",txtID.getText());
     Date fr= jDateChooserFrom.getDate();
    Date to= jDateChooserTo.getDate();
    java.util.Date frm=(fr);
     java.util.Date too=(to);
     java.sql.Date FDATE=new java.sql.Date(frm.getTime());
     java.sql.Date TDATE=new java.sql.Date(too.getTime());
                PdfPTable group=new PdfPTable(1);
                group.addCell(creatTextCellHeader("Name        :"+name));
                group.addCell(creatTextCellHeader("Deposits    :"+String.valueOf(res[0])));
                group.addCell(creatTextCellHeader("Withdraws   :"+String.valueOf(res[1])));
                group.addCell(creatTextCellHeader("For Period  :"+FDATE+ "  To  "+TDATE));
                group.addCell(creatTextCellHeader("Current Balance :"+fetchBalance(txtID.getText())));
                
                pdfp.add(group);
                PdfPTable headings=new PdfPTable(6);
                headings.setTotalWidth(575);
               
                headings.setLockedWidth(true);
                
                
                headings.addCell("tr-no");
                headings.addCell("Mode");
                headings.addCell("Withdrawn");
                headings.addCell("Deposited");
                headings.addCell("Balance");
                headings.addCell("Date");
               
                
                   ArrayList<Personalreports> users = ListUsers(txtID.getText());
                   
                   for (int i=0;i<users.size();i++){
                headings.addCell(createTextCellcolor(((Personalreports) users.get(i)).getID(),i));
                headings.addCell(createTextCellcolor(((Personalreports)users.get(i)).getType(),i));
                headings.addCell(createTextCellcolor(((Personalreports) users.get(i)).getWithdrawal(),i));
                headings.addCell(createTextCellcolor(((Personalreports)users.get(i)).getDeposit(),i));
                headings.addCell( createTextCellcolor(((Personalreports) users.get(i)).getBalance(),i));
                headings.addCell(createTextCellcolor(((Personalreports)users.get(i)).getDate(),i));
                   }
                   pdfp.add(headings);

                 pdfp.close();
                
                
                
                
             
                
                
                
                
                
      } catch (DocumentException ex) {
                Logger.getLogger(GroupSheet.class.getName()).log(Level.SEVERE, null, ex);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(GroupSheet.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(GroupSheet.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            
             FileInputStream psStream = null;
        try {
            psStream = new FileInputStream(new File(chooser.getSelectedFile(),txtID.getText()+" TransactionReport"+DATE+".pdf"));
            } catch (FileNotFoundException ffne) {
              ffne.printStackTrace();
            }
            if (psStream == null) {
                return;
            }
        DocFlavor psInFormat = DocFlavor.INPUT_STREAM.AUTOSENSE;
        Doc myDoc = new SimpleDoc(psStream, psInFormat, null);  
        PrintRequestAttributeSet aset = new HashPrintRequestAttributeSet();
        PrintService[] services = PrintServiceLookup.lookupPrintServices(psInFormat, aset);
         
        // this step is necessary because I have several printers configured
        PrintService myPrinter = null;
        for (int i = 0; i < services.length; i++){
          //  System.out.println("service found: "+svcName);
            String svcName = services[i].toString();           
            if (svcName.contains("printer closest to me")){
                myPrinter = services[i];
                System.out.println("my printer found: "+svcName);
                break;
            }
        }
         
        if (myPrinter != null) {            
            DocPrintJob job = myPrinter.createPrintJob();
            try {
            job.print(myDoc, aset);
             
            } catch (Exception pe) {pe.printStackTrace();}
        } else {
            System.out.println("no printer services found");
        }

        JOptionPane.showMessageDialog(null, "Done ","Success",JOptionPane.PLAIN_MESSAGE);       
}
                
    
        
        
    }
    
      public PdfPCell createTextCellcolor(String text ,int c){
     PdfPCell cell=new PdfPCell();
          if(c%2==0){
        
     Paragraph p=new Paragraph();
    cell.setBackgroundColor(Color.CYAN);
     p.add(text);
     cell.addElement(p);
     }
          else{
            Paragraph p=new Paragraph();
    //cell.setBackgroundColor(Color.CYAN);
     p.add(text);
     cell.addElement(p);  
          }
     
   
    
    return cell;
     
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
            java.util.logging.Logger.getLogger(Personalreport.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Personalreport.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Personalreport.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Personalreport.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Personalreport().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private com.toedter.calendar.JDateChooser jDateChooserFrom;
    private com.toedter.calendar.JDateChooser jDateChooserTo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable table;
    private javax.swing.JTextField txtID;
    // End of variables declaration//GEN-END:variables
    private double[] fetchBalance(String where, String value) {
        String balance = "0";
        double a=0;
        double b=0;
        
        try {
             Date fr= jDateChooserFrom.getDate();
    Date to= jDateChooserTo.getDate();
    java.util.Date frm=(fr);
     java.util.Date too=(to);
     java.sql.Date FDATE=new java.sql.Date(frm.getTime());
     java.sql.Date TDATE=new java.sql.Date(too.getTime());
            
            
            
           
            Connection con = m.getConnection();
            Statement st = con.createStatement();
            Statement st1 = con.createStatement();
            String withi = "withdrawal";
            String depo = "deposits";
//fetches total deposits
            String searchQuery = "SELECT SUM(amount) FROM `transactions` WHERE `" + where + "` ="
                    + " '" + value + "'AND `typeoftransaction`='" + depo + "'AND `updated_at`>= '"+FDATE+"' AND `updated_at`<= '"+TDATE+"'";
//fetch total withdrawals
            String searchQuery2 = "SELECT SUM(amount) FROM `transactions` WHERE `" + where + "` ="
                    + " '" + value + "'AND `typeoftransaction`='" + withi + "'AND `updated_at`>= '"+FDATE+"' AND `updated_at`<= '"+TDATE+"'";

            ResultSet rs = st.executeQuery(searchQuery);

            ResultSet rs1 = st1.executeQuery(searchQuery2);

            if (rs.next()&& rs1.next())
            {
 
                 a = 0;
                 b = 0;

                try 
                {
//subtracts withdrawals from total deposits to get current account balance
                    if(rs1.getString(1)==null&&rs.getString(1)==null){
                        //  JOptionPane.showMessageDialog(null, "Has no deposit");
                    }
                    else{
                    a = Double.valueOf(rs1.getString(1));
                    b = Double.valueOf(rs.getString(1));
                    //balance = String.valueOf(b - a);
                    }

                } 
                catch (Exception n) 
                {

                   // balance = rs.getString(1);

                }
            } 
            else if (rs.next()) 
            {
         //JOptionPane.showMessageDialog(null,"rs ");
             //   balance = rs.getString(1);
            } 
            else 
            {
            //    JOptionPane.showMessageDialog(null,"no");
//if no deposits found in rs query excution
              //  JOptionPane.showMessageDialog(null, "Has no deposit");
            }
            st.close();
            st1.close();
            rs.close();
            rs1.close();
            con.close();

        } catch (SQLException ex) {

           // JOptionPane.showMessageDialog(null, ex);
            Logger.getLogger(Personalreport.class.getName()).log(Level.SEVERE, null, ex);
        }

        return new double[]{b,a};
    }

    private String fetchBalance( String value) {
        String balance = "0";
        double a=0;
        double b=0;
        
        try {
             Date fr= jDateChooserFrom.getDate();
    Date to= jDateChooserTo.getDate();
    java.util.Date frm=(fr);
     java.util.Date too=(to);
     java.sql.Date FDATE=new java.sql.Date(frm.getTime());
     java.sql.Date TDATE=new java.sql.Date(too.getTime());
            
            
            
           
            Connection con = m.getConnection();
            Statement st = con.createStatement();
            Statement st1 = con.createStatement();
            String withi = "withdrawal";
            String depo = "deposits";
//fetches total deposits
            String searchQuery = "SELECT SUM(amount) FROM `transactions` WHERE `id` ="
                    + " '" + value + "'AND `typeoftransaction`='" + depo + "'";
//fetch total withdrawals
            String searchQuery2 = "SELECT SUM(amount) FROM `transactions` WHERE `id` ="
                    + " '" + value + "'AND `typeoftransaction`='" + withi + "'";

            ResultSet rs = st.executeQuery(searchQuery);

            ResultSet rs1 = st1.executeQuery(searchQuery2);

            if (rs.next()&& rs1.next())
            {
 
                 a = 0;
                 b = 0;

                try 
                {
//subtracts withdrawals from total deposits to get current account balance
                    if(rs1.getString(1)==null&&rs.getString(1)==null){
                        //  JOptionPane.showMessageDialog(null, "Has no deposit");
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
         //JOptionPane.showMessageDialog(null,"rs ");
              balance = rs.getString(1);
            } 
            else 
            {
            //    JOptionPane.showMessageDialog(null,"no");
//if no deposits found in rs query excution
              //  JOptionPane.showMessageDialog(null, "Has no deposit");
            }
            st.close();
            st1.close();
            rs.close();
            rs1.close();
            con.close();

        } catch (SQLException ex) {

           // JOptionPane.showMessageDialog(null, ex);
            Logger.getLogger(Personalreport.class.getName()).log(Level.SEVERE, null, ex);
        }

        return balance;
    }




}
