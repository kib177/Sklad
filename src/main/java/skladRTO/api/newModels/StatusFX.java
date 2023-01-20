package skladRTO.api.newModels;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.util.Objects;

public class StatusFX implements ModelFX{
    private SimpleIntegerProperty id;
    private SimpleStringProperty name;

    public int getId() {
        return id.get();
    }

    public SimpleIntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getName() {
        return name.get();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StatusFX statusFX = (StatusFX) o;
        return Objects.equals(id, statusFX.id) && Objects.equals(name, statusFX.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "StatusFX{" +
                "id=" + id +
                ", name=" + name +
                '}';
    }
}
