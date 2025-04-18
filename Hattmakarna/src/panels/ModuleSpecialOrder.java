
package panels;

import static hattmakarna.Hattmakarna.dbm;
import hattmakarna.ModuleWindow;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.table.TableColumn;
import models.Component;
import models.Product;
import javax.swing.table.DefaultTableModel;

public class ModuleSpecialOrder extends javax.swing.JPanel {

    private ModuleWindow window;
    private DefaultTableModel materialTable;
    private Product product;
    private int baseId;
    private OrderPanel order;
    
    public ModuleSpecialOrder(ModuleWindow window, OrderPanel order) {
        this.window = window;
        this.order = order;
        baseId = 0;
        initComponents();
        fillComboBoxes();
        materialTable = (DefaultTableModel) tblMaterial.getModel();
        tblMaterial.setModel(materialTable);
    }
    
    public ModuleSpecialOrder(ModuleWindow window, Product product, OrderPanel order) {
        this.window = window;
        this.product = product;
        this.order = order;
        baseId = product.getProductId();
        initComponents();
        txtWeight.setText(String.valueOf(product.getWeight()));
        fillComboBoxes();
        txtHatName.setText(product.getProductName() + " (Modifierad)");
        txtPrice.setText(String.valueOf(product.getPrice()));
        txtDescription.setText(product.getDescription());
        materialTable = (DefaultTableModel) tblMaterial.getModel();
        insertExistingComponentsIntoTable(product.getProductId());
        tblMaterial.setModel(materialTable);
    }

    private void fillComboBoxes() {
        JComboBox comboBoxNames = new JComboBox();
        JComboBox comboBoxColor = new JComboBox();
        JComboBox comboBoxTypes = new JComboBox();
        JComboBox comboBoxUnits = new JComboBox();
        
        //Använder sets för att bara få in unika värden i listorna
        Set<String> nameSet = new HashSet<>();
        Set<String> typeSet = new HashSet<>();
        Set<String> colorSet = new HashSet<>();
        
        ArrayList<Component> components = dbm.getComponents();
        
        for (Component component : components) {
            nameSet.add(component.getComponentName());
            colorSet.add(component.getColor());
            typeSet.add(component.getType());
        }
        
        comboBoxUnits.addItem("meter");
        comboBoxUnits.addItem("styck");
        
        for(String name : nameSet)comboBoxNames.addItem(name);
        for(String type : typeSet)comboBoxTypes.addItem(type);
        for(String color : colorSet)comboBoxColor.addItem(color);

        TableColumn nameColumn = tblMaterial.getColumnModel().getColumn(0);
        nameColumn.setCellEditor(new DefaultCellEditor(comboBoxNames));
        
        TableColumn typesColumn = tblMaterial.getColumnModel().getColumn(1);
        typesColumn.setCellEditor(new DefaultCellEditor(comboBoxTypes));

        TableColumn colorColumn = tblMaterial.getColumnModel().getColumn(2);
        colorColumn.setCellEditor(new DefaultCellEditor(comboBoxColor));
        
        TableColumn unitColumn = tblMaterial.getColumnModel().getColumn(4);
        unitColumn.setCellEditor(new DefaultCellEditor(comboBoxUnits));    
    }
    
