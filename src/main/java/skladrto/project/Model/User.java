package skladrto.project.Model;

public class User {
    private int id;
    private String ferstName;
    private String lastName;
    private Authentication authentication;

    public User(int id, String ferstName, String lastName, Authentication authentication) {
        this.id = id;
        this.ferstName = ferstName;
        this.lastName = lastName;
        this.authentication = authentication;
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

    public Authentication getAuthentication() {
        return authentication;
    }

    public void setAuthentication(Authentication authentication) {
        this.authentication = authentication;
    }
}
