package manager;

import dao.StudentDAO;
import dao.impl.StudentDAOImpl;
import entity.Student;

import java.util.Scanner;

public class StudentManagerImpl implements StudentManager {
    public final StudentDAO studentDAO;

    public StudentManagerImpl() {
        studentDAO = new StudentDAOImpl();
    }


    @Override
    public void addStudent(Scanner scanner) {
        Student student = new Student();
        student.inputData(scanner);
        if (studentDAO.save(student)) {
            System.out.println("Them sinh vien thanh cong");
        } else {
            System.out.println("Co loi trong qua trinh them sinh vien");
        }
    }
}
