package skladRTO.fx.Controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import skladRTO.api.FX.models.MachinesFX;
import skladRTO.dao.modelDAO.MachinesDAO;
import skladRTO.fx.sceneFX.CreateScene;

import java.lang.ref.WeakReference;
import java.net.URL;
import java.util.ResourceBundle;

public class MachinesController implements Initializable {
    CreateScene createScene = new CreateScene();
    private MachinesDAO machinesDAO = new MachinesDAO();
    @FXML
    private Label LabelVibor;
    @FXML
    private TableColumn<?, ?> Column_id;
    @FXML
    private TableColumn<?, ?> Column_name;
    @FXML
    private TableView<MachinesFX> Table;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        viewMachine();
    }

    public void viewMachine() {
        LabelVibor.visibleProperty().setValue(false);
        WeakReference<MachinesDAO> weakReference = new WeakReference<>(machinesDAO);
        Column_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        Column_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        Table.setItems(machinesDAO.showAllMachine());
    }

    @FXML
    public void AddMachine() {
        createScene.createScene("Window.fxml", 400, 200, false);
        WindowController controller = createScene.getLoader().getController();
        controller.AddMachine(MachinesController.this);
    }

    @FXML
    public void UpdateMachine() {
        LabelVibor.visibleProperty().setValue(true);
        TableView.TableViewSelectionModel<MachinesFX> selectionModel = Table.getSelectionModel();
        selectionModel.selectedItemProperty().addListener(new ChangeListener<MachinesFX>() {
            @Override
            public void changed(ObservableValue<? extends MachinesFX> observableValue, MachinesFX orderFX, MachinesFX newMachine) {
                if (newMachine != null) {

                    createScene.createScene("Window.fxml", 400, 200, false);
                    WindowController controller = createScene.getLoader().getController();
                    controller.UpdateMachine(MachinesController.this, newMachine.getId());
                }
            }
        });
    }


    @FXML
    public void DeleteMachine() {
    LabelVibor.visibleProperty().setValue(true);
        TableView.TableViewSelectionModel<MachinesFX> selectionModel = Table.getSelectionModel();
        selectionModel.selectedItemProperty().addListener(new ChangeListener<MachinesFX>() {
            @Override
            public void changed(ObservableValue<? extends MachinesFX> observableValue, MachinesFX orderFX, MachinesFX newMachine) {
                if (newMachine != null) {

                    createScene.createScene("Window.fxml", 400, 200, false);
                    WindowController controller = createScene.getLoader().getController();
                    controller.deleteMachine(MachinesController.this, newMachine.getId());
                }
            }
        });
    }
}
