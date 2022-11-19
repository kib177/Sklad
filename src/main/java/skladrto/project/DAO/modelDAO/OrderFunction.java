package skladrto.project.DAO.modelDAO;

import javafx.collections.ObservableList;
import skladrto.project.Model.FX.OrderFX;
import skladrto.project.Model.Order;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface OrderFunction<T, K> {
    public ObservableList<T> showListOfOrders();

    public default ObservableList<T> searchName(String orderDescription) {
        return null;
    }

    public default ObservableList<T> searchNumber(String numberOrder) {
        return null;
    }

    public void delete(int id);

    public void add(Order order);

    void FillingInList(K listOrder, ResultSet rs) throws SQLException;
}
