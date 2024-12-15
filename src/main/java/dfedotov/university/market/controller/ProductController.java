package dfedotov.university.market.controller;

import dfedotov.university.market.entity.Product;
import dfedotov.university.market.service.BrandService;
import dfedotov.university.market.service.CategoryService;
import dfedotov.university.market.service.ProductService;
import io.micrometer.core.annotation.Timed;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;
    private final CategoryService categoryService;
    private final BrandService brandService;


    @Timed(value = "my_custom_request_time")
    @GetMapping
    public String getProducts(Model model) {
        model.addAttribute("products", productService.getAllProducts());
        return "products";
    }

    @GetMapping("/{id}")
    public String getProductDetails(@PathVariable Long id, Model model) {
        Product product = productService.getProductById(id);
        model.addAttribute("product", product);
        return "product-details";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Product product = productService.getProductById(id);
        model.addAttribute("product", product);
        model.addAttribute("categories", categoryService.getAllCategories());
        model.addAttribute("brands", brandService.getAllBrands());
        return "product-edit";
    }

    @PostMapping("/edit")
    public String updateProduct(@ModelAttribute("product") Product product) {
        productService.updateProduct(product);
        return "redirect:/products/" + product.getId();
    }

    @PostMapping("/delete/{id}")
    public String deleteProduct(@PathVariable("id") Long id) {
        try {
            productService.deleteProduct(id);
        } catch (EntityNotFoundException e) {
            return "redirect:/products";
        }
        return "redirect:/products";
    }
}
