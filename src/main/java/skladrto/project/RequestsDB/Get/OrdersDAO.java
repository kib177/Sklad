package skladrto.project.RequestsDB.Get;

import javafx.collections.ObservableList;
import skladrto.project.List.ListOrder;
import skladrto.project.DAO.connectDB.DatabaseConnection;
import skladrto.project.Model.Order;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OrdersDAO {

    public ObservableList<Order> showListOfOrders() {
        ListOrder listOrder = new ListOrder();
        WeakReference<ListOrder> weakReference = new WeakReference<>(listOrder);
        try {
            ResultSet resultSet = DatabaseConnection.getStatement().executeQuery("SELECT orders.id, product.name, orders.product_article, " +
                    " product.equipment, orders.status_order_id, orders.amount,orders.user_id, orders.subdivision_id " +
                    " FROM sklad.orders,sklad.product,sklad.users,sklad.subdivision,sklad.order_status " +
                    " where orders.id = orders.id " +
                    " AND product.name = product.name" +
                    " AND orders.product_article = product.article" +
                    " AND product.equipment = product.equipment" +
                    " AND orders.status_order_id = order_status.status" +
                    " AND orders.user_id = users.first_name" +
                    " AND orders.subdivision_id = subdivision.subdivision;");

            while (resultSet.next()) {

                int id = resultSet.getInt("id_users");
                String name = resultSet.getString("name");
                String article = resultSet.getString("article");
                String equipment = resultSet.getString("equipment");
                String status1 = resultSet.getString("status");


                int amount = resultSet.getInt("amount");
                String firstName = resultSet.getString("first_name");
                String subdivision = resultSet.getString("subdivision");
                System.out.printf("%-5s%-20s%-20s%-20ss%-20ss%-20s%-20s%-20s\n", id, name, article, equipment, status1,
                        amount, firstName, subdivision);
                System.out.println("Проверка");


                String firstName = resultSet.getString("first_name");
                listOrder.create(resultSet.getInt("id_users"), resultSet.getString("product_article"),
                        resultSet.getInt("amount"), resultSet.getString("order_description"),
                        resultSet.getString("Заказчик какой-то"), resultSet.getString("subdivision"),
                        resultSet.getString("status"), resultSet.getString("date"));

            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
        return listOrder.getOrderData();
    }
}
