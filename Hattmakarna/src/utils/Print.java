
package utils;

import static hattmakarna.Hattmakarna.dbm;
import java.awt.Desktop;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Map;
import models.*;
import org.apache.pdfbox.pdmodel.*;
import org.apache.pdfbox.pdmodel.font.PDType0Font;
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
    private int customerId;
    private Boolean IsExpress;
    
    public Print (Order order) {
        this.order = order;
        this.IsExpress = order.isExpress();
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
        public Print (String[][] hatStats, String startDate, String stopDate,int customerId) {
        this.data = hatStats;
        this.customerId = customerId;
        this.startDate = startDate;
        this.stopDate = stopDate;
    }

    public void showQuote() throws IOException {
        String headerText = "Offert";
        createOrderDocument(headerText);
        Desktop.getDesktop().open(new File ("temp.pdf"));
    }
        public void showDeliveryNote() throws IOException {

        createDeliveryNoteDocument();
        Desktop.getDesktop().open(new File ("temp.pdf"));
    }
    public void printQuote() throws IOException, PrinterException {
        PrinterJob printJob = PrinterJob.getPrinterJob();
        String headerText = "Offert";
        document = createOrderDocument(headerText);
        PDFPrintable printdoc = new PDFPrintable (PDDocument.load(new File("temp.pdf")), Scaling.SHRINK_TO_FIT);
        if (printJob.printDialog()) {
            printJob.setPrintable(printdoc);
            printJob.print();
        }
    }
    
    public void showConfirmation() throws IOException {
        String headerText = "Orderbekräftelse";
        createOrderDocument(headerText);
        Desktop.getDesktop().open(new File ("temp.pdf"));
    }

    public void printConfirmation() throws IOException, PrinterException {
        PrinterJob printJob = PrinterJob.getPrinterJob();
        String headerText = "Orderbekräftelse";
        document = createOrderDocument(headerText);
        PDFPrintable printdoc = new PDFPrintable (PDDocument.load(new File("temp.pdf")), Scaling.SHRINK_TO_FIT);
        if (printJob.printDialog()) {
            printJob.setPrintable(printdoc);
            printJob.print();
        }
    }
    private PDDocument createDeliveryNoteDocument() throws IOException {

    customer = dbm.getCustomer(order.getCustomer_id());
    Map<String, String> lang = dbm.getDeliveryNoteLanguage(customer.getCountry());
    ArrayList<OrderLine> orderlines = dbm.getOrderlines(order.getId());

    PDPage page = new PDPage();
    PDDocument doc = new PDDocument();
    doc.addPage(page);
    PDPageContentStream cs = new PDPageContentStream(doc, page);
    cs.beginText();
    PDType0Font uniFont = PDType0Font.load(doc, new File("src/fonts/unifont-16.0.03.ttf"));
    cs.setFont(uniFont, 24);
    cs.newLineAtOffset(50, 740);
    cs.showText(lang.get("delivery_note"));
    cs.endText();

    // Kund- och orderinfo
    cs.beginText();
    cs.setFont(uniFont, 14);
    cs.newLineAtOffset(50, 700);
    cs.showText(lang.get("order_number") + ": " + order.getId());
    cs.newLineAtOffset(0, -16);
    cs.showText(lang.get("order_date") + ": " + order.getOrder_date());
    cs.newLineAtOffset(0, -16);
    cs.showText(lang.get("customer_number") + ": " + customer.getId());
    cs.newLineAtOffset(0, -16);
    cs.showText(lang.get("name") + ": " + customer.getFirstName() + " " + customer.getLastName());
    cs.newLineAtOffset(0, -16);
    cs.showText(lang.get("street") + ": " + customer.getStreetName());
    cs.newLineAtOffset(0, -16);
    cs.showText(lang.get("postal") + ": " + customer.getPostalCode() + " " + customer.getPostalCity());
    if (customer.getState() != null && !customer.getState().isEmpty()) {
        cs.newLineAtOffset(0, -16);
        cs.showText(lang.get("state") + ": " + customer.getState());
    }
    cs.newLineAtOffset(0, -16);
    cs.showText(lang.get("country") + ": " + customer.getCountry());
    cs.newLineAtOffset(0, -16);
    cs.showText(lang.get("tariff") + ": 6505.00.90"); // Hårdkodat tullnummer för hattar
       cs.newLineAtOffset(0, -16);
    cs.endText();



    float[] colWidths = {70, 240, 80, 80, 80};
    String[] headers = {
        lang.get("product_id"),
        lang.get("description"),
        lang.get("quantity"),
        lang.get("unit"),
        lang.get("total")
    };

    String[][] rows = new String[orderlines.size()][headers.length];
    DecimalFormat df = new DecimalFormat("##.00");

    for (int i = 0; i < orderlines.size(); i++) {
        OrderLine ol = orderlines.get(i);
        Product p = dbm.getProduct(ol.getProductId());
        double totalPrice = 1 * ol.getPrice();
        rows[i] = new String[]{
            String.valueOf(ol.getProductId()),
            p.getProductName(),
            String.valueOf(1),
            df.format(ol.getPrice()),
            df.format(totalPrice)
        };
    }

    createTable(cs, colWidths, headers, rows, 580);
    cs.close();

    try {
        doc.save("temp.pdf");
    } catch (Exception e) {
        System.err.println("Fel vid sparande av följesedel");
    }
    try {
        doc.close();
    } catch (Exception e) {
        System.err.println("Fel vid stängning av PDF");
    }

    return doc;
}

    private PDDocument createOrderDocument(String header) throws IOException {

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
            double price = line.getPrice();
            String[] data = {
                String.valueOf(line.getOrderLineId()),
                String.valueOf(line.getProductId()),
                product_name,
                IsExpress ? String.valueOf(price * 1.20) : String.valueOf(price),
                line.getCustomerApproval() ? "Ja" : "Nej"
            };
            lines[i] = data;
            i += 1;
            orderTotal += line.getPrice();
        }
        
        float yPosition = createTable(contentStream, columnWidths, headers, lines, yStart);
        
        double orderTotalVAT = orderTotal * 1.25;
        double VAT = orderTotal *0.25;
        
        if (IsExpress) {
            orderTotal = orderTotal * 1.20;
            orderTotalVAT = orderTotalVAT * 1.20;
            VAT = VAT * 1.20;
        }        

 
        yPosition = yPosition - 40;
        contentStream.beginText();
        contentStream.setFont(PDType1Font.HELVETICA, 20);
        contentStream.newLineAtOffset(240, yPosition);
        contentStream.showText("Summa exkl moms: " + df.format(orderTotal) + " SEK");
        contentStream.newLineAtOffset(0, -40);
        contentStream.showText("Moms: "); 
        contentStream.newLineAtOffset(190, 0);
        contentStream.showText(df.format(VAT) + " SEK");
        contentStream.newLineAtOffset(-190, -40);
        contentStream.showText("Summa inkl moms: " + df.format(orderTotalVAT) + " SEK");

        if (IsExpress) {
            contentStream.newLineAtOffset(0, -40);
            contentStream.showText("Expressorder +20%: " + df.format(orderTotalVAT * 0.20) + "SEK");
        }
        
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
            //   contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
            } else {
              //  contentStream.setFont(PDType1Font.HELVETICA, 12);
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
    
        public void printCustomerStats() throws PrinterException, IOException {
        PrinterJob printJob = PrinterJob.getPrinterJob();
        createCustomerStatsList(data, customerId);
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
    
    private void createCustomerHeader(PDPageContentStream contentStream, Customer customer) throws IOException {
        contentStream.beginText();
        contentStream.setFont(PDType1Font.HELVETICA, 14);
        contentStream.newLineAtOffset(50, 695);
        contentStream.showText("Kundnummer: " + Integer.toString(customer.getId()));
        contentStream.newLineAtOffset(0, -16);
        contentStream.showText("Namn: " + customer.getFirstName()+" "+customer.getLastName());
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
    
    private PDDocument createCustomerStatsList (String[][] data, int productId) throws IOException {
        String[] headers = {"Datum", "Antal sålda", "Summa"};
        
        float[] columnWidths = {150, 150, 150};
        
        PDPage page = new PDPage();
        document.addPage(page);
        PDPageContentStream contentStream = new PDPageContentStream(document, page);
        contentStream.beginText();
        contentStream.setFont(PDType1Font.HELVETICA, 24);
        contentStream.newLineAtOffset(50, 740);
        contentStream.showText("Antal sålda hattar till kund: " + startDate + " - " + stopDate);
        contentStream.endText();

        contentStream.moveTo(40, 715);
        contentStream.lineTo(580, 715);
        contentStream.stroke();
        
        Customer customer = dbm.getCustomer(customerId);
        
        createCustomerHeader(contentStream, customer);
        
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

