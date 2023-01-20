package skladRTO.api.newModels;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.util.Objects;

public class AuthenticationFX implements ModelFX{
    private SimpleIntegerProperty id;
    private SimpleStringProperty login;
    private SimpleStringProperty password;
    private SimpleStringProperty email;
    private StatusUserFX status_user;

    public int getId() {
        return id.get();
    }

    public SimpleIntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getLogin() {
        return login.get();
    }

    public SimpleStringProperty loginProperty() {
        return login;
    }

    public void setLogin(String login) {
        this.login.set(login);
    }

    public String getPassword() {
        return password.get();
    }

    public SimpleStringProperty passwordProperty() {
        return password;
    }

    public void setPassword(String password) {
        this.password.set(password);
    }

    public String getEmail() {
        return email.get();
    }

    public SimpleStringProperty emailProperty() {
        return email;
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    public StatusUserFX getStatus_user() {
        return status_user;
    }

    public void setStatus_user(StatusUserFX status_user) {
        this.status_user = status_user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AuthenticationFX that = (AuthenticationFX) o;
        return Objects.equals(id, that.id) && Objects.equals(login, that.login) && Objects.equals(password, that.password) && Objects.equals(email, that.email) && Objects.equals(status_user, that.status_user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, login, password, email, status_user);
    }

    @Override
    public String toString() {
        return "AuthenticationFX{" +
                "id=" + id +
                ", login=" + login +
                ", password=" + password +
                ", email=" + email +
                ", status_user=" + status_user +
                '}';
    }
}
