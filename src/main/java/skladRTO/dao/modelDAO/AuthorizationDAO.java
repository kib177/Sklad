package skladRTO.dao.modelDAO;

import skladRTO.api.models.Authorization;
import skladRTO.dao.connectDB.DatabaseConnection;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AuthorizationDAO {
    public Boolean CheckUsers(String name, String password) {
        skladRTO.dao.modelDAO.UserDAO getUserDAO = new UserDAO();
        try (Connection connection = DatabaseConnection.getDatabaseConnection();
             Statement statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery("SELECT authentication.login, " +
                    "authentication.password FROM authentication");
            while (rs.next()) {
                if (name.equalsIgnoreCase(rs.getString("login")) & password.equals(rs.getString("password"))) {
                    System.out.println("Login: " + name + "; Password: " + password + ";");
                    Authorization.setAuth(getUserDAO.getAuthentication(rs.getString("login"), rs.getString("password")));
                    System.out.println("auth: " + Authorization.getAuth());
                    Authorization.setStatusUser(getUserDAO.getStatusUser(Authorization.getAuth().getStatusUserId()));
                    System.out.println("status: " + Authorization.getStatusUser());
                    Authorization.setUser(getUserDAO.getUser(Authorization.getAuth().getId()));
                    System.out.println("user: " + Authorization.getUser());
                    return true;
                }
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
