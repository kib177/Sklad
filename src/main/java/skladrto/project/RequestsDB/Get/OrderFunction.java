package skladrto.project.RequestsDB.Get;

import javafx.collections.ObservableList;
import skladrto.project.Model.Order;

public interface OrderFunction {
    public ObservableList<Order> showListOfOrders();

    public void createNewOrder();

    public void searchName();

    public void searchArticle();

    public void searchSubdivision();

}
