package skladRTO.fx.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import skladRTO.api.FX.models.MachinesFX;
import skladRTO.dao.modelDAO.MachinesDAO;

import java.lang.ref.WeakReference;
import java.net.URL;
import java.util.ResourceBundle;

public class MachinesController implements Initializable {
    private MachinesDAO machinesDAO = new MachinesDAO();
    @FXML
    private TableColumn<?, ?> Column_id;
    @FXML
    private TableColumn<?, ?> Column_name;
    @FXML
    private TableView<MachinesFX> Table;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        WeakReference<MachinesDAO> weakReference = new WeakReference<>(machinesDAO);
        Column_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        Column_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        Table.setItems(machinesDAO.showAllMachine());
    }
}
