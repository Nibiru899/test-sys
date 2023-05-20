package root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import sequrity.SecurityTool;

import javax.servlet.http.HttpSession;


@Controller
public class Authoriser {
    @Autowired
    HttpSession session;


    private void configureNavbar(String role){
        boolean isTeacherOrAdmin = role.equals("TEACHER") || role == "ADMINISTRATOR";
        session.setAttribute("hideCabinet",false);
        session.setAttribute("hideThemes",isTeacherOrAdmin);
        session.setAttribute("hidePlans",isTeacherOrAdmin);
        session.setAttribute("hideTests",false);
        session.setAttribute("hideStats",isTeacherOrAdmin);
    }

    private void onNavbar(){
        session.setAttribute("hideNavbar",false);
    }

    private void offNavbar(){
        session.setAttribute("hideNavbar",true);
    }
    private String getPage(String page){
        Boolean authorised = (Boolean) session.getAttribute("inSystem");
        if (authorised!=null && authorised.equals(Boolean.TRUE)){
            onNavbar();
            return "nullPage";
        } else {
            offNavbar();
            return page;
        }
    }

    @GetMapping("/")
    public String get(){
        onNavbar();
        return "nullPage";
    }

    @GetMapping("/login")
    public String getLoginScreen(Model model){
        model.addAttribute("ex","exxx");
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
            @RequestParam(name = "role",required = false) String role
    ){
        String message = SecurityTool.register(name,surname,fathername,log,pass,role);
        if (message!=null){
            model.addAttribute("wrong",true);
            model.addAttribute("error",message);

            return "registry";
        } else {
            session.setAttribute("inSystem",Boolean.TRUE);
            session.setAttribute("username",log);
            session.setAttribute("role",role);
            return "nullPage";
        }
    }

    @PostMapping("/login")
    public String login(@RequestParam(name="login",required = false) String log, @RequestParam(name="password",required = false) String pass, Model model){
        String role = SecurityTool.authorise(log,pass);
        if (log!=null && pass!=null && role!=null){
            session.setAttribute("inSystem",Boolean.TRUE);
            session.setAttribute("username",log);
            session.setAttribute("role",role);
            configureNavbar(role);
            return "nullPage";
        } else {
            model.addAttribute("wrong",true);
            return "login";
        }
    }

    @PostMapping("delAcc")
    public String delete(@RequestParam(name="login",required = false) String log, @RequestParam(name="password",required = false) String pass, Model model){
        if (Boolean.TRUE.equals(session.getAttribute("inSystem"))){
            SecurityTool.delAcc(log,pass);
        }
        return "nullPage";
    }

    @GetMapping("/off")
    public String off(){
        Boolean inSystem = (Boolean) session.getAttribute("inSystem");
        if (inSystem){
            session.setAttribute("inSystem",Boolean.FALSE);
            session.setAttribute("username",null);
            session.setAttribute("role",null);
        }
        return "login";
    }
}
