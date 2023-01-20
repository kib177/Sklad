package skladRTO.dao.modelDAO.newModelDAO;

import skladRTO.api.newModels.StatusFX;
import skladRTO.dao.connectDB.ConnectionBuilder;
import skladRTO.dao.connectDB.ConnectionBuilderFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class StatusDAOGenericNew implements InterfaceDAOGeneric<StatusFX, Integer> {
    private ConnectionBuilder builder = ConnectionBuilderFactory.getConnectionBuilder();

    private Connection getConnection() throws SQLException {
        return builder.getConnection();
    }

    @Override
    public int add(StatusFX object) {
        int id = 3;
        System.out.println("Добавляем юзера в БД и возвращаем его ID" + object);
        return id;
    }

    @Override
    public void update(StatusFX object) {

    }

    @Override
    public void delete(Integer id) {

    }

    @Override
    public StatusFX get(Integer id) {
        return null;
    }

    @Override
    public List<StatusFX> getAll() {
        return null;
    }
}
