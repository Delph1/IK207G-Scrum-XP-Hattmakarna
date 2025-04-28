package panels;

import hattmakarna.MainWindow;
import static hattmakarna.Hattmakarna.dbm;
import models.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;
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

    //Metod för att filtrera kunderna baserat på ID
    public void filtreraKund() {
        String input = valdKund.getText().trim();
        if (input.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Den kunden finns inte, välj en annan");
            return;
        }

        try {
            int kundId = Integer.parseInt(input); //Gör kundIdt till en int så man kan använda == försökte == men det måste ju vara int för att kunna jämföra så

            DefaultTableModel model = (DefaultTableModel) orderTable.getModel();
            model.setRowCount(0); // måste rensa jTablet för annars läggs ordarna till och inga försvinner 

            for (Order order : allOrders) {
                if (order.getCustomer_id() == kundId) {
                    model.addRow(new Object[]{
                        order.getId(),
                        order.getCustomer_id(),
                        order.getOrder_date(),
                        order.getOrder_status(),
                        order.isExpress(),
                        order.getShippingCost()
                    });
                }
            }

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "KundId måste vara ett heltal");
        }
    }

    public void rensa() {

        DefaultTableModel model = (DefaultTableModel) orderTable.getModel();
        model.setRowCount(0);

        tableModel = (DefaultTableModel) orderTable.getModel();
        allOrders = dbm.getOrders();
        for (Order order : allOrders) {
            tableModel.addRow(new Object[]{order.getId(), order.getCustomer_id(), order.getOrder_date(), order.getOrder_status(), order.isExpress(), order.getShippingCost()});

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
        valdKund = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        filtreraBTN = new javax.swing.JButton();
        rensaBTN = new javax.swing.JButton();

        setName(""); // NOI18N

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setText("Beställningslista");

        newOrderButton.setText("Ny beställning");
        newOrderButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newOrderButtonActionPerformed(evt);
            }
        });

        showOrderButton.setText("Visa order");
        showOrderButton.setEnabled(false);
        showOrderButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showOrderButtonActionPerformed(evt);
            }
        });

        deleteOrderButton.setText("Radera");
        deleteOrderButton.setEnabled(false);
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
        orderTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                orderTableMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(orderTable);

        valdKund.setToolTipText("Ange kund-ID");
        valdKund.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                valdKundActionPerformed(evt);
            }
        });

        jLabel2.setText("Filtrera efter specifik kund");

        filtreraBTN.setText("Filtrera");
        filtreraBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                filtreraBTNActionPerformed(evt);
            }
        });

        rensaBTN.setText("Rensa");
        rensaBTN.setEnabled(false);
        rensaBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rensaBTNActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 462, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(34, 34, 34)
                                .addComponent(valdKund, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(filtreraBTN))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(64, 64, 64)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel1)
                        .addGap(55, 55, 55)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(newOrderButton)
                    .addComponent(showOrderButton)
                    .addComponent(deleteOrderButton)
                    .addComponent(rensaBTN))
                .addGap(68, 68, 68))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(newOrderButton)
                            .addComponent(valdKund, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(filtreraBTN))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1)
                        .addGap(20, 20, 20)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(showOrderButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(deleteOrderButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rensaBTN))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 407, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(198, Short.MAX_VALUE))
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
            System.out.println("Ordern är raderad");
              showOrderButton.setEnabled(false);
        deleteOrderButton.setEnabled(false); 
        }


    }//GEN-LAST:event_deleteOrderButtonActionPerformed

    private void showOrderButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_showOrderButtonActionPerformed
        int selectedRowId = orderTable.getSelectedRow();
        int selectedOrderId = (int) orderTable.getValueAt(selectedRowId, 0);
        window.showOrderPanel(selectedOrderId);
    }//GEN-LAST:event_showOrderButtonActionPerformed

    private void valdKundActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_valdKundActionPerformed
        String placeholderText = valdKund.getText();


    }//GEN-LAST:event_valdKundActionPerformed

    private void filtreraBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_filtreraBTNActionPerformed
        filtreraKund();
        rensaBTN.setEnabled(true);
    }//GEN-LAST:event_filtreraBTNActionPerformed

    private void rensaBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rensaBTNActionPerformed
        rensa();
        rensaBTN.setEnabled(false); 
// TODO add your handling code here:
    }//GEN-LAST:event_rensaBTNActionPerformed

    private void orderTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_orderTableMouseClicked
        showOrderButton.setEnabled(true);
        deleteOrderButton.setEnabled(true); 
    }//GEN-LAST:event_orderTableMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton deleteOrderButton;
    private javax.swing.JButton filtreraBTN;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton newOrderButton;
    private javax.swing.JTable orderTable;
    private javax.swing.JButton rensaBTN;
    private javax.swing.JButton showOrderButton;
    private javax.swing.JTextField valdKund;
    // End of variables declaration//GEN-END:variables

    private static class Textfield {

        public Textfield() {
        }
    }
}
