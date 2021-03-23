package by.bsuir.dto;

import java.io.Serializable;

public class UserDTO implements Serializable {
    private String login;
    private String role;
    private String password;

    public UserDTO() {
    }

    public UserDTO(String login, String role, String password) {
        this.login = login;
        this.role = role;
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public String getRole() {
        return role;
    }

    public String getPassword() {
        return password;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
