package skladRTO.dao.modelDAO;

import javafx.collections.ObservableList;
import skladRTO.api.lists.ProductStatusList;
import skladRTO.api.models.Product;
import skladRTO.api.models.ProductStatus;
import skladRTO.dao.connectDB.DatabaseConnection;
import skladRTO.dao.interfaces.FillingInListsDAO;
import skladRTO.api.FX.lists.ProductListFX;
import skladRTO.api.FX.models.ProductFX;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class getProduct implements FillingInListsDAO<ProductListFX> {

    public ObservableList<Product> showListOfProducts(Integer id) {
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

    public ObservableList<ProductStatus> getProductStatus() {
        ProductStatusList list = new ProductStatusList();
        try {
            ResultSet rs = DatabaseConnection.getStatement().executeQuery("SELECT order_status.status FROM order_status");

            while (rs.next()) {
                list.create(rs.getString("status"));
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
        return list.getObservableList();
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
}
