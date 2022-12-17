package skladRTO.dao.connectDB;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


public class DatabaseConnection {
    private static final Logger logger = LogManager.getLogger(DatabaseConnection.class.getName());
      public static Connection getDatabaseConnection() throws SQLException, IOException {
          logger.debug("Подключение к БД");
        return DriverManager.getConnection("jdbc:mysql://192.168.241.133:3306/sklad", "root", "admin");
    }

    public static Statement getStatement() throws SQLException, IOException {
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
