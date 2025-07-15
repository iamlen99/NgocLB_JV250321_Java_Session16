package dao;

import entity.BookType;

import java.util.List;

public interface BookTypeDAO {
    List<BookType> getAll();

    boolean isExistName(String typeName);

    boolean save (BookType bookType);

    boolean update(BookType bookType);

    boolean delete(String typeId);

    BookType findById(String typeId);

}
