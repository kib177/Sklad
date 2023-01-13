package skladRTO.fx.Controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import skladRTO.ApachePoiDemo;
import skladRTO.api.FX.models.OrderFX;
import skladRTO.api.FX.models.ProductFX;
import skladRTO.api.models.Authorization;
import skladRTO.api.models.Order;
import skladRTO.dao.modelDAO.OrdersDAO;
import skladRTO.dao.modelDAO.ProductDAO;
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
    private TableColumn<ProductFX, String> ColumnProduct_name;
    @FXML
    private TableColumn<ProductFX, String> ColumnProduct_status;
    @FXML
    private TableColumn<?, ?> Column_Id;
    @FXML
    private TableColumn<?, ?> Column_date;
    @FXML
    private TableColumn<OrderFX, String> Column_number_order;
    @FXML
    private TableColumn<?, ?> Column_user;
    @FXML
    private TableColumn<ProductFX, String> Column_machine;
    @FXML
    private TableView<OrderFX> List_order;
    @FXML
    private Menu Menu_Users;
    @FXML
    private TableView<ProductFX> Table_Items;
    @FXML
    private TextField search_articul;
    @FXML
    private TextField search_name;
    @FXML
    private TextField SearchForName;
    @FXML
    private TableColumn<OrderFX, String> Сolumn_description;
    @FXML
    private Label date;
    private Integer idOrder;
    private ProductFX productFX;
    @FXML
    private MenuItem MenuItem_UpdateNameProduct;
    @FXML
    private ContextMenu ContextMenu_ProductName;
    @FXML
    private ContextMenu ContextMenu_DesOrder;
    @FXML
    private ContextMenu ContextMenu_NumberOrder;
    @FXML
    private MenuItem MenuItemContext_UpdateDesOrder;
    @FXML
    private MenuItem MenuItem_UpdateNumberOrder;

    /**
     *
     * @param location
     * The location used to resolve relative paths for the root object, or
     * {@code null} if the location is not known.
     *
     * @param resources
     * The resources used to localize the root object, or {@code null} if
     * the root object was not localized.
     *
     *  Метод вызывается при загрузке окна
     *  все действия в методе будут выполнены раньше отображения интерфейса окна
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (Authorization.getStatusUser().getStatus().equals("Пользователь")) {// Если пользователь, отключаем доступ
            Menu_Users.disableProperty().setValue(true);// меню управления пользователями
            CheckMenuItem_confirmOrder.disableProperty().setValue(true);// подтверждение заказа
            CheckMenuItem_Delete_Order.disableProperty().setValue(true);// удаление заказа
            CheckMenuItem_deleteProduct.disableProperty().setValue(true);// удаление продукта
        } else if (Authorization.getStatusUser().getStatus().equals("Модератор")) { // если модератор отключаем только доступ к управлению пользователями
            Menu_Users.disableProperty().setValue(true);
        }
        List_order.getSelectionModel().clearSelection();// убираем выдление в таблице
        date();// метод даты
        getProduct.getProductStatus(); // подгружаем метод для создания списка статусов
        Watch_order(new ActionEvent()); // метод выводящий заказы в таблицу

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


    public void UpdateNameProduct(){
        System.out.println("asdsads" + productFX);
        CreateScene createScene = new CreateScene();
        createScene.createScene("UpdateProduct.fxml", 500, 250, false);
        UpdateProductController controller = createScene.getLoader().getController();
        controller.getProduct(productFX);
        controller.getOrderViewController(this);
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
        WeakReference<ProductDAO> weakReference = new WeakReference<>(getProduct);
        return getProduct.showListOfProducts(id);
    }

    /**
     *
     * @param actionEvent передаю действие на кнопку, без участия пользователя (при надобности)
     *  Метод отображения заказов в таблице
     *  List_Order - сама таблица
     *  selectionModel - вызывает метод получения выделения в таблице, после создается список и добавляется в него
     *  addListener<>( new ChangeListener<>()) является списком выделенных обьектов(упорядоченым)
     *  метод changed - переопределен из ChangeListener, позволяет работать с обьектами в списке
     *
     */
    @FXML
    public void Watch_order(ActionEvent actionEvent) {
        WeakReference<OrdersDAO> weakReference = new WeakReference<>(ordersDAO);
        Column_Id.setCellValueFactory(new PropertyValueFactory<>("id"));
        Column_number_order.setCellValueFactory(new PropertyValueFactory<>("number_order"));
        Column_number_order.setCellFactory(tc -> textWrap(Column_number_order));
        Сolumn_description.setCellValueFactory(new PropertyValueFactory<>("order_description"));
        Сolumn_description.setCellFactory(tc -> textWrap(Сolumn_description));
        Column_user.setCellValueFactory(new PropertyValueFactory<>("user"));
        Column_date.setCellValueFactory(new PropertyValueFactory<>("order_date"));
        List_order.setItems(ordersDAO.showListOfOrders());

        TableView.TableViewSelectionModel<OrderFX> selectionModel = List_order.getSelectionModel();
        selectionModel.selectedItemProperty().addListener(new ChangeListener<OrderFX>() {
            @Override
            public void changed(ObservableValue<? extends OrderFX> observableValue, OrderFX orderFX, OrderFX newOrderFX) {
                if (newOrderFX != null) {
                    idOrder = newOrderFX.getId(); // локальная переменная для заполнения листа продуктов по id заказа
                    viewProduct(); // вызов метода отображения продуктов по заказу
                    if (CheckMenuItem_createWord.isSelected()) { // если в меню выбрано создать файл то передаст id выделенного обьекта
                        createWord(ordersDAO.searchOrder(newOrderFX.getId()));
                    }
                }
            }
        });
    }

    public TableCell<OrderFX, String> textWrap(TableColumn<OrderFX, String> tableColumn) {
        TableCell<OrderFX, String> cell = new TableCell<>();
        Text text = new Text();
        cell.setGraphic(text);
        cell.setPrefHeight(Control.USE_COMPUTED_SIZE);

        if(tableColumn == Column_number_order){
            cell.setContextMenu(ContextMenu_NumberOrder);
        } else if (tableColumn == Сolumn_description){
            cell.setContextMenu(ContextMenu_DesOrder);
        }

        text.wrappingWidthProperty().bind(tableColumn.widthProperty());
        text.textProperty().bind(cell.itemProperty());
        return cell;
    }

    private void createWord(Order order) {
        ApachePoiDemo apachePoiDemo = new ApachePoiDemo();
        WeakReference<ApachePoiDemo> weakReference = new WeakReference<>(apachePoiDemo);
        apachePoiDemo.CreateWord(getListProduct(order.getId()), order.getOrderDescription(), order.getUserId(), order.getNumberOrder());
        CheckMenuItem_createWord.selectedProperty().setValue(false);
    }

    public void viewProductInfo(int id) {
        if (CheckMenuItem_Info.isSelected()) {
            createScene.createScene("ViewProductInfo.fxml", 400, 200, false);
            ((ProductInfoListController) createScene.getLoader().getController()).viewProductInfo(id);
            createScene.getStage().setAlwaysOnTop(true);
        }
    }

    /**
     *
     * @param actionEvent - так же действие для вызова метода без нажатия кнопки
     * createScene - локальная сслыка на обьект CreateScene которая формирует вызов окна
     * controller - явдяется обьектом только что вызванного класса, содержащим контроллер загрузчика класса
     *
     */

    @FXML
    public void MenuItem_addOrder(ActionEvent actionEvent) {
        createScene.createScene("New_order.fxml", 505, 400, false);
        OrderNewController controller = createScene.getLoader().getController();
        controller.setOrderViewController(this); // передаем обьект OrderViewController в метод вызванному классу
        controller.getSceneProductNewController(this.createScene.getStage());// передаем Stage (Сцену) вызванному классу
        createScene.getStage().setAlwaysOnTop(true);// привязка сцены поверх старого окна
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
                        WeakReference<CreateScene> weakReference = new WeakReference<>(createScene);
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
                        WeakReference<CreateScene> weakReference = new WeakReference<>(createScene);
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
        ColumnProduct_id.setCellValueFactory(new PropertyValueFactory<>("counter"));
        ColumnProduct_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        ColumnProduct_name.setCellFactory(tc-> textWrapProduct(ColumnProduct_name));
        Column_machine.setCellValueFactory(new PropertyValueFactory<>("machine"));
        Column_machine.setCellFactory(tc -> textWrapProduct(Column_machine));
        ColumnProduct_amount.setCellValueFactory(new PropertyValueFactory<>("AmountUnits"));
        ColumnProduct_status.setCellValueFactory(new PropertyValueFactory<>("status"));
        Table_Items.setItems(getListProduct(idOrder));

        TableView.TableViewSelectionModel<ProductFX> selectionModel = Table_Items.getSelectionModel();
        selectionModel.selectedItemProperty().addListener(new ChangeListener<skladRTO.api.FX.models.ProductFX>() {
            @Override
            public void changed(ObservableValue<? extends ProductFX> observableValue, ProductFX product, ProductFX newProduct) {
                if (newProduct != null) {
                    productFX = newProduct;
                    if (CheckMenuItem_Info.isSelected()) {
                        viewProductInfo(newProduct.getId());
                    }
                }
            }
        });
    }

    public TableCell<ProductFX, String> textWrapProduct(TableColumn<ProductFX, String> tableColumn) {
        TableCell<ProductFX, String> cell = new TableCell<>();
        Text text = new Text();
        cell.setGraphic(text);
        if(tableColumn == ColumnProduct_name) {
        cell.setContextMenu( ContextMenu_ProductName);
        }
        cell.setPrefHeight(Control.USE_COMPUTED_SIZE);
        text.wrappingWidthProperty().bind(tableColumn.widthProperty());
        if(tableColumn == Column_machine) {
            text.setTextAlignment(TextAlignment.CENTER);
        }
        text.textProperty().bind(cell.itemProperty());
        return cell;
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


