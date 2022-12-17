package skladRTO.api.models;

public class User {
    private int id;
    private String ferstName;
    private String lastName;
    private int authenticationId;
    private String FullName;

    public User(int id, String ferstName, String lastName, int authenticationId) {
        this.id = id;
        this.ferstName = ferstName;
        this.lastName = lastName;
        this.authenticationId = authenticationId;
        this.FullName = ferstName+" "+lastName;
    }

    public User(int id, String ferstName, String lastName) {
        this.FullName = ferstName +" "+ lastName;
        this.id = id;
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

    public String getFullName() {
        return FullName;
    }

    public void setFullName(String fullName) {
        FullName = fullName;
    }

    @Override
    public String toString() {
        return  FullName;
    }
}
