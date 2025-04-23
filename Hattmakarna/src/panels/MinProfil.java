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
import hattmakarna.ModularWindow;


/**
 *
 * @author eriks
 */
public class MinProfil extends javax.swing.JPanel {
     private MainWindow window;
private User currentUser;
  
    public MinProfil(MainWindow window) {
        initComponents();
        this.window=window;
        
         currentUser = Hattmakarna.currentUser;
                    dynamicNameLabel.setText(currentUser.getUserName());
    }
    
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        dynamicNameLabel = new javax.swing.JLabel();
        andraLosenBTN = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

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

        jButton2.setText("Lägg till Användare");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Ta bort Användare");

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
                        .addComponent(andraLosenBTN))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dynamicNameLabel))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jButton3))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jButton2)))
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
                .addComponent(andraLosenBTN)
                .addGap(18, 18, 18)
                .addComponent(jButton3)
                .addGap(18, 18, 18)
                .addComponent(jButton2)
                .addContainerGap(131, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void andraLosenBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_andraLosenBTNActionPerformed
window.showandraLosen();
        // TODO add your handling code here:
    }//GEN-LAST:event_andraLosenBTNActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton andraLosenBTN;
    private javax.swing.JLabel dynamicNameLabel;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    // End of variables declaration//GEN-END:variables
}
