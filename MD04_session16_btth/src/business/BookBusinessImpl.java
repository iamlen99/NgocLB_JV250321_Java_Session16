package business;

import dao.BookDAO;
import dao.impl.BookDAOImpl;
import entity.Book;

import java.util.List;

public class BookBusinessImpl implements BookBusiness {

    public final BookDAO bookDAO;

    public BookBusinessImpl() {
        bookDAO = new BookDAOImpl();
    }

    @Override
    public void displayBooks() {
        List<Book> bookList = bookDAO.getAll();
        bookList.forEach(System.out::println);
    }
}
