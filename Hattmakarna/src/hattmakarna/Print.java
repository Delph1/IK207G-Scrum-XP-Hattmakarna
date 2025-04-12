/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hattmakarna;

import static hattmakarna.Hattmakarna.dbm;
import java.io.IOException;
import models.*;
import org.apache.pdfbox.pdmodel.*;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

/**
 *
 * @author nolle
 */
public class Print {
    
    PDDocument document = new PDDocument();
    private Order order;
    private Customer customer;
    private OrderLine orderline;
    
    public Print (Order order) throws IOException{

        //Base data collection
        String headerText = "Orderbekräftelse";
        int orderId = order.getId();
        int customerId = order.getCustomer_id();
        customer = dbm.getCustomer(customerId);
        String state = customer.getState();
        
        
        //Setting up the document for PDFbox
        PDPage page = new PDPage();
        document.addPage(page);
        PDPageContentStream contentStream = new PDPageContentStream(document, page);
        contentStream.beginText();
        
        //Document header, y offset is from the bottom of the document = higher number, higher up on the page, x offset is the from the left border. Also, offset is RELATIVE, NOT ABSOLUTE.
        contentStream.setFont(PDType1Font.HELVETICA, 24);
        contentStream.newLineAtOffset(50, 740);
        contentStream.showText(headerText);
        contentStream.endText();
        contentStream.beginText();
        contentStream.setFont(PDType1Font.HELVETICA, 14);
        contentStream.newLineAtOffset(450, 700);
        contentStream.showText("Ordernummer: " + Integer.toString(orderId));

        //Customer data output below
        contentStream.newLineAtOffset(-400, 0);
        contentStream.showText("Kundnummer: " + Integer.toString(customerId));
        contentStream.newLineAtOffset(0, -16);
        contentStream.showText("Namn: " + customer.getFirstName() + " " + customer.getLastName());
        contentStream.newLineAtOffset(0, -16);
        contentStream.showText("Gata: " + customer.getStreetName());
        contentStream.newLineAtOffset(0, -16);
        contentStream.showText("Postadress: " + customer.getPostalCode() + " " + customer.getPostalCity());
        if ( state != null && !state.isEmpty()) {
            contentStream.newLineAtOffset(0, -16);
            contentStream.showText("Delstat: " + state);
        }
        contentStream.newLineAtOffset(0, -16);
        contentStream.showText("Land: " + customer.getCountry());
        
        contentStream.endText();

        contentStream.close(); 

    
        //Saves the file to the project folder
        String path = "./test.pdf";
        try {
            document.save(path);  
        } catch(Exception e) {
            System.err.println("");
        }
        try {
            document.close();
        }
        catch (Exception e) {
            System.err.println("");
    }
  }
}


