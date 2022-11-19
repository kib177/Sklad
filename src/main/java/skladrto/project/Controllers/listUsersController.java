package skladrto.project.Controllers;

import skladrto.project.RequestsDB.Get.getUserDAO;
import skladrto.project.Model.FX.UserFX;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.lang.ref.WeakReference;

public class listUsersController {

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
    private TableView<UserFX> Table_Users;

    @FXML
    void initialize() {
        getUserDAO userDAO = new getUserDAO(); // подгрузка в таблицу данных
        WeakReference<getUserDAO> weakReference = new WeakReference<>(userDAO);
        showUsers();
        Table_Users.setItems(userDAO.showListOfUsers());
    }

    public void showUsers(){
        column_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        column_login.setCellValueFactory(new PropertyValueFactory<>("login"));
        colunm_password.setCellValueFactory(new PropertyValueFactory<>("password"));
        column_first.setCellValueFactory(new PropertyValueFactory<>("FirstName"));
        column_second.setCellValueFactory(new PropertyValueFactory<>("SecondName"));
        column_status.setCellValueFactory(new PropertyValueFactory<>("status"));
        column_email.setCellValueFactory(new PropertyValueFactory<>("email"));
        column_department.setCellValueFactory(new PropertyValueFactory<>("department"));
    }
}
