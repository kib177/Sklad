package skladRTO.api.FX.lists;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import skladRTO.api.FX.models.MachinesFX;

public class MachinesListFX {
    private ObservableList<MachinesFX> list = FXCollections.observableArrayList();

    public void create(int id, String name){
        list.add(new MachinesFX(id, name));
    }

    public ObservableList<MachinesFX> getList() {
        return list;
    }

    public void setList(ObservableList<MachinesFX> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "MachinesListFX{" +
                "list=" + list +
                '}';
    }
}
