/* обьекте с параметрами соответствующие данным заказа из бд
стандартный набор get set и др методов
 */
package skladrto.project.Model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.util.Objects;

public class Order {
    private SimpleIntegerProperty id;
    private SimpleStringProperty product_articul;
    private SimpleStringProperty name;
    private SimpleIntegerProperty amount;
    private SimpleStringProperty order_description;
    private SimpleStringProperty last_name;
    private SimpleStringProperty equipment;
    private SimpleStringProperty subdivision;
    private SimpleStringProperty status;
    private SimpleStringProperty order_date;

    public Order(int id, String product_articul, String name, int amount, String order_description,
                 String last_name, String equipment, String status, String subdivision, String order_date) {
        this.id = new SimpleIntegerProperty(id);
        this.product_articul = new SimpleStringProperty(product_articul);
        this.name = new SimpleStringProperty(name);
        this.amount = new SimpleIntegerProperty(amount);
        this.order_description = new SimpleStringProperty(order_description);
        this.last_name = new SimpleStringProperty(last_name);
        this.equipment = new SimpleStringProperty(equipment);
        this.subdivision = new SimpleStringProperty(subdivision);
        this.status = new SimpleStringProperty(status);
        this.order_date = new SimpleStringProperty(order_date);
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

    public String getName() {
        return name.get();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getLast_name() {
        return last_name.get();
    }

    public SimpleStringProperty last_nameProperty() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name.set(last_name);
    }

    public String getEquipment() {
        return equipment.get();
    }

    public SimpleStringProperty equipmentProperty() {
        return equipment;
    }

    public void setEquipment(String equipment) {
        this.equipment.set(equipment);
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(id, order.id) && Objects.equals(product_articul, order.product_articul) && Objects.equals(name, order.name) && Objects.equals(amount, order.amount) && Objects.equals(order_description, order.order_description) && Objects.equals(last_name, order.last_name) && Objects.equals(equipment, order.equipment) && Objects.equals(subdivision, order.subdivision) && Objects.equals(status, order.status) && Objects.equals(order_date, order.order_date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, product_articul, name, amount, order_description, last_name, equipment, subdivision, status, order_date);
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", product_articul=" + product_articul +
                ", name=" + name +
                ", amount=" + amount +
                ", order_description=" + order_description +
                ", last_name=" + last_name +
                ", equipment=" + equipment +
                ", subdivision=" + subdivision +
                ", status=" + status +
                ", order_date=" + order_date +
                '}';
    }
}
