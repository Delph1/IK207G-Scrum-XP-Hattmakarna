package hattmakarna;

import database.DatabaseManager;


public class Hattmakarna {
     public static DatabaseManager dbm = new DatabaseManager();

    public static void main(String[] args) {


            new MainWindow().setVisible(true);
    }
    
}
