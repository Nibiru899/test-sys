package data.users;

import data.beans.Bean;
import data.forming.Competity;
import javax.persistence.*;

import java.util.List;

@Entity
@Table(name = "subject")
public class Subject extends Bean {
    @Column(name = "name")
    private String name;

    @OneToMany(fetch = FetchType.LAZY,cascade=CascadeType.ALL)
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
