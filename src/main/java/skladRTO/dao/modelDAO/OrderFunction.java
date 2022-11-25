package skladRTO.dao.modelDAO;

import javafx.collections.ObservableList;
import skladRTO.api.models.Order;
import skladRTO.api.models.ProductAdd;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface OrderFunction<T, K> {
    public ObservableList<T> showListOfOrders();

    public default ObservableList<T> searchName(String orderDescription) {
        return null;
    }

    public default ObservableList<T> searchNumber(String numberOrder) {
        return null;
    }

    public void delete(int id);

    public void add(Order order, List<ProductAdd> list);

    void FillingInList(K listOrder, ResultSet rs) throws SQLException;
}
