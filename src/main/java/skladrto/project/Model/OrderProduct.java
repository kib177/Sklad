package skladrto.project.Model;

public class OrderProduct {
    private int id;
    private String nameProduct;
    private int amount;
    private OrderStatus orderStatus;
    private Order order;
    private ProductInfo productInfo;

    public OrderProduct(int id, String nameProduct, int amount, OrderStatus orderStatus, Order order, ProductInfo productInfo) {
        this.id = id;
        this.nameProduct = nameProduct;
        this.amount = amount;
        this.orderStatus = orderStatus;
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

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public ProductInfo getProductInfo() {
        return productInfo;
    }

    public void setProductInfo(ProductInfo productInfo) {
        this.productInfo = productInfo;
    }
}
