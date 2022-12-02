package skladRTO.fx.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import skladRTO.api.models.StatusUser;
import skladRTO.dao.modelDAO.addUser;
import skladRTO.dao.modelDAO.UserDAO;
import skladRTO.fx.sceneFX.CreateScene;

import java.net.URL;
import java.util.ResourceBundle;

public class RegistrationController implements Initializable {
    @FXML
    private ChoiceBox<StatusUser> ChoiceBox_status;
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        UserDAO getUserDAO = new UserDAO();
        addUser addUser = new addUser();
        ChoiceBox_status.setItems(getUserDAO.getStatus());

        SingUp_back.setOnAction(actionEvent -> buttonBack());

        SingUp_ButttonGone.setOnAction(actionEvent -> {
                addUser.add(SingUp_Name.getText(), SingUp_lastName.getText(), SingUp_login.getText(),
                        SingUp_Email.getText(), SingUp_password.getText(), String.valueOf(ChoiceBox_status.getValue()));
                SingUp_ButttonGone.getScene().getWindow().hide();
        });
    }

    public void buttonBack(){
        SingUp_back.getScene().getWindow().hide();

        CreateScene createScene = new CreateScene();
        createScene.createScene("AdminWindow.fxml", 600, 400);

    }


}