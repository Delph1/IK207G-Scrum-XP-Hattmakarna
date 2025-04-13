package database;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import models.*;
import oru.inf.*;

public class DatabaseManager {

    private InfDB db;

    public DatabaseManager() {
        try {
            db = new InfDB("hattmakaren", "3306", "dbHattAdmin", "dbHattAdminPW");
            System.out.println("ANSLUTEN TILL DATABAS");
        } catch (InfException e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }

    }

    public Order getOrder(int id) {
        try {
            HashMap<String, String> row = db.fetchRow("SELECT * FROM orders where order_id =" + id);
            if (row != null) {
                Order order = new Order(
                        id,
                        row.get("customer_id") == null ? 0 : Integer.parseInt(row.get("customer_id")),
                        LocalDate.parse(row.get("order_date")),
                        row.get("order_status"),
                        Boolean.parseBoolean(row.get("express")),
                        Integer.parseInt(row.get("shipping_cost"))
                );
                return order;
            } else {
                return null;
            }
        } catch (InfException e) {
            System.err.println("Det gick inte att hämta beställning : " + e.getMessage());
            return null;
        }
    }
    // Ta bort en beställning
    public boolean deleteOrder(int id) {
        try {
            db.delete("DELETE FROM orders where order_id = " + id);

            return true;
        } catch (InfException e) {
            System.err.println("Det gick inte att ta bort beställningen : " + e.getMessage());
            return false;
        }
    }

    // Skapar en order, returnerar ett beställnings objekt
    public Order createOrder() {
        try {
            // Hämtar ett ID baserat på nuvarande högsta ID +1
            String maxIdStr = db.fetchColumn("select MAX(order_id) from orders").getFirst();
            int newId = (maxIdStr == null || maxIdStr.isEmpty()) ? 1 : Integer.parseInt(maxIdStr) + 1;
            // Lägger in beställning i databasen

            db.insert("INSERT INTO orders (order_id,customer_id,order_date,order_status, express, shipping_cost) values (" + newId + ",1,NOW(),'ÖPPEN', 0, 39)");


            // Returnerar den nya beställningen
            return getOrder(newId);
        } catch (InfException e) {
            System.err.println("Det gick inte att skapa till en ny beställning : " + e.getMessage());
            return null;
        }

    }

    // Uppdaterar en beställning
    public boolean updateOrder(Order order) {
        try {
            String query = "UPDATE orders SET "
                    + "customer_id = '" + order.getCustomer_id() + "', "
                    + "order_date = '" + order.getOrder_date() + "', "
                    + "order_status = '" + order.getOrder_status() + "', "
                    + "express = " + order.isExpress() + " "
                    + "WHERE order_id = " + order.getId();
            db.update(query);
            return true;
        } catch (InfException e) {
            System.err.println("Kunde inte uppdatera beställningen " + e.getMessage());
            return false;
        }
    }

    // Hämtar en objektlista med alla beställningar
    public ArrayList<Order> getOrders() {
        try {
            ArrayList<Order> orders = new ArrayList<>();
            String query = "SELECT * FROM orders";
            ArrayList<HashMap<String, String>> results = db.fetchRows(query);
            if (results != null) {
                for (HashMap<String, String> row : results) {
                    orders.add(new Order(
                            row.get("order_id") == null ? 0 : Integer.parseInt(row.get("order_id")),
                            row.get("customer_id") == null ? 0 : Integer.parseInt(row.get("customer_id")),
                            LocalDate.parse(row.get("order_date")),
                            row.get("order_status"),
                            Boolean.parseBoolean(row.get("express")),
                            Integer.parseInt(row.get("shipping_cost"))
                    ));
                }
            }
            return orders;
        } catch (InfException e) {
            throw new RuntimeException("Fel vid hämtning av beställningar: " + e.getMessage());
        }

    }

