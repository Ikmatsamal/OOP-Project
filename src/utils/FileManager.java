package utils;

import patterns.singleton.DatabaseSingleton;

import java.io.*;

public class FileManager {

    private static final String DATA_FOLDER = "data";

    // ---------- SAVE ----------

    public static void saveUsers(Object users) {
        saveObject(users, "users.ser");
    }

    public static void saveCourses(Object courses) {
        saveObject(courses, "courses.ser");
    }

    public static void saveProjects(Object projects) {
        saveObject(projects, "projects.ser");
    }

    public static void saveMarks(Object marks) {
        saveObject(marks, "marks.ser");
    }

    public static void saveLessons(Object lessons) {
        saveObject(lessons, "lessons.ser");
    }

    // ---------- LOAD ----------

    public static Object loadUsers() {
        return loadObject("users.ser");
    }

    public static Object loadCourses() {
        return loadObject("courses.ser");
    }

    public static Object loadProjects() {
        return loadObject("projects.ser");
    }

    public static Object loadMarks() {
        return loadObject("marks.ser");
    }

    public static Object loadLessons() {
        return loadObject("lessons.ser");
    }

    // ---------- COMMON METHODS ----------

    private static void saveObject(Object obj, String fileName) {

        try {

            File folder = new File(DATA_FOLDER);

            if (!folder.exists()) {
                folder.mkdirs();
            }

            ObjectOutputStream out =
                    new ObjectOutputStream(
                            new FileOutputStream(DATA_FOLDER + "/" + fileName)
                    );

            out.writeObject(obj);

            out.close();

            System.out.println(fileName + " saved");

        } catch (Exception e) {

            e.printStackTrace();

        }
    }

    private static Object loadObject(String fileName) {

        try {

            ObjectInputStream in =
                    new ObjectInputStream(
                            new FileInputStream(DATA_FOLDER + "/" + fileName)
                    );

            Object obj = in.readObject();

            in.close();

            System.out.println(fileName + " loaded");

            return obj;

        } catch (Exception e) {

            System.out.println(fileName + " not found");

        }

        return null;
    }
}