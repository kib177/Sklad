package skladRTO.dao.modelDAO;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import skladRTO.api.models.Authorization;
import skladRTO.dao.connectDB.DatabaseConnection;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AuthorizationDAO {

    private static final Logger logger = LogManager.getLogger(AuthorizationDAO.class.getName());
    public static final String selectSQL = "SELECT authentication.login, " +
            "authentication.password FROM authentication";
    public static final String SELECT_SQL = selectSQL;

    /**
     * Метод для проверки авторизации пользователя. Принимает на вход имя и пароль введенные пользователем
     * затем он копирует данные из БД с пользователями и проверяет их на совпадение.
     * <p>
     * ВАЖНО!!!: Возможно лучше не копировать всю таблицу из БД и делать проверку, а передать имя и пароль
     * в строку поиска в БД
     *
     * @param name     введенное пользователем имя
     * @param password введенный пользователем пароль
     * @return возвращает true, если пользователь с таким login and password существует в БД
     */
    public Boolean CheckUsers(String name, String password) {
        //странная строка
        logger.debug("Начало проверки авторизации пользователя\n отправка запроса в БД -> \n->"+ SELECT_SQL);
        skladRTO.dao.modelDAO.UserDAO getUserDAO = new UserDAO();
        try (Connection connection = DatabaseConnection.getDatabaseConnection();
             Statement statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery(SELECT_SQL);
            logger.debug("Перебираем полученные данные и БД");
            while (rs.next()) {
                if (name.equalsIgnoreCase(rs.getString("login"))
                        & password.equals(rs.getString("password"))) {
                    logger.debug("Найдено совпадение с пользователем" + "Login: " + name + "; Password: " + password + ";");
                    Authorization.setAuth(getUserDAO.getAuthentication(rs.getString("login"),
                            rs.getString("password")));
                    logger.debug("Заполняем аутент. данные пользователя -> auth: " + Authorization.getAuth());
                    Authorization.setStatusUser(getUserDAO.getStatusUser(Authorization.getAuth().getStatusUserId()));
                    logger.debug("Заполняем статус данные пользователя -> status: " + Authorization.getStatusUser());
                    Authorization.setUser(getUserDAO.getUser(Authorization.getAuth().getId()));
                    logger.debug("Заполняем Юзер данные пользователя -> user: " + Authorization.getUser());
                    return true;
                }
            }
        } catch (SQLException | IOException e) {
            logger.warn("Произошла ошибка подключения к БД или ошибка записи/чтение данных из БД\n\t" + e);
            e.printStackTrace();
        }
        return false;
    }
}
