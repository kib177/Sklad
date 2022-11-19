package skladrto.project.Model;

public class Order {
    private int id;
    private String orderDescription;
    private User user;
    //потом заменить на Date
    private String orderDate;
    private int numberOrder;

    public Order(int id, String orderDescription, User user, String orderDate, int numberOrder) {
        this.id = id;
        this.orderDescription = orderDescription;
        this.user = user;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
