/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imua.development.Accounts;

import imua.development.Accounts.Accgroups;
import imua.development.Methods;
import imua.development.UserDataHolder;
import java.awt.Color;
import java.awt.Container;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *22
 * @author kimani kogi
 */
public class Users extends javax.swing.JFrame {
    private String filePath;
    private String fileurlp = null;
String toCombo[];
    String toAccountsTypes[];
    private String group = null;
    private String accounttype = null;
  String accounts[];
  String groups[];
  Methods m= new Methods();
    /**
     * Creates new form Users
     */
    public Users() {
        initComponents();
        findUsers();
        findGroups();
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
            jPanel2.setBackground(c);
            jPanel1.setBackground(c);
        } catch (Exception ex) {
            Logger.getLogger(Users.class.getName()).log(Level.SEVERE, null, ex);
        }
}
     public void findUsers()
  {
     
    ArrayList<String> usersList =  m.ListUsers();
   accounts=new String[usersList.size()];
   usersList.toArray(accounts);
   accountsTypes();
  }
   public void findGroups()
  {
  
    ArrayList<String> usersList =  m.ListUsersGroups();
   groups=new String[usersList.size()];
   usersList.toArray(groups);
   groupnames();
   //JOptionPane.showMessageDialog(null, groups[0]);
  }

    private void accountsTypes() {
       // String accounts[] = {"Regular Acc", "Projects Acc", "Table Acc"};
        toAccountsTypes = accounts;
        setAccountModel();
    }

    private void setAccountModel() {
        jComboBoxAccount.removeAllItems();
        jComboBoxAccount.addItem("choose");
        for (int a = 0; a < toAccountsTypes.length; a++) {

            jComboBoxAccount.addItem(toAccountsTypes[a]);
        }
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
      String searchQuery = "SELECT * FROM `users`WHERE id='"+id+"' ";
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
  public void findUsers(String loanid) 
  {
    ArrayList<UserDataHolder> users = ListUsers(loanid);
    DefaultTableModel model = new DefaultTableModel();
  //   String ID, Date ate, String Installmentamount, String LoanAmount,String autoid,String instno
    model.setColumnIdentifiers(new Object[] { "ID", "FNAME","SNAME","EMAIL","PHONE","ADDRESS","COUNTY","TOWN","OCC","MARRITAL","ACC","GRP","IMG" });
    Object[] row = new Object[13];
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
        row[11] = ((UserDataHolder)users.get(i)).getGroup();
        row[12] = ((UserDataHolder)users.get(i)).getImage();
      
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
        jPanel2 = new javax.swing.JPanel();
        txtId = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtSalutation = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtFname = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtLname = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtPhone = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtAddress = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtCounty = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtTown = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtOccupation = new javax.swing.JTextField();
        jComboBoxGender = new javax.swing.JComboBox<>();
        jComboBoxAccount = new javax.swing.JComboBox<>();
        jComboBoxGroup = new javax.swing.JComboBox<>();
        img = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        txtFilter = new javax.swing.JTextField();

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
        table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(table);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("DETAILS"));

        txtId.setEditable(false);

        jLabel1.setText("ID");

        jLabel2.setText("SAL..");

        jLabel3.setText("FNAME");

        txtFname.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFnameActionPerformed(evt);
            }
        });

        jLabel4.setText("LNAME");

        jLabel5.setText("EMAIL");

        txtEmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEmailActionPerformed(evt);
            }
        });

        jLabel6.setText("PHONE");

        jLabel7.setText("ADDRESS");

        jLabel8.setText("COUNTY");

        jLabel9.setText("TOWN");

        jLabel10.setText("OCCUP..");

        jComboBoxGender.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "--Select--", "Single", "Married", "Widowed", "Divorced" }));

        jButton1.setText("REPLACE");
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

        jButton3.setText("UPDATE");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("DELETE");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setText("CLEAR");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel9))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtFname, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(txtAddress)
                                    .addComponent(txtEmail)
                                    .addComponent(txtId, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(txtTown, javax.swing.GroupLayout.DEFAULT_SIZE, 102, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel2)
                                            .addComponent(jLabel4)
                                            .addComponent(jLabel6)
                                            .addComponent(jLabel8))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(txtSalutation, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addGap(1, 1, 1)
                                                .addComponent(txtLname))
                                            .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addGap(2, 2, 2)
                                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(txtCounty, javax.swing.GroupLayout.Alignment.TRAILING)
                                                    .addComponent(txtPhone)
                                                    .addComponent(txtOccupation)
                                                    .addComponent(jComboBoxGroup, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                                    .addComponent(jLabel10)))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jComboBoxGender, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboBoxAccount, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(img, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jButton1)
                                    .addComponent(jButton5)))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(74, 74, 74)
                        .addComponent(jButton2)
                        .addGap(18, 18, 18)
                        .addComponent(jButton3)
                        .addGap(18, 18, 18)
                        .addComponent(jButton4)))
                .addGap(44, 44, 44))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(txtSalutation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtFname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(txtLname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(txtPhone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtAddress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(txtCounty, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txtTown, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)
                    .addComponent(txtOccupation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBoxGender, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBoxAccount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBoxGroup, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(img, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(jButton1)
                        .addGap(41, 41, 41)
                        .addComponent(jButton5)))
                .addGap(56, 56, 56)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jButton3)
                    .addComponent(jButton4))
                .addContainerGap(56, Short.MAX_VALUE))
        );

        txtFilter.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtFilterKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 592, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txtFilter, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txtFilter, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1))
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtFnameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFnameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFnameActionPerformed

    private void txtEmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEmailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEmailActionPerformed

    private void txtFilterKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFilterKeyReleased
        refresh(txtFilter.getText());
    }//GEN-LAST:event_txtFilterKeyReleased
