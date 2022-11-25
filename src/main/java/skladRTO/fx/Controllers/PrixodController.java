package skladRTO.fx.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import skladRTO.api.models.FX.ProductFX;

import java.net.URL;
import java.util.ResourceBundle;

public class PrixodController implements Initializable {
    @FXML
    private TextField articul;
    @FXML
    private TextField data;
    @FXML
    private TextArea description;
    @FXML
    private Label label_product;
    @FXML
    private ChoiceBox<?> status;

    @Override
    public void initialize(URL location, ResourceBundle resources){
    }

    public void toStringProduct (ProductFX productFX){
        label_product.setText(String.valueOf(productFX));
    }
}
