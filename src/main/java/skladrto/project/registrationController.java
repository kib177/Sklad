package skladrto.project;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import skladrto.project.RequestsDB.Add.addUser;
import skladrto.project.Registration.CheckData;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class registrationController {

    @FXML
    private Button SingUp_ButttonGone;

    @FXML
    private TextField SingUp_Email;

    @FXML
    private TextField SingUp_Name;

    @FXML
    private Button SingUp_back;

    @FXML
    private TextField SingUp_lastName;

    @FXML
    private TextField SingUp_login;

    @FXML
    private PasswordField SingUp_password;

    @FXML
    private PasswordField SingUp_secondPassword;

    @FXML
    private TextField Status;

    @FXML
    private TextField department;

    @FXML
    void initialize() {
        SingUp_back.setOnAction(actionEvent -> {
            SingUp_back.getScene().getWindow().hide();

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
        });

//        SingUp_ButttonGone.setOnAction(actionEvent -> {
//            CheckData checkData = new CheckData(); // тут должна быть проверка на введены ли все данные, она то работает но не так как хотелось
//            if (checkData.data(SingUp_Name.getText(), SingUp_lastName.getText(), SingUp_login.getText(),
//                    SingUp_Email.getText(), SingUp_password.getText(), SingUp_secondPassword.getText()).equals("")) {
//                addUser.add(SingUp_Name.getText(), SingUp_lastName.getText(), SingUp_login.getText(),
//                        SingUp_Email.getText(), SingUp_password.getText(), Status.getText(), department.getText());
//            }
//
//        });
    }
}