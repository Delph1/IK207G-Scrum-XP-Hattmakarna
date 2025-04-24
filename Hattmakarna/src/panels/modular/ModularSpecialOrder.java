package panels.modular;

import static hattmakarna.Hattmakarna.dbm;
import hattmakarna.ModularWindow;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashSet;
import java.util.Set;
import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableColumn;
import models.Component;
import models.Product;
import javax.swing.table.DefaultTableModel;
import models.ProductImage;
import panels.OrderPanel;
import panels.modular.*;
import utils.ImageManager;

public class ModularSpecialOrder extends javax.swing.JPanel {

    private ModularWindow window;
    private DefaultTableModel materialTable;
    private DefaultTableModel blueprintTable;
    private Product product;
    private int baseId;
    private OrderPanel order;
    private String base64Image;
    private ImageManager imageManager;
    
    public ModularSpecialOrder(ModularWindow window, OrderPanel order) {
        this.window = window;
        this.order = order;
        this.imageManager = new ImageManager(window, true);
        baseId = 0;
        initComponents();
        fillComboBoxes();
        materialTable = (DefaultTableModel) tblMaterial.getModel();
        blueprintTable = (DefaultTableModel) tblBlueprint.getModel();
        tblMaterial.setModel(materialTable);
        tblBlueprint.setModel(blueprintTable);
        createBlueprintListener();
        
        //gömmer tredje kolumnen
        tblBlueprint.getColumnModel().getColumn(2).setMinWidth(0);
        tblBlueprint.getColumnModel().getColumn(2).setMaxWidth(0);
        tblBlueprint.getColumnModel().getColumn(2).setWidth(0);
        
    }
    
