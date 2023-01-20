package skladRTO.dao.modelDAO.newModelDAO;

import skladRTO.api.newModels.ModelFX;
import skladRTO.exaption.DaoException;

import java.util.List;

public interface InterfaceDAOGeneric<T extends ModelFX, K extends Number> {


    // Добавление объекта - возвращает ID добавленного объекта
    public int add(T object) throws DaoException;

    // Редактирование объекта
    public void update(T object) throws DaoException;

    // Удаление объекта по его ID
    public void delete(K id) throws DaoException;

    // Получение объекта по ID
    public T get(K id) throws DaoException;

    //Получение списка объектов
    public List<T> getAll() throws DaoException;
}
