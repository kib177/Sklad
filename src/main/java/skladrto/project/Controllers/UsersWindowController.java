package skladrto.project.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;


import java.io.IOException;

public class UsersWindowController {
    @FXML
    private Button Go_to_order;

    @FXML
    private Button Just_button;

    @FXML
    private Label Lvl_user;

    @FXML
    private Label Name_User;

    @FXML
    void initialize(){
        Go_to_order.setOnAction(actionEvent -> getGoToOrder());
    }

    public void getGoToOrder(){
        Go_to_order.getScene().getWindow().centerOnScreen();

        CreateScene createScene = new CreateScene();
        createScene.createScene("OrderView.fxml", "Список заказов", 800, 500);

    }
}
