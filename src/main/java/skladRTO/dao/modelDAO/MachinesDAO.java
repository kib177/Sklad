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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MachinesDAO {
    private static final Logger logger = LogManager.getLogger(MachinesDAO.class.getName());
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

    public ObservableList<OrderFX> searchForMachine(String name){
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
}
