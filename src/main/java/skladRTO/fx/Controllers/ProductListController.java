package skladRTO.fx.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import skladRTO.api.FX.models.ProductFX;
import skladRTO.api.lists.ProductStatusList;
import skladRTO.api.models.ProductStatus;
import skladRTO.dao.modelDAO.ProductDAO;

import java.net.URL;
import java.util.ResourceBundle;

public class ProductListController implements Initializable {
    private ProductDAO productDAO = new ProductDAO();
    @FXML
    private ChoiceBox<ProductStatus> ChoiceBox;
    @FXML
    private TableColumn<?, ?> ColumnProduct_amount;
    @FXML
    private TableColumn<?, ?> ColumnProduct_id;
    @FXML
    private TableColumn<ProductFX, String> Column_machine;
    @FXML
    private TableColumn<ProductFX, String> ColumnProduct_name;
    @FXML
    private TableColumn<?, ?> ColumnProduct_status;
    @FXML
    private TableView<ProductFX> Table_Items;
    @FXML
    private Button ViewAll;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        getProductDefault();
        ChoiceBox.setItems(ProductStatusList.getObservableList());
        ChoiceBox.setOnAction(actionEvent -> getProductChoiceBox());
    }

    public void getProductChoiceBox(){
        ColumnProduct_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        ColumnProduct_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        ColumnProduct_name.setCellFactory(tc-> textWrapProduct(ColumnProduct_name));
        Column_machine.setCellValueFactory(new PropertyValueFactory<>("machine"));
        Column_machine.setCellFactory(tc-> textWrapProduct(Column_machine));
        ColumnProduct_amount.setCellValueFactory(new PropertyValueFactory<>("AmountUnits"));
        ColumnProduct_status.setCellValueFactory(new PropertyValueFactory<>("status"));
        Table_Items.setItems(productDAO.getProductByStatus(ChoiceBox.getValue().getId()));
    }

    public void getProductDefault(){
        ColumnProduct_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        ColumnProduct_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        ColumnProduct_name.setCellFactory(tc-> textWrapProduct(ColumnProduct_name));
        Column_machine.setCellValueFactory(new PropertyValueFactory<>("machine"));
        Column_machine.setCellFactory(tc-> textWrapProduct(Column_machine));
        ColumnProduct_amount.setCellValueFactory(new PropertyValueFactory<>("AmountUnits"));
        ColumnProduct_status.setCellValueFactory(new PropertyValueFactory<>("status"));
        Table_Items.setItems(productDAO.showAllProducts());
    }

    public TableCell<ProductFX, String> textWrapProduct(TableColumn<ProductFX, String> tableColumn) {
        TableCell<ProductFX, String> cell = new TableCell<>();
        Text text = new Text();
        cell.setGraphic(text);
        cell.setPrefHeight(Control.USE_COMPUTED_SIZE);
        text.wrappingWidthProperty().bind(tableColumn.widthProperty());
        if(tableColumn == Column_machine) {
            text.setTextAlignment(TextAlignment.CENTER);
        }
        text.textProperty().bind(cell.itemProperty());
        return cell;
    }
    @FXML
    public void ViewAll(){
        getProductDefault();
    }
}
