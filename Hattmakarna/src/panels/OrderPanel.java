package panels;

import static hattmakarna.Hattmakarna.dbm;
import hattmakarna.MainWindow;
import javax.swing.table.DefaultTableModel;
import javax.swing.JOptionPane;
//import models.*;

import models.Order;
import models.OrderLine;

public class OrderPanel extends javax.swing.JPanel {

    private MainWindow window;
    private Order order;
    //private OrderLine orderline;
    private DefaultTableModel tableModelOrderline;

    public OrderPanel(MainWindow window, int orderId) {

        // Vi tar emot och lagrar huvudfönstret som ett fält, då kan vi komma åt metoder som att byta panel
        this.window = window;
        initComponents();
        Order order = dbm.getOrder(orderId);
    }

    public OrderPanel(MainWindow window) {
        // Vi tar emot och lagrar huvudfönstret som ett fält, då kan vi komma åt metoder som att byta panel
        this.window = window;
        initComponents();
        order = dbm.createOrder();
        tfOrderlineID.setText("0");
        tfOrderID.setText("" + order.getId());
        tfOrderDate.setText("" + order.getOrder_date());
        tfOrderStatus.setText(order.getOrder_status());
        tfShippingCostOrder.setText("" + order.getShippingCost());
        lblOrderlineID.setVisible(false);
        tfOrderlineID.setVisible(false);
        tableModelOrderline = (DefaultTableModel) tblOrderline.getModel();
        tblOrderline.getColumnModel().getColumn(0).setMinWidth(0);
        tblOrderline.getColumnModel().getColumn(0).setMaxWidth(0);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblOrderPanel = new javax.swing.JLabel();
        pnlNewOrder = new javax.swing.JPanel();
        lblOrderID = new javax.swing.JLabel();
        lblCustomerID = new javax.swing.JLabel();
        tfOrderID = new javax.swing.JTextField();
        tfCustomerID = new javax.swing.JTextField();
        lblOrderDate = new javax.swing.JLabel();
        tfOrderDate = new javax.swing.JTextField();
        lblOrderStatus = new javax.swing.JLabel();
        tfOrderStatus = new javax.swing.JTextField();
        lblAddOrderline = new javax.swing.JLabel();
        lblOrderlineID = new javax.swing.JLabel();
        lblProductID = new javax.swing.JLabel();
        tfOrderlineID = new javax.swing.JTextField();
        tfProductID = new javax.swing.JTextField();
        lblPrice = new javax.swing.JLabel();
        tfPrice = new javax.swing.JTextField();
        lblCustomerApproval = new javax.swing.JLabel();
        lblDescription = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        taDescription = new javax.swing.JTextArea();
        btnAddOrderline = new javax.swing.JButton();
        cboxCustomerApproval = new javax.swing.JCheckBox();
        lblExpressOrder = new javax.swing.JLabel();
        cboxExpressOrder = new javax.swing.JCheckBox();
        lblShippingCostOrder = new javax.swing.JLabel();
        tfShippingCostOrder = new javax.swing.JTextField();
        btnRemoveOrderline = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblOrderline = new javax.swing.JTable();
        btnSaveOrder = new javax.swing.JButton();

        setName(""); // NOI18N

        lblOrderPanel.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblOrderPanel.setText("Beställningspanel");

        pnlNewOrder.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Ny order", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12))); // NOI18N
        pnlNewOrder.setOpaque(false);

        lblOrderID.setText("Order-ID");

        lblCustomerID.setText("Kund-ID");

        tfOrderID.setEnabled(false);

        tfCustomerID.setEnabled(false);

        lblOrderDate.setText("Orderdatum");

        tfOrderDate.setEnabled(false);

        lblOrderStatus.setText("Orderstatus");

        tfOrderStatus.setEnabled(false);

        lblAddOrderline.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblAddOrderline.setText("Lägg till orderrad");

        lblOrderlineID.setText("Orderrad-ID");

        lblProductID.setText("Produkt-ID");

        tfOrderlineID.setEditable(false);

        lblPrice.setText("Pris");

        lblCustomerApproval.setText("Kundgodkännande");

        lblDescription.setText("Beskrivning");

        taDescription.setColumns(20);
        taDescription.setRows(5);
        jScrollPane1.setViewportView(taDescription);

        btnAddOrderline.setText("Lägg till");
        btnAddOrderline.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddOrderlineActionPerformed(evt);
            }
        });

        cboxCustomerApproval.setText("Godkänt");

        lblExpressOrder.setText("Express");

        cboxExpressOrder.setText("Expressbeställning");

        lblShippingCostOrder.setText("Fraktkostnad");

        javax.swing.GroupLayout pnlNewOrderLayout = new javax.swing.GroupLayout(pnlNewOrder);
        pnlNewOrder.setLayout(pnlNewOrderLayout);
        pnlNewOrderLayout.setHorizontalGroup(
            pnlNewOrderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlNewOrderLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlNewOrderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlNewOrderLayout.createSequentialGroup()
                        .addGroup(pnlNewOrderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblDescription, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblCustomerApproval, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 103, Short.MAX_VALUE)
                            .addComponent(lblPrice, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblProductID, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblAddOrderline, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblOrderlineID, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlNewOrderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tfOrderlineID)
                            .addComponent(tfProductID)
                            .addComponent(tfPrice)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 221, Short.MAX_VALUE)
                            .addGroup(pnlNewOrderLayout.createSequentialGroup()
                                .addComponent(cboxCustomerApproval)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(pnlNewOrderLayout.createSequentialGroup()
                        .addGroup(pnlNewOrderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(lblShippingCostOrder, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 89, Short.MAX_VALUE)
                            .addComponent(lblOrderStatus, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblOrderDate, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblCustomerID, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblOrderID, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblExpressOrder, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(pnlNewOrderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tfOrderID)
                            .addComponent(tfCustomerID)
                            .addComponent(tfOrderDate)
                            .addComponent(tfOrderStatus)
                            .addGroup(pnlNewOrderLayout.createSequentialGroup()
                                .addComponent(cboxExpressOrder)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(tfShippingCostOrder)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlNewOrderLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnAddOrderline, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        pnlNewOrderLayout.setVerticalGroup(
            pnlNewOrderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlNewOrderLayout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(pnlNewOrderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblOrderID)
                    .addComponent(tfOrderID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlNewOrderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCustomerID)
                    .addComponent(tfCustomerID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlNewOrderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblOrderDate)
                    .addComponent(tfOrderDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlNewOrderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblOrderStatus)
                    .addComponent(tfOrderStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnlNewOrderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblExpressOrder)
                    .addComponent(cboxExpressOrder))
                .addGap(18, 18, 18)
                .addGroup(pnlNewOrderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblShippingCostOrder)
                    .addComponent(tfShippingCostOrder, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addComponent(lblAddOrderline)
                .addGap(18, 18, 18)
                .addGroup(pnlNewOrderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblOrderlineID)
                    .addComponent(tfOrderlineID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlNewOrderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblProductID)
                    .addComponent(tfProductID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlNewOrderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfPrice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblPrice))
                .addGap(18, 18, 18)
                .addGroup(pnlNewOrderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCustomerApproval)
                    .addComponent(cboxCustomerApproval))
                .addGap(18, 18, 18)
                .addGroup(pnlNewOrderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblDescription)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 41, Short.MAX_VALUE)
                .addComponent(btnAddOrderline)
                .addGap(23, 23, 23))
        );

        btnRemoveOrderline.setText("Ta bort");
        btnRemoveOrderline.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoveOrderlineActionPerformed(evt);
            }
        });

        tblOrderline.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Orderrad-ID", "Produkt-ID", "Pris", "Kundgodkännande", "Beskrivning"
            }
        ));
        jScrollPane2.setViewportView(tblOrderline);
        if (tblOrderline.getColumnModel().getColumnCount() > 0) {
            tblOrderline.getColumnModel().getColumn(0).setResizable(false);
            tblOrderline.getColumnModel().getColumn(0).setHeaderValue("Orderrad-ID");
        }

        btnSaveOrder.setText("Spara");
        btnSaveOrder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveOrderActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnSaveOrder)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(pnlNewOrder, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblOrderPanel))
                        .addGap(36, 36, 36)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnRemoveOrderline)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 536, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(29, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblOrderPanel)
                .addGap(18, 18, 18)
                .addComponent(pnlNewOrder, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(44, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(btnRemoveOrderline)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 490, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnSaveOrder)
                .addGap(64, 64, 64))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddOrderlineActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddOrderlineActionPerformed
        int orderLine_id = Integer.parseInt(tfOrderlineID.getText());
        int product_id = Integer.parseInt(tfProductID.getText());
        int price = Integer.parseInt(tfPrice.getText());
        boolean customer_approval = cboxCustomerApproval.isSelected();
        String description = taDescription.getText();

        DefaultTableModel tableModelOrderline = (DefaultTableModel) tblOrderline.getModel();
        tableModelOrderline.addRow(new Object[]{orderLine_id, product_id, price, customer_approval, description});

        tfProductID.setText("");
        tfPrice.setText("");
        taDescription.setText("");
    }//GEN-LAST:event_btnAddOrderlineActionPerformed

    private void btnSaveOrderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveOrderActionPerformed
        DefaultTableModel tableModelOrderline = (DefaultTableModel) tblOrderline.getModel();

        for (int i = 0; i < tableModelOrderline.getRowCount(); i++) {
            int null_orderId = 0;

            if (tableModelOrderline.getValueAt(i, 0).equals(null_orderId)) {
                OrderLine orderLine = dbm.createOrderLine(order.getId());
                tableModelOrderline.setValueAt(orderLine.getOrderLineId(), i, 0);
                int prod_id = Integer.parseInt(tableModelOrderline.getValueAt(i, 1).toString());
                int price = Integer.parseInt(tableModelOrderline.getValueAt(i, 2).toString());
                boolean customer_approval = (Boolean) tableModelOrderline.getValueAt(i, 3);
                String description = tableModelOrderline.getValueAt(i, 4).toString();

                orderLine.setProductId(prod_id);
                orderLine.setPrice(price);
                orderLine.setCustomerApproval(customer_approval);
                orderLine.setDescription(description);

                dbm.updateOrderLine(orderLine);
            }
        }

    }//GEN-LAST:event_btnSaveOrderActionPerformed

    private void btnRemoveOrderlineActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoveOrderlineActionPerformed
        int selectedTableRowID = tblOrderline.getSelectedRow();
        int selectedOrderlineID = (int) tblOrderline.getValueAt(selectedTableRowID, 0);

        if (selectedTableRowID < 0) {
            JOptionPane.showMessageDialog(this,
                    "No row is selected, please select one row",
                    "Select row",
                    JOptionPane.ERROR_MESSAGE);
        } else {
            DefaultTableModel OrderLine = (DefaultTableModel) tblOrderline.getModel();
            dbm.deleteOrderLine(selectedOrderlineID);
            System.out.println("Orderline " + selectedOrderlineID + " has been deleted");
            OrderLine.removeRow(selectedTableRowID);
        }

    }//GEN-LAST:event_btnRemoveOrderlineActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddOrderline;
    private javax.swing.JButton btnRemoveOrderline;
    private javax.swing.JButton btnSaveOrder;
    private javax.swing.JCheckBox cboxCustomerApproval;
    private javax.swing.JCheckBox cboxExpressOrder;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblAddOrderline;
    private javax.swing.JLabel lblCustomerApproval;
    private javax.swing.JLabel lblCustomerID;
    private javax.swing.JLabel lblDescription;
    private javax.swing.JLabel lblExpressOrder;
    private javax.swing.JLabel lblOrderDate;
    private javax.swing.JLabel lblOrderID;
    private javax.swing.JLabel lblOrderPanel;
    private javax.swing.JLabel lblOrderStatus;
    private javax.swing.JLabel lblOrderlineID;
    private javax.swing.JLabel lblPrice;
    private javax.swing.JLabel lblProductID;
    private javax.swing.JLabel lblShippingCostOrder;
    private javax.swing.JPanel pnlNewOrder;
    private javax.swing.JTextArea taDescription;
    private javax.swing.JTable tblOrderline;
    private javax.swing.JTextField tfCustomerID;
    private javax.swing.JTextField tfOrderDate;
    private javax.swing.JTextField tfOrderID;
    private javax.swing.JTextField tfOrderStatus;
    private javax.swing.JTextField tfOrderlineID;
    private javax.swing.JTextField tfPrice;
    private javax.swing.JTextField tfProductID;
    private javax.swing.JTextField tfShippingCostOrder;
    // End of variables declaration//GEN-END:variables
}
