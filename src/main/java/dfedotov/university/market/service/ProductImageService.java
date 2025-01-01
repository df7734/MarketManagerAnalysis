package dfedotov.university.market.service;

import dfedotov.university.market.entity.ProductImageBLOB;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ProductImageService {
    void saveImage(Long productId, MultipartFile file) throws IOException;

    List<ProductImageBLOB> getImagesForProduct(Long id);

    byte[] getImageData(Long id);
}
