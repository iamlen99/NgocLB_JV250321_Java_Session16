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

    @Override
    public void updateStudent(Student student) {
        if (studentDAO.update(student)) {
            System.out.println("Cap nhat thong tin sinh vien thanh cong");
        } else  {
            System.out.println("Co loi trong qua trinh sua thong tin sinh vien");
        }
    }

    @Override
    public void deleteStudent(int age) {
       studentDAO.delete(age);
    }
}
