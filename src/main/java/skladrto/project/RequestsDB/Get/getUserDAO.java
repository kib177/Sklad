package skladrto.project.RequestsDB.Get;

import javafx.collections.ObservableList;
import skladrto.project.DAO.modelDAO.UserFunction;
import skladrto.project.List.ListUser;
import skladrto.project.DAO.connectDB.DatabaseConnection;
import skladrto.project.Model.User;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.sql.ResultSet;
import java.sql.SQLException;


public class getUserDAO implements UserFunction {

    @Override
    public ObservableList<User> showListOfUsers() {
        ListUser listUser = new ListUser();
        WeakReference<ListUser> weakReference = new WeakReference<>(listUser);
        try {
            ResultSet resultSet = DatabaseConnection.getStatement().executeQuery("SELECT users.id_users, users.first_name," +
                    " users.last_name,authentication.login, authentication.password,authentication.email,subdivision.subdivision," +
                    " status_user.status " +
                    " FROM users " +
                    " LEFT JOIN subdivision ON (users.subdivision_id =subdivision.id)" +
                    " LEFT JOIN authentication ON (users.authentication_id=authentication.id) " +
                    " LEFT JOIN status_user ON (authentication.status_user_id=status_user.id);");
            while (resultSet.next()) {
                listUser.create(resultSet.getInt("id_users"), resultSet.getString("login"),
                        resultSet.getString("password"), resultSet.getString("first_name"),
                        resultSet.getString("last_name"), resultSet.getString("status"),
                        resultSet.getString("email"), resultSet.getString("subdivision"));
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
        return listUser.getUsersData();
    }

    @Override
    public void deleteUser() {

    }

    public String CheckUsers(String name, String password) {

        try {

            ResultSet rs = DatabaseConnection.getStatement().executeQuery("SELECT * FROM authentication JOIN status_user ON authentication.status_user_id=status_user.id");
            while (rs.next()) {
                if (name.equals(rs.getString("login")) & password.equals(rs.getString("password"))) {

                    return rs.getString("status");
                }
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
        return "";
    }
}
