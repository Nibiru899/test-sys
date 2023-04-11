package data.forming;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "theme")
public class Theme {
    @Column(name = "name")
    private String name;

    @OneToMany
    @JoinColumn(name = "competity")
    private List<Competity> competity;

    @OneToMany
    @JoinColumn(name = "questions")
    private List<Question> questions;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Competity> getCompetity() {
        return competity;
    }

    public void setCompetity(List<Competity>     competity) {
        this.competity = competity;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }
}
