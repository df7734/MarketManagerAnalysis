package dfedotov.university.market.service;

import dfedotov.university.market.entity.Product;
import dfedotov.university.market.entity.ProductImageBLOB;
import dfedotov.university.market.repository.ProductImageBLOBRepository;
import dfedotov.university.market.repository.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProductImageServiceImpl implements ProductImageService{
    private ProductImageBLOBRepository productImageBLOBRepository;

    private ProductRepository productRepository;

    public void saveImage(Long productId, MultipartFile file) throws IOException {
        Optional<Product> productOptional = productRepository.findById(productId);
        if (productOptional.isEmpty()) {
            throw new IllegalArgumentException("Product not found with ID: " + productId);
        }

        Product product = productOptional.get();
        ProductImageBLOB productImage = new ProductImageBLOB();
        productImage.setImageData(file.getBytes());
        productImage.setFileName(file.getOriginalFilename());
        productImage.setImageType(file.getContentType());
        productImage.setProduct(product);

        productImageBLOBRepository.save(productImage);
    }

    @Override
    @Transactional
    public List<ProductImageBLOB> getImagesForProduct(Long productId) {
        return productImageBLOBRepository.findByProductId(productId);
    }

    @Override
    public byte[] getImageData(Long id) {
        ProductImageBLOB image = productImageBLOBRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Image not found for id " + id));
        return image.getImageData();
    }
}
