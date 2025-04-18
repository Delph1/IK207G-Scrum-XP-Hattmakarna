
package hattmakarna;

import static hattmakarna.Hattmakarna.dbm;
import java.awt.Desktop;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import models.*;
import org.apache.pdfbox.pdmodel.*;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.printing.PDFPrintable;
import org.apache.pdfbox.printing.Scaling;

public class Print {
    
    PDDocument document = new PDDocument();
    private Order order;
    private Customer customer;
    private OrderLine orderline;
    private Product product;
    private String[][] data;
    private String startDate;
    private String stopDate;
    private int productId;
    
    public Print (Order order) {
        this.order = order;
    }
    
    public Print (String[][] materialLista, String startDate, String stopDate) {
        this.data = materialLista;
        this.startDate = startDate;
        this.stopDate = stopDate;
    }
    
    public Print (String[][] hatStats, int productId, String startDate, String stopDate) {
        this.data = hatStats;
        this.productId = productId;
        this.startDate = startDate;
        this.stopDate = stopDate;
    }

    public void showQoute() throws IOException {
        String headerText = "Offert";
        createPDFForOrder(headerText);
        Desktop.getDesktop().open(new File ("temp.pdf"));
    }
        
    public void printQoute() throws IOException, PrinterException {
        PrinterJob printJob = PrinterJob.getPrinterJob();
        String headerText = "Offert";
        document = createPDFForOrder(headerText);
        PDFPrintable printdoc = new PDFPrintable (PDDocument.load(new File("temp.pdf")), Scaling.SHRINK_TO_FIT);
        if (printJob.printDialog()) {
            printJob.setPrintable(printdoc);
            printJob.print();
        }
    }
    
    public void showConfirmation() throws IOException {
        String headerText = "Orderbekräftelse";
        createPDFForOrder(headerText);
        Desktop.getDesktop().open(new File ("temp.pdf"));
    }

    public void printConfirmation() throws IOException, PrinterException {
        PrinterJob printJob = PrinterJob.getPrinterJob();
        String headerText = "Orderbekräftelse";
        document = createPDFForOrder(headerText);
        PDFPrintable printdoc = new PDFPrintable (PDDocument.load(new File("temp.pdf")), Scaling.SHRINK_TO_FIT);
        if (printJob.printDialog()) {
            printJob.setPrintable(printdoc);
            printJob.print();
        }
    }
    
    private PDDocument createPDFForOrder (String header) throws IOException {

        customer = dbm.getCustomer(order.getCustomer_id());
        double orderTotal = 0;
        DecimalFormat df = new DecimalFormat("##.00");

        //Skapar upp variabler för dokumentet
        PDPage page = new PDPage();
        document.addPage(page);
        PDPageContentStream contentStream = new PDPageContentStream(document, page);
        contentStream.beginText();
        
        createOrderHeader(contentStream, order, customer, header);

        String[] headers = {"ID", "Produkt-ID", "Beskrivning", "Pris", "Säljas igen?"};
        float[] columnWidths = {50, 70, 200, 60, 80};
        float yStart = 600;
        ArrayList<OrderLine> orderlines = dbm.getOrderlines(order.getId());
        String[][] lines = new String[orderlines.size()][columnWidths.length];
        int i = 0;
        

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
            lines[i] = data;
            i += 1;
            orderTotal += line.getPrice();
        }
        
        float yPosition = createTable(contentStream, columnWidths, headers, lines, yStart);
        
        double orderTotalVAT = orderTotal * 1.25;
 
        yPosition = yPosition - 40;
        contentStream.beginText();
        contentStream.setFont(PDType1Font.HELVETICA, 20);
        contentStream.newLineAtOffset(240, yPosition);
        contentStream.showText("Summa exkl moms: " + df.format(orderTotal) + " SEK");
        contentStream.newLineAtOffset(0, -40);
        contentStream.showText("Moms: "); 
        contentStream.newLineAtOffset(190, 0);
        contentStream.showText(df.format(orderTotal * .25) + " SEK");
        contentStream.newLineAtOffset(-190, -40);
        contentStream.showText("Summa inkl moms: " + df.format(orderTotalVAT) + " SEK");
        contentStream.endText();

