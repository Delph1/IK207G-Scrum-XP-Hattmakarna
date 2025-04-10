package models;

import java.time.LocalDate;

public class Order {
    private int id;
    private int customer_id;
    private LocalDate order_date;
    private String order_status;
    private boolean express;


    public Order(int id, int customer_id, LocalDate order_date, String order_status, boolean express) {
        this.id = id;
        this.customer_id = customer_id;
        this.order_date = order_date;
        this.order_status = order_status;
        this.express = express;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public LocalDate getOrder_date() {
        return order_date;
    }

    public void setOrder_date(LocalDate order_date) {
        this.order_date = order_date;
    }

    public String getOrder_status() {
        return order_status;
    }

    public void setOrder_status(String order_status) {
        this.order_status = order_status;
    }

    public boolean isExpress() {
        return express;
    }

    public void setExpress(boolean express) {
        this.express = express;
    }
}
