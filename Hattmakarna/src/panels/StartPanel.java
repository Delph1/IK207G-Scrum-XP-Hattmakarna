package panels;

import hattmakarna.MainWindow;
import static hattmakarna.Hattmakarna.dbm;
import java.util.*;
import javax.swing.table.DefaultTableModel;
import java.time.LocalDate;

public class StartPanel extends javax.swing.JPanel {
    private MainWindow window;
    private int userId = 1;

    public StartPanel(MainWindow window) {
        this.window = window;
        this.userId = userId;
        initComponents(); 
        fyllDagensOrdrar();
    }

    private void fyllDagensOrdrar() {
    String idag = java.time.LocalDate.now().toString();
    ArrayList<HashMap<String, String>> dagensOrdrar = dbm.getOrdersByDeliveryDate(LocalDate.parse(idag));

    DefaultTableModel model = new DefaultTableModel();
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
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        scrollDagensOrder = new javax.swing.JScrollPane();
        tblDagensOrder = new javax.swing.JTable();
        btnSchema = new javax.swing.JButton();
        lblValkommen = new javax.swing.JLabel();

        setName(""); // NOI18N

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel1.setText("Startpanel");

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

        btnSchema.setText("Se schema");
        btnSchema.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSchemaActionPerformed(evt);
            }
        });

        lblValkommen.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblValkommen.setText("Välkommen! Nedan ser du dagens ordrar. ");

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
                        .addGap(35, 35, 35)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblValkommen)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnSchema))
                            .addComponent(scrollDagensOrder, javax.swing.GroupLayout.PREFERRED_SIZE, 527, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(330, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnSchema)
                    .addComponent(lblValkommen))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scrollDagensOrder, javax.swing.GroupLayout.PREFERRED_SIZE, 328, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnSchemaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSchemaActionPerformed
        window.showSchemaPanel(userId);
    }//GEN-LAST:event_btnSchemaActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSchema;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel lblValkommen;
    private javax.swing.JScrollPane scrollDagensOrder;
    private javax.swing.JTable tblDagensOrder;
    // End of variables declaration//GEN-END:variables
}
