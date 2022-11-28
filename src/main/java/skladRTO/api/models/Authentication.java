package skladRTO.api.models;

public class Authentication {
    private int id;
    private String login;
    private String password;
    private int statusUserId;
    private String email;

    public Authentication(int id, String login, String password, int statusUserId, String email) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.statusUserId = statusUserId;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getStatusUserId() {
        return statusUserId;
    }

    public void setStatusUserId(int statusUserId) {
        this.statusUserId = statusUserId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
