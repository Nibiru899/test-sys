import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class AuthoriZator {
    @PostMapping("/loginScreen")
    public String getLoginScreen(Model model){
        model.addAttribute("wrong",false);
        return "loginScreen";
    }

    @PostMapping("/registerScreen")
    public String getRegisterScreen(Model model){
        model.addAttribute("wrong",false);
        return "registerScreen";
    }


    @PostMapping("/lol")
    public String getNext(){
        return "lol";
    }

    @PostMapping("/registerUser")
    public String register(@RequestBody JsonNode body, Model model){
        String name = body.get("name").asText();
        String surname = body.get("surname").asText();
        String fathername = body.get("fathername").asText();

        String log = body.get("login").asText();
        String pass = body.get("password").asText();
        String level = body.get("level").asText();
        Long id = SecurityTool.register(name,surname,fathername,log,pass,level);
        if (id!=null && id>-1){
            model.addAttribute("wrong",true);
            return "registerScreen";
        } else {
            model.addAttribute("wrong",false);
            return "loginScreen";
        }
    }


    @PostMapping("/loginUser")
    public String login(@RequestBody JsonNode body,Model model){
        String log = body.get("login").asText();
        String pass = body.get("password").asText();
        if (SecurityTool.authorise(log,pass)){
            return "lol";
        } else {
            model.addAttribute("wrong",true);
            return "loginScreen";
        }
    }
}
