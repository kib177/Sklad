package skladrto.project.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import skladrto.project.Model.Product;

import java.util.Objects;

public class ListProductOrder {
    private ObservableList<Product> ProductData = FXCollections.observableArrayList();

    public void create(int id, String name, int amount, String status) {
        ProductData.add(new Product(id, name, amount, status));
    }

    public ObservableList<Product> getProductData() {
        return ProductData;
    }

    public void setProductData(ObservableList<Product> productData) {
        ProductData = productData;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ListProductOrder that = (ListProductOrder) o;
        return Objects.equals(ProductData, that.ProductData);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ProductData);
    }

    @Override
    public String toString() {
        return "ListProductOrder{" +
                "ProductData=" + ProductData +
                '}';
    }
}
