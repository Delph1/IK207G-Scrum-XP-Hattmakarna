
package utils;

import static hattmakarna.Hattmakarna.dbm;
import hattmakarna.MainWindow;
import hattmakarna.ModularWindow;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Base64;
import javax.swing.JFileChooser;
import models.ProductImage;

public class ImageManager {
    
    private MainWindow window;
    private ModularWindow mWindow;
    private boolean modular;
    private byte[] imageBytes;
    
    public ImageManager(MainWindow window) {
        this.window = window;
    }

    public ImageManager(ModularWindow window, boolean modular) {
        this.mWindow = window;
        this.modular = modular;
    }
    public byte[] uploadImage() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Välj en bild");
        fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Bildfiler", "jpg", "jpeg", "png", "gif"));
        int result = 0;
        if (window == null) {
            result = fileChooser.showOpenDialog(mWindow);
        } else { 
            result = fileChooser.showOpenDialog(window);
        }
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();

            try {
                // Läs filen som byte-array och konvertera till Base64
                imageBytes = Files.readAllBytes(selectedFile.toPath());

            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return imageBytes;
    }
    
    public void saveNewImage(String base64, int product_id) {
        ProductImage image = dbm.createImage();
        image.setBase64(base64);
        image.setProductId(product_id);
        dbm.updateImage(image);
    }
}
