package skladRTO.api.models;

import skladRTO.dao.connectDB.DatabaseConnection;
import skladRTO.dao.modelDAO.UserDAO;

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
