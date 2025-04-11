
package models;

/**
 *
 * @author Petra Schröder
 */
public class Image {
    
    private String base64; 
    private int image_id; 
    private int product_id; 
    
    public Image (String base64, int image_id, int producr_id){
        this.base64 = base64; 
        this.image_id = image_id;
        this.product_id= product_id; 
    }
    
    public void setName(String name) {
       this.base64 = name; 
    }
     public String getName() {
         return base64; 
     }
     
     public int getImageId() {
         return image_id; 
     }
     
     public int getProductId() {
         return product_id; 
     }
}
