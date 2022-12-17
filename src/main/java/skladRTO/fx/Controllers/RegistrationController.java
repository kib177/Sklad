package skladRTO.fx.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import skladRTO.api.models.StatusUser;
import skladRTO.dao.modelDAO.UserDAO;

import java.net.URL;
import java.util.ResourceBundle;

public class RegistrationController implements Initializable {
    private UserDAO getUserDAO = new UserDAO();
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
    private UsersViewController usersController;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ChoiceBox_status.setItems(getUserDAO.getStatus());
    }

    public void getOrderView(UsersViewController usersViewController) {
        this.usersController = usersViewController;
    }

    @FXML
    public void SingUp_ButttonGone(ActionEvent actionEvent) {
        getUserDAO.add(SingUp_Name.getText(), SingUp_lastName.getText(), SingUp_login.getText(),
                SingUp_Email.getText(), SingUp_password.getText(), String.valueOf(ChoiceBox_status.getValue()));
        usersController.showUsers();
        SingUp_ButttonGone.getScene().getWindow().hide();
    }

    @FXML
    public void SingUp_back() {
        SingUp_back.getScene().getWindow().hide();
    }


}