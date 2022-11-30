package skladRTO.api.FX.lists;

import skladRTO.api.FX.models.OrderFX;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.Objects;

public class OrderListFX {
    private ObservableList<OrderFX> orderFXData = FXCollections.observableArrayList();

    public void create(int id, int number_order, String order_description, String user, String order_date) {
        orderFXData.add(new OrderFX(id,number_order, order_description, user, order_date));
    }

    public ObservableList<OrderFX> getOrderData() {
        return orderFXData;
    }

    public void setOrderData(ObservableList<OrderFX> orderFXData) {
        this.orderFXData = orderFXData;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderListFX listOrder = (OrderListFX) o;
        return Objects.equals(orderFXData, listOrder.orderFXData);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderFXData);
    }

    @Override
    public String toString() {
        return "ListOrder{" +
                "orderData=" + orderFXData +
                '}';
    }
}
