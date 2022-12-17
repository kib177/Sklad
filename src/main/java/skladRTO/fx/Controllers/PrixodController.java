package skladRTO.fx.Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import skladRTO.api.FX.models.ProductFX;
import skladRTO.api.lists.ProductStatusList;
import skladRTO.api.models.Product;
import skladRTO.api.models.ProductStatus;
import skladRTO.api.models.ProductInfo;
import skladRTO.dao.modelDAO.ProductDAO;
import skladRTO.dao.modelDAO.ProductInfoDAO;
import skladRTO.fx.sceneFX.CreateScene;

import java.net.URL;
import java.util.ResourceBundle;

public class PrixodController implements Initializable {
    private static final Logger logger = LogManager.getLogger(PrixodController.class.getName());
    private ProductDAO getProduct = new ProductDAO();
    private ProductFX product = new ProductFX();
    private ProductInfoDAO productInfoDAO = new ProductInfoDAO();
    private CreateScene createScene;

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
    private TableView<ProductFX> Table_Product;
    @FXML
    private TextField articul;
    @FXML
    private TextField data;
    @FXML
    private TextArea description;
    @FXML
    private ChoiceBox<ProductStatus> status;
    private OrderViewController orderViewController;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        getProduct = new ProductDAO();
        status.setItems(ProductStatusList.getObservableList());
        Gone.setOnAction(actionEvent -> addProductInfo());
    }

    public void getOrderView(OrderViewController orderViewController){
        this.orderViewController = orderViewController;
    }

    public void addProduct(ProductFX prod) {
        this.product = prod;
        ObservableList<ProductFX> products = FXCollections.observableArrayList();
        products.add(product);
        Column_ID.setCellValueFactory(new PropertyValueFactory<>("id"));
        Column_Name.setCellValueFactory(new PropertyValueFactory<>("name"));
        Column_Number.setCellValueFactory(new PropertyValueFactory<>("amount"));
        Column_Status.setCellValueFactory(new PropertyValueFactory<>("status"));
        Table_Product.setItems(products);
    }

    public void close(CreateScene createScene) {
        this.createScene = createScene;
    }

    public void addProductInfo() {
        ProductInfo productInfo = new ProductInfo(productInfoDAO.getIdProduct_info(product.getId()), articul.getText(), data.getText(), description.getText());
        product.setStatusFX(getProduct.getIdStatus(String.valueOf(status.getValue()).trim()));
        getProduct.updateProduct(product, productInfo);
        orderViewController.viewProduct();
        createScene.close();
    }
}
