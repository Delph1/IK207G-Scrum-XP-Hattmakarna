package hattmakarna;

import database.DatabaseManager;
import javax.swing.JFrame;


public class Hattmakarna {
    public static DatabaseManager dbm = new DatabaseManager();

    public static void main(String[] args) {
            LoginWindow window = new LoginWindow();
            window.setVisible(true);
         
    }
    
}
