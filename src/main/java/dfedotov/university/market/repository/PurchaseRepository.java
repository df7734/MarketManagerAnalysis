package dfedotov.university.market.repository;

import dfedotov.university.market.entity.Purchase;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PurchaseRepository extends JpaRepository<Purchase, Long> {

    @Query(value = "SELECT p FROM Purchase p ORDER BY p.id ASC LIMIT 100")
    @EntityGraph(attributePaths = "product")
    List<Purchase> findAllLimit100();
}
