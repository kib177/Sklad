package skladrto.project;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import skladrto.project.RequestsDB.Get.UserDAO;

public class authorizationController {
    private UserDAO userDAO;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

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
        SingIn_ButSingIn.setOnAction(actionEvent -> { // нажатие кнопки войти

            if (userDAO.CheckUsers(SingIn_login.getText(), SingIn_password.getText())) { // проверка пользователя в бд
                SingIn_ButSingIn.getScene().getWindow().hide();
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("User.fxml"));

                try {
                    loader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Parent root = loader.getRoot();
                Stage stage = new Stage();
                stage.setScene(new Scene(root, 600, 400));
                stage.show();

            } else SMS.setText("Пользователь не найден!");
        });
    }
}
