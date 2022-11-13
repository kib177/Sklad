package skladrto.project.RequestsDB.Get;

import javafx.collections.ObservableList;
import skladrto.project.List.ListOrder;
import skladrto.project.DAO.connectDB.DatabaseConnection;
import skladrto.project.Model.Order;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OrdersDAO implements OrderFunction {

    @Override
    public ObservableList<Order> showListOfOrders() {
        ListOrder listOrder = new ListOrder();
        WeakReference<ListOrder> weakReference = new WeakReference<>(listOrder);
        try {
            ResultSet resultSet = DatabaseConnection.getStatement().executeQuery("SELECT orders.id, orders.product_article," +
                    " product.name, orders.amount, orders.order_description, " +
                    " users.last_name, product.equipment, order_status.status, subdivision.subdivision, orders.order_date" +
                    " FROM orders " +
                    " LEFT JOIN users ON (orders.user_id=users.id_users)" +
                    " LEFT JOIN product ON (orders.product_article =product.article)" +
                    " LEFT JOIN order_status ON (orders.status_order_id=order_status.id)" +
                    " LEFT JOIN subdivision ON (users.subdivision_id= subdivision.id );");

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String article = resultSet.getString("product_article");
                String name = resultSet.getString("name");
                int amount = resultSet.getInt("amount");
                String description = resultSet.getString("order_description");
                String lastName = resultSet.getString("last_name");
                String equipment = resultSet.getString("equipment");
                String status1 = resultSet.getString("status");
                String subdivision = resultSet.getString("subdivision");
                String orderDate = resultSet.getString("order_date");
                System.out.printf("%-5s%-20s%-20s%-20s%-20s%-20s%-20s%-20s%-20s%-20s\n", id,  article, name, amount,
                        description,lastName,equipment,status1,subdivision,orderDate);

// надо переписать я пока закоментил!!!

//                String lastName1 = resultSet.getString("last_name");
//                listOrder.create(resultSet.getInt("id"), resultSet.getString("product_article"),
//                        resultSet.getInt("amount"), resultSet.getString("order_description"),
//                        resultSet.getString("Заказчик какой-то"), resultSet.getString("subdivision"),
//                        resultSet.getString("status"), resultSet.getString("date"));
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
        return listOrder.getOrderData();
    }

    @Override
    public void createNewOrder() {

    }

    @Override
    public void searchName() {

    }

    @Override
    public void searchArticle() {

    }

    @Override
    public void searchSubdivision() {

    }
}
