package skladRTO.dao.modelDAO;

import javafx.collections.ObservableList;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import skladRTO.api.lists.UnitsList;
import skladRTO.api.models.Units;
import skladRTO.dao.connectDB.DatabaseConnection;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UnitsDAO {
    private static final Logger logger = LogManager.getLogger(UnitsDAO.class.getName());
    public ObservableList<Units> getUnits() {
        UnitsList listUnits = new UnitsList();
        try {
            ResultSet rs = DatabaseConnection.getStatement().executeQuery("SELECT * FROM units;");

            while (rs.next()) {
                listUnits.create(rs.getInt("id"), rs.getString("name"));
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
        return listUnits.getList();
    }

    public String searchNameUnits(int id) {
        String str = "SELECT * FROM units" +
                " WHERE id = ?;";
        logger.debug("Отправляем запрос в БД ->\n -> " + str);
        try (PreparedStatement preparedStatement = DatabaseConnection.getDatabaseConnection().prepareStatement(str)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            logger.debug("Перебираем полученные данные из БД");
            if (resultSet.next()) {
                return resultSet.getString("name");
            }
        } catch (SQLException | IOException e) {
            logger.warn("Произошла ошибка подключения к БД или ошибка записи/чтение данных из БД\n\t" + e);
            e.printStackTrace();
        }
        return null;
    }
}
