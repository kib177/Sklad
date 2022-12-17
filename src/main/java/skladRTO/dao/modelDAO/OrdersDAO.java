package skladRTO.dao.modelDAO;

import javafx.collections.ObservableList;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import skladRTO.api.FX.lists.OrderListFX;
import skladRTO.api.FX.models.OrderFX;
import skladRTO.api.FX.models.ProductFX;
import skladRTO.api.models.Order;
import skladRTO.api.models.Product;
import skladRTO.dao.connectDB.DatabaseConnection;
import skladRTO.dao.interfaces.OrderFunction;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.sql.*;
import java.util.List;

public class OrdersDAO implements OrderFunction<OrderFX, OrderListFX> {

    private static final Logger logger = LogManager.getLogger(OrdersDAO.class.getName());
    public static final String LAST_INDEX_ID = "SELECT LAST_INSERT_ID();";
    public static final String ADD_NEW_ORDER = "INSERT INTO orders (order_description, user_id, machines_id, order_date, number_order)" +
            " Values (?, ?, ?, ?, ?)";

    /**
     * Метод заполняет лист ордеров из БД
     *
     * @return Возвращает заполненный лист ордеров типа OrderListFX
     */
    @Override
    public ObservableList<OrderFX> showListOfOrders() {
        OrderListFX listOrder = new OrderListFX();
        WeakReference<OrderListFX> weakReference = new WeakReference<>(listOrder);
        String str = "SELECT * FROM orders " +
                " LEFT JOIN users ON (orders.user_id=users.id_users)" +
                " LEFT JOIN machines ON (orders.machines_id=machines.id)";
        logger.debug("Отправляем запрос в БД ->\n -> " + str);
        try (ResultSet resultSet = DatabaseConnection.getStatement().executeQuery(str)) {
            logger.debug("Перебираем полученные данные из БД");
            while (resultSet.next()) {
                FillingInList(listOrder, resultSet);
            }
        } catch (SQLException | IOException e) {
            logger.warn("Произошла ошибка подключения к БД или ошибка записи/чтение данных из БД\n\t" + e);
            e.printStackTrace();
        }
        logger.debug("Возвращаем заполненный лист ордеров ->\n -> " + listOrder);
        return listOrder.getOrderData();
    }

    /**
     * Метод ищет ордера в БД по части слова в описании ордера.
     * <p>
     * ВАЖНО!: Нужно переделать запрос без конкатенации строк!
     *
     * @param orderDescription слово или его часть введенная пользователем
     * @return Возвращает заполненный лист ордеров типа OrderListFX
     */
    @Override
    public ObservableList<OrderFX> searchName(String orderDescription) {

        OrderListFX listOrder = new OrderListFX();
        WeakReference<OrderListFX> weakReference = new WeakReference<>(listOrder);
        String str = "SELECT * FROM orders " +
                " LEFT JOIN users ON (orders.user_id=users.id_users)" +
                " WHERE order_description LIKE '%" + orderDescription + "%'";
        logger.debug("Отправляем запрос в БД ->\n -> " + str);
        try (PreparedStatement preparedStatement = DatabaseConnection.getDatabaseConnection().prepareStatement(str)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            logger.debug("Перебираем полученные данные из БД");
            while (resultSet.next()) {
                FillingInList(listOrder, resultSet);
            }
        } catch (SQLException | IOException e) {
            logger.warn("Произошла ошибка подключения к БД или ошибка записи/чтение данных из БД\n\t" + e);
            e.printStackTrace();
        }
        logger.debug("Возвращаем заполненный лист ордеров ->\n -> " + listOrder);
        return listOrder.getOrderData();
    }

    /**
     * Метод ищет ордера в БД по его номеру.
     * <p>
     * ВАЖНО!: Нужно переделать запрос без конкатенации строк!
     *
     * @param numberOrder номер ордера или его часть введенная пользователем
     * @return Возвращает заполненный лист ордеров типа OrderListFX
     */
    @Override
    public ObservableList<OrderFX> searchNumber(String numberOrder) {
        OrderListFX listOrder = new OrderListFX();
        WeakReference<OrderListFX> weakReference = new WeakReference<>(listOrder);
        String str = "SELECT * FROM orders" +
                " LEFT JOIN users ON (orders.user_id=users.id_users)" +
                " WHERE orders.number_order LIKE '%" + numberOrder + "%'";
        logger.debug("Отправляем запрос в БД ->\n -> " + str);
        try (PreparedStatement preparedStatement = DatabaseConnection.getDatabaseConnection().prepareStatement(str)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            logger.debug("Перебираем полученные данные из БД");
            while (resultSet.next()) {
                FillingInList(listOrder, resultSet);
            }
        } catch (SQLException | IOException e) {
            logger.warn("Произошла ошибка подключения к БД или ошибка записи/чтение данных из БД\n\t" + e);
            e.printStackTrace();
        }
        logger.debug("Возвращаем заполненный лист ордеров  ->\n -> " + listOrder);
        return listOrder.getOrderData();
    }

    /**
     * Метод ищет ордера в БД по его номеру.
     * <p>
     * ВАЖНО!: Нужно переделать запрос без конкатенации строк!
     *
     * @param name имя пользователя для поиска
     * @return Возвращает заполненный лист ордеров типа OrderListFX
     */
    public ObservableList<OrderFX> searchForNameUser(String name) {
        OrderListFX listOrder = new OrderListFX();
        WeakReference<OrderListFX> weakReference = new WeakReference<>(listOrder);
        String str = "SELECT * FROM orders" +
                " LEFT JOIN users ON (orders.user_id=users.id_users)" +
                " WHERE users.last_name LIKE '%" + name + "%'";
        logger.debug("Отправляем запрос в БД ->\n -> " + str);
        try (PreparedStatement preparedStatement = DatabaseConnection.getDatabaseConnection().prepareStatement(str)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            logger.debug("Перебираем полученные данные из БД");
            while (resultSet.next()) {
                FillingInList(listOrder, resultSet);
            }
        } catch (SQLException | IOException e) {
            logger.warn("Произошла ошибка подключения к БД или ошибка записи/чтение данных из БД\n\t" + e);
            e.printStackTrace();
        }
        logger.debug("Возвращаем заполненный лист ордеров  ->\n -> " + listOrder);
        return listOrder.getOrderData();
    }

