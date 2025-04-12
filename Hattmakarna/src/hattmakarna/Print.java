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
private Order order;
PDDocument document = new PDDocument();

    public Print (Order order) throws IOException{
 
    int orderId = order.getId();
    
    PDPage newPage = new PDPage();
    
    document.addPage(newPage);
    
    PDPageContentStream contentStream = new PDPageContentStream(document, newPage);
    
    contentStream.beginText();
    
    contentStream.setFont(PDType1Font.HELVETICA, 14);
    
    contentStream.newLineAtOffset(50, 50);
    
    contentStream.showText(Integer.toString(orderId));
    
    contentStream.endText();
    
    contentStream.close(); 
    
    
    
    
    
    
    
    String path = "C:\\Users\\nolle\\Downloads\\test.pdf";
   
    try {
    document.save(path);     
    }
    
    catch(Exception e) {
    System.out.println("");
    }
    
    try {
    document.close();
    }
    
    catch (Exception e) {
    System.out.println("");
    }
  }

public static void main (String [] args) {
       
}
}


