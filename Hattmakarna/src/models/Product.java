
package models;

public class Product {
    private int base_product_id;
    private int product_id;
    private String product_name;
    private int price;
    private boolean copyRight_approved;
    private boolean discontinued; 
    private boolean stock_item;
    private double weight; 
    private String description;
    
    public Product (int base_product_id, int product_id, String product_name, int price, boolean copyRight_approved, boolean discontinued, boolean stock_item,double weight, String description ){
        this.base_product_id= base_product_id;
        this.product_id = product_id;
        this.product_name = product_name;
        this.price = price;
        this.copyRight_approved= copyRight_approved;
        this.discontinued=  discontinued; 
        this.stock_item= stock_item;
        this.weight = weight; 
        this.description = description;
    }
    
    public int getBaseProductId() {
        return base_product_id; 
    }
    
    public int getProductId() {
        return product_id; 
    }

    public String getProductName() {
        return product_name; 
    }

    public double getWeight() {
        return weight; 
    }

    public int getPrice() {
        return price; 
    }
    
    public String getDescription() {
        return description;
    } 
    
    public boolean getcopyRightApproved() {
        return copyRight_approved; 
    }

    public boolean getDiscontinued() {
        return discontinued; 
    }

    public boolean getStockItem() {
        return stock_item; 
    }

    public void setCopyRightApproved(boolean copyRight) {
        this.copyRight_approved = copyRight; 
    }

    public void setProductName(String productname) {
        this.product_name = productname; 
    }
    public void setDiscontinued(boolean discontinued) {
        this.discontinued = discontinued; 
    }

    public void setStockItem(boolean stockItem) {
        this.stock_item = stockItem; 
    }

    public void setWeight(double weight) {
        this.weight = weight;  
    }

    public void setDescription(String newDescription) {
        this.description = newDescription;
    }
       
    public void setPrice(int newPrice) {
        this.price=newPrice; 
    }

}
