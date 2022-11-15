/* класс для работы окна новый заказ
 */
package skladrto.project;

import skladrto.project.RequestsDB.Add.addOrder;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.sql.SQLException;

public class orderNewController {
    @FXML
    private Button button_zakaz;

    @FXML
    private TextField order_articul;

    @FXML
    private TextField order_mashin;

    @FXML
    private TextField order_name;

    @FXML
    private TextField order_number;

    @FXML
    private TextArea order_proper;

    @FXML
    private TextField order_status;

    @FXML
    private TextField order_zakazchik;

    @FXML
    void initialize() {
        button_zakaz.setOnAction(actionEvent -> {
            try {
                addOrder.add(order_name.getText(), order_articul.getText(), order_proper.getText(), order_mashin.getText(),
                        order_status.getText(), order_number.getText(), order_zakazchik.getText());
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
