package testing;

import bdWorkers.BDWorker;
import bdWorkers.Jsoner;
import data.forming.Plans.Plan;
import data.forming.Questions.Answer;
import data.forming.Questions.ConnectingQuestions;
import data.forming.Questions.Connection;
import data.testing.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TestingTool {
    public static Plan getPlan(Long id) {
        return new BDWorker().find(Plan.class,id);
    }
    public static Result createResult(Long planId,String answersJson){
        Result res = new Result();
        res.setDate(LocalDate.now());
        res.setPlan(getPlan(planId));

        List<UserAnswer> userAnswers = (List<UserAnswer>) Jsoner.fromString(UserAnswer.class,answersJson);
        res.setMax(res.getUserAnswers().size());
        int current = 0;
        for (UserAnswer answer : res.getUserAnswers()) {
            boolean flag = true;
            if (answer instanceof UserSimplyAnswer) {
                UserSimplyAnswer sa = (UserSimplyAnswer) answer;
                for (Answer ans : sa.getAnswers()) {
                    if (!ans.getCorrect()) {
                        flag = false;
                    }
                }
            }
            if (answer instanceof UserConnectingAnswer) {
                UserConnectingAnswer uca = (UserConnectingAnswer) answer;
                List<Connection> connections = ((ConnectingQuestions)uca.getQuestion()).getAnswers();
                Map<Answer,Answer> map = connections.stream().collect(Collectors.toMap(
                        e->e.getAnswer1(),
                        e->e.getAnswer2(),
                        (e1,e2)->e1
                ));
                for (UserConnection uc : uca.getConnections()){
                    if (!map.get(uc.getAnswer1()).getId().equals(uc.getAnswer2())){
                        flag = false;
                        break;
                    }
                }
            }
            if (flag){
                current++;
            }
        }
        res.setCurrent(current);
        res.setResult(0);//todo шкала
        new BDWorker().updateOrAdd(res);
        return res;
    }
}
