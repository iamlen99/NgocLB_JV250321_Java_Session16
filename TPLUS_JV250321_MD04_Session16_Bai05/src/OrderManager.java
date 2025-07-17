import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

public class OrderManager {
    public void placeOrder(int customerId, BigDecimal totalAmount, int productId, int quantity) {
        Connection conn = null;
        CallableStatement callStmt = null;
        CallableStatement callStmtUpdate = null;
        try {
            conn = ConnectionDB.openConnection();
            conn.setAutoCommit(false);


            if (!isExistProductId(productId)) {
                System.out.println("San pham khong ton tai");
                return;
            }

            int stock = getProductStock(productId);
            if (stock == -1) {
                System.out.println("Loi khi lay so luong hang ton kho");
                return;
            }

            if (stock < quantity) {
                System.out.println("Khong du hang trong kho");
                return;
            }

            callStmt = conn.prepareCall("{CALL place_order(?,?,?,?)}");
            callStmt.setInt(1, customerId);
            callStmt.setBigDecimal(2, totalAmount);
            callStmt.setInt(3, productId);
            callStmt.setInt(4, quantity);
            int rows = callStmt.executeUpdate();

            if (rows > 0) {
                callStmtUpdate = conn.prepareCall("{CALL update_stock(?, ?)}");
                callStmtUpdate.setInt(1, productId);
                callStmtUpdate.setInt(2, quantity);
                int rowsUpdate = callStmtUpdate.executeUpdate();
                if (rowsUpdate > 0) {
                    conn.commit();
                    System.out.println("Them don hang thanh cong");
                } else {
                    conn.rollback();
                    System.out.println("Cap nhat so luong hang ton kho that bai, rollback du lieu");
                }
            } else {
                System.out.println("Them don hang that bai");
            }
        } catch (Exception e) {
            try {
                if (conn != null) {
                    conn.rollback();
                    System.out.println("Co loi trong qua trinh them don hang, thuc hien roll back");
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callStmt);
            ConnectionDB.closeConnection(null, callStmtUpdate);
        }
    }

    public int getProductStock(int id) {
        Connection conn = null;
        CallableStatement callStmt = null;
        try {
            conn = ConnectionDB.openConnection();
            callStmt = conn.prepareCall("{CALL get_amount(?, ?)}");
            callStmt.setInt(1, id);
            callStmt.registerOutParameter(2, Types.INTEGER);
            callStmt.execute();
            return callStmt.getInt(2);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callStmt);
        }
        return -1;
    }

    public boolean isExistProductId(int id) {
        Connection conn = null;
        CallableStatement callStmt = null;
        try {
            conn = ConnectionDB.openConnection();

            callStmt = conn.prepareCall("{CALL is_exist_product_id(?,?)}");
            callStmt.setInt(1, id);
            callStmt.registerOutParameter(2, Types.INTEGER);
            callStmt.execute();
            int countId = callStmt.getInt(2);
            if (countId == 1) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callStmt);
        }
        return false;
    }
}
