package skladRTO.dao.modelDAO;

import javafx.collections.ObservableList;
import skladRTO.api.FX.models.ProductFX;
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
import java.sql.*;


public class UserDAO implements UserFunction {

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
                System.out.println("111" + resultSet);
                authentication = new Authentication(resultSet.getInt("id"), resultSet.getString("login"),
                        resultSet.getString("password"), resultSet.getInt("status_user_id"),
                        resultSet.getString("email"));
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

    /**
     * @param id
     */

    public void delete(int id) {
        String SQL = "DELETE FROM users WHERE id_users =?;";
        try (Connection connection = DatabaseConnection.getDatabaseConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }

    public Boolean pereborID(int id) {
        String SQL = "SELECT * FROM users WHERE id_users =?;";
        try (Connection connection = DatabaseConnection.getDatabaseConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return true;
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void update() {

    }

    public void add(String firstName, String secondName, String login, String email, String password, String status) {
        try (Connection connection = DatabaseConnection.getDatabaseConnection()) {

            connection.setAutoCommit(false);
            System.out.println("Creating savepoint...");
            Savepoint savepointOne = connection.setSavepoint("SavepointOne");
            String userSQL = "INSERT INTO users (first_name, last_name, authentication_id) Values (?, ?, ?);";
            String authenticationSQL = "INSERT INTO authentication (login,password,email,status_user_id)" +
                    "VALUES(?,?,?,?);";
            String statusSQL = "SELECT id FROM sklad.status_user" +
                    " where status= ? ;";
            int num = 0;
            System.out.println("Первый трай");
            try (PreparedStatement preparedStatement = connection.prepareStatement(statusSQL)) {
                preparedStatement.setString(1, status);
                ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    num = resultSet.getInt(1);
                }
                System.out.println("Второй трай");
                try (PreparedStatement preparedStatement2 = connection.prepareStatement(authenticationSQL)) {
                    preparedStatement2.setString(1, login);
                    preparedStatement2.setString(2, password);
                    preparedStatement2.setString(3, email);
                    preparedStatement2.setInt(4, num);
                    preparedStatement2.executeUpdate();

                    System.out.println("Третий трай");
                    int lastInsertId = 0;
                    try (Statement statement = connection.createStatement();) {
                        String str1 = "SELECT LAST_INSERT_ID();";
                        ResultSet resultSet1 = statement.executeQuery(str1);
                        if (resultSet1.next()) {
                            lastInsertId = resultSet1.getInt(1);
                        }
                        System.out.println("Четвертый трай");
                        if (lastInsertId != 0) {
                            try (PreparedStatement preparedStatement1 = connection.prepareStatement(userSQL)) {
                                preparedStatement1.setString(1, firstName);
                                preparedStatement1.setString(2, secondName);
                                preparedStatement1.setInt(3, lastInsertId);
                                preparedStatement1.executeUpdate();
                            } catch (Exception e) {
                                e.printStackTrace();
                                System.out.println("SQLException. Executing rollback to savepoint...");
                                connection.rollback(savepointOne);
                                System.out.println("после отката");
                            }
                        } else {
                            connection.rollback(savepointOne);
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                System.out.println("Перед коммитом");
                connection.commit();
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
