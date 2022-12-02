package skladRTO.dao.interfaces;

import javafx.collections.ObservableList;
import skladRTO.api.FX.models.ProductFX;
import skladRTO.api.models.Order;
import skladRTO.api.models.Product;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface OrderFunction<T, K> {
    public ObservableList<T> showListOfOrders();

    public ObservableList<T> searchName(String orderDescription);

    public ObservableList<T> searchNumber(String numberOrder);

    public void delete(Order order, List<ProductFX> list);

    public void add(Order order, List<Product> list);

    void FillingInList(K listOrder, ResultSet rs) throws SQLException;
}
