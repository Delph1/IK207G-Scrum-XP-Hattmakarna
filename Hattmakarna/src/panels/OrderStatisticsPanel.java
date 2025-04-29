
package panels;

import hattmakarna.MainWindow;
import javax.swing.table.DefaultTableModel;
import javax.swing.JOptionPane;
import java.util.ArrayList;
import java.util.HashMap;
import static hattmakarna.Hattmakarna.dbm;
import utils.Print;
import java.awt.print.PrinterException;
import java.io.IOException;
import models.*;
import utils.DatePicker;
import utils.Validator;

public class OrderStatisticsPanel extends javax.swing.JPanel {
     private MainWindow window;
     private ArrayList<Integer> productIds = new ArrayList<>();
     private ArrayList<Integer> customerIds = new ArrayList<>();

    public OrderStatisticsPanel(MainWindow window) {
        initComponents();
        this.window=window;
        fillHSComboBox();
        fillCSComboBox();
        
        DatePicker.attachToTextField(window, startDateHSTF);
        DatePicker.attachToTextField(window, endDateHSTF);
                DatePicker.attachToTextField(window, startDateCSTF);
        DatePicker.attachToTextField(window, endDateCSTF);
        Validator.setDate(startDateHSTF);
        Validator.setDate(endDateHSTF);
                Validator.setDate(startDateCSTF);
        Validator.setDate(endDateCSTF);
    }
    

public void fillHSComboBox() {
    ArrayList<Product> products = dbm.getProducts();
    for (Product product : products) {
        selectorHSCB.addItem(product.getProductName());
        productIds.add(product.getProductId());
    }
}

public void fillCSComboBox() {
    ArrayList<Customer> customers = dbm.getCustomers();
    for (Customer customer : customers) {
        selectorCSCB.addItem("["+customer.getId()+"]"+customer.getFirstName()+" "+customer.getLastName());
        customerIds.add(customer.getId());
    }
}

    
    
