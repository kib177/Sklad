package skladRTO.fx.Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import skladRTO.ApachePoiDemo;
import skladRTO.api.FX.models.ProductFX;
import skladRTO.api.models.Machines;
import skladRTO.api.models.Order;
import skladRTO.api.models.Product;
import skladRTO.api.models.Units;
import skladRTO.dao.modelDAO.MachinesDAO;
import skladRTO.dao.modelDAO.OrdersDAO;
import skladRTO.dao.modelDAO.UnitsDAO;

import java.lang.ref.SoftReference;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ProductNewController implements Initializable {
    private List<Product> list = new ArrayList<>();
    private MachinesDAO machinesDAO = new MachinesDAO();
    private UnitsDAO unitsDAO = new UnitsDAO();
    private Stage stage = new Stage();
    ObservableList<ProductFX> listFX = FXCollections.observableArrayList();
    private Order order;
    @FXML
    private TableColumn<?, ?> ColumnProduct_amount;
    @FXML
    private TableColumn<?, ?> ColumnProduct_name;
    @FXML
    private TableColumn<?, ?> Column_Machine;

    @FXML
    private TableView<ProductFX> Table_Items;
    @FXML
    private Button button_add;
    @FXML
    private Button Button_gone;
    @FXML
    private TextField Product_amount;
    @FXML
    private TextArea Product_name;
    @FXML
    private ChoiceBox<Units> ChoiceBox_Units;
    @FXML
    private ChoiceBox<Machines> ChoiceBox_Machine;
    @FXML
    private Label warning;
    private OrderViewController orderViewController;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        button_add.setOnAction(actionEvent -> ProductAdd());
        Button_gone.setOnAction(actionEvent -> gone());
        ChoiceBox_Machine.setItems(machinesDAO.getMachineName());
        ChoiceBox_Machine.setValue(machinesDAO.getMachineName().get(10));
        ChoiceBox_Units.setItems(unitsDAO.getUnits());
        ChoiceBox_Units.setValue(unitsDAO.getUnits().get(0));
    }

    public void getOrderView(OrderViewController orderViewController){
        this.orderViewController = orderViewController;
    }
    public void viewAddProduct() {
        ColumnProduct_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        ColumnProduct_amount.setCellValueFactory(new PropertyValueFactory<>("AmountUnits"));
        Column_Machine.setCellValueFactory(new PropertyValueFactory<>("machine"));
        Table_Items.setItems(listFX);
    }

    public void ProductAdd() {
        list.add(new Product(Product_name.getText(), Product_amount.getText(), ChoiceBox_Machine.getValue().getId(), ChoiceBox_Units.getValue().getId()));
        listFX.add(new ProductFX(Product_name.getText(), Product_amount.getText(), ChoiceBox_Machine.getValue().getName(), ChoiceBox_Units.getValue().getName()));
        viewAddProduct();
        Product_name.setText("");
        Product_amount.setText("");
    }
public void getSceneOrderNewController(Stage stage){
        this.stage = stage;
}
    public void getOrder(Order order) {
        this.order = order;
    }

    public void gone() {
        if (list.size() == 0) {
            warning.visibleProperty().setValue(true);
        } else {
            OrdersDAO OrdersDAO = new OrdersDAO();
            SoftReference<OrdersDAO> weakReference = new SoftReference<>(OrdersDAO);
            OrdersDAO.add(order, list);
            button_add.getScene().getWindow().hide();
            stage.getScene().getWindow().hide();
            ApachePoiDemo apachePoiDemo = new ApachePoiDemo();
            apachePoiDemo.CreateWord(listFX, order.getOrderDescription()+".docx", order.getUserId());

            orderViewController.Watch_order(new ActionEvent());
        }
    }


}