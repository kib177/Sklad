package skladRTO.api.models;

public class Product {
    private int id;
    private String name;
    private int machine;
    private int units;
    private int amount;
    private int Status;
    private int order;
    private int productInfo;

    public Product(String nameProduct, String amount, int machine, int units){
        this.name = nameProduct;
        this.amount = Integer.parseInt(amount);
        this.Status = 1;
        this.machine = machine;
        this.units = units;
    }

    public Product(int id, String nameProduct, int amount, int orderStatus, int order, int productInfo) {
        this.id = id;
        this.name = nameProduct;
        this.amount = amount;
        this.Status = orderStatus;
        this.order = order;
        this.productInfo = productInfo;
    }

    public int getUnits() {
        return units;
    }

    public void setUnits(int units) {
        this.units = units;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMachine() {
        return machine;
    }

    public void setMachine(int machine) {
        this.machine = machine;
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
                ", name='" + name + '\'' +
                ", machine=" + machine +
                ", units=" + units +
                ", amount=" + amount +
                ", Status=" + Status +
                ", order=" + order +
                ", productInfo=" + productInfo +
                '}';
    }
}
