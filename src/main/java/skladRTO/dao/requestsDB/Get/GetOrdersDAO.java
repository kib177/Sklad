package skladRTO.dao.requestsDB.Get;

import javafx.collections.ObservableList;
import skladRTO.api.models.Authorization;
import skladRTO.api.models.OrderStatus;
import skladRTO.dao.modelDAO.OrderFunction;
import skladRTO.api.models.lists.ListOrder;
import skladRTO.dao.connectDB.DatabaseConnection;
import skladRTO.api.models.FX.OrderFX;
import skladRTO.api.models.Order;
import skladRTO.api.models.ProductAdd;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.sql.*;
import java.util.List;

public class GetOrdersDAO implements OrderFunction<OrderFX, ListOrder> {

    @Override
    public ObservableList<OrderFX> showListOfOrders() {
        ListOrder listOrder = new ListOrder();
        WeakReference<ListOrder> weakReference = new WeakReference<>(listOrder);
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

        ListOrder listOrder = new ListOrder();
        WeakReference<ListOrder> weakReference = new WeakReference<>(listOrder);
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
        ListOrder listOrder = new ListOrder();
        WeakReference<ListOrder> weakReference = new WeakReference<>(listOrder);
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

    @Override
    public void delete(Order order, List<ProductAdd> list) {

    }

    //    @Override // пробный метод не трогать!!! артем пидор
//    public void delete(Order order, List<ProductAdd> list) {
//        String SQL = "   INSERT INTO sklad.product_info () VALUES ();" +
//                "           SET @num1 := LAST_INSERT_ID();  " +
//                "           INSERT INTO orders (order_description, user_id, order_date, number_order) Values (?, ?, ?, ?);" +
//                "           SET @num2 := LAST_INSERT_ID();  " +
//                "           INSERT INTO sklad.order_product (name_product,amount,status_id,order_id,product_info_id)" +
//                "           VALUES ('sd', 'sad' , 1,@num2, @num1);";
//
////        String addProductInfo = "INSERT INTO sklad.product_info () VALUES ();";
////        String lastIndexId = "SELECT LAST_INSERT_ID();";
////        String addOrder_Product = " INSERT INTO sklad.order_product (name_product,amount,status_id,order_id,product_info_id)" +
////                " VALUES ('product3', 20 , 1,2, LAST_INSERT_ID());";
////        String addOrder = "INSERT INTO orders (order_description, user_id, order_date, number_order) Values (?, ?, ?, ?)";
//        try (Connection connection = DatabaseConnection.getDatabaseConnection();
//             PreparedStatement statement = connection.prepareStatement(SQL)){
//            statement.setString(1, order.getOrderDescription());
//            System.out.println("начало");
//            // надо вернуть ид юзера, можно вернуть текущий ид аутентифицированного пользователя
//            statement.setInt(2, 4);
//            statement.setString(3, order.getOrderDate());
//            statement.setInt(4, order.getNumberOrder());
//           statement.addBatch();
//            System.out.println("после пуша");
//            System.out.println("перебор массива");
//            for (ProductAdd product : list) {
//                statement.setString(5, product.getNameProduct());
//                statement.setInt(6, product.getAmount());
//                statement.addBatch();
//            }
////            statement.addBatch(lastIndexId);
////            statement.addBatch(addOrder);
//        statement.addBatch();
//            statement.executeBatch();
//        } catch (SQLException | IOException e) {
//            e.printStackTrace();
//        }
//    }
//    //        try (Connection connection = DatabaseConnection.getDatabaseConnection();
//    //             Statement statement = connection.createStatement()) {
//    //            String str = "INSERT INTO sklad.product_info (articul,arrival_date,description) VALUES ('article3', '646' , '321323');" ;
//    //              String str1 =      " INSERT INTO sklad.order_product (name_product,amount,status_id,order_id,product_info_id)" +
//    //                    " VALUES ('product3', 20 , 1,2, LAST_INSERT_ID());";
//    //              statement.addBatch(str);
//    //              statement.addBatch(str1);
//    //    INSERT INTO sklad.product_info () VALUES ();
//    //    SET @num := LAST_INSERT_ID();
//    //    INSERT INTO orders (order_description, user_id, order_date, number_order) Values ('order24', 1, '2002', '2021');
//    //	INSERT INTO sklad.order_product (name_product,amount,status_id,order_id,product_info_id)
//    //                    VALUES ('product3', 20 , 1,LAST_INSERT_ID(), @num);
//
//
//
//    ////INSERT INTO sklad.product_info (articul,arrival_date,description) VALUES ('2464', '646' , '321323');
//    ////INSERT INTO sklad.order_product (name_product,amount,status_id,order_id,product_info_id) VALUES ('ksdjfh', 20 , 1,2, LAST_INSERT_ID());
    @Override
    public void add(Order order, List<ProductAdd> list) {
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
                int lastInsertId = 0;

                //возвращаю последний индекс
                try (Statement statement = connection.createStatement();) {
                    String str1 = "SELECT LAST_INSERT_ID();";
                    ResultSet resultSet = statement.executeQuery(str1);
                    if (resultSet.next()) {
                        lastInsertId = resultSet.getInt(1);
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                // перебираю лист и ложу в БАТЧ а затем передаю в БД
                try (PreparedStatement preparedStatement1 = connection.prepareStatement(str);) {
                    if (lastInsertId != 0) {
                        System.out.println("перебор массива");
                        for (ProductAdd product : list) {
                            preparedStatement1.setString(1, product.getNameProduct());
                            preparedStatement1.setInt(2, product.getAmount());
                            // status_id водиться в ручную, а надо создавать объект!!!!
                            preparedStatement1.setInt(3, 1);
                            //проблема с LAST_INSERT_ID()
                            //     preparedStatement1.setString(4,"LAST_INSERT_ID()");
                            preparedStatement1.setInt(4, lastInsertId);
                            //product_info_id вводиться в ручную, а надо создавать объект!!!!
                            preparedStatement1.setInt(5, 1);
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
    public void FillingInList(ListOrder listOrder, ResultSet resultSet) throws SQLException {
        listOrder.create(resultSet.getInt("id"), resultSet.getInt("number_order"),
                resultSet.getString("order_description"),
                resultSet.getString("last_name"), resultSet.getString("order_date"));
    }

    public void UpdateOrder() {

    }

//    public OrderStatus getStatus(){
//
//    }
}
