package skladrto.project.RequestsDB.Add;

import skladrto.project.connectDB.DatabaseConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class addUser {

//    public static void add(String firstName, String secondName, String login, String email, String password, String status, String department) {
//        DatabaseConnection dbc = new DatabaseConnection();
//        try {
//            PreparedStatement preparedStatement = DatabaseConnection.getDatabaseConnection().prepareStatement("INSERT INTO users (login, password) Values (?, ?);");
//            preparedStatement.setString(1, login);
//            preparedStatement.setString(2, password);
//
//
//            ResultSet rs = dbc.getStatement().executeQuery("SELECT * FROM users"); // как то достать id
//            int id = rs.getInt("id_users");
//            PreparedStatement ps = dbc.getConnection().prepareStatement("INSERT INTO settinguser (first_name, second_name, status, email, department, id_user) Values (?, ?, ?, ?, ?, ?);");
//
//            ps.setString(1, firstName);
//            ps.setString(2, secondName);
//            ps.setString(3, status);
//            ps.setString(4, email);
//            ps.setString(5, department);
//            ps.setInt(6, id);
//
//
//            dbc.close();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//    }
}

