package skladRTO.dao.modelDAO;

import javafx.collections.ObservableList;
import skladRTO.api.FX.lists.ProductInfoListFX;
import skladRTO.api.FX.models.ProductInfoFX;
import skladRTO.dao.connectDB.DatabaseConnection;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductInfoDAO {

    public ObservableList<ProductInfoFX> showListOfProducts(Integer id) {
        ProductInfoListFX listProduct = new ProductInfoListFX();
        WeakReference<ProductInfoListFX> weakReference = new WeakReference<>(listProduct);
        try {
            PreparedStatement preparedStatement = DatabaseConnection.getDatabaseConnection().prepareStatement(
                    "SELECT * FROM product_info " +
                            " LEFT JOIN order_product ON (order_product.product_info_id=product_info.id)" +
                            " WHERE order_product.id_product = ?;");

            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                FillingInList(listProduct, resultSet);
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
        return listProduct.getProductInfoFX();
    }


    public void FillingInList(ProductInfoListFX listProduct, ResultSet rs) throws SQLException {
        listProduct.create(rs.getInt("id"), rs.getString("description"),
                rs.getString("articul"), rs.getString("arrival_date"));
    }

    public int getIdProduct_info(int id) {
        try (PreparedStatement preparedStatement = DatabaseConnection.getDatabaseConnection().prepareStatement(
                "SELECT * FROM product_info " +
                        " LEFT JOIN order_product ON product_info.id=order_product.product_info_id" +
                        " WHERE order_product.id_product = ?;");
        ) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("id");
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
        return 0;
    }
}

