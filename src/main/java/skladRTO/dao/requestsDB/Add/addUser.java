package skladRTO.dao.requestsDB.Add;

import skladRTO.dao.connectDB.DatabaseConnection;

import java.io.IOException;
import java.sql.*;

public class addUser {

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





