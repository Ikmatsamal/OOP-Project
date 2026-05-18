package services;

import java.util.List;
import models.system.News;
import models.system.Message;
import models.system.Complaint;
import patterns.singleton.DatabaseSingleton;

import models.academic.Course;
import models.academic.Mark;
import models.user.Student;
import models.user.Teacher;

public class TeacherService {

    public List<Course> getTeacherCourses(Teacher teacher) {
        return teacher.getCourses();
    }

    public List<Student> getCourseStudents(Course course) {
        return course.getEnrolledStudents();
    }
    public List<News> getNews() {
        return DatabaseSingleton.getInstance().getNews();
    }

    public void putMark(
            Teacher teacher,
            Student student,
            Course course,
            double att1,
            double att2,
            double finalExam
    ) {

        Mark mark = new Mark(
                student,
                course,
                att1,
                att2,
                finalExam
        );

        teacher.putMark(student, mark);
    }

    public void generateReport(Teacher teacher) {
        teacher.generateMarkReport();
    }
    
    public void sendMessage(Teacher teacher, String receiverEmail, String text) {
        if (receiverEmail == null || receiverEmail.isBlank()) {
            throw new IllegalArgumentException("Receiver email cannot be empty");
        }

        if (text == null || text.isBlank()) {
            throw new IllegalArgumentException("Message cannot be empty");
        }

        Message message = new Message(
                teacher.getEmail(),
                receiverEmail,
                text
        );

        DatabaseSingleton.getInstance().getMessages().add(message);
        DatabaseSingleton.getInstance().save();
    }

    public void sendComplaint(Teacher teacher, String text) {
        if (text == null || text.isBlank()) {
            throw new IllegalArgumentException("Complaint cannot be empty");
        }

        Complaint complaint = new Complaint(
                teacher.getEmail(),
                text
        );

        DatabaseSingleton.getInstance().getComplaints().add(complaint);
        DatabaseSingleton.getInstance().save();
    }
    
    public boolean isValidMark(
            double att1,
            double att2,
            double finalExam
    ) {
        return att1 >= 0 && att1 <= 30
                && att2 >= 0 && att2 <= 30
                && finalExam >= 0 && finalExam <= 40
                && att1 + att2 <= 60;
    }
    
   
}
