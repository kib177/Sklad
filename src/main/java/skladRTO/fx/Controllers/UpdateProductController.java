package skladRTO.fx.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import skladRTO.api.FX.models.ProductFX;
import skladRTO.api.models.Machines;
import skladRTO.dao.modelDAO.MachinesDAO;
import skladRTO.dao.modelDAO.ProductDAO;

import java.lang.ref.WeakReference;
import java.net.URL;
import java.util.ResourceBundle;


public class UpdateProductController implements Initializable {
    private OrderViewController orderViewController;
    private MachinesDAO machinesDAO = new MachinesDAO();
    private ProductFX productFX;
    @FXML
    private ChoiceBox<Machines> ChoiceBox_Machine;
    @FXML
    private TextArea TextArea;
    @FXML
    private TextField TextField_Number;
    @FXML
    private Button Button_back;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
//        ChoiceBox_Machine.setItems(machinesDAO.getMachineName());
//        TextArea.setText(productFX.getName());
//        TextField_Number.setText(String.valueOf(productFX.getAmount()));
 }

    @FXML
    public void Button_Gone() {
//        productFX.setAmount(Integer.parseInt(TextField_Number.getText()));
        productFX.setName(TextArea.getText());
//        productFX.setMachine(ChoiceBox_Machine.getValue().getName());
        ProductDAO productDAO = new ProductDAO();
        WeakReference<ProductDAO> weakReference = new WeakReference<>(productDAO);
        productDAO.updateProduct(productFX);
        orderViewController.viewProduct();
    }

    @FXML
    public void Button_back() {
        Button_back.getScene().getWindow().hide();
    }

    public void getProduct(ProductFX productFX) {
        this.productFX = productFX;
    }

    public void getOrderViewController(OrderViewController orderViewController){
        this.orderViewController = orderViewController;
    }
}
