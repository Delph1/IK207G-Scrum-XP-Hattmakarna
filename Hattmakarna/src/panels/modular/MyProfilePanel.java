/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package panels.modular;

import hattmakarna.Hattmakarna;
import models.User;
import static hattmakarna.Hattmakarna.dbm;
import javax.swing.JOptionPane;
import hattmakarna.ModularWindow;
import java.awt.Frame;


/**
 *
 * @author eriks
 */
public class MyProfilePanel extends javax.swing.JPanel {
    private ModularWindow window;
    private User currentUser;
    private Frame frame;
    
    public MyProfilePanel(ModularWindow window, Frame frame) {
        initComponents();
        this.window = window;
        this.frame = frame;
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

    public void deleteUser() {
        String username = JOptionPane.showInputDialog(this, "Ange användarnamnet som du vill inaktivera:");
        if (username.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Inget användarnamn angavs.");
            return;
        }
        User user = dbm.getUserFromUsername(username);
        if (user != null) {
            user.setActive(false);
            dbm.updateUser(user);
            JOptionPane.showMessageDialog(this, "Användaren " + username + " har inaktiverats.");
        } else {
            JOptionPane.showMessageDialog(this, "Ingen användare hittades.");
        }
    }
    
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        dynamicNameLabel = new javax.swing.JLabel();
        btnChangePassword = new javax.swing.JButton();
        btnAddUser = new javax.swing.JButton();
        btnDeleteUser = new javax.swing.JButton();

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 40)); // NOI18N
        jLabel2.setText("Min profil:");

        dynamicNameLabel.setFont(new java.awt.Font("Segoe UI", 0, 40)); // NOI18N
        dynamicNameLabel.setText("dynamicNameLabel");

        btnChangePassword.setText("Ändra Lösenord");
        btnChangePassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChangePasswordActionPerformed(evt);
            }
        });

        btnAddUser.setText("Lägg till Användare");
        btnAddUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddUserActionPerformed(evt);
            }
        });

        btnDeleteUser.setText("Ta bort Användare");
        btnDeleteUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteUserActionPerformed(evt);
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
                        .addContainerGap()
                        .addComponent(btnChangePassword))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dynamicNameLabel))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnAddUser))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnDeleteUser)))
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
                .addGap(47, 47, 47)
                .addComponent(btnChangePassword)
                .addGap(18, 18, 18)
                .addComponent(btnAddUser)
                .addGap(18, 18, 18)
                .addComponent(btnDeleteUser)
                .addContainerGap(131, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnChangePasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChangePasswordActionPerformed
        ModularWindow modularWindow = new ModularWindow(frame, true);
        modularWindow.showandraLosen();;
        modularWindow.setVisible(true);
        modularWindow.setAlwaysOnTop(true);
    }//GEN-LAST:event_btnChangePasswordActionPerformed

    private void btnAddUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddUserActionPerformed
        laggTillAnvandare();
    }//GEN-LAST:event_btnAddUserActionPerformed

    private void btnDeleteUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteUserActionPerformed
        deleteUser();
    }//GEN-LAST:event_btnDeleteUserActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddUser;
    private javax.swing.JButton btnChangePassword;
    private javax.swing.JButton btnDeleteUser;
    private javax.swing.JLabel dynamicNameLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    // End of variables declaration//GEN-END:variables
}
