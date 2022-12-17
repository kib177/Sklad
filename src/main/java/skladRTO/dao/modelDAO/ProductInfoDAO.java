package skladRTO.dao.modelDAO;

import javafx.collections.ObservableList;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import skladRTO.api.FX.lists.ProductInfoListFX;
import skladRTO.api.FX.models.ProductInfoFX;
import skladRTO.dao.connectDB.DatabaseConnection;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductInfoDAO {
    private static final Logger logger = LogManager.getLogger(ProductInfoDAO.class.getName());

    /**
     * Метод ищет объект product_info в БД по ID продукта
     *
     * @param id ID продукта
     * @return объект типа ProductInfoListFX
     */
    public ObservableList<ProductInfoFX> showListOfProducts(Integer id) {
        ProductInfoListFX listProduct = new ProductInfoListFX();
        WeakReference<ProductInfoListFX> weakReference = new WeakReference<>(listProduct);
        String str = "SELECT * FROM product_info " +
                " LEFT JOIN order_product ON (order_product.product_info_id=product_info.id)" +
                " WHERE order_product.id_product = ?;";
        logger.debug("Отправляем запрос в БД ->\n -> " + str);
        try (PreparedStatement preparedStatement = DatabaseConnection.getDatabaseConnection().prepareStatement(str);) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            logger.debug("Перебираем полученные данные из БД");
            while (resultSet.next()) {
                FillingInList(listProduct, resultSet);
            }
        } catch (SQLException | IOException e) {
            logger.warn("Произошла ошибка подключения к БД или ошибка записи/чтение данных из БД\n\t" + e);
            e.printStackTrace();
        }
        logger.debug("Возвращаем заполненный лист ProductInfoFX ->\n -> " + listProduct);
        return listProduct.getProductInfoFX();
    }

    /**
     * Метод возвращает ID product_info по ID продукта
     *
     * @param id ID продукта
     * @return ID product_info
     */
    public int getIdProduct_info(int id) {
        String str = "SELECT * FROM product_info " +
                " LEFT JOIN order_product ON product_info.id=order_product.product_info_id" +
                " WHERE order_product.id_product = ?;";
        logger.debug("Отправляем запрос в БД ->\n -> " + str);
        try (PreparedStatement preparedStatement = DatabaseConnection.getDatabaseConnection().prepareStatement(str)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                logger.debug("Возвращаю ID product_info " + resultSet.getInt("id"));
                return resultSet.getInt("id");
            }
        } catch (SQLException | IOException e) {
            logger.warn("Произошла ошибка подключения к БД или ошибка записи/чтение данных из БД\n\t" + e);
            e.printStackTrace();
        }
        logger.debug("ID product_info не был найден -> возвратиться 0");
        return 0;
    }

    /**
     * Метод заполняет лист ProductInfoListFX из ResultSet
     *
     * @param listProduct лист Product_Info
     * @param rs          ResultSet
     */
    public void FillingInList(ProductInfoListFX listProduct, ResultSet rs) {
        try {
            logger.debug("Выполняем метод заполнения листа  FillingInList");
            listProduct.create(rs.getInt("id"), rs.getString("description"),
                    rs.getString("articul"), rs.getString("arrival_date"));
        } catch (SQLException e) {
            logger.warn("Произошла ошибка подключения к БД или ошибка записи/чтение данных из БД\n\t" + e);
            throw new RuntimeException(e);
        }
    }
}

