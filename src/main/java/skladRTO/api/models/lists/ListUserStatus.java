package skladRTO.api.models.lists;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import skladRTO.api.models.StatusUser;

public class ListUserStatus {
    private ObservableList<StatusUser> observableList = FXCollections.observableArrayList();

    public void create(String status){
        observableList.add(new StatusUser(status));
    }

    public ObservableList<StatusUser> getObservableList() {
        return observableList;
    }

    public void setObservableList(ObservableList<StatusUser> observableList) {
        this.observableList = observableList;
    }
}
