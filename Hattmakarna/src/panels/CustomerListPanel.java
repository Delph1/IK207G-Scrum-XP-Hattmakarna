
package panels;

import hattmakarna.MainWindow;
import static hattmakarna.Hattmakarna.dbm;
import models.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class CustomerListPanel extends javax.swing.JPanel {
//Klassens fält

    private MainWindow window;
    private ArrayList<Customer>allCustomers;
    private DefaultTableModel tableModel;

    //Klassens konstruktor
    public CustomerListPanel(MainWindow window) {
        // Vi tar emot och lagrar huvudfönstret som ett fält, då kan vi komma åt metoder som att byta panel
        this.window=window; 
        initComponents();
        tableModel = (DefaultTableModel) customerTable.getModel();
        updateCustomerList();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        NewCustomerBTN = new javax.swing.JButton();
        deleteCustomerButton = new javax.swing.JButton();
        showCustomerButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        customerTable = new javax.swing.JTable();
        createOrderForCustomerBTN = new javax.swing.JButton();

        setName(""); // NOI18N

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel1.setText("Kundlista");

        NewCustomerBTN.setText("Ny kund");
        NewCustomerBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NewCustomerBTNActionPerformed(evt);
            }
        });

        deleteCustomerButton.setText("Ta bort kund");
        deleteCustomerButton.setEnabled(false);
        deleteCustomerButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteCustomerButtonActionPerformed(evt);
            }
        });

        showCustomerButton.setText("Visa kund");
        showCustomerButton.setEnabled(false);
        showCustomerButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showCustomerButtonActionPerformed(evt);
            }
        });

        customerTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Kund-ID", "Förnamn", "Efternamn"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        customerTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                customerTableMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(customerTable);

        createOrderForCustomerBTN.setText("Skapa order för kund");
        createOrderForCustomerBTN.setEnabled(false);
        createOrderForCustomerBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createOrderForCustomerBTNActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 344, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(showCustomerButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(NewCustomerBTN, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(createOrderForCustomerBTN, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(deleteCustomerButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(100, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(NewCustomerBTN)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(showCustomerButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(createOrderForCustomerBTN)
                        .addGap(19, 19, 19)
                        .addComponent(deleteCustomerButton)))
                .addContainerGap(98, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void updateCustomerList() {
        allCustomers = dbm.getCustomers();
        for (Customer customer : allCustomers) {
            if (!customer.getFirstName().contentEquals("Borttagen")) {
                tableModel.addRow(new Object[]{customer.getId(), customer.getFirstName(), customer.getLastName(), customer.getStreetName(), customer.getPostalCode(), customer.getPostalCity(), customer.getState(), customer.getCountry()});
            }
        }
    }
    
    private void NewCustomerBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NewCustomerBTNActionPerformed
        window.showCustomerPanel(null);
    }//GEN-LAST:event_NewCustomerBTNActionPerformed

    private void deleteCustomerButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteCustomerButtonActionPerformed
        int selectedRowId = customerTable.getSelectedRow();
        int selectedCustomerId = (int) customerTable.getValueAt(selectedRowId, 0);
        Customer customer = dbm.getCustomer(selectedCustomerId);
        customer.setFirstName("Borttagen");
        customer.setLastName("");
        customer.setStreetName("");
        customer.setCountry("");
        customer.setPostalCity("");
        customer.setPostalCode("");
        customer.setState("");
        customer.setEmail(new ArrayList());
        customer.setPhoneNumbers(new ArrayList());
        dbm.updateCustomer(customer);
        tableModel.removeRow(selectedRowId);
        showCustomerButton.setEnabled(false);
        createOrderForCustomerBTN.setEnabled(false);
        deleteCustomerButton.setEnabled(false);
    }//GEN-LAST:event_deleteCustomerButtonActionPerformed

    private void showCustomerButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_showCustomerButtonActionPerformed
        int selectedRowId = customerTable.getSelectedRow();
        int selectedCustomerId = (int) customerTable.getValueAt(selectedRowId, 0);
        Customer customer = dbm.getCustomer(selectedCustomerId);
        window.showCustomerPanel(customer);
    }//GEN-LAST:event_showCustomerButtonActionPerformed

    private void createOrderForCustomerBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createOrderForCustomerBTNActionPerformed
        int selectedRowId = customerTable.getSelectedRow();
        int selectedCustomerId = (int) customerTable.getValueAt(selectedRowId, 0);
        window.showCustomerOrderPanel(selectedCustomerId);
    }//GEN-LAST:event_createOrderForCustomerBTNActionPerformed

    private void customerTableMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_customerTableMousePressed
       showCustomerButton.setEnabled(true);
       createOrderForCustomerBTN.setEnabled(true);
       deleteCustomerButton.setEnabled(true);
    }//GEN-LAST:event_customerTableMousePressed

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton NewCustomerBTN;
    private javax.swing.JButton createOrderForCustomerBTN;
    private javax.swing.JTable customerTable;
    private javax.swing.JButton deleteCustomerButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton showCustomerButton;
    // End of variables declaration//GEN-END:variables
}