        //Saves the file to the project folder
        contentStream.close(); 
        String path = "./temp.pdf";

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
        return document;
    }
        
    private void createOrderHeader(PDPageContentStream contentStream, Order order, Customer customer, String Header) throws IOException {
        String state = customer.getState();
        
        //Dokumenthuvud, y offset räknas från botten = högre nummer, högre upp, x offset är från vänster. Alla värden är RELATIVA, INTE ABSOLUTA.
        contentStream.setFont(PDType1Font.HELVETICA, 24);
        contentStream.newLineAtOffset(50, 740);
        contentStream.showText(Header);
        contentStream.endText();
        contentStream.beginText();
        contentStream.setFont(PDType1Font.HELVETICA, 14);
        contentStream.newLineAtOffset(420, 700);
        contentStream.showText("Ordernummer: " + Integer.toString(order.getId()));
        contentStream.newLineAtOffset(0, -16);
        contentStream.showText("Orderdatum: " + order.getOrder_date());
                
        //Kunddata
        contentStream.newLineAtOffset(-370, 16);
        contentStream.showText("Kundnummer: " + Integer.toString(customer.getId()));
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
    }
    
    private float createTable(PDPageContentStream contentStream, float[] columnWidths, String[] headers, String[][] lines, float yStart) throws IOException {

        //Tabell
        float yPosition = yStart;
        float rowHeight = 20;

        // Rubrikrad
        drawRow(contentStream, 50, yPosition, rowHeight, columnWidths, headers, true);
        yPosition -= rowHeight;

        // Själva datan
        for (int i = 0; i < lines.length; i++) {
            drawRow(contentStream, 50, yPosition, rowHeight, columnWidths, lines[i], false);
            yPosition -= rowHeight;
        }
        return yPosition;
    }
   
    private static void drawRow(PDPageContentStream contentStream, float x, float y, float height, float[] colWidths, String[] texts, boolean isHeader) throws IOException {
        float cellX = x;

        contentStream.setLineWidth(1f);

        for (int i = 0; i < colWidths.length; i++) {
            float cellWidth = colWidths[i];
            // Rektangel
            contentStream.addRect(cellX, y - height, cellWidth, height);
            contentStream.stroke();

            // Text
            contentStream.beginText();
            if (isHeader) { 
                contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
            } else {
                contentStream.setFont(PDType1Font.HELVETICA, 12);
            }
            contentStream.newLineAtOffset(cellX + 2, y - 15);
            //NULL-kontroll
            String celltext = texts[i] != null ? texts[i] : "";
            contentStream.showText(celltext);
            contentStream.endText();

            cellX += cellWidth;
        }
    }
    
    public void showMaterialList() throws IOException {
        createMaterialList(data, startDate, stopDate);
        Desktop.getDesktop().open(new File ("materiallista.pdf"));
    }
    
    public void printMaterialList() throws IOException, PrinterException {
        PrinterJob printJob = PrinterJob.getPrinterJob();
        createMaterialList(data, startDate, stopDate);
        PDFPrintable printdoc = new PDFPrintable (PDDocument.load(new File("./materiallista.pdf")), Scaling.SHRINK_TO_FIT);
        if (printJob.printDialog()) {
            printJob.setPrintable(printdoc);
            printJob.print();
        }
    }
    
    public PDDocument createMaterialList(String[][] materialData, String startDate, String stopDate) throws IOException {
        
        String[] headers = {"ID", "Namn", "Färg", "Mängd"};
        float[] columnWidths = {50, 200, 60, 150};
        
        PDPage page = new PDPage();
        document.addPage(page);
        PDPageContentStream contentStream = new PDPageContentStream(document, page);
        contentStream.beginText();
        contentStream.setFont(PDType1Font.HELVETICA, 24);
        contentStream.newLineAtOffset(50, 740);
        contentStream.showText("Material att beställa: " + startDate + " - " + stopDate);
        contentStream.endText();

        contentStream.moveTo(40, 710);
        contentStream.lineTo(580, 710);
        contentStream.stroke();
        
        createTable(contentStream, columnWidths, headers, materialData, 680);

        //Sparar filen. Ett namn som varierar kan vara pås in plats?
        contentStream.close(); 
        String path = "materiallista.pdf";

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
        return document;
    }
    
    public void printHatStats() throws PrinterException, IOException {
        PrinterJob printJob = PrinterJob.getPrinterJob();
        createStatsList(data, productId);
        PDFPrintable printdoc = new PDFPrintable (PDDocument.load(new File("hatstats.pdf")), Scaling.SHRINK_TO_FIT);
        if (printJob.printDialog()) {
            printJob.setPrintable(printdoc);
            printJob.print();
        }
    }
    
    private void createHatHeader(PDPageContentStream contentStream, Product product) throws IOException {
        contentStream.beginText();
        contentStream.setFont(PDType1Font.HELVETICA, 14);
        contentStream.newLineAtOffset(50, 695);
        contentStream.showText("Produktnummer: " + Integer.toString(product.getProductId()));
        contentStream.newLineAtOffset(0, -16);
        contentStream.showText("Produktnamn: " + product.getProductName());
        contentStream.newLineAtOffset(0, -16);
        contentStream.showText("Pris: " + product.getPrice() + " SEK");
        contentStream.newLineAtOffset(0, -16);
        contentStream.showText("Vikt: " + String.valueOf(product.getWeight()).replace(".", ",") + " Kg");
        contentStream.newLineAtOffset(400, 48);
        String stockitem = product.getStockItem() ? "Ja" : "Nej";
        contentStream.showText("Lagervara: " + stockitem);
        contentStream.newLineAtOffset(0, -16);
        String copyright = product.getcopyRightApproved() ? "Ja" : "Nej";
        contentStream.showText("Copyright: " + copyright);
        contentStream.newLineAtOffset(0, -16);
        String discontinued = product.getcopyRightApproved() ? "Ja" : "Nej";
        contentStream.showText("Utgången vara: " + discontinued);
        int baseId = product.getBaseProductId();
        if (baseId > 0) { 
            contentStream.newLineAtOffset(0, -16);
            contentStream.showText("Baserad på: " + baseId);
        }
        contentStream.endText();

        contentStream.moveTo(40, 610);
        contentStream.lineTo(580, 610);
        contentStream.stroke();
    }
    
    private PDDocument createStatsList (String[][] data, int productId) throws IOException {
        String[] headers = {"Datum", "Antal sålda", "Summa"};
        
        float[] columnWidths = {150, 150, 150};
        
        PDPage page = new PDPage();
        document.addPage(page);
        PDPageContentStream contentStream = new PDPageContentStream(document, page);
        contentStream.beginText();
        contentStream.setFont(PDType1Font.HELVETICA, 24);
        contentStream.newLineAtOffset(50, 740);
        contentStream.showText("Antal sålda hattar: " + startDate + " - " + stopDate);
        contentStream.endText();

        contentStream.moveTo(40, 715);
        contentStream.lineTo(580, 715);
        contentStream.stroke();
        
        Product product = dbm.getProduct(productId);
        
        createHatHeader(contentStream, product);
        
        createTable(contentStream, columnWidths, headers, data, 600);
        
        //Sparar filen
        contentStream.close(); 
        String path = "hatstats.pdf";

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
        return document;
    }
}

