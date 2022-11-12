/* такая же ситуация как с Tovar классом
 */
package skladrto.project.Model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.util.Objects;

public class User {
    private SimpleIntegerProperty id;
    private SimpleStringProperty login;
    private SimpleStringProperty password;
//    private SimpleIntegerProperty status; нужно продумать обработку и добавить в бд потом реализовать тут
    private SimpleStringProperty firstName;
    private SimpleStringProperty secondName;
    private SimpleStringProperty status;
    private SimpleStringProperty email;
    private SimpleStringProperty department;

    public User(int id, String login, String password, String firstName, String secondName,String status, String email, String department) { // тоже смое читай в классе Tovar
        this.id = new SimpleIntegerProperty(id);
        this.login = new SimpleStringProperty(login);
        this.password = new SimpleStringProperty(password);
        this.status = new SimpleStringProperty(status);
        this.firstName = new SimpleStringProperty(firstName);
        this.secondName = new SimpleStringProperty(secondName);
        this.email = new SimpleStringProperty(email);
        this.department = new SimpleStringProperty(department);
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

    public String getDepartment() {
        return department.get();
    }

    public SimpleStringProperty departmentProperty() {
        return department;
    }

    public void setDepartment(String department) {
        this.department.set(department);
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
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(login, user.login) && Objects.equals(password, user.password) && Objects.equals(firstName, user.firstName) && Objects.equals(secondName, user.secondName) && Objects.equals(status, user.status) && Objects.equals(email, user.email) && Objects.equals(department, user.department);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, login, password, firstName, secondName, status, email, department);
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
                ", department=" + department +
                '}';
    }
}
