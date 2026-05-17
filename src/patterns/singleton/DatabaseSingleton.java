package patterns.singleton;

import models.academic.Mark;
import models.academic.Lesson;

import models.user.*;
import models.academic.Course;
import models.research.ResearchProject;
import utils.FileManager;

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
            instance.loadData();

            if (instance.users.isEmpty()) {
                instance.initializeTestData();
            }
        }

        return instance;
    }
    
    
    
    

    // ---------- USERS ----------
    public void addUser(User user) {
        users.add(user);

        userMap.put(user.getEmail(), user);
       save();
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
        save();
    }

    public List<Course> getCourses() {
        return Collections.unmodifiableList(courses);
    }

    // ---------- PROJECTS ----------
    public void addProject(ResearchProject project) {
        projects.add(project);
        save();
    }

    public List<ResearchProject> getProjects() {
        return Collections.unmodifiableList(projects);
    }

        // ---------- MARKS ----------

    public void addMark(Mark mark) {
        marks.add(mark);
        save();
    }

    public List<Mark> getMarks() {
        return Collections.unmodifiableList(marks);
    }


    // ---------- LESSONS ----------

    public void addLesson(Lesson lesson) {
        lessons.add(lesson);
        save();
    }

    public List<Lesson> getLessons() {
        return Collections.unmodifiableList(lessons);
    }
    
    
    private void initializeTestData() {
        if (getUserByEmail("admin@kbtu.kz").isPresent()) {
            return;
        }

        Admin admin = new Admin(
                1,
                "Admin",
                "admin@kbtu.kz",
                "admin123"
        );

        addUser(admin);
    }
    
    @SuppressWarnings("unchecked")
    private void loadData() {
        Object loadedUsers = FileManager.loadUsers();
        Object loadedCourses = FileManager.loadCourses();
        Object loadedProjects = FileManager.loadProjects();
        Object loadedMarks = FileManager.loadMarks();
        Object loadedLessons = FileManager.loadLessons();

        if (loadedUsers != null) {
            users = (List<User>) loadedUsers;
        }

        if (loadedCourses != null) {
            courses = (List<Course>) loadedCourses;
        }

        if (loadedProjects != null) {
            projects = (List<ResearchProject>) loadedProjects;
        }

        if (loadedMarks != null) {
            marks = (List<Mark>) loadedMarks;
        }

        if (loadedLessons != null) {
            lessons = (List<Lesson>) loadedLessons;
        }

        rebuildUserMap();
    }
    
    private void rebuildUserMap() {
        userMap.clear();

        for (User user : users) {
            userMap.put(user.getEmail(), user);
        }
    }
    
    public void save() {
        FileManager.saveUsers(users);
        FileManager.saveCourses(courses);
        FileManager.saveProjects(projects);
        FileManager.saveMarks(marks);
        FileManager.saveLessons(lessons);
    }
    
    public User findUserById(int id) {
        for (User user : users) {
            if (user.getId() == id) {
                return user;
            }
        }
        return null;
    }

    public void deleteUser(int id) {
        users.removeIf(user -> user.getId() == id);
        rebuildUserMap();
        save();
    }
}


