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

    public OrderLine( int orderLine_id,int order_id,boolean customer_approval, String description,  int price, int product_id) {
        this.customer_approval = customer_approval;
        this.description = description;
        this.order_id = order_id;
        this.orderLine_id = orderLine_id;
        this.price = price;
        this.product_id = product_id;
    }

    public void setCustomerApproval(boolean customerApproval) {
        this.customer_approval = customerApproval;
    }

    public boolean getCustomerApproval() {
        return customer_approval;
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

}
