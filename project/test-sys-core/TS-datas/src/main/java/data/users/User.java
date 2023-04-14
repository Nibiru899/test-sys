package data.users;

import data.beans.Bean;
import javax.persistence.*;

@Entity
@Table(name = "users")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "disc")
public class User extends Bean {
    @Column(name = "name")
    private String name;
    @Column(name = "surname")
    private String surName;
    @Column(name = "fathername")
    private String fatherName;
    @Column(name = "login")
    private String login;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "password")
    private Password password;

    public String getName() {
        return name;
    }
    public String getFatherName() {
        return fatherName;
    }
    public String getLogin() {
        return login;
    }
    public Password getPassword() {
        return password;
    }
    public String getSurName() {
        return surName;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setSurName(String surName) {
        this.surName = surName;
    }
    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }
    public void setLogin(String login) {
        this.login = login;
    }
    public void setPassword(Password password) {
        this.password = password;
    }
}
