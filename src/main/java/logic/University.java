package logic;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

public class University {
    public static ArrayList<Student> students = new ArrayList<>();
    public static ArrayList<Professor> professors = new ArrayList<>();
    public static ArrayList<Faculty> faculties = new ArrayList<>();
    public static ArrayList<Course> courses = new ArrayList<>();
    public static Boolean correctLogin(Student student, String username, String password) {
        if (student.username.equals(username) && student.password.equals(hash(password))) return true;
        else return false;
    }
    public static Boolean correctLogin(Professor professor, String username, String password) {
        if (professor.username.equals(username) && professor.password.equals(hash(password))) return true;
        else return false;
    }
    public static String hash(String input){
        StringBuilder hash = new StringBuilder();

        try {
            MessageDigest sha = MessageDigest.getInstance("SHA-1");
            byte[] hashedBytes = sha.digest(input.getBytes());
            char[] digits = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                    'a', 'b', 'c', 'd', 'e', 'f' };
            for (int idx = 0; idx < hashedBytes.length; ++idx) {
                byte b = hashedBytes[idx];
                hash.append(digits[(b & 0xf0) >> 4]);
                hash.append(digits[b & 0x0f]);
            }
        } catch (NoSuchAlgorithmException ignored) {

        }

        return hash.toString();
    }

    public static void load(PrimitiveFile primitiveFile) {
        for (Faculty faculty : primitiveFile.faculties) {
            faculties.add(faculty);
            courses.addAll(faculty.courses);
            professors.addAll(faculty.professors);
            students.addAll(faculty.students);
        }
        for (Faculty faculty : faculties) loadFaculty(faculty);
        for (Student student : students) loadStudent(student);
        for (Professor professor : professors) loadProfessor(professor);
        for (Course course : courses) loadCourse(course);
    }

    public static void loadStudent(Student student) {
        for (Integer rn : student.coursesJSON) {
            student.courses.add(getCourse(rn));
        }
        student.faculty = getFaculty(student.facultyJSON);
        student.supervisor = getProfessor(student.supervisorJSON);
        student.minorRequest = new MinorRequest(student);
    }
    public static void loadProfessor(Professor professor) {
        for (Integer rn : professor.coursesJSON) {
            professor.courses.add(getCourse(rn));
        }
        professor.faculty = getFaculty(professor.facultyJSON);
    }
    public static void loadCourse(Course course) {
        for (Integer rn : course.studentsJSON) {
            course.students.add(getStudent(rn));
        }
        course.professor = getProfessor(course.professorJSON);
        course.faculty = getFaculty(course.facultyJSON);
        for (Student student : course.students) {
            Mark mark = new Mark();
            mark.student = student;
            mark.course = course;
            course.marks.add(mark);
        }
    }
    public static void loadFaculty(Faculty faculty) {
        faculty.dean = getProfessor(faculty.deanJSON);
        faculty.educationalAssistant = getProfessor(faculty.educationalAssistantJSON);
        return;
    }

    public static Student getStudent(int id) {
        for (Student student : students) {
            if (student.studentNumber == id)
                return student;
        }
        return null;
    }
    public static Professor getProfessor(int id) {
        for (Professor professor : professors) {
            if (professor.professorNumber == id) {
                return professor;
            }
        }
        return null;
    }
    public static Course getCourse(int id) {
        for (Course course : courses) {
            if (course.number == id)
                return course;
        }
        return null;
    }
    public static Faculty getFaculty(int id) {
        for (Faculty faculty : faculties) {
            if (faculty.number == id)
                return faculty;
        }
        return null;
    }
}

