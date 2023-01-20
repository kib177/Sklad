package skladRTO.dao.connectDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SimpleConnectionBuilder implements ConnectionBuilder {
    private static final String URL = "jdbc:mysql://192.168.241.133:3306/sklad";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "admin";
    private static final String JDBC_DRIVER = "JDBC_DRIVER";

    public SimpleConnectionBuilder() {
        try {
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }
}
