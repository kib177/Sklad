package skladRTO.api.models.lists;

import skladRTO.api.models.ProductAdd;

import java.util.ArrayList;
import java.util.List;

public class ProductAddList {
    private List<ProductAdd> productAdds = new ArrayList<>();

    public void createProductList(String nameProduct, int amount, int orderStatusId, int productInfoId) {
        productAdds.add(new ProductAdd(nameProduct, amount, orderStatusId, productInfoId));
    }

    public List<ProductAdd> getProductOrderAdds() {
        return productAdds;
    }

    public void setProductOrderAdds(List<ProductAdd> productAdds) {
        this.productAdds = productAdds;
    }
}
