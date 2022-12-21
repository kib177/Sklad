package skladRTO.dao.modelDAO;

import javafx.collections.ObservableList;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import skladRTO.api.FX.lists.UserListFX;
import skladRTO.api.FX.models.UserFX;
import skladRTO.api.lists.UserNameList;
import skladRTO.api.lists.UserStatusLIst;
import skladRTO.api.models.Authentication;
import skladRTO.api.models.StatusUser;
import skladRTO.api.models.User;
import skladRTO.dao.connectDB.DatabaseConnection;
import skladRTO.dao.interfaces.UserFunction;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.sql.*;


public class UserDAO implements UserFunction {
    private static final Logger logger = LogManager.getLogger(UserDAO.class.getName());

    /**
     * Метод заполняет лист всех юзеров из БД в необходимом формате
     *
     * @return лист всех юзеров
     */
    @Override
    public ObservableList<UserFX> showListOfUsers() {
        UserListFX listUser = new UserListFX();
        WeakReference<UserListFX> weakReference = new WeakReference<>(listUser);
        String str = "SELECT users.id_users, users.first_name, users.last_name, authentication.login, " +
                "authentication.password, authentication.email, status_user.status" +
                " FROM users " +
                " LEFT JOIN authentication ON (users.authentication_id=authentication.id) " +
                " LEFT JOIN status_user ON (authentication.status_user_id=status_user.id);";
        logger.debug("Отправляем запрос в БД ->\n -> " + str);
        try (ResultSet resultSet = DatabaseConnection.getStatement().executeQuery(str)) {
            logger.debug("Перебираем полученные данные из БД");
            while (resultSet.next()) {
                logger.debug("Записываем данные из БД в объект типа UserListFX");
                listUser.create(resultSet.getInt("id_users"), resultSet.getString("login"),
                        resultSet.getString("password"), resultSet.getString("first_name"),
                        resultSet.getString("last_name"), resultSet.getString("status"),
                        resultSet.getString("email"));
            }
        } catch (SQLException | IOException e) {
            logger.warn("Произошла ошибка подключения к БД или ошибка записи/чтение данных из БД\n\t" + e);
            e.printStackTrace();
        }
        logger.debug("Возвращаем заполненный лист юзеров ->\n -> " + listUser);
        return listUser.getUsersData();
    }

    /**
     * Метод находит юзера по его ID
     *
     * @param id ID юзера
     * @return объект типа User
     */
    public User getUser(int id) {
        User user = null;
        String str = "SELECT * FROM sklad.users WHERE users.authentication_id = ?;";
        logger.debug("Отправляем запрос в БД ->\n -> " + str);
        try (Connection connection = DatabaseConnection.getDatabaseConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(str)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            logger.debug("Получение данных из БД");
            if (resultSet.next()) {
                user = new User(resultSet.getInt("id_users"), resultSet.getString("first_name"),
                        resultSet.getString("last_name"), resultSet.getInt("authentication_id"));
                logger.debug("Запись данных из БД в объект User ->\n-> " + user);
            }
        } catch (SQLException | IOException e) {
            logger.warn("Произошла ошибка подключения к БД или ошибка записи/чтение данных из БД\n\t" + e);
            e.printStackTrace();
        }
        logger.debug("Возвращаем полученный объект типа User -> " + user);
        return user;
    }

    /**
     * Метод определяет статус юзера по ID
     *
     * @param id status_user id
     * @return объект StatusUser
     */
    public StatusUser getStatusUser(int id) {
        StatusUser statusUser = null;
        String str = "SELECT * FROM sklad.status_user WHERE status_user.id = ?;";
        logger.debug("Отправляем запрос в БД ->\n -> " + str);
        try (Connection connection = DatabaseConnection.getDatabaseConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(str)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            logger.debug("Получение данных из БД");
            if (resultSet.next()) {
                statusUser = new StatusUser(resultSet.getString("status"));
                logger.debug("Запись данных из БД в объект User ->\n-> " + statusUser);
            }
        } catch (SQLException | IOException e) {
            logger.warn("Произошла ошибка подключения к БД или ошибка записи/чтение данных из БД\n\t" + e);
            e.printStackTrace();
        }
        logger.debug("Возвращаем полученный объект типа StatusUser -> " + statusUser);
        return statusUser;
    }

