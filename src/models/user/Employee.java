package models.user;

public class Employee extends User {
    private String employeeId;
    private String department;
    private String jobTitle;
    private double salary;

    public Employee(int id, String name, String email, String phoneNumber, 
                    String password, String employeeId, String department, String jobTitle) {
        super(id, name, email, phoneNumber, password, "EMPLOYEE");
        this.employeeId = employeeId;
        this.department = department;
        this.jobTitle = jobTitle;
        this.salary = 0.0;
    }

    
    public String getEmployeeId() {
        return employeeId;
    }

    public String getDepartment() {
        return department;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public double getSalary() {
        return salary;
    }

    
    public void setDepartment(String department) {
        this.department = department;
        this.updatedAt = System.currentTimeMillis();
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
        this.updatedAt = System.currentTimeMillis();
    }

    public void setSalary(double salary) {
        this.salary = salary;
        this.updatedAt = System.currentTimeMillis();
    }

    
    public void submitLeaveRequest(String reason, String startDate, String endDate) {
        System.out.println(name + " submitted leave request from " + startDate + " to " + endDate);
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Employee ID: " + employeeId);
        System.out.println("Department: " + department);
        System.out.println("Job Title: " + jobTitle);
        System.out.println("Salary: " + salary);
    }
}