package views;
        double finalExam = readDouble();

        teacherService.putMark(
                teacher,
                student,
                course,
                att1,
                att2,
                finalExam
        );

        System.out.println("Mark added successfully");
    }

    // CHOOSE COURSE

    private Course chooseCourse(Teacher teacher) {

        List<Course> courses =
                teacherService.getTeacherCourses(teacher);

        if (courses.isEmpty()) {
            System.out.println("No assigned courses");
            return null;
        }

        System.out.println("\n===== COURSES =====");

        for (int i = 0; i < courses.size(); i++) {

            Course course = courses.get(i);

            System.out.println(
                    (i + 1) + ". " +
                    course.getCourseId() +
                    " - " +
                    course.getName()
            );
        }

        System.out.print("Choose course: ");

        int choice = readInt();

        if (choice < 1 || choice > courses.size()) {
            System.out.println("Invalid course");
            return null;
        }

        return courses.get(choice - 1);
    }

    // READ INT

    private int readInt() {

        while (true) {

            try {

                return Integer.parseInt(
                        sc.nextLine()
                );

            } catch (Exception e) {

                System.out.print(
                        "Enter number: "
                );
            }
        }
    }

    // READ DOUBLE

    private double readDouble() {

        while (true) {

            try {

                return Double.parseDouble(
                        sc.nextLine()
                );

            } catch (Exception e) {

                System.out.print(
                        "Enter valid number: "
                );
            }
        }
    }
}
