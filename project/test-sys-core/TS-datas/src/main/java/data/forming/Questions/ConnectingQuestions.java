package data.forming.Questions;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@DiscriminatorValue("connections")
@Getter @Setter
public class ConnectingQuestions extends Question{
    @OneToMany(fetch = FetchType.LAZY,cascade= CascadeType.ALL)
    @JoinColumn(name = "connections")
    private List<Connection> answers;
}
