package validate;

import java.util.Scanner;

public class Validator {
    public static boolean isEmpty (String data) {
        return data == null || data.trim().isEmpty();
    }

    public static int isInteger (Scanner scanner, String message) {
        System.out.println(message);
        do {
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Du lieu ban nhap khong phai la so nguyen, vui long nhap lai");
            }
        } while (true);
    }
}
