package skladrto.project.DAO.modelDAO;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface FillingInListsDAO<T> {
    void FillingInList(T listOrder, ResultSet rs) throws SQLException;

}
