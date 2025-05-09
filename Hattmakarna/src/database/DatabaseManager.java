package database;

import java.security.MessageDigest;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
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

    public User login(String inputUsername, String inputPassword) {
        try {
            String query = "SELECT * FROM users WHERE username = '" + inputUsername + "' AND password = '" + md5(inputPassword) + "' AND active=1";
            HashMap<String, String> row = db.fetchRow(query);

            if (row.isEmpty()) {
                return null;
            }
            User user = new User(
                    row.get("user_id") == null ? 0 : Integer.parseInt(row.get("user_id")),
                    row.get("username"),
                    row.get("password"),
                    ParseBoolean(row.get("active"))
            );
            return user;

        } catch (InfException e) {
            throw new RuntimeException("Fel vid inloggning: " + e.getMessage());
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

            db.insert("INSERT INTO orders (order_id,customer_id,order_date,order_status, express, shipping_cost) values (" + newId + ",1,NOW(),'Open', 0, 39)");

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
                    + "shipping_cost = '" + order.getShippingCost() + "', "
                    + "express = " + order.isExpress() + " "
                    + "WHERE order_id = " + order.getId();
            System.out.println(query);
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

    public ArrayList<Order> getOrdersBetweenDates(String startDate, String stopDate) {
        try {
            ArrayList<Order> orders = new ArrayList<>();
            String query = "SELECT * FROM orders WHERE order_date BETWEEN '" + startDate + "' and '" + stopDate + "'";
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
                        row.get("product_id") == null ? 0 : Integer.parseInt(row.get("product_id")),
                        "1111-11-11",
                        row.get("hat_status")
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
            String query = "SELECT orderlines.orderline_id, order_id, customer_approval, description, price, product_id, delivery_date, hat_status FROM orderlines LEFT OUTER JOIN hatmaker ON orderlines.orderline_id = hatmaker.orderline_id WHERE order_id = " + order_id;
            ArrayList<HashMap<String, String>> results = db.fetchRows(query);
            System.out.println("These are the results" + results);
            if (results != null) {
                for (HashMap<String, String> row : results) {
                    orderlines.add(new OrderLine(
                            Integer.parseInt(row.get("orderline_id")),
                            order_id,
                            ParseBoolean(row.get("customer_approval")),
                            row.get("description"),
                            Integer.parseInt(row.get("price")),
                            Integer.parseInt(row.get("product_id")),
                            row.get("delivery_date"),
                            row.get("hat_status")
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
            String query = "SELECT * FROM orderlines JOIN hatmaker ON orderlines.orderline_id = hatmaker.orderline_id";
            ArrayList<HashMap<String, String>> results = db.fetchRows(query);
            if (results != null) {
                for (HashMap<String, String> row : results) {
                    orderlines.add(new OrderLine(
                            Integer.parseInt(row.get("orderline_id")),
                            Integer.parseInt(row.get("order_id")),
                            ParseBoolean(row.get("customer_approval")),
                            row.get("description"),
                            Integer.parseInt(row.get("price")),
                            Integer.parseInt(row.get("product_id")),
                            row.get("delivery_date"),
                            row.get("hat_status")
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
            String query = "SELECT * FROM orderlines JOIN hatmaker ON orderlines.orderline_id = hatmaker.orderline_id WHERE hatmaker = " + user_id;
            ArrayList<HashMap<String, String>> results = db.fetchRows(query);
            if (results != null) {
                for (HashMap<String, String> row : results) {
                    orderlines.add(new OrderLine(
                            Integer.parseInt(row.get("orderline_id")),
                            Integer.parseInt(row.get("order_id")),
                            ParseBoolean(row.get("customer_approval")),
                            row.get("description"),
                            Integer.parseInt(row.get("price")),
                            Integer.parseInt(row.get("product_id")),
                            row.get("delivery_date"),
                            row.get("hat_status")
                    ));
                }
            }
            return orderlines;
        } catch (InfException e) {
            throw new RuntimeException("Fel vid hämtning av beställningar: " + e.getMessage());
        }

    }
    
    public String getHatmakerOrderLineDeliveryDate(int id) {
        System.out.println("GET HatmakerOrderline " + id);
        try {
            String sqlQ = "SELECT delivery_date FROM hatmaker WHERE orderline_id = " + id;
            String delivery_date = db.fetchSingle(sqlQ);
            return delivery_date;
        } catch (InfException e) {
            System.err.println("Det gick inte att hämta beställningsrad för hatmaker: " + e.getMessage());
            return null;
        }
    }
    
    public boolean createHatmakerOrderlines(OrderLine orderline, User user, String delivery_date) {
        try {
            int orderline_id = orderline.getOrderLineId();
            int user_id = user.getUserId();
            //String date = delivery_date.toString();
            //System.out.println("This is the date from db manager " + date);
            System.out.println("This is the delivery date from dbmanager " + delivery_date);
            String insert_sql = "INSERT INTO hatmaker (orderline_id, hatmaker, delivery_date) VALUES (" + orderline_id + ", " + user_id + ", " + "str_to_date('" + delivery_date + "', '%Y-%m-%d'))";
            db.insert(insert_sql);
            return true;
        } catch (InfException e) {
            throw new RuntimeException("Det gick inte att lägga till orderraden till hatmaker: " + e.getMessage());
        }
    }
    
    public boolean updateHatmakerOrderLineDeliveryDate(String delivery_date, int orderline_id) {
        try {
            String query = "UPDATE hatmaker SET delivery_date = str_to_date('" + delivery_date + "', '%Y-%m-%d') WHERE orderline_id = " + orderline_id;
            System.out.println(query);
            db.update(query);
            return true;
        } catch (InfException e) {
            System.err.println("Kunde inte uppdatera beställningsrad " + e.getMessage());
            return false;
        }
    }

    public boolean removeHatmakerOrderlines(OrderLine orderline, User user) {
        try {
            int orderline_id = orderline.getOrderLineId();
            int user_id = user.getUserId();
            String delete_sql = "DELETE FROM hatmaker WHERE orderline_id = " + orderline_id + " AND hatmaker = " + user_id;
            db.delete(delete_sql);
            return true;
        } catch (InfException e) {
            throw new RuntimeException("Det gick inte att ta bort orderraden från hatmaker: " + e.getMessage());
        }
    }
    
    // Hämtar en objektlista med alla beställningsrader som inte tillhör en hattmakare
    public ArrayList<OrderLine> getUnassignedOrderlines() {
        try {
            ArrayList<OrderLine> orderlines = new ArrayList<>();
            String query = "SELECT DISTINCT orderlines.* FROM orderlines WHERE orderlines.orderline_id NOT IN (SELECT orderline_id FROM hatmaker)";
            ArrayList<HashMap<String, String>> results = db.fetchRows(query);
            if (results != null) {
                for (HashMap<String, String> row : results) {
                    orderlines.add(new OrderLine(
                            Integer.parseInt(row.get("orderline_id")),
                            Integer.parseInt(row.get("order_id")),
                            ParseBoolean(row.get("customer_approval")),
                            row.get("description"),
                            Integer.parseInt(row.get("price")),
                            Integer.parseInt(row.get("product_id")),
                            "null",
                            row.get("hat_status")
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
                    + "description = '" + orderLine.getDescription() + "', "
                    + "hat_status = '" + orderLine.getHatStatus() + "' "
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
            db.delete("DELETE FROM hatmaker WHERE orderline_id = " + id);
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
                // Hämta e-postadresser
                String emailQuery = "SELECT email_address FROM email_addresses WHERE customer_id = " + customer_id;
                ArrayList<HashMap<String, String>> emailRows = db.fetchRows(emailQuery);
                ArrayList<String> emails = new ArrayList<>();
                for (HashMap<String, String> emailRow : emailRows) {
                    emails.add(emailRow.get("email_address"));
                }

                // Hämta telefonnummer
                String phoneQuery = "SELECT phone_number FROM phone_numbers WHERE customer_id = " + customer_id;
                ArrayList<HashMap<String, String>> phoneRows = db.fetchRows(phoneQuery);
                ArrayList<String> phones = new ArrayList<>();
                for (HashMap<String, String> phoneRow : phoneRows) {
                    phones.add(phoneRow.get("phone_number"));
                }

                // Skapa Customer-objektet med alla uppgifter
                Customer customer = new Customer(
                        customer_id,
                        row.get("firstname"),
                        row.get("lastname"),
                        row.get("streetname"),
                        row.get("postal_code"),
                        row.get("postal_city"),
                        row.get("state"),
                        row.get("country"),
                        phones,
                        emails
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

    // Skapar en kund
    public Customer createCustomer(String firstName, String lastName, String streetName, String postal_code, String postal_city, String state, String country, ArrayList<String> phoneNumbers, ArrayList<String> mails) {
        try {
            String maxIdStr = db.fetchColumn("SELECT MAX(customer_id) FROM customers").getFirst();
            int newId = (maxIdStr == null || maxIdStr.isEmpty()) ? 1 : Integer.parseInt(maxIdStr) + 1;

            String insert = "INSERT INTO customers (customer_id, firstname, lastname, streetname, postal_code, postal_city, state, country) "
                    + "VALUES (" + newId + ", '" + firstName + "', '" + lastName + "','" + streetName + "','" + postal_code + "', '" + postal_city + "', '" + state + "','" + country + "')";
            db.insert(insert);

            Customer customer = getCustomer(newId);

            customer.setPhoneNumbers(phoneNumbers);
            customer.setEmail(mails);

            updateCustomer(customer);

            return customer;
        } catch (InfException e) {
            System.err.println("Kunde inte skapa ny kund: " + e.getMessage());
            return null;
        }

    }
// Uppdaterar en kund

    public boolean updateCustomer(Customer customer) {
        try {
            // 1. Uppdatera kundens grundinformation
            String updateQuery = "UPDATE customers SET "
                    + "firstname = '" + customer.getFirstName() + "', "
                    + "lastname = '" + customer.getLastName() + "', "
                    + "streetname = '" + customer.getStreetName() + "', "
                    + "postal_code = '" + customer.getPostalCode() + "', "
                    + "postal_city = '" + customer.getPostalCity() + "', "
                    + "state = '" + customer.getState() + "', "
                    + "country = '" + customer.getCountry() + "' "
                    + "WHERE customer_id = " + customer.getId();
            db.update(updateQuery);

            int customerId = customer.getId();

            System.out.println("Tar bort emails");
            // 2. Rensa tidigare e-postadresser
            String deleteEmails = "DELETE FROM email_addresses WHERE customer_id = " + customerId;

            db.delete(deleteEmails);
            System.out.println("Uppdaterar emails");
            // 3. Lägg till nya e-postadresser
            for (String email : customer.getEmails()) {
                String insertEmail = "INSERT INTO email_addresses (customer_id, email_address) VALUES ("
                        + customerId + ", '" + email + "')";
                db.insert(insertEmail);
            }
            System.out.println("Tar bort telefonnummer");
            // 4. Rensa tidigare telefonnummer
            String deletePhones = "DELETE FROM phone_numbers WHERE customer_id = " + customerId;
            db.delete(deletePhones);

            System.out.println("Uppdaterar telefonnummer");
            // 5. Lägg till nya telefonnummer
            for (String phone : customer.getPhoneNumbers()) {
                String insertPhone = "INSERT INTO phone_numbers (customer_id, phone_number) VALUES ("
                        + customerId + ", '" + phone + "')";
                db.insert(insertPhone);
            }

            return true;
        } catch (InfException e) {
            System.err.println("Kunde inte uppdatera kund: " + e.getMessage());
            return false;
        }
    }

// Hämtar en objektlista med alla kunder
    public ArrayList<Customer> getCustomers() {
        ArrayList<String> phones = new ArrayList<>();
        ArrayList<String> emails = new ArrayList<>();
        try {
            ArrayList<Customer> customers = new ArrayList<>();
            String query = "SELECT * FROM customers";
            ArrayList<HashMap<String, String>> results = db.fetchRows(query);
            if (results != null) {
                for (HashMap<String, String> row : results) {
                    customers.add(new Customer(
                            row.get("customer_id") == null ? 0 : Integer.parseInt(row.get("customer_id")),
                            row.get("firstname"),
                            row.get("lastname"),
                            row.get("streetname"),
                            row.get("postal_code"),
                            row.get("postal_city"),
                            row.get("state"),
                            row.get("country"),
                            phones,
                            emails
                    ));
                }
            }
            return customers;
        } catch (InfException e) {
            throw new RuntimeException("Fel vid hämtning av kunder  " + e.getMessage());
        }

    }

    //Returnera material som inte beställts mellan två datum
    public ArrayList<ComponentModel> getComponentsBetweenDates(String StartDate, String StopDate) {
        System.out.println("GET materials between " + StartDate + " and " + StopDate);
        ArrayList<ComponentModel> componentList = new ArrayList<>();
        try {
            ArrayList<HashMap<String, String>> materials = db.fetchRows("SELECT c.component_id, c.component_name,c.description, c.color, SUM(pc.amount) AS total_amount_needed, c.unit FROM orders o JOIN orderlines ol ON o.order_id = ol.order_id JOIN products p ON ol.product_id = p.product_id JOIN product_components pc ON p.product_id = pc.product_id JOIN components c ON pc.component_id = c.component_id WHERE o.order_status = 'Confirmed' AND o.order_date BETWEEN '" + StartDate + "' AND '" + StopDate + "' AND p.stock_item = 0 GROUP BY c.component_id, c.component_name, c.unit, c.color ORDER BY c.component_name;");
            if (!materials.isEmpty()) {
                for (HashMap<String, String> material : materials) {
                    componentList.add(new ComponentModel(
                            Double.parseDouble(material.get("total_amount_needed")),
                            Integer.parseInt(material.get("component_id")),
                            material.get("component_name"),
                            material.get("color"),
                            material.get("unit"),
                            material.get("type"),
                            material.get("description")
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
                        Double.parseDouble(row.get("weight")),
                        row.get("description")
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

    // Hämtar en objektlista med alla produkter
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
                            row.get("price") == null ? 0 : Integer.parseInt(row.get("price")),
                            ParseBoolean(row.get("copyright_approved")),
                            ParseBoolean(row.get("discontinued")),
                            ParseBoolean(row.get("stock_item")),
                            Double.parseDouble(row.get("weight")),
                            row.get("description")
                    ));
                }
            }
            return products;
        } catch (InfException e) {
            throw new RuntimeException("Fel vid hämtning av produkter: " + e.getMessage());
        }

    }

    // Skapar en pridukt
    public Product createProduct() {
        try {
            String maxIdStr = db.fetchColumn("SELECT MAX(product_id) FROM products").getFirst();
            int newId = (maxIdStr == null || maxIdStr.isEmpty()) ? 1 : Integer.parseInt(maxIdStr) + 1;

            String insert = "INSERT INTO products (product_id, product_name, copyright_approved, "
                    + "stock_item, discontinued, base_product_id, weight, price) VALUES ("
                    + newId + ", 'Ny Produkt', 0, 1, 0, NULL, 0.0, 0)";
            db.insert(insert);

            return getProduct(newId);
        } catch (InfException e) {
            System.err.println("Kunde inte skapa ny produkt: " + e.getMessage());
            return null;
        }
    }
    // Uppdaterar en produkt

    public boolean updateProduct(Product product) {
        try {
            String query = "UPDATE products SET "
                    + "product_name = '" + product.getProductName() + "', "
                    + "copyright_approved = " + (product.getcopyRightApproved() ? 1 : 0) + ", "
                    + "stock_item = " + (product.getStockItem() ? 1 : 0) + ", "
                    + "discontinued = " + (product.getDiscontinued() ? 1 : 0) + ", "
                    + "base_product_id = " + (product.getBaseProductId() == 0 ? "NULL" : product.getBaseProductId()) + ", "
                    + "weight = " + product.getWeight() + ", "
                    + "price = " + product.getPrice() + ", "
                    + "description = '" + product.getDescription() + "'"
                    + "WHERE product_id = " + product.getProductId();

            db.update(query);
            return true;
        } catch (InfException e) {
            System.err.println("Kunde inte uppdatera produkt: " + e.getMessage());
            return false;
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
    
public ArrayList<HashMap<String, String>> customerSalesBetweenDates(int customerId, String startDate, String stopDate) {
    ArrayList<HashMap<String, String>> result = null;

    try {
        String query = "SELECT o.order_date, COUNT(*) AS quantity_sold, SUM(ol.price) AS total_sale "
                + "FROM orders o "
                + "JOIN orderlines ol ON o.order_id = ol.order_id "
                + "WHERE o.order_status = 'COMPLETED' "
                + "AND o.customer_id = " + customerId + " "
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

    public User getUser(int user_id) {

        try {
            String query = "SELECT * FROM users WHERE user_id = " + user_id;
            HashMap<String, String> row = db.fetchRow(query);
            if (row != null) {
                User user = new User(
                        Integer.parseInt(row.get("user_id")),
                        row.get("username"),
                        row.get("password"),
                        ParseBoolean(row.get("active"))
                );
                return user;
            } else {
                return null;
            }
        } catch (InfException e) {
            System.err.println("Det gick inte att hämta användaren: " + e.getMessage());
            return null;
        }
    }
    
    public User getUserFromUsername (String username) {
        System.out.println("GET user " + username);
        try {
            String query = "SELECT * FROM users WHERE username = '" + username + "'";
            HashMap<String, String> row = db.fetchRow(query);
            if (row != null) {
                User user = new User(
                        Integer.parseInt(row.get("user_id")),
                        row.get("username"),
                        row.get("password"),
                        ParseBoolean(row.get("active"))
                );
                return user;
            } else {
                return null;
            }
        } catch (InfException e) {
            System.err.println("Det gick inte att hämta användaren: " + e.getMessage());
            return null;
        }
        
    }

    public User createUser(String username, String password, boolean active) {
        try {
            
            String maxIdStr = db.fetchColumn("SELECT MAX(user_id) FROM users").getFirst();
            int newId = (maxIdStr == null || maxIdStr.isEmpty()) ? 1 : Integer.parseInt(maxIdStr) + 1;

            
            String insert = "INSERT INTO users (user_id, username, password, active) VALUES (" +
                newId + ", '" + username + "', '" + md5(password) + "', " + (active ? 1 : 0) + ")";

            db.insert(insert);

            return getUser(newId);
        } catch (InfException e) {
            System.err.println("Kunde inte skapa ny användare: " + e.getMessage());
            return null;
        }
    }

    public boolean updateUser(User user) {
        try {
            String query = "UPDATE users SET "
                    + "username = '" + user.getUserName() + "', "
                    + "password = '" + user.getPassword() + "', "
                    + "active = " + (user.getActive() ? 1 : 0) + " "
                    + "WHERE user_id = " + user.getUserId();

            db.update(query);
            return true;
        } catch (InfException e) {
            System.err.println("Kunde inte uppdatera användare: " + e.getMessage());
            return false;
        }
    }

    private boolean ParseBoolean(String tinyIntString) {
        return "1".equals(tinyIntString);
    }

    public String md5(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] hashBytes = md.digest(input.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : hashBytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (Exception e) {
            throw new RuntimeException("Kunde inte hash:a lösenord", e);
        }
    }

    // Hämtar ett material
    public ComponentModel getComponent(int componentId) {
        try {
            String query = "SELECT * FROM components WHERE component_id = " + componentId;
            HashMap<String, String> row = db.fetchRow(query);

            if (row != null && !row.isEmpty()) {
                return new ComponentModel(
                        0,
                        Integer.parseInt(row.get("component_id")),
                        row.get("component_name"),
                        row.get("color"),
                        row.get("unit"),
                        row.get("type"),
                        row.get("description")
                );
            } else {
                return null;
            }
        } catch (InfException e) {
            System.err.println("Kunde inte hämta komponent: " + e.getMessage());
            return null;
        }
    }

    // Skapar ett material
    public ComponentModel createComponent() {
        try {
            String maxIdStr = db.fetchColumn("SELECT MAX(component_id) FROM components").getFirst();
            int newId = (maxIdStr == null || maxIdStr.isEmpty()) ? 1 : Integer.parseInt(maxIdStr) + 1;

            String insert = "INSERT INTO components (component_id, component_name, type, unit, color, description) "
                    + "VALUES (" + newId + ", 'Ny komponent', '', '', '', '')";
            db.insert(insert);

            return getComponent(newId);
        } catch (InfException e) {
            System.err.println("Kunde inte skapa komponent: " + e.getMessage());
            return null;
        }
    }
// Uppdaterar ett material

    public boolean updateComponent(ComponentModel component) {
        try {
            String query = "UPDATE components SET "
                    + "component_name = '" + component.getComponentName() + "', "
                    + "type = '" + component.getType() + "', "
                    + "unit = '" + component.getUnit() + "', "
                    + "color = '" + component.getColor() + "', "
                    + "description = '" + component.getDescription() + "' "
                    + "WHERE component_id = " + component.getComponentId();

            db.update(query);
            return true;
        } catch (InfException e) {
            System.err.println("Kunde inte uppdatera komponent: " + e.getMessage());
            return false;
        }
    }

    //Returnera allt material
    public ArrayList<ComponentModel> getComponents() {
        System.out.println("GET materials");
        ArrayList<ComponentModel> componentList = new ArrayList<>();
        try {
            ArrayList<HashMap<String, String>> materials = db.fetchRows("SELECT * FROM components");
            if (materials != null) {
                for (HashMap<String, String> material : materials) {
                    componentList.add(new ComponentModel(
                            0,
                            Integer.parseInt(material.get("component_id")),
                            material.get("component_name"),
                            material.get("color"),
                            material.get("unit"),
                            material.get("type"),
                            material.get("description")
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
    //Returnera allt material för en produkt

    public ArrayList<ComponentModel> getComponentsForProduct(int productId) {

        ArrayList<ComponentModel> componentList = new ArrayList<>();
        try {
            String query = "SELECT c.component_id, c.component_name, c.color, c.unit, c.type, pc.amount "
                    + "FROM components c "
                    + "JOIN product_components pc ON c.component_id = pc.component_id "
                    + "WHERE pc.product_id = " + productId;
            ArrayList<HashMap<String, String>> materials = db.fetchRows(query);
            if (materials != null) {
                for (HashMap<String, String> material : materials) {
                    componentList.add(new ComponentModel(
                            Double.parseDouble(material.get("amount")),
                            Integer.parseInt(material.get("component_id")),
                            material.get("component_name"),
                            material.get("color"),
                            material.get("unit"),
                            material.get("type"),
                            material.get("description")
                    ));
                }
                return componentList;
            } else {
                return null;
            }
        } catch (InfException e) {
            System.err.println("Det gick inte att hämta material för produkt: " + e.getMessage());
            return null;
        }
    }

    public void setComponentForProduct(int productId, int componentId, double amount) {
        try {
            // 1. Kontrollera om rad redan finns
            String checkQuery = "SELECT * FROM product_components WHERE product_id = " + productId + " AND component_id = " + componentId;
            HashMap<String, String> existingRow = db.fetchRow(checkQuery);

            if (existingRow != null && !existingRow.isEmpty()) {
                // 2. Uppdatera amount om den finns
                String updateQuery = "UPDATE product_components SET amount = " + amount
                        + " WHERE product_id = " + productId + " AND component_id = " + componentId;
                db.update(updateQuery);
            } else {
                // 3. Finns ej, lägg till
                String insertQuery = "INSERT INTO product_components (product_id, component_id, amount) VALUES ("
                        + productId + ", " + componentId + ", " + amount + ")";
                db.insert(insertQuery);
            }

        } catch (InfException e) {
            System.err.println("Kunde inte sätta komponent för produkt: " + e.getMessage());
        }
    }

    public ProductImage createImage() {
        try {
            String maxIdStr = db.fetchColumn("SELECT MAX(image_id) FROM images").getFirst();
            int newId = (maxIdStr == null || maxIdStr.isEmpty()) ? 1 : Integer.parseInt(maxIdStr) + 1;

            String insert = "INSERT INTO images (image_id, product_id, base64, type, description) "
                    + "VALUES (" + newId + ", '1', '', '', '')";
            db.insert(insert);

            return getImage(newId);
        } catch (InfException e) {
            System.err.println("Kunde inte skapa komponent: " + e.getMessage());
            return null;
        }
    }
    
    public ArrayList<HashMap<String, String>> getOrdersByDeliveryDate(LocalDate deliveryDate) {
        try {
            String sql = 
            "SELECT DISTINCT orders.* " +
            "FROM orders, orderlines, hatmaker " +
            "WHERE orders.order_id = orderlines.order_id " +
            "AND orderlines.orderline_id = hatmaker.orderline_id " +
            "AND hatmaker.delivery_date = '" + deliveryDate.toString() + "'";
        return db.fetchRows(sql);
    } catch (InfException e) {
        System.err.println("Fel vid hämtning av ordrar: " + e.getMessage());
        return new ArrayList<>();
   }
}

    public boolean updateImage(ProductImage image) {
        try {
            String query = "UPDATE images SET "
                    + "product_id = '" + image.getProductId() + "', "
                    + "base64 = '" + image.getBase64() + "', "
                    + "type = '" + image.getType() + "', "
                    + "description = '" + image.getDescription() + "' "
                    + "WHERE image_id = " + image.getImageId();
            db.update(query);
            return true;
        } catch (InfException e) {
            System.err.println("Kunde inte uppdatera bilden: " + e.getMessage());
            return false;
        }
    }

    public ProductImage getImage(int imageId) {
        try {
            String query = "SELECT * FROM images WHERE image_id = " + imageId;
            HashMap<String, String> row = db.fetchRow(query);

            if (row != null && !row.isEmpty()) {
                return new ProductImage(
                        row.get("base64"),
                        Integer.parseInt(row.get("image_id")),
                        Integer.parseInt(row.get("product_id")),
                        row.get("type"),
                        row.get("description")
                );
            } else {
                return null;
            }
        } catch (InfException e) {
            System.err.println("Kunde inte hämta bilden: " + e.getMessage());
            return null;
        }
    }

    public ArrayList<ProductImage> getImages() {
        System.out.println("GET images");
        ArrayList<ProductImage> imageList = new ArrayList<>();
        try {
            ArrayList<HashMap<String, String>> images = db.fetchRows("SELECT * FROM images");
            if (images != null) {
                for (HashMap<String, String> image : images) {
                    imageList.add(new ProductImage(
                            image.get("base64"),
                            Integer.parseInt(image.get("image_id")),
                            Integer.parseInt(image.get("product_id")),
                            image.get("type"),
                            image.get("description")
                    ));
                }
                return imageList;
            } else {
                return null;
            }
        } catch (InfException e) {
            System.err.println("Det gick inte att hämta bilder: " + e.getMessage());
            return null;
        }
    }

    public boolean deleteImage(int image_id) {
        try {
            db.delete("DELETE FROM images where image_id = " + image_id);
            return true;
        } catch (InfException e) {
            System.err.println("Det gick inte att ta bort bilden: " + e.getMessage());
            return false;
        }
    }
    
    
    public Map<String, String> getDeliveryNoteLanguage(String country) {
        try {
            // Först: försök hämta översättningar för angivet land
            String query = "SELECT label_key, label_value FROM deliverynote_translation_entries WHERE country = '" + country + "'";
            ArrayList<HashMap<String, String>> rows = db.fetchRows(query);

            // Om inget resultat hittades, försök med fallback till engelska
            if (rows == null || rows.isEmpty()) {
                System.out.println("Inga översättningar hittades för '" + country + "'. Försöker med engelska (fallback).");
                query = "SELECT label_key, label_value FROM deliverynote_translation_entries WHERE code = 'EN'";
                rows = db.fetchRows(query);
            }

            if (rows != null && !rows.isEmpty()) {
                Map<String, String> translations = new HashMap<>();
                for (HashMap<String, String> row : rows) {
                    translations.put(row.get("label_key"), row.get("label_value"));
                }
                return translations;
            } else {
                System.err.println("Varken landsspecifika eller engelska översättningar hittades.");
                return null;
            }

        } catch (InfException e) {
            System.err.println("Fel vid hämtning av översättningar: " + e.getMessage());
            return null;
        }
    }
    
    public boolean deleteComponentForProduct(int productId, int componentId) {
        try {
            System.out.println("Radera relationen mellan komponenten " + componentId + " och produkten " + productId);
            String query = "DELETE FROM product_components WHERE product_id = " + productId + " AND component_id = " + componentId;
            db.delete(query);
            return true;
        } catch (InfException e) {
            System.err.println("Kunde inte radera komponenten för produkt: " + e.getMessage());
            return false;
        }
    }
    
    public Map<String, Set<String>> getComponentAttributesFromName (String componentName) {
        System.out.println("GET Component attributes for " + componentName);
        Map<String, Set<String>> componentAttributes = new HashMap<>();
        try {
            String typeQuery = "SELECT type FROM components WHERE component_name = '" + componentName + "'";
            ArrayList<String> typesArray = db.fetchColumn(typeQuery);
            Set<String> types = new HashSet<>(typesArray);
            componentAttributes.put("types", types);
            System.out.println("Successfully added types");
        } catch (InfException e) {
            System.err.println(e.getMessage());
            return null;
        }

          //Colors är en fix lista som finns i klassen.
//        try {
//            String colorQuery = "SELECT color from components WHERE component_name = " + componentName;
//            ArrayList<String> colorArray = db.fetchColumn(colorQuery);
//            Set<String> colors = new HashSet<>(colorArray);
//            componentAttributes.put("colors", colors);
//        } catch (InfException e) {
//            System.err.println(e.getMessage());
//            return null;
//        }
        
        try {
            String unitQuery = "SELECT unit FROM components WHERE component_name = '" + componentName + "'";
            ArrayList<String> unitArray = db.fetchColumn(unitQuery);
            Set<String> units = new HashSet<>(unitArray);
            componentAttributes.put("units", units);
            System.out.println("Successfully added units");
        } catch (InfException e) {
            System.err.println(e.getMessage());
            return null;
        }
       
        return componentAttributes;
    }
    
    public ArrayList<ProductImage> get3DImagesForProduct(int productId) {
        System.out.println("GET 3D images for product " + productId);
        ArrayList<ProductImage> images = new ArrayList<>();
        try {
            String query = "SELECT * FROM images WHERE product_id = " + productId + " AND type = '3dvy'";
            ArrayList<HashMap<String, String>> result = db.fetchRows(query);
            if (result != null) {
                for (HashMap<String, String> row : result) {
                    images.add(new ProductImage (
                            row.get("base64"),
                            Integer.parseInt(row.get("image_id")),
                            Integer.parseInt(row.get("product_id")),
                            row.get("type"),
                            row.get("description")
                    ));
                }
                return images;
            } else {
                return null;
            }
        } catch (InfException e) {
            System.err.println(e.getMessage());
            return null;
        }
    }
    public ProductImage getProductImageForProduct(int productId) {
        System.out.println("GET product image for product " + productId);
        ArrayList<ProductImage> images = new ArrayList<>();
        try {
            String query = "SELECT * FROM images WHERE product_id = " + productId + " AND type = 'Produktbild'";
            HashMap<String, String> result = db.fetchRow(query);
            if (result.size() > 0) {
                ProductImage image = new ProductImage (
                    result.get("base64"),
                    Integer.parseInt(result.get("image_id")),
                    Integer.parseInt(result.get("product_id")),
                    result.get("type"),
                    result.get("description")
                );
            return image;
            } else {
                return null;
            }
        } catch (InfException e) {
            System.err.println(e.getMessage());
            return null;
        }
    }
}
