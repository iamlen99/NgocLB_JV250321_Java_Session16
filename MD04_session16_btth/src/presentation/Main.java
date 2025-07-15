package presentation;

import business.BookBusiness;
import business.BookBusinessImpl;
import validate.Validator;

import java.util.Scanner;

public class Main {
    public final BookBusiness bookBusiness;

    public Main() {
        bookBusiness = new BookBusinessImpl();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        do {
            System.out.println("**********BOOK MANAGEMENT***********");
            System.out.println("1. Quan ly loai sach");
            System.out.println("2. Quan ly sach");
            System.out.println("3. Thoat");

            int choice = Validator.isInteger(scanner, "Lua chon cua ban:");
            switch (choice) {
                case 1:
                    BookTypeApplication();
                    break;

                case 2:
                    BookApplication();
                    break;

                case 3:
                    System.exit(0);
                    break;

                default:
                    System.out.println("Vui long chon tu 1-3");
            }
        } while (true);
    }

    public static void BookTypeApplication() {
        Main main = new Main();
        Scanner scanner = new Scanner(System.in);
        boolean isExit = false;
        do {
            System.out.println("**********BOOK TYPE***********");
            System.out.println("1. Danh sach loai sach");
            System.out.println("2. Them moi loai sach");
            System.out.println("3. Cap nhat loai sach");
            System.out.println("4. Xoa loai sach");
            System.out.println("5. Thoat");

            int choice = Validator.isInteger(scanner, "Lua chon cua ban:");
            switch (choice) {
                case 1:

                    break;

                case 2:
                    break;

                case 3:
                    break;

                case 4:
                    break;

                case 5:
                    isExit = true;
                    break;

                default:
                    System.out.println("Vui long chon tu 1-5");
            }
        } while (!isExit);
    }

    public static void BookApplication() {
        Main main = new Main();
        Scanner scanner = new Scanner(System.in);
        boolean isExit = false;
        do {
            System.out.println("**********BOOK***********");
            System.out.println("1. Danh sach sach");
            System.out.println("2. Them moi sach");
            System.out.println("3. Them moi nhieu sach");
            System.out.println("4. Cap nhat sach");
            System.out.println("5. Xoa sach");
            System.out.println("6. Tim kiem sach");
            System.out.println("7. Thong ke so luong sach theo tac gia");
            System.out.println("8. Thoat");

            int choice = Validator.isInteger(scanner, "Lua chon cua ban:");
            switch (choice) {
                case 1:
                    main.bookBusiness.displayBooks();
                    break;

                case 2:
                    break;

                case 3:
                    break;

                case 4:
                    break;

                case 5:
                    break;

                case 6:
                    break;

                case 7:
                    break;

                case 8:
                    isExit = true;
                    break;

                default:
                    System.out.println("Vui long chon tu 1-8");
            }
        } while (!isExit);
    }
}
