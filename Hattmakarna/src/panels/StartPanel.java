package panels;

import hattmakarna.MainWindow;
import static hattmakarna.Hattmakarna.dbm;
import models.*;
import java.util.*;
import javax.swing.table.DefaultTableModel;
import java.time.LocalDate;

public class StartPanel extends javax.swing.JPanel {
    private MainWindow window;

    public StartPanel(MainWindow window) {
        this.window = window;
        initComponents(); 
        fyllDagensOrdrar();
    }

private void fyllDagensOrdrar() {
    ArrayList<Order> dagensOrdrar = dbm.getOrders();

    LocalDate idag = LocalDate.now();
    ArrayList<Order> filtreradeOrdrar = new ArrayList<>();
    for (Order order : dagensOrdrar) {
        if (order.getOrder_date().isEqual(idag)) {
            filtreradeOrdrar.add(order);
        }
    }

    DefaultTableModel model = new DefaultTableModel();
    model.addColumn("Order ID");
    model.addColumn("Kund ID");
    model.addColumn("Status");
    model.addColumn("Express");
    model.addColumn("Fraktkostnad");
    model.addColumn("Leveransdatum");

    for (Order order : filtreradeOrdrar) {
        model.addRow(new Object[]{
            order.getId(),
            order.getCustomer_id(),
            order.getOrder_status(),
            order.isExpress() ? "Ja" : "Nej",
            order.getShippingCost(),
            order.getDeliveryDate().toString()
        });
    }

    tblDagensOrder.setModel(model);
}

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        lblValkommen = new javax.swing.JLabel();
        scrollDagensOrder = new javax.swing.JScrollPane();
        tblDagensOrder = new javax.swing.JTable();
        btnSchema = new javax.swing.JButton();

        setName(""); // NOI18N

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel1.setText("Startpanel");

        lblValkommen.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblValkommen.setText("Välkommen! Nedan ser du dagens ordrar:");

        tblDagensOrder.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        scrollDagensOrder.setViewportView(tblDagensOrder);

        btnSchema.setText("Visa Schema");
        btnSchema.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSchemaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(269, 269, 269)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(55, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblValkommen)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnSchema))
                            .addComponent(scrollDagensOrder, javax.swing.GroupLayout.PREFERRED_SIZE, 487, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(58, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(39, 39, 39)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSchema)
                    .addComponent(lblValkommen))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scrollDagensOrder, javax.swing.GroupLayout.PREFERRED_SIZE, 399, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(24, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnSchemaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSchemaActionPerformed
        window.showSchemaPanel();
    }//GEN-LAST:event_btnSchemaActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSchema;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel lblValkommen;
    private javax.swing.JScrollPane scrollDagensOrder;
    private javax.swing.JTable tblDagensOrder;
    // End of variables declaration//GEN-END:variables
}
