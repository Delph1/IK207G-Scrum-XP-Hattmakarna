package panels;

import static hattmakarna.Hattmakarna.dbm;
import hattmakarna.MainWindow;
import hattmakarna.ModularWindow;
import java.awt.print.PrinterException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
//import models.*;

import models.Order;
import models.OrderLine;
import models.Product;
import utils.Print;
import utils.Validator;

public class OrderPanel extends javax.swing.JPanel {

    private MainWindow window;
    private Order order;

    public OrderPanel(MainWindow window, int orderId) {
        // Vi tar emot och lagrar huvudfönstret som ett fält, då kan vi komma åt metoder som att byta panel
        this.window = window;
        initComponents();
        Order order = dbm.getOrder(orderId);
        this.constructorHelp(order);
    }

    public OrderPanel(MainWindow window) {
        // Vi tar emot och lagrar huvudfönstret som ett fält, då kan vi komma åt metoder som att byta panel
        this.window = window;
        initComponents();
        order = dbm.createOrder();
        constructorHelp(order);
    }

    private void constructorHelp(Order theOrder) {
        this.order = theOrder;
        tfCustomerID.setText("" + order.getCustomer_id());
        tfOrderlineID.setText("0");
        tfOrderID.setText("" + order.getId());
        tfOrderDate.setText("" + order.getOrder_date());
        //tfOrderStatus.setText(order.getOrder_status());
        cboxOrderstatus.getSelectedItem();
        tfShippingCostOrder.setText("" + order.getShippingCost());
        lblOrderlineID.setVisible(false);
        tfOrderlineID.setVisible(false);
        tblOrderline.setAutoResizeMode(tblOrderline.AUTO_RESIZE_OFF);
        tblOrderline.getColumnModel().getColumn(0).setMinWidth(0);
        tblOrderline.getColumnModel().getColumn(0).setMaxWidth(0);
        tblOrderline.getColumnModel().getColumn(1).setPreferredWidth(70);
        tblOrderline.getColumnModel().getColumn(2).setPreferredWidth(50);
        tblOrderline.getColumnModel().getColumn(3).setPreferredWidth(120);
        tblOrderline.getColumnModel().getColumn(4).setPreferredWidth(200);
        tblOrders.setAutoResizeMode(tblOrderline.AUTO_RESIZE_OFF);
        tblOrders.getColumnModel().getColumn(0).setPreferredWidth(70);
        tblOrders.getColumnModel().getColumn(1).setPreferredWidth(70);
        tblOrders.getColumnModel().getColumn(2).setPreferredWidth(70);
        ArrayList<Order> allCurrentOrders;
        DefaultTableModel tableModelOrders = (DefaultTableModel) tblOrders.getModel();
        allCurrentOrders = dbm.getOrders();
        for (Order order : allCurrentOrders) {
            tableModelOrders.addRow(new Object[]{order.getId(), order.getCustomer_id(), order.getOrder_date(), order.getOrder_status(), order.isExpress(), order.getShippingCost()});
        }
        btnRemoveOrderline.setEnabled(false);
        tblOrderline.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent event) {
                if (tblOrderline.getSelectedRow() < 0) {
                    btnRemoveOrderline.setEnabled(false);
                    orderLineDoneButton.setEnabled(false);
                } else {
                    btnRemoveOrderline.setEnabled(true);
                    orderLineDoneButton.setEnabled(true);
                }
            }
        });

        ArrayList<OrderLine> allOrderlines = dbm.getOrderlines(order.getId());
        DefaultTableModel tableModelOrderline = (DefaultTableModel) tblOrderline.getModel();

        for (OrderLine orderline : allOrderlines) {
            tableModelOrderline.addRow(new Object[]{orderline.getOrderLineId(), orderline.getProductId(), orderline.getPrice(), orderline.getCustomerApproval(), orderline.getDescription(), orderline.getHatStatus()});
        }
        
        Validator.setNumber(tfCustomerID);
         Validator.setNumber(tfShippingCostOrder);
         Validator.setNumber(tfProductID);
         Validator.setNumber(tfPrice);
         
                 
    }

    public void addOrderline(Object[] orderline) {
        DefaultTableModel tableModelOrderline = (DefaultTableModel) tblOrderline.getModel();
        tableModelOrderline.addRow(orderline);
        //Objektet ska se ut: {0, product_id, price, customer_approval, description});
    }

    private void sendOrder() throws IOException, PrinterException {
        Print sendOrder = new Print(order);
        sendOrder.printDeliveryNote();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane3 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        pnlNewOrder = new javax.swing.JPanel();
        lblOrderID = new javax.swing.JLabel();
        lblCustomerID = new javax.swing.JLabel();
        tfOrderID = new javax.swing.JTextField();
        tfCustomerID = new javax.swing.JTextField();
        lblOrderDate = new javax.swing.JLabel();
        tfOrderDate = new javax.swing.JTextField();
        lblOrderStatus = new javax.swing.JLabel();
        lblAddOrderline = new javax.swing.JLabel();
        lblOrderlineID = new javax.swing.JLabel();
        lblProductID = new javax.swing.JLabel();
        tfOrderlineID = new javax.swing.JTextField();
        tfProductID = new javax.swing.JTextField();
        lblPrice = new javax.swing.JLabel();
        tfPrice = new javax.swing.JTextField();
        lblCustomerApproval = new javax.swing.JLabel();
        lblDescription = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        taDescription = new javax.swing.JTextArea();
        btnAddOrderline = new javax.swing.JButton();
        cboxCustomerApproval = new javax.swing.JCheckBox();
        lblExpressOrder = new javax.swing.JLabel();
        cboxExpressOrder = new javax.swing.JCheckBox();
        lblShippingCostOrder = new javax.swing.JLabel();
        tfShippingCostOrder = new javax.swing.JTextField();
        cboxOrderstatus = new javax.swing.JComboBox<>();
        btnSpecialOrder = new javax.swing.JButton();
        btnModifyHat = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jPanel1 = new javax.swing.JPanel();
        btnRemoveOrderline = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblOrderline = new javax.swing.JTable();
        btnSaveOrder = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblOrders = new javax.swing.JTable();
        lblAllOrders = new javax.swing.JLabel();
        btnPrintOrderConfirmation = new javax.swing.JButton();
        btnPrintQuote = new javax.swing.JButton();
        lblPrint = new javax.swing.JLabel();
        orderLineDoneButton = new javax.swing.JButton();
        goBackButton = new javax.swing.JButton();
        btnSendOrder = new javax.swing.JButton();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane3.setViewportView(jTable1);

        setName(""); // NOI18N
        setPreferredSize(new java.awt.Dimension(1165, 607));

        pnlNewOrder.setOpaque(false);

        lblOrderID.setText("Order-ID");

        lblCustomerID.setText("Kund-ID");

        tfOrderID.setEnabled(false);

        lblOrderDate.setText("Orderdatum");

        tfOrderDate.setEnabled(false);

        lblOrderStatus.setText("Orderstatus");

        lblAddOrderline.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblAddOrderline.setText("Lägg till orderrad");

        lblOrderlineID.setText("Orderrad-ID");

        lblProductID.setText("Produkt-ID");

        tfOrderlineID.setEditable(false);

        lblPrice.setText("Pris");

        lblCustomerApproval.setText("Kundgodkännande");

        lblDescription.setText("Beskrivning");

        taDescription.setColumns(20);
        taDescription.setRows(5);
        jScrollPane1.setViewportView(taDescription);

        btnAddOrderline.setText("Lägg till");
        btnAddOrderline.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddOrderlineActionPerformed(evt);
            }
        });

        cboxCustomerApproval.setText("Godkänt");

        lblExpressOrder.setText("Express");

        cboxExpressOrder.setText("Expressbeställning");

        lblShippingCostOrder.setText("Fraktkostnad");

        cboxOrderstatus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Open", "Confirmed", "Ready", "Completed" }));

        btnSpecialOrder.setText("Specialhatt");
        btnSpecialOrder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSpecialOrderActionPerformed(evt);
            }
        });

        btnModifyHat.setText("Modifiera hatt");
        btnModifyHat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModifyHatActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlNewOrderLayout = new javax.swing.GroupLayout(pnlNewOrder);
        pnlNewOrder.setLayout(pnlNewOrderLayout);
        pnlNewOrderLayout.setHorizontalGroup(
            pnlNewOrderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlNewOrderLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlNewOrderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlNewOrderLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlNewOrderLayout.createSequentialGroup()
                        .addGroup(pnlNewOrderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblDescription, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblCustomerApproval, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblPrice, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblProductID, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblOrderlineID, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlNewOrderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cboxCustomerApproval)
                            .addComponent(cboxExpressOrder)
                            .addGroup(pnlNewOrderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(tfOrderID, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(tfOrderDate, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(cboxOrderstatus, javax.swing.GroupLayout.Alignment.LEADING, 0, 152, Short.MAX_VALUE)
                                .addComponent(tfCustomerID, javax.swing.GroupLayout.Alignment.LEADING))
                            .addGroup(pnlNewOrderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(tfPrice, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 152, Short.MAX_VALUE)
                                .addComponent(tfProductID, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(tfOrderlineID, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(tfShippingCostOrder, javax.swing.GroupLayout.Alignment.LEADING))))
                    .addGroup(pnlNewOrderLayout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(btnSpecialOrder)
                        .addGap(18, 18, 18)
                        .addComponent(btnModifyHat)
                        .addGap(18, 18, 18)
                        .addComponent(btnAddOrderline, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(pnlNewOrderLayout.createSequentialGroup()
                        .addGroup(pnlNewOrderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator1)
                            .addGroup(pnlNewOrderLayout.createSequentialGroup()
                                .addGroup(pnlNewOrderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(lblShippingCostOrder, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lblOrderStatus, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lblOrderDate, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lblCustomerID, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lblOrderID, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lblExpressOrder, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlNewOrderLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblAddOrderline, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(93, 93, 93))
        );
        pnlNewOrderLayout.setVerticalGroup(
            pnlNewOrderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlNewOrderLayout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(pnlNewOrderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblOrderID)
                    .addComponent(tfOrderID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlNewOrderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCustomerID)
                    .addComponent(tfCustomerID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlNewOrderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblOrderDate)
                    .addComponent(tfOrderDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlNewOrderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblOrderStatus)
                    .addComponent(cboxOrderstatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnlNewOrderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblExpressOrder)
                    .addComponent(cboxExpressOrder))
                .addGap(18, 18, 18)
                .addGroup(pnlNewOrderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblShippingCostOrder)
                    .addComponent(tfShippingCostOrder, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addComponent(lblAddOrderline)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlNewOrderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblOrderlineID)
                    .addComponent(tfOrderlineID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlNewOrderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblProductID)
                    .addComponent(tfProductID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlNewOrderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfPrice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblPrice))
                .addGap(18, 18, 18)
                .addGroup(pnlNewOrderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCustomerApproval)
                    .addComponent(cboxCustomerApproval))
                .addGap(18, 18, 18)
                .addGroup(pnlNewOrderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblDescription)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pnlNewOrderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAddOrderline)
                    .addComponent(btnSpecialOrder)
                    .addComponent(btnModifyHat))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 561, Short.MAX_VALUE)
        );

        btnRemoveOrderline.setText("Ta bort orderrad");
        btnRemoveOrderline.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoveOrderlineActionPerformed(evt);
            }
        });

        jScrollPane2.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        tblOrderline.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Orderrad-ID", "Produkt-ID", "Pris", "Kundgodkännande", "Beskrivning", "Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tblOrderline);
        if (tblOrderline.getColumnModel().getColumnCount() > 0) {
            tblOrderline.getColumnModel().getColumn(0).setResizable(false);
        }

        btnSaveOrder.setText("Spara ");
        btnSaveOrder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveOrderActionPerformed(evt);
            }
        });

        jScrollPane4.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane4.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        tblOrders.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Order-ID", "Kund-ID", "Datum"
            }
        ));
        jScrollPane4.setViewportView(tblOrders);

        lblAllOrders.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblAllOrders.setText("Alla ordrar");

        btnPrintOrderConfirmation.setText("Orderbekräftelse");
        btnPrintOrderConfirmation.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrintOrderConfirmationActionPerformed(evt);
            }
        });

        btnPrintQuote.setText("Offert");
        btnPrintQuote.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrintQuoteActionPerformed(evt);
            }
        });

        lblPrint.setText("Skriv ut:");

        orderLineDoneButton.setText("Klarmarkera rad");
        orderLineDoneButton.setEnabled(false);
        orderLineDoneButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                orderLineDoneButtonActionPerformed(evt);
            }
        });

        goBackButton.setText("Tillbaka");
        goBackButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                goBackButtonActionPerformed(evt);
            }
        });

        btnSendOrder.setText("Skicka");
        btnSendOrder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSendOrderActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlNewOrder, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(lblPrint)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btnPrintQuote, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btnPrintOrderConfirmation)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(goBackButton)
                            .addGap(18, 18, 18)
                            .addComponent(btnSaveOrder))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(btnRemoveOrderline, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(orderLineDoneButton, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 517, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(445, 445, 445)
                        .addComponent(btnSendOrder)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 46, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblAllOrders, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(pnlNewOrder, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(btnRemoveOrderline)
                                    .addComponent(orderLineDoneButton)
                                    .addComponent(lblAllOrders))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 442, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 443, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(lblPrint)
                                                .addComponent(btnPrintQuote)
                                                .addComponent(btnPrintOrderConfirmation))
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(goBackButton)
                                                .addComponent(btnSaveOrder)))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnSendOrder)))
                        .addGap(0, 57, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddOrderlineActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddOrderlineActionPerformed
                boolean tfProductIDValid = Validator.isValid(tfProductID);
        boolean tfPriceValid = Validator.isValid(tfPrice);
        if (tfProductIDValid && tfPriceValid) {
        int orderLine_id = Integer.parseInt(tfOrderlineID.getText());
        int product_id = Integer.parseInt(tfProductID.getText());
        int price = Integer.parseInt(tfPrice.getText());
        boolean customer_approval = cboxCustomerApproval.isSelected();
        String description = taDescription.getText();
        String status = "";

        DefaultTableModel tableModelOrderline = (DefaultTableModel) tblOrderline.getModel();
        tableModelOrderline.addRow(new Object[]{orderLine_id, product_id, price, customer_approval, description, status});

        tfProductID.setText("");
        tfPrice.setText("");
        taDescription.setText("");
        }
    }//GEN-LAST:event_btnAddOrderlineActionPerformed

    private void btnSaveOrderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveOrderActionPerformed
        cboxOrderstatus.setSelectedIndex(1);
        boolean tfCustomerIDValid = Validator.isValid(tfCustomerID);
        boolean tfShippingCostOrderValid = Validator.isValid(tfShippingCostOrder);
        
        if (tfCustomerIDValid && tfShippingCostOrderValid) {
            int customer_id = Integer.parseInt(tfCustomerID.getText());
            LocalDate order_date = LocalDate.parse(tfOrderDate.getText());
            //String order_status = tfOrderStatus.getText();
            String order_status = cboxOrderstatus.getSelectedItem().toString();
            boolean express = cboxExpressOrder.isSelected();
            int shippingCost = Integer.parseInt(tfShippingCostOrder.getText());

            order.setCustomer_id(customer_id);
            order.setOrder_date(order_date);
            order.setOrder_status(order_status);
            order.setExpress(express);
            order.setShippingCost(shippingCost);

            System.out.println(shippingCost);
            dbm.updateOrder(order);

            DefaultTableModel tableModelOrderline = (DefaultTableModel) tblOrderline.getModel();

            for (int i = 0; i < tableModelOrderline.getRowCount(); i++) {
                int null_orderId = 0;

                if (tableModelOrderline.getValueAt(i, 0).equals(null_orderId)) {
                    OrderLine orderLine = dbm.createOrderLine(order.getId());
                    tableModelOrderline.setValueAt(orderLine.getOrderLineId(), i, 0);
                    int prod_id = Integer.parseInt(tableModelOrderline.getValueAt(i, 1).toString());
                    int price = Integer.parseInt(tableModelOrderline.getValueAt(i, 2).toString());
                    boolean customer_approval = (Boolean) tableModelOrderline.getValueAt(i, 3);
                    String description = tableModelOrderline.getValueAt(i, 4).toString();
                    Product orderLineHat = dbm.getProduct(prod_id); //hämtar ut produkten för att kunna kolla om det är en lagervara
                    String hatStatus = orderLineHat.getStockItem() ? "ready" : "not ready"; //sätter status beroende på om det är en lagervara eller inte
                    orderLine.setProductId(prod_id);
                    orderLine.setPrice(price);
                    orderLine.setCustomerApproval(customer_approval);
                    orderLine.setDescription(description);
                    orderLine.setHatStatus(hatStatus);

                    dbm.updateOrderLine(orderLine);
                }

            }

            boolean alILinesReady = true;

            ArrayList<OrderLine> orderlines = dbm.getOrderlines(order.getId());
            for (OrderLine line : orderlines) {
                if (!line.getHatStatus().contentEquals("ready")) {
                    return;
                }
            }

            if (alILinesReady) {
                order.setOrder_status("Completed");
                dbm.updateOrder(order);
                int svar = JOptionPane.showConfirmDialog(this, "Din order är redo att skickas. Vill du skicka den nu?", "Bekräfta order", JOptionPane.YES_NO_OPTION);
                if (svar == JOptionPane.YES_OPTION) {
                    try {
                        sendOrder();
                    } catch (PrinterException ex) {
                        Logger.getLogger(OrderPanel.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IOException ex) {
                        Logger.getLogger(OrderPanel.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    order.setOrder_status("Despatched");
                    dbm.updateOrder(order);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Beställningen är sparad"); 
            }
        } else {
            JOptionPane.showMessageDialog(this, "Ogiltlig data"); 
        }
    }//GEN-LAST:event_btnSaveOrderActionPerformed

    private void btnRemoveOrderlineActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoveOrderlineActionPerformed
        int selectedTableRowID = tblOrderline.getSelectedRow();
        int selectedOrderlineID = (int) tblOrderline.getValueAt(selectedTableRowID, 0);
        DefaultTableModel OrderLine = (DefaultTableModel) tblOrderline.getModel();
        dbm.deleteOrderLine(selectedOrderlineID);
        System.out.println("Orderline " + selectedOrderlineID + " has been deleted");
        OrderLine.removeRow(selectedTableRowID);
    }//GEN-LAST:event_btnRemoveOrderlineActionPerformed

    private void btnSpecialOrderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSpecialOrderActionPerformed
        ModularWindow modularWindow = new ModularWindow(window, true);
        modularWindow.specialOrder(this);
        modularWindow.setVisible(true);
        modularWindow.setAlwaysOnTop(true);
    }//GEN-LAST:event_btnSpecialOrderActionPerformed

    private void btnModifyHatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModifyHatActionPerformed
        if (tfProductID.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ange ett produktnummer i fältet ovan först.");
            return;
        }
        ModularWindow modularWindow = new ModularWindow(window, true);
        Product newProduct = dbm.getProduct(Integer.parseInt(tfProductID.getText()));
        modularWindow.modifyHat(newProduct, this);
        modularWindow.setVisible(true);
        modularWindow.setAlwaysOnTop(true);
    }//GEN-LAST:event_btnModifyHatActionPerformed

    private void btnPrintQuoteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrintQuoteActionPerformed
        int orderNo = Integer.parseInt(tfOrderID.getText());
        if (orderNo != 0) {
            Order order = dbm.getOrder(orderNo);
            try {
                Print printOrder = new Print(order);
                printOrder.printQuote();
            } catch (IOException e) {
                System.err.println(e.getMessage());
            } catch (PrinterException ex) {
                System.err.println(ex.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(this, "Du behöver spara din order först.");
        }
    }//GEN-LAST:event_btnPrintQuoteActionPerformed

    private void btnPrintOrderConfirmationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrintOrderConfirmationActionPerformed
        int orderNo = Integer.parseInt(tfOrderID.getText());
        if (orderNo != 0) {
            Order order = dbm.getOrder(orderNo);
            try {
                Print printOrder = new Print(order);
                printOrder.printConfirmation();
            } catch (IOException e) {
                System.err.println(e.getMessage());
            } catch (PrinterException ex) {
                System.err.println(ex.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(this, "Du behöver spara din order först.");
        }
    }//GEN-LAST:event_btnPrintOrderConfirmationActionPerformed

    private void orderLineDoneButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_orderLineDoneButtonActionPerformed
        int selectedTableRowID = tblOrderline.getSelectedRow();
        int selectedOrderlineID = (int) tblOrderline.getValueAt(selectedTableRowID, 0);
        OrderLine line = dbm.getOrderLine(selectedOrderlineID);
        line.setHatStatus("ready");
        dbm.updateOrderLine(line);
        DefaultTableModel tableModelOrderline = (DefaultTableModel) tblOrderline.getModel();
        tableModelOrderline.setValueAt("ready", selectedTableRowID, 5);
        tableModelOrderline.fireTableDataChanged();

        ArrayList<OrderLine> orderlines = dbm.getOrderlines(line.getOrderId());
        for (OrderLine ol : orderlines) {
            if (!ol.getHatStatus().contentEquals("ready")) {
                return;
            }
        }

        Order order = dbm.getOrder(line.getOrderId());
        order.setOrder_status("Completed");
        dbm.updateOrder(order);
    }//GEN-LAST:event_orderLineDoneButtonActionPerformed

    private void goBackButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_goBackButtonActionPerformed
        window.showOrderlistPanel();
    }//GEN-LAST:event_goBackButtonActionPerformed

    private void btnSendOrderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSendOrderActionPerformed

        boolean alILinesReady = true;

        ArrayList<OrderLine> orderlines = dbm.getOrderlines(order.getId());
        for (OrderLine line : orderlines) {
            if (!line.getHatStatus().contentEquals("ready")) {
                JOptionPane.showMessageDialog(this, "Beställningen är inte redo att skickas."); 
                return;
            }
        }

        if (alILinesReady) {
            order.setOrder_status("Completed");
            dbm.updateOrder(order);
            int svar = JOptionPane.showConfirmDialog(this, "Din order är redo att skickas. Vill du skicka den nu?", "Bekräfta order", JOptionPane.YES_NO_OPTION);
            if (svar == JOptionPane.YES_OPTION) {
                try {
                    sendOrder();
                } catch (PrinterException ex) {
                    Logger.getLogger(OrderPanel.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(OrderPanel.class.getName()).log(Level.SEVERE, null, ex);
                }
                order.setOrder_status("Despatched");
                dbm.updateOrder(order);
            }
        }
    }//GEN-LAST:event_btnSendOrderActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddOrderline;
    private javax.swing.JButton btnModifyHat;
    private javax.swing.JButton btnPrintOrderConfirmation;
    private javax.swing.JButton btnPrintQuote;
    private javax.swing.JButton btnRemoveOrderline;
    private javax.swing.JButton btnSaveOrder;
    private javax.swing.JButton btnSendOrder;
    private javax.swing.JButton btnSpecialOrder;
    private javax.swing.JCheckBox cboxCustomerApproval;
    private javax.swing.JCheckBox cboxExpressOrder;
    private javax.swing.JComboBox<String> cboxOrderstatus;
    private javax.swing.JButton goBackButton;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel lblAddOrderline;
    private javax.swing.JLabel lblAllOrders;
    private javax.swing.JLabel lblCustomerApproval;
    private javax.swing.JLabel lblCustomerID;
    private javax.swing.JLabel lblDescription;
    private javax.swing.JLabel lblExpressOrder;
    private javax.swing.JLabel lblOrderDate;
    private javax.swing.JLabel lblOrderID;
    private javax.swing.JLabel lblOrderStatus;
    private javax.swing.JLabel lblOrderlineID;
    private javax.swing.JLabel lblPrice;
    private javax.swing.JLabel lblPrint;
    private javax.swing.JLabel lblProductID;
    private javax.swing.JLabel lblShippingCostOrder;
    private javax.swing.JButton orderLineDoneButton;
    private javax.swing.JPanel pnlNewOrder;
    private javax.swing.JTextArea taDescription;
    private javax.swing.JTable tblOrderline;
    private javax.swing.JTable tblOrders;
    private javax.swing.JTextField tfCustomerID;
    private javax.swing.JTextField tfOrderDate;
    private javax.swing.JTextField tfOrderID;
    private javax.swing.JTextField tfOrderlineID;
    private javax.swing.JTextField tfPrice;
    private javax.swing.JTextField tfProductID;
    private javax.swing.JTextField tfShippingCostOrder;
    // End of variables declaration//GEN-END:variables
}
