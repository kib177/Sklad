package skladRTO.fx.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import skladRTO.dao.modelDAO.UserDAO;
import skladRTO.api.FX.models.UserFX;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import skladRTO.fx.sceneFX.CreateScene;

import java.lang.ref.WeakReference;
import java.net.URL;
import java.util.ResourceBundle;

public class UsersViewController implements Initializable {
    private UserDAO userDAO = new UserDAO();
    private CreateScene createScene = new CreateScene();
    @FXML
    private Button Button_back;
    @FXML
    private TableView<UserFX> Table_Users;
    @FXML
    private TableColumn<?, ?> column_email;
    @FXML
    private TableColumn<?, ?> column_first;
    @FXML
    private TableColumn<?, ?> column_id;
    @FXML
    private TableColumn<?, ?> column_login;
    @FXML
    private TableColumn<?, ?> column_second;
    @FXML
    private TableColumn<?, ?> column_status;
    @FXML
    private TableColumn<?, ?> colunm_password;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        showUsers();
    }

    public void showUsers() {
        WeakReference<UserDAO> weakReference = new WeakReference<>(userDAO);
        column_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        column_login.setCellValueFactory(new PropertyValueFactory<>("login"));
        colunm_password.setCellValueFactory(new PropertyValueFactory<>("password"));
        column_first.setCellValueFactory(new PropertyValueFactory<>("FirstName"));
        column_second.setCellValueFactory(new PropertyValueFactory<>("SecondName"));
        column_status.setCellValueFactory(new PropertyValueFactory<>("status"));
        column_email.setCellValueFactory(new PropertyValueFactory<>("email"));
        Table_Users.setItems(userDAO.showListOfUsers());
    }

    @FXML
    public void Button_addUser(ActionEvent actionEvent) {
        createScene.createScene("Registration.fxml", 550, 370, false);
        RegistrationController controller = createScene.getLoader().getController();
        controller.getOrderView(this);
        createScene.getStage().setAlwaysOnTop(true);
    }

    @FXML
    public void Button_deleteUser() {
        createScene.createScene("DeleteUser.fxml", 300, 100, false);
        UserDeleteController controller = createScene.getLoader().getController();
        controller.getUsersController(this);
        createScene.getStage().setAlwaysOnTop(true);
    }

    @FXML
    public void Button_back(ActionEvent actionEvent) {
        Button_back.getScene().getWindow().hide();
    }

    @FXML
    public void Replace(){
        showUsers();
    }
}
