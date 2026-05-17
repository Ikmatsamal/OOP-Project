package services;

import java.util.List;

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
}
