/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imua.development.Accounts;

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
import imua.development.LoanDataHolder;
import imua.development.Methods;
import imua.development.Reports.GroupSheet;
import imua.development.UserDataHolder;
import java.awt.Color;
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
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author kimani kogi
 */
public class GroupView extends javax.swing.JFrame {
String toCombo[];
    String toAccountsTypes[];
    private String group = null;
    private String accounttype = null;
  String accounts[];
  String groups[];
  Methods n=new Methods();
    /**
     * Creates new form GroupView
     */
    public GroupView() {
        initComponents();
         setTilteImage();
        findGroups();
         
    }
      private void setTilteImage(){
        try {
           
            String t= n.setTitle();
            this.setTitle(t);
            String i=n.setIconImage();
            this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource(i)));
            
            String col=n.selectcolor();
            Color c=new Color(Integer.parseInt(col));
            jPanel1.setBackground(c);
            
        } catch (Exception ex) {
            Logger.getLogger(GroupSheet.class.getName()).log(Level.SEVERE, null, ex);
        }
}
     public void findGroups()
  {
  
    ArrayList<String> usersList =  n.ListUsersGroups();
   groups=new String[usersList.size()];
   usersList.toArray(groups);
   groupnames();
   //JOptionPane.showMessageDialog(null, groups[0]);
  }
     private void groupnames() {
       // String namesdb[] = {"eric", "kogi"};
        toCombo = groups;
        setModel();

    }

    private void setModel() {
        jComboBoxGroup.removeAllItems();
        jComboBoxGroup.addItem("choose");
        for (int a = 0; a < toCombo.length; a++) {

            jComboBoxGroup.addItem(toCombo[a]);
        }
    }
public ArrayList<UserDataHolder> ListUsers(String id )
  {
    ArrayList<UserDataHolder> usersList = new ArrayList();
      
         String nl="null";
    try
    {
        Methods m=new Methods();
        Connection con = m.getConnection();
     // Connection con = getConnection();
      Statement st = con.createStatement();
     // String searchQuery = "SELECT * FROM `accounttypes`";
      String searchQuery = "SELECT * FROM `users`WHERE `group`='"+id+"'";
      ResultSet rs = st.executeQuery(searchQuery);
      while (rs.next())
      {
        UserDataHolder user = new UserDataHolder(rs.getString("id"),rs.getString("id"), rs.getString("fname"), rs.getString("sname"),
                rs.getString("email")
                ,rs.getString("phone"),rs.getString("address"),rs.getString("county"),rs.getString("town"),
                rs.getString("occupation"),
                rs.getString("maritalstatus"),rs.getString("acounttype"),rs.getString("group"),rs.getString("imgurl")
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
  private void refresh(String id){
       
        DefaultTableModel model = (DefaultTableModel)this.table.getModel();
        
        model.setRowCount(0);
        
        findUsers(id);
  }
  public void findUsers(String group) 
  {
    ArrayList<UserDataHolder> users = ListUsers(group);
    DefaultTableModel model = new DefaultTableModel();
  //   String ID, Date ate, String Installmentamount, String LoanAmount,String autoid,String instno
    model.setColumnIdentifiers(new Object[] { "ID", "FNAME","SNAME","EMAIL","PHONE","ADDRESS","COUNTY","TOWN","OCC","MARRITAL","ACC" });
    Object[] row = new Object[11];
    for (int i = 0; i < users.size(); i++)
    {
        row[0] = ((UserDataHolder) users.get(i)).getId();
        row[1] = ((UserDataHolder) users.get(i)).getFname();
        row[2] = ((UserDataHolder) users.get(i)).getLname();
        row[3] = ((UserDataHolder) users.get(i)).getEmail();
        row[4] = ((UserDataHolder) users.get(i)).getPhone();
        row[5] = ((UserDataHolder) users.get(i)).getAddress();
        row[6] = ((UserDataHolder) users.get(i)).getCounty();
        row[7] = ((UserDataHolder) users.get(i)).getTown();
        row[8] = ((UserDataHolder) users.get(i)).getOccupation();
        row[9] = ((UserDataHolder) users.get(i)).getMarital();
        row[10] = ((UserDataHolder)users.get(i)).getAccount();
     //   row[11] = ((UserDataHolder)users.get(i)).getGroup();
       // row[12] = ((UserDataHolder)users.get(i)).getImage();
      
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

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        jComboBoxGroup = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();

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

        jComboBoxGroup.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBoxGroupItemStateChanged(evt);
            }
        });

        jButton1.setText("Print");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 959, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jComboBoxGroup, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42)
                .addComponent(jButton1)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBoxGroup, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 475, Short.MAX_VALUE))
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

    private void jComboBoxGroupItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBoxGroupItemStateChanged
      if(!"choose".equals(jComboBoxGroup.getSelectedItem().toString())){
          refresh(jComboBoxGroup.getSelectedItem().toString());
      }
    }//GEN-LAST:event_jComboBoxGroupItemStateChanged

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
      
         if(!"choose".equals(jComboBoxGroup.getSelectedItem().toString())||
                 table.getRowCount()!=0||
              table.getColumnCount()!=0)
         {
         String der[]=getNameImage();
       imgurl=der[0];
       contact=der[1];
       OrgDetails=contact;
       print(OrgDetails,imgurl);
      }
         else{
             JOptionPane.showMessageDialog(null,"Choose Group","ERROR",JOptionPane.INFORMATION_MESSAGE);
         }
         
    }//GEN-LAST:event_jButton1ActionPerformed
