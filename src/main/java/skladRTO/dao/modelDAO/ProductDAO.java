package skladRTO.dao.modelDAO;

import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;
import skladRTO.api.FX.lists.ProductStatistic;
import skladRTO.api.FX.models.ProductFX;
import skladRTO.api.lists.ProductStatusList;
import skladRTO.api.models.Product;
import skladRTO.api.models.ProductStatus;
import skladRTO.dao.connectDB.DatabaseConnection;
import skladRTO.dao.interfaces.FillingInListsDAO;
import skladRTO.api.FX.lists.ProductListFX;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.sql.*;
import java.util.List;

public class ProductDAO implements FillingInListsDAO<ProductListFX> {

    public static final String ADD_NEW_PRODUCT = "INSERT INTO order_product (name_product,amount,status_id,order_id,product_info_id)" +
            "VALUES(?,?,?,?,?);";
    public static final String LAST_INSERT_ID = "SELECT LAST_INSERT_ID();";

    public ObservableList<ProductFX> showListOfProducts(Integer id) {
        ProductListFX listProduct = new ProductListFX();
        WeakReference<ProductListFX> weakReference = new WeakReference<>(listProduct);
        try {
            PreparedStatement preparedStatement = DatabaseConnection.getDatabaseConnection().prepareStatement(
                    "SELECT * FROM order_product " +
                            " LEFT JOIN orders ON (order_product.order_id=orders.id)" +
                            " WHERE order_product.order_id = ?;");

            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                FillingInList(listProduct, resultSet);
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
        return listProduct.getProductList();
    }

    @Override
    public void FillingInList(ProductListFX listProduct, ResultSet rs) throws SQLException {
        listProduct.create(rs.getInt("id_product"), rs.getString("name_product"), rs.getInt("amount"),
                rs.getInt("status_id"), rs.getInt("order_id"), rs.getInt("product_info_id"));
    }

    public void getProductStatus() {

        try {
            ResultSet rs = DatabaseConnection.getStatement().executeQuery("SELECT * FROM order_status");

            while (rs.next()) {
                ProductStatusList.create(rs.getInt("id"), rs.getString("status"));
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }

    public int getIdStatus(String status) {
        int id = 0;
        try (PreparedStatement preparedStatement = DatabaseConnection.getDatabaseConnection().prepareStatement(
                "SELECT * FROM order_status WHERE order_status.status = ?;");
        ) {
            preparedStatement.setString(1, status);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                id = resultSet.getInt("id");
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
        return id;
    }

    /**
     * Метод удаляет продукт по его id, но сперва удаляет объект product_info который на него ссылается
     *
     * @param product - принимает на вход объект типа продукт (позиция)
     */
    public void delete(ProductFX product) {
        // возможно необходимо добавить транзакцию!
        String SQL = "DELETE FROM order_product WHERE id_product =?;";
        String SQL1 = "DELETE FROM product_info WHERE id =?;";
        try (Connection connection = DatabaseConnection.getDatabaseConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {
            preparedStatement.setInt(1, product.getId());
            preparedStatement.executeUpdate();
            try (PreparedStatement preparedStatement1 = connection.prepareStatement(SQL1)) {
                preparedStatement1.setInt(1, product.getProductInfo());

                preparedStatement1.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }

    public ObservableList<ProductFX> getProduct(int id) {
        ProductListFX listProduct = new ProductListFX();
        WeakReference<ProductListFX> weakReference = new WeakReference<>(listProduct);
        try {
            PreparedStatement preparedStatement = DatabaseConnection.getDatabaseConnection().prepareStatement(
                    "SELECT * FROM order_product " +
                            " LEFT JOIN order_status ON (order_product.status_id=order_status.id)" +
                            " WHERE order_product.status_id = ?;");

            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                FillingInList(listProduct, resultSet);
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
        return listProduct.getProductList();
    }

    public ObservableList<PieChart.Data> getStatistic() {
        int num1 = 0, num2 = 0, num3 = 0, num4 = 0;
        ProductStatistic listProduct = new ProductStatistic();
        WeakReference<ProductStatistic> weakReference = new WeakReference<>(listProduct);
        try {
            ResultSet rs = DatabaseConnection.getStatement().executeQuery(
                    "SELECT * FROM order_product " +
                            " LEFT JOIN order_status ON (order_product.status_id=order_status.id)");
            while (rs.next()) {
                String str = rs.getString("status");
                if (str.equals("ожидание")) {
                    num2++;
                } else if (str.equals("принят")) {
                    num1++;
                } else if (str.equals("доставлен")){
                    num3++;
                } else if (str.equals("отклонен")){
                    num4++;
                }
            }
            listProduct.create("Принятых"+" "+num1, "В ожидании"+" "+num2,"Доставленных"+" "+num3,
                    "Отклоненных"+" "+num4, num1, num2, num3, num4);
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
        return listProduct.getPieCharts();
    }

    /**
     * ВОПРОС: В try на одном statement у меня executeUpdate и executeQuery
     *
     * @param list
     * @param orderId
     */
    public void createProduct(List<Product> list, int orderId) {
        try (Connection connection = DatabaseConnection.getDatabaseConnection();
             PreparedStatement preparedStatement1 = connection.prepareStatement(ADD_NEW_PRODUCT)) {
            connection.setAutoCommit(false);
            Savepoint savepointOne = connection.setSavepoint("SavepointOne");
            int lastInsertId = 0;
            for (Product product : list) {
                //создание пустого продукт-инфо
                try (Statement statement = connection.createStatement();) {
                    statement.executeUpdate("INSERT INTO sklad.product_info () VALUES ();");
                    ResultSet resultSet = statement.executeQuery(LAST_INSERT_ID);
                    if (resultSet.next()) {
                        lastInsertId = resultSet.getInt(1);
                    }
                    preparedStatement1.setString(1, product.getNameProduct());
                    preparedStatement1.setInt(2, product.getAmount());
                    preparedStatement1.setInt(3, 1);
                    preparedStatement1.setInt(4, orderId);
                    preparedStatement1.setInt(5, lastInsertId);
                    preparedStatement1.addBatch();
                } catch (SQLException e) {
                    e.printStackTrace();
                    connection.rollback(savepointOne);
                }
            }
            preparedStatement1.executeBatch();
            connection.commit();
            connection.setAutoCommit(true);
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }

    public ObservableList<ProductFX> showAllProducts() {
        ProductListFX listProduct = new ProductListFX();
        WeakReference<ProductListFX> weakReference = new WeakReference<>(listProduct);
        try {
            ResultSet resultSet = DatabaseConnection.getStatement().executeQuery(
                    "SELECT * FROM order_product " +
                            " LEFT JOIN order_status ON (order_product.status_id=order_status.status)");
            while (resultSet.next()) {
                FillingInList(listProduct, resultSet);
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
        return listProduct.getProductList();
    }
}