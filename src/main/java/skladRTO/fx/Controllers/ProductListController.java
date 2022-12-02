package skladRTO.fx.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import skladRTO.api.FX.models.ProductFX;
import skladRTO.api.lists.ProductStatusList;
import skladRTO.api.models.ProductStatus;
import skladRTO.dao.modelDAO.ProductDAO;

import java.net.URL;
import java.util.ResourceBundle;

public class ProductListController implements Initializable {
    @FXML
    private ChoiceBox<ProductStatus> ChoiceBox;
    @FXML
    private TableColumn<?, ?> ColumnProduct_amount;
    @FXML
    private TableColumn<?, ?> ColumnProduct_id;
    @FXML
    private TableColumn<?, ?> ColumnProduct_name;
    @FXML
    private TableColumn<?, ?> ColumnProduct_status;
    @FXML
    private TableView<ProductFX> Table_Items;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ChoiceBox.setItems(ProductStatusList.getObservableList());
        ChoiceBox.setOnAction(actionEvent -> getProduct());
    }

    public void getProduct(){
        ProductDAO productDAO = new ProductDAO();
        ColumnProduct_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        ColumnProduct_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        ColumnProduct_amount.setCellValueFactory(new PropertyValueFactory<>("amount"));
        ColumnProduct_status.setCellValueFactory(new PropertyValueFactory<>("status"));
        Table_Items.setItems(productDAO.getProduct(ChoiceBox.getValue().getId()));
    }
}
