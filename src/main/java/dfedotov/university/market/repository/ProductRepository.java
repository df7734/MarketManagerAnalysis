package dfedotov.university.market.repository;

import dfedotov.university.market.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query(value = "SELECT p FROM Product p JOIN Brand b " +
            "ON p.brand.id = b.id " +
            "WHERE (:price IS NULL OR p.price >= :price) " +
            "AND (:brandName IS NULL OR b.name ILIKE :brandName)", nativeQuery = false)
    List<Product> findByPriceAndBrandName(@Param("price") Integer price, @Param("brandName") String brandName);

    @Query( "SELECT p.product " +
            "FROM Purchase p " +
            "GROUP BY p.product " +
            "ORDER BY COUNT(p) DESC " +
            "LIMIT 50")
    List<Product> findTop50PopularProducts();
}
