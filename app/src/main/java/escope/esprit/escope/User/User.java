package escope.esprit.escope.User;

public class User {

    private int id;
    private String firstname;
    private String lastname;
    private String email;
    private String image;
    private String role;
    private String birthdate;
    private String tokens;
    private String password;

    public User(String firstname, String lastname, String email,String role, String birthdate, String image) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.image = image;
        this.role = role;
        this.birthdate = birthdate;
    }
    public User(String firstname, String lastname, String email,String role, String birthdate) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.role = role;
        this.birthdate = birthdate;
    }

    public User() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getRole() {
        return role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public String getTokens() {
        return tokens;
    }

    public void setTokens(String tokens) {
        this.tokens = tokens;
    }
}
