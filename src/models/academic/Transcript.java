
package models.academic;

import models.user.Student;

import java.util.ArrayList;
import java.util.List;

import java.io.Serializable;

public class Transcript implements Serializable{
    private Student student;
    private List<Mark> marks;

    public Transcript(Student student) {
        this.student = student;
        this.marks = new ArrayList<>();
    }

    public void addMark(Mark mark) {
        // Если оценка за этот курс уже есть то обновляем
        marks.removeIf(m -> m.getCourse().equals(mark.getCourse()));
        marks.add(mark);
    }

    public List<Mark> getMarks() {
        return marks;
    }
    
    public List<Mark> getMarks(Course course) {
        List<Mark> result = new ArrayList<>();

        for (Mark mark : marks) {
            if (mark.getCourse().equals(course)) {
                result.add(mark);
            }
        }

        return result;
    }
    

    public double calculateGPA() {
        if (marks.isEmpty()) return 0.0;

        double total = 0.0;
        int totalCredits = 0;

        for (Mark mark : marks) {
            int credits = mark.getCourse().getCredits();
            total += gradeToPoint(mark.getFinalGrade()) * credits;
            totalCredits += credits;
        }

        return totalCredits == 0 ? 0.0 : total / totalCredits;
    }

    private double gradeToPoint(double grade) {

        if (grade < 0 || grade > 100) {
            throw new IllegalArgumentException(
                    "Grade must be between 0 and 100"
            );
        }

        if (grade >= 90) return 4.0;
        if (grade >= 85) return 3.7;
        if (grade >= 80) return 3.3;
        if (grade >= 75) return 3.0;
        if (grade >= 70) return 2.7;
        if (grade >= 65) return 2.3;
        if (grade >= 60) return 2.0;

        return 0.0; // F
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("=== Transcript for ")
            .append(student.getName())
            .append(" ===\n");
        for (Mark m : marks) {
            sb.append("  ").append(m.getCourse().getName())
              .append(" → ").append(m.getFinalGrade())
              .append(" (").append(m.getGradeType()).append(")\n");
        }
        sb.append("GPA: ").append(String.format("%.2f", calculateGPA()));
        return sb.toString();
    }
}