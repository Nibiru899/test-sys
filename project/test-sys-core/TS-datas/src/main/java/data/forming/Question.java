package data.forming;

import data.beans.Bean;
import javax.persistence.*;

import java.util.List;

@Entity
@Table(name = "question")
public class Question extends Bean {
    @Column(name = "text")
    private String text;

    @OneToMany(mappedBy = "question")
    @JoinColumn(name = "answers")
    private List<Answer> answers;

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
}
