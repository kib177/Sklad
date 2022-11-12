package skladrto.project;

import java.io.IOException;
import java.lang.ref.WeakReference;

import skladrto.project.Model.Order;
import skladrto.project.RequestsDB.Get.OrdersDAO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class orderViewController {
    private OrdersDAO ordersDAO;
    @FXML
    private TableView<Order> List_order;
    @FXML
    private TableColumn<?, ?> Column_Id;

    @FXML
    private TableColumn<?, ?> Column_articul;

    @FXML
    private TableColumn<?, ?> Column_date;

    @FXML
    private TableColumn<?, ?> Column_table;

    @FXML
    private TableColumn<?, ?> Column_user;

    @FXML
    private TableColumn<?, ?> Сolumn_amount;

    @FXML
    private TableColumn<?, ?> Сolumn_description;

    @FXML
    private TableColumn<?, ?> Сolumn_oboryd;

    @FXML
    private TableColumn<?, ?> сolumn_status;

    @FXML
    private Button Watch_order;

    @FXML
    private Button new_order;

    @FXML
    private TextField search_articul;

    @FXML
    private TextField search_name;


    @FXML
    void initialize() {
        ordersDAO = new OrdersDAO();
        WeakReference<OrdersDAO> weakReference = new WeakReference<>(ordersDAO);
        Column_Id.setCellValueFactory(new PropertyValueFactory<>("id"));
        Column_articul.setCellValueFactory(new PropertyValueFactory<>("product_articul"));
        Сolumn_amount.setCellValueFactory(new PropertyValueFactory<>("amount"));
        Сolumn_description.setCellValueFactory(new PropertyValueFactory<>("order_description"));
        Column_user.setCellValueFactory(new PropertyValueFactory<>("user"));
        Сolumn_oboryd.setCellValueFactory(new PropertyValueFactory<>("subdivision"));
        сolumn_status.setCellValueFactory(new PropertyValueFactory<>("status"));
        Column_date.setCellValueFactory(new PropertyValueFactory<>("date"));
        Column_table.setCellValueFactory(new PropertyValueFactory<>(""));
        List_order.setItems(ordersDAO.showListOfOrders());

        new_order.setOnAction(actionEvent -> {
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

        });
    }


}
