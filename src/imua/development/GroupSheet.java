/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imua.development;

import java.awt.Color;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import org.apache.commons.lang3.ArrayUtils;

/**
 *
 * @author kimani kogi
 */
public class GroupSheet extends javax.swing.JFrame {
String toCombo[];
    String toAccountsTypes[];
    private String group = null;
    private String accounttype = null;
  String accounts[];
  String groups[];
   Methods m= new Methods();
    /**
     * Creates new form GroupSheet
     */
    public GroupSheet() {
        initComponents();
        setTilteImage();
        findGroups();
        findLoans();
        JOptionPane.showMessageDialog(null, "NOT YET COMPLETE","GROUP COLLECTION SHEET",JOptionPane.INFORMATION_MESSAGE);
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
            jPanel1.setBackground(c);
            
        } catch (Exception ex) {
            Logger.getLogger(Accgroups.class.getName()).log(Level.SEVERE, null, ex);
        }
}
     public void findGroups()
  {
  
    ArrayList<String> usersList =  m.ListUsersGroups();
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
     public void findLoans()
  {
      Methods m=new Methods();
    ArrayList<String> usersList =  m.ListLoanTypes();
   accounts=new String[usersList.size()];
   usersList.toArray(accounts);
  }
//public void findUsers()
//  {
// // String []  Subjects=findSubjectid();
//  String []  Loans=accounts;
//  int count=Loans.length;
// // int subjectCount=Subjects.length;
//  //JOptionPane.showMessageDialog(null, subjectCount);
//      
//    ArrayList<LoanDataHolder> users = ListUsers(jComboBoxGroup.getSelectedItem());
//    DefaultTableModel model = new DefaultTableModel();
//    
//   
////    String [] subjectsIdentifiers={"Maths","Eng","Kisw","Phy","Chem","Bio","Hist","Geo","Cre","Hre","Agri","Home Scie","Arts","Comp",
////                                      "B/c","Wood","Metal","Music","French","German","Arabic","Business"};
////    for(int y=0;y<Subjects.length;y++){
////        
////    }
////    String []toCol;
//    String col[]={"ID","NAMES","SAVINGS"};
//    
//   // String [] subjectsIdentifiers={}
//    String [] both=(String[])ArrayUtils.addAll(col,Loans);
//    
//    
//    model.setColumnIdentifiers(both);
//    Object[] row = new Object[count+4];
//    for (int i = 0; i < users.size(); i++)
//    {
//       
//      row[0] = ((ExamDbDataHolder)users.get(i)).getSid();
//      
//      row[1] = ((ExamDbDataHolder)users.get(i)).getUnique();
//      
//       int c=2;
//      for(int s=0;s<Subjects.length;s++){
//          
//          if(Subjects[s].equals("s1")){
//             row[c] = ((ExamDbDataHolder)users.get(i)).getMathematics();
//             c++;
//          }
//          else if(Subjects[s].equals("s2")){
//               row[c] = ((ExamDbDataHolder)users.get(i)).getEnglish();
//               c++;
//          } 
//          else if(Subjects[s].equals("s3")){
//               row[c] = ((ExamDbDataHolder)users.get(i)).getKiswahili();
//               c++;
//          }
//          else if(Subjects[s].equals("s4")){
//               row[c] = ((ExamDbDataHolder)users.get(i)).getPhysics();
//               c++;
//          }
//          else if(Subjects[s].equals("s5")){
//               row[c] = ((ExamDbDataHolder)users.get(i)).getChemistry();
//               c++;
//          }
//          else if(Subjects[s].equals("s6")){
//               row[c] = ((ExamDbDataHolder)users.get(i)).getBiology();
//               c++;
//          }
//          else if(Subjects[s].equals("s7")){
//               row[c] = ((ExamDbDataHolder)users.get(i)).getHistory();
//               c++;
//          }
//          else if(Subjects[s].equals("s8")){
//               row[c] = ((ExamDbDataHolder)users.get(i)).getGeography();
//               c++;
//          }
//          else if(Subjects[s].equals("s9")){
//               row[c] = ((ExamDbDataHolder)users.get(i)).getCre();
//               c++;
//          }
//          else if(Subjects[s].equals("s10")){
//               row[c] = ((ExamDbDataHolder)users.get(i)).getIre();
//               c++;
//          }
//          else if(Subjects[s].equals("s11")){
//               row[c] = ((ExamDbDataHolder)users.get(i)).getHre();
//               c++;
//          }
//          else if(Subjects[s].equals("s12")){
//               row[c] = ((ExamDbDataHolder)users.get(i)).getAgriculture();
//               c++;
//          }
//          else if(Subjects[s].equals("s13")){
//               row[c] = ((ExamDbDataHolder)users.get(i)).getHomescience();
//               c++;
//          }
//          else if(Subjects[s].equals("s14")){
//               row[c] = ((ExamDbDataHolder)users.get(i)).getArtdesign();
//               c++;
//          }
//          else if(Subjects[s].equals("s15")){
//               row[c] = ((ExamDbDataHolder)users.get(i)).getComputer();
//               c++;
//               
//          }
//          else if(Subjects[s].equals("s16")){
//               row[c] = ((ExamDbDataHolder)users.get(i)).getBuilding();
//               c++;
//          }
//          else if(Subjects[s].equals("s17")){
//               row[c] = ((ExamDbDataHolder)users.get(i)).getWoodwork();
//               c++;
//          }
//          else if(Subjects[s].equals("s18")){
//               row[c] = ((ExamDbDataHolder)users.get(i)).getMetalwork();
//               c++;
//          }
//          else if(Subjects[s].equals("s19")){
//               row[c] = ((ExamDbDataHolder)users.get(i)).getMusic();
//               c++;
//          }
//          else if(Subjects[s].equals("s20")){
//               row[c] = ((ExamDbDataHolder)users.get(i)).getFrench();
//               c++;
//          }
//          else if(Subjects[s].equals("s21")){
//               row[c] = ((ExamDbDataHolder)users.get(i)).getGerman();
//               c++;
//          }
//          else if(Subjects[s].equals("s22")){
//               row[c] = ((ExamDbDataHolder)users.get(i)).getArabic();
//               c++;
//          }
//          else if(Subjects[s].equals("s23")){
//               row[c] = ((ExamDbDataHolder)users.get(i)).getBusiness();
//               c++;
//          }
//     }
//      
//      
//      
////      row[2] = ((ExamDbDataHolder)users.get(i)).getMathematics();
////      row[3] = ((ExamDbDataHolder)users.get(i)).getEng();
////      row[4] = ((ExamDbDataHolder)users.get(i)).getKiswa();
////      row[5] = ((ExamDbDataHolder)users.get(i)).getPhy();
////      row[6] = ((ExamDbDataHolder)users.get(i)).getChem();
////      row[7] = ((ExamDbDataHolder)users.get(i)).getBio();
////      row[8] = ((ExamDbDataHolder)users.get(i)).getHist();
////      row[9] = ((ExamDbDataHolder)users.get(i)).getGeo();
////      row[10] = ((ExamDbDataHolder)users.get(i)).getCre();
////      row[11] = ((ExamDbDataHolder)users.get(i)).getAgri();
////      row[12] = ((ExamDbDataHolder)users.get(i)).getBs();
//    
//      
//      model.addRow(row);
//    }
//    this.table.setModel(model);
//  }
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

        jButton1.setText("Print");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 972, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jComboBoxGroup, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(40, 40, 40)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBoxGroup, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 478, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(GroupSheet.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GroupSheet.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GroupSheet.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GroupSheet.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GroupSheet().setVisible(true);
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
