package softuni.exam_shoppinglist.service;

import softuni.exam_shoppinglist.model.entity.entityenum.CategoryEnum;
import softuni.exam_shoppinglist.model.service.ProductServiceModel;

import java.util.List;

public interface ProductService {

    List<ProductServiceModel> getProductsByCategory(CategoryEnum categoryEnum);
}
