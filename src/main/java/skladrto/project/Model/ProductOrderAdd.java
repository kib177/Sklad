package skladrto.project.Model;

public class ProductOrderAdd {
    private String nameProduct;
    private int amount;
    private int orderStatusId;
    private int productInfoId;

    public ProductOrderAdd(String nameProduct, int amount, int orderStatusId, int productInfoId) {
        this.nameProduct = nameProduct;
        this.amount = amount;
        this.orderStatusId = orderStatusId;
        this.productInfoId = productInfoId;
    }

    public String getNameProduct() {
        return nameProduct;
    }

    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getOrderStatusId() {
        return orderStatusId;
    }

    public void setOrderStatusId(int orderStatusId) {
        this.orderStatusId = orderStatusId;
    }

    public int getProductInfoId() {
        return productInfoId;
    }

    public void setProductInfoId(int productInfoId) {
        this.productInfoId = productInfoId;
    }
}
