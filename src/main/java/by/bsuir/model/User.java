package by.bsuir.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "user")
public class User implements Serializable {
    private static final long serialVersionUID = 5L;
    @Id
    private String login;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idWorker")
    private Worker worker;

    @Column
    private String role;

    @Column
    private String password;

    public User() {
    }

    public User(String login, String role, String password) {
        this.login = login;
        this.role = role;
        this.password = password;
    }

    public User(String login, Worker worker, String role, String password) {
        this.login = login;
        this.worker = worker;
        this.role = role;
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public Worker getWorker() {
        return worker;
    }

    public void setWorker(Worker worker) {
        this.worker = worker;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
