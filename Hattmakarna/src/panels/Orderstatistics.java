
package panels;

import hattmakarna.MainWindow;
import javax.swing.table.DefaultTableModel;
import javax.swing.JOptionPane;
import java.util.ArrayList;
import java.util.HashMap;
import static hattmakarna.Hattmakarna.dbm;
import hattmakarna.Print;
import java.awt.print.PrinterException;
import java.io.IOException;
import models.*;

public class Orderstatistics extends javax.swing.JPanel {
     private MainWindow window;
     private ArrayList<Integer> productIds = new ArrayList<>();


    public Orderstatistics(MainWindow window) {
        initComponents();
        this.window=window;
        fyllComboBox();
    }
    

public void fyllComboBox() {
    ArrayList<Product> products = dbm.getProducts();
    for (Product product : products) {
        hattComboBox.addItem(product.getProductName());
        productIds.add(product.getProductId());
    }
}


    
    
   public void fyllTabell() {
    try {
        int index = hattComboBox.getSelectedIndex();
            if(index < 0 ){
                JOptionPane.showMessageDialog(this, "Välj en hatt från listan.");
                return;
            }
        
        int productId = productIds.get(index); 
        String startDate = startDatum.getText().trim();
        String stopDate = slutDatum.getText().trim();

        ArrayList<HashMap<String, String>> salesData = dbm.productSalesBetweenDates(productId, startDate, stopDate);

        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Datum");
        model.addColumn("Antal Sålda");
        model.addColumn("Summa");

        if (salesData != null && !salesData.isEmpty()) {
            for (HashMap<String, String> row : salesData) {
                model.addRow(new Object[]{
                    row.get("order_date"),
                    row.get("quantity_sold"),
                    row.get("total_sale")
                });
            }
        } else {
            JOptionPane.showMessageDialog(null, "Inga försäljningsdata hittades för perioden.");
        }

        hattTabell.setModel(model);

    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Ett fel uppstod: " + e.getMessage());
    }
}



    // * This method is called from within the constructor to initialize the form.
    // * WARNING: Do NOT modify this code. The content of this method is always
    // * regenerated by the Form Editor.
    // */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        hattComboBox = new javax.swing.JComboBox<>();
        startDatum = new javax.swing.JTextField();
        slutDatum = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        hattTabell = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        btnPrintHatStats = new javax.swing.JButton();

        hattComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hattComboBoxActionPerformed(evt);
            }
        });

        startDatum.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startDatumActionPerformed(evt);
            }
        });

        slutDatum.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                slutDatumActionPerformed(evt);
            }
        });

        jButton1.setText("Sök");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        hattTabell.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null}
            },
            new String [] {
                "Datum", "Antal Sålda", "Summa"
            }
        ));
        jScrollPane1.setViewportView(hattTabell);

        jLabel1.setText("Startdatum:");

        jLabel2.setText("Stoppdatum:");

        jLabel3.setText("Vänligen skriv datum i formatet: 2024-01-01");

        btnPrintHatStats.setText("Skriv ut");
        btnPrintHatStats.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrintHatStatsActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(57, 57, 57)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnPrintHatStats)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 523, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(hattComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(startDatum, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(18, 18, 18)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(slutDatum, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jButton1))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel2)
                                    .addGap(0, 0, Short.MAX_VALUE))))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addComponent(jLabel3)
                            .addGap(161, 161, 161))))
                .addGap(98, 98, 98))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(hattComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1)
                    .addComponent(startDatum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(slutDatum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnPrintHatStats)
                .addContainerGap(185, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void hattComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hattComboBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_hattComboBoxActionPerformed

    private void startDatumActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startDatumActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_startDatumActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        fyllTabell();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void slutDatumActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_slutDatumActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_slutDatumActionPerformed

    private void btnPrintHatStatsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrintHatStatsActionPerformed
        
        String startDate = startDatum.getText();
        String stopDate = slutDatum.getText();
        
        int productId = productIds.get(hattComboBox.getSelectedIndex());
        
        String stringData[][] = new String[hattTabell.getRowCount()][3];
        
        for (int i = 0; i < hattTabell.getRowCount(); i++) {
            stringData[i][0] = hattTabell.getValueAt(i, 0).toString();
            stringData[i][1] = hattTabell.getValueAt(i, 1).toString();
            stringData[i][2] = hattTabell.getValueAt(i, 2).toString() + " SEK";
        }
        Print printHatStats = new Print(stringData, productId, startDate, stopDate);
        try {
            printHatStats.printHatStats();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        } catch (PrinterException ep) {
            System.err.println(ep.getMessage());
        }
    }//GEN-LAST:event_btnPrintHatStatsActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnPrintHatStats;
    private javax.swing.JComboBox<String> hattComboBox;
    private javax.swing.JTable hattTabell;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField slutDatum;
    private javax.swing.JTextField startDatum;
    // End of variables declaration//GEN-END:variables
}
