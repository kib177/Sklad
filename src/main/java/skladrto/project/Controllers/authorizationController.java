package skladrto.project.Controllers;

import java.lang.ref.WeakReference;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import skladrto.project.RequestsDB.Get.getUserDAO;

public class authorizationController {
    private getUserDAO userDAO;
    @FXML
    private Button SingIn_ButSingIn;

    @FXML
    private Button SingIn_ButSingUp;

    @FXML
    private TextField SingIn_login;

    @FXML
    private PasswordField SingIn_password;

    @FXML
    private Label SMS;

    @FXML
    void initialize() {

        SingIn_ButSingIn.setOnAction(actionEvent -> choiceUserOrAdmin());
    }

    public void showAdminWindow() {
        SingIn_ButSingIn.getScene().getWindow().hide();
        CreateScene createScene = new CreateScene();
        createScene.createScene("AdminWindow.fxml", "Админ", 600, 400);

    }

    public void showUserWindow() {
        SingIn_ButSingIn.getScene().getWindow().hide();
        CreateScene createScene = new CreateScene();
        createScene.createScene("UsersWindow.fxml", "Юзер-лох", 600, 400);

    }

    public void choiceUserOrAdmin() {
        userDAO = new getUserDAO();
        WeakReference<getUserDAO> weakReference = new WeakReference<>(userDAO);
        if (userDAO.CheckUsers(SingIn_login.getText(), SingIn_password.getText()).equals("админ")) {
            showAdminWindow();
        } else if (userDAO.CheckUsers(SingIn_login.getText(), SingIn_password.getText()).equals("юзер")) {
            showUserWindow();
        } else SMS.setText("Пользователь не найден!");
    }
}
