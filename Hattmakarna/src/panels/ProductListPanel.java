package panels;

import hattmakarna.MainWindow;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import javax.swing.JOptionPane;
import static hattmakarna.Hattmakarna.dbm;
import hattmakarna.ModularWindow;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Base64;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import models.*;


public class ProductListPanel extends javax.swing.JPanel {
    private MainWindow window;
    private JButton btnRight, btnLeft;
    private ArrayList<ProductImage> images;
    private int imageIndex = 0;
    private JLabel imageLabel;
    private JLabel textLabel;

    public ProductListPanel(MainWindow window) {
            this.window = window;
            this.images = new ArrayList<>();
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
        ProductImage image = dbm.getProductImageForProduct(Integer.parseInt(product_id.toString()));
        
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

            tblProdukter.setModel(model);  

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Kunde inte hämta produkter: " + e.getMessage());
        }
    }
    
    private void swapPicture(int riktning, JFrame threedView) {
        imageIndex += riktning;

        if (imageIndex < 0) {
            imageIndex = images.size() - 1;
        } else if (imageIndex >= images.size()) {
            imageIndex = 0;
        }
        
         try {
            String base64Sträng = images.get(imageIndex).getBase64();
            byte[] bildBytes = Base64.getDecoder().decode(base64Sträng);
            BufferedImage bild = ImageIO.read(new ByteArrayInputStream(bildBytes));
            ImageIcon ikon = new ImageIcon(bild);
            imageLabel.setIcon(ikon);
            imageLabel.setText("");
            textLabel.setText(images.get(imageIndex).getDescription());
            threedView.pack();

        } catch (IOException ex) {
            imageLabel.setText("Kunde inte ladda bilden.");
            ex.printStackTrace();
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
        btnEditProduct = new javax.swing.JButton();
        btnViewLargeImage = new javax.swing.JButton();
        btn3dView = new javax.swing.JButton();
        btnUploadImages = new javax.swing.JButton();

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

        btnEditProduct.setText("Ändra produkt");
        btnEditProduct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditProductActionPerformed(evt);
            }
        });

        btnViewLargeImage.setText("Visa fullstor bild");
        btnViewLargeImage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnViewLargeImageActionPerformed(evt);
            }
        });

        btn3dView.setText("Visa 3D-vy");
        btn3dView.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn3dViewActionPerformed(evt);
            }
        });

        btnUploadImages.setText("Ladda upp bilder");
        btnUploadImages.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUploadImagesActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 520, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(269, 269, 269)
                                .addComponent(jLabel1))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnSlutsald)
                                .addGap(18, 18, 18)
                                .addComponent(btnEditProduct)))
                        .addGap(18, 18, 18)
                        .addComponent(NewProductBTN)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 42, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(lblImage, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(btnViewLargeImage)
                            .addGap(18, 18, 18)
                            .addComponent(btn3dView, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnUploadImages)
                        .addGap(123, 123, 123)))
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
                    .addComponent(btnSlutsald)
                    .addComponent(btnEditProduct))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblImage, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnViewLargeImage)
                            .addComponent(btn3dView))
                        .addGap(18, 18, 18)
                        .addComponent(btnUploadImages)))
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

    private void btnEditProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditProductActionPerformed
        int rad = tblProdukter.getSelectedRow();
        if (rad == -1) {
            JOptionPane.showMessageDialog(this, "Välj en produkt först.");
            return;
        }

        int produktId = (int) tblProdukter.getValueAt(rad, 0);
        Product produkt = dbm.getProduct(produktId);

        ModularWindow modularWindow = new ModularWindow(window, true);
        modularWindow.editProduct(produkt);  
        modularWindow.setVisible(true);
        modularWindow.setAlwaysOnTop(true); 

    }//GEN-LAST:event_btnEditProductActionPerformed

    private void btnViewLargeImageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnViewLargeImageActionPerformed
        int rad = tblProdukter.getSelectedRow();
        if (rad == -1) {
                JOptionPane.showMessageDialog(this, "Välj en produkt först.");
                return;
        }
        try {
           
            int productId = (int) tblProdukter.getValueAt(rad, 0);

            ProductImage image = dbm.getProductImageForProduct(productId);
            
            // Avkoda Base64 → byte[]
            byte[] bildBytes = Base64.getDecoder().decode(image.getBase64());
            BufferedImage buffImage = ImageIO.read(new ByteArrayInputStream(bildBytes));

            // Skapa fönster
            JFrame frame = new JFrame("Förhandsvisning av hatt");
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.setSize(600, 600); // eller anpassa till image.getWidth()

            // Visa bild i JLabel
            JLabel bildLabel = new JLabel(new ImageIcon(buffImage));
            bildLabel.setHorizontalAlignment(JLabel.CENTER);
            bildLabel.setVerticalAlignment(JLabel.CENTER);
            frame.add(new JScrollPane(bildLabel), BorderLayout.CENTER);

            frame.setLocationRelativeTo(null); // centrera
            frame.setVisible(true);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Kunde inte visa bilden.");
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnViewLargeImageActionPerformed
    private void btn3dViewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn3dViewActionPerformed
       
        int rad = tblProdukter.getSelectedRow();
        if (rad == -1) {
            JOptionPane.showMessageDialog(this, "Välj en produkt först.");
            return;
        }

        // Ladda bilder
        int productId = (int) tblProdukter.getValueAt(rad, 0);
        images = dbm.get3DImagesForProduct(productId);

        if (images.size() < 1) {
            JOptionPane.showMessageDialog(this, "Inga bilder för 3D-vyn hittades.");
            return;
        }
        
        JFrame threedView = new JFrame("Hattvisare");
        threedView.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        threedView.setSize(500, 500);
        threedView.setLayout(new BorderLayout());

        //Visar beskrivningen
        textLabel = new JLabel();
        textLabel.setVerticalAlignment(JLabel.NORTH);
        textLabel.setHorizontalAlignment(JLabel.CENTER);
        threedView.add(textLabel, BorderLayout.NORTH);

        // Bildvisare
        imageLabel = new JLabel();
        imageLabel.setHorizontalAlignment(JLabel.CENTER);
        threedView.add(imageLabel, BorderLayout.CENTER);
        

        // Panel med knappar
        JPanel knappPanel = new JPanel(new FlowLayout());

        JButton btnLeft = new JButton("←");
        JButton btnRight = new JButton("→");

        btnLeft.addActionListener(e -> swapPicture(-1, threedView));
        btnRight.addActionListener(e -> swapPicture(1, threedView));

        knappPanel.add(btnLeft);
        knappPanel.add(btnRight);

        threedView.add(knappPanel, BorderLayout.SOUTH);
        threedView.setLocationRelativeTo(null);
        threedView.setVisible(true);
        try {
            String base64Sträng = images.get(0).getBase64();
            byte[] bildBytes = Base64.getDecoder().decode(base64Sträng);
            BufferedImage bild = ImageIO.read(new ByteArrayInputStream(bildBytes));
            ImageIcon ikon = new ImageIcon(bild);
            imageLabel.setIcon(ikon);
            imageLabel.setText("");
            textLabel.setText(images.get(0).getDescription());
            threedView.pack();

        } catch (Exception ex) {
            imageLabel.setText("Kunde inte ladda bilden.");
            ex.printStackTrace();
        }

    }//GEN-LAST:event_btn3dViewActionPerformed

    private void btnUploadImagesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUploadImagesActionPerformed
        int rad = tblProdukter.getSelectedRow();
        if (rad == -1) {
            JOptionPane.showMessageDialog(this, "Välj en produkt först.");
            return;
        }
        int productId = (int) tblProdukter.getValueAt(rad, 0);

        ModularWindow modularWindow = new ModularWindow(window, true);
        modularWindow.uploadImages(productId);
        modularWindow.setVisible(true);
        modularWindow.setAlwaysOnTop(true);
    }//GEN-LAST:event_btnUploadImagesActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton NewProductBTN;
    private javax.swing.JButton btn3dView;
    private javax.swing.JButton btnEditProduct;
    private javax.swing.JButton btnSlutsald;
    private javax.swing.JButton btnUploadImages;
    private javax.swing.JButton btnViewLargeImage;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblImage;
    private javax.swing.JTable tblProdukter;
    // End of variables declaration//GEN-END:variables
}
