package dfedotov.university.market.service;

import dfedotov.university.market.entity.Product;
import dfedotov.university.market.entity.ProductImage;
import dfedotov.university.market.repository.ProductImageRepository;
import dfedotov.university.market.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ProductImageRepository productImageRepository;

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
    public List<Product> getPopularProducts() {
        return productRepository.findTop50PopularProducts();
    }

    @Override
    public List<String> getImages(Long productId) {
        List<ProductImage> images = productImageRepository.findByProductId(productId);
        List<String> imageUrls = new ArrayList<>();
        if(!images.isEmpty()){
            for(ProductImage image: images){
                imageUrls.add(image.getImageUrl());
            }
        }
        return imageUrls;
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
