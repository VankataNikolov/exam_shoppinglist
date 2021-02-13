package softuni.exam_shoppinglist.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam_shoppinglist.model.binding.ProductAddBindingModel;
import softuni.exam_shoppinglist.model.entity.ProductEntity;
import softuni.exam_shoppinglist.model.entity.entityenum.CategoryEnum;
import softuni.exam_shoppinglist.model.service.ProductServiceModel;
import softuni.exam_shoppinglist.repository.ProductRepository;
import softuni.exam_shoppinglist.service.CategoryService;
import softuni.exam_shoppinglist.service.ProductService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryService categoryService;
    private final ModelMapper modelMapper;

    public ProductServiceImpl(ProductRepository productRepository, CategoryService categoryService, ModelMapper modelMapper) {
        this.productRepository = productRepository;
        this.categoryService = categoryService;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<ProductServiceModel> getProductsByCategory(CategoryEnum categoryEnum) {
        List<ProductServiceModel> productsByCategory = new ArrayList<>();
        List<ProductEntity> byCategoryName = this.productRepository.findByCategoryName(categoryEnum);
        byCategoryName.forEach(productEntity ->
            productsByCategory.add(this.modelMapper.map(productEntity, ProductServiceModel.class))
        );

        return productsByCategory;

    }

    @Override
    public void addProduct(ProductAddBindingModel productAddBindingModel) {
        ProductEntity newProduct = this.modelMapper.map(productAddBindingModel, ProductEntity.class);
        newProduct.setCategory(this.categoryService.getCategoryById(productAddBindingModel.getCategoryId()));
        this.productRepository.save(newProduct);

    }

    @Override
    public void buyProduct(Long id) {
        this.productRepository.deleteById(id);
    }

    @Override
    public BigDecimal getPriceSum() {

        return this.productRepository.getSumOfPrice();
    }

    @Override
    public void buyAllProducts() {
        this.productRepository.deleteAll();
    }
}
