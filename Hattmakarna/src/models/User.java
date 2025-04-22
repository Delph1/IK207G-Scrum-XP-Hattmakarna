package models;

/**
 *
 * @author Petra Schröder
 */
public class User {

    private int user_id;
    private String password;
    private String userName;
    private boolean active;


    public User(int user_id, String password, String userName, boolean active) {
        this.user_id = user_id;
        this.password = password;
        this.userName = userName;
        this.active = active;
    }

    public int getUserId() {
        return user_id;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean getActive() {
        return active;
    }

    @Override
    public String toString() {
        return userName;
    }

    public Object getUsername() {
        return userName;
    }

    public boolean isActive() {
        return active;
    }
    

    
}