    public ModularSpecialOrder(ModularWindow window, Product product, OrderPanel order) {
        this.window = window;
        this.product = product;
        this.order = order;
        this.imageManager = new ImageManager(window, true);
        baseId = product.getProductId();
        initComponents();
        txtWeight.setText(String.valueOf(product.getWeight()));
        fillComboBoxes();
        txtHatName.setText(product.getProductName() + " (Modifierad)");
        txtPrice.setText(String.valueOf(product.getPrice()));
        txtDescription.setText(product.getDescription());
        materialTable = (DefaultTableModel) tblMaterial.getModel();
        blueprintTable = (DefaultTableModel) tblBlueprint.getModel();
        insertExistingComponentsIntoTable(product.getProductId());
        tblMaterial.setModel(materialTable);
        tblBlueprint.setModel(blueprintTable);
        createBlueprintListener();
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
    
    private void createBlueprintListener() {
        tblBlueprint.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    int markeradRad = tblBlueprint.getSelectedRow();
                    if (markeradRad != -1) {
                        updateImage(markeradRad);
                    }
                }
            }
        });
    }
    
    private void updateImage(int rad) {
        base64Image = tblBlueprint.getValueAt(rad, 2).toString();
        
        if (base64Image != null && !base64Image.isEmpty()) {
            byte[] imageBytes = Base64.getDecoder().decode(base64Image);
            ImageIcon icon = new ImageIcon(imageBytes);
            Image scaledImage = icon.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
            lblBlueprint.setIcon(new ImageIcon(scaledImage));
            lblBlueprint.setText(null);
        } else {
            lblBlueprint.setIcon(null);
            lblBlueprint.setText("Ingen bild hittades");
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
        cbxCopyright = new javax.swing.JCheckBox();
        lblResale1 = new javax.swing.JLabel();
        lblImage = new javax.swing.JLabel();
        btnUploadImage = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblBlueprint = new javax.swing.JTable();
        lblBlueprint = new javax.swing.JLabel();
        btnUploadBlueprint = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(620, 535));
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

        lblResale1.setText("Copyright-skydd");

        lblImage.setText("Ingen bild än...");

        btnUploadImage.setText("Välj bild ...");
        btnUploadImage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUploadImageActionPerformed(evt);
            }
        });

        tblBlueprint.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Beskrivning", "Base64"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane3.setViewportView(tblBlueprint);
        if (tblBlueprint.getColumnModel().getColumnCount() > 0) {
            tblBlueprint.getColumnModel().getColumn(0).setPreferredWidth(15);
            tblBlueprint.getColumnModel().getColumn(1).setPreferredWidth(100);
        }

        lblBlueprint.setText("Välj en ritning i listan");

        btnUploadBlueprint.setText("Ladda upp ritning");
        btnUploadBlueprint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUploadBlueprintActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblHatName)
                            .addComponent(lblDescription)
                            .addComponent(lblWeight))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtWeight, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblResale)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cbxResale)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblResale1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cbxCopyright))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtHatName, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(lblPrice)
                                .addGap(18, 18, 18)
                                .addComponent(txtPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane2))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lblImage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(32, 32, 32)
                                .addComponent(btnUploadImage, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnUploadBlueprint)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnCancel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSave)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(btnAddMaterial)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnRemoveRow)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnCreateMaterial))
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 395, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblBlueprint, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblHatName)
                            .addComponent(txtHatName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblPrice)
                            .addComponent(txtPrice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblDescription)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(lblImage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtWeight, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblWeight))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(cbxResale)
                        .addComponent(lblResale)
                        .addComponent(cbxCopyright)
                        .addComponent(lblResale1)
                        .addComponent(btnUploadImage)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAddMaterial)
                    .addComponent(btnRemoveRow)
                    .addComponent(btnCreateMaterial))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblBlueprint, javax.swing.GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSave)
                    .addComponent(btnCancel)
                    .addComponent(btnUploadBlueprint))
                .addGap(12, 12, 12))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        window.dispose();
    }//GEN-LAST:event_btnCancelActionPerformed

    private void btnAddMaterialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddMaterialActionPerformed
        materialTable.addRow(new Object[]{ "", "", "", "", "" });
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
        newProduct.setCopyRightApproved(cbxCopyright.isSelected());
        newProduct.setProductBaseId(baseId);
        newProduct.setDescription(txtDescription.getText());
        newProduct.setWeight(Double.parseDouble(txtWeight.getText().replace(",", ".")));
        dbm.updateProduct(newProduct);
        
        //Sparar bilden om det finns någon
        if (base64Image != null) {
            imageManager.saveNewImage(base64Image, newProduct.getProductId(), "Produktbild", "");
        }
        
        if (tblBlueprint.getRowCount() > 0) {
            for (int i = 0; i < tblBlueprint.getRowCount(); i++){
                ProductImage newBlueprint = dbm.createImage();
                newBlueprint.setProductId(newProduct.getProductId());
                newBlueprint.setType("Ritning");
                newBlueprint.setDescription(tblBlueprint.getValueAt(i, 1).toString());
                newBlueprint.setBase64(tblBlueprint.getValueAt(i, 2).toString());
                dbm.updateImage(newBlueprint);
            }
        }
        
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

    private void btnUploadImageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUploadImageActionPerformed
        
        byte[] imageBytes = imageManager.uploadImage();
        
        ImageIcon icon = new ImageIcon(imageBytes);
        Image scaledImage = icon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        lblImage.setIcon(new ImageIcon(scaledImage));
        lblImage.setText(null);
        base64Image = Base64.getEncoder().encodeToString(imageBytes);
    }//GEN-LAST:event_btnUploadImageActionPerformed

    private void btnUploadBlueprintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUploadBlueprintActionPerformed
        byte[] imageBytes = imageManager.uploadImage();
        
        ImageIcon icon = new ImageIcon(imageBytes);
        Image scaledImage = icon.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
        lblBlueprint.setIcon(new ImageIcon(scaledImage));
        lblBlueprint.setText(null);
        base64Image = Base64.getEncoder().encodeToString(imageBytes);
        
        int newRow = blueprintTable.getRowCount() + 1;
        blueprintTable.addRow(new Object[] {newRow, "", base64Image});
    }//GEN-LAST:event_btnUploadBlueprintActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddMaterial;
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnCreateMaterial;
    private javax.swing.JButton btnRemoveRow;
    private javax.swing.JButton btnSave;
    private javax.swing.JButton btnUploadBlueprint;
    private javax.swing.JButton btnUploadImage;
    private javax.swing.JCheckBox cbxCopyright;
    private javax.swing.JCheckBox cbxResale;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lblBlueprint;
    private javax.swing.JLabel lblDescription;
    private javax.swing.JLabel lblHatName;
    private javax.swing.JLabel lblImage;
    private javax.swing.JLabel lblPrice;
    private javax.swing.JLabel lblResale;
    private javax.swing.JLabel lblResale1;
    private javax.swing.JLabel lblWeight;
    private javax.swing.JTable tblBlueprint;
    private javax.swing.JTable tblMaterial;
    private javax.swing.JTextArea txtDescription;
    private javax.swing.JTextField txtHatName;
    private javax.swing.JTextField txtPrice;
    private javax.swing.JTextField txtWeight;
    // End of variables declaration//GEN-END:variables
}
