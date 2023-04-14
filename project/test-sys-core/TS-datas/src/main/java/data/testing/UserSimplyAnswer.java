package data.testing;

import data.forming.Answer;
import javax.persistence.*;

@Entity
@DiscriminatorValue("sa")
public class UserSimplyAnswer extends UserAnswer {
    @OneToOne(fetch = FetchType.LAZY,cascade=CascadeType.ALL)
    @JoinColumn(name = "answer")
    private Answer answer;

    public Answer getAnswer() {
        return answer;
    }

    public void setAnswer(Answer answer) {
        this.answer = answer;
    }
}
