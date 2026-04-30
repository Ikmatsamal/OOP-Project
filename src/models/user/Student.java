package models.user;

public class Student extends User {
    private String studentId;
    private String enrollmentYear;
    private double gpa;
    private String major;

    public Student(int id, String name, String email, String phoneNumber, 
                   String password, String studentId, String enrollmentYear, String major) {
        super(id, name, email, phoneNumber, password, "STUDENT");
        this.studentId = studentId;
        this.enrollmentYear = enrollmentYear;
        this.major = major;
        this.gpa = 0.0;
    }

    // Getters
    public String getStudentId() {
        return studentId;
    }

    public String getEnrollmentYear() {
        return enrollmentYear;
    }

    public double getGpa() {
        return gpa;
    }

    public String getMajor() {
        return major;
    }

    // Setters
    public void setGpa(double gpa) {
        this.gpa = gpa;
        this.updatedAt = System.currentTimeMillis();
    }

    public void setMajor(String major) {
        this.major = major;
        this.updatedAt = System.currentTimeMillis();
    }

    // Student-specific methods
    public void enrollCourse(String courseId) {
        System.out.println(name + " enrolled in course: " + courseId);
    }

    public void viewGrades() {
        System.out.println("Current GPA: " + gpa);
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Student ID: " + studentId);
        System.out.println("Enrollment Year: " + enrollmentYear);
        System.out.println("Major: " + major);
        System.out.println("GPA: " + gpa);
    }
}