    /**
     * Метод ищет в БД совпадение login and password
     *
     * @param login    логин введенный пользователем
     * @param password пароль введенный пользователем
     * @return найденный объект при совпадении, иначе null
     */
    public Authentication getAuthentication(String login, String password) {
        Authentication authentication = null;
        String str = "SELECT * FROM authentication WHERE login = ? AND password = ?";
        logger.debug("Отправляем запрос в БД ->\n -> " + str);
        try (Connection connection = DatabaseConnection.getDatabaseConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(str)) {
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            logger.debug("Получение данных из БД");
            if (resultSet.next()) {
                authentication = new Authentication(resultSet.getInt("id"), resultSet.getString("login"),
                        resultSet.getString("password"), resultSet.getInt("status_user_id"),
                        resultSet.getString("email"));
                logger.debug("Запись данных из БД в объект Authentication ->\n-> " + authentication);
            }
        } catch (SQLException | IOException e) {
            logger.warn("Произошла ошибка подключения к БД или ошибка записи/чтение данных из БД\n\t" + e);
            e.printStackTrace();
        }
        logger.debug("Возвращаем полученный объект типа Authentication -> " + authentication);
        return authentication;
    }

    /**
     * Метод возвращает всех юзеров
     *
     * @return лист юзеров типа UserStatusLIst
     */
    public ObservableList<StatusUser> getStatus() {
        UserStatusLIst listUserStatus = new UserStatusLIst();
        String str = "SELECT * FROM status_user;";
        try {
            logger.debug("Отправляем запрос в БД ->\n -> " + str);
            ResultSet rs = DatabaseConnection.getStatement().executeQuery(str);
            logger.debug("Перебираем полученные данные из БД");
            while (rs.next()) {
                listUserStatus.create(rs.getString("status"));
            }
        } catch (SQLException | IOException e) {
            logger.warn("Произошла ошибка подключения к БД или ошибка записи/чтение данных из БД\n\t" + e);
            e.printStackTrace();
        }
        logger.debug("Возвращаем полученный список типа UserStatusLIst -> " + listUserStatus);
        return listUserStatus.getObservableList();
    }

