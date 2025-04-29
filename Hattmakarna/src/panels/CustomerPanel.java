package panels;

import hattmakarna.Hattmakarna;
import static hattmakarna.Hattmakarna.dbm;
import hattmakarna.MainWindow;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import models.Customer;
import utils.Validator;

//Fält
public class CustomerPanel extends javax.swing.JPanel {

    private MainWindow window;
    private Customer customer;
    ArrayList<String> phoneNumbers;
    ArrayList<String> mails;
    private DefaultListModel<String> phoneNumberModel;
    private DefaultListModel<String> mailModel;

    //Konstruktor 
    public CustomerPanel(MainWindow window, Customer customer) {
        // Vi tar emot och lagrar huvudfönstret som ett fält, då kan vi komma åt metoder som att byta panel
        this.window = window;
        initComponents();
        phoneNumberModel = new DefaultListModel(); 
        phoneNumbers = new ArrayList();
        mails = new ArrayList(); 
        mailModel = new DefaultListModel(); 
        this.customer = customer;
        if (this.customer != null) {
            firstNameTextField.setText(this.customer.getFirstName());
            lastNameTextField.setText(this.customer.getLastName());
            streetAddressTextField.setText(this.customer.getStreetName());
            postalCodeTextField.setText(this.customer.getPostalCode());
            stateTextField.setText(this.customer.getState());
            countryTextField.setText(this.customer.getCountry());

            //Hämtar innehållet till listorna i vyn
            phoneNumbers = this.customer.getPhoneNumbers();
            mails = this.customer.getEmails();
        }

        //Om listan ska korrigeras
        phoneNumberList.setModel(phoneNumberModel);
        mailList.setModel(mailModel);
        populateListModels(); 
        
        Validator.setLength(firstNameTextField, 2, 50);
        Validator.setLength(lastNameTextField, 2, 50);
        Validator.setLength(streetAddressTextField, 2, 50);
        Validator.setLength(postalCodeTextField, 2, 10);
        Validator.setLength(stateTextField, 2, 50);
        Validator.setLength(countryTextField, 2, 50);
        Validator.setNumber(phoneNumberTextField);
        Validator.setEmail(emailTextField);
    }

    //Fyller i listmodellerna med datan som finns i ArrayListorna
    private void populateListModels() {
        phoneNumberModel.clear();
        mailModel.clear();
        phoneNumberModel.addAll(phoneNumbers);
        mailModel.addAll(mails);
    }

//Lägger till en kund
    private void newCustomer() {

        String firstName = firstNameTextField.getText();
        String lastName = lastNameTextField.getText();
        String streetAddress = streetAddressTextField.getText();
        String postal_code = postalCodeTextField.getText();
        String postal_city = cityTextField.getText();
        String state = stateTextField.getText();
        String country = countryTextField.getText();

        this.customer = dbm.createCustomer(firstName, lastName, streetAddress, postal_code, postal_city, state, country, phoneNumbers, mails);
    }

    public void updateCustomer() {
        String firstName = firstNameTextField.getText();
        String lastName = lastNameTextField.getText();
        String streetAddress = streetAddressTextField.getText();
        String postal_code = postalCodeTextField.getText();
        String postal_city = cityTextField.getText();
        String state = stateTextField.getText();
        String country = countryTextField.getText();
        this.customer.setFirstName(firstName);
        this.customer.setLastName(lastName);
        this.customer.setStreetName(streetAddress);
        this.customer.setPostalCode(postal_code);
        this.customer.setPostalCity(postal_city);
        this.customer.setState(state);
        this.customer.setCountry(country);
        
        this.customer.setEmail(mails);
        this.customer.setPhoneNumbers(phoneNumbers);
        dbm.updateCustomer(this.customer);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        customerPanel = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        firstNameTextField = new javax.swing.JTextField();
        lastNameTextField = new javax.swing.JTextField();
        streetAddressTextField = new javax.swing.JTextField();
        postalCodeTextField = new javax.swing.JTextField();
        cityTextField = new javax.swing.JTextField();
        stateTextField = new javax.swing.JTextField();
        countryTextField = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        saveCustomerButton = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        phoneNumberList = new javax.swing.JList<>();
        phoneNumberTextField = new javax.swing.JTextField();
        addEmailButton = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        removePhoneNumberButton = new javax.swing.JButton();
        addPhonNumberButton = new javax.swing.JButton();
        removeMailButton = new javax.swing.JButton();
        emailTextField = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        mailList = new javax.swing.JList<>();
        backButton = new javax.swing.JButton();

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        jLabel12.setText("jLabel12");

        setEnabled(false);
        setName(""); // NOI18N
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel1.setText("Kundformulär");
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(296, 6, -1, -1));

        customerPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        customerPanel.setToolTipText("");
        customerPanel.setName(""); // NOI18N

        jLabel3.setText("Förnamn");

        jLabel4.setText("Efternamn");

        jLabel5.setText("Adress");

        jLabel6.setText("Postnummer");

        jLabel7.setText("Stad");

        jLabel8.setText("Stat");

        jLabel9.setText("Land");

        javax.swing.GroupLayout customerPanelLayout = new javax.swing.GroupLayout(customerPanel);
        customerPanel.setLayout(customerPanelLayout);
        customerPanelLayout.setHorizontalGroup(
            customerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(customerPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(customerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(customerPanelLayout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(postalCodeTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 137, Short.MAX_VALUE))
                    .addGroup(customerPanelLayout.createSequentialGroup()
                        .addGroup(customerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(customerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 68, Short.MAX_VALUE)
                                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jLabel9))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(customerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(firstNameTextField)
                            .addComponent(lastNameTextField)
                            .addComponent(streetAddressTextField)
                            .addComponent(cityTextField)
                            .addComponent(stateTextField)
                            .addComponent(countryTextField))))
                .addContainerGap(41, Short.MAX_VALUE))
        );
        customerPanelLayout.setVerticalGroup(
            customerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(customerPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(customerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(firstNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(customerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(lastNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addGroup(customerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(streetAddressTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(customerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(postalCodeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(customerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(cityTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(customerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(stateTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addGap(18, 18, 18)
                .addGroup(customerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(countryTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addContainerGap(83, Short.MAX_VALUE))
        );

        add(customerPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 50, 260, 320));
        customerPanel.getAccessibleContext().setAccessibleName("");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setText("Registrera ny kund");
        add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 28, -1, -1));

        saveCustomerButton.setText("Spara kund");
        saveCustomerButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveCustomerButtonActionPerformed(evt);
            }
        });
        add(saveCustomerButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 390, 119, -1));

        jLabel10.setText("Telefonnummer");
        add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(389, 50, -1, -1));

        phoneNumberList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                phoneNumberListMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(phoneNumberList);

        add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(313, 112, 197, 90));

        phoneNumberTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                phoneNumberTextFieldActionPerformed(evt);
            }
        });
        add(phoneNumberTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(311, 78, 199, 23));

        addEmailButton.setText("Lägg till");
        addEmailButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addEmailButtonActionPerformed(evt);
            }
        });
        add(addEmailButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 250, -1, -1));

        jLabel11.setText("E-post");
        add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 220, -1, -1));

        removePhoneNumberButton.setText("Ta bort");
        removePhoneNumberButton.setEnabled(false);
        removePhoneNumberButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removePhoneNumberButtonActionPerformed(evt);
            }
        });
        add(removePhoneNumberButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(517, 112, -1, -1));

        addPhonNumberButton.setText("Lägg till");
        addPhonNumberButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addPhonNumberButtonActionPerformed(evt);
            }
        });
        add(addPhonNumberButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(516, 78, -1, -1));

        removeMailButton.setText("Ta bort");
        removeMailButton.setEnabled(false);
        removeMailButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeMailButtonActionPerformed(evt);
            }
        });
        add(removeMailButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 280, -1, -1));
        add(emailTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 250, 197, -1));

        mailList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mailListMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(mailList);

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 280, 195, 90));

        backButton.setText("Tillbaka");
        backButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backButtonActionPerformed(evt);
            }
        });
        add(backButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 390, -1, -1));
    }// </editor-fold>//GEN-END:initComponents

    private void saveCustomerButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveCustomerButtonActionPerformed

