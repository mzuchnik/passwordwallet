package pl.mzuchnik.mypasswordwallet.entity;

import javax.persistence.*;

@Entity
@Table(name = "passwords")
public class Password {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "address")
    private String webAddress;

    @Column(name = "login")
    private String webLogin;

    @Column(name = "password")
    private String webPassword;

    @Column(name = "description")
    private String webDescription;



    public Password() {
    }

    public Password(String webAddress, String webLogin, String webPassword) {
        this.id = null;
        this.webAddress = webAddress;
        this.webLogin = webLogin;
        this.webPassword = webPassword;
    }

    public Password(String webAddress, String webLogin, String webPassword, String webDescription) {
        this.webAddress = webAddress;
        this.webLogin = webLogin;
        this.webPassword = webPassword;
        this.webDescription = webDescription;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getWebAddress() {
        return webAddress;
    }

    public void setWebAddress(String webAddress) {
        this.webAddress = webAddress;
    }

    public String getWebLogin() {
        return webLogin;
    }

    public void setWebLogin(String webLogin) {
        this.webLogin = webLogin;
    }

    public String getWebPassword() {
        return webPassword;
    }

    public void setWebPassword(String webPassword) {
        this.webPassword = webPassword;
    }

    public String getWebDescription() {
        return webDescription;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setWebDescription(String webDescription) {
        this.webDescription = webDescription;
    }

    @Override
    public String toString() {
        return "Password{" +
                "id=" + id +
                ", user=" + user +
                ", webAddress='" + webAddress + '\'' +
                ", webLogin='" + webLogin + '\'' +
                ", webPassword='" + webPassword + '\'' +
                ", webDescription='" + webDescription + '\'' +
                '}';
    }
}
