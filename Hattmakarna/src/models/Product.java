
package models;

/**
 *
 * @author Petra Schröder
 */
public class Product {
    private int base_product_id;
    private int product_id;
    private String product_name;
    private int price;
    private boolean copyRight_approved;
    private boolean discontinued; 
    private boolean stock_item;
    private double weight; 
    
    public Product (int base_product_id, int product_id, String product_name, int price, boolean copyRight_approved, boolean discontinued, boolean stock_item,double weight ){
    this.base_product_id= base_product_id;
    this.product_id = product_id;
   this.product_name = product_name;
    this.price = price;
    this.copyRight_approved= copyRight_approved;
    this.discontinued=  discontinued; 
    this.stock_item= stock_item;
    this.weight = weight; 
    }
    
    public int getBaseProductId(){
        return base_product_id; 
    }
    
    public int getProductId()
    {
        return product_id; 
    }
    public void setProductName(String productname) {
        this.product_name = productname; 
    }
    public String getProductName() {
        return product_name; 
    }
        public void setPrice(int newPrice) {
        this.price=newPrice; 
    }
    public int getPrice() {
        return price; 
    }
    
    public void setProductBaseId(int productId) {
        this.base_product_id = productId;
    }
    
    public void setCopyRightApproved(boolean copyRight) {
        this.copyRight_approved = copyRight; 
    }
    public boolean getcopyRightApproved() {
        return copyRight_approved; 
    }
       public void setDiscontinued(boolean discontinued) {
        this.discontinued = discontinued; 
    }
    public boolean getDiscontinued() {
        return discontinued; 
    }
       public void setStockItem(boolean stockItem) {
        this.stock_item = stockItem; 
    }
    public boolean getStockItem() {
        return stock_item; 
    }
       public void setWeight(double weight) {
        this.weight = weight;  
    }
    public double getWeight() {
        return weight; 
    }
}
