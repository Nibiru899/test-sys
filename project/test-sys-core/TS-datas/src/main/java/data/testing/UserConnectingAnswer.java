package data.testing;

import data.forming.Answer;
import jakarta.persistence.*;

import java.util.List;

@Entity
@DiscriminatorValue("ca")
public class UserConnectingAnswer {
    @OneToMany
    @JoinColumn(name = "first")
    private List<Answer> first;
    @OneToMany
    @JoinColumn(name = "second")
    private List<Answer> second;

    public List<Answer> getFirst() {
        return first;
    }

    public void setFirst(List<Answer> first) {
        this.first = first;
    }

    public List<Answer> getSecond() {
        return second;
    }

    public void setSecond(List<Answer> second) {
        this.second = second;
    }
}
