package skladRTO.dao.interfaces;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface FillingInListsDAO<T> {


    void FillingInList(T list, ResultSet rs) throws SQLException;

}
