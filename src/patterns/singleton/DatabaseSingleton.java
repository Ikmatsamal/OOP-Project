package patterns.singleton;

import models.academic.Mark;
import models.academic.Lesson;

import models.user.User;
import models.academic.Course;
import models.research.ResearchProject;

import java.util.*;

public class DatabaseSingleton implements java.io.Serializable {

    private static final long serialVersionUID = 1L;
    
    private Map<String, User> userMap;

    private static DatabaseSingleton instance;

    private List<User> users;
    private List<Course> courses;
    private List<ResearchProject> projects;

    private List<Mark> marks;
    private List<Lesson> lessons;

    private DatabaseSingleton() {
        users = new ArrayList<>();
        courses = new ArrayList<>();
        projects = new ArrayList<>();

        marks = new ArrayList<>();
        lessons = new ArrayList<>();

        userMap = new HashMap<>();
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

        userMap.put(user.getEmail(), user);
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

        // ---------- MARKS ----------

    public void addMark(Mark mark) {
        marks.add(mark);
    }

    public List<Mark> getMarks() {
        return Collections.unmodifiableList(marks);
    }


    // ---------- LESSONS ----------

    public void addLesson(Lesson lesson) {
        lessons.add(lesson);
    }

    public List<Lesson> getLessons() {
        return Collections.unmodifiableList(lessons);
    }

}
