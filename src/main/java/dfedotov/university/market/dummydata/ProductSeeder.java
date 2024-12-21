package dfedotov.university.market.dummydata;

import com.github.javafaker.Faker;
import dfedotov.university.market.entity.Brand;
import dfedotov.university.market.entity.Category;
import dfedotov.university.market.entity.Product;
import dfedotov.university.market.repository.BrandRepository;
import dfedotov.university.market.repository.CategoryRepository;
import dfedotov.university.market.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Random;

@Component
@RequiredArgsConstructor
public class ProductSeeder implements CommandLineRunner {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final BrandRepository brandRepository;

    private final Faker faker = new Faker();
    private final Random random = new Random();

    @Override
    public void run(String... args) throws Exception {
        if (productRepository.count() == 0) {
            List<Category> categories = categoryRepository.findAll();
            List<Brand> brands = brandRepository.findAll();

            if (categories.isEmpty() || brands.isEmpty()) {
                System.out.println("AT FIRST FILL OTHER TABLES.");
                return;
            }

            for (int i = 0; i < 50; i++) {
                Product product = new Product();
                product.setName(faker.commerce().productName());
                product.setDescription(faker.lorem().sentence(15));
                String priceString = faker.commerce().price(10.0, 1000.0);
                priceString = priceString.replace(",", ".");
                product.setPrice(Double.parseDouble(priceString));
                product.setQuantity(random.nextInt(1, 100));
                product.setImage("https://via.placeholder.com/150");

                product.setCategory(getRandomElement(categories));
                product.setBrand(getRandomElement(brands));

                productRepository.save(product);
            }
            System.out.println("50 products were added to DB.");
        }
    }

    private <T> T getRandomElement(List<T> list) {
        return list.get(random.nextInt(list.size()));
    }
}
