package skladRTO.api.lists;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import skladRTO.api.models.Machines;

public class MachinesList {
    private ObservableList<Machines> list = FXCollections.observableArrayList();

    public void create(int id, String name){
        list.add(new Machines(id, name));
    }

    public ObservableList<Machines> getList() {
        return list;
    }

    public void setList(ObservableList<Machines> list) {
        this.list = list;
    }
}
