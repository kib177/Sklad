package skladrto.project.RequestsDB.Get;

import javafx.collections.ObservableList;
import skladrto.project.Model.User;

public interface UserFunction {
    public ObservableList<User> showListOfUsers();

    public void deleteUser();
}
