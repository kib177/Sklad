package skladRTO.api.newModels;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.util.Objects;

public class UserFX implements ModelFX {
    private SimpleIntegerProperty id;
    private SimpleStringProperty first_name;
    private SimpleStringProperty second_name;
    private AuthenticationFX authentication;




    public int getId() {
        return id.get();
    }

    public SimpleIntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getFirst_name() {
        return first_name.get();
    }

    public SimpleStringProperty first_nameProperty() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name.set(first_name);
    }

    public String getSecond_name() {
        return second_name.get();
    }

    public SimpleStringProperty second_nameProperty() {
        return second_name;
    }

    public void setSecond_name(String second_name) {
        this.second_name.set(second_name);
    }

    public AuthenticationFX getAuthentication() {
        return authentication;
    }

    public void setAuthentication(AuthenticationFX authentication) {
        this.authentication = authentication;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserFX userFX = (UserFX) o;
        return Objects.equals(id, userFX.id) && Objects.equals(first_name, userFX.first_name) && Objects.equals(second_name, userFX.second_name) && Objects.equals(authentication, userFX.authentication);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, first_name, second_name, authentication);
    }

    @Override
    public String toString() {
        return "UserFX{" +
                "id=" + id +
                ", first_name=" + first_name +
                ", second_name=" + second_name +
                ", authentication=" + authentication +
                '}';
    }
}
