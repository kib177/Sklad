package skladRTO.fx.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import skladRTO.api.FX.models.ProductFX;
import skladRTO.api.models.Order;
import skladRTO.dao.modelDAO.UserDAO;
import skladRTO.dao.modelDAO.ver1_1.OrdersDAO;
import skladRTO.dao.modelDAO.ver1_1.ProductDAO;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class WindowController implements Initializable {
    @FXML
    private Button button_cancel;
    @FXML
    private Button button_gone;
    @FXML
    private Label label_text;
    private OrderViewController orderViewController;
    private UsersViewController usersViewController;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        label_text.setText("");
        button_cancel.setOnAction(actionEvent -> button_cancel.getScene().getWindow().hide());
    }

    public void deleteOrder(Order order, List<ProductFX> list) {
        OrdersDAO ordersDAO = new OrdersDAO();
        label_text.setText("Вы действительно хотите удалить заказ №" + order.getId());
        button_gone.setOnAction(actionEvent -> {
            ordersDAO.delete(order, list);
            orderViewController.Watch_order(new ActionEvent());
            button_gone.getScene().getWindow().hide();
        });
    }

    public void getUsersController(UsersViewController usersViewController) {
        this.usersViewController = usersViewController;
    }

    public void getOrderView(OrderViewController orderViewController) {
        this.orderViewController = orderViewController;
    }

    public void deleteProduct(ProductFX product) {
        ProductDAO productDAO = new ProductDAO();
        label_text.setText("Вы действительно хотите удалить позицию №" + product.getId());
        button_gone.setOnAction(actionEvent -> {
            productDAO.delete(product);
            orderViewController.viewProduct();
            button_gone.getScene().getWindow().hide();
        });
    }

    public void deleteUser(int id) {
        UserDAO userDAO = new UserDAO();
        label_text.setText("Вы действительно хотите удалить пользователя " + id);
        button_gone.setOnAction(actionEvent -> {
            userDAO.delete(id);
            usersViewController.showUsers();
            button_gone.getScene().getWindow().hide();
        });
    }
}
