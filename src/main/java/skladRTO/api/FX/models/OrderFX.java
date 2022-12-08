/* обьекте с параметрами соответствующие данным заказа из бд
стандартный набор get set и др методов
 */
package skladRTO.api.FX.models;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.util.Objects;

public class OrderFX {
    private SimpleIntegerProperty id;
    private SimpleStringProperty number_order;
    private SimpleStringProperty order_description;
    private SimpleStringProperty user;
    private SimpleStringProperty order_date;

    public OrderFX(int id, String number_order, String order_description, String user, String order_date) {
        this.id = new SimpleIntegerProperty(id);
        this.number_order = new SimpleStringProperty(number_order);
        this.order_description = new SimpleStringProperty(order_description);
        this.user = new SimpleStringProperty(user);
        this.order_date = new SimpleStringProperty(order_date);
    }

    public String getNumber_order() {
        return number_order.get();
    }

    public SimpleStringProperty number_orderProperty() {
        return number_order;
    }

    public void setNumber_order(String number_order) {
        this.number_order.set(number_order);
    }

    public int getId() {
        return id.get();
    }

    public SimpleIntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getOrder_description() {
        return order_description.get();
    }

    public SimpleStringProperty order_descriptionProperty() {
        return order_description;
    }

    public void setOrder_description(String order_description) {
        this.order_description.set(order_description);
    }

    public String getOrder_date() {
        return order_date.get();
    }

    public SimpleStringProperty order_dateProperty() {
        return order_date;
    }

    public void setOrder_date(String order_date) {
        this.order_date.set(order_date);
    }

    public String getUser() {
        return user.get();
    }

    public SimpleStringProperty userProperty() {
        return user;
    }

    public void setUser(String user) {
        this.user.set(user);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderFX orderFX = (OrderFX) o;
        return Objects.equals(id, orderFX.id) && Objects.equals(number_order, orderFX.number_order) && Objects.equals(order_description, orderFX.order_description) && Objects.equals(user, orderFX.user) && Objects.equals(order_date, orderFX.order_date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, number_order, order_description, user, order_date);
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", number_order=" + number_order +
                ", order_description=" + order_description +
                ", user=" + user +
                ", order_date=" + order_date +
                '}';
    }
}
