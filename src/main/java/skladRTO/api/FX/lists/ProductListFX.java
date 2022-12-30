package skladRTO.api.FX.lists;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import skladRTO.api.FX.models.ProductFX;

public class ProductListFX {
    private ObservableList<ProductFX> productList = FXCollections.observableArrayList();

    public void create(int id, String name, String machine, int amount, int status, int order, int productInfo, String units) {
        productList.add(new ProductFX(id, name, machine, amount, status, order, productInfo, units));
    }

    public ObservableList<ProductFX> getProductList() {
        return productList;
    }

    public void setProductList(ObservableList<ProductFX> productList) {
        this.productList = productList;
    }

    @Override
    public String toString() {
        return "ProductListFX{" +
                "productDataFX=" + productList +
                '}';
    }
}
