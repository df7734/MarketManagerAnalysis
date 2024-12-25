package dfedotov.university.market.dummydata;


import com.github.javafaker.Faker;
import dfedotov.university.market.entity.Brand;
import dfedotov.university.market.repository.BrandRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BrandSeeder  implements CommandLineRunner {
    private final BrandRepository brandRepository;

    @Override
    public void run(String... args) throws Exception {
        if (brandRepository.count() == 0) {
            Faker faker = new Faker();

            for (int i = 0; i < 990; i++) {
                Brand brand = new Brand();
                brand.setName(faker.commerce().productName());
                brand.setDescription(faker.lorem().sentence(10));
                brand.setImage("https://via.placeholder.com/150");
                brandRepository.save(brand);
            }
            System.out.println("10 brands were added to DB.");
        }
    }
}
