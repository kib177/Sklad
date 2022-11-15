/* класс окна AdminWindow.fxml отображает кнопки с добавлением просмотром пользователей, переход к заказам.
Никаких уловий и функциональности кроме перехода по окнам нет.
 */
package skladrto.project;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AdminWindowController {
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button Add_user;

    @FXML
    private Button Delete_user;

    @FXML
    private Button Go_to_order;

    @FXML
    private Button Just_button;

    @FXML
    private Label Lvl_user;

    @FXML
    private Label Name_User;

    @FXML
    private Button view_users;

    @FXML
    void initialize() {
        Go_to_order.setOnAction(actionEvent -> {
            goToOrder();
        });

        Add_user.setOnAction(actionEvent -> {
            addUser();
        });

        view_users.setOnAction(actionEvent -> {
            getViewUsers();
        });
    }

    public void getViewUsers(){ // просмотреть пользователей
        view_users.getScene().getWindow().centerOnScreen();

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("List_Users.fxml"));

        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root, 1200, 500));
        stage.show();
    }

    public void addUser(){ // регистрация пользователя
        Add_user.getScene().getWindow().centerOnScreen();

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("Registration.fxml"));

        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root, 800, 400));
        stage.show();
    }

    public void goToOrder(){ // просмотреть заказы
        Go_to_order.getScene().getWindow().centerOnScreen();

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("OrderView.fxml"));

        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root, 1400, 700));
        stage.show();
    }
}
