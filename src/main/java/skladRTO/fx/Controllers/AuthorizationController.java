package skladRTO.fx.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import skladRTO.dao.modelDAO.AuthorizationDAO;
import skladRTO.fx.sceneFX.CreateScene;

import java.lang.ref.WeakReference;
import java.net.URL;
import java.util.ResourceBundle;

public class AuthorizationController implements Initializable {
    private static final Logger logger = LogManager.getLogger(AuthorizationController.class.getName());
    @FXML
    Button SingIn_ButSingIn;
    @FXML
    private TextField SingIn_login;
    @FXML
    private PasswordField SingIn_password;
    @FXML
    private Label SMS;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    @FXML
    public void SingIn_ButSingIn(ActionEvent actionEvent) {
        logger.debug("Нажатие кнопки войти");
        AuthorizationDAO avtoriz = new AuthorizationDAO();
        WeakReference<AuthorizationDAO> weakReference = new WeakReference<>(avtoriz);
        if (avtoriz.CheckUsers(SingIn_login.getText(), SingIn_password.getText())) {
            CreateScene createScene = new CreateScene();
            createScene.createScene("OrderView.fxml", 920, 650, true);
            ((OrderViewController) createScene.getLoader().getController()).setCreateScene(createScene);
            SingIn_ButSingIn.getScene().getWindow().hide();
        } else {
            SMS.setText("Пользователь не найден!");
            logger.debug("Кнопка нажата, пользователь не вошел");
        }

    }

}
