package dao.impl;

import dao.BookTypeDAO;
import entity.BookType;
import ulti.ConnectionDB;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.List;

public class BookTypeDAOImpl implements BookTypeDAO {
    @Override
    public List<BookType> getAll() {
        return List.of();
    }

    @Override
    public boolean isExistName(String typeName) {
        Connection conn = null;
        CallableStatement callStmt = null;
        try {
            conn = ConnectionDB.openConnection();
            callStmt = conn.prepareCall("CALL is_exist_book_type_name(?,?);");
            callStmt.setString(1,typeName);
            callStmt.setInt(2, Types.INTEGER);
            callStmt.execute();
            int countBookType = callStmt.getInt(2);
            if (countBookType == 1) {
                return true;
            }
        } catch (Exception e) {

        } finally {

        }
        return false;
    }

    @Override
    public boolean save(BookType bookType) {

    }

    @Override
    public boolean update(BookType bookType) {

    }

    @Override
    public boolean delete(String typeId) {

    }

    @Override
    public BookType findById(String typeId) {

    }
}
