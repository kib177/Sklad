package skladRTO.fx.Controllers;

import java.lang.ref.WeakReference;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import skladRTO.api.models.Authorization;
import skladRTO.fx.sceneFX.CreateScene;

public class AuthorizationController implements Initializable {
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
        Authorization avtoriz = new Authorization();
        WeakReference<Authorization> weakReference = new WeakReference<>(avtoriz);
        if (avtoriz.CheckUsers(SingIn_login.getText(), SingIn_password.getText())) {

            // проверка статик переменных
           // System.out.println(Avtoriz.getAuth() + "\n" + Avtoriz.getStatusUser() + "\n" + Avtoriz.getUser());


            CreateScene createScene = new CreateScene();
            createScene.createScene("OrderView.fxml", 800, 600);
            SingIn_ButSingIn.getScene().getWindow().hide();
        } else SMS.setText("Пользователь не найден!");
    }
}