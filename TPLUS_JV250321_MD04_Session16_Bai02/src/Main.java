import entity.Student;
import manager.StudentManager;
import manager.StudentManagerImpl;

import java.util.Scanner;

public class Main {

    public final StudentManager studentManager;

    public Main() {
        studentManager = new StudentManagerImpl();
    }
    public static void main(String[] args) {
        Main main = new Main();
        Student student = new Student(1, "Nguyen Van A", 21);
        main.studentManager.updateStudent(student);
    }
}
