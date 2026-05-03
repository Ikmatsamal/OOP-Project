package patterns.singleton;

import models.user.User;
import models.academic.Course;
import models.research.ResearchProject;

import java.util.*;

public class DatabaseSingleton {

    private static DatabaseSingleton instance;

    private List<User> users;
    private List<Course> courses;
    private List<ResearchProject> projects;

    private DatabaseSingleton() {
        users = new ArrayList<>();
        courses = new ArrayList<>();
        projects = new ArrayList<>();
    }

    public static DatabaseSingleton getInstance() {
        if (instance == null) {
            instance = new DatabaseSingleton();
        }
        return instance;
    }

    // ---------- USERS ----------
    public void addUser(User user) {
        users.add(user);
    }

    public Optional<User> getUserByEmail(String email) {
        return users.stream()
                .filter(u -> u.getEmail().equals(email))
                .findFirst();
    }

    public List<User> getUsers() {
        return Collections.unmodifiableList(users);
    }

    // ---------- COURSES ----------
    public void addCourse(Course course) {
        courses.add(course);
    }

    public List<Course> getCourses() {
        return Collections.unmodifiableList(courses);
    }

    // ---------- PROJECTS ----------
    public void addProject(ResearchProject project) {
        projects.add(project);
    }

    public List<ResearchProject> getProjects() {
        return Collections.unmodifiableList(projects);
    }
}
