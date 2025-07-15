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
        Scanner scanner = new Scanner(System.in);

        System.out.println("Nhap tuoi de xoa nhung hoc sinh co do tuoi nho hon:");
        int age = Integer.parseInt(scanner.nextLine());
        main.studentManager.deleteStudent(age);
    }
}
