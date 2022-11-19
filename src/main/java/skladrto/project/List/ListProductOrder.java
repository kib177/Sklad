package skladrto.project.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import skladrto.project.Model.FX.ProductFX;

import java.util.Objects;

public class ListProductOrder {
    private ObservableList<ProductFX> productFXData = FXCollections.observableArrayList();

    public void create(int id, String name, int amount, String status) {
        productFXData.add(new ProductFX(id, name, amount, status));
    }

    public ObservableList<ProductFX> getProductData() {
        return productFXData;
    }

    public void setProductData(ObservableList<ProductFX> productFXData) {
        this.productFXData = productFXData;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ListProductOrder that = (ListProductOrder) o;
        return Objects.equals(productFXData, that.productFXData);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productFXData);
    }

    @Override
    public String toString() {
        return "ListProductOrder{" +
                "ProductData=" + productFXData +
                '}';
    }
}
