package skladRTO.fx.Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import skladRTO.api.FX.models.ProductFX;
import skladRTO.api.models.Order;
import skladRTO.api.models.Product;
import skladRTO.dao.modelDAO.OrdersDAO;

import java.lang.ref.SoftReference;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ProductNewController implements Initializable {
    private List<Product> list = new ArrayList<>();
    ObservableList<ProductFX> listFX = FXCollections.observableArrayList();
    private Order order;
    @FXML
    private TableColumn<?, ?> ColumnProduct_amount;

    @FXML
    private TableColumn<?, ?> ColumnProduct_id;

    @FXML
    private TableColumn<?, ?> ColumnProduct_name;

    @FXML
    private TableColumn<?, ?> ColumnProduct_status;
    @FXML
    private TableView<ProductFX> Table_Items;
    @FXML
    private Button button_add;
    @FXML
    private Button Button_gone;
    @FXML
    private TextField Product_amount;
    @FXML
    private TextField Product_name;
    @FXML
    private Label warning;
    private OrderViewController orderViewController;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        button_add.setOnAction(actionEvent -> ProductAdd(list));
        Button_gone.setOnAction(actionEvent -> gone(list));
    }

    public void getOrderView(OrderViewController orderViewController){
        this.orderViewController = orderViewController;
    }
    public void viewAddProduct() {
        ColumnProduct_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        ColumnProduct_amount.setCellValueFactory(new PropertyValueFactory<>("amount"));
        Table_Items.setItems(listFX);
    }

    public void ProductAdd(List<Product> list) {
        list.add(new Product(Product_name.getText(), Product_amount.getText()));
        listFX.add(new ProductFX(Product_name.getText(), Product_amount.getText()));
        viewAddProduct();
        Product_name.setText("");
        Product_amount.setText("");
    }

    public void createOrder(Order order) {
        this.order = order;
    }

    public void gone(List<Product> list) {
        if (list.size() == 0) {
            warning.visibleProperty().setValue(true);
        } else {
            OrdersDAO OrdersDAO = new OrdersDAO();
            SoftReference<OrdersDAO> weakReference = new SoftReference<>(OrdersDAO);
            OrdersDAO.add(order, list);
            button_add.getScene().getWindow().hide();
            orderViewController.Watch_order(new ActionEvent());
        }
    }


}