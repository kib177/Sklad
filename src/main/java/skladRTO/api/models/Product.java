package skladRTO.api.models;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Product {
    private int id;
    private String nameProduct;
    private int amount;
    private int Status;
    private int order;
    private int productInfo;

    public Product(SimpleIntegerProperty id, SimpleStringProperty nameProduct, SimpleIntegerProperty amount, SimpleIntegerProperty orderStatus, SimpleIntegerProperty order, SimpleIntegerProperty productInfo) {
        this.id = id.getValue();
        this.nameProduct = nameProduct.getValue();
        this.amount = amount.getValue();
        this.Status = orderStatus.getValue();
        this.order = order.getValue();
        this.productInfo = productInfo.getValue();
    }

    public Product(String nameProduct, String amount){
        this.nameProduct = nameProduct;
        this.amount = Integer.parseInt(amount);
        this.Status = 1;
    }

    public Product(int id, String nameProduct, int amount, int orderStatus, int order, int productInfo) {
        this.id = id;
        this.nameProduct = nameProduct;
        this.amount = amount;
        this.Status = orderStatus;
        this.order = order;
        this.productInfo = productInfo;
    }

    //    public Product(SimpleIntegerProperty id, SimpleStringProperty nameProduct, SimpleIntegerProperty amount,
//                   SimpleIntegerProperty orderStatus, SimpleIntegerProperty order, SimpleIntegerProperty productInfo) {
//        this.id = id.getValue();
//        this.nameProduct = nameProduct.getValue();
//        this.amount = amount.getValue();
//        this.orderStatus = orderStatus.getValue();
//        this.order = order.getValue();
//        this.productInfo = productInfo.getValue();
//    }

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
                ", nameProduct='" + nameProduct + '\'' +
                ", amount=" + amount +
                ", orderStatus=" + Status +
                ", order=" + order +
                ", productInfo=" + productInfo +
                '}';
    }
}
