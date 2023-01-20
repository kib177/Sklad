package skladRTO.api.newModels;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import skladRTO.api.FX.models.UserFX;

import java.util.Objects;

public class OrderFX implements ModelFX{
    private SimpleIntegerProperty id;
    private SimpleStringProperty description;
    private UserFX user;
    private SimpleStringProperty date;
    private SimpleIntegerProperty number;

    public int getId() {
        return id.get();
    }

    public SimpleIntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getDescription() {
        return description.get();
    }

    public SimpleStringProperty descriptionProperty() {
        return description;
    }

    public void setDescription(String description) {
        this.description.set(description);
    }

    public UserFX getUser() {
        return user;
    }

    public void setUser(UserFX user) {
        this.user = user;
    }

    public String getDate() {
        return date.get();
    }

    public SimpleStringProperty dateProperty() {
        return date;
    }

    public void setDate(String date) {
        this.date.set(date);
    }

    public int getNumber() {
        return number.get();
    }

    public SimpleIntegerProperty numberProperty() {
        return number;
    }

    public void setNumber(int number) {
        this.number.set(number);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderFX orderFX = (OrderFX) o;
        return Objects.equals(id, orderFX.id) && Objects.equals(description, orderFX.description) && Objects.equals(user, orderFX.user) && Objects.equals(date, orderFX.date) && Objects.equals(number, orderFX.number);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description, user, date, number);
    }

    @Override
    public String toString() {
        return "OrderFX{" +
                "id=" + id +
                ", description=" + description +
                ", user=" + user +
                ", date=" + date +
                ", number=" + number +
                '}';
    }
}
