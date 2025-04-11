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
                        Integer.parseInt(row.get("order_id")),
                        Integer.parseInt(row.get("customer_id")),
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
            System.err.println("Det gick inte att skapa till en ny beställning : " + e.getMessage());
            return null;
        }
    }
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
            db.insert("INSERT INTO orders (order_id,customer_id,order_date,order_status) values (" + newId + ",1,NOW(),'SKAPAD')");

            // Returnerar den nya beställningen
            return getOrder(newId);
        } catch (InfException e) {
            System.err.println("Det gick inte att skapa till en ny beställning : " + e.getMessage());
            return null;
        }

    }
    // Uppdaterar en order
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
                            Integer.parseInt(row.get("order_id")),
                            Integer.parseInt(row.get("customer_id")),
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
}
