package hattmakarna;

import database.DatabaseManager;
import javax.swing.JFrame;
import models.User;


public class Hattmakarna {
    public static DatabaseManager dbm = new DatabaseManager();
    public static User currentUser;


    public static void main(String[] args) {
            LoginWindow window = new LoginWindow();
            window.setVisible(true);
         
    }
    
}
