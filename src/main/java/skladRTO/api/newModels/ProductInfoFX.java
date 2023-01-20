package skladRTO.api.newModels;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.util.Objects;

public class ProductInfoFX implements ModelFX{
    private SimpleIntegerProperty id;
    private SimpleStringProperty articul;
    private SimpleStringProperty date;
    private SimpleStringProperty description;

    public int getId() {
        return id.get();
    }

    public SimpleIntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getArticul() {
        return articul.get();
    }

    public SimpleStringProperty articulProperty() {
        return articul;
    }

    public void setArticul(String articul) {
        this.articul.set(articul);
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

    public String getDescription() {
        return description.get();
    }

    public SimpleStringProperty descriptionProperty() {
        return description;
    }

    public void setDescription(String description) {
        this.description.set(description);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductInfoFX that = (ProductInfoFX) o;
        return Objects.equals(id, that.id) && Objects.equals(articul, that.articul) && Objects.equals(date, that.date) && Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, articul, date, description);
    }

    @Override
    public String toString() {
        return "ProductInfoFX{" +
                "id=" + id +
                ", articul=" + articul +
                ", date=" + date +
                ", description=" + description +
                '}';
    }
}
