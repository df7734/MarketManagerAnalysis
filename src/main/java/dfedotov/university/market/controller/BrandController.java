package dfedotov.university.market.controller;

import dfedotov.university.market.service.BrandService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/brands")
public class BrandController {
    private final BrandService brandService;

    @GetMapping
    public String getBrands(Model model) {
        model.addAttribute("brands", brandService.getAllBrands());
        return "brands";
    }
}
