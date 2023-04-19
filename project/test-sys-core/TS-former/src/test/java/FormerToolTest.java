import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import data.forming.Questions.Answer;
import data.forming.Questions.Question;
import data.forming.Questions.SimpleQuestion;
import data.forming.Themes.Theme;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class FormerToolTest {
    @Test
    public void testing() throws JsonProcessingException {
        SimpleQuestion q = new SimpleQuestion();
        q.setText("asdasas");

        Answer ans = new Answer();
        ans.setText("1");

        Answer ans2 = new Answer();
        ans2.setText("2");
        ans2.setCorrect(true);

        q.setAnswers(Arrays.asList(ans,ans2));

        Theme theme = new Theme();
        theme.setName("testovaya");
        theme.setQuestions(Collections.singletonList(q));

        String json = Jsoner.toJson(theme);
        FormerTool.update("Theme",json);

        List l  = FormerTool.getAll("Theme","");
        Theme theme2 = Jsoner.fromString(Theme.class, (String) l.get(0));
        SimpleQuestion q2 = (SimpleQuestion) theme2.getQuestions().get(0);
        Assertions.assertEquals(q.getText(),q2.getText());

        Answer ans3 = q2.getAnswers().get(0);
        Assertions.assertEquals(ans.getText(),ans3.getText());

        Answer ans4 = q2.getAnswers().get(1);
        Assertions.assertEquals(ans4.getText(),ans2.getText());

    }
}
