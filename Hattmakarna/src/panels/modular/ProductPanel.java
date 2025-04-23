package panels.modular;

import static hattmakarna.Hattmakarna.dbm;
import hattmakarna.MainWindow;

import hattmakarna.ModularWindow;
import java.awt.Image;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashSet;
import java.util.Set;
import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import models.*;
import utils.ImageManager;

public class ProductPanel extends javax.swing.JPanel {
    private ModularWindow window;
    private ImageManager imageManager;
    private String base64Image;
    private DefaultTableModel materialTable; 
    
    public ProductPanel(ModularWindow window) {
        this.window=window; 
        this.imageManager = new ImageManager(window, true);
        materialTable = (DefaultTableModel) tblMaterial.getModel();
        tblMaterial.setModel(materialTable);
        fillComboBoxes();
        initComponents();        
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
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtProduktnamn = new java.awt.TextField();
        txtPris = new java.awt.TextField();
        jLabel3 = new javax.swing.JLabel();
        txtVikt = new java.awt.TextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        chkLagerfor = new javax.swing.JCheckBox();
        btnLaggTillProdukt = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtBeskrivning = new javax.swing.JTextArea();
        lblImage = new javax.swing.JLabel();
        btnUploadImage = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblMaterial = new javax.swing.JTable();
        btnAddMaterial = new javax.swing.JButton();
        btnRemoveRow = new javax.swing.JButton();
        btnCreateMaterial = new javax.swing.JButton();

        setName(""); // NOI18N

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel1.setText("Produktformulär");

        jLabel2.setText("Hattnamn:");

        jLabel3.setText("Pris");

        jLabel4.setText("Vikt (kg)");

        jLabel5.setText("Beskrivning");

        chkLagerfor.setSelected(true);
        chkLagerfor.setText("Lagerförd");

        btnLaggTillProdukt.setText("Lägg till hatt");
        btnLaggTillProdukt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLaggTillProduktActionPerformed(evt);
            }
        });

        txtBeskrivning.setColumns(20);
        txtBeskrivning.setLineWrap(true);
        txtBeskrivning.setRows(5);
        txtBeskrivning.setWrapStyleWord(true);
        jScrollPane1.setViewportView(txtBeskrivning);

        btnUploadImage.setText("Ladda upp bild");
        btnUploadImage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUploadImageActionPerformed(evt);
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
        jScrollPane2.setViewportView(tblMaterial);

        btnAddMaterial.setText("+ Rad");
        btnAddMaterial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddMaterialActionPerformed(evt);
            }
        });

        btnRemoveRow.setText("- Rad ");
        btnRemoveRow.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoveRowActionPerformed(evt);
            }
        });

        btnCreateMaterial.setText("Nytt material");
        btnCreateMaterial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCreateMaterialActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel1)
                        .addGap(227, 227, 227))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel5)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jScrollPane1))
                                .addGroup(layout.createSequentialGroup()
                                    .addGap(12, 12, 12)
                                    .addComponent(jLabel4)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(txtVikt, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(55, 55, 55)))
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtProduktnamn, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel3)
                                .addGap(14, 14, 14)
                                .addComponent(txtPris, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(69, 69, 69)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(125, 125, 125)
                                .addComponent(btnUploadImage))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(lblImage, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane2)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnAddMaterial)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnRemoveRow)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnCreateMaterial))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(chkLagerfor)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnLaggTillProdukt)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel2)
                                .addComponent(txtProduktnamn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtPris, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel3))
                        .addGap(23, 23, 23)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtVikt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4)))
                    .addComponent(lblImage, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(btnUploadImage)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAddMaterial)
                    .addComponent(btnRemoveRow)
                    .addComponent(btnCreateMaterial))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnLaggTillProdukt)
                    .addComponent(chkLagerfor))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnLaggTillProduktActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLaggTillProduktActionPerformed
        try {
            Product nyProdukt = dbm.createProduct();
            nyProdukt.setProductName(txtProduktnamn.getText());
            nyProdukt.setPrice(Integer.parseInt(txtPris.getText()));
            nyProdukt.setWeight(Double.parseDouble(txtVikt.getText()));
            nyProdukt.setDescription(txtBeskrivning.getText());
            nyProdukt.setStockItem(true);
            nyProdukt.setDiscontinued(false);
            nyProdukt.setCopyRightApproved(false);
            nyProdukt.setProductBaseId(0);  

            dbm.updateProduct(nyProdukt);

            if (base64Image != null) {
                imageManager.saveNewImage(base64Image, nyProdukt.getProductId(), "Produktbild", "");
            }

            javax.swing.JOptionPane.showMessageDialog(this, "Ny hatt tillagd!");

            txtProduktnamn.setText("");
            txtPris.setText("");
            txtVikt.setText("");
            txtBeskrivning.setText("");
            chkLagerfor.setSelected(true);

        }   catch (Exception e) {
            javax.swing.JOptionPane.showMessageDialog(this, "Fel: " + e.getMessage());
        }
        window.dispose();

        if (window.getParent() instanceof MainWindow mw) {
            mw.showProductListPanel(); 
        }
    }//GEN-LAST:event_btnLaggTillProduktActionPerformed

    private void btnUploadImageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUploadImageActionPerformed

        byte[] imageBytes = imageManager.uploadImage();

        ImageIcon icon = new ImageIcon(imageBytes);
        Image scaledImage = icon.getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH);
        lblImage.setIcon(new ImageIcon(scaledImage));
        lblImage.setText(null);
        base64Image = Base64.getEncoder().encodeToString(imageBytes);
    }//GEN-LAST:event_btnUploadImageActionPerformed

    private void btnAddMaterialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddMaterialActionPerformed
        materialTable.addRow(new Object[]{ "", "", "", "", "" });
        tblMaterial.setModel(materialTable);
    }//GEN-LAST:event_btnAddMaterialActionPerformed

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
    private javax.swing.JButton btnCreateMaterial;
    private javax.swing.JButton btnLaggTillProdukt;
    private javax.swing.JButton btnRemoveRow;
    private javax.swing.JButton btnUploadImage;
    private javax.swing.JCheckBox chkLagerfor;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblImage;
    private javax.swing.JTable tblMaterial;
    private javax.swing.JTextArea txtBeskrivning;
    private java.awt.TextField txtPris;
    private java.awt.TextField txtProduktnamn;
    private java.awt.TextField txtVikt;
    // End of variables declaration//GEN-END:variables
}
