package skladRTO.api.FX.lists;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import skladRTO.api.FX.models.ProductInfoFX;

public class ProductInfoListFX {
    private ObservableList<ProductInfoFX> productInfoFX = FXCollections.observableArrayList();

    public void create(int id, String des, String articul, String date){
        productInfoFX.add(new ProductInfoFX(id, des, articul, date));
    }

    public ObservableList<ProductInfoFX> getProductInfoFX() {
        return productInfoFX;
    }

    public void setProductInfoFX(ObservableList<ProductInfoFX> productInfoFX) {
        this.productInfoFX = productInfoFX;
    }
}
