package data.testing;

import data.forming.Questions.Answer;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import java.util.List;

@Entity
@DiscriminatorValue("ca")
@Getter @Setter
public class UserConnectingAnswer extends UserAnswer{
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "first")
    private List<UserConnection> connections;

}
