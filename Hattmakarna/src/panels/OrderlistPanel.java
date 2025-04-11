package panels;

import hattmakarna.MainWindow;
import static hattmakarna.Hattmakarna.dbm;
import models.*;
import java.util.ArrayList;
import javax.swing.DefaultListModel;

public class OrderlistPanel extends javax.swing.JPanel {
//Fält

    private ArrayList<Order> allOrders;
    private MainWindow window;
    private DefaultListModel<String> orderListModel;

//Konstruktor
    public OrderlistPanel(MainWindow window) {
        // Vi tar emot och lagrar huvudfönstret som ett fält, då kan vi komma åt metoder som att byta panel
        this.window = window;
        initComponents();
        dbm.createOrder();
        DefaultListModel<String> orderListModel = new DefaultListModel<String>();
        allOrders = dbm.getOrders();
        for (Order order : allOrders) {
            System.out.println(order.getId());
            orderListModel.addElement("test");
        }
        orderList.setModel(orderListModel);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        newOrderButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        orderList = new javax.swing.JList<>();
        showOrderButton = new javax.swing.JButton();
        deleteOrderButton = new javax.swing.JButton();

        setName(""); // NOI18N

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel1.setText("Beställningslista");

        newOrderButton.setText("Ny beställning");
        newOrderButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newOrderButtonActionPerformed(evt);
            }
        });

        orderList.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        orderList.setToolTipText("");
        jScrollPane1.setViewportView(orderList);

        showOrderButton.setText("Visa order");

        deleteOrderButton.setText("Radera");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(newOrderButton)
                .addGap(38, 38, 38))
            .addGroup(layout.createSequentialGroup()
                .addGap(269, 269, 269)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 291, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 100, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(showOrderButton)
                    .addComponent(deleteOrderButton))
                .addGap(91, 91, 91))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(newOrderButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 102, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(showOrderButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(deleteOrderButton)))
                .addGap(63, 63, 63))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void newOrderButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newOrderButtonActionPerformed
        window.showOrderPanel();
    }//GEN-LAST:event_newOrderButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton deleteOrderButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton newOrderButton;
    private javax.swing.JList<String> orderList;
    private javax.swing.JButton showOrderButton;
    // End of variables declaration//GEN-END:variables
}
