package dfedotov.university.market.service;

import dfedotov.university.market.entity.Product;
import dfedotov.university.market.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> getAllProducts(Integer price, String brandName) {
        if(brandName != null){
            brandName = "%" + brandName + "%";
        }
        return productRepository.findByPriceAndBrandName(price, brandName);
    }

    @Override
    public Product getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Product not found with ID: " + id));
    }

    @Override
    public void updateProduct(Product product) {
        Product existingProduct = getProductById(product.getId());

        existingProduct.setName(product.getName());
        existingProduct.setDescription(product.getDescription());
        existingProduct.setPrice(product.getPrice());
        existingProduct.setQuantity(product.getQuantity());
        existingProduct.setImage(product.getImage());
        existingProduct.setCategory(product.getCategory());
        existingProduct.setBrand(product.getBrand());

        productRepository.save(existingProduct);
    }

    @Override
    public void deleteProduct(Long productId) {
        if (productRepository.existsById(productId)) {
            productRepository.deleteById(productId);
        } else {
            throw new EntityNotFoundException("Product not found with id " + productId);
        }
    }
}
