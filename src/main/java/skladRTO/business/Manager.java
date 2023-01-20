package skladRTO.business;

import skladRTO.api.newModels.ModelFX;
import skladRTO.exaption.BusinessException;

import java.util.List;

/**
 * Разобраться с параметрами методов
 */
public interface Manager {
    // Добавление объекта - возвращает ID добавленного объекта
    int addObject(ModelFX object) throws BusinessException;

    // Редактирование объекта
    void updateObject(ModelFX object);

    // Удаление объекта по его ID
    void deleteObject(int contactId);

    // Получение одного объекта
    ModelFX getObject(int contactId);

    // Получение списка объектов
    List<ModelFX> getAll();
}
