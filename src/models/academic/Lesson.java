package models.academic;

import enums.LessonType;
import models.user.Teacher;

import java.io.Serializable;

public class Lesson implements Serializable {

    private LessonType type;
    private String name;
    private Teacher instructor;

    public Lesson(
            LessonType type,
            String name,
            Teacher instructor
    ) {
        this.type = type;
        this.name = name;
        this.instructor = instructor;
    }

    public LessonType getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public Teacher getInstructor() {
        return instructor;
    }

    @Override
    public String toString() {
        return "Lesson{" +
                "type=" + type +
                ", name='" + name + '\'' +
                '}';
    }
}