
package panels;

import static hattmakarna.Hattmakarna.dbm;
import hattmakarna.MainWindow;
import hattmakarna.ModularWindow;
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
        imageTable.addColumn("Typ");
        imageTable.addColumn("base64");
        
        for (ProductImage image : images ) {
            Product imageProduct = dbm.getProduct(image.getProductId());
            imageTable.addRow(new Object[] {image.getProductId(), imageProduct.getProductName(), image.getImageId(), image.getType(), image.getBase64()});
        }
        tblImages.setModel(imageTable);
        //gömmer femte kolumnen        
        tblImages.getColumnModel().getColumn(4).setMinWidth(0);
        tblImages.getColumnModel().getColumn(4).setMaxWidth(0);
        tblImages.getColumnModel().getColumn(4).setWidth(0);

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
    
       
    public void showImageInBigWindow(String base64Image) {
        try {
            // Dekoda Base64 → byte[]
            byte[] bildBytes = Base64.getDecoder().decode(base64Image);

            // Skapa ImageIcon
            ImageIcon ikon = new ImageIcon(bildBytes);

            // Visa i ny ruta
            JLabel bildLabel = new JLabel(ikon);
            JScrollPane scroll = new JScrollPane(bildLabel);

            JFrame imageWindow = new JFrame("Storbild");
            imageWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            imageWindow.add(scroll);
            imageWindow.pack(); 
            imageWindow.setVisible(true);
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(null, "Fel vid avkodning av bild.", "Fel", JOptionPane.ERROR_MESSAGE);
        }
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblImage = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblImages = new javax.swing.JTable();
        btnDeleteImage = new javax.swing.JButton();
        btnShowPicture = new javax.swing.JButton();

        lblImage.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));

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

        btnShowPicture.setText("Visa ursprungsbild");
        btnShowPicture.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnShowPictureActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnShowPicture)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnDeleteImage))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(lblImage, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(36, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblImage, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnShowPicture)
                    .addComponent(btnDeleteImage))
                .addContainerGap(15, Short.MAX_VALUE))
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

    private void btnShowPictureActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnShowPictureActionPerformed
        int rad = tblImages.getSelectedRow();
        if (rad != -1) {
            String base64Sträng = (String) tblImages.getValueAt(rad, 4);
            showImageInBigWindow(base64Sträng);
        }
    }//GEN-LAST:event_btnShowPictureActionPerformed
 
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDeleteImage;
    private javax.swing.JButton btnShowPicture;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblImage;
    private javax.swing.JTable tblImages;
    // End of variables declaration//GEN-END:variables
}
