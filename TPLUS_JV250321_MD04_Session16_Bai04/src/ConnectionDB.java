import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class ConnectionDB {
    public static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    public static final String URL = "jdbc:mysql://localhost:3306/md4_ss16_db";
    public static final String USER = "root";
    public static final String PASSWORD = "quxTNL43@";

    public static Connection openConnection() {
        Connection conn = null;
        try {
            Class.forName(DRIVER);
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }

    public static void closeConnection(Connection conn, Statement stmt) {
        if (conn != null) {
            try {
                conn.close();
            } catch ( Exception e ) {
                throw new RuntimeException();
            }
        }

        if (stmt != null) {
            try {
                stmt.close();
            } catch ( Exception e ) {
                throw new RuntimeException();
            }
        }
    }
}
