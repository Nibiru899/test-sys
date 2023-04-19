package data.testing;

import data.beans.Bean;
import data.forming.Questions.Answer;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "userconnections")
@Getter @Setter
public class UserConnection extends Bean {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "answer1")
    private Answer answer1;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "answer2")
    private Answer answer2;
}
