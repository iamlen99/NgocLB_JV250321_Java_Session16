package manager;

import entity.Student;

import java.util.Scanner;

public interface StudentManager {
    void addStudent(Scanner scanner);

    void updateStudent(Student student);
}
