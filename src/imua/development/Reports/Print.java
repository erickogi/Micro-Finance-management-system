/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imua.development.Reports;

import IDataHolders.ILoanDataHolder;
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
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.sql.Connection;
import java.sql.ResultSet;
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
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author kimani kogi
 */
public class Print {
    Methods m=new Methods();
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
        if(chooser.showOpenDialog(null)==JFileChooser.APPROVE_OPTION){
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
                 ArrayList<ILoanDataHolder> Loan = ListUsers(loanid);
                // for(int f=0;f<Loan.size();f++){
                int ap=Integer.valueOf(((ILoanDataHolder)Loan.get(0)).getInstallmentsno())+1;
                group.addCell(creatTextCellHeader("Name        :"+name));
                group.addCell(creatTextCellHeader("ID          :"+pid));
                group.addCell(creatTextCellHeader("Amount     :"+((ILoanDataHolder)Loan.get(0)).getLoanAmount()));
                group.addCell(creatTextCellHeader("Type       :"+loantype));
                group.addCell(creatTextCellHeader("Installment:"+String.valueOf(ap)));
                group.addCell(creatTextCellHeader("Value      :"+((ILoanDataHolder)Loan.get(0)).getInstallmentamount()));
                group.addCell(creatTextCellHeader("Given On   :"+((ILoanDataHolder)Loan.get(0)).getLoangivenOn() ));
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
                
                
                
                ArrayList<ILoanDataHolder> users = ListUsers(loanid);
                
                for (int a=0;a<users.size();a++) {
                   
                    
                        
                        // String name=((LoanDataHolder)Loanusers.get(i)).getId().toString();
                        headings.addCell(((ILoanDataHolder)users.get(a)).getPaidon());
                        headings.addCell(((ILoanDataHolder)users.get(a)).getLoanbalance());
                        headings.addCell(((ILoanDataHolder)users.get(a)).getTodaypay());
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
            
            
            
            
             FileInputStream psStream = null;
        try {
            psStream = new FileInputStream(new File(chooser.getSelectedFile(),loanid+""+DATE+".pdf"));
            } catch (FileNotFoundException ffne) {
              ffne.printStackTrace();
            }
            if (psStream == null) {
                return rc;
               // return;
            }
        DocFlavor psInFormat = DocFlavor.INPUT_STREAM.AUTOSENSE;
        Doc myDoc = new SimpleDoc(psStream, psInFormat, null);  
        PrintRequestAttributeSet aset = new HashPrintRequestAttributeSet();
        PrintService[] services = PrintServiceLookup.lookupPrintServices(psInFormat, aset);
       //   String []list={ "Cash", "Mpesa", "Cheque"};
            JComboBox jcb=new JComboBox(services);
            JOptionPane.showMessageDialog(null, jcb,"Select mode of printing",JOptionPane.QUESTION_MESSAGE);
           int mode=jcb.getSelectedIndex();
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
         myPrinter=services[mode];
        if (myPrinter != null) {            
            DocPrintJob job = myPrinter.createPrintJob();
            try {
            job.print(myDoc, aset);
             
            } catch (Exception pe) {pe.printStackTrace();}
        } else {
            System.out.println("no printer services found");
        }
            
            
               JOptionPane.showMessageDialog(null, "Done ","Success",JOptionPane.PLAIN_MESSAGE); 
               rc=1;
        }  
        
}
    catch(Exception m){
        m.printStackTrace();
    }
    return rc;
    }

      public ArrayList<ILoanDataHolder> First(String loanid )
  {
    ArrayList<ILoanDataHolder> usersList = new ArrayList();
      
         String nl="null";
    try
    {
        Methods m=new Methods();
        Connection con = m.getConnection();
     // Connection con = getConnection();
      Statement st = con.createStatement();
      String xr="first";
     // String searchQuery = "SELECT * FROM `accounttypes`";
      String searchQuery = "SELECT * FROM `iloans`WHERE loanid='"+loanid+"'AND `extra`='"+xr+" ";
      ResultSet rs = st.executeQuery(searchQuery);
      while (rs.next())
      {
            ILoanDataHolder data = new ILoanDataHolder(
              rs.getString("autoid")
              ,rs.getString("customerid"),
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
    
    public ArrayList<ILoanDataHolder> ListUsers(String loanid )
  {
    ArrayList<ILoanDataHolder> usersList = new ArrayList();
      
         String nl="null";
    try
    {
        Methods m=new Methods();
        Connection con = m.getConnection();
     // Connection con = getConnection();
      Statement st = con.createStatement();
     // String searchQuery = "SELECT * FROM `accounttypes`";
      String searchQuery = "SELECT * FROM `iloans`WHERE loanid='"+loanid+"' ";
      ResultSet rs = st.executeQuery(searchQuery);
      while (rs.next())
      {
           ILoanDataHolder data = new ILoanDataHolder(
              rs.getString("autoid")
              ,rs.getString("customerid"),
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
