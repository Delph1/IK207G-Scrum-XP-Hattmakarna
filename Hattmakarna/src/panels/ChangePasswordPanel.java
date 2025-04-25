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
/**
 *
 * @author eriks
 */
public class ChangePasswordPanel extends javax.swing.JPanel {
private MainWindow window;
private User currentUser;
   
    public ChangePasswordPanel(MainWindow window) {
        initComponents();
        this.window=window;
        }

  public void andraLosen(){
       String gammaltLosen = gammaltLosenField.getText().trim();
       String nyttLosen = nyttLosenField.getText().trim();
       
      User user = Hattmakarna.currentUser;
    
    String krypteratGammalt = dbm.md5(gammaltLosen);

    if (!krypteratGammalt.equals(user.getPassword())) {
        JOptionPane.showMessageDialog(this, "Fel lösenord");
        return;
    }

    String krypteratNytt = dbm.md5(nyttLosen);

    user.setPassword(krypteratNytt);
    
    boolean lyckades = dbm.updateUser(user);

    if (lyckades) {
        JOptionPane.showMessageDialog(this, "Lösenordet har ändrats.");
    } else {
        JOptionPane.showMessageDialog(this, "Det gick inte att ändra lösenordet.");
    }
}
   

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        gammaltLosenField = new javax.swing.JTextField();
        nyttLosenField = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        bekraftaBTN = new javax.swing.JButton();

        jLabel1.setText("Gammalt lösenord");

        jLabel2.setText("Nytt lösenord");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel3.setText("Ändra Lösenord");

        bekraftaBTN.setText("Bekräfta");
        bekraftaBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bekraftaBTNActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(bekraftaBTN)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(gammaltLosenField, javax.swing.GroupLayout.DEFAULT_SIZE, 115, Short.MAX_VALUE)
                                .addComponent(nyttLosenField)))))
                .addContainerGap(144, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(gammaltLosenField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nyttLosenField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addComponent(bekraftaBTN)
                .addContainerGap(137, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void bekraftaBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bekraftaBTNActionPerformed
     andraLosen();
     
        // TODO add your handling code here:
    }//GEN-LAST:event_bekraftaBTNActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bekraftaBTN;
    private javax.swing.JTextField gammaltLosenField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JTextField nyttLosenField;
    // End of variables declaration//GEN-END:variables
}
