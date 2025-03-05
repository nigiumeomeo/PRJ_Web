package model;

public class User {

    private int id;
    private String firstName;
    private String lastName;
    private String address;
    private String role;
    private String avatarURL;
    private String email;
    private String password;

    public User() {
    }

    public User(int id, String firstName, String lastName, String address, String avatarURL) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.avatarURL = avatarURL;
    }

    public User(int id, String firstName, String lastName, String address, String role, String avatarURL) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.role = role;
        this.avatarURL = avatarURL;
    }

    public User(int id, String firstName, String lastName, String address, String role, String avatarURL, String password) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.role = role;
        this.avatarURL = avatarURL;
        this.password = password;
    }

    public User(int id, String firstName, String lastName, String address, String role, String avatarURL, String email, String password) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.role = role;
        this.avatarURL = avatarURL;
        this.email = email;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getAvatarURL() {
        return avatarURL;
    }

    public void setAvatarURL(String avatarURL) {
        this.avatarURL = avatarURL;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String userName) {
        this.email = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", address=" + address + ", role=" + role + ", avatarURL=" + avatarURL + ", email=" + email + ", password=" + password + '}';
    }

}
