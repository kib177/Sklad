package skladRTO.dao.modelDAO;

import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import skladRTO.api.FX.lists.ProductListFX;
import skladRTO.api.FX.lists.ProductStatistic;
import skladRTO.api.FX.models.ProductFX;
import skladRTO.api.lists.ProductStatusList;
import skladRTO.api.models.Product;
import skladRTO.api.models.ProductInfo;
import skladRTO.dao.connectDB.DatabaseConnection;
import skladRTO.dao.interfaces.FillingInListsDAO;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.sql.*;
import java.util.List;

public class ProductDAO implements FillingInListsDAO<ProductListFX> {
    private static final Logger logger = LogManager.getLogger(ProductDAO.class.getName());
    public static final String ADD_NEW_PRODUCT = "INSERT INTO order_product (name_product,amount,status_id,order_id,product_info_id)" +
            "VALUES(?,?,?,?,?);";
    public static final String LAST_INSERT_ID = "SELECT LAST_INSERT_ID();";
    public static final String UPDATE_PRODUCT = "UPDATE order_product" +
            " SET status_id = ? WHERE id_product = ?;";
    public static final String UPDATE_PRODUCT_INFO = "UPDATE product_info SET articul = ?," +
            " arrival_date = ?, description = ? WHERE id = ?";
    public static final String SELECT_FROM_ORDER_PRODUCT = "SELECT * FROM order_product " +
            " LEFT JOIN orders ON (order_product.order_id=orders.id)" +
            " WHERE order_product.order_id = ?;";
    public static final String SELECT_FROM_ORDER_STATUS = "SELECT * FROM order_status";
    public static final String ORDER_STATUS_STATUS = "SELECT * FROM order_status WHERE order_status.status = ?;";
    public static final String GET_PRODUCT_BY_STATUS = "SELECT * FROM order_product " +
            " LEFT JOIN order_status ON (order_product.status_id=order_status.id)" +
            " WHERE order_product.status_id = ?;";

    /**
     * Метод заполняет лист позиций из БД по ID ордера
     *
     * @param id ID ордера
     * @return
     */
    public ObservableList<ProductFX> showListOfProducts(Integer id) {
        ProductListFX listProduct = new ProductListFX();
        WeakReference<ProductListFX> weakReference = new WeakReference<>(listProduct);
        logger.debug("Отправляем запрос в БД ->\n -> " + SELECT_FROM_ORDER_PRODUCT);
        try {
            PreparedStatement preparedStatement = DatabaseConnection.getDatabaseConnection().prepareStatement(
                    SELECT_FROM_ORDER_PRODUCT);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            logger.debug("Перебираем полученные данные из БД");
            while (resultSet.next()) {
                FillingInList(listProduct, resultSet);
            }
        } catch (SQLException | IOException e) {
            logger.warn("Произошла ошибка подключения к БД или ошибка записи/чтение данных из БД\n\t" + e);
            e.printStackTrace();
        }
        logger.debug("Возвращаем заполненный лист позиций ->\n -> " + listProduct);
        return listProduct.getProductList();
    }

    /**
     * Метод находит все статусы для ордеров из БД и сохраняет их в лист ProductStatusList
     */
    public void getProductStatus() {
        try {
            logger.debug("Отправляем запрос в БД ->\n -> " + SELECT_FROM_ORDER_STATUS);
            ResultSet rs = DatabaseConnection.getStatement().executeQuery(SELECT_FROM_ORDER_STATUS);
            logger.debug("Перебираем полученные данные из БД");
            while (rs.next()) {
                ProductStatusList.create(rs.getInt("id"), rs.getString("status"));
            }
        } catch (SQLException | IOException e) {
            logger.warn("Произошла ошибка подключения к БД или ошибка записи/чтение данных из БД\n\t" + e);
            e.printStackTrace();
        }
    }

