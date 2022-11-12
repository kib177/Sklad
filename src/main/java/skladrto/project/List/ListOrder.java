package skladrto.project.List;

import skladrto.project.Model.Order;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ListOrder {
    private ObservableList<Order> orderData = FXCollections.observableArrayList();

    public void create(int id, String product_article, int amount, String order_description, String user,
                       String subdivision, String status, String date) {
        orderData.add(new Order(id, product_article, amount, order_description, user, subdivision, status, date));
    }

    public ObservableList<Order> getOrderData() {
        return orderData;
    }

    public void setOrderData(ObservableList<Order> orderData) {
        this.orderData = orderData;
    }
}
