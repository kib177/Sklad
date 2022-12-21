package skladRTO.dao.modelDAO;

import javafx.collections.ObservableList;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import skladRTO.api.FX.lists.MachinesListFX;
import skladRTO.api.FX.lists.OrderListFX;
import skladRTO.api.FX.models.MachinesFX;
import skladRTO.api.FX.models.OrderFX;
import skladRTO.api.lists.MachinesList;
import skladRTO.api.models.Machines;
import skladRTO.dao.connectDB.DatabaseConnection;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MachinesDAO {
    private static final Logger logger = LogManager.getLogger(MachinesDAO.class.getName());

    /**
     * @return
     */
    public ObservableList<Machines> getMachineName() {
        MachinesList listMachine = new MachinesList();
        try {
            ResultSet rs = DatabaseConnection.getStatement().executeQuery("SELECT * FROM machines;");

            while (rs.next()) {
                listMachine.create(rs.getInt("id"), rs.getString("machine"));
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
        return listMachine.getList();
    }

    /**
     * @param name
     * @return
     */
    public ObservableList<OrderFX> searchForMachine(String name) {
        OrderListFX listOrder = new OrderListFX();
        WeakReference<OrderListFX> weakReference = new WeakReference<>(listOrder);
        String str = "SELECT * FROM orders" +
                " LEFT JOIN users ON (orders.user_id=users.id_users)" +
                " LEFT JOIN machines ON (orders.machines_id=machines.id)" +
                " WHERE machines.machine LIKE '%" + name + "%'";
        logger.debug("Отправляем запрос в БД ->\n -> " + str);
        try (PreparedStatement preparedStatement = DatabaseConnection.getDatabaseConnection().prepareStatement(str)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            logger.debug("Перебираем полученные данные из БД");
            while (resultSet.next()) {
                FillingInList(listOrder, resultSet);
            }
        } catch (SQLException | IOException e) {
            logger.warn("Произошла ошибка подключения к БД или ошибка записи/чтение данных из БД\n\t" + e);
            e.printStackTrace();
        }
        logger.debug("Возвращаем заполненный лист ордеров  ->\n -> " + listOrder);
        return listOrder.getOrderData();
    }

    /**
     * @param listOrder
     * @param resultSet
     */
    public void FillingInList(OrderListFX listOrder, ResultSet resultSet) {
        try {
            logger.debug("Выполняем метод заполнения листа  FillingInList");
            listOrder.create(resultSet.getInt("id"), resultSet.getString("number_order"),
                    resultSet.getString("order_description"), resultSet.getString("machine"),
                    resultSet.getString("first_name"), resultSet.getString("last_name"),
                    resultSet.getString("order_date"));
        } catch (SQLException e) {
            logger.warn("Произошла ошибка подключения к БД или ошибка записи/чтение данных из БД\n\t" + e);
            throw new RuntimeException(e);
        }
    }

    /**
     * @return
     */
    public ObservableList<MachinesFX> showAllMachine() {
        MachinesListFX list = new MachinesListFX();
        WeakReference<MachinesListFX> weakReference = new WeakReference<>(list);
        try {
            ResultSet resultSet = DatabaseConnection.getStatement().executeQuery("SELECT * FROM machines ");

            while (resultSet.next()) {
                list.create(resultSet.getInt("id"), resultSet.getString("machine"));
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
        return list.getList();
    }

    /**
     * Метод удаляет объект machines из БД по ID
     *
     * @param id ID юзера
     */
    public void delete(int id) {
        String str = "DELETE FROM machines WHERE id =?;";
        logger.debug("Отправляем запрос в БД ->\n -> " + str);
        try (Connection connection = DatabaseConnection.getDatabaseConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(str)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException | IOException e) {
            logger.warn("Произошла ошибка подключения к БД или ошибка записи/чтение данных из БД\n\t" + e);
            e.printStackTrace();
        }
    }

    /**
     * Метод удаляет объект machines из БД
     *
     * @param name название оборудования
     */
    public void create(String name) {
        String str = "INSERT INTO machines (machine) Values (?);";
        logger.debug("Отправляем запрос в БД ->\n -> " + str);
        try (Connection connection = DatabaseConnection.getDatabaseConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(str)) {
            preparedStatement.setString(1, name);
            preparedStatement.executeUpdate();
            logger.debug("Метод выполнен успешно");
        } catch (SQLException | IOException e) {
            logger.warn("Произошла ошибка подключения к БД или ошибка записи/чтение данных из БД\n\t" + e);
            e.printStackTrace();
        }
    }

    /**
     * Метод изменяет объект machines в БД
     *
     * @param id   ID оборудования
     * @param name название оборудования
     */
    public void update(int id, String name) {
        String str = "UPDATE machines SET machines = ? WHERE id = ?";
        logger.debug("Отправляем запрос в БД ->\n -> " + str);
        try (Connection connection = DatabaseConnection.getDatabaseConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(str)) {
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();
            logger.debug("Метод выполнен успешно");
        } catch (SQLException | IOException e) {
            logger.warn("Произошла ошибка подключения к БД или ошибка записи/чтение данных из БД\n\t" + e);
            e.printStackTrace();
        }
    }
}
