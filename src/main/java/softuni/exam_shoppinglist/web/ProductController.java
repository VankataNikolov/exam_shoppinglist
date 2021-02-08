package softuni.exam_shoppinglist.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import softuni.exam_shoppinglist.model.binding.ProductAddBindingModel;

import javax.validation.Valid;

@Controller
@RequestMapping("/products")
public class ProductController {


    @GetMapping("/add")
    public String productsAdd(Model model){
        if(!model.containsAttribute("productAddBindingModel")){
            model.addAttribute("productAddBindingModel", new ProductAddBindingModel());
        }

        return "product-add";
    }

    @PostMapping("/add")
    public String productsAddConfirm(@Valid ProductAddBindingModel productAddBindingModel,
                                     BindingResult bindingResult,
                                     RedirectAttributes redirectAttributes){
        if(bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("productAddBindingModel", productAddBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.productAddBindingModel", bindingResult);
            return "redirect:add";
        }

        //TODO business logic for adding product

        return "redirect:home";
    }
}
