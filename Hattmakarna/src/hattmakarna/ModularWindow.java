package hattmakarna;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JPanel;
import models.Product;
import panels.*;
import panels.modular.ProductPanel;
import panels.modular.UploadImages;
import panels.modular.ModularSpecialOrder;

public class ModularWindow extends javax.swing.JDialog {

    private JPanel panel;
    
    public ModularWindow(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }

    public void specialOrder(OrderPanel order) {
        showPanel(new ModularSpecialOrder(this, order));
    }
    
    public void modifyHat(Product product, OrderPanel order) {
        showPanel(new ModularSpecialOrder(this, product, order));
    }
    
    // Intern metod för att visa ett panelobjekt i vår panel
    private void showPanel(JPanel newPanel) {
        getContentPane().removeAll();
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(newPanel, BorderLayout.CENTER);
        pack();
    }
    
    public void newProduct() {
        showPanel(new ProductPanel(this));
    }
    
    public void editProduct(Product product) {
        showPanel(new ProductPanel(this, product));
    }
    
    public void uploadImages(int productId) {
        showPanel(new UploadImages(this, productId));
    }
    
    // Intern metod för att "måla" en panel
    private void redrawCurrentPanel() {
        panel.revalidate();
        panel.repaint();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setSize(new java.awt.Dimension(620, 535));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 600, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 600, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
