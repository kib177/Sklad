package skladRTO.fx.Controllers;

import java.io.ObjectInputFilter;
import java.lang.ref.WeakReference;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.util.Callback;

import skladRTO.api.models.Authorization;
import skladRTO.api.models.FX.OrderFX;
import skladRTO.api.models.FX.ProductFX;
import skladRTO.dao.requestsDB.Get.GetOrdersDAO;
import javafx.fxml.FXML;
import javafx.scene.control.cell.PropertyValueFactory;
import skladRTO.dao.requestsDB.Get.getProduct;
import skladRTO.fx.sceneFX.CreateScene;


public class OrderViewController implements Initializable {
    private CreateScene createScene;
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
    private TableColumn<ProductFX, String> Сolumn_description;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        Watch_order(new ActionEvent());


        if (Authorization.getStatusUser().getStatus().equals("Пользователь")) {
            Menu_Users.disableProperty().setValue(true);
            MenuItem_confirmOrder.disableProperty().setValue(true);
            MenuItem_deleteOrder.disableProperty().setValue(true);
        } else if (Authorization.getStatusUser().getStatus().equals("Модератор")) {
            Menu_Users.disableProperty().setValue(true);
        }
    }

    @FXML
    public void Watch_order(ActionEvent actionEvent) {
        GetOrdersDAO ordersDAO = new GetOrdersDAO();
        WeakReference<GetOrdersDAO> weakReference = new WeakReference<>(ordersDAO);

        Column_Id.setCellValueFactory(new PropertyValueFactory<>("id"));
        Column_number_order.setCellValueFactory(new PropertyValueFactory<>("number_order"));
        Сolumn_description.setCellValueFactory(new PropertyValueFactory<ProductFX,String>("order_description"));
        Сolumn_description.setCellFactory(tc -> {
            TableCell<ProductFX, String> cell = new TableCell<>();
            Text text = new Text();
            cell.setGraphic(text);
            cell.setPrefHeight(Control.USE_COMPUTED_SIZE);
            text.wrappingWidthProperty().bind(Сolumn_description.widthProperty());
            text.textProperty().bind(cell.itemProperty());
            return cell ;
        });
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
        createScene = new CreateScene();
        createScene.createScene("New_order.fxml", 800, 400);

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
                    ((PrixodController) createScene.getLoader().getController()).addProductFX(newProduct);

                }
            }
        });
    }

    @FXML
    public void MenuItem_users(ActionEvent actionEvent) {
        CreateScene createScene = new CreateScene();
        createScene.createScene("List_Users.fxml", 950, 370);
    }

    @FXML
    public void MenuItem_confirmOrder(ActionEvent actionEvent) {
        createScene = new CreateScene();
        createScene.createScene("Prixod.fxml", 533, 247);
    }
}
