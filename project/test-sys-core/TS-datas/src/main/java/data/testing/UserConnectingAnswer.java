package data.testing;

import data.beans.Bean;
import data.forming.Answer;
import javax.persistence.*;

import java.util.List;

@Entity
@DiscriminatorValue("ca")
public class UserConnectingAnswer extends UserAnswer{
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "first")
    private List<Answer> first;
    @OneToOne(fetch = FetchType.LAZY)
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
