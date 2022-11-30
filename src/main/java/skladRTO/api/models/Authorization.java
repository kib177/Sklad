package skladRTO.api.models;

import skladRTO.dao.connectDB.DatabaseConnection;
import skladRTO.dao.modelDAO.getUserDAO;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Authorization {
   static Authentication auth ;
   static StatusUser statusUser;
   static User user ;

    public Authorization() {
    }

    public Boolean CheckUsers(String name, String password) {
        getUserDAO getUserDAO = new getUserDAO();
        try (Connection connection = DatabaseConnection.getDatabaseConnection();
             Statement statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery("SELECT authentication.login, " +
                    "authentication.password FROM authentication");
            while (rs.next()) {
                if (name.equals(rs.getString("login")) & password.equals(rs.getString("password"))) {
                    System.out.println( name + password);
                    auth = getUserDAO.getAuthentication(rs.getString("login"), rs.getString("password"));
                    System.out.println("auth"+auth);
                    statusUser = getUserDAO.getStatusUser(auth.getStatusUserId());
                    System.out.println("status"+statusUser);
                    user = getUserDAO.getUser(auth.getId());
                    System.out.println("user"+ user);
                    return true;
                }
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static Authentication getAuth() {
        return auth;
    }

    public static void setAuth(Authentication auth) {
        Authorization.auth = auth;
    }

    public static StatusUser getStatusUser() {
        return statusUser;
    }

    public static void setStatusUser(StatusUser statusUser) {
        Authorization.statusUser = statusUser;
    }

    public static User getUser() {
        return user;
    }

    public static void setUser(User user) {
        Authorization.user = user;
    }
}