    /**
     * Метод удаляет заказ и все его позиции. Метод ничего не возвращает.
     * <p>
     * ВАЖНО!: По возможности добавить транзакцию, но надо подумать необходима ли она
     *
     * @param order - принимает на вход объект типа Ордер, который необходимо удалить
     * @param list  - лист со всеми позициями данного ордера
     */
    @Override
    public void delete(Order order, List<ProductFX> list) {

        // Добавить транзакцию
        String SQL = "DELETE FROM orders WHERE id =?;";
        logger.debug("Отправляем запрос в БД ->\n -> " + SQL);
        try (Connection connection = DatabaseConnection.getDatabaseConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {
            preparedStatement.setInt(1, order.getId());
            logger.debug("Создаем объект типа ProductDAO для того, что бы очистить список позиций: " + list);
            ProductDAO getProduct = new ProductDAO();
            for (ProductFX product : list) {
                logger.debug("Передаю позицию на удаление -> " + product);
                getProduct.delete(product);
            }
            logger.debug("Метод был выполнен успешно!");
            preparedStatement.executeUpdate();
        } catch (SQLException | IOException e) {
            logger.warn("Произошла ошибка подключения к БД или ошибка записи/чтение данных из БД\n\t" + e);
            e.printStackTrace();
        }
    }

    /**
     * Метод создает новый ордер в БД. Для создания новых позиций в БД используется заранее заполненный лист позиций,
     * для него вызывается метод createProduct в классе ProductDAO
     * В методе используется транзакция в БД
     * <p>
     * ВОПРОС: Три try в одном методе
     * ВАЖНО!: Надо проверить работу транзакции, возможно в этом методе она неправильно работает с передачей ID,
     * возможно надо изучить области действия
     *
     * @param order принимает на вход объект типа Ордер дл создания нового объекта в БД
     * @param list  лист со всеми позициями данного ордера
     */
    @Override
    public void add(Order order, List<Product> list) { // лист приходит заполненым только имя и количество id создать

        try (Connection connection = DatabaseConnection.getDatabaseConnection();) {
            logger.debug("Создание Savepoint для транзакции");
            connection.setAutoCommit(false);
            Savepoint savepointOne = connection.setSavepoint("SavepointOne");
            logger.debug("Отправляем запрос в БД ->\n -> " + ADD_NEW_ORDER);
            try (PreparedStatement preparedStatement = connection.prepareStatement(ADD_NEW_ORDER)) {
                //создаю новый ордер
                preparedStatement.setString(1, order.getOrderDescription());
                preparedStatement.setInt(2, order.getUserId());
                preparedStatement.setInt(3, order.getMachinesId());
                preparedStatement.setString(4, order.getOrderDate());
                preparedStatement.setString(5, order.getNumberOrder());
                preparedStatement.executeUpdate();
                int lastInsertId = 0;
                logger.debug("Отправляем запрос в БД ->\n -> " + LAST_INDEX_ID);
                try (Statement statement = connection.createStatement();) {
                    ResultSet resultSet = statement.executeQuery(LAST_INDEX_ID);
                    if (resultSet.next()) {
                        lastInsertId = resultSet.getInt(1);
                        logger.debug("Сохраняю последний созданный ID ордера -> " + lastInsertId);
                    }
                } catch (SQLException e) {
                    logger.warn("Произошла ошибка подключения к БД или ошибка записи/чтение данных из БД\n\t" + e);
                    e.printStackTrace();
                }
                //Проблема с этой транзакцией, если ее ставить в конце метода то не срабатывает
                //  передоваемое значение во второй вызываемый метод
                connection.commit();
                logger.debug("Создаю новый объект ProductDAO и вызываю метод создания позиций в БД из листа");
                ProductDAO productDAO = new ProductDAO();
                productDAO.createProduct(list, lastInsertId);
            } catch (Exception e) {
                logger.warn("Произошла ошибка транзакции -> возвращение на savepoint \n\t " +
                        "SQLException. Executing rollback to savepoint...");
                connection.rollback(savepointOne);
            }
            logger.debug("Метод был выполнен успешно!");
            connection.setAutoCommit(true);
        } catch (SQLException | IOException e) {
            logger.warn("Произошла ошибка подключения к БД или ошибка записи/чтение данных из БД\n\t" + e);
            e.printStackTrace();
        }
    }

    /**
     * Метод заполняет лист OrderListFX из ResultSet
     *
     * @param listOrder лист заказов
     * @param resultSet ResultSet
     */
    @Override
    public void FillingInList(OrderListFX listOrder, ResultSet resultSet) {
        try {
            logger.debug("Выполняем метод заполнения листа  FillingInList");
            listOrder.create(resultSet.getInt("id"), resultSet.getString("number_order"),
                    resultSet.getString("order_description"), resultSet.getString("machine"),
                    resultSet.getString("first_name"), resultSet.getString("last_name"),
                    resultSet.getString("order_date"));
        } catch (SQLException e) {
            logger.warn("Произошла ошибка подключения к БД или ошибка записи/чтение данных из БД\n\t" + e);
            throw new RuntimeException(e);
        }
    }



    public void UpdateOrder() {

    }
}


