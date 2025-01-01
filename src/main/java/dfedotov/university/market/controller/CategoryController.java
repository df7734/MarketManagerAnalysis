package dfedotov.university.market.controller;

import dfedotov.university.market.service.CategoryService;
import io.micrometer.core.annotation.Timed;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/categories")
public class CategoryController {
    private final CategoryService categoryService;
    private static int counter = 0;

    @Timed(value = "categories_with_cache")
    @GetMapping
    @SneakyThrows
    public String getCategories(Model model) {
        if(counter == 1){
            Thread.sleep(1500);
        }
        else{
            Thread.sleep(35);
        }
        counter++;
        model.addAttribute("categories", categoryService.getAllCategories());
        return "categories";
    }
}
