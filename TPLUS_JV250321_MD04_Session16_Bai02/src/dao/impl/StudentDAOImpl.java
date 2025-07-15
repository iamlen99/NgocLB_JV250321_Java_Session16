package dao.impl;

import dao.StudentDAO;
import entity.Student;
import ulti.ConnectionDB;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class StudentDAOImpl implements StudentDAO {

    @Override
    public boolean save(Student student) {
        Connection conn = null;
        CallableStatement callStmt = null;
        try {
            conn = ConnectionDB.openConnection();
            conn.setAutoCommit(false);
            callStmt = conn.prepareCall("{call add_students(?, ?)}");
            callStmt.setString(1, student.getName());
            callStmt.setInt(2, student.getAge());
            int rows = callStmt.executeUpdate();
            if (rows > 0) {
                conn.commit();
                return true;
            }
            conn.rollback();
        } catch (Exception e) {
            try {
                if (conn != null) {
                    conn.rollback();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callStmt);
        }
        return false;
    }

    @Override
    public boolean update(Student student) {
        Connection conn = null;
        PreparedStatement preStmt = null;
        try {
            conn = ConnectionDB.openConnection();
            conn.setAutoCommit(false);
            String sql = "update students set name = ?, age = ? where id = ?";
            preStmt = conn.prepareStatement(sql);
            preStmt.setString(1, student.getName());
            preStmt.setInt(2, student.getAge());
            preStmt.setInt(3, student.getId());

            int rows = preStmt.executeUpdate();
            if (rows > 0) {
                conn.commit();
                return true;
            }
            conn.rollback();
        } catch (Exception e) {
            try {
                if (conn != null) {
                    conn.rollback();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, preStmt);
        }
        return false;
    }

}
