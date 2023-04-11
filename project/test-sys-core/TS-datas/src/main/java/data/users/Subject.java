package data.users;

import data.forming.Competity;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "subject")
public class Subject {
    @Column(name = "name")
    private String name;

    @OneToMany
    @JoinColumn(name = "competities_ids")
    private List<Competity> competities;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Competity> getCompetities() {
        return competities;
    }

    public void setCompetities(List<Competity> competities) {
        this.competities = competities;
    }
}
