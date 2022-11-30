package skladRTO.fx.Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import skladRTO.api.models.Product;
import skladRTO.api.models.ProductStatus;
import skladRTO.api.models.ProductInfo;
import skladRTO.dao.modelDAO.getProduct;

import java.net.URL;
import java.util.ResourceBundle;

public class PrixodController implements Initializable {
    private getProduct getProduct;
    private Product product;
    @FXML
    private Button Gone;
    @FXML
    private TableColumn<Product, String> Column_ID;
    @FXML
    private TableColumn<Product, String> Column_Name;
    @FXML
    private TableColumn<Product, String> Column_Number;
    @FXML
    private TableColumn<Product, String> Column_Status;
    @FXML
    private TableView<Product> Table_Product;
    @FXML
    private TextField articul;
    @FXML
    private TextField data;
    @FXML
    private TextArea description;
    @FXML
    private ChoiceBox<ProductStatus> status;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        getProduct = new getProduct();
        status.setItems(getProduct.getProductStatus());
        Gone.setOnAction(actionEvent -> addProductInfo());
    }

    public void addProduct(Product prod) {
        this.product = prod;
        ObservableList<Product> products = FXCollections.observableArrayList();
        products.add(product);
        Column_ID.setCellValueFactory(new PropertyValueFactory<>("id"));
        Column_Name.setCellValueFactory(new PropertyValueFactory<>("name"));
        Column_Number.setCellValueFactory(new PropertyValueFactory<>("amount"));
        Column_Status.setCellValueFactory(new PropertyValueFactory<>("status"));
        Table_Product.setItems(products);
    }

    public void addProductInfo() {

        getProduct = new getProduct();
        ProductInfo productInfo = new ProductInfo(articul.getText(), data.getText(), description.getText());
        product.setStatus(getProduct.getIdStatus(String.valueOf(status.getValue())));

        System.out.println(product);
        Gone.getScene().getWindow().hide();
    }
}
