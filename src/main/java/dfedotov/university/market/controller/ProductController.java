package dfedotov.university.market.controller;

import dfedotov.university.market.entity.Product;
import dfedotov.university.market.entity.ProductImageBLOB;
import dfedotov.university.market.service.BrandService;
import dfedotov.university.market.service.CategoryService;
import dfedotov.university.market.service.ProductImageService;
import dfedotov.university.market.service.ProductService;
import io.micrometer.core.annotation.Timed;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;
    private final CategoryService categoryService;
    private final BrandService brandService;
    private final ProductImageService productImageService;

//    @Timed(value = "get_products_filter_by_price_and_name")
//    @GetMapping
//    public String getProducts(@RequestParam(required = false) Integer price,
//                              @RequestParam(required = false) String brandName,
//                              Model model) {
//        model.addAttribute("products", productService.getAllProducts(price, brandName));
//        return "products";
//    }

    @GetMapping
    public String getProducts(Model model) {
        model.addAttribute("products", productService.getAllProducts());
        return "products";
    }

    @GetMapping("/popular")
    public String getPopularProducts(Model model) {
        model.addAttribute("products", productService.getPopularProducts());
        return "products";
    }

    @GetMapping("/{id}")
    public String getProductDetailsBLOB(@PathVariable Long id, Model model) {
        Product product = productService.getProductById(id);
        List<ProductImageBLOB> images = productImageService.getImagesForProduct(id);
        model.addAttribute("product", product);
        model.addAttribute("images", images);
        return "product-details-2";
    }

    @GetMapping("/v2/{id}")
    public String getProductDetails(@PathVariable Long id, Model model) {
        Product product = productService.getProductById(id);
        model.addAttribute("product", product);
        model.addAttribute("images", productService.getImages(product.getId()));
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

    @GetMapping("/image/{id}")
    @ResponseBody
    public ResponseEntity<byte[]> getImage(@PathVariable Long id) {
        byte[] imageData = productImageService.getImageData(id);
        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(imageData);
    }
}
