package models.user;

public class Employee extends User {
    private double salary;

    public Employee(int id, String name, String email, 
                    String password) {
        super(id, name, email, password, "EMPLOYEE");
        this.salary = 0.0;
    }

    
    public double getSalary() {
        return salary;
    }


    public void setSalary(double salary) {
        this.salary = salary;
        this.updatedAt = System.currentTimeMillis();
    }

    
    public void submitLeaveRequest(String reason, String startDate, String endDate) {
        System.out.println(name + " submitted leave request from " + startDate + " to " + endDate);
    }
    
    public void sendMessage(String message) {
        System.out.println(name + " sent message: " + message);
    }

    public void sendComplaint(String complaint) {
        System.out.println(name + " submitted complaint: " + complaint);
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Salary: " + salary);
    }
}