
package models;

public class ProductImage {
    
    private String base64; 
    private int image_id; 
    private int product_id; 
    private String type;
    private String description;
    
    public ProductImage (String base64, int image_id, int producr_id, String type, String description){
        this.base64 = base64; 
        this.image_id = image_id;
        this.product_id= product_id;
        this.type = type;
        this.description = description;
    }
    
    public String getBase64() {
        return base64; 
    }
     
    public int getImageId() {
        return image_id; 
    }
     
    public int getProductId() {
        return product_id;
    }
    
    public String getType() {
        return type;
    }
    
    public String getDescription() {
        return description;
    }

    public void setBase64 (String base64) {
        this.base64 = base64; 
    }
    
    public void setImageId(int imageId) {
        this.image_id = imageId; 
    }

    public void setProductId(int product_id) {
        this.product_id = product_id; 
    }
    
    public void setType(String type) {
        this.type = type;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
}
