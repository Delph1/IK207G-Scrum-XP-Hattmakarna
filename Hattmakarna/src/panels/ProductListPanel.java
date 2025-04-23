package panels;

import hattmakarna.MainWindow;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import javax.swing.JOptionPane;
import static hattmakarna.Hattmakarna.dbm;
import hattmakarna.ModularWindow;
import java.awt.Image;
import java.util.Base64;
import javax.swing.ImageIcon;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import models.*;


public class ProductListPanel extends javax.swing.JPanel {
    private MainWindow window;

    public ProductListPanel(MainWindow window) {
            this.window = window;
            initComponents();
            fyllProduktTabell();
            skapaListener();
        }
    
    private void skapaListener() {
        tblProdukter.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    int markeradRad = tblProdukter.getSelectedRow();
                    if (markeradRad != -1) {
                        updateImage(markeradRad);
                    }
                }
            }
        });
    }
    
    private void updateImage(int rad) {
        Object product_id = tblProdukter.getValueAt(rad, 0);
        ProductImage image = dbm.getImage(Integer.parseInt(product_id.toString()));
        
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
    
    private void fyllProduktTabell() {
        try {
            ArrayList<Product> produkter = dbm.getProducts();

            DefaultTableModel model = new DefaultTableModel();
            model.addColumn("ID");
            model.addColumn("Namn");
            model.addColumn("Pris");
            model.addColumn("Vikt");
            model.addColumn("Beskrivning");
            model.addColumn("Status"); 

            for (Product p : produkter) {
                if (p.getStockItem()) {
                System.out.println(p.getProductName() + " - Status: " + (p.getDiscontinued() ? "Slutsåld" : "I lager"));
                model.addRow(new Object[]{
                        p.getProductId(),
                        p.getProductName(),
                        p.getPrice(),
                        p.getWeight(),
                        p.getDescription(),
                        p.getDiscontinued() ? "Slutsåld" : "I lager" 
                    });
                }
            }

            tblProdukter.setModel(model);  

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Kunde inte hämta produkter: " + e.getMessage());
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        NewProductBTN = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblProdukter = new javax.swing.JTable();
        btnSlutsald = new javax.swing.JButton();
        lblImage = new javax.swing.JLabel();

        setName(""); // NOI18N

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel1.setText("Produktlista");

        NewProductBTN.setText("Ny produkt");
        NewProductBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NewProductBTNActionPerformed(evt);
            }
        });

        tblProdukter.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tblProdukter);

        btnSlutsald.setText("Ändra lagerstatus");
        btnSlutsald.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSlutsaldActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnSlutsald)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(NewProductBTN))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(269, 269, 269)
                            .addComponent(jLabel1))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(40, 40, 40)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 520, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 47, Short.MAX_VALUE)
                .addComponent(lblImage, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(NewProductBTN)
                    .addComponent(btnSlutsald))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblImage, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(93, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void NewProductBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NewProductBTNActionPerformed
    ModularWindow modularWindow = new ModularWindow(window, true);
    modularWindow.newProduct();  
    modularWindow.setVisible(true);
    modularWindow.setAlwaysOnTop(true); 
    }//GEN-LAST:event_NewProductBTNActionPerformed

    private void btnSlutsaldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSlutsaldActionPerformed
    int rad = tblProdukter.getSelectedRow();
    if (rad == -1) {
        JOptionPane.showMessageDialog(this, "Välj en produkt först.");
        return;
    }

    int produktId = (int) tblProdukter.getValueAt(rad, 0);
    Product produkt = dbm.getProduct(produktId);

    boolean nyStatus = !produkt.getDiscontinued();  
    produkt.setDiscontinued(nyStatus);
    dbm.updateProduct(produkt);

    JOptionPane.showMessageDialog(this, nyStatus ? 
        "Produkten är nu markerad som slutsåld." : 
        "Produkten är nu tillgänglig igen.");

    fyllProduktTabell(); 
    }//GEN-LAST:event_btnSlutsaldActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton NewProductBTN;
    private javax.swing.JButton btnSlutsald;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblImage;
    private javax.swing.JTable tblProdukter;
    // End of variables declaration//GEN-END:variables
}
