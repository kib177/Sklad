package skladrto.project.RequestsDB.Get;

import javafx.collections.ObservableList;
import skladrto.project.List.ListUser;
import skladrto.project.DAO.connectDB.DatabaseConnection;
import skladrto.project.Model.User;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.sql.ResultSet;
import java.sql.SQLException;


public class UserDAO {

    public ObservableList<User> showListOfUsers() {
        ListUser listUser = new ListUser();
        WeakReference<ListUser> weakReference = new WeakReference<>(listUser);

        try {
            ResultSet resultSet = DatabaseConnection.getStatement().executeQuery("SELECT users.id_users, users.first_name, users.last_name," +
                    " authentication.login, authentication.password,authentication.email,subdivision.subdivision," +
                    " status_user.status " +
                    " FROM sklad.users,sklad.subdivision,sklad.status_user,sklad.authentication" +
                    " WHERE users.authentication_id = authentication.id and" +
                    " users.subdivision_id = subdivision.id and " +
                    " authentication.status_user_id = status_user.id" +
                    ";");
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

//    public Boolean CheckUsers(String name, String password) { // проверка пользователя
//
//        try {
//
//            ResultSet rs = DatabaseConnection.getStatement().executeQuery("SELECT * FROM authentication ");
//            while (rs.next()) {
//                if (name.equals(rs.getString("login")) & password.equals(rs.getString("password"))) {
//                    return true;
//                }
//            }
//        } catch (SQLException | IOException e) {
//            e.printStackTrace();
//        }
//        return false;
//    }
}
