package skladRTO.api.model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import skladRTO.api.FX.models.UserFX;

public class OrderFX {
    private SimpleIntegerProperty id;
    private SimpleStringProperty description;
    private UserFX user;
    private SimpleStringProperty date;
    private SimpleIntegerProperty number;


}
