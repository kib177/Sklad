package skladRTO.fx.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import skladRTO.api.models.Authorization;
import skladRTO.api.models.Order;
import skladRTO.api.models.User;
import skladRTO.dao.modelDAO.MachinesDAO;
import skladRTO.dao.modelDAO.UserDAO;
import skladRTO.fx.sceneFX.CreateScene;

import java.lang.ref.WeakReference;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;

public class OrderNewController implements Initializable {
    private static final Logger logger = LogManager.getLogger(OrderNewController.class.getName());
    private CreateScene createScene = new CreateScene();
    private UserDAO userDAO = new UserDAO();
    private Stage stage = new Stage();
    @FXML
    private Button button_next;
    @FXML
    private TextField order_date;
    @FXML
    private TextField order_number;
    @FXML
    private TextArea order_proper;
    @FXML
    private ChoiceBox<User> order_zakazchik;
    @FXML
    private Label warning;
    private OrderViewController orderViewController;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Date currentDate = new Date();
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault());
        MachinesDAO machinesDAO = new MachinesDAO();
        WeakReference<MachinesDAO> weakReference = new WeakReference<>(machinesDAO);
        order_date.setText(dateFormat.format(currentDate));

        if (!Authorization.getStatusUser().getStatus().equals("Администратор")) {
            order_zakazchik.setValue(Authorization.getUser());
            logger.debug("Доступ администратора");
        } else {
            order_zakazchik.disableProperty().setValue(false);
            viewUserChoiceBox();
        }
    }

    public void setOrderViewController(OrderViewController orderViewController) {
        this.orderViewController = orderViewController;
    }

    private void viewUserChoiceBox() {
        WeakReference<UserDAO> weakReference = new WeakReference<>(userDAO);
        order_zakazchik.setItems(userDAO.showListName());
    }

    @FXML
    public void button_next(ActionEvent actionEvent) {
        if (order_proper.getText().equals("")) {
            warning.setText("Введите описание!");
            logger.debug("Не ввели описание заказа");
        } else if (order_number.getText().equals("")) {
            warning.setText("Введите номер заказа");
            logger.debug("Не ввели номер заказа");
        } else {
            Order order = new Order(order_proper.getText(), order_zakazchik.getValue().getId(), order_date.getText(),
                    order_number.getText());
            logger.info("Создан объект заказа");
            logger.debug("Переход к окну с заполнением позиций");
            createScene.createScene("Product_add.fxml", 750, 400, false);
            createScene.getStage().setAlwaysOnTop(true);
            ProductNewController controller = createScene.getLoader().getController();
            controller.getOrder(order);
            controller.getOrderView(orderViewController);
            controller.getSceneOrderNewController(stage);

            cleanUpString();
        }
    }

    public void getSceneProductNewController(Stage stage){
        this.stage= stage;
    }
    private void cleanUpString() {
        logger.debug("Отчистка строк в окне создания заказа");
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault());
        Date currentDate = new Date();
        order_date.setText(dateFormat.format(currentDate));
        order_proper.setText("");
        order_number.setText("");
    }
}

