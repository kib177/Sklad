package skladrto.project.List;

import skladrto.project.Model.Order;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.Objects;

public class ListOrder {
    private ObservableList<Order> orderData = FXCollections.observableArrayList();

    public void create(int id, int number_order, String order_description, String user, String order_date) {
        orderData.add(new Order(id,number_order, order_description, user, order_date));
    }

    public ObservableList<Order> getOrderData() {
        return orderData;
    }

    public void setOrderData(ObservableList<Order> orderData) {
        this.orderData = orderData;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ListOrder listOrder = (ListOrder) o;
        return Objects.equals(orderData, listOrder.orderData);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderData);
    }

    @Override
    public String toString() {
        return "ListOrder{" +
                "orderData=" + orderData +
                '}';
    }
}
