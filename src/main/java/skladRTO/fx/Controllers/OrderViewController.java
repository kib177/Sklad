package skladRTO.fx.Controllers;

import java.lang.ref.WeakReference;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.text.Text;

import skladRTO.api.lists.ProductStatusList;
import skladRTO.api.models.Authorization;
import skladRTO.api.FX.models.OrderFX;
import skladRTO.api.FX.models.ProductFX;
import skladRTO.api.models.Order;
import skladRTO.api.models.ProductStatus;
import skladRTO.dao.modelDAO.OrdersDAO;
import javafx.fxml.FXML;
import javafx.scene.control.cell.PropertyValueFactory;
import skladRTO.dao.modelDAO.ProductDAO;
import skladRTO.fx.sceneFX.CreateScene;


public class OrderViewController implements Initializable {
    private ProductDAO getProduct;
    private CreateScene createScene;
    @FXML
    private CheckMenuItem CheckMenuItem_Delete_Order;
    @FXML
    private CheckMenuItem CheckMenuItem_confirmOrder;
    @FXML
    private CheckMenuItem CheckMenuItem_deleteProduct;
    @FXML
    private CheckMenuItem CheckMenuItem_updateOrder;
    @FXML
    private TableColumn<?, ?> ColumnProduct_amount;
    @FXML
    private TableColumn<?, ?> ColumnProduct_id;
    @FXML
    private TableColumn<?, ?> ColumnProduct_name;
    @FXML
    private TableColumn<ProductFX, String> ColumnProduct_status;
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
    private MenuItem MenuItem_List_Product;
    @FXML
    private MenuItem MenuItem_addOrder;
    @FXML
    private MenuItem MenuItem_exit;
    @FXML
    private MenuItem MenuItem_users;
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
    private TableColumn<OrderFX, String> Сolumn_description;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        getProduct = new ProductDAO();
        getProduct.getProductStatus();
        MenuItem_List_Product.setOnAction(actionEvent -> {
            createScene = new CreateScene();
            createScene.createScene("List_Product.fxml", 600, 400);
        });
        System.out.println(ProductStatusList.getObservableList());
        Watch_order(new ActionEvent());
        if (Authorization.getStatusUser().getStatus().equals("Пользователь")) {
            Menu_Users.disableProperty().setValue(true);
            CheckMenuItem_confirmOrder.disableProperty().setValue(true);
            CheckMenuItem_Delete_Order.disableProperty().setValue(true);
            CheckMenuItem_deleteProduct.disableProperty().setValue(true);
        } else if (Authorization.getStatusUser().getStatus().equals("Модератор")) {
            Menu_Users.disableProperty().setValue(true);
        }
    }

    public ObservableList<ProductFX> getListProduct(int id) {
        getProduct = new ProductDAO();
        WeakReference<ProductDAO> getProductWeakReference = new WeakReference<>(getProduct);
        return getProduct.showListOfProducts(id);
    }

    @FXML
    public void Watch_order(ActionEvent actionEvent) {
        OrdersDAO ordersDAO = new OrdersDAO();
        WeakReference<OrdersDAO> weakReference = new WeakReference<>(ordersDAO);

        Column_Id.setCellValueFactory(new PropertyValueFactory<>("id"));
        Column_number_order.setCellValueFactory(new PropertyValueFactory<>("number_order"));
        Сolumn_description.setCellValueFactory(new PropertyValueFactory<>("order_description"));
        Сolumn_description.setCellFactory(tc -> textWrap());
        Column_user.setCellValueFactory(new PropertyValueFactory<>("user"));
        Column_date.setCellValueFactory(new PropertyValueFactory<>("order_date"));
        List_order.setItems(ordersDAO.showListOfOrders());

        TableView.TableViewSelectionModel<OrderFX> selectionModel = List_order.getSelectionModel();
        selectionModel.selectedItemProperty().addListener(new ChangeListener<OrderFX>() {
            @Override
            public void changed(ObservableValue<? extends OrderFX> observableValue, OrderFX orderFX, OrderFX newOrderFX) {
                if (newOrderFX != null) {
                    viewProduct(newOrderFX.getId());
                }
            }
        });
    }

    public TableCell<OrderFX, String> textWrap() {
        TableCell<OrderFX, String> cell = new TableCell<>();
        Text text = new Text();
        cell.setGraphic(text);
        cell.setPrefHeight(Control.USE_COMPUTED_SIZE);
        text.wrappingWidthProperty().bind(Сolumn_description.widthProperty());
        text.textProperty().bind(cell.itemProperty());
        return cell;
    }

    @FXML
    public void MenuItem_addOrder(ActionEvent actionEvent) {
        createScene = new CreateScene();
        createScene.createScene("New_order.fxml", 800, 400);
    }

    @FXML
    public void CheckMenuItem_deleteProduct() {

        TableView.TableViewSelectionModel<ProductFX> selectionModel = Table_Items.getSelectionModel();
        selectionModel.selectedItemProperty().addListener(new ChangeListener<skladRTO.api.FX.models.ProductFX>() {
            @Override
            public void changed(ObservableValue<? extends ProductFX> observableValue, ProductFX product, ProductFX newProduct) {
                if (CheckMenuItem_deleteProduct.isSelected() && !CheckMenuItem_Delete_Order.isSelected()) {
                    if (newProduct != null) {
                        createScene = new CreateScene();
                        createScene.createScene("Window.fxml", 400, 200);
                        ((WindowController) createScene.getLoader().getController()).deleteProduct(newProduct);
                        CheckMenuItem_deleteProduct.selectedProperty().setValue(false);
                    }
                }
            }
        });

    }


    @FXML
    public void CheckMenuItem_Delete_Order(ActionEvent actionEvent) {
        TableView.TableViewSelectionModel<OrderFX> selectionModel = List_order.getSelectionModel();
        selectionModel.selectedItemProperty().addListener(new ChangeListener<skladRTO.api.FX.models.OrderFX>() {
            @Override
            public void changed(ObservableValue<? extends OrderFX> observableValue, OrderFX order, OrderFX newOrder) {
                if (CheckMenuItem_Delete_Order.isSelected() && !CheckMenuItem_deleteProduct.isSelected()) {
                    if (newOrder != null) {
                        Order order1 = new Order(newOrder.getId(), newOrder.getOrder_description(), newOrder.getOrder_date(),
                                newOrder.getNumber_order());
                        List<ProductFX> list1 = Table_Items.getItems();
                        createScene = new CreateScene();
                        createScene.createScene("Window.fxml", 400, 200);
                        ((WindowController) createScene.getLoader().getController()).deleteOrder(order1, list1);
                        CheckMenuItem_Delete_Order.selectedProperty().setValue(false);
                    }
                }
            }
        });
    }


    @FXML
    public void SearchOrderDescription() {
        OrdersDAO ordersDAO = new OrdersDAO();
        WeakReference<OrdersDAO> weakReference = new WeakReference<>(ordersDAO);
        Watch_order(new ActionEvent());
        List_order.setItems(ordersDAO.searchName(search_name.getText()));
    }

    @FXML
    public void SearchOrderNumber() {
        OrdersDAO ordersDAO = new OrdersDAO();
        WeakReference<OrdersDAO> weakReference = new WeakReference<>(ordersDAO);
        Watch_order(new ActionEvent());
        List_order.setItems(ordersDAO.searchNumber(search_articul.getText()));
    }

    public void viewProduct(int id) {
        ColumnProduct_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        ColumnProduct_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        ColumnProduct_amount.setCellValueFactory(new PropertyValueFactory<>("amount"));
        ColumnProduct_status.setCellValueFactory(new PropertyValueFactory<>("status"));
        Table_Items.setItems(getListProduct(id));

        if (CheckMenuItem_confirmOrder.isSelected()) {
            TableView.TableViewSelectionModel<ProductFX> selectionModel = Table_Items.getSelectionModel();
            selectionModel.selectedItemProperty().addListener(new ChangeListener<skladRTO.api.FX.models.ProductFX>() {
                @Override
                public void changed(ObservableValue<? extends ProductFX> observableValue, ProductFX product, ProductFX newProduct) {
                    if (newProduct != null) {
                        ((PrixodController) createScene.getLoader().getController()).addProduct(newProduct);
                    }
                }
            });
        }
    }

    @FXML
    public void MenuItem_users(ActionEvent actionEvent) {
        CreateScene createScene = new CreateScene();
        createScene.createScene("List_Users.fxml", 950, 370);
    }

    @FXML
    public void CheckMenuItem_confirmOrder(ActionEvent actionEvent) {
        createScene = new CreateScene();
        createScene.createScene("Prixod.fxml", 533, 247);
    }
}
