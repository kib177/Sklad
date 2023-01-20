package skladRTO.dao.modelDAO.newModelDAO;

import skladRTO.api.newModels.OrderFX;

import java.util.List;

public class OrderDAOGenericNew implements InterfaceDAOGeneric<OrderFX, Integer> {
    @Override
    public int add(OrderFX object) {
        return 0;
    }

    @Override
    public void update(OrderFX object) {

    }

    @Override
    public void delete(Integer id) {

    }

    @Override
    public OrderFX get(Integer id) {
        return null;
    }

    @Override
    public List<OrderFX> getAll() {
        return null;
    }
}
