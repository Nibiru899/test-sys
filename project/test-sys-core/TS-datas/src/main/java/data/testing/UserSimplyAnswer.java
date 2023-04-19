package data.testing;

import data.forming.Questions.Answer;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@DiscriminatorValue("sa")
@Getter @Setter
public class UserSimplyAnswer extends UserAnswer {
    @ManyToMany
    @JoinColumn(name = "ans")
    private List<Answer> answers;
}