String marital;
String acc;
String grp;
String imgp;
    private void tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableMouseClicked
          int i = this.table.getSelectedRow();
     ;
    TableModel model = this.table.getModel();
                txtId.setText(model.getValueAt(i, 0).toString());
                this.txtFname.setText(model.getValueAt(i, 1).toString());
                this.txtLname.setText(model.getValueAt(i, 2).toString());
                this.txtEmail.setText(model.getValueAt(i, 3).toString());
                this.txtPhone.setText(model.getValueAt(i, 4).toString());
                this.txtAddress.setText(model.getValueAt(i, 5).toString());
                this.txtCounty.setText(model.getValueAt(i, 6).toString());
                this.txtTown.setText(model.getValueAt(i, 7).toString());
                this.txtOccupation.setText(model.getValueAt(i, 8).toString());
                marital=(model.getValueAt(i, 9).toString());
               acc=(model.getValueAt(i, 10).toString());
               grp=(model.getValueAt(i, 11).toString());
               imgp=(model.getValueAt(i, 12).toString());
                jComboBoxGroup.setSelectedItem(grp);
                jComboBoxAccount.setSelectedItem(acc);
                jComboBoxGender.setSelectedItem(marital);
                setImage();
    }//GEN-LAST:event_tableMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        attachImage();
        
    }
    private void attachImage() {
        try {

            JFileChooser chooser = new JFileChooser();
            FileFilter ft = new FileNameExtensionFilter("Image Files", new String[]{"jpg", "png", "jpeg"});

            chooser.addChoosableFileFilter(ft);

            chooser.showOpenDialog(null);
            File f = chooser.getSelectedFile();

            this.imgp = f.getAbsolutePath().toString();
            BufferedImage img = null;
            try {
                img = ImageIO.read(new File(this.imgp));
                this.fileurlp = this.imgp.replace("\\", "\\\\");
            } catch (IOException e) {
                e.printStackTrace();
            }
            Image dimg = img.getScaledInstance(this.img.getWidth(), this.img.getHeight(), 4);

            ImageIcon icon = new ImageIcon(dimg);
            this.img.setText("");
            this.img.setIcon(icon);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            JOptionPane.showMessageDialog(null, "error loading image ");
        }
    
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
       this.img.setText("no image");
            this.img.setIcon(null);
            imgp="no image";
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        if(txtId.getText().isEmpty()||txtFname.getText().isEmpty()||jComboBoxGender.getSelectedIndex()==0||jComboBoxAccount.getSelectedIndex()==0
                ||jComboBoxGroup.getSelectedIndex()==0){
            JOptionPane.showMessageDialog(null, "VERY IMPORTANT FIELDS MISSING","WARNING",JOptionPane.ERROR_MESSAGE);
        }
        else{
        update();
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
      if(checkLoan()==1){
           JOptionPane.showMessageDialog(null, "Customer has loans payment pending","Warning",JOptionPane.INFORMATION_MESSAGE);
      }
      else if(checkMaxId()==1){
           JOptionPane.showMessageDialog(null, "Customer has savings in account","Warning",JOptionPane.INFORMATION_MESSAGE);
      }
      else{
          
    String query = "DELETE FROM `users` WHERE id = " + this.txtId.getText();
    
    if(m.executeSQlQueryN(query)==1){
        JOptionPane.showMessageDialog(null, "DELETED SUCCESFULLY","DELETE PROCESS",JOptionPane.INFORMATION_MESSAGE);
           clear();
    }
    else{
         JOptionPane.showMessageDialog(null, " NOT DELETED SUCCESFULLY","DELETE PROCESS",JOptionPane.ERROR_MESSAGE);
    }
      }

    }//GEN-LAST:event_jButton4ActionPerformed
public int checkLoan(){
   int yn=0;
    try {
           Methods m = new Methods();
           Connection con = m.getConnection();
           Statement st = con.createStatement();
           
           String searchQuery = "SELECT loanid FROM `loans` WHERE `id` = '" +txtId.getText()+ "'";
           ResultSet rs = st.executeQuery(searchQuery);
           if(rs.next()){
              yn=1; 
               //val=Double.valueOf(rs.getString("balance"));
               
               
               
           }
       } catch (SQLException ex) {
           Logger.getLogger(Users.class.getName()).log(Level.SEVERE, null, ex);
       }
    return yn;
}
public int checkMaxId(){
       int yn=0;
    try {
           Methods m = new Methods();
           Connection con = m.getConnection();
           Statement st = con.createStatement();
           
           String searchQuery = "SELECT  MAX(autoid)FROM `transactions` WHERE `id` = '" +txtId.getText()+ "'";
           
           ResultSet rs = st.executeQuery(searchQuery);
           if(rs.next()){
               
              yn=checkDeposits(rs.getString("autoid"));
               //val=Double.valueOf(rs.getString("balance"));
               
               
               
           }
       } catch (SQLException ex) {
           Logger.getLogger(Users.class.getName()).log(Level.SEVERE, null, ex);
       }
    return yn;
}
public int checkDeposits(String max){
       int yn=0;
    try {
           Methods m = new Methods();
           Connection con = m.getConnection();
           Statement st = con.createStatement();
           
           String searchQuery = "SELECT  `balance` FROM `transactions` WHERE `autoid` = '" +max+ "'";
           
           ResultSet rs = st.executeQuery(searchQuery);
           if(rs.next()){
               
               if(Double.valueOf(rs.getString("balance"))> 0.0){
                   yn=1;
               
           }
             // yn=1; 
               //val=Double.valueOf(rs.getString("balance"));
               
               
               
           }
       } catch (SQLException ex) {
           Logger.getLogger(Users.class.getName()).log(Level.SEVERE, null, ex);
       }
    return yn;
}
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
       clear();
    }//GEN-LAST:event_jButton2ActionPerformed
