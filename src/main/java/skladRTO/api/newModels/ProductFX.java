package skladRTO.api.newModels;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.util.Objects;

public class ProductFX implements ModelFX{
    private SimpleIntegerProperty id;
    private SimpleStringProperty name;
    private SimpleIntegerProperty amount;
    private StatusFX status;
    private OrderFX order;
    private ProductInfoFX productInfo;
    private MachineFX machine;
    private UnitFX unit;



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

    public StatusFX getStatus() {
        return status;
    }

    public void setStatus(StatusFX status) {
        this.status = status;
    }

    public OrderFX getOrder() {
        return order;
    }

    public void setOrder(OrderFX order) {
        this.order = order;
    }

    public ProductInfoFX getProductInfo() {
        return productInfo;
    }

    public void setProductInfo(ProductInfoFX productInfo) {
        this.productInfo = productInfo;
    }

    public MachineFX getMachine() {
        return machine;
    }

    public void setMachine(MachineFX machine) {
        this.machine = machine;
    }

    public UnitFX getUnit() {
        return unit;
    }

    public void setUnit(UnitFX unit) {
        this.unit = unit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductFX productFX = (ProductFX) o;
        return Objects.equals(id, productFX.id) && Objects.equals(name, productFX.name) && Objects.equals(amount, productFX.amount) && Objects.equals(status, productFX.status) && Objects.equals(order, productFX.order) && Objects.equals(productInfo, productFX.productInfo) && Objects.equals(machine, productFX.machine) && Objects.equals(unit, productFX.unit);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, amount, status, order, productInfo, machine, unit);
    }

    @Override
    public String toString() {
        return "ProductFX{" +
                "id=" + id +
                ", name=" + name +
                ", amount=" + amount +
                ", status=" + status +
                ", order=" + order +
                ", productInfo=" + productInfo +
                ", machine=" + machine +
                ", unit=" + unit +
                '}';
    }
}
