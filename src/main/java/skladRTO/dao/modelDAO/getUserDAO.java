package skladRTO.dao.modelDAO;

import javafx.collections.ObservableList;
import skladRTO.api.models.Authentication;
import skladRTO.api.models.StatusUser;
import skladRTO.api.models.User;
import skladRTO.api.lists.UserStatusLIst;
import skladRTO.dao.interfaces.UserFunction;
import skladRTO.api.FX.lists.UserListFX;
import skladRTO.dao.connectDB.DatabaseConnection;
import skladRTO.api.FX.models.UserFX;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class getUserDAO implements UserFunction {

    @Override
    public ObservableList<UserFX> showListOfUsers() {
        UserListFX listUser = new UserListFX();
        WeakReference<UserListFX> weakReference = new WeakReference<>(listUser);
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

    public User getUser(int id) {
        User user = null;
        try (Connection connection = DatabaseConnection.getDatabaseConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT * FROM sklad.users WHERE users.authentication_id = ?;")) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                user = new User(resultSet.getInt("id_users"), resultSet.getString("first_name"),
                        resultSet.getString("last_name"), resultSet.getInt("authentication_id"));
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
        return user;
    }

    public StatusUser getStatusUser(int id) {
        StatusUser statusUser = null;
        try (Connection connection = DatabaseConnection.getDatabaseConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT * FROM sklad.status_user WHERE status_user.id = ?;")) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                statusUser = new StatusUser(resultSet.getString("status"));
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
        return statusUser;
    }

    public Authentication getAuthentication(String login, String password) {
        Authentication authentication = null;
        try (Connection connection = DatabaseConnection.getDatabaseConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT * FROM authentication " +
                             " WHERE login = ? AND password = ?")) {
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                System.out.println("111"+resultSet);
                authentication = new Authentication(resultSet.getInt("id"), resultSet.getString("login"),
                        resultSet.getString("password"), resultSet.getInt("status_user_id"), resultSet.getString("email"));
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
        return authentication;
    }



    public ObservableList<StatusUser> getStatus() {
        UserStatusLIst listUserStatus = new UserStatusLIst();
        try {
            ResultSet rs = DatabaseConnection.getStatement().executeQuery("SELECT status_user.status FROM status_user;");

            while (rs.next()) {
                listUserStatus.create(rs.getString("status"));
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
        return listUserStatus.getObservableList();
    }
}
