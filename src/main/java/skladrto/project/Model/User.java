package skladrto.project.Model;

public class User {
    private int id;
    private String ferstName;
    private String lastName;
    private int authenticationId;

    public User(int id, String ferstName, String lastName, int authenticationId) {
        this.id = id;
        this.ferstName = ferstName;
        this.lastName = lastName;
        this.authenticationId = authenticationId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFerstName() {
        return ferstName;
    }

    public void setFerstName(String ferstName) {
        this.ferstName = ferstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAuthenticationId() {
        return authenticationId;
    }

    public void setAuthenticationId(int authenticationId) {
        this.authenticationId = authenticationId;
    }
}
