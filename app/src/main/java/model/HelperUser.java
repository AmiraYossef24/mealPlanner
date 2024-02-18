package model;

public class HelperUser {

    private String username;
    private  String email;
    private Long phone;
    private  String pass;

    public HelperUser() {
    }

    public HelperUser(String username,  String email, Long phone, String pass) {
        this.username = username;
        this.email = email;
        this.phone = phone;
        this.pass = pass;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getPhone() {
        return phone;
    }

    public void setPhone(Long phone) {
        this.phone = phone;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}
