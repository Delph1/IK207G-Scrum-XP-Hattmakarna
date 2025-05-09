
package panels;

import hattmakarna.MainWindow;
import static hattmakarna.Hattmakarna.dbm;
import models.*;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import utils.Print;
import java.awt.print.PrinterException;
import java.io.IOException;
import javax.swing.JOptionPane;
import utils.DatePicker;

public class MaterialListPanel extends javax.swing.JPanel {
    private MainWindow window;
    private Object[][] materialData;

    public MaterialListPanel(MainWindow window) {
        // Vi tar emot och lagrar huvudfönstret som ett fält, då kan vi komma åt metoder som att byta panel
        this.window=window; 
        initComponents();
        DatePicker.attachToTextField(window, txtStartDate);
        DatePicker.attachToTextField(window, txtStopDate);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnSearch = new javax.swing.JButton();
        txtStartDate = new javax.swing.JTextField();
        txtStopDate = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblMaterialList = new javax.swing.JTable();
        lblStartDate = new javax.swing.JLabel();
        lblStopDate = new javax.swing.JLabel();
        btnPlaceOrder = new javax.swing.JButton();

        btnSearch.setText("Sök");
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        tblMaterialList.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Namn", "Färg", "Mängd"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblMaterialList);

        lblStartDate.setText("Startdatum:");

        lblStopDate.setText("Stoppdatum:");

        btnPlaceOrder.setText("Beställ material");
        btnPlaceOrder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPlaceOrderActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnPlaceOrder)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addGap(64, 64, 64)
                                    .addComponent(lblStartDate)
                                    .addGap(37, 37, 37))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addContainerGap()
                                    .addComponent(txtStartDate, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(lblStopDate)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(txtStopDate, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(btnSearch))))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(58, 58, 58)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(28, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblStartDate)
                    .addComponent(lblStopDate))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtStartDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtStopDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSearch))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnPlaceOrder)
                .addContainerGap(89, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        String startDate = txtStartDate.getText();
        String stopDate = txtStopDate.getText();
        
        if (stopDate.length() == 0 || startDate.length() == 0) {
            JOptionPane.showConfirmDialog(this, "Du måste fylla i ett datum i båda fälten.");
            return;
        }
        
        ArrayList<ComponentModel> components = dbm.getComponentsBetweenDates(startDate, stopDate);

        if (components == null) {
            JOptionPane.showMessageDialog(this, "Inget materialbehov hittades mellan dessa datum.");
            return;
        }
        
        String[] columns = {"ID", "Namn", "Färg", "Mängd"};
        
        DefaultTableModel MaterialModel = new DefaultTableModel();
        MaterialModel.addColumn(columns[0]);
        MaterialModel.addColumn(columns[1]);
        MaterialModel.addColumn(columns[2]);
        MaterialModel.addColumn(columns[3]);
        
        Object[][] materialData = new Object[components.size()][4];
        
        for (int i = 0; i < components.size(); i++) {
            ComponentModel component = components.get(i);
            materialData[i][0] = component.getComponentId();
            materialData[i][1] = component.getComponentName();
            materialData[i][2] = component.getColor();
            materialData[i][3] = String.valueOf(component.getAmount()).replace('.', ',') + " " + component.getUnit();
            MaterialModel.addRow(materialData[i]);
        };
        
        tblMaterialList.setModel(MaterialModel);
        
    }//GEN-LAST:event_btnSearchActionPerformed

    private void btnPlaceOrderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPlaceOrderActionPerformed
        String startDate = txtStartDate.getText();
        String stopDate = txtStopDate.getText();        

        String stringData[][] = new String[tblMaterialList.getRowCount()][4];
        
        for (int i = 0; i < tblMaterialList.getRowCount(); i++) {
            stringData[i][0] = tblMaterialList.getValueAt(i, 0).toString();
            stringData[i][1] = tblMaterialList.getValueAt(i, 1).toString();
            stringData[i][2] = tblMaterialList.getValueAt(i, 2).toString();
            stringData[i][3] = tblMaterialList.getValueAt(i, 3).toString();
        }
        Print printMaterial = new Print(stringData, startDate, stopDate);
        try {
            printMaterial.printMaterialList();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        } catch (PrinterException ep) {
            System.err.println(ep.getMessage());
        }
        dbm.updateOrderStatusBetweenDates(startDate, stopDate, "ready");
    }//GEN-LAST:event_btnPlaceOrderActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnPlaceOrder;
    private javax.swing.JButton btnSearch;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblStartDate;
    private javax.swing.JLabel lblStopDate;
    private javax.swing.JTable tblMaterialList;
    private javax.swing.JTextField txtStartDate;
    private javax.swing.JTextField txtStopDate;
    // End of variables declaration//GEN-END:variables
}
