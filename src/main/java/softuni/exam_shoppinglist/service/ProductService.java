package softuni.exam_shoppinglist.service;

import softuni.exam_shoppinglist.model.binding.ProductAddBindingModel;
import softuni.exam_shoppinglist.model.entity.entityenum.CategoryEnum;
import softuni.exam_shoppinglist.model.service.ProductServiceModel;

import java.math.BigDecimal;
import java.util.List;

public interface ProductService {

    List<ProductServiceModel> getProductsByCategory(CategoryEnum categoryEnum);


    void addProduct(ProductAddBindingModel productAddBindingModel);

    void buyProduct(Long id);

    BigDecimal getPriceSum();

    void buyAllProducts();
}
