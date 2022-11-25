package skladRTO.fx.Controllers;

import java.lang.ref.WeakReference;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import skladRTO.api.models.FX.OrderFX;
import skladRTO.api.models.FX.ProductFX;
import skladRTO.dao.requestsDB.Get.GetOrdersDAO;
import javafx.fxml.FXML;
import javafx.scene.control.cell.PropertyValueFactory;
import skladRTO.dao.requestsDB.Get.getProduct;
import skladRTO.fx.sceneFX.CreateScene;


public class OrderViewController implements Initializable {
    @FXML
    private CheckBox CheckBox_product;
    @FXML
    private TableColumn<?, ?> ColumnProduct_amount;
    @FXML
    private TableColumn<?, ?> ColumnProduct_id;
    @FXML
    private TableColumn<?, ?> ColumnProduct_name;
    @FXML
    private TableColumn<?, ?> ColumnProduct_status;
    @FXML
    private TableColumn<?, ?> Column_Id;
    @FXML
    private TableColumn<?, ?> Column_date;
    @FXML
    private TableColumn<?, ?> Column_number_order;
    @FXML
    private TableColumn<?, ?> Column_user;
    @FXML
    private TableView<OrderFX> List_order;
    @FXML
    private MenuItem MenuItem_addOrder;
    @FXML
    private MenuItem MenuItem_confirmOrder;
    @FXML
    private MenuItem MenuItem_deleteOrder;
    @FXML
    private MenuItem MenuItem_exit;
    @FXML
    private MenuItem MenuItem_users;
    @FXML
    private MenuItem MenuItem_updateOrder;
    @FXML
    private Menu Menu_Orders;
    @FXML
    private Menu Menu_Users;
    @FXML
    private TableView<ProductFX> Table_Items;
    @FXML
    private Button Watch_order;
    @FXML
    private Menu menu_edit;
    @FXML
    private Menu menu_file;
    @FXML
    private Menu menu_help;
    @FXML
    private TextField search_articul;
    @FXML
    private TextField search_name;
    @FXML
    private TableColumn<?, ?> Сolumn_description;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Menu_Users.disableProperty().setValue(false);
        Watch_order(new ActionEvent());
    }

    @FXML
    public void Watch_order(ActionEvent actionEvent) {
        GetOrdersDAO ordersDAO = new GetOrdersDAO();
        WeakReference<GetOrdersDAO> weakReference = new WeakReference<>(ordersDAO);

        Column_Id.setCellValueFactory(new PropertyValueFactory<>("id"));
        Column_number_order.setCellValueFactory(new PropertyValueFactory<>("number_order"));
        Сolumn_description.setCellValueFactory(new PropertyValueFactory<>("order_description"));
        Column_user.setCellValueFactory(new PropertyValueFactory<>("user"));
        Column_date.setCellValueFactory(new PropertyValueFactory<>("order_date"));
        List_order.setItems(ordersDAO.showListOfOrders());

        TableView.TableViewSelectionModel<OrderFX> selectionModel = List_order.getSelectionModel();

        selectionModel.selectedItemProperty().addListener(new ChangeListener<OrderFX>() {

            @Override
            public void changed(ObservableValue<? extends OrderFX> observableValue, OrderFX orderFX, OrderFX newOrderFX) {
                if (newOrderFX != null) {
                    if (!Table_Items.disabledProperty().getValue()) {
                        viewProduct(newOrderFX.getId());
                    }
                }
            }
        });
    }


    public void info(String str) {

    }
    @FXML
    public void MenuItem_addOrder(ActionEvent actionEvent) {
        CreateScene createScene = new CreateScene();
        createScene.createScene("New_order.fxml", "Новый заказ", 800, 400);

    }

    @FXML
    public void CheckBox_product(ActionEvent actionEvent) {
        if (Table_Items.disabledProperty().getValue()) {
            Table_Items.setDisable(false);
        } else Table_Items.setDisable(true);
    }

    @FXML
    public void SearchOrderDescription() {
        GetOrdersDAO ordersDAO = new GetOrdersDAO();
        WeakReference<GetOrdersDAO> weakReference = new WeakReference<>(ordersDAO);
        Watch_order(new ActionEvent());
        List_order.setItems(ordersDAO.searchName(search_name.getText()));
    }

    @FXML
    public void SearchOrderNumber() {
        GetOrdersDAO ordersDAO = new GetOrdersDAO();
        WeakReference<GetOrdersDAO> weakReference = new WeakReference<>(ordersDAO);
        Watch_order(new ActionEvent());
        List_order.setItems(ordersDAO.searchNumber(search_articul.getText()));
    }

    public void viewProduct(int id) {
        getProduct getProduct = new getProduct();
        WeakReference<getProduct> getProductWeakReference = new WeakReference<>(getProduct);
        ColumnProduct_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        ColumnProduct_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        ColumnProduct_amount.setCellValueFactory(new PropertyValueFactory<>("amount"));
        ColumnProduct_status.setCellValueFactory(new PropertyValueFactory<>("status"));
        Table_Items.setItems(getProduct.showListOfProducts(id));

        TableView.TableViewSelectionModel<ProductFX> selectionModel = Table_Items.getSelectionModel();

        selectionModel.selectedItemProperty().addListener(new ChangeListener<ProductFX>() {

            @Override
            public void changed(ObservableValue<? extends ProductFX> observableValue, ProductFX product, ProductFX newProduct) {
                if (newProduct != null) {
                    CreateScene createScene = new CreateScene();
                    createScene.createScene("Prixod.fxml", "Подтверждение заказа", 533, 247);
                    ((PrixodController) createScene.getLoader().getController()).toStringProduct(newProduct);
                    }
                }
        });
    }

    @FXML
    public void MenuItem_users(ActionEvent actionEvent) {
        CreateScene createScene = new CreateScene();
        createScene.createScene("List_Users.fxml", "Список пользователей", 950, 370);
    }
}
