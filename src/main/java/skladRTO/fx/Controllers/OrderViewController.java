package skladRTO.fx.Controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import skladRTO.ApachePoiDemo;
import skladRTO.api.FX.models.OrderFX;
import skladRTO.api.FX.models.ProductFX;
import skladRTO.api.models.Authorization;
import skladRTO.api.models.Order;
import skladRTO.dao.modelDAO.OrdersDAO;
import skladRTO.dao.modelDAO.ProductDAO;
import skladRTO.dao.modelDAO.UserDAO;
import skladRTO.fx.sceneFX.CreateScene;

import java.lang.ref.WeakReference;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;


public class OrderViewController implements Initializable {
    private OrdersDAO ordersDAO = new OrdersDAO();
    private Date currentDate = new Date();
    private DateFormat dateFormat;
    private ProductDAO getProduct = new ProductDAO();
    private CreateScene createScene = new CreateScene();
    private UserDAO userDAO = new UserDAO();
    private CreateScene createSceneOrderView;
    @FXML
    private CheckMenuItem CheckMenuItem_Delete_Order;
    @FXML
    private MenuItem CheckMenuItem_confirmOrder;
    @FXML
    private CheckMenuItem CheckMenuItem_deleteProduct;
    @FXML
    private CheckMenuItem CheckMenuItem_createWord;
    @FXML
    private CheckMenuItem CheckMenuItem_updateOrder;
    @FXML
    private CheckMenuItem CheckMenuItem_Info;
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
    private TableColumn<OrderFX, String> Column_machine;
    @FXML
    private TableView<OrderFX> List_order;
    @FXML
    private MenuItem MenuItem_List_Product;
    @FXML
    private MenuItem MenuItem_addOrder;
    @FXML
    private MenuItem MenuItem_exit;
    @FXML
    private MenuItem MenuItem_Statistic;
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
    private TextField search_machine;
    @FXML
    private TextField SearchForName;
    @FXML
    private TableColumn<OrderFX, String> Сolumn_description;
    @FXML
    private Label date;
    @FXML
    private ContextMenu NumberOrderContextMenu;
    private Integer id;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        List_order.getSelectionModel().clearSelection();
        date();
        getProduct.getProductStatus();
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

    @FXML
    public void MenuItem_print() {

    }

    @FXML
    public void MenuItem_listMachine() {
        createScene = new CreateScene();
        createScene.createScene("Machines.fxml", 600, 400, true);
    }

    public void setCreateScene(CreateScene createScene) {
        this.createSceneOrderView = createScene;
    }

    @FXML
    public void MenuItem_exit() {
        createScene.createScene("Authorization.fxml", 350, 250, false);
        createSceneOrderView.getStage().close();
    }

