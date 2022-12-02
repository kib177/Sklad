package skladRTO.api.models;

public class Product {
    private int id;
    private String name;
    private int amount;
    private int Status;
    private int order;
    private int productInfo;

    public Product(String nameProduct, String amount){
        this.name = nameProduct;
        this.amount = Integer.parseInt(amount);
        this.Status = 1;
    }

    public Product(int id, String nameProduct, int amount, int orderStatus, int order, int productInfo) {
        this.id = id;
        this.name = nameProduct;
        this.amount = amount;
        this.Status = orderStatus;
        this.order = order;
        this.productInfo = productInfo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameProduct() {
        return name;
    }

    public void setNameProduct(String nameProduct) {
        this.name = nameProduct;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getStatus() {
        return Status;
    }

    public void setStatus(int orderStatus) {
        this.Status = orderStatus;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public int getProductInfo() {
        return productInfo;
    }

    public void setProductInfo(int productInfo) {
        this.productInfo = productInfo;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", nameProduct='" + name +
                ", amount=" + amount +
                ", orderStatus=" + Status +
                ", order=" + order +
                ", productInfo=" + productInfo +
                '}';
    }
}
