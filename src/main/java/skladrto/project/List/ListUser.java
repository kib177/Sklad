package skladrto.project.List;

import skladrto.project.Model.FX.UserFX;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ListUser {
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
