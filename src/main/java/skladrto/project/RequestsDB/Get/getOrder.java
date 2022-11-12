package skladrto.project.RequestsDB.Get;

import skladrto.project.List.ListOrder;
import skladrto.project.connectDB.DatabaseConnection;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class getOrder {

    public static void GetOrder() {

        try {
            ResultSet rs = DatabaseConnection.getStatement().executeQuery("select * from orders");

            while (rs.next()) { // передаем в лист заказов
                ListOrder.create(rs.getString("Name"), rs.getString("articul"), rs.getString("opis"),
                        rs.getString("oboryd"), rs.getString("status"), rs.getString("kol"), rs.getString("zakazchik"));
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }
}