   public void fillHSTable() {
    try {
        int index = selectorHSCB.getSelectedIndex();
            if(index < 0 ){
                JOptionPane.showMessageDialog(this, "Välj en hatt från listan.");
                return;
            }
        
        int productId = productIds.get(index); 
        String startDate = startDateHSTF.getText().trim();
        String stopDate = endDateHSTF.getText().trim();
        int totalQuantity=0;
        int totalSum=0;
        ArrayList<HashMap<String, String>> salesData = dbm.productSalesBetweenDates(productId, startDate, stopDate);

        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Datum");
        model.addColumn("Antal Sålda");
        model.addColumn("Summa");

        if (salesData != null && !salesData.isEmpty()) {
            for (HashMap<String, String> row : salesData) {
                
                              totalSum+=Integer.parseInt(row.get("total_sale"));
                    totalQuantity+=Integer.parseInt(row.get("quantity_sold"));
                model.addRow(new Object[]{
                    row.get("order_date"),
                    row.get("quantity_sold"),
                    row.get("total_sale")
                });
            }
                             model.addRow(new Object[]{
                    "Totalt",
                    totalQuantity,
                    totalSum
                });
        } else {
            JOptionPane.showMessageDialog(null, "Inga försäljningsdata hittades för perioden.");
        }

        HSTable.setModel(model);

    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Ett fel uppstod: " + e.getMessage());
    }
}
   public void fillCSTable() {
    try {
        int index = selectorCSCB.getSelectedIndex();
            if(index < 0 ){
                JOptionPane.showMessageDialog(this, "Välj en kund från listan.");
                return;
            }
        
        int customerID = customerIds.get(index); 
        String startDate = startDateCSTF.getText().trim();
        String stopDate = endDateCSTF.getText().trim();
        
        int totalQuantity=0;
        int totalSum=0;
       
        ArrayList<HashMap<String, String>> salesData = dbm.customerSalesBetweenDates(customerID, startDate, stopDate);

        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Datum");
        model.addColumn("Antal Sålda");
        model.addColumn("Summa");

        if (salesData != null && !salesData.isEmpty()) {
                  
            for (HashMap<String, String> row : salesData) {
              totalSum+=Integer.parseInt(row.get("total_sale"));
                    totalQuantity+=Integer.parseInt(row.get("quantity_sold"));
                model.addRow(new Object[]{
                    row.get("order_date"),
                    row.get("quantity_sold"),
                    row.get("total_sale")
                });
            }
                     model.addRow(new Object[]{
                    "Totalt",
                    totalQuantity,
                    totalSum
                });
        } else {
            JOptionPane.showMessageDialog(null, "Inga försäljningsdata hittades för perioden.");
        }

        CSTable.setModel(model);

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

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        HSTable = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        printHSBTN = new javax.swing.JButton();
        selectorHSCB = new javax.swing.JComboBox<>();
        startDateHSTF = new javax.swing.JTextField();
        endDateHSTF = new javax.swing.JTextField();
        searchHSBTN = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        CSTable = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        printCSBTN = new javax.swing.JButton();
        selectorCSCB = new javax.swing.JComboBox<>();
        startDateCSTF = new javax.swing.JTextField();
        endDateCSTF = new javax.swing.JTextField();
        searchCSBTN = new javax.swing.JButton();

        HSTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null}
            },
            new String [] {
                "Datum", "Antal Sålda", "Summa"
            }
        ));
        jScrollPane1.setViewportView(HSTable);

        jLabel1.setText("Startdatum:");

        jLabel2.setText("Stoppdatum:");

        printHSBTN.setText("Skriv ut");
        printHSBTN.setEnabled(false);
        printHSBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                printHSBTNActionPerformed(evt);
            }
        });

        selectorHSCB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectorHSCBActionPerformed(evt);
            }
        });

        startDateHSTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startDateHSTFActionPerformed(evt);
            }
        });

        endDateHSTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                endDateHSTFActionPerformed(evt);
            }
        });

        searchHSBTN.setText("Sök");
        searchHSBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchHSBTNActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(printHSBTN)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 523, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(selectorHSCB, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(startDateHSTF, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(18, 18, 18)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(endDateHSTF, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(searchHSBTN))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel2)
                                    .addGap(0, 0, Short.MAX_VALUE))))))
                .addContainerGap(137, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(selectorHSCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(searchHSBTN)
                    .addComponent(startDateHSTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(endDateHSTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(printHSBTN)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Per hatt", jPanel1);

        CSTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null}
            },
            new String [] {
                "Datum", "Antal Sålda", "Summa"
            }
        ));
        jScrollPane2.setViewportView(CSTable);

        jLabel3.setText("Startdatum:");

        jLabel4.setText("Stoppdatum:");

        printCSBTN.setText("Skriv ut");
        printCSBTN.setEnabled(false);
        printCSBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                printCSBTNActionPerformed(evt);
            }
        });

        selectorCSCB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectorCSCBActionPerformed(evt);
            }
        });

        startDateCSTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startDateCSTFActionPerformed(evt);
            }
        });

        endDateCSTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                endDateCSTFActionPerformed(evt);
            }
        });

        searchCSBTN.setText("Sök");
        searchCSBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchCSBTNActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(printCSBTN)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 523, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addComponent(selectorCSCB, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(startDateCSTF, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(18, 18, 18)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel3Layout.createSequentialGroup()
                                    .addComponent(endDateCSTF, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(searchCSBTN))
                                .addGroup(jPanel3Layout.createSequentialGroup()
                                    .addComponent(jLabel4)
                                    .addGap(0, 0, Short.MAX_VALUE))))))
                .addContainerGap(137, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(selectorCSCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(searchCSBTN)
                    .addComponent(startDateCSTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(endDateCSTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(printCSBTN)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Per kund", jPanel3);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 414, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void searchCSBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchCSBTNActionPerformed
       boolean startDateTFValid =    Validator.isValid(startDateCSTF);
        boolean endDateTFValid =    Validator.isValid(endDateCSTF);
        if (startDateTFValid && endDateTFValid) {
            fillCSTable();
            printCSBTN.setEnabled(true);
        }        
    }//GEN-LAST:event_searchCSBTNActionPerformed

    private void endDateCSTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_endDateCSTFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_endDateCSTFActionPerformed

    private void startDateCSTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startDateCSTFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_startDateCSTFActionPerformed

    private void selectorCSCBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectorCSCBActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_selectorCSCBActionPerformed

    private void printCSBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_printCSBTNActionPerformed
        String startDate = startDateCSTF.getText();
        String stopDate = endDateCSTF.getText();

        int customerId = productIds.get(selectorCSCB.getSelectedIndex());

        String stringData[][] = new String[CSTable.getRowCount()][3];

        for (int i = 0; i < CSTable.getRowCount(); i++) {
            stringData[i][0] = CSTable.getValueAt(i, 0).toString();
            stringData[i][1] = CSTable.getValueAt(i, 1).toString();
            stringData[i][2] = CSTable.getValueAt(i, 2).toString() + " SEK";
        }
        Print printHatStats = new Print(stringData, startDate, stopDate,customerId);
        try {
            printHatStats.printCustomerStats();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        } catch (PrinterException ep) {
            System.err.println(ep.getMessage());
        }        // TODO add your handling code here:
    }//GEN-LAST:event_printCSBTNActionPerformed

    private void searchHSBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchHSBTNActionPerformed
        boolean startDateTFValid =    Validator.isValid(startDateHSTF);
        boolean endDateTFValid =    Validator.isValid(endDateHSTF);
        if (startDateTFValid && endDateTFValid) {
            fillHSTable();
            printHSBTN.setEnabled(true);
        }
    }//GEN-LAST:event_searchHSBTNActionPerformed

    private void endDateHSTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_endDateHSTFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_endDateHSTFActionPerformed

    private void startDateHSTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startDateHSTFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_startDateHSTFActionPerformed

    private void selectorHSCBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectorHSCBActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_selectorHSCBActionPerformed

    private void printHSBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_printHSBTNActionPerformed

        String startDate = startDateHSTF.getText();
        String stopDate = endDateHSTF.getText();

        int productId = productIds.get(selectorHSCB.getSelectedIndex());

        String stringData[][] = new String[HSTable.getRowCount()][3];

        for (int i = 0; i < HSTable.getRowCount(); i++) {
            stringData[i][0] = HSTable.getValueAt(i, 0).toString();
            stringData[i][1] = HSTable.getValueAt(i, 1).toString();
            stringData[i][2] = HSTable.getValueAt(i, 2).toString() + " SEK";
        }
        Print printHatStats = new Print(stringData, productId, startDate, stopDate);
        try {
            printHatStats.printHatStats();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        } catch (PrinterException ep) {
            System.err.println(ep.getMessage());
        }
    }//GEN-LAST:event_printHSBTNActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable CSTable;
    private javax.swing.JTable HSTable;
    private javax.swing.JTextField endDateCSTF;
    private javax.swing.JTextField endDateHSTF;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JButton printCSBTN;
    private javax.swing.JButton printHSBTN;
    private javax.swing.JButton searchCSBTN;
    private javax.swing.JButton searchHSBTN;
    private javax.swing.JComboBox<String> selectorCSCB;
    private javax.swing.JComboBox<String> selectorHSCB;
    private javax.swing.JTextField startDateCSTF;
    private javax.swing.JTextField startDateHSTF;
    // End of variables declaration//GEN-END:variables
}
