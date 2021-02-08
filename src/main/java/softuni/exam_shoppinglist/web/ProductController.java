package softuni.exam_shoppinglist.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/products")
public class ProductController {


    @GetMapping("/add")
    public String productsAdd(){
        return "product-add";
    }

    @PostMapping("/add")
    public String productsAddConfirm(){
        return "redirect:home";
    }
}
