package models.user;

import models.academic.Course;
import models.user.Student;
import models.user.Teacher;
import models.research.ResearcherDecorator;
import enums.ManagerType;

public class Manager extends Employee {

    private ManagerType type;

    public Manager(int id, String name, String email,
            String password, ManagerType type) {

    	super(id, name, email, password);

        this.role = "MANAGER";
        this.type = type;
    }

    public ManagerType getType() {
        return type;
    }

    public void approveRegistration(Student s, Course c) {

        c.enrollStudent(s);
    
        System.out.println(
                name + " approved registration of "
                        + s.getName()
                        + " to "
                        + c.getName()
        );
    }

    public void assignCourse(Course c, Teacher t) {
        t.manageCourse(c);
        System.out.println(name + " assigned course " + c.getName() + " to " + t.getName());
    }

    public void assignSupervisor(Student s, ResearcherDecorator r) {
        System.out.println(name + " assigned supervisor to " + s.getName());
    }

    public void manageNews() {
        System.out.println(name + " is managing news");
    }

    public void viewRequests() {
        System.out.println(name + " is viewing requests");
    }

    public void createReport() {
        System.out.println(name + " is creating report");
    }
    @Override
    public String toString() {
        return "Manager {" +
                "id=" + getId() +
                ", name='" + getName() + '\'' +
                ", email='" + getEmail() + '\'' +
                ", type=" + type +
                '}';
    }
}