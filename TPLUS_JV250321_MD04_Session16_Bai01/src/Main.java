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

        main.studentManager.addStudent(scanner);
    }
}
