package softuni.exam_shoppinglist.service;

import softuni.exam_shoppinglist.model.entity.CategoryEntity;
import softuni.exam_shoppinglist.model.service.CategoryServiceModel;

import java.util.List;

public interface CategoryService {

    void initCategories();

    List<CategoryServiceModel> getAllCategories();

    CategoryEntity getCategoryById(Long id);
}
