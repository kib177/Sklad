package skladRTO.fx.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import skladRTO.api.models.Authorization;
import skladRTO.api.models.Order;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import skladRTO.fx.sceneFX.CreateScene;

import java.net.URL;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;

public class OrderNewController implements Initializable {
    private CreateScene createScene = new CreateScene();
    @FXML
    private Button button_next;
    @FXML
    private TextField order_date;
    @FXML
    private TextField order_number;
    @FXML
    private TextArea order_proper;
    @FXML
    private TextField order_zakazchik;
    @FXML
    private Label warning;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Date currentDate = new Date();
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault());
        SimpleDateFormat formatForDateNow = new SimpleDateFormat("yy-MMdd-hhmm");

        order_zakazchik.setText(Authorization.getUser().getFerstName() + " " + Authorization.getUser().getLastName());
        order_date.setText(dateFormat.format(currentDate));
        order_number.setText(Authorization.getUser().getId()+formatForDateNow.format(currentDate));
    }

    @FXML
    public void button_next(ActionEvent actionEvent) {
        if (order_proper.getText().equals("")) {
            warning.setText("Введите описание!");
        } else {
            Order order = new Order(order_proper.getText(), order_date.getText(), order_number.getText());
            createScene.createScene("Product_add.fxml", 480, 350);
            createScene.getStage().setAlwaysOnTop(true);
            ((ProductNewController) createScene.getLoader().getController()).createOrder(order);
            cleanUpString();
        }
    }

    public void cleanUpString() {
        SimpleDateFormat formatForDateNow = new SimpleDateFormat("yy-MMdd-hhmm");
        Date currentDate = new Date();
        order_proper.setText("");
        order_number.setText(Authorization.getUser().getId()+formatForDateNow.format(currentDate));
    }
}

