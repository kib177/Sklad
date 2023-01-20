package skladRTO.dao.modelDAO.newModelDAO;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import skladRTO.api.newModels.StatusUserFX;
import skladRTO.dao.connectDB.ConnectionBuilder;
import skladRTO.dao.connectDB.ConnectionBuilderFactory;
import skladRTO.dao.modelDAO.ProductInfoDAO;
import skladRTO.exaption.DaoException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class StatusUserDAONewGeneric implements InterfaceDAOGeneric<StatusUserFX, Integer> {

    private static final Logger logger = LogManager.getLogger(ProductInfoDAO.class.getName());
    private ConnectionBuilder builder = ConnectionBuilderFactory.getConnectionBuilder();

    private Connection getConnection() throws SQLException {
        return builder.getConnection();
    }

    @Override
    public int add(StatusUserFX object) throws DaoException {
        String str = "INSERT INTO status_user (status) VALUES (?)";
        logger.debug("Отправляем запрос в БД ->\n -> " + str);
        try (Connection con = getConnection();
             PreparedStatement pst = con.prepareStatement(str, new String[]{"id"})) {
            int id = -1;
            pst.setString(1, object.getName());
            pst.executeUpdate();
            ResultSet key = pst.getGeneratedKeys();
            logger.debug("Получение последнего созданного ИД из БД");
            if (key.next()) {
                id = key.getInt(1);
            }
            key.close();
            logger.debug("Возвращаем последний созданный ИД" + id);
            return id;
        } catch (Exception e) {
            logger.warn("Произошла ошибка подключения к БД или ошибка записи/чтение данных из БД\n\t" + e);
            throw new DaoException(e);
        }
    }

    @Override
    public void update(StatusUserFX object) throws DaoException {
        String str = "UPDATE status_user SET status=? WHERE id=?";
        logger.debug("Отправляем запрос в БД ->\n -> " + str);
        try (Connection con = getConnection();
             PreparedStatement pst = con.prepareStatement(str)) {
            pst.setString(1, object.getName());
            pst.setInt(2, object.getId());
            pst.executeUpdate();
        } catch (Exception e) {
            logger.warn("Произошла ошибка подключения к БД или ошибка записи/чтение данных из БД\n\t" + e);
            throw new DaoException(e);
        }
    }

    @Override
    public void delete(Integer id) throws DaoException {
        String str = "DELETE FROM status_user WHERE id =?;";
        logger.debug("Отправляем запрос в БД ->\n -> " + str);
        try (Connection connection = getConnection();
             PreparedStatement pst = connection.prepareStatement(str)) {
            pst.setInt(1, id);
            pst.executeUpdate();
        } catch (Exception e) {
            logger.warn("Произошла ошибка подключения к БД или ошибка записи/чтение данных из БД\n\t" + e);
            throw new DaoException(e);
        }
    }

    @Override
    public StatusUserFX get(Integer id) throws DaoException {
        String str = "SELECT id, status FROM status_user WHERE id=?";
        StatusUserFX status = null;
        logger.debug("Отправляем запрос в БД ->\n -> " + str);
        try (Connection con = getConnection()) {
//Почему pst не в трае???
            PreparedStatement pst = con.prepareStatement(str);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            logger.debug("Получение данных из БД");
            if (rs.next()) {
                status = fillStatusUser(rs);
            }
            rs.close();
            pst.close();
            logger.debug("Возвращаем найденный объект -> " + status);
            return status;
        } catch (Exception e) {
            logger.warn("Произошла ошибка подключения к БД или ошибка записи/чтение данных из БД\n\t" + e);
            throw new DaoException(e);
        }
    }

    @Override
    public List<StatusUserFX> getAll() throws DaoException {
        String str = "SELECT id, status FROM status_user WHERE id=? ORDER BY status";
        List<StatusUserFX> list = new LinkedList<>();
        logger.debug("Отправляем запрос в БД ->\n -> " + str);
        try (Connection con = getConnection();
             PreparedStatement pst = con.prepareStatement(str);
             ResultSet rs = pst.executeQuery()) {
            logger.debug("Перебираем полученные данные из БД");
            while (rs.next()) {
                list.add(fillStatusUser(rs));
            }
        } catch (Exception e) {
            logger.warn("Произошла ошибка подключения к БД или ошибка записи/чтение данных из БД\n\t" + e);
            throw new DaoException(e);
        }
        logger.debug("Возвращаем найденный лист объектов \n -> " + list);
        return list;
    }

    private StatusUserFX fillStatusUser(ResultSet rs) throws SQLException {
        logger.debug("Выполняем метод заполнения листа  fillStatusUser");
        StatusUserFX status = new StatusUserFX();
        status.setId(rs.getInt("id"));
        status.setName(rs.getString("status"));
        return status;
    }
}
