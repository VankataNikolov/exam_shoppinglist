package softuni.exam_shoppinglist.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import softuni.exam_shoppinglist.security.CurrentUser;

@Controller
public class HomeController {

    private final CurrentUser currentUser;

    public HomeController(CurrentUser currentUser) {
        this.currentUser = currentUser;
    }

    @GetMapping("/")
    public String index(){
        if(this.currentUser.isLogged()){
            return "home";
        }
        return "index";
    }
}
