package skladRTO.dao.interfaces.ver1_1;

import javafx.collections.ObservableList;
import skladRTO.api.FX.models.OrderFX;
import skladRTO.api.FX.models.ProductFX;
import skladRTO.api.models.Order;
import skladRTO.api.models.Product;

import java.util.List;

public interface OrdersDAOInterface extends CRUDInterfaceDAO<Integer, Order> {
    public List<Order> findEntityByName(String nameDescriptionOrder);
    public List<Order> findEntityByNumber(String numberOrder);
    public void delete(Order order, List<ProductFX> list);
    public void create(Order order, List<Product> list);
}
