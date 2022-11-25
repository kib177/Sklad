package skladRTO.fx.Controllers;

import javafx.fxml.Initializable;
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
    private Order order;
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
        button_gone.setOnAction(actionEvent -> {
            try {
                buttonGone();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public void buttonGone() throws IOException {
        this.order = new Order(order_proper.getText(), order_zakazchik.getText(), order_date.getText(), order_number.getText());
        CreateScene createScene = new CreateScene();
        createScene.createScene("Product_add.fxml", "Заказ позиции", 600, 400);
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

