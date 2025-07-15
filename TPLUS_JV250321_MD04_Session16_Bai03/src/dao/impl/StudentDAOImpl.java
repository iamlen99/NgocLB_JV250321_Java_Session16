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

    @Override
    public boolean delete(int age) {
        Connection conn = null;
        CallableStatement callStmt = null;
        try {
            conn = ConnectionDB.openConnection();
            conn.setAutoCommit(false);
            callStmt = conn.prepareCall("{call delete_students_by_age(?)}");
            callStmt.setInt(1, age);
            int rows = callStmt.executeUpdate();
            if (rows > 0) {
                conn.commit();
                System.out.printf("Xoa thanh cong %d hoc sinh co do tuoi nho hon : %d\n", rows, age);
                return true;
            }
            System.out.printf("Khong tim thay hoc sinh nao co do tuoi nho hon : %d\n", age);
            conn.rollback();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callStmt);
        }
        return false;
    }

}