    /**
     * Метод удаляет объект User из БД по ID
     *
     * @param id ID юзера
     */
    @Override
    public void delete(int id) {
        String str = "DELETE FROM users WHERE id_users =?;";
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
     * Метод проверяет есть ли юзер в БД с таким ID
     *
     * @param id ID юзера
     * @return возвращает Boolean значение в зависимости от результата
     */
    public Boolean pereborID(int id) {
        String str = "SELECT * FROM users WHERE id_users =?;";
        logger.debug("Отправляем запрос в БД ->\n -> " + str);
        try (Connection connection = DatabaseConnection.getDatabaseConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(str)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            logger.debug("Получение данных из БД");
            if (resultSet.next()) {
                logger.debug("Возвращаем значение true");
                return true;
            }
        } catch (SQLException | IOException e) {
            logger.warn("Произошла ошибка подключения к БД или ошибка записи/чтение данных из БД\n\t" + e);
            e.printStackTrace();
        }
        logger.debug("Возвращаем значение false");
        return false;
    }

    public void update() {

    }

    /**
     * Метод добавляет пользователя в БД. В методе используется транзакция. Сначала по указанному статусу возвращаем ID статуса,
     * затем заполняем таблицу authentication в БД из данных нового пользователя,
     * далее возвращаем вновь созданный ID из таблицы authentication, затем делаем проверку вернулся ли ID, если да тогда
     * заполняем таблицу users.
     *
     * @param firstName  имя пользователя
     * @param secondName фамилия пользователя
     * @param login      логин пользователя
     * @param email      почта пользователя
     * @param password   пароль пользователя
     * @param status     статус пользователя
     */
    public void add(String firstName, String secondName, String login, String email, String password, String status) {
        try (Connection connection = DatabaseConnection.getDatabaseConnection()) {
            logger.debug("Создание Savepoint для транзакции");
            connection.setAutoCommit(false);
            Savepoint savepointOne = connection.setSavepoint("SavepointOne");
            String userSQL = "INSERT INTO users (first_name, last_name, authentication_id) Values (?, ?, ?);";
            String authenticationSQL = "INSERT INTO authentication (login,password,email,status_user_id)" +
                    "VALUES(?,?,?,?);";
            String statusSQL = "SELECT id FROM sklad.status_user" +
                    " where status= ? ;";
            int num = 0;
            logger.debug("Отправляем запрос в БД ->\n -> " + statusSQL);
            try (PreparedStatement preparedStatement = connection.prepareStatement(statusSQL)) {
                preparedStatement.setString(1, status);
                ResultSet resultSet = preparedStatement.executeQuery();
                logger.debug("Получение данных из БД");
                if (resultSet.next()) {
                    num = resultSet.getInt(1);
                    logger.debug("Запись данных из БД -> " + num);
                }
                logger.debug("Отправляем запрос в БД ->\n -> " + authenticationSQL);
                try (PreparedStatement preparedStatement2 = connection.prepareStatement(authenticationSQL)) {
                    preparedStatement2.setString(1, login);
                    preparedStatement2.setString(2, password);
                    preparedStatement2.setString(3, email);
                    preparedStatement2.setInt(4, num);
                    preparedStatement2.executeUpdate();
                    int lastInsertId = 0;
                    try (Statement statement = connection.createStatement();) {
                        String str1 = "SELECT LAST_INSERT_ID();";
                        logger.debug("Отправляем запрос в БД ->\n -> " + str1);
                        ResultSet resultSet1 = statement.executeQuery(str1);
                        logger.debug("Получение данных из БД");
                        if (resultSet1.next()) {
                            lastInsertId = resultSet1.getInt(1);
                            logger.debug("Запись данных из БД последнего ID-> " + lastInsertId);
                        }
                        if (lastInsertId != 0) {
                            logger.debug("Отправляем запрос в БД ->\n -> " + userSQL);
                            try (PreparedStatement preparedStatement1 = connection.prepareStatement(userSQL)) {
                                preparedStatement1.setString(1, firstName);
                                preparedStatement1.setString(2, secondName);
                                preparedStatement1.setInt(3, lastInsertId);
                                preparedStatement1.executeUpdate();
                            } catch (Exception e) {
                                logger.warn("Произошла ошибка транзакции -> возвращение на savepoint \n\t " +
                                        "SQLException. Executing rollback to savepoint...\n" + e);
                                connection.rollback(savepointOne);
                            }
                        } else {
                            logger.warn("Произошла ошибка транзакции -> возвращение на savepoint \n\t " +
                                    "SQLException. Executing rollback to savepoint...\n");
                            connection.rollback(savepointOne);
                        }
                    } catch (SQLException e) {
                        logger.warn("Произошла ошибка подключения к БД или ошибка записи/чтение данных из БД\n\t" + e);
                        e.printStackTrace();
                    }
                } catch (SQLException e) {
                    logger.warn("Произошла ошибка подключения к БД или ошибка записи/чтение данных из БД\n\t" + e);
                    e.printStackTrace();
                }
                logger.debug("Транзакция выполнена успешно");
                connection.commit();
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                logger.warn("Произошла ошибка подключения к БД или ошибка записи/чтение данных из БД\n\t" + e);
                e.printStackTrace();
            }
        } catch (SQLException | IOException e) {
            logger.warn("Произошла ошибка подключения к БД или ошибка записи/чтение данных из БД\n\t" + e);
        }
    }

    /**
     * Метод получает из БД ID, ИМЯ и ФАМИЛИЮ всех юзеров
     *
     * @return лист юзеров
     */
    public ObservableList<User> showListName() {
        UserNameList listUser = new UserNameList();
        WeakReference<UserNameList> weakReference = new WeakReference<>(listUser);
        String str = "SELECT users.id_users, users.first_name, users.last_name FROM users";
        logger.debug("Отправляем запрос в БД ->\n -> " + str);
        try (
                ResultSet resultSet = DatabaseConnection.getStatement().executeQuery(str)) {
            logger.debug("Перебираем полученные данные из БД");
            while (resultSet.next()) {
                listUser.create(resultSet.getInt("id_users"), resultSet.getString("first_name"),
                        resultSet.getString("last_name"));
            }
        } catch (SQLException | IOException e) {
            logger.warn("Произошла ошибка подключения к БД или ошибка записи/чтение данных из БД\n\t" + e);
            e.printStackTrace();
        }
        logger.debug("Возвращаем полученный список типа UserNameList -> " + listUser);
        return listUser.getList();
    }
}
