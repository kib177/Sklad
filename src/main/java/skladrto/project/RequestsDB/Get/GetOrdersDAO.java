package skladrto.project.RequestsDB.Get;

import javafx.collections.ObservableList;
import skladrto.project.DAO.modelDAO.OrderFunction;
import skladrto.project.List.ListOrder;
import skladrto.project.DAO.connectDB.DatabaseConnection;
import skladrto.project.Model.FX.OrderFX;
import skladrto.project.Model.Order;
import skladrto.project.Model.ProductOrderAdd;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GetOrdersDAO implements OrderFunction<OrderFX, ListOrder> {

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

        try (Connection connection = DatabaseConnection.getDatabaseConnection()) {
            connection.setAutoCommit(false);
            System.out.println("Creating savepoint...");
            Savepoint savepointOne = connection.setSavepoint("SavepointOne");
            //Возможно лучше вынести его из метода в переменную класса вместе с запросом
            String SQL = "INSERT INTO orders (order_description, user_id, order_date, number_order) Values (?, ?, ?, ?)";
            String str = "INSERT INTO order_product (name_product,amount,status_id,order_id,product_info_id)" +
                    "VALUES(?,?,?,?,?);";
            try (PreparedStatement preparedStatement = connection.prepareStatement(SQL);
                 Statement statement = connection.createStatement();
                 PreparedStatement preparedStatement1 = connection.prepareStatement(str);) {
                //создаю новый ордер
                System.out.println("Create order");
                preparedStatement.setString(1, order.getOrderDescription());
                // надо вернуть ид юзера, можно вернуть текущий ид аутентифицированного пользователя
                preparedStatement.setInt(2, order.getUserId());
                preparedStatement.setString(3, order.getOrderDate());
                preparedStatement.setInt(4, order.getNumberOrder());
                preparedStatement.executeUpdate();
                System.out.println("Complete create order");

                String str1 = "SELECT LAST_INSERT_ID();";
                ResultSet resultSet = statement.executeQuery(str1);
                int lastInsertId = 0;
                if (resultSet.next()) {
                    lastInsertId = resultSet.getInt(1);
                }
                List<ProductOrderAdd> list = new ArrayList<>();
                list.add(new ProductOrderAdd("dress", 15, 1, 1));
                list.add(new ProductOrderAdd("short", 21, 1, 1));
                list.add(new ProductOrderAdd("clothes", 11, 1, 1));
                System.out.println("Complete create list");
                System.out.println("LAST INDEX ID = " + lastInsertId);

                if (lastInsertId != 0) {
                    System.out.println("перебор массива");
                    for (ProductOrderAdd product : list) {
                        preparedStatement1.setString(1, product.getNameProduct());
                        preparedStatement1.setInt(2, product.getAmount());
                        preparedStatement1.setInt(3, 1);
                        //проблема с LAST_INSERT_ID()
                        //     preparedStatement1.setString(4,"LAST_INSERT_ID()");
                        preparedStatement1.setInt(4, lastInsertId);
                        preparedStatement1.setInt(5, 1);
                        preparedStatement1.addBatch();
                    }
                    System.out.println("push list");
                    preparedStatement1.executeBatch();
                    System.out.println("complete push list");
                }else {
                    connection.rollback(savepointOne);
                }
            } catch (Exception e) {
                //проблема с откатом, его нет
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
    public void FillingInList(ListOrder listOrder, ResultSet resultSet) throws SQLException {
        listOrder.create(resultSet.getInt("id"), resultSet.getInt("number_order"),
                resultSet.getString("order_description"),
                resultSet.getString("last_name"), resultSet.getString("order_date"));
    }
}
