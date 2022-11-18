package skladrto.project.DAO.connectDB;

import java.io.IOException;
import java.sql.*;


public class DatabaseConnection {
      public static Connection getDatabaseConnection() throws SQLException, IOException {
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/sklad", "root", "admin");
    }

    public static Statement getStatement() throws SQLException, IOException  {
           return getDatabaseConnection().createStatement();
    }

    public static void close(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void close(Statement statement) {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