    // Hämta en beställningsrad från databas
    public OrderLine getOrderLine(int id) {
        System.out.println("GET orderline "+id);
        try {
            HashMap<String, String> row = db.fetchRow("SELECT * FROM orderlines where orderline_id =" + id);
            if (row != null) {
                OrderLine orderLine = new OrderLine(
                        id,
                        row.get("order_id") == null ? 0 : Integer.parseInt(row.get("order_id")),
                        Boolean.parseBoolean(row.get("customer_approval")),
                        row.get("description"),
                        row.get("price") == null ? 0 : Integer.parseInt(row.get("price")),
                        row.get("product_id") == null ? 0 : Integer.parseInt(row.get("product_id"))
                );
                return orderLine;
            } else {
                return null;
            }
        } catch (InfException e) {
            System.err.println("Det gick inte att hämta beställningsrad: " + e.getMessage());
            return null;
        }
    }

    // Skapar en beställningsrad, returnerar ett beställningsrad objekt
    public OrderLine createOrderLine(int orderId) {
        try {
            // Hämtar ett ID baserat på nuvarande högsta ID +1
            String maxIdStr = db.fetchColumn("select MAX(orderline_id) from orderlines").getFirst();
            int newId = (maxIdStr == null || maxIdStr.isEmpty()) ? 1 : Integer.parseInt(maxIdStr) + 1;
            // Lägger in beställningrad i databasen
            db.insert("INSERT INTO orderlines (order_id,orderline_id) "
                    + "values ("+orderId+"," + newId+")");

            // Returnerar den nya beställningen
            return getOrderLine(newId);
        } catch (InfException e) {
            System.err.println("Det gick inte att skapa till en ny beställningsrad : " + e.getMessage());
            return null;
        }

    }
    
        // Uppdaterar en beställning
    public boolean updateOrderLine(OrderLine orderLine) {
        try {
            String query = "UPDATE orderlines SET "
                    + "product_id = " + orderLine.getProductId()+ ", "
                    + "price = " + orderLine.getPrice()+ ", "
                    + "customer_approval = " + orderLine.getCustomerApproval()+ ", "
                    + "description = '" + orderLine.getDescription()+ "' "
                    + "WHERE orderline_id = " + orderLine.getOrderLineId();
            System.out.println(query);
            db.update(query);
            return true;
        } catch (InfException e) {
            System.err.println("Kunde inte uppdatera beställningsrad " + e.getMessage());
            return false;
        }
    }
    
        // Ta bort en beställningrad
    public boolean deleteOrderLine(int id) {
        try {
            db.delete("DELETE FROM orderlines where orderline_id = " + id);

            return true;
        } catch (InfException e) {
            System.err.println("Det gick inte att ta bort beställningsrad : " + e.getMessage());
            return false;
        }
    }

        //Returnera material som inte beställts mellan två datum
    public ArrayList<Component> getMaterials(String StartDate, String StopDate) {
        System.out.println("GET materials between " + StartDate + " and " + StopDate);
        ArrayList<Component> componentList = new ArrayList<>();
        try {
            ArrayList<HashMap<String, String>> materials = db.fetchRows("SELECT c.component_id, c.component_name, c.color, SUM(pc.amount) AS total_amount_needed, c.unit FROM orders o JOIN orderlines ol ON o.order_id = ol.order_id JOIN products p ON ol.product_id = p.product_id JOIN product_components pc ON p.product_id = pc.product_id JOIN components c ON pc.component_id = c.component_id WHERE o.order_status = 'confirmed' AND o.order_date BETWEEN '" + StartDate + "' AND '" + StopDate + "' GROUP BY c.component_id, c.component_name, c.unit, c.color ORDER BY c.component_name;");
            if (materials != null) {
                for (HashMap<String, String> material : materials) {
                    componentList.add(new Component(
                            Double.parseDouble(material.get("total_amount_needed")),
                            Integer.parseInt(material.get("component_id")),
                            material.get("component_name"),
                            material.get("color"),
                            material.get("unit")
                    ));
                }
                return componentList;
            } else {
                return null;
            }
        } catch (InfException e) {
            System.err.println("Det gick inte att hämta material: " + e.getMessage());
            return null;
        }
    }

}
