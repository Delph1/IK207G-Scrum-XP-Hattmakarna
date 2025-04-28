/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package panels;

import hattmakarna.Hattmakarna;
import hattmakarna.MainWindow;
import models.User;
import static hattmakarna.Hattmakarna.dbm;
import javax.swing.JOptionPane;
import java.util.ArrayList;
import javax.swing.GroupLayout;



public class MyProfile extends javax.swing.JPanel {
     private MainWindow window;
private User currentUser;
  
    public MyProfile(MainWindow window) {
        initComponents();
        this.window=window;
        fyllComboBoxen();
        
         currentUser = Hattmakarna.currentUser;
         dynamicNameLabel.setText(currentUser.getUserName());

    }
    
    
public void laggTillAnvandare() {
    String username = JOptionPane.showInputDialog(this, "Ange användarnamn:");
    if (username == null || username.trim().isEmpty()) {
        JOptionPane.showMessageDialog(this, "Användarnamn får inte vara tomt.");
        return;
    }

    String password = JOptionPane.showInputDialog(this, "Ange lösenord:");
    if (password == null || password.trim().isEmpty()) {
        JOptionPane.showMessageDialog(this, "Lösenord får inte vara tomt.");
        return;
    }

    int option = JOptionPane.showConfirmDialog(this, "Ska användaren vara aktiv?", "Användarstatus", JOptionPane.YES_NO_OPTION);
    boolean isActive = (option == JOptionPane.YES_OPTION);

    User nyUser = Hattmakarna.dbm.createUser(username.trim(), password.trim(), isActive);

    if (nyUser != null) {
        JOptionPane.showMessageDialog(this, "Användaren '" + username + "' skapades!");
    } else {
        JOptionPane.showMessageDialog(this, "Något gick fel vid skapandet.");
    }
}
    
public void fyllComboBoxen(){
    anvandareComboBox.removeAllItems();
    
    ArrayList<User> users = dbm.getUsers();
    for(User user : users){
        if(user.isActive()){
            if(user.isActive()){
                anvandareComboBox.addItem(user.getUserName());

            }
        }
    }
}




public void taBortAnvandare(){
    String valdAnvandare = (String) anvandareComboBox.getSelectedItem();
    if(valdAnvandare == null){
        JOptionPane.showMessageDialog(this, "ingen användare vald");
        return;
    }
    
    ArrayList<User> users = dbm.getUsers();
        for(User user : users){
            if(user.getUserName().equals(valdAnvandare)){
                user.setActive(false);
                
                boolean success = dbm.updateUser(user);
                
                if(success){
                    JOptionPane.showMessageDialog(this, "Användare: " + valdAnvandare + " har avaktiverats");
                    anvandareComboBox.removeAllItems();
                    fyllComboBoxen();
                } else{
                    JOptionPane.showMessageDialog(this, "Kunde inte avaktiver användare, kan redan vara avaktiverad");
                }
                break;
            }
           
        }
}

    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        dynamicNameLabel = new javax.swing.JLabel();
        andraLosenBTN = new javax.swing.JButton();
        laggTillAnvandareBTN = new javax.swing.JButton();
        anvandareComboBox = new javax.swing.JComboBox<>();
        taBortAnvandare = new javax.swing.JButton();

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 40)); // NOI18N
        jLabel2.setText("Min profil:");

        dynamicNameLabel.setFont(new java.awt.Font("Segoe UI", 0, 40)); // NOI18N
        dynamicNameLabel.setText("dynamicNameLabel");

        andraLosenBTN.setText("Ändra Lösenord");
        andraLosenBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                andraLosenBTNActionPerformed(evt);
            }
        });

        laggTillAnvandareBTN.setText("Lägg till Användare");
        laggTillAnvandareBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                laggTillAnvandareBTNActionPerformed(evt);
            }
        });

        anvandareComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                anvandareComboBoxActionPerformed(evt);
            }
        });

        taBortAnvandare.setText("Avaktivera Användare");
        taBortAnvandare.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                taBortAnvandareActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(182, 182, 182)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dynamicNameLabel))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(anvandareComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(taBortAnvandare, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(andraLosenBTN, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(laggTillAnvandareBTN, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dynamicNameLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addGap(13, 13, 13)
                .addComponent(andraLosenBTN)
                .addGap(27, 27, 27)
                .addComponent(laggTillAnvandareBTN)
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(anvandareComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(taBortAnvandare))
                .addContainerGap(147, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void andraLosenBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_andraLosenBTNActionPerformed
window.showandraLosen();
        // TODO add your handling code here:
    }//GEN-LAST:event_andraLosenBTNActionPerformed

    private void laggTillAnvandareBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_laggTillAnvandareBTNActionPerformed
      laggTillAnvandare();
        // TODO add your handling code here:
    }//GEN-LAST:event_laggTillAnvandareBTNActionPerformed

    private void anvandareComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_anvandareComboBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_anvandareComboBoxActionPerformed

    private void taBortAnvandareActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_taBortAnvandareActionPerformed
taBortAnvandare();
        // TODO add your handling code here:
    }//GEN-LAST:event_taBortAnvandareActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton andraLosenBTN;
    private javax.swing.JComboBox<String> anvandareComboBox;
    private javax.swing.JLabel dynamicNameLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JButton laggTillAnvandareBTN;
    private javax.swing.JButton taBortAnvandare;
    // End of variables declaration//GEN-END:variables
}
