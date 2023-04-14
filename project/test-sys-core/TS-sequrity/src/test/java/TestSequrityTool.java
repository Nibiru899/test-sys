import data.forming.Answer;
import data.forming.Question;
import data.users.Group;
import data.users.Password;
import data.users.User;
import org.junit.jupiter.api.*;
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestSequrityTool {
    @BeforeAll
    public static void delAllGroupsBefore(){
        BDWorker worker = new BDWorker();
        worker.delete(User.class,"");


    }

    @AfterAll
    public static void delAllGroupsAftter(){
        BDWorker worker = new BDWorker();
        worker.delete(User.class,"");
    }

    @Test
    @Order(1)
    public void register(){
        Long id = SecurityTool.register("Иван","Иванов", "Иванов","vany_OK_2007","secretPass321!");
        //есть аккаунт
        BDWorker worker = new BDWorker();
        User user = worker.find(User.class,"login = 'vany_OK_2007'");
        Assertions.assertNotNull(user);
        //пароль зашифрован
        Assertions.assertNull(worker.find(User.class,"password.text = 'secretPass321!'"));
        Assertions.assertNull(worker.find(Password.class,"text = ''"));
        Assertions.assertTrue(id != -1);
        Assertions.assertFalse(
                SecurityTool.register("Иasdван","Иваsadasdнов", "Ивasdанов","vany_OK_2007","asdasd") != -1
        );
    }

    @Test
    @Order(2)
    public void authorise(){
        Assertions.assertTrue(SecurityTool.authorise("vany_OK_2007","secretPass321!"));
        Assertions.assertFalse(SecurityTool.authorise("vany_OK_2007","secretPass321"));
        Assertions.assertFalse(SecurityTool.authorise("vany_OK_2007","secretPass"));
        Assertions.assertFalse(SecurityTool.authorise("vany","secretPass"));

    }
    @Test
    @Order(3)
    public void change(){
        Assertions.assertTrue(SecurityTool.authorise("vany_OK_2007","secretPass321!"));
        SecurityTool.changePass("vany_OK_2007","secretPass321!","qwerty");
        Assertions.assertFalse(SecurityTool.authorise("vany_OK_2007","secretPass321!"));
        Assertions.assertTrue(SecurityTool.authorise("vany_OK_2007","qwerty"));
        SecurityTool.changePassNoOld("vany_OK_2007","admin");
        Assertions.assertFalse(SecurityTool.authorise("vany_OK_2007","qwerty"));
        Assertions.assertTrue(SecurityTool.authorise("vany_OK_2007","admin"));
    }

    @Test
    @Order(4)
    public void delete(){
        Assertions.assertTrue(SecurityTool.authorise("vany_OK_2007","admin"));
        Assertions.assertTrue(SecurityTool.delAcc("vany_OK_2007","admin"));
        Assertions.assertFalse(SecurityTool.authorise("vany_OK_2007","admin"));

    }


}
