package softuni.exam_shoppinglist.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import softuni.exam_shoppinglist.security.CurrentUser;
import softuni.exam_shoppinglist.service.ProductService;

@Controller
public class HomeController {

    private final CurrentUser currentUser;
    private final ProductService productService;

    public HomeController(CurrentUser currentUser, ProductService productService) {
        this.currentUser = currentUser;
        this.productService = productService;
    }

    @GetMapping("/")
    public String index(){
        if(this.currentUser.isLogged()){
            return "redirect:home";
        }
        return "index";
    }

    @GetMapping("/home")
    public String home(Model model){


        return "home";
    }
}