    /**
     * Метод принимает на вход статус ордера типа String, ищет его в БД и возвращает его ID
     *
     * @param status Название статуса
     * @return ID статуса
     */
    public int getIdStatus(String status) {
        int id = 0;
        logger.debug("Отправляем запрос в БД ->\n -> " + ORDER_STATUS_STATUS);
        try (PreparedStatement preparedStatement = DatabaseConnection.getDatabaseConnection().prepareStatement(
                ORDER_STATUS_STATUS)) {
            preparedStatement.setString(1, status);
            ResultSet resultSet = preparedStatement.executeQuery();
            logger.debug("Получение данных из БД");
            if (resultSet.next()) {
                id = resultSet.getInt("id");
            }
        } catch (SQLException | IOException e) {
            logger.warn("Произошла ошибка подключения к БД или ошибка записи/чтение данных из БД\n\t" + e);
            e.printStackTrace();
        }
        logger.debug("Возвращаем полученный ID -> " + id);
        return id;
    }

    /**
     * Метод удаляет продукт по его id, но сперва удаляет объект product_info который на него ссылается
     * <p>
     * ВАЖНО!: По возможности добавить транзакцию, но надо подумать необходима ли она
     *
     * @param product - принимает на вход объект типа продукт (позиция)
     */
    public void delete(ProductFX product) {
        String SQL = "DELETE FROM order_product WHERE id_product =?;";
        String SQL1 = "DELETE FROM product_info WHERE id =?;";
        logger.debug("Отправляем запрос в БД на удаление позиции по ID: " + product.getId() + "->\n -> " + SQL);
        try (Connection connection = DatabaseConnection.getDatabaseConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {
            preparedStatement.setInt(1, product.getId());
            preparedStatement.executeUpdate();
            logger.debug("Отправляем запрос в БД на удаление product_info по ID: "
                    + product.getProductInfo() + "->\n -> " + SQL1);
            try (PreparedStatement preparedStatement1 = connection.prepareStatement(SQL1)) {
                preparedStatement1.setInt(1, product.getProductInfo());
                preparedStatement1.executeUpdate();
            } catch (SQLException e) {
                logger.warn("Произошла ошибка подключения к БД или ошибка записи/чтение данных из БД\n\t" + e);
                e.printStackTrace();
            }
        } catch (SQLException | IOException e) {
            logger.warn("Произошла ошибка подключения к БД или ошибка записи/чтение данных из БД\n\t" + e);
            e.printStackTrace();
        }
    }

    /**
     * Метод находит в БД позиции по статусу(ID статуса) и записывает их в лист типа ProductListFX
     *
     * @param id ID статуса
     * @return возвращает заполненный лист типа ProductListFX
     */
    public ObservableList<ProductFX> getProductByStatus(int id) {
        ProductListFX listProduct = new ProductListFX();
        WeakReference<ProductListFX> weakReference = new WeakReference<>(listProduct);
        try {
            logger.debug("Отправляем запрос в БД на поиск позиции по ее статусу через ID: " + id + "->\n -> "
                    + GET_PRODUCT_BY_STATUS);
            PreparedStatement preparedStatement = DatabaseConnection.getDatabaseConnection().prepareStatement(
                    GET_PRODUCT_BY_STATUS);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            logger.debug("Перебираем полученные данные из БД");
            while (resultSet.next()) {
                FillingInList(listProduct, resultSet);
            }
        } catch (SQLException | IOException e) {
            logger.warn("Произошла ошибка подключения к БД или ошибка записи/чтение данных из БД\n\t" + e);
            e.printStackTrace();
        }
        logger.debug("Возвращаем заполненный лист позиций ->\n -> " + listProduct);
        return listProduct.getProductList();
    }

    /**
     * Метод для сбора статистики по статусу заказов. Метод перебирает все позиции,
     * берет их статус и считает в цикле количество позиций по каждому статусу
     * ВАЖНО!: Переделать if-else через switch, он работает быстрее за счет того, чо обрабатывается вво время компиляции;
     * Возможно лучше сделать через GROUP BY и протестировать на скорость выполнения
     *
     * @return возвращает заполненный лист ProductStatistic
     */
    public ObservableList<PieChart.Data> getStatistic() {
        int num1 = 0, num2 = 0, num3 = 0, num4 = 0;
        String strSelect = "SELECT * FROM order_product " +
                " LEFT JOIN order_status ON (order_product.status_id=order_status.id)";
        ProductStatistic listProduct = new ProductStatistic();
        WeakReference<ProductStatistic> weakReference = new WeakReference<>(listProduct);
        try {
            logger.debug("Отправляем запрос в БД на поиск позиций по их статусу: " + "->\n -> " + strSelect);
            ResultSet rs = DatabaseConnection.getStatement().executeQuery(strSelect);
            logger.debug("Перебираем полученные данные и подсчитываем количество позиций по каждой группе");
            while (rs.next()) {
                String str = rs.getString("status");
                if (str.equals("ожидание")) {
                    num2++;
                } else if (str.equals("принят")) {
                    num1++;
                } else if (str.equals("доставлен")) {
                    num3++;
                } else if (str.equals("отклонен")) {
                    num4++;
                }
            }
            logger.debug("Заполняем лист типа ProductStatistic полученными данными");
            listProduct.create("Принятых" + " " + num1, "В ожидании" + " " + num2, "Доставленных"
                    + " " + num3, "Отклоненных" + " " + num4, num1, num2, num3, num4);
        } catch (SQLException | IOException e) {
            logger.warn("Произошла ошибка подключения к БД или ошибка записи/чтение данных из БД\n\t" + e);
            e.printStackTrace();
        }
        logger.debug("Возвращаем заполненный лист ProductStatistic ->\n -> " + listProduct);
        return listProduct.getPieCharts();
    }

    /**
     * Метод создает объект позиции в БД в цикле for, для этого он сначала создает пустой объект product_info,
     * затем возвращает его ID, и записывает его в объект позиции.
     * Метод использует транзакцию
     * <p>
     * ВОПРОС: В try на одном statement у меня executeUpdate и executeQuery
     *
     * @param list    лист позиций введенных пользователем
     * @param orderId ID ордера с данными позициями
     */
    public void createProduct(List<Product> list, int orderId) {
        String createProductInfo = "INSERT INTO sklad.product_info () VALUES ();";
        try (Connection connection = DatabaseConnection.getDatabaseConnection();
             PreparedStatement preparedStatement1 = connection.prepareStatement(ADD_NEW_PRODUCT)) {
            logger.debug("Создание Savepoint для транзакции");
            connection.setAutoCommit(false);
            Savepoint savepointOne = connection.setSavepoint("SavepointOne");
            int lastInsertId = 0;
            logger.debug("Начала цикла перебора листа позиций");
            for (Product product : list) {
                logger.debug("Отправляем запрос в БД для создания пустого product_info: ->\n -> "
                        + createProductInfo);
                try (Statement statement = connection.createStatement();) {
                    statement.executeUpdate(createProductInfo);
                    logger.debug("Отправляем запрос в БД для возвращение последнего ID: ->\n -> "
                            + createProductInfo);
                    ResultSet resultSet = statement.executeQuery(LAST_INSERT_ID);
                    if (resultSet.next()) {
                        lastInsertId = resultSet.getInt(1);
                        logger.debug("Сохраняем последний ID в переменную: " + lastInsertId);
                    }
                    logger.debug("Отправляем запрос в БД для создания позиции через addBatch()");
                    preparedStatement1.setString(1, product.getNameProduct());
                    preparedStatement1.setInt(2, product.getAmount());
                    preparedStatement1.setInt(3, 1);
                    preparedStatement1.setInt(4, orderId);
                    preparedStatement1.setInt(5, lastInsertId);
                    preparedStatement1.addBatch();
                } catch (Exception e) {
                    logger.warn("Произошла ошибка транзакции -> возвращение на savepoint \n\t " +
                            "SQLException. Executing rollback to savepoint...");
                    connection.rollback(savepointOne);
                }
            }
            logger.debug("Отправляем запрос в БД для записи всего листа позиций через executeBatch()");
            preparedStatement1.executeBatch();
            logger.debug("Транзакция выполнена успешно");
            connection.commit();
            connection.setAutoCommit(true);
        } catch (IOException | SQLException e) {
            logger.warn("Произошла ошибка подключения к БД или ошибка записи/чтение данных из БД\n\t" + e);
            e.printStackTrace();
        }
    }

    /**
     * Метод возвращает лист всех позиций, где ID статуса изменен на его имя
     *
     * @return возвращает заполненный лист типа ProductListFX
     */
    public ObservableList<ProductFX> showAllProducts() {
        ProductListFX listProduct = new ProductListFX();
        WeakReference<ProductListFX> weakReference = new WeakReference<>(listProduct);
        try {
            String str = "SELECT * FROM order_product " +
                    " LEFT JOIN order_status ON (order_product.status_id=order_status.status)";
            ResultSet resultSet = DatabaseConnection.getStatement().executeQuery(str);
            logger.debug("Отправляем запрос в БД на поиск всех позиций: ->\n -> " + str);
            while (resultSet.next()) {
                FillingInList(listProduct, resultSet);
            }
        } catch (SQLException | IOException e) {
            logger.warn("Произошла ошибка подключения к БД или ошибка записи/чтение данных из БД\n\t" + e);
            e.printStackTrace();
        }
        logger.debug("Возвращаем заполненный лист позиций ->\n -> " + listProduct);
        return listProduct.getProductList();
    }

    /**
     * Метод изменяет значения ProductFX и Product_Info в БД.
     * Метод использует транзакцию.
     *
     * @param product     Заполненный объект типа ProductFX
     * @param productInfo Заполненный объект типа ProductInfo
     */
    public void updateProduct(ProductFX product, ProductInfo productInfo) {
        //    Возможно стоит создать лист
        try (Connection connection = DatabaseConnection.getDatabaseConnection()) {
            logger.debug("Создание Savepoint для транзакции");
            connection.setAutoCommit(false);
            Savepoint savepointOne = connection.setSavepoint("SavepointOne");
            logger.debug("Отправляем запрос в БД на на изменение ProductInfo: ->\n -> " + UPDATE_PRODUCT_INFO);
            try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_PRODUCT_INFO)) {
                preparedStatement.setString(1, productInfo.getArticle());
                preparedStatement.setString(2, productInfo.getArrivalDate());
                preparedStatement.setString(3, productInfo.getDescription());
                preparedStatement.setInt(4, productInfo.getId());
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                logger.warn("Произошла ошибка подключения к БД или ошибка записи/чтение данных из БД\n\t" + e);
                e.printStackTrace();
                connection.rollback(savepointOne);
            }
            logger.debug("Отправляем запрос в БД на на изменение order_product: ->\n -> " + UPDATE_PRODUCT);
            try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_PRODUCT)) {
                preparedStatement.setInt(1, product.getStatusFX());
                preparedStatement.setInt(2, product.getId());
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                logger.warn("Произошла ошибка подключения к БД или ошибка записи/чтение данных из БД\n\t" + e);
                e.printStackTrace();
                connection.rollback(savepointOne);
            }
            logger.debug("Метод был выполнен успешно!");
            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException | IOException e) {
            logger.warn("Произошла ошибка подключения к БД или ошибка записи/чтение данных из БД\n\t" + e);
            e.printStackTrace();
        }
    }

    /**
     * Метод заполняет лист ProductListFX из ResultSet
     *
     * @param listProduct лист позиций
     * @param rs          ResultSet
     */
    @Override
    public void FillingInList(ProductListFX listProduct, ResultSet rs) {
        try {
            logger.debug("Выполняем метод заполнения листа  FillingInList");
            listProduct.create(rs.getInt("id_product"), rs.getString("name_product"), rs.getInt("amount"),
                    rs.getInt("status_id"), rs.getInt("order_id"), rs.getInt("product_info_id"));
        } catch (SQLException e) {
            logger.warn("Произошла ошибка подключения к БД или ошибка записи/чтение данных из БД\n\t" + e);
            throw new RuntimeException(e);
        }
    }
}