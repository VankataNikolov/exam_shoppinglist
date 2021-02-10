package softuni.exam_shoppinglist.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam_shoppinglist.model.entity.CategoryEntity;
import softuni.exam_shoppinglist.model.entity.entityenum.CategoryEnum;
import softuni.exam_shoppinglist.model.service.CategoryServiceModel;
import softuni.exam_shoppinglist.repository.CategoryRepository;
import softuni.exam_shoppinglist.service.CategoryService;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;

    public CategoryServiceImpl(CategoryRepository categoryRepository, ModelMapper modelMapper) {
        this.categoryRepository = categoryRepository;
        this.modelMapper = modelMapper;
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

    @Override
    public List<CategoryServiceModel> getAllCategories() {
        List<CategoryServiceModel> categories = new ArrayList<>();
        List<CategoryEntity> allCategories = this.categoryRepository.findAll();
        allCategories.forEach(cat -> categories.add(this.modelMapper.map(cat, CategoryServiceModel.class)));
        return categories;
    }

    @Override
    public CategoryEntity getCategoryById(Long id) {
        return this.categoryRepository.getOne(id);
    }
}
