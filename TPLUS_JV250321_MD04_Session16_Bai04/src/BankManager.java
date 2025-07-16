import java.sql.*;

public class BankManager {
    public static void transfer(int fromId, int toId, double balance) {
        Connection conn = null;
        CallableStatement callStmt = null;
        try {
            conn = ConnectionDB.openConnection();
            conn.setAutoCommit(false);
            callStmt = conn.prepareCall("{call transfer_funds(?, ?, ?)}");
            callStmt.setInt(1, fromId);
            callStmt.setInt(2, toId);
            callStmt.setDouble(3, balance);

            if (!isExistId(fromId)) {
                System.out.println("Khong tim thay nguoi gui");
                return;
            }

            if (!isExistId(toId)) {
                System.out.println("Khong tim thay nguoi nhan");
                return;
            }

            double fromBalance = getBalance(fromId);
            if (fromBalance == 0 || fromBalance < balance) {
                System.out.println("Nguoi gui khong du tien");
                return;
            }

            int rows = callStmt.executeUpdate();
            if (rows > 0) {
                conn.commit();
                System.out.println("Chuyen tien thanh cong");
            } else {
                conn.rollback();
            }
        } catch (Exception e) {
            if (conn != null) {
                try {
                    conn.rollback();
                    System.out.println("Co loi trong qua trinh chuyen khoan");
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callStmt);
        }
    }

    public static boolean isExistId(int id) {
        Connection conn = null;
        CallableStatement callStmt = null;
        try {
            conn = ConnectionDB.openConnection();
            callStmt = conn.prepareCall("{call is_exist_id(?, ?)}");
            callStmt.setInt(1, id);
            callStmt.registerOutParameter(2, Types.INTEGER);
            callStmt.execute();
            int result = callStmt.getInt(2);
            return result == 1;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callStmt);
        }
        return false;
    }


    public static double getBalance(int id) {
        Connection conn = null;
        PreparedStatement preStmt = null;
        try {
            conn = ConnectionDB.openConnection();
            String sql = "select balance from accounts where id = ?";
            preStmt = conn.prepareStatement(sql);
            preStmt.setInt(1, id);
            ResultSet rs = preStmt.executeQuery();
            if (rs.next()) {
                return rs.getDouble("balance");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, preStmt);
        }
        return 0.0;
    }
}
