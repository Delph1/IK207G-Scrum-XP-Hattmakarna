package hattmakarna;
import java.awt.Component;
import panels.*; // Hämtar alla paneler
import javax.swing.JPanel;
import static hattmakarna.Hattmakarna.dbm;
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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 600, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(startButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ordersButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnPrint)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addComponent(jSeparator1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(startButton)
                    .addComponent(ordersButton)
                    .addComponent(btnPrint))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 372, Short.MAX_VALUE))
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
            Print printOrder = new Print(order);        // TODO add your handling code here:
        } catch (IOException ex) {
            Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnPrintActionPerformed

    
    // Publik Metod för att skapa start-panelobjekt och anropa den interna metoden för att visa panelen
    public void showStartPanel() {
       showPanel(new StartPanel(this));
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
    private javax.swing.JButton btnPrint;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JButton ordersButton;
    private javax.swing.JButton startButton;
    // End of variables declaration//GEN-END:variables
}
