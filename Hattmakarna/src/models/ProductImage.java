
package models;

public class ProductImage {
    
    private String base64; 
    private int image_id; 
    private int product_id; 
    
    public ProductImage (String base64, int image_id, int producr_id){
        this.base64 = base64; 
        this.image_id = image_id;
        this.product_id= product_id; 
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

    public void setBase64 (String base64) {
        this.base64 = base64; 
    }
    
    public void setImageId(int imageId) {
        this.image_id = imageId; 
    }

    public void setProductId(int product_id) {
        this.product_id = product_id; 
    }
}
