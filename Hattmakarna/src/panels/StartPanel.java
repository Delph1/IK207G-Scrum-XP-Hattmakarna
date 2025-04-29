package panels;

import static hattmakarna.Hattmakarna.dbm;
import hattmakarna.MainWindow;
import hattmakarna.ModularWindow;
import java.util.*;
import javax.swing.table.DefaultTableModel;
import java.time.LocalDate;
import javax.swing.JOptionPane;
import models.Customer;
import utils.DatePicker;
import models.Order;
import models.OrderLine;

public class StartPanel extends javax.swing.JPanel {

    private MainWindow window;
    private ArrayList<Customer> allCustomers;
    private DefaultTableModel tableModel;
    private Object[][] orderlineData;
    private Order order;
    

    public StartPanel(MainWindow window) {
        this.window = window;
        initComponents();
        tableModel = (DefaultTableModel) customerTable.getModel();
        updateCustomerList();
        fyllDagensOrdrar();
        DatePicker.attachToTextField(window, tfStartDateOrderlines);
        DatePicker.attachToTextField(window, tfEndDateOrderlines);
        
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
        lblValkommen = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        customerTable = new javax.swing.JTable();
        NewCustomerBTN = new javax.swing.JButton();
        showCustomerButton = new javax.swing.JButton();
        createOrderForCustomerBTN = new javax.swing.JButton();
        deleteCustomerButton = new javax.swing.JButton();
        lblValkommen1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        lblStartDateOrderlines = new javax.swing.JLabel();
        tfStartDateOrderlines = new javax.swing.JTextField();
        lblEndDateOrderlines = new javax.swing.JLabel();
        tfEndDateOrderlines = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblOrderlinesBetweenDates = new javax.swing.JTable();
        lblSearchOrderlinesDate = new javax.swing.JLabel();
        btnSearchOrderlinesBetwDates = new javax.swing.JButton();

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

        lblStartDateOrderlines.setText("Startdatum");

        lblEndDateOrderlines.setText("Stoppdatum");

        tblOrderlinesBetweenDates.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Order-ID", "Orderrad-ID", "Produkt-ID", "Pris", "Kundgodkännande", "Beskrivning", "Status", "Leveransdatum"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblOrderlinesBetweenDates.setEnabled(false);
        jScrollPane2.setViewportView(tblOrderlinesBetweenDates);

        lblSearchOrderlinesDate.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblSearchOrderlinesDate.setText("Sök efter orderrader mellan två datum");

        btnSearchOrderlinesBetwDates.setText("Sök");
        btnSearchOrderlinesBetwDates.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchOrderlinesBetwDatesActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lblStartDateOrderlines, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfStartDateOrderlines, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblEndDateOrderlines, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(tfEndDateOrderlines, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(39, 39, 39)
                        .addComponent(btnSearchOrderlinesBetwDates))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 879, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblSearchOrderlinesDate, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblSearchOrderlinesDate)
                .addGap(12, 12, 12)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblStartDateOrderlines)
                    .addComponent(tfStartDateOrderlines, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblEndDateOrderlines)
                    .addComponent(tfEndDateOrderlines, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSearchOrderlinesBetwDates))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(scrollDagensOrder)
                    .addComponent(lblValkommen, javax.swing.GroupLayout.Alignment.LEADING))
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
                            .addComponent(deleteCustomerButton, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE))))
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
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(62, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

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

    private void btnSearchOrderlinesBetwDatesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchOrderlinesBetwDatesActionPerformed
        String startDate = tfStartDateOrderlines.getText();
        String endDate = tfEndDateOrderlines.getText();

        if (startDate.length() == 0 || endDate.length() == 0) {
            JOptionPane.showConfirmDialog(this, "Du måste fylla i ett datum i båda fälten.");
            return;
        }
        
        ArrayList<Order> ordersList = new ArrayList<>();
        ordersList = dbm.getOrdersBetweenDates(startDate, endDate);
        System.out.println("This is the ordersList" + ordersList);
        if (ordersList == null) {
            JOptionPane.showMessageDialog(this, "Inga ordrar hittades mellan dessa datum.");
            return;
        }
        
        ArrayList<OrderLine> orderlineList = new ArrayList<>(); 
        for(Order order : ordersList) {
            int order_id = order.getId();
            ArrayList<OrderLine> result = dbm.getOrderlines(order_id);
            orderlineList.addAll(result);
        }
        
        String[] columns = {"Order-ID", "Orderrad-ID", "Produkt-ID", "Pris", "Kundgodkännande", "Beskrivning", "Status", "Leveransdatum"};
        DefaultTableModel orderlineModel = new DefaultTableModel();
        orderlineModel.addColumn(columns[0]);
        orderlineModel.addColumn(columns[1]);
        orderlineModel.addColumn(columns[2]);
        orderlineModel.addColumn(columns[3]);
        orderlineModel.addColumn(columns[4]);
        orderlineModel.addColumn(columns[5]);
        orderlineModel.addColumn(columns[6]);
        orderlineModel.addColumn(columns[7]);
        
        Object[][] orderlineData = new Object[orderlineList.size()][8];
        
        for (int i = 0; i < orderlineList.size(); i++) {
            OrderLine orderline = orderlineList.get(i);
            orderlineData[i][0] = orderline.getOrderId();
            orderlineData[i][1] = orderline.getOrderLineId();
            orderlineData[i][2] = orderline.getProductId();
            orderlineData[i][3] = orderline.getPrice();
            orderlineData[i][4] = orderline.getCustomerApproval();
            orderlineData[i][5] = orderline.getDescription();
            orderlineData[i][6] = orderline.getHatStatus();
            orderlineData[i][7] = orderline.getDeliveryDate();
            orderlineModel.addRow(orderlineData[i]);
        };
        
        tblOrderlinesBetweenDates.setModel(orderlineModel);
        
    }//GEN-LAST:event_btnSearchOrderlinesBetwDatesActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton NewCustomerBTN;
    private javax.swing.JButton btnSearchOrderlinesBetwDates;
    private javax.swing.JButton createOrderForCustomerBTN;
    private javax.swing.JTable customerTable;
    private javax.swing.JButton deleteCustomerButton;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblEndDateOrderlines;
    private javax.swing.JLabel lblSearchOrderlinesDate;
    private javax.swing.JLabel lblStartDateOrderlines;
    private javax.swing.JLabel lblValkommen;
    private javax.swing.JLabel lblValkommen1;
    private javax.swing.JScrollPane scrollDagensOrder;
    private javax.swing.JButton showCustomerButton;
    private javax.swing.JTable tblDagensOrder;
    private javax.swing.JTable tblOrderlinesBetweenDates;
    private javax.swing.JTextField tfEndDateOrderlines;
    private javax.swing.JTextField tfStartDateOrderlines;
    // End of variables declaration//GEN-END:variables
}
