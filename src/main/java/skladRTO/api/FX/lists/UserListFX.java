package skladRTO.api.FX.lists;

import skladRTO.api.FX.models.UserFX;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class UserListFX {
    private ObservableList<UserFX> usersData = FXCollections.observableArrayList();

    public void create(int id, String login, String password, String firstName, String secondName, String status,
                       String email){
        usersData.add(new UserFX(id,login,password,firstName,secondName,status,email));
    }

    public ObservableList<UserFX> getUsersData() {
        return usersData;
    }

    public void setUsersData(ObservableList<UserFX> usersData) {
        this.usersData = usersData;
    }

    @Override
    public String toString() {
        return "ListUser{" +
                "usersData=" + usersData +
                '}';
    }
}
