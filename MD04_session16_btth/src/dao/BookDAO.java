package dao;


import entity.Book;

import java.util.List;

public interface BookDAO {
    List<Book> getAll();

    boolean isExistName (String bookName);

    boolean save (Book book);

    boolean update(Book book);

    boolean delete(String bookId);

    List<Book> findByName(String bookName);

    List<Book> listStatisticsBook();

}
