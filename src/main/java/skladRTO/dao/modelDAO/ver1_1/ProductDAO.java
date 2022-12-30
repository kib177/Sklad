package skladRTO.dao.modelDAO.ver1_1;

import javafx.collections.ObservableList;
import skladRTO.api.FX.lists.ProductListFX;
import skladRTO.api.FX.models.ProductFX;
import skladRTO.api.lists.ProductStatusList;
import skladRTO.dao.connectDB.DatabaseConnection;
import skladRTO.dao.interfaces.FillingInListsDAO;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductDAO implements FillingInListsDAO<ProductListFX> {

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
        listProduct.create(rs.getInt("id_product"), rs.getString("name_product"), rs.getString("machine"), rs.getInt("amount"),
                rs.getInt("status_id"), rs.getInt("order_id"), rs.getInt("product_info_id"), rs.getString("name"));
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
}

