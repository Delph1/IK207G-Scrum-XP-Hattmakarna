package hattmakarna;
import panels.modular.ProductPanel;
import java.awt.Component;
import panels.*; // Hämtar alla paneler
import javax.swing.JPanel;
import static hattmakarna.Hattmakarna.dbm;
import java.awt.print.PrinterException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.*;

public class MainWindow extends javax.swing.JFrame {
    public MainWindow() {
        initComponents();

        // Börjar med att visa startpanelen
       showStartPanel();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        mainPanel = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        startButton = new javax.swing.JButton();
        ordersButton = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        btnPrint = new javax.swing.JButton();
        schemaButton = new javax.swing.JButton();
        btnMaterials = new javax.swing.JButton();
        statisticsBTN = new javax.swing.JButton();
        customerListBTN = new javax.swing.JButton();
        ProductListBTN = new javax.swing.JButton();
        logOut = new javax.swing.JButton();
        minProfilBTN = new javax.swing.JButton();
        btnImageManager = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel1.setText("Hattmakarna 0.1");

        mainPanel.setBackground(new java.awt.Color(204, 204, 204));
        mainPanel.setAlignmentX(0.0F);
        mainPanel.setAlignmentY(0.0F);
        mainPanel.setMaximumSize(new java.awt.Dimension(400, 300));
        mainPanel.setMinimumSize(new java.awt.Dimension(400, 300));
        mainPanel.setPreferredSize(new java.awt.Dimension(400, 300));
        mainPanel.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                mainPanelComponentResized(evt);
            }
        });
        mainPanel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 0, 0));

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Panelholder");
        mainPanel.add(jLabel2);

        startButton.setText("Start");
        startButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startButtonActionPerformed(evt);
            }
        });

        ordersButton.setText("Beställningar");
        ordersButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ordersButtonActionPerformed(evt);
            }
        });

        btnPrint.setText("Print");
        btnPrint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrintActionPerformed(evt);
            }
        });

        schemaButton.setText("Schema");
        schemaButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                schemaButtonActionPerformed(evt);
            }
        });

        btnMaterials.setText("Materiallista");
        btnMaterials.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMaterialsActionPerformed(evt);
            }
        });

        statisticsBTN.setText("Statistik");
        statisticsBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                statisticsBTNActionPerformed(evt);
            }
        });

        customerListBTN.setText("Kunder");
        customerListBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                customerListBTNActionPerformed(evt);
            }
        });

        ProductListBTN.setText("Produkter");
        ProductListBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ProductListBTNActionPerformed(evt);
            }
        });

        logOut.setText("Logga Ut");
        logOut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logOutActionPerformed(evt);
            }
        });

        minProfilBTN.setText("Min Profil");
        minProfilBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                minProfilBTNActionPerformed(evt);
            }
        });

        btnImageManager.setText("Bilder");
        btnImageManager.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImageManagerActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(minProfilBTN)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(logOut)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnPrint))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(startButton)
                        .addGap(18, 18, 18)
                        .addComponent(ordersButton)
                        .addGap(18, 18, 18)
                        .addComponent(btnMaterials)
                        .addGap(18, 18, 18)
                        .addComponent(schemaButton, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(statisticsBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(customerListBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(ProductListBTN)
                        .addGap(18, 18, 18)
                        .addComponent(btnImageManager)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addComponent(jSeparator1)
            .addGroup(layout.createSequentialGroup()
                .addComponent(mainPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 1100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnPrint)
                        .addComponent(logOut))
                    .addComponent(minProfilBTN))
                .addGap(4, 4, 4)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(startButton)
                    .addComponent(ordersButton)
                    .addComponent(schemaButton)
                    .addComponent(btnMaterials)
                    .addComponent(statisticsBTN)
                    .addComponent(customerListBTN)
                    .addComponent(ProductListBTN)
                    .addComponent(btnImageManager))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(mainPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 700, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void startButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startButtonActionPerformed
        // Visa startpanelen när man trycker på "Start" knappen
        showStartPanel();
    }//GEN-LAST:event_startButtonActionPerformed

    private void ordersButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ordersButtonActionPerformed
        // Visa beställningslista när man trycker på "Beställningar" knappen
        showOrderlistPanel();
    }//GEN-LAST:event_ordersButtonActionPerformed

    private void mainPanelComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_mainPanelComponentResized
        // Skalar nuvarande panel ifall fönster dras ut.
        redrawCurrentPanel();
    }//GEN-LAST:event_mainPanelComponentResized

    private void btnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrintActionPerformed
        Order order = dbm.getOrder(1);
        try {
            Print printOrder = new Print(order);
            printOrder.showQoute();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }//GEN-LAST:event_btnPrintActionPerformed

    private void schemaButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_schemaButtonActionPerformed
       //Visar schemasida 
       User user = Hattmakarna.currentUser;
       int user_id = user.getUserId();
       showSchemaPanel(user_id); 
    }//GEN-LAST:event_schemaButtonActionPerformed

    private void btnMaterialsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMaterialsActionPerformed
        showMaterialListPanel();
    }//GEN-LAST:event_btnMaterialsActionPerformed

    private void statisticsBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_statisticsBTNActionPerformed
        showOrderStatisticsPanel();        // TODO add your handling code here:
    }//GEN-LAST:event_statisticsBTNActionPerformed

    private void customerListBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_customerListBTNActionPerformed
        showCustomerListPanel();        
    }//GEN-LAST:event_customerListBTNActionPerformed

    private void ProductListBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ProductListBTNActionPerformed
        showProductListPanel();   
    }//GEN-LAST:event_ProductListBTNActionPerformed

    private void logOutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logOutActionPerformed
        Hattmakarna.currentUser = null;
        LoginWindow window = new LoginWindow();
        window.setVisible(true);
        this.dispose();

        // TODO add your handling code here:
    }//GEN-LAST:event_logOutActionPerformed

    private void minProfilBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_minProfilBTNActionPerformed
     showMinProfil();
        // TODO add your handling code here:
    }//GEN-LAST:event_minProfilBTNActionPerformed

    private void btnImageManagerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImageManagerActionPerformed
        showImageManagerPanel();
    }//GEN-LAST:event_btnImageManagerActionPerformed

    // Publik Metod för att skapa start-panelobjekt och anropa den interna metoden för att visa panelen
    public void showStartPanel() {
       showPanel(new StartPanel(this));
    }
    
    public void showMinProfil(){
    showPanel(new MinProfil(this));
    }
    
    // Publik Metod för att skapa start-panelobjekt och anropa den interna metoden för att visa panelen
    public void showOrderlistPanel() {
       showPanel(new OrderlistPanel(this));
    }
    
    public void showOrderPanel(int orderId) {
       showPanel(new OrderPanel(this, orderId));
    }
    
    public void showOrderPanel() {
       showPanel(new OrderPanel(this));
    }

    public void showSchemaPanel(int user_id) {
        showPanel(new SchemaPanel(this, user_id)); 
    }

    public void showMaterialListPanel() {
       showPanel(new MaterialListPanel(this));
    }
    
    public void showOrderStatisticsPanel() {
        showPanel(new Orderstatistics(this)); 
    }
    
    public void showCustomerPanel(Customer customer) {
       showPanel(new CustomerPanel(this,customer));
    }
    
    public void showCustomerListPanel() {
       showPanel(new CustomerListPanel(this));
    }
    
    public void showProductListPanel() {
        showPanel(new ProductListPanel(this));
    }
        
    private void showImageManagerPanel() {
        showPanel(new ImageManagerPanel(this));
    }

    // Intern metod för att visa ett panelobjekt i vår mainPanel
    private void showPanel(JPanel newPanel) {
        // Rensa mainPanel från tidigare tillagda paneler
        mainPanel.removeAll();

        // Lägg till den nya panelen
        mainPanel.add(newPanel);

        // Målar den nya panelen
        redrawCurrentPanel();
    }
    
    // Intern metod för att "måla" en panel efter mainPanel's storlek
    private void redrawCurrentPanel() {
        Component currentPanel = mainPanel.getComponent(0);
        currentPanel.setPreferredSize(mainPanel.getSize());
        currentPanel.revalidate();
        currentPanel.repaint();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ProductListBTN;
    private javax.swing.JButton btnImageManager;
    private javax.swing.JButton btnMaterials;
    private javax.swing.JButton btnPrint;
    private javax.swing.JButton customerListBTN;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JButton logOut;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JButton minProfilBTN;
    private javax.swing.JButton ordersButton;
    private javax.swing.JButton schemaButton;
    private javax.swing.JButton startButton;
    private javax.swing.JButton statisticsBTN;
    // End of variables declaration//GEN-END:variables

}
