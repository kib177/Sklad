package skladrto.project.RequestsDB.Get;

import javafx.collections.ObservableList;
import skladrto.project.DAO.modelDAO.FillingInListsDAO;
import skladrto.project.DAO.modelDAO.OrderFunction;
import skladrto.project.List.ListOrder;
import skladrto.project.DAO.connectDB.DatabaseConnection;
import skladrto.project.Model.Order;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class getOrdersDAO implements OrderFunction<Order, ListOrder> {

    @Override
    public ObservableList<Order> showListOfOrders() {
        ListOrder listOrder = new ListOrder();
        WeakReference<ListOrder> weakReference = new WeakReference<>(listOrder);
        try {
            ResultSet resultSet = DatabaseConnection.getStatement().executeQuery("SELECT * FROM orders " +
                    " LEFT JOIN users ON (orders.user_id=users.id_users)");

            while (resultSet.next()) {
                FillingInList(listOrder, resultSet);
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
        return listOrder.getOrderData();
    }

    @Override
    public ObservableList<Order> searchName(String orderDescription) {

        ListOrder listOrder = new ListOrder();
        WeakReference<ListOrder> weakReference = new WeakReference<>(listOrder);
        try {
            PreparedStatement preparedStatement = DatabaseConnection.getDatabaseConnection().prepareStatement(
                    "SELECT * FROM orders " +
                    " LEFT JOIN users ON (orders.user_id=users.id_users)" +
                    " WHERE order_description LIKE '%" + orderDescription + "%'");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                FillingInList(listOrder, resultSet);
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
        return listOrder.getOrderData();
    }

    @Override
    public ObservableList<Order> searchNumber(String numberOrder) {
        ListOrder listOrder = new ListOrder();
        WeakReference<ListOrder> weakReference = new WeakReference<>(listOrder);

        try {
            PreparedStatement preparedStatement = DatabaseConnection.getDatabaseConnection().prepareStatement(
                    "SELECT * FROM orders " +
                    " LEFT JOIN users ON (orders.user_id=users.id_users)" +
                    " WHERE orders.number_order = ?;");
            preparedStatement.setString(1, numberOrder);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                FillingInList(listOrder, resultSet);
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
        return listOrder.getOrderData();
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public void FillingInList(ListOrder listOrder, ResultSet resultSet) throws SQLException {
        listOrder.create(resultSet.getInt("id"), resultSet.getInt("number_order"),
                resultSet.getString("order_description"),
                resultSet.getString("last_name"), resultSet.getString("order_date"));
    }
}
