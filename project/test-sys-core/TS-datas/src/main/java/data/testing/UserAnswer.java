package data.testing;

import data.beans.Bean;
import data.forming.Answer;
import data.forming.Question;
import jakarta.persistence.*;

@Entity
@Table(name = "useranswer")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "disc")
public class UserAnswer extends Bean {
    @OneToOne
    @JoinColumn(name = "question")
    private Question question;

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }
}
