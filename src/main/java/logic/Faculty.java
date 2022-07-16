package logic;

import java.util.ArrayList;

public class Faculty {
    public ArrayList<Course> courses = new ArrayList<Course>();
    public ArrayList<Professor> professors = new ArrayList<Professor>();
    public ArrayList<Student> students = new ArrayList<Student>();

    //TODO -> okinLoad
    public transient Professor dean;
    public int deanJSON;

    //TODO -> okinLoad
    public transient Professor educationalAssistant;
    public int educationalAssistantJSON;

    public String name = "";
    public int number;
    public static int ID = 1;
    public Faculty() {
        this.number = ID++;
    }
}
