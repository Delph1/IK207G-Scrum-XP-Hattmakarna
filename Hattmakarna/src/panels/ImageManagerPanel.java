
package panels;

import static hattmakarna.Hattmakarna.dbm;
import hattmakarna.MainWindow;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Base64;
import models.ProductImage;

public class ImageManagerPanel extends javax.swing.JPanel {

    private JLabel imageLabel;
    private String base64Image;
    private MainWindow window;
    
    public ImageManagerPanel(MainWindow window) {
        this.window = window;
        initComponents();
        
        lblImage.setVerticalAlignment(SwingConstants.CENTER);
    }

    private void uploadImage() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Välj en bild");
        fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Bildfiler", "jpg", "jpeg", "png", "gif"));

        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();

            try {
                // Läs filen som byte-array och konvertera till Base64
                byte[] imageBytes = Files.readAllBytes(selectedFile.toPath());
                base64Image = Base64.getEncoder().encodeToString(imageBytes);

                // Visa bilden
                ImageIcon icon = new ImageIcon(imageBytes);
                Image scaledImage = icon.getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH);
                lblImage.setIcon(new ImageIcon(scaledImage));
                lblImage.setText(null);  // ta bort texten "Ingen bild vald"

            } catch (IOException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Kunde inte läsa bilden.", "Fel", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    private void saveImage() {
        ProductImage image = dbm.createImage();
        image.setBase64(base64Image);
        dbm.updateImage(image);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblImage = new javax.swing.JLabel();
        btnUpload = new javax.swing.JButton();
        btnSave = new javax.swing.JButton();

        lblImage.setText("Ladda upp en bild ...");

        btnUpload.setText("Ladda upp bild");
        btnUpload.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUploadActionPerformed(evt);
            }
        });

        btnSave.setText("Spara bild");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(169, 169, 169)
                        .addComponent(lblImage))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnSave)
                            .addComponent(btnUpload))))
                .addContainerGap(124, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(btnUpload)
                .addGap(86, 86, 86)
                .addComponent(lblImage)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 114, Short.MAX_VALUE)
                .addComponent(btnSave)
                .addGap(16, 16, 16))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnUploadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUploadActionPerformed
        uploadImage();
    }//GEN-LAST:event_btnUploadActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        saveImage();
    }//GEN-LAST:event_btnSaveActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSave;
    private javax.swing.JButton btnUpload;
    private javax.swing.JLabel lblImage;
    // End of variables declaration//GEN-END:variables
}
