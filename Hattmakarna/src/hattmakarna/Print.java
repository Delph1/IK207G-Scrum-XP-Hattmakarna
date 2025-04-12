/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hattmakarna;

import static hattmakarna.Hattmakarna.dbm;
import java.io.IOException;
import java.util.ArrayList;
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
    private Product product;
    
    public Print (Order order) throws IOException{

        //Base data collection
        String headerText = "Orderbekräftelse";
        int orderId = order.getId();
        int customerId = order.getCustomer_id();
        int orderTotal = 0;
        customer = dbm.getCustomer(customerId);
        String state = customer.getState();
        ArrayList<OrderLine> orderlines = dbm.getOrderlines(orderId);
        
        //Setting up the document for PDFbox
        float margin = 50;
        PDPage page = new PDPage();
        document.addPage(page);
        PDPageContentStream contentStream = new PDPageContentStream(document, page);
        contentStream.beginText();
        
        //Document header, y offset is from the bottom of the document = higher number, higher up on the page, x offset is the from the left border. Also, offset is RELATIVE, NOT ABSOLUTE.
        contentStream.setFont(PDType1Font.HELVETICA, 24);
        contentStream.newLineAtOffset(margin, 740);
        contentStream.showText(headerText);
        contentStream.endText();
        contentStream.beginText();
        contentStream.setFont(PDType1Font.HELVETICA, 14);
        contentStream.newLineAtOffset(420, 700);
        contentStream.showText("Ordernummer: " + Integer.toString(orderId));
        contentStream.newLineAtOffset(0, -16);
        contentStream.showText("Orderdatum: " + order.getOrder_date());
                
        //Customer data output below
        contentStream.newLineAtOffset(-370, 16);
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

        contentStream.moveTo(40, 610);
        contentStream.lineTo(580, 610);
        contentStream.stroke();
        
        //Table for orderlines ... it looks "OK"
        float yStart = 600;
        float yPosition = yStart;
        float rowHeight = 20;
        float[] columnWidths = {50, 70, 200, 60, 80}; 

        // Header row
        String[] headers = {"ID", "Produkt-ID", "Beskrivning", "Pris", "Säljas igen?"};
        drawRow(contentStream, margin, yPosition, rowHeight, columnWidths, headers, true);
        yPosition -= rowHeight;

        // Table rows
        for (OrderLine line : orderlines) {
            Product product = dbm.getProduct(line.getProductId());
            String product_name = product.getProductName();
            String[] data = {
                String.valueOf(line.getOrderLineId()),
                String.valueOf(line.getProductId()),
                product_name,
                String.valueOf(line.getPrice()),
                line.getCustomerApproval() ? "Ja" : "Nej"
            };
            drawRow(contentStream, margin, yPosition, rowHeight, columnWidths, data, false);
            orderTotal += line.getPrice();
            yPosition -= rowHeight;
        }
        
        //int orderTotalVAT = orderTotal * 1.25;
 
        yPosition = yPosition - 20;
        contentStream.beginText();
        contentStream.setFont(PDType1Font.HELVETICA, 20);
        contentStream.newLineAtOffset(240, yPosition);
        contentStream.showText("Summa exkl moms: " + orderTotal + " SEK");
//        yPosition = yPosition - 20;
//        contentStream.newLineAtOffset(600, yPosition);
//        contentStream.showText("Summa inkl moms: " + orderTotalVAT + " SEK");
        contentStream.endText();
        
        //Saves the file to the project folder
        contentStream.close(); 
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

    private static void drawRow(PDPageContentStream contentStream, float x, float y, float height, float[] colWidths, String[] texts, boolean isHeader) throws IOException {
        float cellX = x;

        contentStream.setLineWidth(1f);

        for (int i = 0; i < colWidths.length; i++) {
            float cellWidth = colWidths[i];
            // Draw cell rectangle
            contentStream.addRect(cellX, y - height, cellWidth, height);
            contentStream.stroke();

            // Text
            contentStream.beginText();
            contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
            contentStream.newLineAtOffset(cellX + 2, y - 15);
            //NULL-check
            String celltext = texts[i] != null ? texts[i] : "";
            contentStream.showText(celltext);
            contentStream.endText();

            cellX += cellWidth;
        }
    }
}

