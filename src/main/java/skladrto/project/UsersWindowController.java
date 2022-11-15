package skladrto.project;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class UsersWindowController {
    @FXML
    private Button Go_to_order;

    @FXML
    private Button Just_button;

    @FXML
    private Label Lvl_user;

    @FXML
    private Label Name_User;

    @FXML
    void initialize(){
        Go_to_order.setOnAction(actionEvent -> {
            getGoToOrder();
        });
    }

    public void getGoToOrder(){
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
