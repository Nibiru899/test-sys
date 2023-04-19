package data.forming.Plans;

import data.beans.Bean;
import data.users.Group;
import data.users.User;
import lombok.Getter;
import lombok.Setter;


import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "plan")
@Getter @Setter
public class Plan extends Bean {
    @Column(name = "name")
    private String name;
    @Column(name = "starting")
    private LocalDate starting;
    @Column(name = "ending")
    private LocalDate ending;
    @ManyToOne(fetch = FetchType.LAZY,cascade=CascadeType.ALL)
    @JoinColumn(name = "author")
    private User author;
    @ManyToMany(fetch = FetchType.LAZY,cascade=CascadeType.ALL)
    private List<User> privateAccess;
    @ManyToMany(fetch = FetchType.LAZY,cascade=CascadeType.ALL)
    private List<Group> takePart;


    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "themePersist")
    private List<ThemePersist> themePersists;

    @ElementCollection
    private List<Integer> counts;

}
