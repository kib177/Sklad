package skladRTO.fx.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import skladRTO.dao.modelDAO.UserDAO;
import skladRTO.fx.sceneFX.CreateScene;

import java.net.URL;
import java.util.ResourceBundle;

public class UserDeleteController implements Initializable {
    private UserDAO userDAO = new UserDAO();
    private CreateScene createScene = new CreateScene();
    @FXML
    private Button Gone;
    @FXML
    private TextField ID;
    @FXML
    private Label Label;
    private UsersViewController usersViewController;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    public void getUsersController(UsersViewController usersViewController){
        this.usersViewController = usersViewController;
    }
    @FXML
    public void Gone(ActionEvent event) {
        if (ID.getText().equals("")) {
            Label.setText("Введите ID пользователя!");
        } else if (userDAO.pereborID(Integer.parseInt(ID.getText()))){
            createScene.createScene("Window.fxml", 400, 200);
            createScene.getStage().setAlwaysOnTop(true);
            ((WindowController) createScene.getLoader().getController()).deleteUser(Integer.parseInt(ID.getText()));
            WindowController controller = createScene.getLoader().getController();
            controller.getUsersController(usersViewController);
        } else{
            Label.setText("Проверьте правильность ID!");
        }
    }
}
