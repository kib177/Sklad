package skladRTO.dao.connectDB;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Интерфейс для создания подключения к БД
 */
public interface ConnectionBuilder {
    Connection getConnection() throws SQLException;
}
