package panels;

import hattmakarna.MainWindow;
import static hattmakarna.Hattmakarna.dbm;
import hattmakarna.ModularWindow;
import java.util.*;
import javax.swing.table.DefaultTableModel;
import java.time.LocalDate;
import models.Customer;

public class StartPanel extends javax.swing.JPanel {
    private MainWindow window;
    private int userId = 1;
    private ArrayList<Customer>allCustomers;
    private DefaultTableModel tableModel;


    public StartPanel(MainWindow window) {
        this.window = window;
        this.userId = userId;
        initComponents(); 
        tableModel = (DefaultTableModel) customerTable.getModel();
        updateCustomerList();
        fyllDagensOrdrar();
    }

    private void fyllDagensOrdrar() {
        String idag = java.time.LocalDate.now().toString();
        ArrayList<HashMap<String, String>> dagensOrdrar = dbm.getOrdersByDeliveryDate(LocalDate.parse(idag));

        DefaultTableModel model = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }

        };
        model.addColumn("Order ID");
        model.addColumn("Kund ID");
        model.addColumn("Status");
        model.addColumn("Express");
        model.addColumn("Fraktkostnad");
        model.addColumn("Orderdatum");

        for (HashMap<String, String> row : dagensOrdrar) {
            model.addRow(new Object[]{
                row.get("order_id"),
                row.get("customer_id"),
                row.get("order_status"),
                row.get("express").equals("1") ? "Ja" : "Nej",
                row.get("shipping_cost"),
                row.get("order_date")
            });
        }
        tblDagensOrder.setModel(model);
    }
    
    private void updateCustomerList() {
        allCustomers = dbm.getCustomers();
        for (Customer customer : allCustomers) {
            if (!customer.getFirstName().contentEquals("Borttagen")) {
                tableModel.addRow(new Object[]{customer.getId(), customer.getFirstName(), customer.getLastName(), customer.getStreetName(), customer.getPostalCode(), customer.getPostalCity(), customer.getState(), customer.getCountry()});
            }
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        scrollDagensOrder = new javax.swing.JScrollPane();
        tblDagensOrder = new javax.swing.JTable();
        btnSchema = new javax.swing.JButton();
        lblValkommen = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        customerTable = new javax.swing.JTable();
        NewCustomerBTN = new javax.swing.JButton();
        showCustomerButton = new javax.swing.JButton();
        createOrderForCustomerBTN = new javax.swing.JButton();
        deleteCustomerButton = new javax.swing.JButton();
        lblValkommen1 = new javax.swing.JLabel();

        setName(""); // NOI18N

        tblDagensOrder.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        scrollDagensOrder.setViewportView(tblDagensOrder);

        btnSchema.setText("Gå till Att göra");
        btnSchema.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSchemaActionPerformed(evt);
            }
        });

        lblValkommen.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblValkommen.setText("Mina order");

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

        NewCustomerBTN.setText("Ny kund");
        NewCustomerBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NewCustomerBTNActionPerformed(evt);
            }
        });

        showCustomerButton.setText("Visa kund");
        showCustomerButton.setEnabled(false);
        showCustomerButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showCustomerButtonActionPerformed(evt);
            }
        });

        createOrderForCustomerBTN.setText("Skapa order för kund");
        createOrderForCustomerBTN.setEnabled(false);
        createOrderForCustomerBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createOrderForCustomerBTNActionPerformed(evt);
            }
        });

        deleteCustomerButton.setText("Ta bort kund");
        deleteCustomerButton.setEnabled(false);
        deleteCustomerButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteCustomerButtonActionPerformed(evt);
            }
        });

        lblValkommen1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblValkommen1.setText("Kunder");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnSchema)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(scrollDagensOrder, javax.swing.GroupLayout.PREFERRED_SIZE, 377, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblValkommen))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblValkommen1)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 344, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(showCustomerButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(NewCustomerBTN, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(createOrderForCustomerBTN, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(deleteCustomerButton, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblValkommen)
                    .addComponent(lblValkommen1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(scrollDagensOrder, javax.swing.GroupLayout.DEFAULT_SIZE, 274, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(NewCustomerBTN)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(showCustomerButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(createOrderForCustomerBTN)
                        .addGap(19, 19, 19)
                        .addComponent(deleteCustomerButton))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(btnSchema)
                .addContainerGap(103, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnSchemaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSchemaActionPerformed
        window.showSchemaPanel(userId);
    }//GEN-LAST:event_btnSchemaActionPerformed

    private void customerTableMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_customerTableMousePressed
        showCustomerButton.setEnabled(true);
        createOrderForCustomerBTN.setEnabled(true);
        deleteCustomerButton.setEnabled(true);
    }//GEN-LAST:event_customerTableMousePressed

    private void NewCustomerBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NewCustomerBTNActionPerformed
        ModularWindow modularWindow = new ModularWindow(window, true);
        modularWindow.showCustomerPanel(null);
        modularWindow.setVisible(true);
        modularWindow.setAlwaysOnTop(true);
    }//GEN-LAST:event_NewCustomerBTNActionPerformed

    private void showCustomerButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_showCustomerButtonActionPerformed
        int selectedRowId = customerTable.getSelectedRow();
        int selectedCustomerId = (int) customerTable.getValueAt(selectedRowId, 0);
        Customer customer = dbm.getCustomer(selectedCustomerId);
        ModularWindow modularWindow = new ModularWindow(window, true);
        modularWindow.showCustomerPanel(customer);
        modularWindow.setVisible(true);
        modularWindow.setAlwaysOnTop(true);
    }//GEN-LAST:event_showCustomerButtonActionPerformed

    private void createOrderForCustomerBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createOrderForCustomerBTNActionPerformed
        int selectedRowId = customerTable.getSelectedRow();
        int selectedCustomerId = (int) customerTable.getValueAt(selectedRowId, 0);
        window.showCustomerOrderPanel(selectedCustomerId);
    }//GEN-LAST:event_createOrderForCustomerBTNActionPerformed

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

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton NewCustomerBTN;
    private javax.swing.JButton btnSchema;
    private javax.swing.JButton createOrderForCustomerBTN;
    private javax.swing.JTable customerTable;
    private javax.swing.JButton deleteCustomerButton;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblValkommen;
    private javax.swing.JLabel lblValkommen1;
    private javax.swing.JScrollPane scrollDagensOrder;
    private javax.swing.JButton showCustomerButton;
    private javax.swing.JTable tblDagensOrder;
    // End of variables declaration//GEN-END:variables
}
