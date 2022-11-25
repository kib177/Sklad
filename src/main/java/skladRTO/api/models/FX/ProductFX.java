package skladRTO.api.models.FX;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.util.Objects;

public class ProductFX {
    private SimpleIntegerProperty id;
    private SimpleStringProperty name;
    private SimpleIntegerProperty amount;
    private SimpleStringProperty status;

    public ProductFX(int id, String name, int amount, String status) {
        this.id = new SimpleIntegerProperty(id);
        this.name = new SimpleStringProperty(name);
        this.amount = new SimpleIntegerProperty(amount);
        this.status = new SimpleStringProperty(status);
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

    public String getStatus() {
        return status.get();
    }

    public SimpleStringProperty statusProperty() {
        return status;
    }

    public void setStatus(String status) {
        this.status.set(status);
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
                ", name=" + name +
                ", amount=" + amount +
                ", status=" + status;
    }
}
