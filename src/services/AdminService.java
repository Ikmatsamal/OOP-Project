package services;

import models.system.Message;
import models.user.Admin;
import models.user.Manager;
import models.user.Student;
import models.user.Teacher;
import models.user.User;
import enums.*;
import models.academic.Course;
import patterns.singleton.DatabaseSingleton;

import java.util.List;

public class AdminService {

    private final DatabaseSingleton db =
            DatabaseSingleton.getInstance();

    public List<User> getStudents() {
        return db.getUsers()
                .stream()
                .filter(user -> user instanceof Student)
                .toList();
    }

    public List<User> getTeachers() {
        return db.getUsers()
                .stream()
                .filter(user -> user instanceof Teacher)
                .toList();
    }

    public List<User> getManagers() {
        return db.getUsers()
                .stream()
                .filter(user -> user instanceof Manager)
                .toList();
    }

    public void sendMessage(Admin admin, String receiverEmail, String text) {
        Message message = new Message(
                admin.getEmail(),
                receiverEmail,
                text
        );

        db.addMessage(message);
    }
    

    public void addStudent(
            int id,
            String name,
            String email,
            String password,
            Major major,
            int year
    ) {
        Student student = new Student(
                id,
                name,
                email,
                password,
                major,
                year
        );

        db.addUser(student);
    }
    

    public void addTeacher(
            int id,
            String name,
            String email,
            String password,
            String department
    ) {
        Teacher teacher = new Teacher(
                id,
                name,
                email,
                password,
                department
        );

        db.addUser(teacher);
    }
    
    
    public void addManager(
            int id,
            String name,
            String email,
            String password,
            ManagerType type
    ) {
        Manager manager = new Manager(
                id,
                name,
                email,
                password,
                type
        );

        db.addUser(manager);
    }
    
    public User findUserById(int id) {
        return db.findUserById(id);
    }

    public void deleteUser(int id) {
        db.deleteUser(id);
        db.save();
    }

    public void updateName(User user, String name) {
        user.setName(name);
        db.save();
    }

    public void updateEmail(User user, String email) {
        user.setEmail(email);
        db.save();
    }

    public void updatePassword(User user, String password) {
        user.setPassword(password);
        db.save();
    }
    
    public List<Course> getCourses() {
        return db.getCourses();
    }

   
}