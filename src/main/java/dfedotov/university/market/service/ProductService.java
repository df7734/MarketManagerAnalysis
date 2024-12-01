package dfedotov.university.market.service;

import dfedotov.university.market.entity.Product;

import java.util.List;

public interface ProductService {
    List<Product> getAllProducts();

    Product getProductById(Long id);

    void updateProduct(Product product);

    void deleteProduct(Long id);
}
