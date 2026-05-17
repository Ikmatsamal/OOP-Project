package views;

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
