package pl.mzuchnik.mypasswordwallet.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @OneToMany(cascade = CascadeType.ALL, fetch=FetchType.EAGER, mappedBy = "user")
    private List<Password> userPasswords;

    private String login;
    private String password;


    public User() {
    }

    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public User(String login, String password, List<Password> userPasswords) {
        this.login = login;
        this.password = password;
        this.userPasswords = userPasswords;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public List<Password> getUserPasswords() {
        return userPasswords;
    }

    public void setUserPasswords(List<Password> userPasswords) {
        this.userPasswords = userPasswords;
    }
}
