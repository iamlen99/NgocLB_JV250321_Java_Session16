import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

public class EmployeeProjectManager {
    public void assignEmployeeToProject(int employeeId, int projectId) {
        Connection conn = null;
        CallableStatement callStmt = null;

        if (!isExistId(employeeId, "{call is_exist_employee(?,?)}")) {
            System.out.println("Nhan vien khong ton tai");
            return;
        }

        if (!isExistId(projectId, "{call is_exist_project(?,?)}")) {
            System.out.println("Du an khong ton tai");
            return;
        }

        try {
            conn = ConnectionDB.openConnection();
            conn.setAutoCommit(false);
            callStmt = conn.prepareCall("{call assign_employee_to_project(?,?)}");
            callStmt.setInt(1, employeeId);
            callStmt.setInt(2, projectId);

            int rows = callStmt.executeUpdate();
            if (rows > 0) {
                conn.commit();
                System.out.println("Gan nhan vien vao du an thanh cong");
            } else {
                conn.rollback();
                System.out.println("Gan nhan vien vao du an that bai, rollback");
            }
        } catch (Exception e) {
            if (conn != null) {
                try {
                    conn.rollback();
                    System.out.println("Co loi trong qua trinh gan nhan vien vao du an!");
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            e.printStackTrace();
        } finally {
            ConnectionDB.closeStatement(callStmt);
            ConnectionDB.closeConnection(conn);
        }
    }

    public boolean isExistId(int id, String sql) {
        Connection conn = null;
        CallableStatement callStmt = null;
        try {
            conn = ConnectionDB.openConnection();
            callStmt = conn.prepareCall(sql);
            callStmt.setInt(1, id);
            callStmt.registerOutParameter(2, Types.INTEGER);
            callStmt.execute();
            return callStmt.getInt(2) > 0;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeStatement(callStmt);
            ConnectionDB.closeConnection(conn);
        }
        return false;
    }
}
