package skladRTO.dao.connectDB;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;

public class ComboConnectionBuilder implements ConnectionBuilder {
    private static final String URL = "jdbc:mysql://192.168.241.133:3306/sklad";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "admin";
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";

    private ComboPooledDataSource dataSource;

    public ComboConnectionBuilder() {
        dataSource = new ComboPooledDataSource();
        try {
            dataSource.setDriverClass(DRIVER);
            dataSource.setJdbcUrl(URL);
            dataSource.setUser(USERNAME);
            dataSource.setPassword(PASSWORD);
            dataSource.setMaxPoolSize(5);       //количество максимальных соединений
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        }
    }
        @Override
        public Connection getConnection () throws SQLException {
            return dataSource.getConnection();
        }
    }