boolean validFirstName = Validator.isValid(firstNameTextField);
boolean validLastName = Validator.isValid(lastNameTextField);
boolean validStreet = Validator.isValid(streetAddressTextField);
boolean validPostalCode = Validator.isValid(postalCodeTextField);
boolean validCity = Validator.isValid(cityTextField);
boolean validState = Validator.isValid(stateTextField);
boolean validCountry = Validator.isValid(countryTextField);

if (!validFirstName || !validLastName || !validStreet || !validPostalCode || !validCity || !validState || !validCountry) {
   
            JOptionPane.showMessageDialog(this, "Alla fält inte korrekt ifyllda."); 
        } else {
            if (this.customer == null) {
                newCustomer();
                JOptionPane.showMessageDialog(this, "Kunden är sparad"); 


            } else {
                updateCustomer();
                JOptionPane.showMessageDialog(this, "Kunden är uppdaterad"); 
            }
        } 

    }//GEN-LAST:event_saveCustomerButtonActionPerformed

    private void addPhonNumberButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addPhonNumberButtonActionPerformed
        if(Validator.isValid(phoneNumberTextField)){
            phoneNumbers.add(phoneNumberTextField.getText()); 
            populateListModels(); 
        }
    }//GEN-LAST:event_addPhonNumberButtonActionPerformed

    private void phoneNumberTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_phoneNumberTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_phoneNumberTextFieldActionPerformed

    private void addEmailButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addEmailButtonActionPerformed
        if(Validator.isValid(emailTextField)){
           mails.add(emailTextField.getText());
           populateListModels();
        }
    }//GEN-LAST:event_addEmailButtonActionPerformed

    private void removePhoneNumberButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removePhoneNumberButtonActionPerformed
       phoneNumbers.remove(phoneNumberList.getSelectedIndex());
       populateListModels(); 
       removePhoneNumberButton.setEnabled(false);
    }//GEN-LAST:event_removePhoneNumberButtonActionPerformed

    private void removeMailButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeMailButtonActionPerformed
        mails.remove(mailList.getSelectedIndex()); 
        populateListModels(); 
        removeMailButton.setEnabled(false);
    }//GEN-LAST:event_removeMailButtonActionPerformed

    private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backButtonActionPerformed
       window.showCustomerListPanel();
    }//GEN-LAST:event_backButtonActionPerformed

    private void phoneNumberListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_phoneNumberListMouseClicked
        removePhoneNumberButton.setEnabled(true);
    }//GEN-LAST:event_phoneNumberListMouseClicked

    private void mailListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mailListMouseClicked
       removeMailButton.setEnabled(true);
    }//GEN-LAST:event_mailListMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addEmailButton;
    private javax.swing.JButton addPhonNumberButton;
    private javax.swing.JButton backButton;
    private javax.swing.JTextField cityTextField;
    private javax.swing.JTextField countryTextField;
    private javax.swing.JPanel customerPanel;
    private javax.swing.JTextField emailTextField;
    private javax.swing.JTextField firstNameTextField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField lastNameTextField;
    private javax.swing.JList<String> mailList;
    private javax.swing.JList<String> phoneNumberList;
    private javax.swing.JTextField phoneNumberTextField;
    private javax.swing.JTextField postalCodeTextField;
    private javax.swing.JButton removeMailButton;
    private javax.swing.JButton removePhoneNumberButton;
    private javax.swing.JButton saveCustomerButton;
    private javax.swing.JTextField stateTextField;
    private javax.swing.JTextField streetAddressTextField;
    // End of variables declaration//GEN-END:variables
}
