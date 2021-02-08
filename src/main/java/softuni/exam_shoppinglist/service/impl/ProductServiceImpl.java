package softuni.exam_shoppinglist.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam_shoppinglist.model.entity.ProductEntity;
import softuni.exam_shoppinglist.model.entity.entityenum.CategoryEnum;
import softuni.exam_shoppinglist.model.service.ProductServiceModel;
import softuni.exam_shoppinglist.repository.ProductRepository;
import softuni.exam_shoppinglist.service.ProductService;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;

    public ProductServiceImpl(ProductRepository productRepository, ModelMapper modelMapper) {
        this.productRepository = productRepository;
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
}
