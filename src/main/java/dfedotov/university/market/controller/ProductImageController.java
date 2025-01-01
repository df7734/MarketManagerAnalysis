package dfedotov.university.market.controller;

import dfedotov.university.market.service.ProductImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@RequestMapping("/images")
@RequiredArgsConstructor
public class ProductImageController {

    private final ProductImageService productImageService;

    @GetMapping("/upload")
    public String showUploadPage() {
        return "image-upload";
    }

    @PostMapping("/upload")
    public String uploadImage(
            @RequestParam("productId") Long productId,
            @RequestParam("file") MultipartFile file,
            Model model) {
        try {
            productImageService.saveImage(productId, file);
            model.addAttribute("message", "Image uploaded successfully!");
        } catch (IOException e) {
            model.addAttribute("error", "Error uploading image: " + e.getMessage());
        }
        return "image-upload";
    }
}