String groupname;
String OrgDetails;
String imgurl;
String date=new Date().toString();
String contact="www.inuadevelopment.co.ke\n inua@inuadevelopment.co.ke\n 0714406984\n"+date;

public String[] getNameImage(){
   String contactx=null;
   String imgurlx=null;
    try {
        Methods m=new Methods();
        Connection con = m.getConnection();
        Statement st2 = con.createStatement();
        
        ResultSet res7 = st2.executeQuery("SELECT imgurl,name,address,email,website,phone FROM prefrences  WHERE id=1");
        if (res7.next()) {
         imgurlx = res7.getString("imgurl");
        contactx = res7.getString("name")+"\n"+res7.getString("address")+"\n"+res7.getString("website")+
        "\n"+res7.getString("email")+"\n"+res7.getString("phone");
            
        } else {
            JOptionPane.showMessageDialog(null, "error loading image \n  make sure image is in images folder ");
        }
        st2.close();
        res7.close();   
        con.close();
    } catch (SQLException ex) {
        Logger.getLogger(GroupSheet.class.getName()).log(Level.SEVERE, null, ex);
    }
  return new String[] {imgurlx,contactx};
}
    
    public void print (String groupName,String imgurl){
    
    Calendar  c= Calendar.getInstance();
       Date today=c.getTime();
         
         java.util.Date d=(today);
  //Days d=Days.daysBeetween();
         java.sql.Date DATE=new java.sql.Date(d.getTime());
    
   // FileInputStream fis = null;
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
                PdfWriter.getInstance(pdfp, new FileOutputStream(new File(chooser.getSelectedFile(),"Group "+jComboBoxGroup.getSelectedItem().toString()+"Members"+DATE+".pdf")));
                pdfp.open();
                PdfPTable header=new PdfPTable(2);
                //  tbl.setWidthPercentage(100);
                
                header.setTotalWidth(575);
                header.setLockedWidth(true);
                
                header.setWidths(new int[]{1,4});
                if(imgurl.equals("image")){
                    header.addCell("no image ");
                    
                    String img=imgurl;
                }
                else{
                    header.addCell(createImageCell(imgurl));
                    
                }
                
                header.addCell(createTextCell(OrgDetails));
                
                PdfPTable group=new PdfPTable(1);
                // headings.s
                group.addCell(creatTextCellHeader("Group : "+jComboBoxGroup.getSelectedItem().toString()));
                
                
                PdfPTable headings=new PdfPTable(6);
                headings.setTotalWidth(575);
                
                headings.setLockedWidth(true);

                
                headings.addCell(creatTextCellH("Id"));
                headings.addCell(creatTextCellH("Name"));
                headings.addCell(creatTextCellH("Name"));
                headings.addCell(creatTextCellH("Email"));
                headings.addCell(creatTextCellH("Phone"));
                headings.addCell(creatTextCellH("Account"));
              //  headings.addCell("Others");
                
                
                
                ArrayList<UserDataHolder> users = ListUsers(jComboBoxGroup.getSelectedItem().toString());
                
               
                  // ArrayList<UserDataHolder> users = ListUsers(group);
                    for(int i=0;i<users.size();i++){
  
                        // String name=((LoanDataHolder)Loanusers.get(i)).getId().toString();
                        headings.addCell(((UserDataHolder) users.get(i)).getId());
                        headings.addCell(((UserDataHolder) users.get(i)).getFname());
                        headings.addCell(((UserDataHolder) users.get(i)).getLname());
                        headings.addCell(((UserDataHolder) users.get(i)).getEmail());
                        headings.addCell(((UserDataHolder) users.get(i)).getPhone());
                        headings.addCell(((UserDataHolder)users.get(i)).getAccount());
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
            
            
            FileInputStream psStream = null;
        try {
            psStream = new FileInputStream(new File(chooser.getSelectedFile(),"Group "+jComboBoxGroup.getSelectedItem().toString()+"Members"+DATE+".pdf"));
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
    catch(Exception m){
        m.printStackTrace();
    }
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
      public PdfPCell creatTextCellH(String text){
        PdfPCell cell = new PdfPCell();
        Paragraph p = new Paragraph();
        p.setFont(FontFactory.getFont(FontFactory.TIMES_BOLD, 12, Font.BOLD));
        p.add(text);
        cell.addElement(p);
       // cell.setBorder(Rectangle.NO_BORDER);
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
            java.util.logging.Logger.getLogger(GroupView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GroupView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GroupView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GroupView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GroupView().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox<String> jComboBoxGroup;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable table;
    // End of variables declaration//GEN-END:variables
}
