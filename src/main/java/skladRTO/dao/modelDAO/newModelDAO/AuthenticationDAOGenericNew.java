package skladRTO.dao.modelDAO.newModelDAO;

import skladRTO.api.newModels.AuthenticationFX;

import java.util.List;

public class AuthenticationDAOGenericNew implements InterfaceDAOGeneric<AuthenticationFX, Integer> {

    @Override
    public int add(AuthenticationFX object) {
        return 0;
    }

    @Override
    public void update(AuthenticationFX object) {

    }

    @Override
    public void delete(Integer id) {

    }

    @Override
    public AuthenticationFX get(Integer id) {
        return null;
    }

    @Override
    public List<AuthenticationFX> getAll() {
        return null;
    }
}
