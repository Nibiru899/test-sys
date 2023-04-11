package data.testing;

import data.forming.Plan;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "result")
public class Result {
    @Column(name = "date")
    private LocalDate date;
    @Column(name = "max")
    private Integer max;
    @Column(name = "current")
    private Integer current;
    @Column(name = "result")
    private Integer result;
    @OneToOne
    private Plan plan;
    @OneToMany
    @JoinColumn(name = "answers")
    private List<UserAnswer> userAnswers;

    public Integer getCurrent() {
        return current;
    }

    public Integer getMax() {
        return max;
    }

    public Integer getResult() {
        return result;
    }

    public List<UserAnswer> getUserAnswers() {
        return userAnswers;
    }

    public LocalDate getDate() {
        return date;
    }

    public Plan getPlan() {
        return plan;
    }

    public void setCurrent(Integer current) {
        this.current = current;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setMax(Integer max) {
        this.max = max;
    }

    public void setPlan(Plan plan) {
        this.plan = plan;
    }

    public void setResult(Integer result) {
        this.result = result;
    }

    public void setUserAnswers(List<UserAnswer> userAnswers) {
        this.userAnswers = userAnswers;
    }
}
