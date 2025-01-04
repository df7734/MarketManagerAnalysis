package dfedotov.university.market.service;

import dfedotov.university.market.repository.BrandRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
@Slf4j
public class RequestService {
    private final RestTemplate restTemplate;
    private final BrandRepository brandRepository;
    private static final Logger logger = LoggerFactory.getLogger(RequestService.class);
    private static final Random random = new Random();

    public void sendRequests() {
        List<String> brandNames = brandRepository.findAllBrandNames();
        String baseUrl = "http://localhost:8080/products?price={price}&brandName={brandName}";

        for (int i = 0; i < 50; i++) {
            int price = 10 + random.nextInt(991);

            String brandName = brandNames.get(random.nextInt(brandNames.size()));

            String url = baseUrl.replace("{price}", String.valueOf(price))
                    .replace("{brandName}", brandName);

            String response = restTemplate.getForObject(url, String.class);
            logger.info("PRICE: {}", price);
            logger.info("BRAND: {}", brandName);
            logger.info("Response {}:\n{}", i + 1, response);
        }
    }

    public void sendRequests2() {
        String baseUrl = "http://localhost:8080/purchases";

        for (int i = 0; i < 100; i++) {
            String response = restTemplate.getForObject(baseUrl, String.class);
            logger.info("Response {}:\n{}", i + 1, response);
        }
    }
}
