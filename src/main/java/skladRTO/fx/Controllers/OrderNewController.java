package skladRTO.fx.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import skladRTO.api.models.Authorization;
import skladRTO.api.models.Order;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import skladRTO.fx.sceneFX.CreateScene;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class OrderNewController implements Initializable {
    @FXML
    private Button button_gone;
    @FXML
    private TextField order_date;
    @FXML
    private TextField order_number;
    @FXML
    private TextArea order_proper;
    @FXML
    private TextField order_zakazchik;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
//        order_zakazchik.setText(Authorization.getUser().getFerstName() + " " + Authorization.getUser().getLastName());

    }

    @FXML
    public void button_gone(ActionEvent actionEvent){

        Order order = new Order(order_proper.getText(), order_date.getText(), order_number.getText());
        CreateScene createScene = new CreateScene();
        createScene.createScene("Product_add.fxml", 600, 400);
        ((ProductNewController) createScene.getLoader().getController()).createOrder(order);
        cleanUpString();
    }

    public void cleanUpString() {
        order_proper.setText("");
        order_zakazchik.setText("");
        order_date.setText("");
        order_number.setText("");
    }
}

