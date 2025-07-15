package dao.impl;

import dao.BookDAO;
import entity.Book;
import ulti.ConnectionDB;


import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

public class BookDAOImpl implements BookDAO {

    @Override
    public List<Book> getAll() {
        Connection conn = null;
        CallableStatement callStmt = null;
        List<Book> books = null;
        try {
            conn = ConnectionDB.openConnection();
            callStmt = conn.prepareCall("{call get_all_book_by_priceASC()}");
            ResultSet rs = callStmt.executeQuery();
            books = new ArrayList<>();
            while (rs.next()) {
                Book book = new Book();
                book.setBookId(rs.getString("book_id"));
                book.setBookName(rs.getString("book_name"));
                book.setBookTitle(rs.getString("book_title"));
                book.setBookPages(rs.getInt("book_pages"));
                book.setBookAuthor(rs.getString("book_author"));
                book.setBookPrice(rs.getFloat("book_price"));
                book.setTypeId(rs.getInt("type_id"));
                book.setBook_status(rs.getBoolean("book_status"));
                books.add(book);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callStmt);
        }
        return books;
    }

    @Override
    public boolean isExistName(String bookName) {
        Connection conn = null;
        CallableStatement callStmt = null;
        try {
            conn = ConnectionDB.openConnection();
            callStmt = conn.prepareCall("{call is_exist_book_name(?,?)}");
            callStmt.setString(1, bookName);
            callStmt.setInt(2, Types.INTEGER);
            callStmt.execute();
            int countBook = callStmt.getInt(2);
            if (countBook > 0) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callStmt);
        }
        return false;
    }

    @Override
    public boolean save(Book book) {
        Connection conn = null;
        CallableStatement callStmt = null;
        try {
            conn = ConnectionDB.openConnection();
            callStmt = conn.prepareCall("{call save_book(?,?,?,?,?,?)}");
            callStmt.setString(1, book.getBookName());
            callStmt.setString(2, book.getBookTitle());
            callStmt.setInt(3, book.getBookPages());
            callStmt.setString(4, book.getBookAuthor());
            callStmt.setFloat(5, book.getBookPrice());
            callStmt.setInt(6, book.getTypeId());
            callStmt.executeUpdate();
            return true;
        } catch (Exception e)  {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callStmt);
        }
        return false;
    }


    @Override
    public boolean update(Book book) {
        Connection conn = null;
        CallableStatement callStmt = null;
        try {
            conn = ConnectionDB.openConnection();
            callStmt = conn.prepareCall("{call update_book(?,?,?,?,?,?,?)}");
            callStmt.setString(1, book.getBookId());
            callStmt.setString(2, book.getBookName());
            callStmt.setString(3, book.getBookTitle());
            callStmt.setInt(4, book.getBookPages());
            callStmt.setString(5, book.getBookAuthor());
            callStmt.setFloat(6, book.getBookPrice());
            callStmt.setInt(7, book.getTypeId());
            callStmt.executeUpdate();
            return true;
        } catch (Exception e)  {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callStmt);
        }
        return false;
    }

    @Override
    public boolean delete(String bookId) {
        Connection conn = null;
        CallableStatement callStmt = null;
        try {
            conn = ConnectionDB.openConnection();
            callStmt = conn.prepareCall("{call delete_book(?)}");
            callStmt.setString(1, bookId);
            callStmt.executeUpdate();
            return true;
        } catch (Exception e)  {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callStmt);
        }
        return false;
    }

    @Override
    public List<Book> findByName(String bookName) {
        Connection conn = null;
        CallableStatement callStmt = null;
        List<Book> books = null;
        try {
            conn = ConnectionDB.openConnection();
            callStmt = conn.prepareCall("{call find_book_by_book_name(?)}");
            callStmt.setString(1, bookName);
            ResultSet rs = callStmt.executeQuery();
            books = new ArrayList<>();
            while (rs.next()) {
                Book book = new Book();
                book.setBookId(rs.getString("book_id"));
                book.setBookName(rs.getString("book_name"));
                book.setBookTitle(rs.getString("book_title"));
                book.setBookPages(rs.getInt("book_pages"));
                book.setBookAuthor(rs.getString("book_author"));
                book.setBookPrice(rs.getFloat("book_price"));
                book.setTypeId(rs.getInt("type_id"));
                book.setBook_status(rs.getBoolean("book_status"));
                books.add(book);
            }
        } catch (Exception e)  {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callStmt);
        }
        return books;
    }

    @Override
    public List<Book> listStatisticsBook() {
        return List.of();
    }

}
