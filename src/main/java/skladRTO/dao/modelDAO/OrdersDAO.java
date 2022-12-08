package skladRTO.dao.modelDAO;

import javafx.collections.ObservableList;
import skladRTO.api.FX.models.ProductFX;
import skladRTO.api.models.*;
import skladRTO.dao.interfaces.OrderFunction;
import skladRTO.api.FX.lists.OrderListFX;
import skladRTO.dao.connectDB.DatabaseConnection;
import skladRTO.api.FX.models.OrderFX;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.sql.*;
import java.util.List;

public class OrdersDAO implements OrderFunction<OrderFX, OrderListFX> {

    public static final String LAST_INDEX_ID = "SELECT LAST_INSERT_ID();";
    public static final String ADD_NEW_ORDER = "INSERT INTO orders (order_description, user_id, order_date, number_order)" +
            " Values (?, ?, ?, ?)";

    @Override
    public ObservableList<OrderFX> showListOfOrders() {
        OrderListFX listOrder = new OrderListFX();
        WeakReference<OrderListFX> weakReference = new WeakReference<>(listOrder);
        String str = "SELECT * FROM orders " +
                " LEFT JOIN users ON (orders.user_id=users.id_users)";
        try (ResultSet resultSet = DatabaseConnection.getStatement().executeQuery(str)) {
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

        OrderListFX listOrder = new OrderListFX();
        WeakReference<OrderListFX> weakReference = new WeakReference<>(listOrder);
        String str = "SELECT * FROM orders " +
                " LEFT JOIN users ON (orders.user_id=users.id_users)" +
                " WHERE order_description LIKE '%" + orderDescription + "%'";
        try (PreparedStatement preparedStatement = DatabaseConnection.getDatabaseConnection().prepareStatement(str)) {
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
        OrderListFX listOrder = new OrderListFX();
        WeakReference<OrderListFX> weakReference = new WeakReference<>(listOrder);
        String str = "SELECT * FROM orders" +
                " LEFT JOIN users ON (orders.user_id=users.id_users)" +
                " WHERE orders.number_order LIKE '%"+numberOrder+"%'";
        try (PreparedStatement preparedStatement = DatabaseConnection.getDatabaseConnection().prepareStatement(str)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                FillingInList(listOrder, resultSet);
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
        return listOrder.getOrderData();
    }

    /**
     * Метод удаляет заказ и все его позиции
     *
     * @param order - принимает на вход объект типа Ордер, который необходимо удалить
     * @param list  - лист со всеми позициями данного ордера
     */
    @Override
    public void delete(Order order, List<ProductFX> list) {

        // Добавить транзакцию
        String SQL = "DELETE FROM orders WHERE id =?;";
        try (Connection connection = DatabaseConnection.getDatabaseConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {
            preparedStatement.setInt(1, order.getId());
            ProductDAO getProduct = new ProductDAO();
            for (ProductFX product : list) {
                getProduct.delete(product);
            }
            preparedStatement.executeUpdate();
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * ВОПРОС: Три try в одном методе
     *
     * @param order
     * @param list
     */
    @Override
    public void add(Order order, List<Product> list) { // лист приходит заполненым только имя и количество id создать

        try (Connection connection = DatabaseConnection.getDatabaseConnection();) {
            connection.setAutoCommit(false);
            Savepoint savepointOne = connection.setSavepoint("SavepointOne");
            try (PreparedStatement preparedStatement = connection.prepareStatement(ADD_NEW_ORDER)) {
                //создаю новый ордер
                preparedStatement.setString(1, order.getOrderDescription());
                preparedStatement.setInt(2, Authorization.getUser().getId());
                preparedStatement.setString(3, order.getOrderDate());
                preparedStatement.setString(4, order.getNumberOrder());
                preparedStatement.executeUpdate();
                int lastInsertId = 0;
                //возвращаю последний индекс
                try (Statement statement = connection.createStatement();) {
                    ResultSet resultSet = statement.executeQuery(LAST_INDEX_ID);
                    if (resultSet.next()) {
                        lastInsertId = resultSet.getInt(1);
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
 //Проблема с этой транзакцией, если ее ставить в конце метода то не срабатывает
 //  передоваемое значение во второй вызываемый метод
                connection.commit();
                ProductDAO productDAO = new ProductDAO();
                productDAO.createProduct(list, lastInsertId);
            } catch (Exception e) {
                System.out.println("SQLException. Executing rollback to savepoint...");
                connection.rollback(savepointOne);
            }
            connection.setAutoCommit(true);
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void FillingInList(OrderListFX listOrder, ResultSet resultSet) throws SQLException {
        listOrder.create(resultSet.getInt("id"), resultSet.getString("number_order"),
                resultSet.getString("order_description"),
                resultSet.getString("first_name"),resultSet.getString("last_name"),
                resultSet.getString("order_date"));
    }

    public void UpdateOrder() {

    }
public ObservableList<OrderFX> searchForNameUser(String name){
    OrderListFX listOrder = new OrderListFX();
    WeakReference<OrderListFX> weakReference = new WeakReference<>(listOrder);
    String str = "SELECT * FROM orders" +
            " LEFT JOIN users ON (orders.user_id=users.id_users)" +
            " WHERE users.last_name LIKE '%"+name+"%'";
        try (PreparedStatement preparedStatement = DatabaseConnection.getDatabaseConnection().prepareStatement(str)) {
        ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
            FillingInList(listOrder, resultSet);
        }
    } catch (SQLException | IOException e) {
        e.printStackTrace();
    }
        return listOrder.getOrderData();
}
}


