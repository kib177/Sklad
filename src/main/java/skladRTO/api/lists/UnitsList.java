package skladRTO.api.lists;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import skladRTO.api.models.Units;

public class UnitsList {
    private ObservableList<Units> list = FXCollections.observableArrayList();

    public void create(int id, String name){
        list.add(new Units(id, name));
    }

    public ObservableList<Units> getList() {
        return list;
    }

    public void setList(ObservableList<Units> list) {
        this.list = list;
    }
}
