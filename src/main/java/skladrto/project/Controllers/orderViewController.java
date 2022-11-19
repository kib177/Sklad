package skladrto.project.Controllers;

import java.lang.ref.WeakReference;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.*;
import skladrto.project.Model.FX.OrderFX;
import skladrto.project.Model.FX.ProductFX;
import skladrto.project.RequestsDB.Get.getOrdersDAO;
import javafx.fxml.FXML;
import javafx.scene.control.cell.PropertyValueFactory;
import skladrto.project.RequestsDB.Get.getProduct;


public class orderViewController {
    @FXML
    private TableColumn<?, ?> Column_Id;
    @FXML
    private TableColumn<?, ?> ColumnProduct_amount;
    @FXML
    private TableColumn<?, ?> Column_date;
    @FXML
    private TableColumn<?, ?> ColumnProduct_id;
    @FXML
    private TableColumn<?, ?> ColumnProduct_name;
    @FXML
    private TableColumn<?, ?> Column_number_order;
    @FXML
    private TableColumn<?, ?> ColumnProduct_status;
    @FXML
    private TableColumn<?, ?> Column_user;
    @FXML
    private TableView<OrderFX> List_order;
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
    private Menu menu_id;
    @FXML
    private Menu menu_replace;
    @FXML
    private Button new_order;
    @FXML
    private MenuItem MenuItem_id;
    @FXML
    private MenuItem MenuItem_product;
    @FXML
    private TextField search_articul;
    @FXML
    private TextField search_name;
    @FXML
    private TableColumn<?, ?> Сolumn_description;

    @FXML
    void initialize() {
        getOrders();

        MenuItem_product.setOnAction(actionEvent -> {
            Table_Items.visibleProperty().setValue(!watchProduct());
        });

        MenuItem_id.setOnAction(actionEvent -> {
            ColumnProduct_id.visibleProperty().setValue(!watchID());
            Column_Id.visibleProperty().setValue(!watchID());
        });

        Watch_order.setOnAction(actionEvent -> getOrders());
        new_order.setOnAction(actionEvent -> newOrder());
    }

    public void getOrders() {
        getOrdersDAO ordersDAO = new getOrdersDAO();
        WeakReference<getOrdersDAO> weakReference = new WeakReference<>(ordersDAO);

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
                    if (watchProduct()) {
                        viewProduct(newOrderFX.getId());
                    }
                }
            }
        });
    }


    public void newOrder() {
        CreateScene createScene = new CreateScene();
        new_order.getScene().getWindow();
        createScene.createScene("New_order.fxml", "Новый заказ", 800, 400);
    }

    public Boolean watchID() {
        return Column_Id.visibleProperty().getValue();
    }

    public Boolean watchProduct() {
        return Table_Items.visibleProperty().getValue();
    }

    public void SearchOrderDescription() {
        getOrdersDAO ordersDAO = new getOrdersDAO();
        WeakReference<getOrdersDAO> weakReference = new WeakReference<>(ordersDAO);
        getOrders();
        List_order.setItems(ordersDAO.searchName(search_name.getText()));
    }

    public void SearchOrderNumber() {
        getOrdersDAO ordersDAO = new getOrdersDAO();
        WeakReference<getOrdersDAO> weakReference = new WeakReference<>(ordersDAO);

        getOrders();
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

    }

}
