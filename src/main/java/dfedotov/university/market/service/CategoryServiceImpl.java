package dfedotov.university.market.service;

import dfedotov.university.market.entity.Category;
import dfedotov.university.market.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    @Override
    @SneakyThrows
    @Cacheable("category_data")
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }
}
