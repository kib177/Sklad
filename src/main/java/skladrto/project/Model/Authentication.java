package skladrto.project.Model;

public class Authentication {
    private int id;
    private String login;
    private String password;
    private StatusUser statusUser;
    private String email;

    public Authentication(int id, String login, String password, StatusUser statusUser, String email) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.statusUser = statusUser;
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

    public StatusUser getStatusUser() {
        return statusUser;
    }

    public void setStatusUser(StatusUser statusUser) {
        this.statusUser = statusUser;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
