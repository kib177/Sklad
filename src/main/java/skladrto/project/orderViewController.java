/* класс окна отображения заказов и кнопка для перехода на окно нового заказа
 */
package skladrto.project;


import java.io.IOException;

import skladrto.project.List.ListOrder;
import skladrto.project.Model.Tovar;
import skladrto.project.RequestsDB.Get.getOrder;
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

    @FXML
    private TableColumn<?, ?> Column_name;

    @FXML
    private TableView<Tovar> List_order;

    @FXML
    private Button Watch_order;

    @FXML
    private TableColumn<?, ?> column_articul;

    @FXML
    private TableColumn<?, ?> column_kol;

    @FXML
    private TableColumn<?, ?> column_oboryd;

    @FXML
    private TableColumn<?, ?> column_opis;

    @FXML
    private TableColumn<?, ?> column_status;

    @FXML
    private TableColumn<?, ?> column_zakaz;

    @FXML
    private Button new_order;

    @FXML
    private TextField search_articul;

    @FXML
    private TextField search_name;

    @FXML
    void initialize() {
            getOrder.GetOrder(); // вызываем метод для данных из бд
        // присвоение колонкам в таблице окна данных, имена в ковычках должны соответствовать именам в классе объекте (Tovar).
            Column_name.setCellValueFactory(new PropertyValueFactory<>("name"));
            column_status.setCellValueFactory(new PropertyValueFactory<>("status"));
            column_opis.setCellValueFactory(new PropertyValueFactory<>("opis"));
            column_kol.setCellValueFactory(new PropertyValueFactory<>("kol"));
            column_articul.setCellValueFactory(new PropertyValueFactory<>("articul"));
            column_oboryd.setCellValueFactory(new PropertyValueFactory<>("oboryd"));
            column_zakaz.setCellValueFactory(new PropertyValueFactory<>("zakazchik"));
            List_order.setItems(ListOrder.getUsersData());// присвоение таблице лист с данными из бд, которая содержит в себе выше перечисленные колонки,
        // расскидывает исходя из имен колонок поэтому имена колонок должны быть точно такие как в классе объекте

            new_order.setOnAction(actionEvent -> { // кнопка перехода в окно нового заказа
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
