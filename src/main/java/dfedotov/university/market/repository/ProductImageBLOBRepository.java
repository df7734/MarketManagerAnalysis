package dfedotov.university.market.repository;

import dfedotov.university.market.entity.ProductImageBLOB;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductImageBLOBRepository extends JpaRepository<ProductImageBLOB, Long> {

    List<ProductImageBLOB> findByProductId(Long productId);
}
