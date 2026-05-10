package models.academic;

import enums.LessonType;
import models.user.Teacher;

import java.time.LocalDate;

import java.io.Serializable;

public class Lesson implements Serializable{
    private LessonType type;
    private LocalDate date;
    private String name;
    private String room;
    private Teacher instructor;

    public Lesson(LessonType type, LocalDate date, String name, String room, Teacher instructor) {
        this.type = type;
        this.date = date;
        this.name = name;
        this.room = room;
        this.instructor = instructor;
    }

    // Getters & Setters
    public LessonType getType() { return type; }
    public LocalDate getDate() { return date; }
    public String getName() { return name; }
    public String getRoom() { return room; }
    public Teacher getInstructor() { return instructor; }
    public void setInstructor(Teacher instructor) { this.instructor = instructor; }
    public void setRoom(String room) { this.room = room; }

    @Override
    public String toString() {
        return "Lesson{type=" + type + ", date=" + date + ", name='" + name + "', room='" + room + "'}";
    }
}