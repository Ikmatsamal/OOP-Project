package services;

import models.academic.Course;
import models.system.News;
import models.user.Manager;
import models.user.Student;
import models.user.Teacher;
import patterns.observer.NewsPublisher;
import patterns.singleton.DatabaseSingleton;
import models.system.Complaint;

import java.time.LocalDateTime;
import java.util.List;

public class ManagerService {
	
	
    private final DatabaseSingleton db =
            DatabaseSingleton.getInstance();

    public List<Student> getStudents() {

        return db.getUsers()
                .stream()
                .filter(user -> user instanceof Student)
                .map(user -> (Student) user)
                .toList();
    }

    public List<Teacher> getTeachers() {

        return db.getUsers()
                .stream()
                .filter(user -> user instanceof Teacher)
                .map(user -> (Teacher) user)
                .toList();
    }

    public List<Student> getStudentsSortedByGPA() {

        return db.getUsers()
                .stream()
                .filter(user -> user instanceof Student)
                .map(user -> (Student) user)
                .sorted((s1, s2) ->
                        Double.compare(
                                s2.getGPA(),
                                s1.getGPA()
                        ))
                .toList();
    }

    public List<Teacher> getTeachersAlphabetically() {

        return db.getUsers()
                .stream()
                .filter(user -> user instanceof Teacher)
                .map(user -> (Teacher) user)
                .sorted((t1, t2) ->
                        t1.getName()
                                .compareToIgnoreCase(
                                        t2.getName()
                                ))
                .toList();
    }
    
    public List<Complaint> getComplaints() {
        return DatabaseSingleton
                .getInstance()
                .getComplaints();
    }
    
    public Student findStudentByEmail(String email) {

        return db.getUsers()
                .stream()
                .filter(user -> user instanceof Student)
                .map(user -> (Student) user)
                .filter(student ->
                        student.getEmail()
                                .equalsIgnoreCase(email))
                .findFirst()
                .orElse(null);
    }

    public void approveRegistration(
            Student student,
            Course course
    ) {
        course.enrollStudent(student);
        db.save();
    }

    public List<News> getNews() {
        return db.getNews();
    }
    
    public String generateReport() {

        long students =
                db.getUsers().stream()
                .filter(user -> user instanceof Student)
                .count();

        long teachers =
                db.getUsers().stream()
                .filter(user -> user instanceof Teacher)
                .count();

        int courses =
                db.getCourses().size();

        double avgGpa =
                db.getUsers().stream()
                .filter(user -> user instanceof Student)
                .map(user -> (Student) user)
                .mapToDouble(Student::getGPA)
                .average()
                .orElse(0.0);

        return
                "Total students: " + students + "\n"
                + "Total teachers: " + teachers + "\n"
                + "Total courses: " + courses + "\n"
                + "Average GPA: " + avgGpa;
    }

    public void publishNews(
            Manager manager,
            String title,
            String content
    ) {
        News news = new News(
                title,
                content,
                java.time.LocalDateTime.now(),
                manager.getName()
        );

        db.addNews(news);

        NewsPublisher publisher = new NewsPublisher();

        db.getUsers()
                .forEach(user -> publisher.addObserver(user));

        publisher.notifyObservers(
                "New news published: " + title
        );
    }

    public void assignCourseToTeacher(
            Course course,
            Teacher teacher
    ) {

        teacher.manageCourse(course);

        course.addInstructor(teacher);

        db.save();
    }
}