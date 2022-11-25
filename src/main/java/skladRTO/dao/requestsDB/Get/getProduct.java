package skladRTO.dao.requestsDB.Get;

import javafx.collections.ObservableList;
import skladRTO.dao.connectDB.DatabaseConnection;
import skladRTO.dao.modelDAO.FillingInListsDAO;
import skladRTO.api.models.lists.ListProductOrder;
import skladRTO.api.models.FX.ProductFX;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class getProduct implements FillingInListsDAO<ListProductOrder> {

    public ObservableList<ProductFX> showListOfProducts(Integer id) {
        ListProductOrder listProduct = new ListProductOrder();
        WeakReference<ListProductOrder> weakReference = new WeakReference<>(listProduct);
        try {
            PreparedStatement preparedStatement = DatabaseConnection.getDatabaseConnection().prepareStatement(
                    "SELECT * FROM order_product " +
                    " LEFT JOIN orders ON (order_product.order_id=orders.id)" +
                    " LEFT JOIN order_status ON (order_product.status_id=order_status.id)" +
                    " WHERE order_product.order_id = ?;");

            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                FillingInList(listProduct, resultSet);
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
        return listProduct.getProductData();
    }

    @Override
    public void FillingInList(ListProductOrder listProduct, ResultSet rs) throws SQLException {
        listProduct.create(rs.getInt("id_product"), rs.getString("name_product"), rs.getInt("amount"),
                rs.getString("status"));
    }
}
