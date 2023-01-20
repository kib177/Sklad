package skladRTO.business;

import skladRTO.api.newModels.ModelFX;
import skladRTO.api.newModels.ModelsFX;
import skladRTO.dao.factory.DAOFactory;
import skladRTO.dao.modelDAO.newModelDAO.InterfaceDAOGeneric;
import skladRTO.exaption.BusinessException;
import skladRTO.exaption.DaoException;

import java.util.List;

public class ModelManagerDAO implements Manager{
    /**
     * Ругается на Дженерик интерфейс
     * убрать потом ретерн нал, поменять исключения на наши
     */
    private final InterfaceDAOGeneric dao;

    public ModelManagerDAO(ModelsFX model) {
        dao = DAOFactory.getInterfaceDAO(model);
    }
    @Override
    // Добавление объекта - возвращает ID добавленного объекта
    public int addObject(ModelFX object) throws BusinessException {
        try {
            return dao.add(object);
        } catch (DaoException ex) {
            throw new BusinessException(ex);
        }
    }
    @Override
    // Редактирование объекта
    public void updateObject(ModelFX object)  {
        try {
            dao.update(object);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    // Удаление объекта по его ID
    public void deleteObject(int contactId)  {
        try {
            dao.delete(contactId);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    // Получение одного объекта
    public ModelFX getObject(int contactId)  {
        try {
            return dao.get(contactId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    @Override
    // Получение списка объектов

    public List<ModelFX> getAll() {
        try {
            return dao.getAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
