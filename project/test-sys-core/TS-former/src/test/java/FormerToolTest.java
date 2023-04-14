import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import data.forming.Answer;
import data.forming.Question;
import org.junit.jupiter.api.Test;

import java.text.Normalizer;
import java.util.Arrays;
import java.util.List;

public class FormerToolTest {
    @Test
    public void testing() throws JsonProcessingException {
        Question q = new Question();
        q.setText("asdasas");

        Answer ans = new Answer();
        ans.setText("1");

        Answer ans2 = new Answer();
        ans2.setText("2");
        ans2.setCorrect(true);

        q.setAnswers(Arrays.asList(ans,ans2));

        String json = new ObjectMapper().writeValueAsString(q);
        FormerTool.update("Question",json);
        List l  = FormerTool.getAll("Question","");

    }
}
