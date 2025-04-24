
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
        allCustomers= dbm.getCustomers();
       for (Customer customer : allCustomers) {
           tableModel.addRow(new Object[] {customer.getId(), customer.getFirstName(), customer.getLastName(), customer.getStreetName(), customer.getPostalCode(), customer.getPostalCity(), customer.getState(), customer.getCountry()});
      System.out.println(customer.getId());
       }
        
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
        deleteCustomerButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteCustomerButtonActionPerformed(evt);
            }
        });

        showCustomerButton.setText("Visa kund");

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
        jScrollPane1.setViewportView(customerTable);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 344, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(showCustomerButton, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(NewCustomerBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(deleteCustomerButton))))
                .addContainerGap(142, Short.MAX_VALUE))
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
                        .addGap(28, 28, 28)
                        .addComponent(showCustomerButton)
                        .addGap(26, 26, 26)
                        .addComponent(deleteCustomerButton)))
                .addContainerGap(98, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void NewCustomerBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NewCustomerBTNActionPerformed
        window.showCustomerPanel(null);
    }//GEN-LAST:event_NewCustomerBTNActionPerformed

    private void deleteCustomerButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteCustomerButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_deleteCustomerButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton NewCustomerBTN;
    private javax.swing.JTable customerTable;
    private javax.swing.JButton deleteCustomerButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton showCustomerButton;
    // End of variables declaration//GEN-END:variables
}
