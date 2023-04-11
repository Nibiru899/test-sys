package data.forming;

import jakarta.persistence.*;

@Entity
@Table(name = "answer")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "disc")
@DiscriminatorValue(value = "simple")
public class Answer {
    @Column(name = "text")
    private String text;
    @Column(name = "correct")
    private Boolean correct;

    @OneToMany(mappedBy = "answers")
    private Question question;

    public Boolean getCorrect() {
        return correct;
    }

    public String getText() {
        return text;
    }

    public void setCorrect(Boolean correct) {
        this.correct = correct;
    }

    public void setText(String text) {
        this.text = text;
    }
}
