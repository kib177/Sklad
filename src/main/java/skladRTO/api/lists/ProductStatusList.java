package skladRTO.api.lists;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import skladRTO.api.models.ProductStatus;

public class ProductStatusList {
    private ObservableList<ProductStatus> observableList = FXCollections.observableArrayList();

    public void create(String status){
        observableList.add(new ProductStatus(status));
    }

    public ObservableList<ProductStatus> getObservableList() {
        return observableList;
    }

    public void setObservableList(ObservableList<ProductStatus> observableList) {
        this.observableList = observableList;
    }
}
