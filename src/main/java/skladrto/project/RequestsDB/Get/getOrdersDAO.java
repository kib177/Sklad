package skladrto.project.RequestsDB.Get;

import javafx.collections.ObservableList;
import skladrto.project.DAO.modelDAO.OrderFunction;
import skladrto.project.List.ListOrder;
import skladrto.project.DAO.connectDB.DatabaseConnection;
import skladrto.project.Model.Order;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class getOrdersDAO implements OrderFunction {

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
    public ObservableList<Order> searchSubdivision(String subdivisionSearch) {
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
            preparedStatement.setInt(1, metod(subdivisionSearch));
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
        // оставил тебе для проверок или коректировок
//        int id = resultSet.getInt("id");
//        String article = resultSet.getString("product_article");
//        String name = resultSet.getString("name");
//        int amount = resultSet.getInt("amount");
//        String description = resultSet.getString("order_description");
//        String lastName = resultSet.getString("last_name");
//        String equipment = resultSet.getString("equipment");
//        String status1 = resultSet.getString("status");
//        String subdivision = resultSet.getString("subdivision");
//        String orderDate = resultSet.getString("order_date");
//        System.out.printf("%-5s%-20s%-20s%-20s%-20s%-20s%-20s%-20s%-20s%-20s\n", id, article, name, amount,
//                description, lastName, equipment, status1, subdivision, orderDate);

        listOrder.create(resultSet.getInt("id"), resultSet.getString("product_article"),
                resultSet.getString("name"), resultSet.getInt("amount"),
                resultSet.getString("order_description"), resultSet.getString("last_name"),
                resultSet.getString("equipment"), resultSet.getString("status"),
                resultSet.getString("subdivision"), resultSet.getString("order_date"));
    }

    public int metod(String str) {
        // потом доделать через дженерик для поиска необходимой таблице
        int num = 0;
        try {
            ResultSet resultSet = DatabaseConnection.getStatement().executeQuery("select * from subdivision where " +
                    "subdivision.subdivision = '" + str + "'");
            while (resultSet.next()) {
                num = resultSet.getInt("subdivision.id");
            }
        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }

        return num;
    }
}
