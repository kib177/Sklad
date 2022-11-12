package skladrto.project.RequestsDB.Add;

import skladrto.project.connectDB.DatabaseConnection;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class addOrder {

    public static void add(String name, String articul, String opis, String oboryd, String status, String kol, String zakazchik) throws SQLException {

        try {
            PreparedStatement preparedStatement = DatabaseConnection.getDatabaseConnection().prepareStatement("INSERT INTO orders (Name, articul, opis, oboryd, status, kol, zakazchik) Values (?, ?, ?, ?, ?, ?, ?)");
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, articul);
            preparedStatement.setString(3, opis);
            preparedStatement.setString(4, oboryd);
            preparedStatement.setString(5, status);
            preparedStatement.setString(6, kol);
            preparedStatement.setString(7, zakazchik);

            int rows = preparedStatement.executeUpdate();

            System.out.printf("%d rows added", rows);

        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}

