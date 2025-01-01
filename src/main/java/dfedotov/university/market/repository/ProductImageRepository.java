package dfedotov.university.market.repository;

import dfedotov.university.market.entity.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductImageRepository extends JpaRepository<ProductImage, Long>{
    @Query(value = "SELECT p FROM ProductImage p WHERE p.product.id = :productId")
    List<ProductImage> findByProductId(Long productId);
}
