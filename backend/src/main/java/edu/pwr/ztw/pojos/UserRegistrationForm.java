package edu.pwr.ztw.pojos;

import java.util.Date;

public class UserRegistrationForm {
    private String login;
    private String password;
    private String confirmPassword;
    private String email;
    private String firstname;
    private String lastname;
    private Date dateOfBirth;

    public UserRegistrationForm() {
    }

    public UserRegistrationForm(String login, String password, String confirmPassword, String email, String firstname, String lastname, Date dateOfBirth) {
        this.login = login;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.email = email;
        this.firstname = firstname;
        this.lastname = lastname;
        this.dateOfBirth = dateOfBirth;
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

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
}
