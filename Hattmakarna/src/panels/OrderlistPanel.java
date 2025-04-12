
package panels;

import hattmakarna.MainWindow;
import static hattmakarna.Hattmakarna.dbm;
import models.*;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;


public class OrderlistPanel extends javax.swing.JPanel {
//Fält

    private ArrayList<Order> allOrders;
    private MainWindow window;
    private DefaultTableModel tableModel;
    // private DefaultListModel<String> orderListModel;

//Konstruktor
    public OrderlistPanel(MainWindow window) {
        // Vi tar emot och lagrar huvudfönstret som ett fält, då kan vi komma åt metoder som att byta panel
        this.window = window;
        initComponents();
//        dbm.createOrder();

        tableModel = (DefaultTableModel) orderTable.getModel();
        allOrders = dbm.getOrders();
        for (Order order : allOrders) {
            tableModel.addRow(new Object[]{order.getId(), order.getCustomer_id(), order.getOrder_date(), order.getOrder_status(), order.isExpress(), order.getShippingCost()});

            System.out.println(order.getId());

        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        newOrderButton = new javax.swing.JButton();
        showOrderButton = new javax.swing.JButton();
        deleteOrderButton = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        orderTable = new javax.swing.JTable();

        setName(""); // NOI18N

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel1.setText("Beställningslista");

        newOrderButton.setText("Ny beställning");
        newOrderButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newOrderButtonActionPerformed(evt);
            }
        });

        showOrderButton.setText("Visa order");
        showOrderButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showOrderButtonActionPerformed(evt);
            }
        });

        deleteOrderButton.setText("Radera");
        deleteOrderButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteOrderButtonActionPerformed(evt);
            }
        });

        orderTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "OrderID", "KundID", "Datum", "Orderstatus", "Express", "Fraktkostnad"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Boolean.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(orderTable);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 468, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(newOrderButton)
                    .addComponent(showOrderButton)
                    .addComponent(deleteOrderButton))
                .addGap(68, 68, 68))
            .addGroup(layout.createSequentialGroup()
                .addGap(269, 269, 269)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(9, 9, 9)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(newOrderButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(showOrderButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(deleteOrderButton))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 407, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(229, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void newOrderButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newOrderButtonActionPerformed
        window.showOrderPanel();
    }//GEN-LAST:event_newOrderButtonActionPerformed

    private void deleteOrderButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteOrderButtonActionPerformed

        int selectedRowId = orderTable.getSelectedRow();
        System.out.println(selectedRowId);

        int selectedOrderId = (int) orderTable.getValueAt(selectedRowId, 0);
        System.out.println("OrderID:" + selectedOrderId);

        if (dbm.deleteOrder(selectedOrderId)) {
            tableModel.removeRow(selectedRowId);
            System.out.println("YAY deleted");
        }


    }//GEN-LAST:event_deleteOrderButtonActionPerformed

    private void showOrderButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_showOrderButtonActionPerformed
        int selectedRowId = orderTable.getSelectedRow();
        int selectedOrderId = (int) orderTable.getValueAt(selectedRowId, 0);
        window.showOrderPanel(selectedOrderId);
    }//GEN-LAST:event_showOrderButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton deleteOrderButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton newOrderButton;
    private javax.swing.JTable orderTable;
    private javax.swing.JButton showOrderButton;
    // End of variables declaration//GEN-END:variables
}