    private void insertExistingComponentsIntoTable (int productId) {
        
        materialTable.removeRow(0);
        ArrayList<Component> components = dbm.getComponentsForProduct(productId);

        for (Component component : components) {
            materialTable.addRow(new Object[]{ component.getComponentName(), component.getType(), component.getColor(), component.getAmount(), component.getUnit()}); 
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblHatName = new javax.swing.JLabel();
        txtHatName = new javax.swing.JTextField();
        lblPrice = new javax.swing.JLabel();
        txtPrice = new javax.swing.JTextField();
        lblDescription = new javax.swing.JLabel();
        btnAddMaterial = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblMaterial = new javax.swing.JTable();
        btnSave = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();
        btnRemoveRow = new javax.swing.JButton();
        btnCreateMaterial = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtDescription = new javax.swing.JTextArea();
        txtWeight = new javax.swing.JTextField();
        lblWeight = new javax.swing.JLabel();
        cbxResale = new javax.swing.JCheckBox();
        lblResale = new javax.swing.JLabel();

        setPreferredSize(new java.awt.Dimension(600, 600));
        setVerifyInputWhenFocusTarget(false);

        lblHatName.setText("Hattnamn:");

        lblPrice.setText("Pris:");

        lblDescription.setText("Beskrivning:");

        btnAddMaterial.setText("+ Rad");
        btnAddMaterial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddMaterialActionPerformed(evt);
            }
        });

        tblMaterial.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null}
            },
            new String [] {
                "Namn", "Typ", "Färg", "Mängd", "Enhet"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblMaterial);
        if (tblMaterial.getColumnModel().getColumnCount() > 0) {
            tblMaterial.getColumnModel().getColumn(0).setPreferredWidth(150);
            tblMaterial.getColumnModel().getColumn(2).setPreferredWidth(50);
            tblMaterial.getColumnModel().getColumn(3).setResizable(false);
            tblMaterial.getColumnModel().getColumn(3).setPreferredWidth(15);
            tblMaterial.getColumnModel().getColumn(4).setResizable(false);
            tblMaterial.getColumnModel().getColumn(4).setPreferredWidth(15);
        }

        btnSave.setText("Spara");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        btnCancel.setText("Avbryt");
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });

        btnRemoveRow.setText("- Rad ");
        btnRemoveRow.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoveRowActionPerformed(evt);
            }
        });

        btnCreateMaterial.setText("Skapa material");
        btnCreateMaterial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCreateMaterialActionPerformed(evt);
            }
        });

        txtDescription.setColumns(20);
        txtDescription.setRows(5);
        jScrollPane2.setViewportView(txtDescription);

        lblWeight.setText("Vikt:");

        lblResale.setText("Får säljas vidare");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(btnAddMaterial)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnRemoveRow)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnCreateMaterial))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 588, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnCancel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSave))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblHatName)
                            .addComponent(lblDescription)
                            .addComponent(lblWeight))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtWeight, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblResale)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cbxResale)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtHatName, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblPrice)
                                .addGap(18, 18, 18)
                                .addComponent(txtPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane2))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblHatName)
                    .addComponent(txtHatName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblPrice)
                    .addComponent(txtPrice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblDescription)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtWeight, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblWeight))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(cbxResale)
                        .addComponent(lblResale)))
                .addGap(18, 18, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAddMaterial)
                    .addComponent(btnRemoveRow)
                    .addComponent(btnCreateMaterial))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSave)
                    .addComponent(btnCancel))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        window.dispose();
    }//GEN-LAST:event_btnCancelActionPerformed

    private void btnAddMaterialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddMaterialActionPerformed
        materialTable.addRow(new Object[]{ "" ,"", "", ""});
        tblMaterial.setModel(materialTable);
    }//GEN-LAST:event_btnAddMaterialActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        
        if (txtWeight.getText().isEmpty() || txtHatName.getText().isEmpty() || txtPrice.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Du behöver fylla i alla fält innan du går vidare.");
            return;
        }
        
        if ( tblMaterial.getCellEditor() != null ) tblMaterial.getCellEditor().stopCellEditing();
        //Skapar produkten
        Product newProduct = dbm.createProduct();
        newProduct.setProductName(txtHatName.getText());
        newProduct.setPrice(Integer.parseInt(txtPrice.getText()));
        newProduct.setDiscontinued(false);
        newProduct.setStockItem(false);
        newProduct.setCopyRightApproved(false);
        newProduct.setProductBaseId(baseId);
        newProduct.setDescription(txtDescription.getText());
        newProduct.setWeight(Double.parseDouble(txtWeight.getText().replace(",", ".")));
        dbm.updateProduct(newProduct);
        
        //Skapar komponenterna som hör till produkten
        for (int i = 0; i < tblMaterial.getRowCount(); i++) {
                Component newComponent = dbm.createComponent();
                newComponent.setComponentName(tblMaterial.getValueAt(i, 0).toString());
                newComponent.setColor(tblMaterial.getValueAt(i, 2).toString());
                newComponent.setType(tblMaterial.getValueAt(i, 1).toString());
                newComponent.setAmount(Double.parseDouble(tblMaterial.getValueAt(i, 3).toString().replace(",", ".")));
                newComponent.setUnit(tblMaterial.getValueAt(i, 4).toString());
                dbm.updateComponent(newComponent);
                dbm.setComponentForProduct(newProduct.getProductId(), newComponent.getComponentId(), newComponent.getAmount());
        }
        Object[] orderline = new Object[]{0, newProduct.getProductId(), newProduct.getPrice(), cbxResale.isSelected(), newProduct.getDescription()};
        order.addOrderline(orderline);
        window.dispose();
    }//GEN-LAST:event_btnSaveActionPerformed

    private void btnRemoveRowActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoveRowActionPerformed
        int selectedRowId = tblMaterial.getSelectedRow();
        if (selectedRowId == -1) {
            JOptionPane.showMessageDialog(this, "Du måste markera en rad i tabellen först.");
            return;
        }
        materialTable.removeRow(selectedRowId);
        tblMaterial.setModel(materialTable);
    }//GEN-LAST:event_btnRemoveRowActionPerformed

    private void btnCreateMaterialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCreateMaterialActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnCreateMaterialActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddMaterial;
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnCreateMaterial;
    private javax.swing.JButton btnRemoveRow;
    private javax.swing.JButton btnSave;
    private javax.swing.JCheckBox cbxResale;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblDescription;
    private javax.swing.JLabel lblHatName;
    private javax.swing.JLabel lblPrice;
    private javax.swing.JLabel lblResale;
    private javax.swing.JLabel lblWeight;
    private javax.swing.JTable tblMaterial;
    private javax.swing.JTextArea txtDescription;
    private javax.swing.JTextField txtHatName;
    private javax.swing.JTextField txtPrice;
    private javax.swing.JTextField txtWeight;
    // End of variables declaration//GEN-END:variables
}
