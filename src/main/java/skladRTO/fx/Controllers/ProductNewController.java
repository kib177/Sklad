package skladRTO.fx.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import skladRTO.api.models.Order;
import skladRTO.api.models.ProductAdd;
import skladRTO.dao.requestsDB.Get.GetOrdersDAO;

import java.lang.ref.WeakReference;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ProductNewController implements Initializable {
    private List<ProductAdd> list = new ArrayList<>();
    private Order order;
    @FXML
    private Button button_add;
    @FXML
    private Button Button_gone;
    @FXML
    private TextField Product_amount;
    @FXML
    private TextField Product_name;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        button_add.setOnAction(actionEvent -> ProductAdd(list));
        Button_gone.setOnAction(actionEvent -> gone(list));
    }
    public void ProductAdd(List<ProductAdd> list) {
        list.add(new ProductAdd(Product_name.getText(), Product_amount.getText()));
        Product_name.setText("");
        Product_amount.setText("");
    }

    public void createOrder(Order order) {
        this.order = order;
    }

    public void gone(List<ProductAdd> list) {

        GetOrdersDAO getOrdersDAO = new GetOrdersDAO();
        WeakReference<GetOrdersDAO> weakReference = new WeakReference<>(getOrdersDAO);

        getOrdersDAO.add(order, list);
        button_add.getScene().getWindow().hide();
    }


}