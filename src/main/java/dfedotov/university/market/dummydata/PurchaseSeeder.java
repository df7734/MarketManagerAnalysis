package dfedotov.university.market.dummydata;

import com.github.javafaker.Faker;
import dfedotov.university.market.entity.Product;
import dfedotov.university.market.entity.Purchase;
import dfedotov.university.market.repository.ProductRepository;
import dfedotov.university.market.repository.PurchaseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Random;

@Component
@RequiredArgsConstructor
public class PurchaseSeeder implements CommandLineRunner {

    private final PurchaseRepository purchaseRepository;
    private final ProductRepository productRepository;

    private final Faker faker = new Faker();
    private final Random random = new Random();

    @Override
    public void run(String... args) throws Exception {
        if (purchaseRepository.count() == 0) {
            List<Product> products = productRepository.findAll();

            if (products.isEmpty()) {
                System.out.println("AT FIRST FILL PRODUCT TABLE.");
                return;
            }

            for (int i = 0; i < 900; i++) {
                Purchase purchase = new Purchase();

                purchase.setProduct(getRandomElement(products));
                purchase.setCreatedDate(faker.date()
                        .past(365, java.util.concurrent.TimeUnit.DAYS)
                        .toInstant()
                        .atZone(java.time.ZoneId.systemDefault())
                        .toLocalDateTime());
                purchase.setAddress(faker.address().fullAddress());

                purchaseRepository.save(purchase);
            }
            System.out.println("New purchases were added to DB.");
        }
    }

    private <T> T getRandomElement(List<T> list) {
        return list.get(random.nextInt(list.size()));
    }
}
