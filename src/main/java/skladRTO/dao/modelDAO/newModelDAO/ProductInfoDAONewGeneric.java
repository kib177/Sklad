package skladRTO.dao.modelDAO.newModelDAO;

import skladRTO.api.newModels.ProductInfoFX;

import java.util.List;

public class ProductInfoDAONewGeneric implements InterfaceDAOGeneric<ProductInfoFX, Integer> {
    @Override
    public int add(ProductInfoFX object) {
        return 0;
    }

    @Override
    public void update(ProductInfoFX object) {

    }

    @Override
    public void delete(Integer id) {

    }

    @Override
    public ProductInfoFX get(Integer id) {
        return null;
    }

    @Override
    public List<ProductInfoFX> getAll() {
        return null;
    }
}
