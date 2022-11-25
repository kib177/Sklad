package skladRTO.fx.Controllers;

import java.lang.ref.WeakReference;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import skladRTO.dao.requestsDB.Get.getUserDAO;
import skladRTO.fx.sceneFX.CreateScene;

public class AuthorizationController implements Initializable {
    @FXML
    private Button SingIn_ButSingIn;
    @FXML
    private TextField SingIn_login;
    @FXML
    private PasswordField SingIn_password;
    @FXML
    private Label SMS;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    public void showAdminWindow() {
        SingIn_ButSingIn.getScene().getWindow().hide();
        CreateScene createScene = new CreateScene();
        createScene.createScene("OrderView.fxml", "Заказы", 800, 600);
    }

    @FXML
    public void SingIn_ButSingIn(ActionEvent actionEvent) {
        getUserDAO userDAO = new getUserDAO();
        WeakReference<getUserDAO> weakReference = new WeakReference<>(userDAO);
        if (userDAO.CheckUsers(SingIn_login.getText(), SingIn_password.getText())) {
            showAdminWindow();
        } else SMS.setText("Пользователь не найден!");
    }
}
