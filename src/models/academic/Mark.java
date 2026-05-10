package models.academic;

import enums.GradeType;
import models.user.Student;

import java.io.Serializable;

public class Mark implements Serializable{
    private double attestation1;
    private double attestation2;
    private double finalExam;
    private Student student;
    private Course course;

    public Mark(Student student, Course course,
                double attestation1, double attestation2, double finalExam) {
        this.student = student;
        this.course = course;
        this.attestation1 = attestation1;
        this.attestation2 = attestation2;
        this.finalExam = finalExam;
    }

    public double getFinalGrade() {
        // Стандартная формула: 30% + 30% + 40%
        return (attestation1 * 0.3) + (attestation2 * 0.3) + (finalExam * 0.4);
    }

    public GradeType getGradeType() {
        double grade = getFinalGrade();
        if (grade >= 90) return GradeType.A;
        if (grade >= 85) return GradeType.A_MINUS;
        if (grade >= 80) return GradeType.B_PLUS;
        if (grade >= 75) return GradeType.B;
        if (grade >= 70) return GradeType.B_MINUS;
        if (grade >= 65) return GradeType.C_PLUS;
        if (grade >= 60) return GradeType.C;
        return GradeType.F;
    }

    public boolean isPassed() {
        return getFinalGrade() >= 50.0;
    }

    // Getters & Setters
    public double getAttestation1() { return attestation1; }
    public double getAttestation2() { return attestation2; }
    public double getFinalExam() { return finalExam; }
    public Student getStudent() { return student; }
    public Course getCourse() { return course; }

    public void setAttestation1(double attestation1) { this.attestation1 = attestation1; }
    public void setAttestation2(double attestation2) { this.attestation2 = attestation2; }
    public void setFinalExam(double finalExam) { this.finalExam = finalExam; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Mark)) return false;
        Mark m = (Mark) o;
        return student.equals(m.student) && course.equals(m.course);
    }

    @Override
    public int hashCode() {
        return student.hashCode() + course.hashCode();
    }

    @Override
    public String toString() {
        return "Mark{student='" + student.getName() + "', course='" + course.getName()
                + "', finalGrade=" + getFinalGrade() + ", grade=" + getGradeType() + "}";
    }
}