public void setImage(){
     BufferedImage img = null;
            try {
                img = ImageIO.read(new File(this.imgp));
                this.fileurlp = this.imgp.replace("\\", "\\\\");
            } catch (IOException e) {
               // e.printStackTrace();
               this.img.setIcon(null);
                 this.img.setText("no image");
            }
            Image dimg = img.getScaledInstance(this.img.getWidth(), this.img.getHeight(), 4);

            ImageIcon icon = new ImageIcon(dimg);
            this.img.setText("");
            this.img.setIcon(icon);
}
public void update(){
    
     String query = "UPDATE `users` SET `fname`='" + this.txtFname.getText() + "',`sname`= '" + this.txtLname.getText() + "'"
            + ",`email`= '" + this.txtEmail.getText() + "',`phone`= '" + this.txtPhone.getText() + "',`address`= '" + this.txtAddress.getText() + "',"
            + "`county`= '"+txtCounty.getText()+"',`town`='"+txtTown.getText()+"',"
            + "`occupation`='"+txtOccupation.getText()+"',`maritalstatus`= '" + this.jComboBoxGender.getSelectedItem().toString() + "',"
             + "`phone`= '" + this.txtPhone.getText() + "',`acounttype`= '" + this.jComboBoxAccount.getSelectedItem().toString()+ "',"
             + "`group`= '" + this.jComboBoxGroup.getSelectedItem().toString() + "',`imgurl`= '" + fileurlp + "'"
             + "WHERE id= '" + this.txtId.getText()+"'";
      
       if(m. executeSQlQueryN(query)==1){
           JOptionPane.showMessageDialog(null, "UPDATED SUCCESFULLY","UPDATE PROCESS",JOptionPane.INFORMATION_MESSAGE);
           clear();
       }
       else{
            JOptionPane.showMessageDialog(null, " NOT UPDATED SUCCESFULLY","UPDATE PROCESS",JOptionPane.ERROR_MESSAGE);
       }
}
public void clear(){
   jComboBoxAccount.setSelectedIndex(0);
  jComboBoxGender.setSelectedIndex(0);
   jComboBoxGroup.setSelectedIndex(0);
   txtAddress.setText("");
     txtCounty.setText("");
     txtEmail.setText("");
    txtFilter.setText("");
    txtFname.setText("");
    txtId.setText("");
    txtLname.setText("");
     txtOccupation.setText("");
     txtPhone.setText("");
     txtSalutation.setText("");
     txtTown.setText("");
     this.img.setText("no image");
            this.img.setIcon(null);
            imgp="no image";
            refresh(txtFilter.getText());
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
            java.util.logging.Logger.getLogger(Users.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Users.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Users.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Users.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Users().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel img;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JComboBox<String> jComboBoxAccount;
    private javax.swing.JComboBox<String> jComboBoxGender;
    private javax.swing.JComboBox<String> jComboBoxGroup;
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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable table;
    private javax.swing.JTextField txtAddress;
    private javax.swing.JTextField txtCounty;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtFilter;
    private javax.swing.JTextField txtFname;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtLname;
    private javax.swing.JTextField txtOccupation;
    private javax.swing.JTextField txtPhone;
    private javax.swing.JTextField txtSalutation;
    private javax.swing.JTextField txtTown;
    // End of variables declaration//GEN-END:variables
}
