package skladRTO.api.FX.models;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import skladRTO.api.lists.ProductStatusList;
import skladRTO.api.models.ProductStatus;

import java.util.Objects;

public class ProductFX {
    private SimpleIntegerProperty id;
    private SimpleStringProperty name;
    private SimpleIntegerProperty amount;
    private SimpleStringProperty status;
    private SimpleIntegerProperty order;
    private SimpleIntegerProperty productInfo;

    public ProductFX(int id, String name, int amount, int status, int order, int productInfo) {
        this.id = new SimpleIntegerProperty(id);
        this.name = new SimpleStringProperty(name);
        this.amount = new SimpleIntegerProperty(amount);
        this.status = getStringStatus(status);
        this.order = new SimpleIntegerProperty(order);
        this.productInfo = new SimpleIntegerProperty(productInfo);
    }

    public SimpleStringProperty getStringStatus(int stat){
        for(ProductStatus status: ProductStatusList.getObservableList()){
            if(status.getId().equals(stat)) {
                return new SimpleStringProperty(status.getStatus());
            }
        } return null;
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
