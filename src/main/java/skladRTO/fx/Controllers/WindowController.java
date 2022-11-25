package skladRTO.fx.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import skladRTO.fx.sceneFX.CreateScene;

import java.net.URL;
import java.util.ResourceBundle;


public class WindowController implements Initializable {
    @FXML
    private Button Add_user;
    @FXML
    private Button button_prixod;
    @FXML
    private Button Delete_user;
    @FXML
    private Button Go_to_order;
   @FXML
    private Button Just_button;
    @FXML
    private Label Lvl_user;
    @FXML
    private Label Name_User;
    @FXML
    private Button view_users;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        CreateScene createScene = new CreateScene();
        button_prixod.setOnAction(actionEvent -> createScene.createScene("Prixod.fxml", "Привет урод", 400, 400));

        Go_to_order.setOnAction(actionEvent -> goToOrder(createScene));

        Add_user.setOnAction(actionEvent -> addUser(createScene));

        view_users.setOnAction(actionEvent -> getViewUsers(createScene));
    }

    public void getViewUsers(CreateScene createScene) {
        view_users.getScene().getWindow();
        createScene.createScene("List_Users.fxml", "Список пользователей", 900, 500);
    }

    public void addUser(CreateScene createScene) {
        Add_user.getScene().getWindow();
        createScene.createScene("Registration.fxml", "Регистрация", 550, 370);
    }

    public void goToOrder(CreateScene createScene) {
        Go_to_order.getScene().getWindow().hide();
        createScene.createScene("OrderView.fxml", "Список заказов", 800, 600);
    }
}
