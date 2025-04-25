package models;

import java.time.LocalDate;

/**
 *
 * @author Petra Schröder
 */
public class OrderLine {

    private boolean customer_approval;
    private String description;
    private int order_id;
    private int orderLine_id;
    private int price;
    private int product_id;
    private String delivery_date;
    private String hat_status;

    public OrderLine(int orderLine_id, int order_id, boolean customer_approval, String description, int price, int product_id, String delivery_date, String hat_status) {
        this.customer_approval = customer_approval;
        this.description = description;
        this.order_id = order_id;
        this.orderLine_id = orderLine_id;
        this.price = price;
        this.product_id = product_id;
        this.delivery_date = delivery_date;
        this.hat_status = hat_status;
    }

    public void setCustomerApproval(boolean customerApproval) {
        this.customer_approval = customerApproval;
    }

    public boolean getCustomerApproval() {
        return customer_approval;
    }

    public void setProductId(int productId) {
        this.product_id = productId;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public int getOrderId() {
        return order_id;
    }

    public int getOrderLineId() {
        return orderLine_id;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getPrice() {
        return price;
    }

    public int getProductId() {
        return product_id;
    }

    @Override
    public String toString() {
        return "Order-id: " + order_id + ",  " + "OrderLine-Id: " + orderLine_id + ",  " + "Beskrivning: " + description;
    }
    
    public String getDeliveryDate() {
        return delivery_date; 
    }
    
    public void setDeliveryDate(String delivery_date) {
        this.delivery_date = delivery_date;
    }
    
    public String getHatStatus() {
        return hat_status;
    }
    
    public void setHatStatus(String hat_status) {
        this.hat_status = hat_status;
    }
}
