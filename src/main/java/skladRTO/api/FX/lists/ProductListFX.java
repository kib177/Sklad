package skladRTO.api.FX.lists;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import skladRTO.api.FX.models.ProductFX;
import skladRTO.api.models.Product;

public class ProductListFX {
    private ObservableList<Product> productList = FXCollections.observableArrayList();

    public void create(int id, String name, int amount, int status, int order, int productInfo) {
        productList.add(new Product(new SimpleIntegerProperty(id), new SimpleStringProperty(name),
                new SimpleIntegerProperty(amount), new SimpleIntegerProperty(status), new SimpleIntegerProperty(order),
                new SimpleIntegerProperty(productInfo)));
    }

    public ObservableList<Product> getProductList() {
        return productList;
    }

    public void setProductList(ObservableList<Product> productList) {
        this.productList = productList;
    }

    @Override
    public String toString() {
        return "ProductListFX{" +
                "productDataFX=" + productList +
                '}';
    }
}
