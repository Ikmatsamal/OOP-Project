package models.user;

public class Admin extends User {
    private String adminId;
    private String[] permissions;
    private boolean canManageUsers;
    private boolean canViewLogs;

    public Admin(int id, String name, String email, String phoneNumber, String password, String adminId) {
        super(id, name, email, phoneNumber, password, "ADMIN");
        this.adminId = adminId;
        this.permissions = new String[20];
        this.canManageUsers = true;
        this.canViewLogs = true;
    }

    
    public String getAdminId() {
        return adminId;
    }

    public boolean canManageUsers() {
        return canManageUsers;
    }

    public boolean canViewLogs() {
        return canViewLogs;
    }

    public String[] getPermissions() {
        return permissions;
    }

    
    public void setManageUsersPermission(boolean permission) {
        this.canManageUsers = permission;
        this.updatedAt = System.currentTimeMillis();
    }

    public void setViewLogsPermission(boolean permission) {
        this.canViewLogs = permission;
        this.updatedAt = System.currentTimeMillis();
    }

    
    public void addPermission(String permission) {
        for (int i = 0; i < permissions.length; i++) {
            if (permissions[i] == null) {
                permissions[i] = permission;
                System.out.println("Permission added: " + permission);
                return;
            }
        }
    }

    public void deleteUser(int userId) {
        System.out.println(name + " (Admin) deleted user with ID: " + userId);
    }

    public void viewSystemLogs() {
        System.out.println(name + " is viewing system logs");
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Admin ID: " + adminId);
        System.out.println("Can Manage Users: " + canManageUsers);
        System.out.println("Can View Logs: " + canViewLogs);
    }
}