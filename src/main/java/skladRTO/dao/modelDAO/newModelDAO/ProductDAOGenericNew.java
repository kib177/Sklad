package skladRTO.dao.modelDAO.newModelDAO;

import skladRTO.api.newModels.ProductFX;

import java.util.List;

public class ProductDAOGenericNew implements InterfaceDAOGeneric<ProductFX, Integer> {
    @Override
    public int add(ProductFX object) {
        return 0;
    }

    @Override
    public void update(ProductFX object) {

    }

    @Override
    public void delete(Integer id) {

    }

    @Override
    public ProductFX get(Integer id) {
        return null;
    }

    @Override
    public List<ProductFX> getAll() {
        return null;
    }
}
