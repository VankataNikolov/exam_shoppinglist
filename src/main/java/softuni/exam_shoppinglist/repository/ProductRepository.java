package softuni.exam_shoppinglist.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import softuni.exam_shoppinglist.model.entity.ProductEntity;
import softuni.exam_shoppinglist.model.entity.entityenum.CategoryEnum;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

    List<ProductEntity> findByCategoryName(CategoryEnum categoryEnum);

    @Query("SELECT SUM(p.price) FROM ProductEntity p")
    BigDecimal getSumOfPrice();
}
