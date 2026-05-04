package models.user;

import java.util.ArrayList;
import java.util.List;

public class Admin extends User {

    private String adminId;
    private List<String> permissions;
    private boolean canManageUsers;
    private boolean canViewLogs;

    public Admin(int id, String name, String email, String phoneNumber, String password, String adminId) {
        super(id, name, email, phoneNumber, password, "ADMIN");
        this.adminId = adminId;
        this.permissions = new ArrayList<>();
        this.canManageUsers = true;
        this.canViewLogs = true;
    }

    public void addUser(User u) {
        System.out.println(name + " added user: " + u.getName());
    }

    public void removeUser(String id) {
        System.out.println(name + " removed user with ID: " + id);
    }

    public void updateUser(User u) {
        System.out.println(name + " updated user: " + u.getName());
    }

    public List<String> viewLogs() {
        List<String> logs = new ArrayList<>();
        logs.add(name + " viewed system logs");
        return logs;
    }

    public void addPermission(String permission) {
        permissions.add(permission);
    }

    public String getAdminId() {
        return adminId;
    }

    public List<String> getPermissions() {
        return permissions;
    }

    public boolean canManageUsers() {
        return canManageUsers;
    }

    public boolean canViewLogs() {
        return canViewLogs;
    }

    public void setManageUsersPermission(boolean permission) {
        this.canManageUsers = permission;
        this.updatedAt = System.currentTimeMillis();
    }

    public void setViewLogsPermission(boolean permission) {
        this.canViewLogs = permission;
        this.updatedAt = System.currentTimeMillis();
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Admin ID: " + adminId);
        System.out.println("Can Manage Users: " + canManageUsers);
        System.out.println("Can View Logs: " + canViewLogs);
    }

    @Override
    public String toString() {
        return "Admin{" +
                "name='" + name + '\'' +
                ", adminId='" + adminId + '\'' +
                '}';
    }
}