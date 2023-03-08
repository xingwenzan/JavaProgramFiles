package IntroductoryExperiment.StudentManagment;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Manager {
    private List<Student> database = new ArrayList<>();

    public void add(String id, int score) {
        Student student = new Student();
        student.id = id;
        student.score = score;
        database.add(student);
    }

    public void sort() {
        database.sort(Comparator.comparing(Student::getScore));
    }

    public List<Student> getDatabase() {
        return database;
    }
}
