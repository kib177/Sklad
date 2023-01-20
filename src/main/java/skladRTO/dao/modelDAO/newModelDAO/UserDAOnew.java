package skladRTO.dao.modelDAO.newModelDAO;

import skladRTO.api.newModels.UserFX;

import java.util.List;

public class UserDAOnew implements InterfaceDAOGeneric<UserFX, Integer> {

    @Override
    public int add(UserFX object) {
        int id = 3;
        System.out.println("Добавляем юзера в БД и возвращаем его ID" + object);
        return id;
    }

    @Override
    public void update(UserFX object) {

    }

    @Override
    public void delete(Integer id) {

    }

    @Override
    public UserFX get(Integer id) {
        return null;
    }

    @Override
    public List<UserFX> getAll() {
        return null;
    }
}
