package softuni.exam_shoppinglist.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import softuni.exam_shoppinglist.model.entity.entityenum.CategoryEnum;
import softuni.exam_shoppinglist.model.service.ProductServiceModel;
import softuni.exam_shoppinglist.security.CurrentUser;
import softuni.exam_shoppinglist.service.ProductService;

import java.math.BigDecimal;
import java.util.List;

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
        if(!this.currentUser.isLogged()){
            return "redirect:/";
        }

        List<ProductServiceModel> food = this.productService.getProductsByCategory(CategoryEnum.FOOD);
        List<ProductServiceModel> drink = this.productService.getProductsByCategory(CategoryEnum.DRINK);
        List<ProductServiceModel> household = this.productService.getProductsByCategory(CategoryEnum.HOUSEHOLD);
        List<ProductServiceModel> other = this.productService.getProductsByCategory(CategoryEnum.OTHER);

        model.addAttribute("food", food);
        model.addAttribute("drink", drink);
        model.addAttribute("household", household);
        model.addAttribute("other", other);

        BigDecimal priceSum = this.productService.getPriceSum();

        model.addAttribute("priceSum", priceSum == null ? 0 : priceSum);

        return "home";
    }
}
