package models.user;
import java.io.Serializable;

public class User implements Serializable, patterns.observer.Observer {
    
    protected int id;
    protected String name;
    protected String email;
    protected String password;
    protected String role;
    protected boolean isActive;
    protected long createdAt;
    protected long updatedAt;

    
    public User(int id, String name, String email, String password, String role) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
        this.isActive = true;
        this.createdAt = System.currentTimeMillis();
        this.updatedAt = System.currentTimeMillis();
    }

    
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    

    public String getRole() {
        return role;
    }

    public boolean isActive() {
        return isActive;
    }

    public long getCreatedAt() {
        return createdAt;
    }

    public long getUpdatedAt() {
        return updatedAt;
    }

    public String getPassword() {
        return password;
}

    
    public void setName(String name) {
        this.name = name;
        this.updatedAt = System.currentTimeMillis();
    }

    public void setEmail(String email) {
        this.email = email;
        this.updatedAt = System.currentTimeMillis();
    }

    

    public void setPassword(String password) {
        this.password = password;
        this.updatedAt = System.currentTimeMillis();
    }

    public void setActive(boolean active) {
        isActive = active;
        this.updatedAt = System.currentTimeMillis();
    }

    
    public void displayInfo() {
        System.out.println("=== User Information ===");
        System.out.println("ID: " + id);
        System.out.println("Name: " + name);
        System.out.println("Email: " + email);
        System.out.println("Role: " + role);
        System.out.println("Active: " + isActive);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", role='" + role + '\'' +
                ", isActive=" + isActive +
                '}';
    }

    @Override
    public void update(String message) {

        System.out.println(name + " received news: " + message);

    }
}