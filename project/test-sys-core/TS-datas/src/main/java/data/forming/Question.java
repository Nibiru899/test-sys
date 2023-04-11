package data.forming;

import data.beans.Bean;
import data.service.Picture;

import javax.persistence.*;

import java.util.List;

@Entity
@Table(name = "question")
public class Question extends Bean {
    @Column(name = "text")
    private String text;

    @OneToMany
    @JoinColumn(name = "answers")
    private List<Answer> answers;

    @ManyToOne
    @JoinColumn(name = "picture")
    private Picture picture;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }

    public Picture getPicture() {
        return picture;
    }

    public void setPicture(Picture picture) {
        this.picture = picture;
    }
}
