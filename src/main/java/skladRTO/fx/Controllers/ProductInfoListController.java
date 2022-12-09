package skladRTO.fx.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Control;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import skladRTO.api.FX.models.OrderFX;
import skladRTO.api.FX.models.ProductInfoFX;
import skladRTO.dao.modelDAO.ProductInfoDAO;

import java.net.URL;
import java.util.ResourceBundle;

public class ProductInfoListController implements Initializable {
    private ProductInfoDAO productInfoDAO = new ProductInfoDAO();
    @FXML
    private TableColumn<ProductInfoFX, String> Column_Desc;

    @FXML
    private TableColumn<?, ?> Column_articyl;

    @FXML
    private TableColumn<?, ?> Column_date;

    @FXML
    private TableView<ProductInfoFX> TableView_Info;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void viewProductInfo(int id) {
        Column_Desc.setCellValueFactory(new PropertyValueFactory<>("des"));
        Column_Desc.setCellFactory(tc -> textWrap());
        Column_articyl.setCellValueFactory(new PropertyValueFactory<>("articul"));
        Column_date.setCellValueFactory(new PropertyValueFactory<>("date"));
        TableView_Info.setItems(productInfoDAO.showListOfProducts(id));
    }

    public TableCell<ProductInfoFX, String> textWrap() {
        TableCell<ProductInfoFX, String> cell = new TableCell<>();
        Text text = new Text();
        cell.setGraphic(text);
        cell.setPrefHeight(Control.USE_COMPUTED_SIZE);
        text.wrappingWidthProperty().bind(Column_Desc.widthProperty());
        text.textProperty().bind(cell.itemProperty());
        return cell;
    }
}
