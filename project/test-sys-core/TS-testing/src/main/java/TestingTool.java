import data.forming.Answer;
import data.forming.Plan;
import data.testing.Result;
import data.testing.UserAnswer;
import data.testing.UserConnectingAnswer;
import data.testing.UserSimplyAnswer;

import java.time.LocalDate;

public class TestingTool {
    public static Plan getPlan(Long id) {
        return new BDWorker().find(Plan.class,id);
    }
    public static Result createResult(Long planId,String answersJson){
        Result res = new Result();
        res.setDate(LocalDate.now());
        res.setPlan(getPlan(planId));
        //todo хуйня ебаная как парсить жсон сука
        //res.setUserAnswers(Jsoner.fromString();
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
//            if (answer instanceof UserConnectingAnswer) {
//                UserConnectingAnswer uca = (UserConnectingAnswer) answer;
//                //todo хуйня ебаная
//            }
        }
        new BDWorker().updateOrAdd(res);
        return res;
    }
}
