package panels.modular;

import hattmakarna.MainWindow;

import static hattmakarna.Hattmakarna.dbm;
import hattmakarna.ModularWindow;
import java.awt.Image;
import java.util.Base64;
import javax.swing.ImageIcon;
import models.*;
import utils.ImageManager;

public class ProductPanel extends javax.swing.JPanel {
    private ModularWindow window;
    private ImageManager imageManager;
    private String base64Image;

    public ProductPanel(ModularWindow window) {
        this.window=window; 
        this.imageManager = new ImageManager(window, true);
        initComponents();        
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtProduktnamn = new java.awt.TextField();
        txtPris = new java.awt.TextField();
        jLabel3 = new javax.swing.JLabel();
        txtVikt = new java.awt.TextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        chkLagerfor = new javax.swing.JCheckBox();
        btnLaggTillProdukt = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtBeskrivning = new javax.swing.JTextArea();
        lblImage = new javax.swing.JLabel();
        btnUploadImage = new javax.swing.JButton();

        setName(""); // NOI18N

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel1.setText("Produktformulär");

        jLabel2.setText("Produktnamn");

        jLabel3.setText("Pris");

        txtVikt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtViktActionPerformed(evt);
            }
        });

        jLabel4.setText("Vikt (kilogram)");

        jLabel5.setText("Beskrivning");

        chkLagerfor.setSelected(true);
        chkLagerfor.setText("Lagerförd");

        btnLaggTillProdukt.setText("Lägg till hatt");
        btnLaggTillProdukt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLaggTillProduktActionPerformed(evt);
            }
        });

        txtBeskrivning.setColumns(20);
        txtBeskrivning.setLineWrap(true);
        txtBeskrivning.setRows(5);
        txtBeskrivning.setWrapStyleWord(true);
        jScrollPane1.setViewportView(txtBeskrivning);

        btnUploadImage.setText("Ladda upp bild");
        btnUploadImage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUploadImageActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel5)
                    .addComponent(jLabel3)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(txtProduktnamn, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtPris, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE))
                    .addComponent(jLabel4)
                    .addComponent(txtVikt, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 76, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnLaggTillProdukt)
                            .addComponent(chkLagerfor))
                        .addGap(52, 52, 52))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblImage, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnUploadImage))
                        .addGap(18, 18, 18))))
            .addGroup(layout.createSequentialGroup()
                .addGap(269, 269, 269)
                .addComponent(jLabel1)
                .addGap(133, 133, 133))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtProduktnamn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                        .addComponent(txtPris, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtVikt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblImage, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnUploadImage)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(chkLagerfor)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnLaggTillProdukt)))
                .addGap(55, 55, 55))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnLaggTillProduktActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLaggTillProduktActionPerformed
    try {
        Product nyProdukt = dbm.createProduct();
        nyProdukt.setProductName(txtProduktnamn.getText());
        nyProdukt.setPrice(Integer.parseInt(txtPris.getText()));
        nyProdukt.setWeight(Double.parseDouble(txtVikt.getText()));
        nyProdukt.setDescription(txtBeskrivning.getText());
        nyProdukt.setStockItem(true);
        nyProdukt.setDiscontinued(false);
        nyProdukt.setCopyRightApproved(false);
        nyProdukt.setProductBaseId(0);  

        dbm.updateProduct(nyProdukt);

        if (base64Image != null) {
            imageManager.saveNewImage(base64Image, nyProdukt.getProductId(), "Produktbild", "");
        }
        
        javax.swing.JOptionPane.showMessageDialog(this, "Ny hatt tillagd!");

        txtProduktnamn.setText("");
        txtPris.setText("");
        txtVikt.setText("");
        txtBeskrivning.setText("");
        chkLagerfor.setSelected(true);

    }   catch (Exception e) {
        javax.swing.JOptionPane.showMessageDialog(this, "Fel: " + e.getMessage());
    }
    window.dispose();
    
    if (window.getParent() instanceof MainWindow mw) {
    mw.showProductListPanel(); 
    }
    }//GEN-LAST:event_btnLaggTillProduktActionPerformed

    private void txtViktActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtViktActionPerformed
    }//GEN-LAST:event_txtViktActionPerformed

    private void btnUploadImageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUploadImageActionPerformed

        byte[] imageBytes = imageManager.uploadImage();

        ImageIcon icon = new ImageIcon(imageBytes);
        Image scaledImage = icon.getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH);
        lblImage.setIcon(new ImageIcon(scaledImage));
        lblImage.setText(null);
        base64Image = Base64.getEncoder().encodeToString(imageBytes);
    }//GEN-LAST:event_btnUploadImageActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLaggTillProdukt;
    private javax.swing.JButton btnUploadImage;
    private javax.swing.JCheckBox chkLagerfor;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblImage;
    private javax.swing.JTextArea txtBeskrivning;
    private java.awt.TextField txtPris;
    private java.awt.TextField txtProduktnamn;
    private java.awt.TextField txtVikt;
    // End of variables declaration//GEN-END:variables
}
