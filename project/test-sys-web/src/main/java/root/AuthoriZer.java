package root;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import sequrity.SecurityTool;


@Controller
public class AuthoriZer {
    @GetMapping("/login")
    public String getLoginScreen(Model model){
        return "login";
    }

    @GetMapping("/registry")
    public String getRegisterScreen(Model model){
        return "registry";
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
            return "loginScreen";
        }
    }
}
