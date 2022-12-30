package skladRTO.api.FX.models;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import skladRTO.api.lists.ProductStatusList;
import skladRTO.api.models.ProductStatus;

import java.util.Objects;

public class ProductFX {
    private SimpleIntegerProperty id;
    private SimpleStringProperty name;
    private SimpleIntegerProperty amount;
    private SimpleStringProperty machine;
    private SimpleStringProperty AmountUnits;
    private SimpleStringProperty status;
    private Integer statusFX;
    private SimpleIntegerProperty order;
    private SimpleIntegerProperty productInfo;
    private SimpleStringProperty units;


    public ProductFX(int id, String name, String machine, int amount, int status, int order, int productInfo, String units) {
        this.id = new SimpleIntegerProperty(id);
        this.name = new SimpleStringProperty(name);
        this.machine = new SimpleStringProperty(machine);
        this.amount = new SimpleIntegerProperty(amount);
        this.status = getStringStatus(status);
        this.order = new SimpleIntegerProperty(order);
        this.productInfo = new SimpleIntegerProperty(productInfo);
        this.AmountUnits = new SimpleStringProperty(amount + " " + units);
        this.units = new SimpleStringProperty(units);
    }

    public ProductFX(String name, String number, String machine, String units) {
        this.name = new SimpleStringProperty(name);
        this.machine = new SimpleStringProperty(machine);
        this.AmountUnits = new SimpleStringProperty(number+" " + units);

    }

    public SimpleStringProperty getStringStatus(int stat) {
        for (ProductStatus status : ProductStatusList.getObservableList()) {
            if (status.getId().equals(stat)) {
                return new SimpleStringProperty(status.getStatus());
            }
        }
        return null;
    }

    public String getUnits() {
        return units.get();
    }

    public SimpleStringProperty unitsProperty() {
        return units;
    }

    public void setUnits(String units) {
        this.units.set(units);
    }

    public String getAmountUnits() {
        return AmountUnits.get();
    }

    public SimpleStringProperty amountUnitsProperty() {
        return AmountUnits;
    }

    public void setAmountUnits(String amountUnits) {
        this.AmountUnits.set(amountUnits);
    }

    public String getMachine() {
        return machine.get();
    }

    public SimpleStringProperty machineProperty() {
        return machine;
    }

    public void setMachine(String machine) {
        this.machine.set(machine);
    }

    public Integer getStatusFX() {
        return statusFX;
    }

    public void setStatusFX(Integer statusFX) {
        this.statusFX = statusFX;
        this.status = getStringStatus(statusFX);
    }

    public ProductFX() {
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
        return Objects.equals(id, productFX.id) && Objects.equals(name, productFX.name) && Objects.equals(amount, productFX.amount) && Objects.equals(machine, productFX.machine) && Objects.equals(status, productFX.status) && Objects.equals(statusFX, productFX.statusFX) && Objects.equals(order, productFX.order) && Objects.equals(productInfo, productFX.productInfo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, amount, machine, status, statusFX, order, productInfo);
    }

    @Override
    public String toString() {
        return "ProductFX{" +
                "id=" + id +
                ", name=" + name +
                ", amount=" + amount +
                ", machine=" + machine +
                ", status=" + status +
                ", statusFX=" + statusFX +
                ", order=" + order +
                ", productInfo=" + productInfo +
                '}';
    }
}
