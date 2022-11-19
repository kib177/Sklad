package skladrto.project.RequestsDB.Get;

import javafx.collections.ObservableList;
import skladrto.project.DAO.modelDAO.OrderFunction;
import skladrto.project.List.ListOrder;
import skladrto.project.DAO.connectDB.DatabaseConnection;
import skladrto.project.Model.FX.OrderFX;
import skladrto.project.Model.Order;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.sql.*;

public class getOrdersDAO implements OrderFunction<OrderFX, ListOrder> {

    @Override
    public ObservableList<OrderFX> showListOfOrders() {
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
    public ObservableList<OrderFX> searchName(String orderDescription) {

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
    public ObservableList<OrderFX> searchNumber(String numberOrder) {
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
    public void add(Order order) {
        //для добавления объектов через транзакции мне нужен объект конекшена, пока создаю новый, но он сам закроется
        // добавление через JDBS транзакцию
        try (Connection connection = DatabaseConnection.getDatabaseConnection();) {
            connection.setAutoCommit(false);
            Savepoint savepointOne = connection.setSavepoint("SavepointOne");
            //Возможно лучше вынести его из метода в переменную класса вместе с запросом
            String SQL;
            try {
                //создаю новый ордер
                SQL = "INSERT INTO orders (order_description, user_id, order_date, number_order) Values (?, ?, ?, ?)";
                PreparedStatement preparedStatement = DatabaseConnection.getDatabaseConnection().prepareStatement(SQL);
                preparedStatement.setString(1, order.getOrderDescription());
                // надо вернуть ид юзера, можно вернуть текущий ид аутентифицированного пользователя
                preparedStatement.setInt(2, order.getUser().getId());
                preparedStatement.setString(3, order.getOrderDate());
                preparedStatement.setInt(4, order.getNumberOrder());
                preparedStatement.executeUpdate();

            //    "SELECT LAST_INSERT_ID();"


                connection.commit();
            } catch (Exception e) {
                System.out.println("SQLException. Executing rollback to savepoint...");
                connection.rollback(savepointOne);
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void FillingInList(ListOrder listOrder, ResultSet resultSet) throws SQLException {
        listOrder.create(resultSet.getInt("id"), resultSet.getInt("number_order"),
                resultSet.getString("order_description"),
                resultSet.getString("last_name"), resultSet.getString("order_date"));
    }
}
