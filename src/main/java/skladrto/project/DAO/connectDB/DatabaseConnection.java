package skladrto.project.DAO.connectDB;

import java.io.IOException;
import java.sql.*;


public class DatabaseConnection {

    public static Connection getDatabaseConnection() throws SQLException, IOException {
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/sklad", "root", "Sm5290768oke");
    }

    public static Statement getStatement() throws SQLException, IOException  {
           return getDatabaseConnection().createStatement();
    }
}
