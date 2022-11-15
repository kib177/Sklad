package skladrto.project.List;

import skladrto.project.Model.Order;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ListOrder {
    private ObservableList<Order> orderData = FXCollections.observableArrayList();

    public void create(int id, String product_article, String name, int amount, String order_description, String last_name, String equipment,
                        String status, String subdivision, String order_date) {
        orderData.add(new Order(id, product_article, name, amount, order_description, last_name,equipment, status, subdivision, order_date));
    }

    public ObservableList<Order> getOrderData() {
        return orderData;
    }

    public void setOrderData(ObservableList<Order> orderData) {
        this.orderData = orderData;
    }
}
