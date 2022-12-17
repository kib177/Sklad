package skladRTO.api.models;

public class Order {
    private int id;
    private String orderDescription;
    private int userId;

    private int machinesId;
    private String orderDate;
    private String numberOrder;

    public Order(int id, String orderDescription, String orderDate, String numberOrder) {
        this.id = id;
        this.orderDescription = orderDescription;
  //      this.userId = userId;
        this.orderDate = orderDate;
        this.numberOrder = numberOrder;
    }

    //добавил конструктор полный для пробы
    public Order(int id, String orderDescription, int userId, int machinesId, String orderDate, String numberOrder) {
        this.id = id;
        this.orderDescription = orderDescription;
        this.userId = userId;
        this.machinesId = machinesId;
        this.orderDate = orderDate;
        this.numberOrder = numberOrder;
    }

    public Order (String orderDescription, int userId, int machinesId, String orderDate, String numberOrder){
        this.orderDescription = orderDescription;
        this.userId = userId;
        this.machinesId = machinesId;
        this.orderDate = orderDate;
        this.numberOrder = numberOrder;
    }

    public int getMachinesId() {
        return machinesId;
    }

    public void setMachinesId(int machinesId) {
        this.machinesId = machinesId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOrderDescription() {
        return orderDescription;
    }

    public void setOrderDescription(String orderDescription) {
        this.orderDescription = orderDescription;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getNumberOrder() {
        return numberOrder;
    }

    public void setNumberOrder(String numberOrder) {
        this.numberOrder = numberOrder;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", orderDescription='" + orderDescription + '\'' +
                ", orderDate='" + orderDate + '\'' +
                ", numberOrder=" + numberOrder +
                '}';
    }
}
