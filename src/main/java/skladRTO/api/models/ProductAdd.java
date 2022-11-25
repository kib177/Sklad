package skladRTO.api.models;

public class ProductAdd {
    private String nameProduct;
    private int amount;
    private int orderStatusId;
    private int productInfoId;

    public ProductAdd(String nameProduct, int amount, int orderStatusId, int productInfoId) {
        this.nameProduct = nameProduct;
        this.amount = amount;
        this.orderStatusId = orderStatusId;
        this.productInfoId = productInfoId;
    }

    public ProductAdd(String nameProduct, String amount){
        this.nameProduct = nameProduct;
        this.amount = Integer.parseInt(amount);
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

    @Override
    public String toString() {
        return "ProductOrderAdd{" +
                "nameProduct='" + nameProduct + '\'' +
                ", amount=" + amount +
                ", orderStatusId=" + orderStatusId +
                ", productInfoId=" + productInfoId +
                '}';
    }
}
