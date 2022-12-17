package skladRTO.api.lists;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import skladRTO.api.models.User;

public class UserNameList {
    private ObservableList<User> list = FXCollections.observableArrayList();

    public void create(int id, String FirstName, String LastName){
        list.add(new User(id, FirstName, LastName));
    }

    public ObservableList<User> getList() {
        return list;
    }

    public void setList(ObservableList<User> list) {
        this.list = list;
    }
}
