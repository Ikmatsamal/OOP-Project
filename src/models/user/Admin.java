package models.user;

import java.util.ArrayList;
import java.util.List;

public class Admin extends User {

    private List<String> permissions;
    private boolean canManageUsers;
    private boolean canViewLogs;

    public Admin(int id, String name, String email, String password) {
        super(id, name, email, password, "ADMIN");

        this.permissions = new ArrayList<>();
        this.canManageUsers = true;
        this.canViewLogs = true;

        permissions.add("MANAGE_USERS");
        permissions.add("VIEW_LOGS");
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
        System.out.println("Can Manage Users: " + canManageUsers);
        System.out.println("Can View Logs: " + canViewLogs);
    }

    @Override
    public String toString() {
        return "Admin{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}