package models.academic;

import enums.GradeType;
import models.user.Student;

import java.io.Serializable;

public class Mark implements Serializable {

    private double attestation1;
    private double attestation2;
    private double finalExam;

    private Student student;
    private Course course;

public Mark(
        Student student,
        Course course,
        double attestation1,
        double attestation2,
        double finalExam
) {
    if (student == null) {
        throw new IllegalArgumentException("Student cannot be null");
    }

    if (course == null) {
        throw new IllegalArgumentException("Course cannot be null");
    }

    if (attestation1 < 0 || attestation1 > 30) {
        throw new IllegalArgumentException("Attestation 1 must be between 0 and 30");
    }

    if (attestation2 < 0 || attestation2 > 30) {
        throw new IllegalArgumentException("Attestation 2 must be between 0 and 30");
    }

    if (finalExam < 0 || finalExam > 40) {
        throw new IllegalArgumentException("Final exam must be between 0 and 40");
    }

    if (attestation1 + attestation2 > 60) {
        throw new IllegalArgumentException("Total attestation cannot exceed 60");
    }

    this.student = student;
    this.course = course;
    this.attestation1 = attestation1;
    this.attestation2 = attestation2;
    this.finalExam = finalExam;
}
    public double getFinalGrade() {
        return attestation1 + attestation2 + finalExam;
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

    public double getAttestation1() {
        return attestation1;
    }

    public double getAttestation2() {
        return attestation2;
    }

    public double getFinalExam() {
        return finalExam;
    }

    public Student getStudent() {
        return student;
    }

    public Course getCourse() {
        return course;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;

        if (!(o instanceof Mark)) return false;

        Mark m = (Mark) o;

        return student.equals(m.student)
                && course.equals(m.course);
    }

    @Override
    public int hashCode() {
        return student.hashCode() + course.hashCode();
    }

    @Override
    public String toString() {

        return "Mark{" +
                "student='" + student.getName() + '\'' +
                ", course='" + course.getName() + '\'' +
                ", finalGrade=" + getFinalGrade() +
                ", grade=" + getGradeType() +
                '}';
    }
}