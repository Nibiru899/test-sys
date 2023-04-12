package data.forming;

import data.beans.Bean;
import data.users.Group;
import data.users.User;


import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "plan")
public class Plan extends Bean {
    @Column(name = "name")
    private String name;
    @Column(name = "starting")
    private LocalDate starting;
    @Column(name = "ending")
    private LocalDate ending;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author")
    private User author;
    @ManyToMany(fetch = FetchType.LAZY)
    private List<User> privateAccess;
    @ManyToMany(fetch = FetchType.LAZY)
    private List<Theme> themes;
    @ManyToMany(fetch = FetchType.LAZY)
    private List<Group> takePart;
    @ElementCollection
    private List<Integer> counts;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getStarting() {
        return starting;
    }

    public void setStarting(LocalDate starting) {
        this.starting = starting;
    }

    public LocalDate getEnding() {
        return ending;
    }

    public void setEnding(LocalDate ending) {
        this.ending = ending;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public List<Theme> getThemes() {
        return themes;
    }

    public void setThemes(List<Theme> themes) {
        this.themes = themes;
    }

    public List<Group> getTakePart() {
        return takePart;
    }

    public void setTakePart(List<Group> takePart) {
        this.takePart = takePart;
    }

    public List<Integer> getCounts() {
        return counts;
    }

    public void setCounts(List<Integer> counts) {
        this.counts = counts;
    }

    public List<User> getPrivateAccess() {
        return privateAccess;
    }

    public void setPrivateAccess(List<User> privateAccess) {
        this.privateAccess = privateAccess;
    }
}
