package data.users;

import data.beans.Bean;
import data.forming.Themes.Competity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import java.util.List;

@Entity
@Table(name = "subject")
@Getter @Setter
public class Subject extends Bean {
    @Column(name = "name")
    private String name;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "competities_ids")
    private List<Competity> competities;

}
