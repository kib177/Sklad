package skladrto.project.RequestsDB.Get;

import javafx.collections.ObservableList;
import skladrto.project.List.ListOrder;
import skladrto.project.DAO.connectDB.DatabaseConnection;
import skladrto.project.Model.Order;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.sql.PreparedStatement;
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
                nePridumalNazvanieDlyaMethoda(listOrder, resultSet);
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
        return listOrder.getOrderData();
    }

    @Override
    public ObservableList<Order> searchName(String productName) {
        // поиск по имени
        // принимает на вход введенное имя продукта
        // если продукт с таким именем есть в заказах показывает все заказы - если такого нет ничего не показывает!!!
        //          возможно надо сделать проверку по продуктам (с приведением к нижнему регистру) и если такого нет,
        //          выводить сообщение "продукт не найден или вы ввели название неправильно" что-то типа того
        ListOrder listOrder = new ListOrder();
        WeakReference<ListOrder> weakReference = new WeakReference<>(listOrder);
        try {
            PreparedStatement preparedStatement = DatabaseConnection.getDatabaseConnection().prepareStatement("SELECT orders.id, orders.product_article," +
                    " product.name, orders.amount, orders.order_description, " +
                    " users.last_name, product.equipment, order_status.status, subdivision.subdivision, orders.order_date" +
                    " FROM orders " +
                    " LEFT JOIN users ON (orders.user_id=users.id_users)" +
                    " LEFT JOIN product ON (orders.product_article =product.article)" +
                    " LEFT JOIN order_status ON (orders.status_order_id=order_status.id)" +
                    " LEFT JOIN subdivision ON (users.subdivision_id= subdivision.id )" +
                    " WHERE product.name = ?;");
            preparedStatement.setString(1, productName);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                nePridumalNazvanieDlyaMethoda(listOrder, resultSet);
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
        return listOrder.getOrderData();
    }

    @Override
    public ObservableList<Order> searchArticle(String articleProduct) {
        // поиск по артиклю
        // принимает на вход введенное артикль продукта
        // если продукт с таким артиклем есть в заказах показывает все заказы - если такого нет ничего не показывает!!!
        //          возможно надо сделать проверку по продуктам (с приведением к нижнему регистру) и если такого нет,
        //          выводить сообщение "продукт не найден или вы ввели название неправильно" что-то типа того
        ListOrder listOrder = new ListOrder();
        WeakReference<ListOrder> weakReference = new WeakReference<>(listOrder);
        try {
            PreparedStatement preparedStatement = DatabaseConnection.getDatabaseConnection().prepareStatement("SELECT orders.id, orders.product_article," +
                    " product.name, orders.amount, orders.order_description, " +
                    " users.last_name, product.equipment, order_status.status, subdivision.subdivision, orders.order_date" +
                    " FROM orders " +
                    " LEFT JOIN users ON (orders.user_id=users.id_users)" +
                    " LEFT JOIN product ON (orders.product_article =product.article)" +
                    " LEFT JOIN order_status ON (orders.status_order_id=order_status.id)" +
                    " LEFT JOIN subdivision ON (users.subdivision_id= subdivision.id )" +
                    " WHERE orders.product_article = ?;");
            preparedStatement.setString(1, articleProduct);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                nePridumalNazvanieDlyaMethoda(listOrder, resultSet);
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
        return listOrder.getOrderData();
    }

    @Override
    public ObservableList<Order> searchSubdivision(int subdivisionSearch) {
        // поиск по подразделению
        // принимает на вход введенное ID подразделения
        // если продукт с таким ID подразделения есть в заказах показывает все заказы - если такого нет ничего не показывает!!!
        //          возможно надо сделать проверку по продуктам и если такого нет,
        //          выводить сообщение "продукт не найден или вы ввели название неправильно" что-то типа того
        ListOrder listOrder = new ListOrder();
        WeakReference<ListOrder> weakReference = new WeakReference<>(listOrder);
        try {
            PreparedStatement preparedStatement = DatabaseConnection.getDatabaseConnection().prepareStatement("SELECT orders.id, orders.product_article," +
                    " product.name, orders.amount, orders.order_description, " +
                    " users.last_name, product.equipment, order_status.status, subdivision.subdivision, orders.order_date" +
                    " FROM orders " +
                    " LEFT JOIN users ON (orders.user_id=users.id_users)" +
                    " LEFT JOIN product ON (orders.product_article =product.article)" +
                    " LEFT JOIN order_status ON (orders.status_order_id=order_status.id)" +
                    " LEFT JOIN subdivision ON (users.subdivision_id= subdivision.id )" +
                    " WHERE subdivision.id = ?;");
            preparedStatement.setInt(1, subdivisionSearch);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                nePridumalNazvanieDlyaMethoda(listOrder, resultSet);
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
        return listOrder.getOrderData();
    }

    private void nePridumalNazvanieDlyaMethoda(ListOrder listOrder, ResultSet resultSet) throws SQLException {
        // вынес получение данных из БД в отдельный метод чтобы не дублировать код, он одинаковый для всех операций
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
        System.out.printf("%-5s%-20s%-20s%-20s%-20s%-20s%-20s%-20s%-20s%-20s\n", id, article, name, amount,
                description, lastName, equipment, status1, subdivision, orderDate);

// надо переписать я пока закоментил!!!

//        String lastName1 = resultSet.getString("last_name");
//        listOrder.create(resultSet.getInt("id"), resultSet.getString("product_article"),
//                resultSet.getInt("amount"), resultSet.getString("order_description"),
//                resultSet.getString("Заказчик какой-то"), resultSet.getString("subdivision"),
//                resultSet.getString("status"), resultSet.getString("date"));
    }
}
