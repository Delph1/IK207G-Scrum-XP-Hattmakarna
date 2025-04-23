
package panels;

import static hattmakarna.Hattmakarna.dbm;
import hattmakarna.MainWindow;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

import java.util.Base64;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import models.Product;
import models.ProductImage;
import utils.ImageManager;

public class ImageManagerPanel extends javax.swing.JPanel {

    private String base64Image;
    private MainWindow window;
    private ImageManager imageManager;
    private DefaultTableModel imageTable;
    
    public ImageManagerPanel(MainWindow window) {
        this.window = window;
        this.imageManager = new ImageManager(window);
        this.imageTable = new DefaultTableModel(); 
        initComponents();
        
        fillImageTable();
        createListener();
    }
    
    private void fillImageTable() {
        ArrayList<ProductImage> images = dbm.getImages();
        imageTable.addColumn("Produkt ID");
        imageTable.addColumn("Produkt");
        imageTable.addColumn("Bild ID");
        
        for (ProductImage image : images ) {
            Product imageProduct = dbm.getProduct(image.getProductId());
            imageTable.addRow(new Object[] {image.getProductId(), imageProduct.getProductName(), image.getImageId()});
        }
        tblImages.setModel(imageTable);
    }
    
    private void createListener() {
        tblImages.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    int markeradRad = tblImages.getSelectedRow();
                    if (markeradRad != -1) {
                        updateImage(markeradRad);
                    }
                }
            }
        });
    }

    private void updateImage(int rad) {
        ProductImage image = dbm.getImage(Integer.parseInt(tblImages.getValueAt(rad, 2).toString()));
        
        if (image != null) {
            byte[] imageBytes = Base64.getDecoder().decode(image.getBase64());
            ImageIcon icon = new ImageIcon(imageBytes);
            Image scaledImage = icon.getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH);
            lblImage.setIcon(new ImageIcon(scaledImage));
            lblImage.setText(null);
        } else {
            lblImage.setIcon(null);
            lblImage.setText("Ingen bild hittades");
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblImage = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblImages = new javax.swing.JTable();
        btnDeleteImage = new javax.swing.JButton();

        lblImage.setText("Ingen bild hittades");

        tblImages.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(tblImages);

        btnDeleteImage.setText("Radera bild");
        btnDeleteImage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteImageActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblImage)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(btnDeleteImage)
                        .addContainerGap(80, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblImage)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnDeleteImage))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnDeleteImageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteImageActionPerformed
        int rad = tblImages.getSelectedRow();
        if (rad != -1) {
            int imageId = Integer.parseInt(tblImages.getValueAt(rad, 2).toString());
            dbm.deleteImage(imageId);
            imageTable.removeRow(rad);
            lblImage.setIcon(null);
        } else {
            JOptionPane.showMessageDialog(this, "Ingen rad är markerad.");
        }
    }//GEN-LAST:event_btnDeleteImageActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDeleteImage;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblImage;
    private javax.swing.JTable tblImages;
    // End of variables declaration//GEN-END:variables
}
