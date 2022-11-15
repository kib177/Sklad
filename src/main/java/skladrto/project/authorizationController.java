package skladrto.project;

import java.io.IOException;
import java.lang.ref.WeakReference;
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
import skladrto.project.RequestsDB.Get.getUserDAO;

public class authorizationController {
    private getUserDAO userDAO;

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
        SingIn_ButSingIn.setOnAction(actionEvent -> {
            choiceUserOrAdmin();
        });
    }

    public void showAdminWindow() { // окно для админа
        SingIn_ButSingIn.getScene().getWindow().hide();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("AdminWindow.fxml"));

        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root, 600, 400));
        stage.show();

    }

    public void showUserWindow() { // окно для смертных
        SingIn_ButSingIn.getScene().getWindow().hide();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("UsersWindow.fxml"));

        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root, 600, 400));
        stage.show();
    }

    public void choiceUserOrAdmin() { // проверка смертный или пидор
        userDAO = new getUserDAO();
        WeakReference<getUserDAO> weakReference = new WeakReference<>(userDAO);
        if (userDAO.CheckUsers(SingIn_login.getText(), SingIn_password.getText()).equals("админ")) {
            showAdminWindow();
        } else if (userDAO.CheckUsers(SingIn_login.getText(), SingIn_password.getText()).equals("юзер")) {
            showUserWindow();
        } else SMS.setText("Пользователь не найден!");
    }
}
