package softuni.exam_shoppinglist.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import softuni.exam_shoppinglist.model.binding.ProductAddBindingModel;
import softuni.exam_shoppinglist.security.CurrentUser;
import softuni.exam_shoppinglist.service.CategoryService;
import softuni.exam_shoppinglist.service.ProductService;

import javax.validation.Valid;

@Controller
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;
    private final CategoryService categoryService;
    private final CurrentUser currentUser;
    public ProductController(ProductService productService, CategoryService categoryService, CurrentUser currentUser) {
        this.productService = productService;
        this.categoryService = categoryService;
        this.currentUser = currentUser;
    }

    @GetMapping("/add")
    public String productsAdd(Model model){
        if(!this.currentUser.isLogged()){
            return "redirect:/";
        }
        if(!model.containsAttribute("productAddBindingModel")){
            model.addAttribute("productAddBindingModel", new ProductAddBindingModel());
        }
        model.addAttribute("categories", categoryService.getAllCategories());
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

        this.productService.addProduct(productAddBindingModel);

        return "redirect:/";
    }

    @GetMapping("/buy/{id}")
    public String buy(@PathVariable("id") Long id){
        this.productService.buyProduct(id);
        return "redirect:/";
    }

    @GetMapping("/buyAll")
    public String buyAll(){
        this.productService.buyAllProducts();
        return "redirect:/";
    }
}
