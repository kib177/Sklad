package skladrto.project;

import java.io.IOException;
import java.lang.ref.WeakReference;

import javafx.scene.control.*;
import skladrto.project.Model.Order;
import skladrto.project.RequestsDB.Get.getOrdersDAO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class orderViewController {

    @FXML
    private CheckBox Check_articul;

    @FXML
    private CheckBox Check_department;

    @FXML
    private CheckBox Check_name;

    @FXML
    private Button search;
    @FXML
    private Label date;
    @FXML
    private TableColumn<?, ?> Column_Id;

    @FXML
    private TableColumn<?, ?> Column_articul;

    @FXML
    private TableColumn<?, ?> Column_date;

    @FXML
    private TableColumn<?, ?> Column_department;

    @FXML
    private TableColumn<?, ?> Column_name;

    @FXML
    private TableColumn<?, ?> Column_user;

    @FXML
    private TableView<Order> List_order;

    @FXML
    private Button Watch_order;

    @FXML
    private Button new_order;

    @FXML
    private TextField search_articul;

    @FXML
    private TextField search_department;

    @FXML
    private TextField search_name;

    @FXML
    private TableColumn<?, ?> Сolumn_amount;

    @FXML
    private TableColumn<?, ?> Сolumn_description;

    @FXML
    private TableColumn<?, ?> Сolumn_oboryd;

    @FXML
    private TableColumn<?, ?> Сolumn_status;

    @FXML
    void initialize() {
        getOrdersDAO ordersDAO = new getOrdersDAO();
        WeakReference<getOrdersDAO> weakReference = new WeakReference<>(ordersDAO);

        Watch_order.setOnAction(actionEvent -> {
            getOrders();
            List_order.setItems(ordersDAO.showListOfOrders());
        });

        search.setOnAction(actionEvent -> {
            SearchOrder(ordersDAO);
        });

        new_order.setOnAction(actionEvent -> {
            newOrder();

        });


    }

    public void getOrders() {
        Column_Id.setCellValueFactory(new PropertyValueFactory<>("id"));
        Column_articul.setCellValueFactory(new PropertyValueFactory<>("product_articul"));
        Column_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        Сolumn_amount.setCellValueFactory(new PropertyValueFactory<>("amount"));
        Сolumn_description.setCellValueFactory(new PropertyValueFactory<>("order_description"));
        Column_user.setCellValueFactory(new PropertyValueFactory<>("last_name"));
        Сolumn_status.setCellValueFactory(new PropertyValueFactory<>("status"));
        Сolumn_oboryd.setCellValueFactory(new PropertyValueFactory<>("equipment"));
        Column_department.setCellValueFactory(new PropertyValueFactory<>("subdivision"));
        Column_date.setCellValueFactory(new PropertyValueFactory<>("order_date"));
    }

    public void newOrder() {
        new_order.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("New_order.fxml"));

        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root, 800, 400));
        stage.show();

    }

    public void SearchOrder(getOrdersDAO ordersDAO){
        if (Check_name.isSelected()) {
            getOrders();
            List_order.setItems(ordersDAO.searchName(search_name.getText()));
        } else if (Check_articul.isSelected()) {
            getOrders();
            List_order.setItems(ordersDAO.searchArticle(search_articul.getText()));
        } else if (Check_department.isSelected()) {
            getOrders();
            List_order.setItems(ordersDAO.searchSubdivision(search_department.getText()));
        }
    }

}
