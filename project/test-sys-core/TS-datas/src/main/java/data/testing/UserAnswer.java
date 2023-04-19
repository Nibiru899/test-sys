package data.testing;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import data.beans.Bean;
import data.forming.Questions.ConnectingQuestions;
import data.forming.Questions.Question;
import data.forming.Questions.SimpleQuestion;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "useranswer")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "disc")
@Getter @Setter
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY)
@JsonSubTypes({
        @JsonSubTypes.Type(value = UserConnectingAnswer.class, name = "Connecting"),

        @JsonSubTypes.Type(value = UserSimplyAnswer.class, name = "Simple") }
)
public class UserAnswer extends Bean {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question")
    private Question question;
}
