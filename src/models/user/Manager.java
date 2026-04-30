package models.user;

public class Manager extends Employee {
    private String managerId;
    private int supervisedEmployeeCount;
    private String[] subordinates;

    public Manager(int id, String name, String email, String phoneNumber, 
                   String password, String employeeId, String department, 
                   String jobTitle, String managerId) {
        super(id, name, email, phoneNumber, password, employeeId, department, jobTitle);
        this.role = "MANAGER";
        this.managerId = managerId;
        this.supervisedEmployeeCount = 0;
        this.subordinates = new String[50];
    }

    
    public String getManagerId() {
        return managerId;
    }

    public int getSupervisedEmployeeCount() {
        return supervisedEmployeeCount;
    }

    public String[] getSubordinates() {
        return subordinates;
    }

    
    public void addSubordinate(String employeeId) {
        if (supervisedEmployeeCount < subordinates.length) {
            subordinates[supervisedEmployeeCount] = employeeId;
            supervisedEmployeeCount++;
            System.out.println(name + " added subordinate: " + employeeId);
        }
    }

    public void approveLeavRequest(String employeeId) {
        System.out.println(name + " (Manager) approved leave request for: " + employeeId);
    }

    public void evaluateEmployee(String employeeId, String evaluation) {
        System.out.println(name + " evaluated " + employeeId + ": " + evaluation);
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Manager ID: " + managerId);
        System.out.println("Supervised Employees: " + supervisedEmployeeCount);
    }
}