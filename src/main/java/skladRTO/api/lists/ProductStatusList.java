package skladRTO.api.lists;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import skladRTO.api.models.ProductStatus;

public class ProductStatusList {
    private static ObservableList<ProductStatus> observableList = FXCollections.observableArrayList();

    public static void create(int id, String status){
        observableList.add(new ProductStatus(id, status));
    }

    public static Integer equalsStatusId(String status){
        for(ProductStatus status1: observableList){
            if(status1.getStatus().equals(status)){
                return status1.getId();
            }
        } return null;
    }

    public static ObservableList<ProductStatus> getObservableList() {
        return observableList;
    }

    public static void setObservableList(ObservableList<ProductStatus> observableList) {
        ProductStatusList.observableList = observableList;
    }
}
