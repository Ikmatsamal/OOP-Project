package utils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Logger {

    private static final List<String> logs =
            new ArrayList<>();

    public static void info(String message) {

        String log =
                "[INFO] " + LocalDateTime.now()
                        + " " + message;

        logs.add(log);

        System.out.println(log);
    }

    public static void warn(String message) {

        String log =
                "[WARNING] " + LocalDateTime.now()
                        + " " + message;

        logs.add(log);

        System.out.println(log);
    }

    public static List<String> getLogs() {
        return logs;
    }

}