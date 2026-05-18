package patterns.singleton;

import models.academic.Mark;

import models.system.Message;
import models.system.Complaint;
import models.academic.Lesson;

import models.user.*;
import models.academic.Course;
import models.research.ResearchProject;
import utils.FileManager;

import models.system.News;

import java.util.*;

public class DatabaseSingleton implements java.io.Serializable {

    private List<News> news;
    private List<Message> messages;
    private List<Complaint> complaints;

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
        
        news = new ArrayList<>();
        
        messages = new ArrayList<>();
        complaints = new ArrayList<>();
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

    public List<News> getNews() {
        return news;
    }
    
    public List<Message> getMessages() {
        return messages;
    }

    public List<Complaint> getComplaints() {
        return complaints;
    }
    
    public void addNews(News newsItem) {
        news.add(newsItem);
        save();
    }
    
    public void addMessage(Message message) {

        messages.add(message);

        save();
    }
    
    @SuppressWarnings("unchecked")
    private void loadData() {
        Object loadedUsers = FileManager.loadUsers();
        Object loadedCourses = FileManager.loadCourses();
        Object loadedProjects = FileManager.loadProjects();
        Object loadedMarks = FileManager.loadMarks();
        Object loadedLessons = FileManager.loadLessons();
        Object loadedNews = FileManager.loadNews();
        Object loadedMessages = FileManager.loadMessages();
        Object loadedComplaints = FileManager.loadComplaints();

        if (loadedNews != null) {
            news = (List<News>) loadedNews;
        }

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
        if (loadedNews != null) {
            news = (List<News>) loadedNews;
        }
        if (loadedMessages != null) {
            messages = (List<Message>) loadedMessages;
        }

        if (loadedComplaints != null) {
            complaints = (List<Complaint>) loadedComplaints;
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
        FileManager.saveNews(news);
        FileManager.saveUsers(users);
        FileManager.saveCourses(courses);
        FileManager.saveProjects(projects);
        FileManager.saveMarks(marks);
        FileManager.saveLessons(lessons);
        FileManager.saveMessages(messages);
        FileManager.saveComplaints(complaints);
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
    
    public void addComplaint(Complaint complaint) {
        complaints.add(complaint);
        save();
    }
}