    public void date() {
        dateFormat = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault());
        date.setText(dateFormat.format(currentDate));
    }

    @FXML
    public void MenuItem_Statistic() {
        createScene.createScene("PieChart.fxml", 500, 350, false);
        createScene.getStage().setAlwaysOnTop(true);
    }

    @FXML
    public void MenuItem_List_Product() {
        createScene.createScene("List_Product.fxml", 600, 400, true);
        createScene.getStage().setAlwaysOnTop(true);
    }

    public ObservableList<ProductFX> getListProduct(int id) {
        getProduct = new ProductDAO();
        WeakReference<ProductDAO> getProductWeakReference = new WeakReference<>(getProduct);
        return getProduct.showListOfProducts(id);
    }

    @FXML
    public void Watch_order(ActionEvent actionEvent) {
        WeakReference<OrdersDAO> weakReference = new WeakReference<>(ordersDAO);
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
                    id = newOrderFX.getId();
                    viewProduct();
                    if(CheckMenuItem_createWord.isSelected()){
                        createWord(ordersDAO.searchOrder(newOrderFX.getId()));
                        System.out.println(newOrderFX.getId()+" saasdsadsadsadsadasd");
                    }

                }
            }
        });
    }

    private void createWord(Order order){
        System.out.println(order+"  asdsd sda as d");
        ApachePoiDemo apachePoiDemo = new ApachePoiDemo();
        apachePoiDemo.CreateWord(getListProduct(order.getId()), order.getOrderDescription()+".docx", order.getUserId());
        CheckMenuItem_createWord.selectedProperty().setValue(false);
    }


    public void viewProductInfo(int id) {
        if (CheckMenuItem_Info.isSelected()) {
            createScene.createScene("ViewProductInfo.fxml", 400, 200, false);
            ((ProductInfoListController) createScene.getLoader().getController()).viewProductInfo(id);
            createScene.getStage().setAlwaysOnTop(true);
        }
    }

    @FXML
    public void MenuItem_addOrder(ActionEvent actionEvent) {
        createScene.createScene("New_order.fxml", 505, 400, false);
        OrderNewController controller = createScene.getLoader().getController();
        controller.setOrderViewController(this);
        controller.getSceneProductNewController(this.createScene.getStage());
        createScene.getStage().setAlwaysOnTop(true);
    }

    @FXML
    public void CheckMenuItem_deleteProduct() {

        TableView.TableViewSelectionModel<ProductFX> selectionModel = Table_Items.getSelectionModel();
        selectionModel.selectedItemProperty().addListener(new ChangeListener<skladRTO.api.FX.models.ProductFX>() {
            @Override
            public void changed(ObservableValue<? extends ProductFX> observableValue, ProductFX product, ProductFX newProduct) {
                if (CheckMenuItem_deleteProduct.isSelected() && !CheckMenuItem_Delete_Order.isSelected()) {
                    if (newProduct != null) {
                        CreateScene createScene = new CreateScene();
                        createScene.createScene("Window.fxml", 400, 200, false);

                        WindowController controller = createScene.getLoader().getController();
                        controller.deleteProduct(newProduct);
                        controller.getOrderView(OrderViewController.this);
                        CheckMenuItem_deleteProduct.selectedProperty().setValue(false);
                    }
                }
            }
        });
    }

    @FXML
    public void CheckMenuItem_Delete_Order() {
        TableView.TableViewSelectionModel<OrderFX> selectionModel = List_order.getSelectionModel();
        selectionModel.selectedItemProperty().addListener(new ChangeListener<skladRTO.api.FX.models.OrderFX>() {
            @Override
            public void changed(ObservableValue<? extends OrderFX> observableValue, OrderFX order, OrderFX newOrder) {
                if (CheckMenuItem_Delete_Order.isSelected() && !CheckMenuItem_deleteProduct.isSelected()) {
                    if (newOrder != null) {
                        Order order1 = new Order(newOrder.getId(), newOrder.getOrder_description(), newOrder.getOrder_date(),
                                newOrder.getNumber_order());
                        List<ProductFX> list1 = Table_Items.getItems();
                        CreateScene createScene = new CreateScene();
                        createScene.createScene("Window.fxml", 400, 200, false);

                        WindowController controller = createScene.getLoader().getController();
                        controller.deleteOrder(order1, list1);
                        controller.getOrderView(OrderViewController.this);
                        CheckMenuItem_Delete_Order.selectedProperty().setValue(false);
                    }
                }
            }
        });
    }

    @FXML
    public void SearchForName() {
        WeakReference<OrdersDAO> weakReference = new WeakReference<>(ordersDAO);
        Watch_order(new ActionEvent());
        List_order.setItems(ordersDAO.searchForNameUser(SearchForName.getText()));
    }

    @FXML
    public void SearchOrderDescription() {
        WeakReference<OrdersDAO> weakReference = new WeakReference<>(ordersDAO);
        Watch_order(new ActionEvent());
        List_order.setItems(ordersDAO.searchName(search_name.getText()));
    }

    @FXML
    public void SearchOrderNumber() {
        WeakReference<OrdersDAO> weakReference = new WeakReference<>(ordersDAO);
        Watch_order(new ActionEvent());
        List_order.setItems(ordersDAO.searchNumber(search_articul.getText()));
    }

    public void viewProduct() {
        ColumnProduct_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        ColumnProduct_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        Column_machine.setCellValueFactory(new PropertyValueFactory<>("machine"));
        ColumnProduct_amount.setCellValueFactory(new PropertyValueFactory<>("AmountUnits"));
        ColumnProduct_status.setCellValueFactory(new PropertyValueFactory<>("status"));
        Table_Items.setItems(getListProduct(id));

        TableView.TableViewSelectionModel<ProductFX> selectionModel = Table_Items.getSelectionModel();
        selectionModel.selectedItemProperty().addListener(new ChangeListener<skladRTO.api.FX.models.ProductFX>() {
            @Override
            public void changed(ObservableValue<? extends ProductFX> observableValue, ProductFX product, ProductFX newProduct) {
                if (newProduct != null) {
                    if (CheckMenuItem_Info.isSelected()) {
                        viewProductInfo(newProduct.getId());

                    }
                }
            }
        });
    }


    @FXML
    public void MenuItem_users() {
        createScene.createScene("List_Users.fxml", 950, 370, true);
    }

    @FXML
    public void CheckMenuItem_confirmOrder() {
        CreateScene createScene = new CreateScene();
        createScene.createScene("Prixod.fxml", 533, 247, false);
        createScene.getStage().setAlwaysOnTop(true);

        TableView.TableViewSelectionModel<ProductFX> selectionModel = Table_Items.getSelectionModel();
        selectionModel.selectedItemProperty().addListener(new ChangeListener<skladRTO.api.FX.models.ProductFX>() {
            @Override
            public void changed(ObservableValue<? extends ProductFX> observableValue, ProductFX product, ProductFX newProduct) {
                if (newProduct != null) {
                    PrixodController controller = createScene.getLoader().getController();
                    controller.addProduct(newProduct);
                    controller.close(createScene);
                    controller.getOrderView(OrderViewController.this);
                }
            }
        });
    }
}


