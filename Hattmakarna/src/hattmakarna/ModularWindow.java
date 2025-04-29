package hattmakarna;

import panels.modular.CustomerPanel;
import panels.modular.ChangePasswordPanel;
import panels.modular.MyProfilePanel;
import java.awt.BorderLayout;
import java.awt.Frame;
import javax.swing.JPanel;
import models.Customer;
import models.Product;
import panels.*;
import panels.modular.ProductPanel;
import panels.modular.UploadImagesPanel;
import panels.modular.SpecialOrderPanel;

public class ModularWindow extends javax.swing.JDialog {

    private Frame window;
    
    public ModularWindow(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        this.window = parent;
        initComponents();
    }

    public void specialOrder(OrderPanel order) {
        showPanel(new SpecialOrderPanel(this, order));
    }
    
    public void modifyHat(Product product, OrderPanel order) {
        showPanel(new SpecialOrderPanel(this, product, order));
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
        showPanel(new UploadImagesPanel(this, productId));
    }

    public void showMyProfile() {
        showPanel(new MyProfilePanel(this, window));
    }    

    public void showandraLosen() {
        showPanel(new ChangePasswordPanel(this, window));
    }

    public void showCustomerPanel(Customer customer) {
       showPanel(new CustomerPanel(this,customer));
    }
         

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(650, 650));
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
