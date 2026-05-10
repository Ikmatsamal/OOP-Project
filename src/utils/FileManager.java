package utils;

import patterns.singleton.DatabaseSingleton;

import java.io.*;

public class FileManager {

    public static void save(DatabaseSingleton db) {

        try {

            ObjectOutputStream out =
                    new ObjectOutputStream(
                            new FileOutputStream("database.ser")
                    );

            out.writeObject(db);

            out.close();

            System.out.println("Database saved");

        } catch (Exception e) {

            e.printStackTrace();

        }

    }

    public static DatabaseSingleton load() {

        try {

            ObjectInputStream in =
                    new ObjectInputStream(
                            new FileInputStream("database.ser")
                    );

            DatabaseSingleton db =
                    (DatabaseSingleton) in.readObject();

            in.close();

            System.out.println("Database loaded");

            return db;

        } catch (Exception e) {

            e.printStackTrace();

        }

        return null;
    }

}