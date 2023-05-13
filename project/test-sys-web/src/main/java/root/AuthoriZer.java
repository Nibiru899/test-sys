package root;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;
import sequrity.SecurityTool;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.net.http.HttpRequest;


@Controller
public class AuthoriZer {
    @Autowired
    HttpSession session;

    private String getPage(String page){
        Boolean authorised = (Boolean) session.getAttribute("inSystem");
        if (authorised!=null && authorised.equals(Boolean.TRUE)){
            return "lol";
        } else {
            return page;
        }
    }

    @GetMapping("/")
    public String get(){
        return getPage("login");
    }

    @GetMapping("/login")
    public String getLoginScreen(Model model){
        return getPage("login");
    }

    @GetMapping("/registry")
    public String getRegisterScreen(Model model){
        return getPage("registry");
    }

    @PostMapping("/registry")
    public String register(Model model,
            @RequestParam(name = "name",required = false) String name,
            @RequestParam(name = "surname",required = false) String surname,
            @RequestParam(name = "fathername",required = false) String fathername,
            @RequestParam(name = "login",required = false) String log,
            @RequestParam(name = "password",required = false) String pass,
            @RequestParam(name = "level",required = false) String level
    ){
        String message = SecurityTool.register(name,surname,fathername,log,pass,level);
        if (message!=null){
            model.addAttribute("wrong",true);
            model.addAttribute("error",message);
            return "registry";
        } else {
            session.setAttribute("inSystem",Boolean.TRUE);
            session.setAttribute("username",log);
            return "lol";
        }
    }

    @PostMapping("/login")
    public String login(@RequestParam(name="login",required = false) String log, @RequestParam(name="password",required = false) String pass, Model model){
        if (log!=null && pass!=null && SecurityTool.authorise(log,pass)){
            session.setAttribute("inSystem",Boolean.TRUE);
            session.setAttribute("username",log);
            return "lol";
        } else {
            model.addAttribute("wrong",true);
            return "login";
        }
    }
}
