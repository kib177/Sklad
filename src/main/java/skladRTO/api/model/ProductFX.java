package skladRTO.api.model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class ProductFX {
    private SimpleIntegerProperty id;
    private SimpleStringProperty name;
    private SimpleIntegerProperty amount;
    private StatusFX status;
    private OrderFX order;
    private ProductInfoFX productInfo;
    private MachineFX machine;
    private UnitFX unit;

}
