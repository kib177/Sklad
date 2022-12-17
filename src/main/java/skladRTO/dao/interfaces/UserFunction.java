package skladRTO.dao.interfaces;

import javafx.collections.ObservableList;
import skladRTO.api.FX.models.UserFX;

public interface UserFunction {
    public ObservableList<UserFX> showListOfUsers();

    public void delete(int id);
}
