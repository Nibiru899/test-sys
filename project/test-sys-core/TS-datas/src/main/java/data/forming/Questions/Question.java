package data.forming.Questions;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import data.beans.Bean;
import data.service.Picture;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "question")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "disc")
@Getter @Setter
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY)
@JsonSubTypes({
        @JsonSubTypes.Type(value = ConnectingQuestions.class, name = "Connecting"),

        @JsonSubTypes.Type(value = SimpleQuestion.class, name = "Simple") }
)
public class Question extends Bean {
    @Column(name = "text")
    private String text;

    @ManyToOne(fetch = FetchType.LAZY,cascade=CascadeType.ALL)
    @JoinColumn(name = "picture")
    private Picture picture;

}
