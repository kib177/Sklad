package skladrto.project.List;

import skladrto.project.Model.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ListUser {
    private ObservableList<User> usersData = FXCollections.observableArrayList();

    public void create(int id, String login, String password, String firstName, String secondName, String status,
                       String email, String department){
        usersData.add(new User(id,login,password,firstName,secondName,status,email,department));
        // тоже самое читай в ListOrder
    }

    public ObservableList<User> getUsersData() {
        return usersData;
    }

    public void setUsersData(ObservableList<User> usersData) {
        this.usersData = usersData;
    }

    @Override
    public String toString() {
        return "ListUser{" +
                "usersData=" + usersData +
                '}';
    }
}
