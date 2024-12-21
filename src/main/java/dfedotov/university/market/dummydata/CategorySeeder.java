package dfedotov.university.market.dummydata;

import com.github.javafaker.Faker;
import dfedotov.university.market.entity.Category;
import dfedotov.university.market.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CategorySeeder implements CommandLineRunner {

    private final CategoryRepository categoryRepository;

    @Override
    public void run(String... args) throws Exception {
        if (categoryRepository.count() == 0) {
            Faker faker = new Faker();

            for (int i = 0; i < 20; i++) {
                Category category = new Category();
                category.setName(faker.commerce().department());
                category.setDescription(faker.lorem().sentence(10));
                category.setImage("https://via.placeholder.com/150");
                categoryRepository.save(category);
            }
            System.out.println("20 categories were added to DB.");
        }
    }
}
