import bdWorkers.SessionFactoryFactory;
import data.forming.Answer;
import data.forming.Question;
import data.users.Group;

import org.hibernate.criterion.Restrictions;

import org.hibernate.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestDB {
    @BeforeAll
    public static void delAllGroupsBefore(){
        BDWorker worker = new BDWorker();
        worker.delete(Group.class,"");
        worker.delete(Answer.class,"");
        worker.delete(Question.class,"");

    }

    @AfterAll
    public static void delAllGroupsAftter(){
        BDWorker worker = new BDWorker();
        worker.delete(Group.class,"");
        worker.delete(Answer.class,"");
        worker.delete(Question.class,"");
    }

    @Test
    public void testGroupINSERTSELECTDELETEUPDATE() {
        //1 - создать два объекта
        Group group1 = new Group();
        group1.setCode("ИПБ-19");
        group1.setName("Лютая ВАЩЕ инженерия");

        Group group2 = new Group();
        group2.setCode("ИПБ=20");
        group2.setName("ИНЖЕНЕРИЯ ПОМЕНЬШЕ");
        //загрузить в таблицу
        BDWorker worker = new BDWorker();

        worker.updateOrAdd(group1);
        worker.updateOrAdd(group2);
        Assertions.assertEquals(worker.getObjects(Group.class,"").size(),2); // в бд всего две группы
        //сделать выборку
        String code1 = group1.getCode();
        String code2 = worker.find(Group.class,"bean.code = '" + code1 + "'").getCode();
        Assertions.assertEquals(code1,code2);
        //сделать изменения
        group2 = worker.find(Group.class,"bean.code = '" + group2.getCode()+ "'");
        group2.setCode("newCode");
        worker.updateOrAdd(group2);
        Assertions.assertNotNull(worker.find(Group.class,"bean.code = 'newCode'"));
        Assertions.assertEquals(worker.find(Group.class,"bean.code = 'newCode'").getCode(),"newCode");
        //удалить
        group2 = worker.find(Group.class,group2.getId());
        worker.delete(group2);
        Assertions.assertEquals(worker.getObjects(Group.class,"").size(),1);//проверить

    }

    @Test
    public void testObjectParameters(){
        Answer answer1 = new Answer();
        Answer answer2 = new Answer();
        Answer answer3 = new Answer();

        answer1.setText("ans1");
        answer2.setText("ans2");
        answer2.setCorrect(true);
        answer3.setText("ans3");

        Question question = new Question();
        question.setText("text");
        question.setAnswers(Arrays.asList(answer1, answer2, answer3));

        BDWorker worker = new BDWorker();
        worker.updateOrAdd(question);

        Question question2 = worker.find(Question.class,question.getId());

        Assertions.assertEquals(question.getText(),question2.getText());
        Assertions.assertEquals(question.getAnswers().get(0).getText(),question2.getAnswers().get(0).getText());
        Assertions.assertEquals(question.getAnswers().get(0).getCorrect(),question2.getAnswers().get(0).getCorrect());
        Assertions.assertEquals(question.getAnswers().get(1).getText(),question2.getAnswers().get(1).getText());
        Assertions.assertEquals(question.getAnswers().get(1).getCorrect(),question2.getAnswers().get(1).getCorrect());
        Assertions.assertEquals(question.getAnswers().get(2).getText(),question2.getAnswers().get(2).getText());
        Assertions.assertEquals(question.getAnswers().get(2).getCorrect(),question2.getAnswers().get(2).getCorrect());

        Answer answer = question.getAnswers().get(0);
        Assertions.assertEquals(answer.getText(),worker.find(Answer.class,"text = '" + answer.getText() + "'").getText());

    }
}
