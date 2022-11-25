package skladRTO.dao.requestsDB.Get;

import javafx.collections.ObservableList;
import skladRTO.dao.modelDAO.UserFunction;
import skladRTO.api.models.lists.ListUser;
import skladRTO.dao.connectDB.DatabaseConnection;
import skladRTO.api.models.lists.ListUserStatus;
import skladRTO.api.models.FX.UserFX;
import skladRTO.api.models.StatusUser;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.sql.ResultSet;
import java.sql.SQLException;


public class getUserDAO implements UserFunction {

    @Override
    public ObservableList<UserFX> showListOfUsers() {
        ListUser listUser = new ListUser();
        WeakReference<ListUser> weakReference = new WeakReference<>(listUser);
        try {
            ResultSet resultSet = DatabaseConnection.getStatement().executeQuery("SELECT users.id_users," +
                    " users.first_name, users.last_name, authentication.login, authentication.password," +
                    " authentication.email, status_user.status" +
                    " FROM users " +
                    " LEFT JOIN authentication ON (users.authentication_id=authentication.id) " +
                    " LEFT JOIN status_user ON (authentication.status_user_id=status_user.id);");
            while (resultSet.next()) {
                listUser.create(resultSet.getInt("id_users"), resultSet.getString("login"),
                        resultSet.getString("password"), resultSet.getString("first_name"),
                        resultSet.getString("last_name"), resultSet.getString("status"),
                        resultSet.getString("email"));
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
        return listUser.getUsersData();
    }

    @Override
    public void deleteUser() {

    }

    public Boolean CheckUsers(String name, String password) {
        try {
            ResultSet rs = DatabaseConnection.getStatement().executeQuery("SELECT authentication.login, " +
                    "authentication.password FROM authentication");

            while (rs.next()) {
                if (name.equals(rs.getString("login")) & password.equals(rs.getString("password"))) {
                    return true;
                }
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public ObservableList<StatusUser> getStatus() {
        ListUserStatus listUserStatus = new ListUserStatus();
        try {
            ResultSet rs = DatabaseConnection.getStatement().executeQuery("SELECT status_user.status FROM status_user");

            while (rs.next()) {
                listUserStatus.create(rs.getString("status"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } return listUserStatus.getObservableList();
    }
}
