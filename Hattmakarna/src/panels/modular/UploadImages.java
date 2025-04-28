
package panels.modular;

import static hattmakarna.Hattmakarna.dbm;
import hattmakarna.ModularWindow;
import java.awt.Component;
import java.awt.Image;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.Set;
import java.util.TreeSet;
import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import models.Product;
import models.ProductImage;
import utils.ImageManager;

public class UploadImages extends javax.swing.JPanel {

    private ModularWindow window;
    private DefaultTableModel imageTable;
    private int productId;
    private ImageManager imageManager;
    
    public UploadImages(ModularWindow window, int productId) {
        this.window = window;
        this.productId = productId;
        this.imageTable = new DefaultTableModel();
        this.imageManager = new ImageManager(window, true);
        initComponents();
        tblImages.setModel(imageTable);
        createTable();
    }
    
    private void createTable() {
        imageTable.addColumn("ID");
        imageTable.addColumn("Typ");
        imageTable.addColumn("base64");
        imageTable.addColumn("Beskrivning");
        
        ArrayList<ProductImage> images = dbm.getImages();
        //populerar tabellen med existerande bilder
        if (images.size() > 0) { 
            for (ProductImage image : images) {
                if (image.getProductId() == productId) {
                    imageTable.addRow(new Object[]{image.getImageId(), image.getType(), image.getBase64()});
                }
            }
        }
        
        Set<String> types = new TreeSet<>(Arrays.asList(
            "Produktbild", "Ritning", "3dvy"
        ));
        JComboBox<String> comboBoxTypes = new JComboBox<>(types.toArray(new String[0]));
        tblImages.getColumnModel().getColumn(1).setCellEditor(new DefaultCellEditor(comboBoxTypes));

        
        //Ställer in kolumner        
        tblImages.getColumnModel().getColumn(2).setMinWidth(0);
        tblImages.getColumnModel().getColumn(2).setMaxWidth(0);
        tblImages.getColumnModel().getColumn(2).setWidth(0);
        tblImages.getColumnModel().getColumn(0).setWidth(0);
        tblImages.getColumnModel().getColumn(0).setMinWidth(0);
        tblImages.getColumnModel().getColumn(0).setMaxWidth(0);
        tblImages.getColumnModel().getColumn(1).setWidth(75);
        tblImages.getColumnModel().getColumn(1).setMinWidth(75);
        tblImages.getColumnModel().getColumn(1).setMaxWidth(75);
        
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tblImages = new javax.swing.JTable();
        btnUploadImage = new javax.swing.JButton();
        lblImage = new javax.swing.JLabel();
        btnSave = new javax.swing.JButton();

        tblImages.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(tblImages);

        btnUploadImage.setText("Ladda upp bild");
        btnUploadImage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUploadImageActionPerformed(evt);
            }
        });

        lblImage.setText("Ingen bild uppladdad än");

        btnSave.setText("Spara bilder");
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
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblImage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnUploadImage)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnSave))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 484, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnUploadImage)
                    .addComponent(btnSave))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(7, 7, 7)
                .addComponent(lblImage, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(11, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnUploadImageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUploadImageActionPerformed
        byte[] imageBytes = imageManager.uploadImage();
        
        ImageIcon icon = new ImageIcon(imageBytes);
        Image scaledImage = icon.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
        lblImage.setIcon(new ImageIcon(scaledImage));
        lblImage.setText(null);
        String base64Image = Base64.getEncoder().encodeToString(imageBytes);
        
        imageTable.addRow(new Object[] {0, "", base64Image});
        
        int lastRow = imageTable.getRowCount() - 1;
        
        tblImages.editCellAt(lastRow, 1);  // Starta redigering
        Component editor = tblImages.getEditorComponent();
        if (editor != null) {
            editor.requestFocus(); // Fokusera komponenten (comboBox)
        }
    }//GEN-LAST:event_btnUploadImageActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        for (int i = 0; i < tblImages.getRowCount(); i++) {
            String description = tblImages.getValueAt(i, 3) == null ? "" : tblImages.getValueAt(i, 3).toString(); 
            if (Integer.parseInt(tblImages.getValueAt(i, 0).toString()) != 0) {                
                ProductImage updatedImage = new ProductImage (
                    tblImages.getValueAt(i, 2).toString(),
                    Integer.parseInt(tblImages.getValueAt(i, 0).toString()),
                    productId,
                    tblImages.getValueAt(i, 1).toString(),
                    description
                );
                dbm.updateImage(updatedImage);
            } else {
                ProductImage newImage = dbm.createImage();
                newImage.setBase64(tblImages.getValueAt(i, 2).toString());
                newImage.setProductId(productId);
                newImage.setType(tblImages.getValueAt(i, 1).toString());
                newImage.setDescription(description);
                dbm.updateImage(newImage);
            }
        }
    window.dispose();
    }//GEN-LAST:event_btnSaveActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSave;
    private javax.swing.JButton btnUploadImage;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblImage;
    private javax.swing.JTable tblImages;
    // End of variables declaration//GEN-END:variables
}
