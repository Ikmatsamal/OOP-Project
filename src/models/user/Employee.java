package models.user;

import interfaces.MessageSender;

public class Employee extends User implements MessageSender {

    private double salary;

    public Employee(
            int id,
            String name,
            String email,
            String password
    ) {
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

    @Override
    public void sendMessage(Employee receiver, String text) {

        System.out.println(
                "Message sent to "
                        + receiver.getEmail()
        );
    }

    @Override
    public void displayInfo() {

        super.displayInfo();

        System.out.println(
                "Salary: " + salary
        );
    }
}