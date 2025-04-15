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
            HashMap<String, String> row = db.fetchRow("SELECT * FROM orders where order_id = " + id);
            if (row != null) {
                Order order = new Order(
                        id,
                        row.get("customer_id") == null ? 0 : Integer.parseInt(row.get("customer_id")),
                        LocalDate.parse(row.get("order_date")),
                        row.get("order_status"),
                        ParseBoolean(row.get("express")),
                        row.get("shipping_cost") == null ? 0 : Integer.parseInt(row.get("shipping_cost"))
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
                            ParseBoolean(row.get("express")),
                            row.get("shipping_cost") == null ? 0 : Integer.parseInt(row.get("shipping_cost"))
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
        System.out.println("GET orderline " + id);
        try {
            HashMap<String, String> row = db.fetchRow("SELECT * FROM orderlines where orderline_id =" + id);
            if (row != null) {
                OrderLine orderLine = new OrderLine(
                        id,
                        row.get("order_id") == null ? 0 : Integer.parseInt(row.get("order_id")),
                        ParseBoolean(row.get("customer_approval")),
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

    // Hämtar en objektlista med alla beställningsrader
    public ArrayList<OrderLine> getOrderlines(int order_id) {
        try {
            ArrayList<OrderLine> orderlines = new ArrayList<>();
            String query = "SELECT * FROM orderlines WHERE order_id = " + order_id;
            ArrayList<HashMap<String, String>> results = db.fetchRows(query);
            if (results != null) {
                for (HashMap<String, String> row : results) {
                    orderlines.add(new OrderLine(
                            Integer.parseInt(row.get("orderline_id")),
                            order_id,
                            ParseBoolean(row.get("customer_approval")),
                            row.get("description"),
                            Integer.parseInt(row.get("price")),
                            Integer.parseInt(row.get("product_id"))
                    ));
                }
            }
            return orderlines;
        } catch (InfException e) {
            throw new RuntimeException("Fel vid hämtning av beställningar: " + e.getMessage());
        }

    }
     // Hämtar en objektlista med alla beställningsrader
    public ArrayList<OrderLine> getOrderlines() {
        try {
            ArrayList<OrderLine> orderlines = new ArrayList<>();
            String query = "SELECT * FROM orderlines";
            ArrayList<HashMap<String, String>> results = db.fetchRows(query);
            if (results != null) {
                for (HashMap<String, String> row : results) {
                    orderlines.add(new OrderLine(
                            Integer.parseInt(row.get("orderline_id")),
                            Integer.parseInt(row.get("order_id")),
                            ParseBoolean(row.get("customer_approval")),
                            row.get("description"),
                            Integer.parseInt(row.get("price")),
                            Integer.parseInt(row.get("product_id"))
                    ));
                }
            }
            return orderlines;
        } catch (InfException e) {
            throw new RuntimeException("Fel vid hämtning av beställningar: " + e.getMessage());
        }

    }
    
        // Hämtar en objektlista med alla beställningsrader för en hattmakare
    public ArrayList<OrderLine> getHatmakerOrderlines(int user_id) {
        try {
            ArrayList<OrderLine> orderlines = new ArrayList<>();
            String query = "SELECT * FROM orderlines, hatmaker WHERE hatmaker.hatmaker = " + user_id;
            ArrayList<HashMap<String, String>> results = db.fetchRows(query);
            if (results != null) {
                for (HashMap<String, String> row : results) {
                    orderlines.add(new OrderLine(
                            Integer.parseInt(row.get("orderline_id")),
                            Integer.parseInt(row.get("order_id")),
                            ParseBoolean(row.get("customer_approval")),
                            row.get("description"),
                            Integer.parseInt(row.get("price")),
                            Integer.parseInt(row.get("product_id"))
                    ));
                }
            }
            return orderlines;
        } catch (InfException e) {
            throw new RuntimeException("Fel vid hämtning av beställningar: " + e.getMessage());
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
                    + "values (" + orderId + "," + newId + ")");

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
                    + "product_id = " + orderLine.getProductId() + ", "
                    + "price = " + orderLine.getPrice() + ", "
                    + "customer_approval = " + orderLine.getCustomerApproval() + ", "
                    + "description = '" + orderLine.getDescription() + "' "
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

    public Customer getCustomer(int customer_id) {
        System.out.println("GET customer " + customer_id);
        try {
            String query = "SELECT * FROM customers WHERE customer_id = " + customer_id;
            HashMap<String, String> row = db.fetchRow(query);
            if (row != null) {
                Customer customer = new Customer(
                        customer_id,
                        row.get("firstname"),
                        row.get("lastname"),
                        row.get("streetname"),
                        row.get("postal_code"),
                        row.get("postal_city"),
                        row.get("state"),
                        row.get("country")
                );
                return customer;
            } else {
                return null;
            }
        } catch (InfException e) {
            System.err.println("Det gick inte att hämta kunden: " + e.getMessage());
            return null;
        }
    }

    //Returnera material som inte beställts mellan två datum
    public ArrayList<Component> getComponentsBetweenDates(String StartDate, String StopDate) {
        System.out.println("GET materials between " + StartDate + " and " + StopDate);
        ArrayList<Component> componentList = new ArrayList<>();
        try {
            ArrayList<HashMap<String, String>> materials = db.fetchRows("SELECT c.component_id, c.component_name, c.color, SUM(pc.amount) AS total_amount_needed, c.unit FROM orders o JOIN orderlines ol ON o.order_id = ol.order_id JOIN products p ON ol.product_id = p.product_id JOIN product_components pc ON p.product_id = pc.product_id JOIN components c ON pc.component_id = c.component_id WHERE o.order_status = 'confirmed' AND o.order_date BETWEEN '" + StartDate + "' AND '" + StopDate + "' AND p.stock_item = 0 GROUP BY c.component_id, c.component_name, c.unit, c.color ORDER BY c.component_name;");
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

    public Product getProduct(int product_id) {
        System.out.println("GET product " + product_id);
        try {
            String query = "SELECT * FROM products WHERE product_id = " + product_id;
            HashMap<String, String> row = db.fetchRow(query);
            if (row != null) {
                Product product = new Product(
                        row.get("base_product_id") == null ? 0 : Integer.parseInt(row.get("base_product_id")),
                        Integer.parseInt(row.get("product_id")),
                        row.get("product_name"),
                        row.get("price") == null ? 0 : Integer.parseInt(row.get("price")),
                        ParseBoolean(row.get("copyright_approved")),
                        ParseBoolean(row.get("discontinued")),
                        ParseBoolean(row.get("stock_item")),
                        Double.parseDouble(row.get("weight"))
                );
                return product;
            } else {
                return null;
            }
        } catch (InfException e) {
            System.err.println("Det gick inte att hämta produkten:" + e.getMessage());
            return null;
        }
    }
    
         // Hämtar en objektlista med alla beställningsrader
    public ArrayList<Product> getProducts() {
        try {
            ArrayList<Product> products = new ArrayList<>();
            String query = "SELECT * FROM products";
            ArrayList<HashMap<String, String>> results = db.fetchRows(query);
            if (results != null) {
                for (HashMap<String, String> row : results) {
                    //Product (double weight )
                    products.add(new Product(
                             row.get("base_product_id") == null ? 0 : Integer.parseInt(row.get("base_product_id")),
                            Integer.parseInt(row.get("product_id")),
                                row.get("product_name"),
                           row.get("price") == null ? 0 :  Integer.parseInt(row.get("price")),
                            ParseBoolean(row.get("copyright_approved")),
                            ParseBoolean(row.get("discountinued")),  
                            ParseBoolean(row.get("stock_item")),  
                             Double.parseDouble(row.get("weight"))
                            
                    ));
                }
            }
            return products;
        } catch (InfException e) {
            throw new RuntimeException("Fel vid hämtning av produkter: " + e.getMessage());
        }

    }

    // Uppdaterar beställningsstatus mellan två datum
    public boolean updateOrderStatusBetweenDates(String StartDate, String StopDate, String status) {
        try {
            String query = "UPDATE orders SET "
                    + "order_status = '" + status + "' "
                    + "WHERE order_date BETWEEN '" + StartDate + "' AND '" + StopDate + "'";

            db.update(query);
            return true;
        } catch (InfException e) {
            System.err.println("Kunde inte uppdatera beställningstatus" + e.getMessage());
            return false;
        }
    }

    public ArrayList<HashMap<String, String>> productSalesBetweenDates(int productID, String startDate, String stopDate) {
        ArrayList<HashMap<String, String>> result = null;

        try {
            String query
                    = "SELECT o.order_date, COUNT(*) AS quantity_sold, SUM(ol.price) AS total_sale "
                    + "FROM orders o "
                    + "JOIN orderlines ol ON o.order_id = ol.order_id "
                    + "JOIN products p ON ol.product_id = p.product_id "
                    + "WHERE o.order_status = 'COMPLETED' "
                    + "AND p.product_id = " + productID + " "
                    + "AND o.order_date BETWEEN '" + startDate + "' AND '" + stopDate + "' "
                    + "GROUP BY o.order_date "
                    + "ORDER BY o.order_date";

            result = db.fetchRows(query);
        } catch (InfException e) {
            System.err.println("Could not fetch sales data: " + e.getMessage());
        }

        return result;
    }

    // Hämtar en objektlista med alla users
    public ArrayList<User> getUsers() {
        try {
            ArrayList<User> users = new ArrayList<>();
            String query = "SELECT * FROM users";
            ArrayList<HashMap<String, String>> results = db.fetchRows(query);
            if (results != null) {
                for (HashMap<String, String> row : results) {
                    users.add(new User(
                            row.get("user_id") == null ? 0 : Integer.parseInt(row.get("user_id")),
                            row.get("username"),
                            row.get("password"),
                            ParseBoolean(row.get("active"))
                    ));

                }
            }
            return users;
        } catch (InfException e) {
            throw new RuntimeException("Fel vid hämtning av användare " + e.getMessage());
        }

    }
    
        private boolean ParseBoolean(String tinyIntString) {
            return "1".equals(tinyIntString);
        }
}
