package logic;

import com.google.gson.*;
import com.google.gson.stream.JsonReader;
import javafx.collections.FXCollections;
import logic.Faculty;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Scanner;

public class BuildGson {

    public static PrimitiveFile makeData() {
        /*Faculty math = new Faculty();
        math.name = "Math";

        Professor Alish = new Professor();
        Alish.facultyJSON = 1;
        Alish.degree = "Professor";
        Alish.personalInformation.mail = "kasraAlish@yahoo.com";
        Alish.personalInformation.name = "kasra Alishahi";
        Alish.username = "k";
        Alish.password = University.hash("a");
        Alish.isEducationalAssistant = true;
        //AP course
        Course Ap = new Course();
        Ap.name = "AP"; Ap.units = 4; Ap.section = "bachelor"; Ap.professorJSON = 1; Ap.facultyJSON = 1;
        Alish.coursesJSON.add(1);
        // DF course
        Course differentialEquations = new Course();
        differentialEquations.name = "Differential Equations"; differentialEquations.units = 3; differentialEquations.section = "Master"; differentialEquations.professorJSON = 1; differentialEquations.facultyJSON = 1;
        Alish.coursesJSON.add(2);
        // behradStudent
        Student behrad = new Student();
        behrad.username = "b";
        behrad.password = University.hash("s");
        behrad.degree = 0;
        behrad.personalInformation.fullName = "behrad samimi";
        behrad.personalInformation.mail = "samimi.behrad@yahoo.com";
        behrad.major = "CS";
        behrad.degree = 0;
        behrad.facultyJSON = 1;
        //
        math.students.add(behrad);
        math.courses.add(Ap);
        math.courses.add(differentialEquations);
        math.professors.add(Alish);
        //
        behrad.coursesJSON.add(1);
        Ap.studentsJSON.add(1);
        behrad.coursesJSON.add(2);
        differentialEquations.studentsJSON.add(1);
        PrimitiveFile primitiveFile = new PrimitiveFile();
        primitiveFile.faculties.add(math);
         */
        //faculty
        Faculty math = new Faculty();
        math.name = "math";
        //faculty
        Faculty computerEngineering = new Faculty();
        computerEngineering.name = "computerEngineering";
        //faculty
        Faculty electricalEngineering = new Faculty();
        electricalEngineering.name = "electricalEngineering";
        //faculty
        Faculty mechanicalEngineering = new Faculty();
        mechanicalEngineering.name = "mechanicalEngineering";
        //faculty
        Faculty economy = new Faculty();
        economy.name = "economy";


        //Professor
        Professor aryan = new Professor();
        aryan.username = "aryan"; aryan.password = University.hash("aryan");
        aryan.degree = "Professor"; aryan.facultyJSON = 1; aryan.roomID = 101;
        aryan.personalInformation.name = "artan fazlikhani"; aryan.personalInformation.mail = "aryan@yahoo.com"; aryan.personalInformation.phoneNumber = "09121111111";
        aryan.isDean = true;

        math.deanJSON = 1;
        math.professors.add(aryan);
        //Professor
        Professor elahe = new Professor();
        elahe.username = "elahe"; elahe.password = University.hash("elahe");
        elahe.degree = "Professor"; elahe.facultyJSON = 1; elahe.roomID = 102;
        elahe.personalInformation.name = "elahe zahiri"; elahe.personalInformation.mail = "elahe@yahoo.com"; elahe.personalInformation.phoneNumber = "09121111111";
        elahe.isEducationalAssistant = true;

        math.educationalAssistantJSON = 2;
        math.professors.add(elahe);
        //Professor
        Professor yasna = new Professor();
        yasna.username = "yasna"; yasna.password = University.hash("yasna");
        yasna.degree = "AssociateProfessor"; yasna.facultyJSON = 2; yasna.roomID = 201;
        yasna.personalInformation.name = "yasna aminayi"; yasna.personalInformation.mail = "yasna@yahoo.com"; yasna.personalInformation.phoneNumber = "09121111111";
        yasna.isDean = true;

        computerEngineering.deanJSON = 3;
        computerEngineering.professors.add(yasna);
        //Professor
        Professor mehdi = new Professor();
        mehdi.username = "mehdi"; mehdi.password = University.hash("mehdi");
        mehdi.degree = "Professor"; mehdi.facultyJSON = 2; mehdi.roomID = 202;
        mehdi.personalInformation.name = "mehdi hajibeigi"; mehdi.personalInformation.mail = "mehdi@yahoo.com"; mehdi.personalInformation.phoneNumber = "09121111111";

        computerEngineering.professors.add(mehdi);
        //Professor
        Professor borna = new Professor();
        borna.username = "borna"; borna.password = University.hash("borna");
        borna.degree = "Professor"; borna.facultyJSON = 3; borna.roomID = 301;
        borna.personalInformation.name = "borna khodabande"; borna.personalInformation.mail = "borna@yahoo.com"; borna.personalInformation.phoneNumber = "09121111111";
        borna.isDean = true;

        electricalEngineering.deanJSON = 5;
        electricalEngineering.professors.add(borna);
        //Professor
        Professor ashkan = new Professor();
        ashkan.username = "ashkan"; ashkan.password = University.hash("ashkan");
        ashkan.degree = "AssistantProfessor"; ashkan.facultyJSON = 3; ashkan.roomID = 302;
        ashkan.personalInformation.name = "ashkan kooshki"; ashkan.personalInformation.mail = "ashkan@yahoo.com"; ashkan.personalInformation.phoneNumber = "09121111111";
        ashkan.isEducationalAssistant = true;

        electricalEngineering.educationalAssistantJSON = 6;
        electricalEngineering.professors.add(ashkan);
        //Professor
        Professor taha = new Professor();
        taha.username = "taha"; taha.password = University.hash("taha");
        taha.degree = "AssociateProfessor"; taha.facultyJSON = 4; taha.roomID = 401;
        taha.personalInformation.name = "taha jalali"; taha.personalInformation.mail = "taha@yahoo.com"; taha.personalInformation.phoneNumber = "09121111111";
        taha.isDean = true;

        mechanicalEngineering.deanJSON = 7;
        mechanicalEngineering.professors.add(taha);
        //Professor
        Professor abolfazl = new Professor();
        abolfazl.username = "abolfazl"; abolfazl.password = University.hash("abolfazl");
        abolfazl.degree = "Professor"; abolfazl.facultyJSON = 5; abolfazl.roomID = 501;
        abolfazl.personalInformation.name = "abolfazl asadi"; abolfazl.personalInformation.mail = "abolfazl@yahoo.com"; yasna.personalInformation.phoneNumber = "09121111111";
        abolfazl.isDean = true;

        computerEngineering.deanJSON = 8;
        computerEngineering.professors.add(abolfazl);

        //Student
        Student behrad = new Student();
        behrad.username = "behrad"; behrad.password = University.hash("behrad");
        behrad.degree = 0; behrad.supervisorJSON = 2; behrad.facultyJSON = 1;
        behrad.personalInformation.fullName = "behrad samimi"; behrad.personalInformation.mail = "samimi.behrad@yahoo.com"; behrad.personalInformation.phoneNumber = "09122302160";
        behrad.personalInformation.entranceYear = "1400";

        math.students.add(behrad);
        //Student
        Student mahsa = new Student();
        mahsa.username = "mahsa"; mahsa.password = University.hash("mahsa");
        mahsa.degree = 0; mahsa.supervisorJSON = 3; mahsa.facultyJSON = 2;
        mahsa.personalInformation.fullName = "mahsa sajadpor"; mahsa.personalInformation.mail = "mahsa@yahoo.com"; mahsa.personalInformation.phoneNumber = "09122222222";
        mahsa.personalInformation.entranceYear = "1400";

        computerEngineering.students.add(mahsa);
        //Student
        Student sina = new Student();
        sina.username = "sina"; sina.password = University.hash("sina");
        sina.degree = 1; sina.supervisorJSON = 6; sina.facultyJSON = 3;
        sina.personalInformation.fullName = "sina A"; sina.personalInformation.mail = "sina.behrad@yahoo.com"; sina.personalInformation.phoneNumber = "09122302160";
        behrad.personalInformation.entranceYear = "1400";

        electricalEngineering.students.add(sina);
        //Student
        Student kiana = new Student();
        kiana.username = "kiana"; kiana.password = University.hash("kiana");
        kiana.degree = 1; kiana.supervisorJSON = 5; kiana.facultyJSON = 3;
        kiana.personalInformation.fullName = "kiana kazemi"; kiana.personalInformation.mail = "kiana.behrad@yahoo.com"; kiana.personalInformation.phoneNumber = "09122302160";
        kiana.personalInformation.entranceYear = "1400";

        electricalEngineering.students.add(kiana);
        //Student
        Student farbod = new Student();
        farbod.username = "farbod"; farbod.password = University.hash("farbod");
        farbod.degree = 2; farbod.supervisorJSON = 7; farbod.facultyJSON = 4;
        farbod.personalInformation.fullName = "farbod pakan"; farbod.personalInformation.mail = "farbod@yahoo.com"; farbod.personalInformation.phoneNumber = "09122302160";
        farbod.personalInformation.entranceYear = "1400";

        mechanicalEngineering.students.add(farbod);
        //Student
        Student erfan = new Student();
        erfan.username = "erfan"; erfan.password = University.hash("erfan");
        erfan.degree = 2; erfan.supervisorJSON = 8; erfan.facultyJSON = 5;
        erfan.personalInformation.fullName = "erfan tamimi"; erfan.personalInformation.mail = "erfan@yahoo.com"; erfan.personalInformation.phoneNumber = "09122302160";
        erfan.personalInformation.entranceYear = "1400";

        economy.students.add(erfan);


        //Course
        Course AP = new Course();
        AP.name = "AP"; AP.units = 4; AP.exam.date = "1400/04/02"; AP.exam.time = "09:00"; AP.section = "bachelor";
        AP.firstSession.date = "Saturday"; AP.firstSession.time = "08:00";
        AP.secondSession.date = "Monday"; AP.secondSession.time = "08:00";
        AP.professorJSON = 1; AP.facultyJSON = 1;
        aryan.coursesJSON.add(1); math.courses.add(AP);
        AP.studentsJSON.add(1); behrad.coursesJSON.add(1);
        AP.studentsJSON.add(2); mahsa.coursesJSON.add(1);
        //Course
        Course OS = new Course();
        OS.name = "OS"; OS.units = 3; OS.exam.date = "1400/04/05"; AP.exam.time = "09:00"; OS.section = "master";
        OS.firstSession.date = "Saturday"; OS.firstSession.time = "11:00";
        OS.secondSession.date = "Monday"; OS.secondSession.time = "11:00";
        OS.professorJSON = 2; OS.facultyJSON = 1;
        elahe.coursesJSON.add(2); math.courses.add(OS);
        OS.studentsJSON.add(1); behrad.coursesJSON.add(2);
        OS.studentsJSON.add(3); sina.coursesJSON.add(2);
        //Course
        Course DS = new Course();
        DS.name = "DS"; DS.units = 3; DS.exam.date = "1400/05/07"; DS.exam.time = "08:00"; DS.section = "phd";
        DS.firstSession.date = "Sunday"; DS.firstSession.time = "10:00";
        DS.secondSession.date = "Tuesday"; DS.secondSession.time = "10:00";
        DS.professorJSON = 4; DS.facultyJSON = 2;
        mehdi.coursesJSON.add(3); computerEngineering.courses.add(DS);
        DS.studentsJSON.add(1); behrad.coursesJSON.add(3);
        DS.studentsJSON.add(4); kiana.coursesJSON.add(3);
        //Course
        Course logicCircuits = new Course();
        logicCircuits.name = "logic circuits"; logicCircuits.units = 2; logicCircuits.exam.date = "1400/03/02"; logicCircuits.exam.time = "09:00"; logicCircuits.section = "bachelor";
        logicCircuits.firstSession.date = "Saturday"; logicCircuits.firstSession.time = "10:00";
        logicCircuits.secondSession.date = "Monday"; logicCircuits.secondSession.time = "10:00";
        logicCircuits.professorJSON = 3; logicCircuits.facultyJSON = 2;
        yasna.coursesJSON.add(4); computerEngineering.courses.add(logicCircuits);
        logicCircuits.studentsJSON.add(2); mahsa.coursesJSON.add(4);
        logicCircuits.studentsJSON.add(3); sina.coursesJSON.add(4);
        //Course
        Course electronic = new Course();
        electronic.name = "electronic"; electronic.units = 3; electronic.exam.date = "1400/04/11"; electronic.exam.time = "09:00"; electronic.section = "bachelor";
        electronic.firstSession.date = "thursday"; electronic.firstSession.time = "10:00";
        electronic.secondSession.date = "-"; electronic.secondSession.time = "-";
        electronic.professorJSON = 5; electronic.facultyJSON = 3;
        borna.coursesJSON.add(5); electricalEngineering.courses.add(electronic);
        electronic.studentsJSON.add(3); sina.coursesJSON.add(5);
        electronic.studentsJSON.add(4); kiana.coursesJSON.add(5);
        //Course
        Course magnetism = new Course();
        magnetism.name = "magnetism"; magnetism.units = 3; magnetism.exam.date = "1400/04/15"; magnetism.exam.time = "09:00"; magnetism.section = "master";
        magnetism.firstSession.date = "thursday"; magnetism.firstSession.time = "10:00";
        magnetism.secondSession.date = "wednesday"; magnetism.secondSession.time = "10:00";
        magnetism.professorJSON = 6; magnetism.facultyJSON = 3;
        ashkan.coursesJSON.add(6); electricalEngineering.courses.add(magnetism);
        magnetism.studentsJSON.add(3); sina.coursesJSON.add(6);
        magnetism.studentsJSON.add(5); farbod.coursesJSON.add(6);
        //Course
        Course fizik3 = new Course();
        fizik3.name = "fizik3"; fizik3.units = 3; fizik3.exam.date = "1400/04/17"; fizik3.exam.time = "09:00"; fizik3.section = "bachelor";
        fizik3.firstSession.date = "thursday"; fizik3.firstSession.time = "14:00";
        fizik3.secondSession.date = "wednesday"; fizik3.secondSession.time = "14:00";
        fizik3.professorJSON = 7; fizik3.facultyJSON = 4;
        taha.coursesJSON.add(7); mechanicalEngineering.courses.add(fizik3);
        fizik3.studentsJSON.add(6); erfan.coursesJSON.add(7);
        fizik3.studentsJSON.add(5); farbod.coursesJSON.add(7);
        //Course
        Course fluid = new Course();
        fluid.name = "fluid"; fluid.units = 3; fluid.exam.date = "1400/04/17"; fluid.exam.time = "12:00"; fluid.section = "phd";
        fluid.firstSession.date = "thursday"; fluid.firstSession.time = "18:00";
        fluid.secondSession.date = "wednesday"; fluid.secondSession.time = "18:00";
        fluid.professorJSON = 7; fluid.facultyJSON = 4;
        taha.coursesJSON.add(8); mechanicalEngineering.courses.add(fluid);
        fluid.studentsJSON.add(6); erfan.coursesJSON.add(8);
        fluid.studentsJSON.add(5); farbod.coursesJSON.add(8);
        //Course
        Course technicalَAnalysis = new Course();
        technicalَAnalysis.name = "technicalَAnalysis"; technicalَAnalysis.units = 2; technicalَAnalysis.exam.date = "1400/04/17"; technicalَAnalysis.exam.time = "12:00"; technicalَAnalysis.section = "phd";
        technicalَAnalysis.firstSession.date = "monday"; technicalَAnalysis.firstSession.time = "18:00";
        technicalَAnalysis.secondSession.date = "tuesday"; technicalَAnalysis.secondSession.time = "18:00";
        technicalَAnalysis.professorJSON = 8; technicalَAnalysis.facultyJSON = 5;
        abolfazl.coursesJSON.add(9); economy.courses.add(technicalَAnalysis);
        technicalَAnalysis.studentsJSON.add(6); erfan.coursesJSON.add(9);
        technicalَAnalysis.studentsJSON.add(1); behrad.coursesJSON.add(9);
        //Course
        Course basicEconomy = new Course();
        basicEconomy.name = "basicEconomy"; basicEconomy.units = 3; basicEconomy.exam.date = "1400/03/17"; basicEconomy.exam.time = "08:00"; basicEconomy.section = "bachelor";
        basicEconomy.firstSession.date = "saturday"; basicEconomy.firstSession.time = "18:00";
        basicEconomy.secondSession.date = "sunday"; basicEconomy.secondSession.time = "18:00";
        basicEconomy.professorJSON = 8; basicEconomy.facultyJSON = 5;
        abolfazl.coursesJSON.add(10); economy.courses.add(basicEconomy);
        basicEconomy.studentsJSON.add(6); erfan.coursesJSON.add(10);//change after
        basicEconomy.studentsJSON.add(4); kiana.coursesJSON.add(10);//change after

        PrimitiveFile primitiveFile = new PrimitiveFile();
        primitiveFile.faculties.add(math);
        primitiveFile.faculties.add(computerEngineering);
        primitiveFile.faculties.add(electricalEngineering);
        primitiveFile.faculties.add(mechanicalEngineering);
        primitiveFile.faculties.add(economy);
        return primitiveFile;
    }
    public static void buildGson() {
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(LocalDateTime.class, (JsonSerializer<LocalDateTime>) (localDateTime, type, jsonSerializationContext) -> {
            if (localDateTime == null)
                return JsonNull.INSTANCE;
            return new JsonPrimitive(localDateTime.toString());
        });

        builder.registerTypeAdapter(LocalDateTime.class, (JsonDeserializer<LocalDateTime>) (jsonElement, type, jsonDeserializationContext) -> {
            if (jsonElement == JsonNull.INSTANCE)
                return null;
            return LocalDateTime.parse(jsonElement.getAsString());
        });

        builder.registerTypeAdapter(LocalDate.class, (JsonSerializer<LocalDate>) (localDate, type, jsonSerializationContext) -> {
            if (localDate == null)
                return JsonNull.INSTANCE;
            return new JsonPrimitive(localDate.toString());
        });

        builder.registerTypeAdapter(LocalDate.class, (JsonDeserializer<LocalDate>) (jsonElement, type, jsonDeserializationContext) -> {
            if (jsonElement == JsonNull.INSTANCE)
                return null;
            return LocalDate.parse(jsonElement.getAsString());
        });

        builder.registerTypeAdapter(LocalTime.class, (JsonSerializer<LocalTime>) (localTime, type, jsonSerializationContext) -> {
            if (localTime == null)
                return JsonNull.INSTANCE;
            return new JsonPrimitive(localTime.toString());
        });
        builder.registerTypeAdapter(LocalTime.class, (JsonDeserializer<LocalTime>) (jsonElement, type, jsonDeserializationContext) -> {
            if (jsonElement == JsonNull.INSTANCE)
                return null;
            return LocalTime.parse(jsonElement.getAsString());
        });
        builder.setPrettyPrinting();
        Gson gson = builder.create();

        PrimitiveFile primitiveFileWrite = makeData();
        String jsonWrite = gson.toJson(primitiveFileWrite, PrimitiveFile.class);
        try {
            FileWriter writer = new FileWriter("JSON.txt");
            writer.write(jsonWrite);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


        StringBuilder jsonRead = new StringBuilder();
        try {
            File file = new File("JSON.txt");
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                jsonRead.append(scanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        PrimitiveFile primitiveFileRead = gson.fromJson(jsonRead.toString(), PrimitiveFile.class);
        University.load(primitiveFileRead);
    }

}
