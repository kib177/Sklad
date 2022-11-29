package skladRTO.fx.Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import skladRTO.api.models.FX.ProductFX;
import skladRTO.api.models.StatusUser;
import skladRTO.dao.requestsDB.Get.getUserDAO;

import java.net.URL;
import java.util.ResourceBundle;

public class PrixodController implements Initializable {
    private getUserDAO getUserDAO;
    @FXML
    private TableColumn<ProductFX, String> Column_ID;
    @FXML
    private TableColumn<ProductFX, String> Column_Name;
    @FXML
    private TableColumn<ProductFX, String> Column_Number;
    @FXML
    private TableColumn<ProductFX, String> Column_Status;
    @FXML
    private TableView<ProductFX> Table_Product;
    @FXML
    private TextField articul;
    @FXML
    private TextField data;
    @FXML
    private TextArea description;
    @FXML
    private ChoiceBox<StatusUser> status;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        getUserDAO = new getUserDAO();
        status.setItems(getUserDAO.getStatus());
    }

    public void addProductFX(ProductFX productFX) {
        ObservableList<ProductFX> product = FXCollections.observableArrayList();
        product.add(productFX);
        Column_ID.setCellValueFactory(new PropertyValueFactory<>("id"));
        Column_Name.setCellValueFactory(new PropertyValueFactory<>("name"));
        Column_Number.setCellValueFactory(new PropertyValueFactory<>("amount"));
        Column_Status.setCellValueFactory(new PropertyValueFactory<>("status"));
        Table_Product.setItems(product);
    }

    public void addProductInfo(){
        description.getText();
        articul.getText();
        data.getText();
    }
}
