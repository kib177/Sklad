package skladRTO.api.FX.models;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.util.Objects;

public class ProductFX {
    private int id;
    private SimpleStringProperty name;
    private SimpleIntegerProperty amount;
    private SimpleIntegerProperty status;
    private SimpleIntegerProperty order;
    private SimpleIntegerProperty productInfo;

    public ProductFX(SimpleIntegerProperty id, String name, int amount, int status, int order, int productInfo) {
        this.id = id.getValue();
        this.name = new SimpleStringProperty(name);
        this.amount = new SimpleIntegerProperty(amount);
        this.status = new SimpleIntegerProperty(status);
        this.order = new SimpleIntegerProperty(order);
        this.productInfo = new SimpleIntegerProperty(productInfo);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getAmount() {
        return amount.get();
    }

    public SimpleIntegerProperty amountProperty() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount.set(amount);
    }

    public int getStatus() {
        return status.get();
    }

    public SimpleIntegerProperty statusProperty() {
        return status;
    }

    public void setStatus(int status) {
        this.status.set(status);
    }

    public int getOrder() {
        return order.get();
    }

    public SimpleIntegerProperty orderProperty() {
        return order;
    }

    public void setOrder(int order) {
        this.order.set(order);
    }

    public int getProductInfo() {
        return productInfo.get();
    }

    public SimpleIntegerProperty productInfoProperty() {
        return productInfo;
    }

    public void setProductInfo(int productInfo) {
        this.productInfo.set(productInfo);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductFX productFX = (ProductFX) o;
        return Objects.equals(id, productFX.id) && Objects.equals(name, productFX.name) && Objects.equals(amount, productFX.amount) && Objects.equals(status, productFX.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, amount, status);
    }

    @Override
    public String toString() {
        return  id +
                " название: " + name +
                " количество: " + amount +
                " статус: " + status;
    }
}
