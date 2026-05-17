# TeacherView.java

```java
package views;

import java.util.List;
import java.util.Scanner;

import models.academic.Course;
import models.academic.Mark;
import models.user.Student;
import models.user.Teacher;
import services.CourseService;
import utils.Logger;

public class TeacherView {

    private final Scanner sc = new Scanner(System.in);
    private final CourseService courseService = new CourseService();

    public void show(Teacher teacher) {

        while (true) {

            System.out.println("\n=== TEACHER MENU ===");
            System.out.println("1. View my courses");
            System.out.println("2. View students of course");
            System.out.println("3. Put marks");
            System.out.println("4. Generate report");
            System.out.println("5. Research menu");
            System.out.println("0. Logout");

            int choice = readInt("Choose option: ");

            switch (choice) {
                case 1 -> viewMyCourses(teacher);
                case 2 -> viewStudents(teacher);
                case 3 -> putMarks(teacher);
                case 4 -> generateReport(teacher);
                case 5 -> new ResearchView().show(teacher);
                case 0 -> {
                    Logger.info("Logout: " + teacher.getEmail());
                    return;
                }
                default -> System.out.println("Invalid option.");
            }
        }
    }

    private void viewMyCourses(Teacher teacher) {

        List<Course> courses = teacher.getCourses();

        if (courses.isEmpty()) {
            System.out.println("No assigned courses.");
            return;
        }

        System.out.println("=== MY COURSES ===");

        for (Course course : courses) {
            System.out.printf("%s - %s (%d credits)\n",
                    course.getCourseId(),
                    course.getName(),
                    course.getCredits());
        }
    }

    private void viewStudents(Teacher teacher) {

        Course course = chooseCourse(teacher);

        if (course == null) {
            return;
        }

        List<Student> students = course.getEnrolledStudents();

        if (students.isEmpty()) {
            System.out.println("No students enrolled.");
            return;
        }

        System.out.println("=== STUDENTS ===");

        for (Student student : students) {
            System.out.println(student);
        }
    }

    private void putMarks(Teacher teacher) {

        Course course = chooseCourse(teacher);

        if (course == null) {
            return;
        }

        List<Student> students = course.getEnrolledStudents();

        if (students.isEmpty()) {
            System.out.println("No students in this course.");
            return;
        }

        System.out.println("=== STUDENTS ===");

        for (int i = 0; i < students.size(); i++) {
            System.out.println((i + 1) + ". " + students.get(i).getName());
        }

        int choice = readInt("Choose student: ");

        if (choice < 1 || choice > students.size()) {
            System.out.println("Invalid student.");
            return;
        }

        Student student = students.get(choice - 1);

        double att1 = readDouble("Attestation 1: ");
        double att2 = readDouble("Attestation 2: ");
        double finalExam = readDouble("Final exam: ");

        Mark mark = new Mark(student, course, att1, att2, finalExam);

        teacher.putMark(student, mark);

        System.out.println("Mark added successfully.");
    }

    private void generateReport(Teacher teacher) {
        teacher.generateMarkReport();
    }

    private Course chooseCourse(Teacher teacher) {

        List<Course> courses = teacher.getCourses();

        if (courses.isEmpty()) {
            System.out.println("No assigned courses.");
            return null;
        }

        System.out.println("=== COURSES ===");

        for (int i = 0; i < courses.size(); i++) {
            Course course = courses.get(i);

            System.out.printf("%d. %s - %s\n",
                    i + 1,
                    course.getCourseId(),
                    course.getName());
        }

        int choice = readInt("Choose course: ");

        if (choice < 1 || choice > courses.size()) {
            System.out.println("Invalid course.");
            return null;
        }

        return courses.get(choice - 1);
    }

    private int readInt(String message) {

        while (true) {
            try {
                System.out.print(message);
                return Integer.parseInt(sc.nextLine().trim());
            }
            catch (NumberFormatException e) {
                System.out.println("Please enter a number.");
            }
        }
    }

    private double readDouble(String message) {

        while (true) {
            try {
                System.out.print(message);
                return Double.parseDouble(sc.nextLine().trim());
            }
            catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }
}
```

# Что делает этот TeacherView

## Меню:

* просмотр своих курсов
* просмотр студентов курса
* выставление оценок
* report
* research menu
* logout

# Что еще проверить

## 1. Manager assigning courses

В `ManagerView` должно быть:

```java
teacher.manageCourse(course);
course.addInstructor(teacher);
```

Иначе у teacher.getCourses() будет пусто.

---

## 2. После выставления оценок

Можно добавить:

```java
DatabaseSingleton.getInstance().save();
```

чтобы marks сохранялись после перезапуска.

---

## 3. Проверить login

Нужно чтобы teacher действительно логинился через:

```java
else if (user instanceof Teacher teacher) {
    new TeacherView().show(teacher);
}
```

У вас это уже есть.
