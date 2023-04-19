package data.forming.Themes;

import data.beans.Bean;
import data.forming.Questions.Question;
import data.forming.Themes.Competity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import java.util.List;

@Entity
@Table(name = "theme")
@Getter @Setter
public class Theme extends Bean {
    @Column(name = "name")
    private String name;

    @OneToMany(fetch = FetchType.LAZY,cascade=CascadeType.ALL)
    @JoinColumn(name = "competity")
    private List<Competity> competity;

    @OneToMany(fetch = FetchType.LAZY,cascade=CascadeType.ALL)
    @JoinColumn(name = "questions")
    private List<Question> questions;

}
