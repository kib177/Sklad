package skladrto.project;

import skladrto.project.RequestsDB.Get.UserDAO;
import skladrto.project.List.ListUser;
import skladrto.project.Model.User;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class listUsersController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableColumn<?, ?> column_department;

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

    @FXML
    private TableView<User> Table_Users;

    @FXML
    void initialize() {
//        getUser.readUsers();
        UserDAO userDAO = new UserDAO();
        userDAO.showListOfUsers();
        column_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        column_login.setCellValueFactory(new PropertyValueFactory<>("login"));
        colunm_password.setCellValueFactory(new PropertyValueFactory<>("password"));
        column_status.setCellValueFactory(new PropertyValueFactory<>("status"));
        column_first.setCellValueFactory(new PropertyValueFactory<>("FirstName"));
        column_second.setCellValueFactory(new PropertyValueFactory<>("SecondName"));
        column_email.setCellValueFactory(new PropertyValueFactory<>("email"));
        column_department.setCellValueFactory(new PropertyValueFactory<>("department"));
        Table_Users.setItems(userDAO.showListOfUsers()); // смотри OrderViewController такое же

    }
}