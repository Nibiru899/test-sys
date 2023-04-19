package data.forming.Questions;

import data.beans.Bean;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "connection")
@Getter @Setter
public class Connection extends Bean {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "answer1")
    private Answer answer1;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "answer2")
    private Answer answer2;
}
