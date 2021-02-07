package softuni.exam_shoppinglist.service.impl;

import org.springframework.stereotype.Service;
import softuni.exam_shoppinglist.model.entity.CategoryEntity;
import softuni.exam_shoppinglist.model.entity.entityenum.CategoryEnum;
import softuni.exam_shoppinglist.repository.CategoryRepository;
import softuni.exam_shoppinglist.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void initCategories() {
        if(this.categoryRepository.count() == 0){
            CategoryEnum[] categories = CategoryEnum.values();
            for (CategoryEnum categoryEnum : categories){
                CategoryEntity categoryEntity = new CategoryEntity();
                categoryEntity.setName(categoryEnum);
                categoryEntity.setDescription(String.format("It is for %s products", categoryEnum.name()));
                this.categoryRepository.save(categoryEntity);
            }
        }
    }
}
