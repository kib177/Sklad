/* такая же ситуация как с Tovar классом
 */
package skladRTO.api.FX.models;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.util.Objects;

public class UserFX {
    private SimpleIntegerProperty id;
    private SimpleStringProperty login;
    private SimpleStringProperty password;
//    private SimpleIntegerProperty status; нужно продумать обработку и добавить в бд потом реализовать тут
    private SimpleStringProperty firstName;
    private SimpleStringProperty secondName;
    private SimpleStringProperty status;
    private SimpleStringProperty email;

    public UserFX(int id, String login, String password, String firstName, String secondName, String status, String email) { // тоже смое читай в классе Tovar
        this.id = new SimpleIntegerProperty(id);
        this.login = new SimpleStringProperty(login);
        this.password = new SimpleStringProperty(password);
        this.status = new SimpleStringProperty(status);
        this.firstName = new SimpleStringProperty(firstName);
        this.secondName = new SimpleStringProperty(secondName);
        this.email = new SimpleStringProperty(email);
    }

    public String getStatus() {
        return status.get();
    }

    public SimpleStringProperty statusProperty() {
        return status;
    }

    public void setStatus(String status) {
        this.status.set(status);
    }

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

    public String getFirstName() {
        return firstName.get();
    }

    public SimpleStringProperty firstNameProperty() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName.set(firstName);
    }

    public String getSecondName() {
        return secondName.get();
    }

    public SimpleStringProperty secondNameProperty() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName.set(secondName);
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserFX userFX = (UserFX) o;
        return Objects.equals(id, userFX.id) && Objects.equals(login, userFX.login) && Objects.equals(password, userFX.password) && Objects.equals(firstName, userFX.firstName) && Objects.equals(secondName, userFX.secondName) && Objects.equals(status, userFX.status) && Objects.equals(email, userFX.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, login, password, firstName, secondName, status, email);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login=" + login +
                ", password=" + password +
                ", firstName=" + firstName +
                ", secondName=" + secondName +
                ", status=" + status +
                ", email=" + email +
                '}';
    }
}
