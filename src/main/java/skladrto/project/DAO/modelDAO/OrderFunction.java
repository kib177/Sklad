package skladrto.project.DAO.modelDAO;

import javafx.collections.ObservableList;
import skladrto.project.Model.Order;

public interface OrderFunction {
    public ObservableList<Order> showListOfOrders();

    public ObservableList<Order> searchName(String productName);

    public ObservableList<Order> searchArticle(String articleProduct);

    public ObservableList<Order> searchSubdivision(String subdivision);

}
