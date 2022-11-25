package skladRTO.dao.modelDAO;

import javafx.collections.ObservableList;
import skladRTO.api.models.FX.UserFX;

public interface UserFunction {
    public ObservableList<UserFX> showListOfUsers();

    public void deleteUser();
}
