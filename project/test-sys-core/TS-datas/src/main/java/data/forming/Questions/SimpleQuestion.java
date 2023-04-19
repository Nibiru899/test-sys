package data.forming.Questions;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@DiscriminatorValue("simple")
@Getter @Setter
public class SimpleQuestion extends Question{
    @OneToMany(fetch = FetchType.LAZY,cascade= CascadeType.ALL)
    @JoinColumn(name = "answers")
    private List<Answer> answers;
}
