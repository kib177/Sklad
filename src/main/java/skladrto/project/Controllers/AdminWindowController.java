package skladrto.project.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;


public class AdminWindowController {

    @FXML
    private Button Add_user;

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

    @FXML
    void initialize() {

        Go_to_order.setOnAction(actionEvent -> goToOrder());

        Add_user.setOnAction(actionEvent -> addUser());

        view_users.setOnAction(actionEvent -> getViewUsers());
    }

    public void getViewUsers() {
        view_users.getScene().getWindow();

        CreateScene createScene = new CreateScene();
        createScene.createScene("List_Users.fxml", "Список пользователей", 1200, 500);

    }

    public void addUser() {
        Add_user.getScene().getWindow();

        CreateScene createScene = new CreateScene();
        createScene.createScene("Registration.fxml", "Регистрация", 800, 400);
    }

    public void goToOrder() {
        Go_to_order.getScene().getWindow();

        CreateScene createScene = new CreateScene();
        createScene.createScene("OrderView.fxml", "Список заказов", 800, 600);
    }
}
