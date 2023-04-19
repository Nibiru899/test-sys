package data.testing;

import data.beans.Bean;
import data.forming.Plans.Plan;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "result")
@Getter @Setter
public class Result extends Bean {
    @Column(name = "date")
    private LocalDate date;
    @Column(name = "max")
    private Integer max;
    @Column(name = "current")
    private Integer current;
    @Column(name = "result")
    private Integer result;
    @ManyToOne(fetch = FetchType.LAZY,cascade=CascadeType.ALL)
    private Plan plan;
    @OneToMany(fetch = FetchType.LAZY,cascade=CascadeType.ALL)
    @JoinColumn(name = "answers")
    private List<UserAnswer> userAnswers;
}
