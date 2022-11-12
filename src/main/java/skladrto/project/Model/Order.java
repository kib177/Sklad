/* обьекте с параметрами соответствующие данным заказа из бд
стандартный набор get set и др методов
 */
package skladrto.project.Model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import java.util.Objects;

public class Order { // тип данных нужно наследовать от класса SimpleТИПProperty для работы с таблицами javaFX
    private SimpleIntegerProperty id;
    private SimpleStringProperty product_articul;
    private SimpleIntegerProperty amount;
    private SimpleStringProperty order_description;
    private SimpleStringProperty user;
    private SimpleStringProperty subdivision;
    private SimpleStringProperty status;
    private SimpleStringProperty date;

    public Order(int id, String product_articul, int amount, String order_description,
                 String user, String subdivision, String status, String date) {
        this.id = new SimpleIntegerProperty(id);
        this.product_articul = new SimpleStringProperty(product_articul);
        this.amount = new SimpleIntegerProperty(amount);
        this.order_description = new SimpleStringProperty(order_description);
        this.user = new SimpleStringProperty(user);
        this.subdivision = new SimpleStringProperty(subdivision);
        this.status = new SimpleStringProperty(status);
        this.date = new SimpleStringProperty(date);
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

    public String getProduct_articul() {
        return product_articul.get();
    }

    public SimpleStringProperty product_articulProperty() {
        return product_articul;
    }

    public void setProduct_articul(String product_articul) {
        this.product_articul.set(product_articul);
    }

    public int getAmount() {
        return amount.get();
    }

    public SimpleIntegerProperty amountProperty() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount.set(amount);
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

    public String getUser() {
        return user.get();
    }

    public SimpleStringProperty userProperty() {
        return user;
    }

    public void setUser(String user) {
        this.user.set(user);
    }

    public String getSubdivision() {
        return subdivision.get();
    }

    public SimpleStringProperty subdivisionProperty() {
        return subdivision;
    }

    public void setSubdivision(String subdivision) {
        this.subdivision.set(subdivision);
    }

    public String getStatus() {
        return status.get();
    }

    public SimpleStringProperty statusProperty() {
        return status;
    }

    public void setStatus(String status) {
        this.status.set(status);
    }

    public String getDate() {
        return date.get();
    }

    public SimpleStringProperty dateProperty() {
        return date;
    }

    public void setDate(String date) {
        this.date.set(date);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(id, order.id) && Objects.equals(product_articul, order.product_articul) && Objects.equals(amount, order.amount) && Objects.equals(order_description, order.order_description) && Objects.equals(user, order.user) && Objects.equals(subdivision, order.subdivision) && Objects.equals(status, order.status) && Objects.equals(date, order.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, product_articul, amount, order_description, user, subdivision, status, date);
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", product_articul=" + product_articul +
                ", amount=" + amount +
                ", order_description=" + order_description +
                ", user=" + user +
                ", subdivision=" + subdivision +
                ", status=" + status +
                ", date=" + date +
                '}';
    }
}
