package entity;

import dao.BookTypeDAO;
import dao.impl.BookTypeDAOImpl;
import validate.Validator;

import java.util.Scanner;

public class BookType {
    private int typeId;
    private String typeName;
    private String typeDescription;
    private boolean typeStatus;

    public final BookTypeDAO bookTypeDAO;

    public BookType() {
        bookTypeDAO = new BookTypeDAOImpl();
    };

    public BookType(int typeId, String typeName, String typeDescription, boolean typeStatus) {
        this.typeId = typeId;
        this.typeName = typeName;
        this.typeDescription = typeDescription;
        this.typeStatus = typeStatus;
        bookTypeDAO = new BookTypeDAOImpl();
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getTypeDescription() {
        return typeDescription;
    }

    public void setTypeDescription(String typeDescription) {
        this.typeDescription = typeDescription;
    }

    public boolean isTypeStatus() {
        return typeStatus;
    }

    public void setTypeStatus(boolean typeStatus) {
        this.typeStatus = typeStatus;
    }

    public void inputData (Scanner scanner) {
        this.typeName = inputTypeName(scanner, "Nhap ten loai sach:");
        this.typeDescription = inputTypeDescription(scanner, "Nhap mo ta loai sach:");
    }

    public String inputTypeName (Scanner scanner, String message) {
        System.out.println(message);
        do {
            String typeName = scanner.nextLine();
            if (Validator.isEmpty(typeName)) {
                System.err.println("Ten loai sach khong duoc de trong, vui long nhap ten loai sach:");
            } else {
                if (!bookTypeDAO.isExistName(typeName)) {
                    return typeName;
                }
                System.out.println("Ten loai sach da ton tai, vui long nhap lai:");
            }
        } while (true);
    }

    public String inputTypeDescription (Scanner scanner, String message) {
        System.out.println(message);
        do {
            String typeDescription = scanner.nextLine();
            if (!Validator.isEmpty(typeDescription)) {
                return typeDescription;
            }
            System.out.println("Mo ta loai sach khong duoc de trong, vui long nhap mo ta:");
        } while (true);
    }

    @Override
    public String toString() {
        return String.format("Ma loai sach: %d - Ten loai sach: %s - Mo ta loai sach: % - Trang thai: %s"
                , this.typeId, this.typeName, this.typeDescription, this.typeStatus ? "Con" : "Khong con");
    }
}
