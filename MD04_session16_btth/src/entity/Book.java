package entity;

import dao.BookDAO;
import dao.impl.BookDAOImpl;
import validate.Validator;

import java.util.Scanner;

public class Book {
    private String bookId;
    private String bookName;
    private String bookTitle;
    private int bookPages;
    private String bookAuthor;
    private float bookPrice;
    private int typeId;
    private boolean book_status;

    public final BookDAO bookDAO;

    public Book() {
        bookDAO = new BookDAOImpl();
    }

    public Book(String bookId, String bookName, String bookTitle, int bookPages,
                String bookAuthor, float bookPrice, int typeId, boolean book_status) {
        this.bookId = bookId;
        this.bookName = bookName;
        this.bookTitle = bookTitle;
        this.bookPages = bookPages;
        this.bookAuthor = bookAuthor;
        this.bookPrice = bookPrice;
        this.typeId = typeId;
        this.book_status = book_status;
        bookDAO = new BookDAOImpl();
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public int getBookPages() {
        return bookPages;
    }

    public void setBookPages(int bookPages) {
        this.bookPages = bookPages;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }

    public float getBookPrice() {
        return bookPrice;
    }

    public void setBookPrice(float bookPrice) {
        this.bookPrice = bookPrice;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public boolean isBook_status() {
        return book_status;
    }

    public void setBook_status(boolean book_status) {
        this.book_status = book_status;
    }

    public void inputData(Scanner scanner) {
        this.bookId = inputBookId(scanner, "Nhap ma sach:");
        this.bookName = inputBookName(scanner, "Nhap ten sach:");
        this.bookTitle = inputBookTitle(scanner, "Nhap tieu de sach:");
        this.bookPages = inputBookPages(scanner, "Nhap so trang sach:");
        this.bookAuthor = inputBookAuthor(scanner, "Nhap tac gia sach:");
        this.bookPrice = inputBookPrice(scanner, "Nhap gia sach:");
        this.typeId = inputTypeId(scanner, "Nhap ma loai sach:");
    }

    public String inputBookId(Scanner scanner, String message) {
        System.out.println(message);
        do {
            String bookId = scanner.nextLine();
            if (!Validator.isEmpty(bookId)) {
                if (bookId.length() == 5) {
                    return bookId;
                }
                System.err.println("Vui long nhap ma sach gom 5 ki tu");
            } else {
                System.err.println("Vui long nhap ma sach:");
            }
        } while (true);
    }

    public String inputBookName(Scanner scanner, String message) {
        System.out.println(message);
        do {
            String bookName = scanner.nextLine();
            if (Validator.isEmpty(bookName)) {
                System.err.println("Ten sach khong duoc de trong, vui long nhap ten sach");
            } else {
                if (!bookDAO.isExistName(bookName)) {
                    return bookName;
                }
                System.err.println("Ten sach da ton tai, vui long nhap lai ten sach khac:");
            }
        } while (true);
    }

    public String inputBookTitle(Scanner scanner, String message) {
        System.out.println(message);
        do {
            String bookTitle = scanner.nextLine();
            if (!Validator.isEmpty(bookTitle)) {
                return bookTitle;
            }
            System.err.println("Vui long nhap tieu de sach");
        } while (true);
    }

    public int inputBookPages(Scanner scanner, String message) {
        System.out.println(message);
        do {
            String input = scanner.nextLine();
            if (Validator.isEmpty(input)) {
                try {
                    int bookPages = Integer.parseInt(input);
                    if (bookPages > 0) {
                        return bookPages;
                    }
                    System.err.println("So trang sach phai lon hon 0, vui long nhap lai:");
                } catch (NumberFormatException e) {
                    System.err.println("Vui long nhap so trang sach la 1 so nguyen");
                }
            } else {
                System.out.println("So trang sach khong duoc de trong, vui long nhap so trang sach:");
            }

        } while (true);
    }

    public String inputBookAuthor(Scanner scanner, String message) {
        System.out.println(message);
        do {
            String bookAuthor = scanner.nextLine();
            if (!Validator.isEmpty(bookAuthor)) {
                return bookAuthor;
            }
            System.err.println("Tac gia sach khong duoc de trong, vui long nhap tac gia sach:");
        } while (true);
    }

    public float inputBookPrice(Scanner scanner, String message) {
        System.out.println(message);
        do {
            String input = scanner.nextLine();
            if (!Validator.isEmpty(input)) {
                try {
                    float price = Float.parseFloat(input);
                    if (price > 0) {
                        return price;
                    }
                    System.err.println("Gia sach la 1 so thuc lon hon 0, vui long nhap lai:");
                } catch (NumberFormatException e) {
                    System.err.println("Vui long nhap gia sach la 1 so thuc:");
                }
            } else {
                System.err.println("Gia sach khong duoc de trong, vui long nhap gia sach:");
            }
        } while (true);
    }

    public int inputTypeId(Scanner scanner, String message) {
        System.out.println(message);
        do {
            String type = scanner.nextLine();
            if (!Validator.isEmpty(type)) {
                return Integer.parseInt(type);
            }
            System.out.println("Ma loai sach khong duoc de trong, vui long nhap tac ma loai sach:");

        } while (true);
    }

    @Override
    public String toString() {
        return String.format("| %s | %s | %s | %d | %s | %.1f | %d | %s",
                bookId, bookName, bookTitle, bookPages, bookAuthor, bookPrice, typeId, book_status ? "Con" : "Khong con");
    }
}
