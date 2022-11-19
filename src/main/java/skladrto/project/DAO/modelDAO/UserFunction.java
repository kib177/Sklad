package skladrto.project.DAO.modelDAO;

import javafx.collections.ObservableList;
import skladrto.project.Model.FX.UserFX;

public interface UserFunction {
    public ObservableList<UserFX> showListOfUsers();

    public void deleteUser();
}
