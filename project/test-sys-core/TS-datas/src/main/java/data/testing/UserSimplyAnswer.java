package data.testing;

import data.forming.Answer;
import javax.persistence.*;
import java.util.List;

@Entity
@DiscriminatorValue("sa")
public class UserSimplyAnswer extends UserAnswer {
    @OneToMany(fetch = FetchType.LAZY,cascade=CascadeType.ALL)
    @JoinColumn(name = "answers")
    private List<Answer> answers;


    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }
}
