package data.forming.Questions;

import data.beans.Bean;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "answer")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "disc")
@DiscriminatorValue(value = "simple")
@Getter @Setter
public class Answer extends Bean {
    public Answer(){
        correct = false;
    }

    @Column(name = "text")
    private String text;
    @Column(name = "correct")
    private Boolean correct;

}
