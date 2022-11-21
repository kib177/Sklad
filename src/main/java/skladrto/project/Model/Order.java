package skladrto.project.Model;

public class Order {
    private int id;
    private String orderDescription;
    private int userId;
    //потом заменить на Date
    private String orderDate;
    private int numberOrder;

    public Order(int id, String orderDescription, int userId, String orderDate, int numberOrder) {
        this.id = id;
        this.orderDescription = orderDescription;
        this.userId = userId;
        this.orderDate = orderDate;
        this.numberOrder = numberOrder;
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

    public int getNumberOrder() {
        return numberOrder;
    }

    public void setNumberOrder(int numberOrder) {
        this.numberOrder = numberOrder;
    }
}
