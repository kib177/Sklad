package skladRTO.dao.modelDAO;

import javafx.collections.ObservableList;
import skladRTO.api.models.*;
import skladRTO.dao.interfaces.OrderFunction;
import skladRTO.api.FX.lists.OrderListFX;
import skladRTO.dao.connectDB.DatabaseConnection;
import skladRTO.api.FX.models.OrderFX;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.sql.*;
import java.util.List;

public class GetOrdersDAO implements OrderFunction<OrderFX, OrderListFX> {

    @Override
    public ObservableList<OrderFX> showListOfOrders() {
        OrderListFX listOrder = new OrderListFX();
        WeakReference<OrderListFX> weakReference = new WeakReference<>(listOrder);
        String str = "SELECT * FROM orders " +
                " LEFT JOIN users ON (orders.user_id=users.id_users)";
        try (ResultSet resultSet = DatabaseConnection.getStatement().executeQuery(str);) {
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
                " WHERE orders.number_order = ?;";
        try (PreparedStatement preparedStatement = DatabaseConnection.getDatabaseConnection().prepareStatement(str);) {
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

    /**
     * Метод удаляет заказ и все его позиции
     * (Сначало вызывается метод удаления позиций, после чего удаляется объект
     * @param order - принимает на вход объект типа Ордер, который необходимо удалить
     * @param list - лист со всеми позициями данного ордера
     */
    @Override
    public void delete(Order order, List<Product> list) {

        // Добавить транзакцию
        String SQL = "DELETE FROM orders WHERE id =?;";
        try (Connection connection = DatabaseConnection.getDatabaseConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {
            getProduct getProduct = new getProduct();
            for (Product product :list){
                getProduct.delete(product);
            }
            preparedStatement.setInt(1, order.getId());
            preparedStatement.executeUpdate();
        }catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void add(Order order, List<Product> list) { // лист приходит заполненым только имя и количество id создать

        try (Connection connection = DatabaseConnection.getDatabaseConnection()) {
            connection.setAutoCommit(false);
            System.out.println("Creating savepoint...");
            Savepoint savepointOne = connection.setSavepoint("SavepointOne");
            //Возможно лучше вынести его из метода в переменную класса вместе с запросом
            String SQL = "INSERT INTO orders (order_description, user_id, order_date, number_order) Values (?, ?, ?, ?)";
            String str = "INSERT INTO order_product (name_product,amount,status_id,order_id,product_info_id)" +
                    "VALUES(?,?,?,?,?);";
            String str1 = "SELECT LAST_INSERT_ID();";
            //
            try (PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {
                //создаю новый ордер
                System.out.println("Create order");
                System.out.println(order);
                preparedStatement.setString(1, order.getOrderDescription());
                // надо вернуть ид юзера, можно вернуть текущий ид аутентифицированного пользователя
                preparedStatement.setInt(2, Authorization.getUser().getId());
                preparedStatement.setString(3, order.getOrderDate());
                preparedStatement.setInt(4, order.getNumberOrder());
                preparedStatement.executeUpdate();
                System.out.println("Complete create order");
                int lastInsertId1 = 0;
                int lastInsertId2 = 0;

                //возвращаю последний индекс
                try (Statement statement = connection.createStatement();) {
                    ResultSet resultSet = statement.executeQuery(str1);
                    if (resultSet.next()) {
                        lastInsertId1 = resultSet.getInt(1);
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }

                try (PreparedStatement preparedStatement1 = connection.prepareStatement(str);) {
                    if (lastInsertId1 != 0) {
                        System.out.println("перебор массива");
                        for (Product product : list) {
                            try (Statement statement = connection.createStatement();) {
                                statement.executeUpdate("INSERT INTO sklad.product_info () VALUES ();");
                                ResultSet resultSet = statement.executeQuery(str1);
                                if (resultSet.next()) {
                                    lastInsertId2 = resultSet.getInt(1);
                                }
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                            preparedStatement1.setString(1, product.getNameProduct());
                            preparedStatement1.setInt(2, product.getAmount());
                            // status_id водиться в ручную, а надо создавать объект!!!!
                            preparedStatement1.setInt(3, 1);
                            //проблема с LAST_INSERT_ID()
                            //     preparedStatement1.setString(4,"LAST_INSERT_ID()");
                            preparedStatement1.setInt(4, lastInsertId1);
                            //product_info_id вводиться в ручную, а надо создавать объект!!!!
                            preparedStatement1.setInt(5, lastInsertId2);
                            preparedStatement1.addBatch();
                        }
                        System.out.println("push list");
                        preparedStatement1.executeBatch();
                        System.out.println("complete push list");
                    } else {
                        connection.rollback(savepointOne);
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } catch (Exception e) {
                System.out.println("SQLException. Executing rollback to savepoint...");
                connection.rollback(savepointOne);
                System.out.println("после отката");
            }
            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void FillingInList(OrderListFX listOrder, ResultSet resultSet) throws SQLException {
        listOrder.create(resultSet.getInt("id"), resultSet.getInt("number_order"),
                resultSet.getString("order_description"),
                resultSet.getString("last_name"), resultSet.getString("order_date"));
    }

    public void UpdateOrder() {

    }
}


