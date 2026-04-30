package models.user;

public class Teacher extends User {
    private String employeeId;
    private String department;
    private String qualification;
    private String[] teachingSubjects;

    public Teacher(int id, String name, String email, String phoneNumber, 
                   String password, String employeeId, String department, String qualification) {
        super(id, name, email, phoneNumber, password, "TEACHER");
        this.employeeId = employeeId;
        this.department = department;
        this.qualification = qualification;
        this.teachingSubjects = new String[10];
    }

    
    public String getEmployeeId() {
        return employeeId;
    }

    public String getDepartment() {
        return department;
    }

    public String getQualification() {
        return qualification;
    }

    public String[] getTeachingSubjects() {
        return teachingSubjects;
    }

    
    public void setDepartment(String department) {
        this.department = department;
        this.updatedAt = System.currentTimeMillis();
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
        this.updatedAt = System.currentTimeMillis();
    }

    
    public void addTeachingSubject(String subject) {
        for (int i = 0; i < teachingSubjects.length; i++) {
            if (teachingSubjects[i] == null) {
                teachingSubjects[i] = subject;
                System.out.println(name + " is now teaching: " + subject);
                return;
            }
        }
    }

    public void gradeStudent(String studentId, double grade) {
        System.out.println(name + " graded student " + studentId + " with: " + grade);
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Employee ID: " + employeeId);
        System.out.println("Department: " + department);
        System.out.println("Qualification: " + qualification);
    }
}